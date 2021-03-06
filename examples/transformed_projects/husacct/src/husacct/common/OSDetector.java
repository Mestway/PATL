package husacct.common;

import java.io.File;

public class OSDetector {
	public enum OS {
		WINDOWS,
		LINUX,
		MAC
	};
	
	private static OS operatingSystem;
	private static String appPath;

    static
    {
        String os = System.getProperty("os.name").toLowerCase();
        if(os.contains("win")) {
        	operatingSystem = OS.WINDOWS;
        	appPath = System.getProperty("user.home") + File.separator + "HUSACCT" + File.separator;
        } else if(os.contains("nux") || os.contains("nix")) {
        	operatingSystem = OS.LINUX;
        	appPath = System.getProperty("user.home") + File.separator + ".husacct" + File.separator;
        } else if(os.contains("mac")) {
        	operatingSystem = OS.MAC;
        	appPath = System.getProperty("user.home") + File.separator + ".husacct" + File.separator;
        }
    }

    public static OS getOS() {
    	return operatingSystem;
    }
    
    public static  String getAppFolder() {
    	return appPath;
    }
    
    
}
