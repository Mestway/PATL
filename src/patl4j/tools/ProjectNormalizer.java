package patl4j.tools;

import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaModelException;

import patl4j.handlers.PatlOption;
import patl4j.java.JavaPackage;
import patl4j.java.JavaProject;
import patl4j.util.ErrorManager;

public class ProjectNormalizer {
	
	// normalize a project
	public void normalize(JavaProject project, PatlOption option) {
		System.out.println("Working in project " + project.getIJavaProject().getElementName());
		try {
			normalizePackage(project, option);
		} catch (JavaModelException e) {
			ErrorManager.error("Failed to print java package info");
			e.printStackTrace();
		}
	}

	private void normalizePackage(JavaProject javaProject, PatlOption option)
			throws JavaModelException {
		for (JavaPackage mypackage : javaProject.getPackages()) {
			// Only deal with the programs in the source library
			// Other packages are in the build path
			if (option.packageIgnored(mypackage.getIPackageFrag().getElementName()))
				continue;
			if (mypackage.getIPackageFrag().getKind() == IPackageFragmentRoot.K_SOURCE) {
				mypackage.createNormalizedFiles(option);
			}
		}
	}
}
