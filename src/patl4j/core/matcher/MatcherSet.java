package patl4j.core.matcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

import patl4j.common.ast.pattern.ModInstruction;
import patl4j.common.ast.pattern.Rule;
import patl4j.common.java.analyzer.Analyzer;
import patl4j.common.tools.ErrorManager;
import patl4j.common.util.Pair;
import patl4j.common.util.VariableContext;
import patl4j.core.shifter.datastructure.BlockSTreeNode;

public class MatcherSet {
	
	List<Matcher> matchers = new ArrayList<Matcher>();
	
	// Which method is the matcher set working in;
	String methodName;
	
	public MatcherSet(List<Rule> rules) {
		for (Rule r : rules) {
			matchers.add(new Matcher(r));
		}
	}
	
	// Specify which method is the matcher set matching in
	public void setMethodName(String methodName) {
		this.methodName = methodName;
		for (Matcher m : this.matchers) {
			m.setMethodName(this.methodName);
		}
	}
	
	// Update the matcher collection set with a Java Statement,
	// which will then shift the responsibility to matchers
	public MatcherSet accept(Statement s, VariableContext context) {
		List<Matcher> collection = new ArrayList<Matcher>();
		for (Matcher i : matchers) {
			// After update, both the updated and the un-updated matcher will be genearted
			List<Matcher> updated = i.accept(s, context);
			for (Matcher j : updated) {
				collection.add(j);
			}
		}
		matchers = collection;
		return this;
	}
	
	// Clear all unfinished matcher, note that after clearence, the original unfinished matcher is gone forever.
	public MatcherSet clear() {
		List<Matcher> cleared = new ArrayList<Matcher>();
		for (Matcher m : matchers) {
			if (m.getMatchPoint().equals(-1)) {
				cleared.add(m);
			}
		}
		HashSet<Integer> deleteMatcher = new HashSet<>();
		// Check whether there is a statement binded to more than one minus mod instruction
		for(int i = 0; i < cleared.size(); i++){
			for(int j = i + 1; j < cleared.size(); j++){
				Matcher m = cleared.get(i);
				Matcher n = cleared.get(j);
				for (Pair<ModInstruction, Optional<Statement>> p : m.getInstructionBindings()) {
					for (Pair<ModInstruction, Optional<Statement>> q : n.getInstructionBindings()) {
						if (p.getSecond().isPresent() && q.getSecond().isPresent()) {
							if (p.getFirst().isMinus() && q.getFirst().isMinus() && p.getSecond().get().getStartPosition() == q.getSecond().get().getStartPosition()) {
								ErrorManager.Message("Statement bound to mutiple minus instr", p.getSecond().get().toString() + "@" + p.getSecond().get().getStartPosition());
								if(m.getInstructionBindings().size() > n.getInstructionBindings().size()){
									deleteMatcher.add(j);
								}else{
									deleteMatcher.add(i);
								}
							}
						}
					}
				}
			}
		}
		
		List<Integer> list = new ArrayList<>(deleteMatcher);
		
		Collections.sort(list);
	
		int index = 0;
		this.matchers.clear();
		for(int i =0; i < cleared.size(); i++){
			if(index < list.size() && i == list.get(index)){
				index ++;
				continue;
			}
			this.matchers.add(cleared.get(i));
		}
		
		
//		for (Matcher m : cleared) {
//			for (Matcher n : cleared) {
//				if (m.equals(n)) continue;
//				for (Pair<ModInstruction, Optional<Statement>> p : m.getInstructionBindings()) {
//					for (Pair<ModInstruction, Optional<Statement>> q : n.getInstructionBindings()) {
//						if (p.getSecond().isPresent() && q.getSecond().isPresent()) {
//							if (p.getFirst().isMinus() && q.getFirst().isMinus() && p.getSecond().get().getStartPosition() == q.getSecond().get().getStartPosition()) {
//								ErrorManager.Message("Statement bound to mutiple minus instr", p.getSecond().get().toString() + "@" + p.getSecond().get().getStartPosition());
//							}
//						}
//					}
//				}
//			}
//		}
		
		
		
//		for(Integer m : deleteMatcher){
//			cleared.remove(m);
//		}
		
//		this.matchers = cleared;
		return this;
	}
	
	// Warning: might cause some order problem
	// TODO: we might be able to improve its performance using an alternative implementation
	public static MatcherSet merge(MatcherSet u, MatcherSet v) {
		for (Matcher i : v.matchers) {
			boolean flag = false;
			for (Matcher j : u.matchers) {
				if (j.equals(i)) { 
					flag = true;
					break;
				}
			}
			if (flag == false) {
				u.matchers.add(i);
			}
		}
		return u;
	}
	
	@Override
	public String toString() {
		String str = "";
		for (Matcher i : matchers) {
			str += i.toString() + "\n\n";
		}
		return str;
	}
	
