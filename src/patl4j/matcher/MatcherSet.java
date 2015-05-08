package patl4j.matcher;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.Statement;

import patl4j.patl.ast.Rule;

public class MatcherSet {
	
	List<Matcher> matchers = new ArrayList<Matcher>();
	
	public MatcherSet(List<Rule> rules) {
		for (Rule r : rules) {
			matchers.add(new Matcher(r));
		}
	}
	
	// Update the matcher collection set with a Java Statement,
	// which will then shift the responsibility to matchers
	public MatcherSet accept(Statement s) {
		List<Matcher> collection = new ArrayList<Matcher>();
		for (Matcher i : matchers) {
			// After update, both the updated and the un-updated matcher will be genearted
			List<Matcher> updated = i.accept(s);
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
		this.matchers = cleared;
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
	public boolean stmtMatchedToLastStmt(Statement s) {
		for (Matcher m : this.matchers) {
			if (m.matchedToTheLastSrcStmtPattern(s)) {
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
			if (m.matchedToTheLastSrcStmtPattern(s)) {
				List<Statement> tStmts = m.generatedNewStatements();
				for (Statement i : tStmts) {
					stmtList.add(i);
				}
			}
		}
		return stmtList;
	}
	
}
