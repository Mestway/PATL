package husacct.common.dto;

public class DependencyDTO extends AbstractDTO{
	
	//unique name of the from-class
	public String from;
	//class path of the from-class file; in case of inner classes classPathFrom is shorter than from 
	public String fromClassPath;
	//unique name of the to-class
	public String to;
	//class path of the to-class file; in case of inner classes classPathFrom is shorter than from 
	public String toClassPath;
	public String via;
	public String type;
	public int lineNumber;
	public boolean isIndirect;
	
	public DependencyDTO(String from, String fromClassPath, String to, String toClassPath, String type, int lineNumber){
		this.from = from;
		this.fromClassPath = fromClassPath;
		this.to = to;
		this.toClassPath = toClassPath;
		this.type = type;
		this.lineNumber = lineNumber;
		this.isIndirect = false;
	}
	
	public DependencyDTO(String from, String fromClassPath, String to, String toClassPath, String type, int lineNumber, boolean indirect){
		this.from = from;
		this.fromClassPath = fromClassPath;
		this.to = to;
		this.toClassPath = toClassPath;
		this.type = type;
		this.lineNumber = lineNumber;
		this.isIndirect = indirect;
	}
	
	public boolean equals(DependencyDTO other){
		boolean result = true;
		result = result && (this.from == other.from);
		result = result && (this.fromClassPath == other.fromClassPath);
		result = result && (this.to == other.to);
		result = result && (this.toClassPath == other.toClassPath);
		result = result && (this.type == other.type);
		result = result && (this.lineNumber == other.lineNumber);
		result = result && (this.isIndirect == other.isIndirect);
		return result;
	}
	
	public String toString(){
		String result = "";
		result += "\nFrom: " + from + ", ";
		result += "ClassPathfrom: " + fromClassPath;
		result += "\nTo: " + to + ", ";
		result += "ClassPathTo: " + toClassPath;
		result += "\nType: " + type + ", ";
		result += "Line: " + lineNumber + ", ";
		result += "Indirect : " + isIndirect + ".";
		result += "\n";
		return result;
	}
}