	/**
	 * Check whether the given statement s is matched to any matcher with a minus pattern. 
	 * @param s: The given statement to be checked
	 * @return a boolean representing whether it is matched to a minus pattern
	 */
	public boolean stmtMathedToMinus(Statement s) {
		for (Matcher m : this.matchers) {
			if (m.matchedToMinusStmtPattern(s)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Used to find the generation point of the statements
	 * @param s: statement to be checked
	 * @return whether or not
	 */
	public boolean stmtMatchedToGenPoint(Statement s) {
		for (Matcher m : this.matchers) {
			if (m.isGenPointForTheMatcher(s)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Given a statement, if the statement s is the last statement matched in the matcher, 
	 * 	then the corresponding generated new statements will be returned from the matchers
	 * @param s: The statement
	 * @return
	 */
	public List<Statement> generateFromStatement(Statement s) {
		List<Statement> stmtList = new ArrayList<Statement>();
		for (Matcher m : this.matchers) {
			if (m.isGenPointForTheMatcher(s)) {
				List<Statement> tStmts = m.generatedNewStatements();
				for (Statement i : tStmts) {
					stmtList.add(i);
				}
				m.genPointUsed();
			}
		}
		return stmtList;
	}

	/**
	 *  When a statement matched to a minus pattern of a matcher, return which matcher contains the statement
	 * @param stmt the matched statement
	 * @return its corresponding matcher
	 */
	public Matcher getMatchedMatcher(VariableDeclarationStatement stmt) {
		for (Matcher m : this.matchers) {
			if (m.matchedToMinusStmtPattern(stmt)) {
				return m;
			}
		}
		return null;
	}
	
	/**
	 * Return the image type (which is collected from the declarations of the matchers)
	 * @param typeName
	 * @return
	 */
	public Type mappedType(String typeName) {
		switch(typeName) {
			case "void": return AST.newAST(AST.JLS8).newPrimitiveType(PrimitiveType.VOID);
			case "int": return AST.newAST(AST.JLS8).newPrimitiveType(PrimitiveType.INT);
			case "char": return AST.newAST(AST.JLS8).newPrimitiveType(PrimitiveType.CHAR);
			case "long": return AST.newAST(AST.JLS8).newPrimitiveType(PrimitiveType.LONG); 
			case "boolean": return AST.newAST(AST.JLS8).newPrimitiveType(PrimitiveType.BOOLEAN);
			case "double": return AST.newAST(AST.JLS8).newPrimitiveType(PrimitiveType.DOUBLE);
			case "float": return AST.newAST(AST.JLS8).newPrimitiveType(PrimitiveType.FLOAT);
			case "short": return AST.newAST(AST.JLS8).newPrimitiveType(PrimitiveType.SHORT);
			case "byte": return AST.newAST(AST.JLS8).newPrimitiveType(PrimitiveType.BYTE);
		}
		
		for (Matcher m : this.matchers) {
			if (m.getMappedType(typeName) != null) {
				return m.getMappedType(typeName);
			}
		}

		AST tAST = AST.newAST(AST.JLS8);
		if (typeName.contains("<") && typeName.contains(">"))
			return tAST.newSimpleType(tAST.newName(typeName.substring(0, typeName.indexOf("<"))));
		else if (typeName.contains("[") && typeName.contains("]")) {
			
//			System.out.println("MatcherSet.java @202 arrayType : "+typeName);
			return tAST.newArrayType((Type) ASTNode.copySubtree(tAST, mappedType(typeName.substring(0, typeName.indexOf("[")))));
//			return tAST.newArrayType(tAST.newSimpleType(tAST.newName(typeName.substring(0, typeName.indexOf("[")))));
		} else {
			// To work more
			return tAST.newSimpleType(tAST.newName(typeName));
		}
	}
	
	/**
	 * Check the block level information of all matchers with the root node.
	 * @param bstn
	 */
	public void matcherBlockLevelCheck(BlockSTreeNode bstn) {
		boolean result = true;
		for (Matcher m : this.matchers) {
			result = result && m.blockTreeLevelCheck(bstn);
		}
		if (!result) {
			ErrorManager.error("MatcherSet@178", "MatcherSet check failed.");
		}
	}

	public void collectStatementsToBeShifted(BlockSTreeNode blockTree) {
		for (Matcher m : this.matchers) {
			m.collectStatementsToBeShifted();
		}
	}

	/**
	 * Given a block, return the statements to be added to the given block. This is actually a call back function.
	 * @param blk 
	 */
	public void getStatementsToBeAddedToTheBlock(BlockSTreeNode blk) {
		for (Matcher m : this.matchers) {
			if (m.getHighLevelBlock() == null)
				return;
			if(m.getHighLevelBlock().getId().equals(blk.getId())) {
				for (Statement s : m.getFirstHalfStatementsToBeShifted()) {
					blk.addToStmtListBeginning(s);
				}
				for (Statement s : m.getSecondHalfStatementsToBeShifted()) {
					blk.addToStmtListEnd(s);
				}
			}
		}
	}

	public void collectStatementsToBeDeletedInBlock(
			BlockSTreeNode blk) {
		for (Matcher m : this.matchers) {
			m.collectStatementsToBeDeletedInBlock(blk);
		}
	}

	/**
	 * Add analyzers to the mathers
	 * @param analyzer
	 */
	public void addAnalyzer(Analyzer analyzer) {
		for (Matcher m : this.matchers)		
			m.addAnalyzer(analyzer);
	}
}
