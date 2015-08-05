package patl4j.patl.ast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

import patl4j.matcher.Matcher;
import patl4j.util.ErrorManager;
import patl4j.util.Pair;
import patl4j.util.TypeHandler;
import patl4j.util.VariableContext;

public class AssignStmtPattern implements StatementPattern {
	
	MetaVariable variable;
	RHSPattern expression;
	
	public AssignStmtPattern(MetaVariable variable, RHSPattern expression) {
		this.variable = variable;
		this.expression = expression;
	}
	
	@Override
	public String toString() {
		return variable.getName() + "=" + expression.toString() + ";";
	}

	@Override
	public Pair<List<Pair<String, Name>>, Boolean> tryMatch(Statement s,
			Map<String, String> var2type,
			VariableContext context) {
		
		List<Pair<String, Name>> matchedVarList = new ArrayList<Pair<String,Name>>();
		Boolean matchedSuccessful = false;
		
		// An assignment statement pattern should match both 
		//				assignment statements 
		//  as well as  variabledelcaration statements
		
		if (s instanceof ExpressionStatement) {
			Expression exp = ((ExpressionStatement) s).getExpression();
			if (exp instanceof Assignment) {
				Assignment assignment = (Assignment) exp;
				
				// Note that the left hand side expression of an assignment expression is always a *name*
				Name lhsExp = null;;
				try {
					lhsExp = (Name) assignment.getLeftHandSide();
				} catch(Exception e) {
					ErrorManager.error("AssignStmtPattrn@58", "The left hand side pattern is not a name, instead: " + assignment);
					return new Pair<List<Pair<String, Name>>, Boolean>(matchedVarList, matchedSuccessful);
				}
				
				// Debugging types
				TypeHandler.printTypeMatchInfo(lhsExp, var2type.get(this.variable), "AssignStmtPattern@57");
				// Check the type between the lhs metavariable and the name
				
				//TypeHandler.typeMatchCheck(lhsExp, var2type.get(this.variable))
				if (context.variableMatchCheck(lhsExp, this.variable)) {
					matchedVarList.add(new Pair<String, Name>(this.variable.getName(), lhsExp));
				} else {
					// Type of the lhs expression does not match
					matchedSuccessful = false;
				}
				
				// Try to match the expression pattern
				Expression rhsExp = assignment.getRightHandSide(); 
				Pair<List<Pair<String, Name>>, Boolean> expMatch = this.expression.tryMatch(rhsExp, var2type, context);
				
				if (expMatch.getSecond()) {
					for (Pair<String, Name> p : expMatch.getFirst()) {
						matchedVarList.add(p);
					}
					matchedSuccessful = true;
				}
			}
		} else if (s instanceof VariableDeclarationStatement) {
			
			VariableDeclarationStatement vds = (VariableDeclarationStatement) s;
		
			if (vds.fragments().size() != 1) {
				// If the error occurs, go and fix the bug in 
				ErrorManager.error("AssignStmtPattern@lien81","The size of variable declaration fragments is not 1");
			}
			
			VariableDeclarationFragment vdf = (VariableDeclarationFragment) vds.fragments().get(0);
			
			// Print debugging info for the lhs expression and its corresponding type pattern name
			TypeHandler.printTypeMatchInfo(vdf.getName(), var2type.get(this.variable), "AssignStmtPattern@line83");
			
			// Type check on the lhs variable
			//if (TypeHandler.typeMatchCheck(vdf.getName(), var2type.get(this.variable)))
			if (context.variableMatchCheck(vdf.getName(), this.variable))
				matchedVarList.add(new Pair<String, Name>(this.variable.getName(), vdf.getName()));
			
			Pair<List<Pair<String, Name>>, Boolean> expMatch = this.expression.tryMatch(vdf.getInitializer(), var2type, context);
			
			if (expMatch.getSecond()) {
				for (Pair<String, Name> p : expMatch.getFirst()) {
					matchedVarList.add(p);
				}
				matchedSuccessful = true;
			} 

		}
		
		return new Pair<List<Pair<String, Name>>, Boolean>(matchedVarList, matchedSuccessful);
	}
	
	public String getVariable() {
		return this.variable.getName();
	}

	@Override
	public Statement toJavaStatement(Matcher m) {
		ErrorManager.error("AssignStmtPattern@118", "The method should only be call on Full-series classes, this one is only for src pattern.");
		return null;
	}
	
}
