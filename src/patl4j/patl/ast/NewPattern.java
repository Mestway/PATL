package patl4j.patl.ast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SimpleType;

import patl4j.util.ErrorManager;
import patl4j.util.Pair;
import patl4j.util.TypeHandler;

public class NewPattern implements PEPattern{

	String className;
	List<String> argList;
	
	public NewPattern(String className, List<String> argList) {
		this.className = className;
		this.argList = argList;
	}
	
	public String toString() {
		String args = "(";
		boolean flag = true;
		for (String i : argList) {
			if (!flag)
				args += "," + i;
			else {
				args += i;
				flag = false;
			}
		}
		args += ")";
		return "new" + " " + className + args;
	}

	@Override
	public Pair<List<Pair<String, Name>>, Boolean> tryMatch(Expression exp,
			Map<String, String> var2type) {
		List<Pair<String, Name>> matchedVarList = new ArrayList<Pair<String, Name>>();
		Boolean matchedSuccessful = true;	
		
		if (exp instanceof ClassInstanceCreation) {
			ClassInstanceCreation cic = (ClassInstanceCreation) exp;
			
			// Check if it is a simple type
			if (cic.getType().isSimpleType()) {
				SimpleType st = (SimpleType) cic.getType();
				System.out.println("[NewPattern@line56] the name of the type: " + st.getName().getFullyQualifiedName());
				// Check type name equivalence
				if (this.className.equals(st.getName().getFullyQualifiedName())) {
					
					Integer len = cic.arguments().size();
					
					if (len.equals(this.argList.size())) {
						for (Integer i = 0; i < len; i ++) {
							Expression argi = (Expression) cic.arguments().get(i);
							if (argi instanceof SimpleName) {
								
								// Print type debugging info
								TypeHandler.printTypeMatchInfo(argi, var2type.get(this.argList.get(i)), "[NewPattern@line69]");
								
								// Perform Type check on the variables
								if (TypeHandler.typeMatchCheck(argi, var2type.get(this.argList.get(i))))
									matchedVarList.add(new Pair<String, Name>(this.argList.get(i), (SimpleName)argi));
								else {
									// The type of the arguments does not match
									matchedSuccessful = false;
								}
							} else {
								// The argument of the expression is not normalized
								ErrorManager.error("ClassInstanceCreation normalizing problem.");
								matchedSuccessful = false;
							}
						}
					} else {
						// The argument size is not equal
						matchedSuccessful = false;
					}
				} else {
					// The type name does not match
					matchedSuccessful = false;
				}
			} else {
				// The type is not a simple type
				System.out.println("Isn't the type a simpletype? The real type is: " + cic.getType().toString());
				matchedSuccessful = false;
			}
		} else {
			// The node is not a ClassInstanceCreation expression
			matchedSuccessful = false;
		}
		
		return new Pair<List<Pair<String,Name>>, Boolean>(matchedVarList, matchedSuccessful);
	}
}
