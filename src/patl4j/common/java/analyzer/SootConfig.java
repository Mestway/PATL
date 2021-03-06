package patl4j.common.java.analyzer;

import java.util.List;

import patl4j.common.config.PatlOption;
import soot.PackManager;
import soot.Scene;
import soot.SootClass;
import soot.options.Options;
import soot.*;

/*
 * This class is used to set global configuration of soot. 
 * It should be created at the very beginning as a singleton.
 */

public class SootConfig {
	
	static void setClassPath(PatlOption option) {
		String seperator = ":";
		if (option.getPlatform().equals("windows")) {
			seperator = ";";
		}
		
		List<String> classPath = option.getClassPath();
		String concatPath = "";
		for (String st: classPath) {
			
			System.out.println(concatPath);
			
			concatPath += seperator + st;
		}
		
        String oldPath = Scene.v().getSootClassPath();
        System.out.println("[SOOT Classpath]: " + oldPath + concatPath);
        Scene.v().setSootClassPath(oldPath + concatPath);

	}

	/*
	 * @param entryClass the main class of the whole project containing the main function
	 * @param option the patl option class
	 */
	public static void configSoot(String entryClass, PatlOption option) {
		setClassPath(option);

		Options.v().set_whole_program(true);
        //Options.v().setPhaseOption("cg.spark","on");
        Options.v().setPhaseOption("jb", "use-original-names:true");
        Options.v().set_keep_line_number(true);
		
        System.out.println("[Main class] " + option.getMainClassName());
        SootClass s = Scene.v().loadClassAndSupport(option.getMainClassName());
        for (String classToLoad : option.getClassToLoad()) {
        	Scene.v().loadClassAndSupport(classToLoad);
        }
        Scene.v().loadNecessaryClasses();
        Options.v().set_main_class(entryClass);
        Scene.v().addBasicClass(entryClass, SootClass.SIGNATURES);
        Scene.v().addBasicClass("husacct.XMLReportWriter",SootClass.SIGNATURES);
        Scene.v().addBasicClass("javax.crypto.IllegalBlockSizeException",SootClass.SIGNATURES);

        PackManager.v().runPacks();
	}
}
