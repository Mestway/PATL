package husacct.analyse.domain.famix;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.naming.directory.InvalidAttributesException;
import org.apache.log4j.Logger;
import husacct.analyse.domain.IModelCreationService;
import husacct.common.dto.AnalysedModuleDTO;

public class FamixCreationServiceImpl implements IModelCreationService {

    private FamixModel model;
    private FamixCreationPostProcessor creationPostProcessor;
    private final Logger logger = Logger.getLogger(FamixCreationServiceImpl.class);
    

    public FamixCreationServiceImpl() {
        model = FamixModel.getInstance();
        creationPostProcessor = new FamixCreationPostProcessor();
    }

    @Override
    public void clearModel() {
        model.clear();
    }

    public void createPackage(String uniqueName, String belongsToPackage, String name) {
        if (!model.packages.containsKey(uniqueName)){
	        FamixPackage fPackage = new FamixPackage();
	        fPackage.uniqueName = uniqueName;
	        fPackage.belongsToPackage = belongsToPackage;
	        fPackage.name = name;
	        if ((!belongsToPackage.equals("") && (!model.packages.containsKey(belongsToPackage)))){
	        	createPackageParent(belongsToPackage);
	        }
        	addToModel(fPackage);
        }
    }

    private void createPackageParent(String uniquePackageName){
    	String belongsToPackage = "";
        String name = "";
        if (uniquePackageName.contains(".")) {
        	// Determine parentPackageName
            String[] allPackages = uniquePackageName.split("\\.");
            for (int i = 0; i < allPackages.length - 1; i++) {
                if (belongsToPackage == "") {
                    belongsToPackage += allPackages[i];
                } else {
                    belongsToPackage += "." + allPackages[i];
                }
            }
            name = allPackages[allPackages.length - 1];
        } else {
            name = uniquePackageName;
        }
        createPackage(uniquePackageName, belongsToPackage, name);
    }
    
    @Override
    public void createClass(String sourceFilePath, int linesOfCode, String uniqueName, String name, String belongsToPackage,
            boolean isAbstract, boolean isInnerClass, String belongsToClass, String visibility, boolean isInterface) {
    	if ((uniqueName != null) && !uniqueName.equals("") && (name != null) && !name.equals("") && (belongsToPackage != null) && !belongsToPackage.equals("") && (belongsToClass != null)) { 
	    	FamixClass fClass = new FamixClass();
	    	fClass.sourceFilePath = sourceFilePath;
	    	fClass.linesOfCode = linesOfCode;
	        fClass.uniqueName = uniqueName.trim();
	        fClass.isAbstract = isAbstract;
	        fClass.belongsToPackage = belongsToPackage.trim();
	        fClass.isInnerClass = isInnerClass;
	     	fClass.name = name.trim();
	        fClass.belongsToClass = belongsToClass;
	        if (visibility.equals("")) {
	            fClass.visibility = "default";
	        } else {
	            fClass.visibility = visibility;
	        }
	        fClass.isInterface = isInterface;
	        addToModel(fClass);
    	} else {
    		// String breakpoint = "true"; // Test helper
    	}
    	
    }

    @Override
    public void createImport(String importingClass, String importedModule, int lineNumber,
            String completeImportString, boolean importsCompletePackage) {

        FamixImport fImport = new FamixImport();
        fImport.from = importingClass;
        fImport.to = importedModule;
        fImport.lineNumber = lineNumber;
        fImport.importingClass = importingClass;
        fImport.completeImportString = completeImportString;
        fImport.importedModule = completeImportString;
        fImport.importsCompletePackage = importsCompletePackage;
        addToModel(fImport);
    }

    public void createMethodOnly(String name, String uniqueName, String accessControlQualifier,
            String signature, boolean isPureAccessor, String declaredReturnType,
            String belongsToClass, boolean isConstructor, boolean isAbstract, boolean hasClassScope, int lineNumber) {

    	FamixMethod famixMethod = new FamixMethod();
        famixMethod.name = name;
        famixMethod.uniqueName = uniqueName;
        famixMethod.accessControlQualifier = accessControlQualifier;
        if (signature.equals("")) {
            signature = "()";
        }
        famixMethod.signature = signature;
        famixMethod.isPureAccessor = isPureAccessor;
        famixMethod.declaredReturnType = declaredReturnType;
        famixMethod.belongsToClass = belongsToClass;
        famixMethod.isConstructor = isConstructor;
        famixMethod.isAbstract = isAbstract;
        famixMethod.hasClassScope = hasClassScope;
        addToModel(famixMethod);
    }

    public void createMethod(String name, String uniqueName, String accessControlQualifier,
            String signature, boolean isPureAccessor, String declaredReturnType,
            String belongsToClass, boolean isConstructor, boolean isAbstract, boolean hasClassScope, int lineNumber) {

    	createMethodOnly(name, uniqueName, accessControlQualifier, signature, isPureAccessor, declaredReturnType, belongsToClass, isConstructor, isAbstract, hasClassScope, lineNumber);
    	
        if ((declaredReturnType != null) && (!declaredReturnType.equals(""))) {
            FamixAssociation fAssocation = new FamixAssociation();
            fAssocation.from = belongsToClass;
            fAssocation.to = declaredReturnType;
            fAssocation.type = "DeclarationReturnType";
            fAssocation.lineNumber = lineNumber;
            model.waitingAssociations.add(fAssocation);
        }
    }

    @Override
    public void createAttributeOnly(Boolean classScope, String accesControlQualifier,
            String belongsToClass, String declareType, String name, String uniqueName, int line) {
        FamixAttribute famixAttribute = new FamixAttribute();
        famixAttribute.hasClassScope = classScope;
        famixAttribute.accessControlQualifier = accesControlQualifier;
        famixAttribute.belongsToClass = belongsToClass;
        famixAttribute.declareType = declareType;
        famixAttribute.name = name;
        famixAttribute.uniqueName = uniqueName;
        famixAttribute.lineNumber = line;
        model.waitingStructuralEntities.add(famixAttribute);
    }

    @Override
    public void createAttribute(Boolean classScope, String accesControlQualifier, String belongsToClass,
            String declareType, String name, String uniqueName, int line) {
    	
    	createAttributeOnly(classScope, accesControlQualifier, belongsToClass, declareType, name, uniqueName, line);
        
        FamixAssociation fAssocation = new FamixAssociation();
        fAssocation.from = belongsToClass;
        fAssocation.to = declareType;
        fAssocation.type = "DeclarationInstanceVariable";
        fAssocation.lineNumber = line;
        model.waitingAssociations.add(fAssocation);
    }

    @Override
    public void createLocalVariable(String belongsToClass, String declareType, String name,
            String uniqueName, int lineNumber, String belongsToMethodString) {

    	createLocalVariableOnly(belongsToClass, declareType, name, uniqueName, lineNumber, belongsToMethodString);

        FamixAssociation fAssocation = new FamixAssociation();
        fAssocation.from = belongsToClass;
        fAssocation.to = declareType;
        fAssocation.type = "DeclarationVariableWithinMethod";
        fAssocation.lineNumber = lineNumber;
        model.waitingAssociations.add(fAssocation);
    }

    @Override
    public void createLocalVariableOnly(String belongsToClass, String declareType, String name,
            String uniqueName, int lineNumber, String belongsToMethodString) {
        FamixLocalVariable famixLocalVariable = new FamixLocalVariable();
        famixLocalVariable.belongsToMethod = belongsToMethodString;
        famixLocalVariable.belongsToClass = belongsToClass;
        famixLocalVariable.declareType = declareType;
        famixLocalVariable.name = name;
        famixLocalVariable.uniqueName = uniqueName;
        famixLocalVariable.lineNumber = lineNumber;
        model.waitingStructuralEntities.add(famixLocalVariable);
    }

    @Override
    public void createParameterOnly(String name, String uniqueName, String declareType, String belongsToClass,
            int lineNumber, String belongsToMethod, List<String> declareTypes) {
        FamixFormalParameter famixParameter = new FamixFormalParameter();
        famixParameter.belongsToClass = belongsToClass;
        famixParameter.belongsToMethod = belongsToMethod;
        famixParameter.declareType = declareType;
        famixParameter.lineNumber = lineNumber;
        famixParameter.name = name;
        famixParameter.uniqueName = uniqueName;
        famixParameter.declaredTypes = declareTypes;
        model.waitingStructuralEntities.add(famixParameter);
    }

    @Override
    public void createParameter(String name, String uniqueName, String declareType, String belongsToClass, 
    		int lineNumber, String belongsToMethod, List<String> declareTypes) {

    	this.createParameterOnly(name, uniqueName, declareType, belongsToClass, lineNumber, belongsToMethod, declareTypes);

        FamixAssociation fAssocation = new FamixAssociation();
        fAssocation.from = belongsToClass;
        fAssocation.to = declareType;
        fAssocation.type = "DeclarationParameter";
        fAssocation.lineNumber = lineNumber;
        model.waitingAssociations.add(fAssocation);

        for (String type : declareTypes) {
            FamixAssociation fParamAssocation = new FamixAssociation();
            fParamAssocation.from = belongsToClass;
            fParamAssocation.to = type;
            fParamAssocation.type = "Declaration6";
            fParamAssocation.lineNumber = lineNumber;
            model.waitingAssociations.add(fParamAssocation);
        }
    }

    @Override
    public void createAnnotation(String belongsToClass, String declareType, String name,
            String uniqueName, int linenumber) {

        FamixAttribute famixAttribute = new FamixAttribute();
        famixAttribute.hasClassScope = false;
        famixAttribute.accessControlQualifier = "public";
        famixAttribute.belongsToClass = belongsToClass;
        famixAttribute.declareType = declareType;
        famixAttribute.name = name;
        famixAttribute.uniqueName = uniqueName;
        //addToModel(famixAttribute);
        model.waitingStructuralEntities.add(famixAttribute);
        FamixAssociation fAssocation = new FamixAssociation();
        fAssocation.from = belongsToClass;
        fAssocation.to = declareType;
        fAssocation.type = "Annotation";
        fAssocation.lineNumber = linenumber;
        model.waitingAssociations.add(fAssocation);
    }

    @Override
    public void createException(String fromClass, String ExceptionClass, int lineNumber, String declarationType) {
        FamixException exception = new FamixException();
        exception.from = fromClass;
        exception.to = ExceptionClass;
        exception.lineNumber = lineNumber;
        exception.exceptionType = declarationType;
        model.waitingAssociations.add(exception);
    }

    @Override
    public void createInheritanceDefinition(String from, String to, int lineNumber) {
        FamixInheritanceDefinition famixInheritanceDefinition = new FamixInheritanceDefinition();
        famixInheritanceDefinition.from = from;
        famixInheritanceDefinition.to = to;
        famixInheritanceDefinition.lineNumber = lineNumber;
        model.waitingAssociations.add(famixInheritanceDefinition);
    }

    @Override
    public void createImplementsDefinition(String from, String to, int lineNumber) {
    	FamixInheritanceDefinition fImplements = new FamixInheritanceDefinition();
        fImplements.from = from;
        fImplements.to = to;
        fImplements.lineNumber = lineNumber;
        model.waitingAssociations.add(fImplements);
    }

    @Override
    public void createDeclarationTypeCast(String from, String to, int lineNumber) {

        FamixAssociation fAssocation = new FamixAssociation();
        fAssocation.from = from;
        fAssocation.to = to;
        fAssocation.type = "DeclarationTypeCast";
        fAssocation.lineNumber = lineNumber;
        model.waitingAssociations.add(fAssocation);
    }

    @Override
    public void createMethodInvocation(String from, String to, int lineNumber,
            String invocationName, String belongsToMethod, String nameOfInstance, String type) {

        FamixInvocation famixInvocation = new FamixInvocation();
        famixInvocation.type = "InvocMethod";
        famixInvocation.from = from;
        famixInvocation.lineNumber = lineNumber;
        famixInvocation.to = to;
        famixInvocation.invocationName = invocationName;
        famixInvocation.belongsToMethod = belongsToMethod;
        famixInvocation.nameOfInstance = nameOfInstance;
        model.waitingAssociations.add(famixInvocation);
    }

    @Override
    public void createPropertyOrFieldInvocation(String from, String to, int lineNumber,
            String invocationName, String belongsToMethod, String nameOfInstance) {

        FamixInvocation famixInvocation = new FamixInvocation();
        famixInvocation.type = "AccessPropertyOrField";
        famixInvocation.from = from;
        famixInvocation.lineNumber = lineNumber;
        famixInvocation.to = to;
        famixInvocation.invocationName = invocationName;
        famixInvocation.belongsToMethod = belongsToMethod;
        famixInvocation.nameOfInstance = nameOfInstance;
        model.waitingAssociations.add(famixInvocation);
    }

    @Override
    public void executePostProcesses() {
    	creationPostProcessor.processImports();
        createClassesAndLibrariesBasedOnImports();
        this.logger.info(new Date().toString() + " Finished: distinguisAndCreateLibraries(), Nr of Libraries = " + model.libraries.size());
        int associationsNumber = model.associations.size();
        this.logger.info(new Date().toString() + " Starting: processWaitingStructuralEntities(), Model.entities = " + model.structuralEntities.size() + ", WaitingStructuralEntities = " + model.waitingStructuralEntities.size());
        creationPostProcessor.processWaitingStructuralEntities();
        this.logger.info(new Date().toString() + " Finished: processWaitingStructuralEntities(), Model.entities = " + model.structuralEntities.size() + ", Model.associations = " + model.associations.size() + ", WaitingAssociations = " + model.waitingAssociations.size());
        creationPostProcessor.processBehaviouralEntities();
        creationPostProcessor.processInheritanceAssociations();
        creationPostProcessor.processWaitingAssociations();
        creationPostProcessor.processWaitingDerivedAssociations();
        associationsNumber = model.associations.size();
        this.logger.info(new Date().toString() + " Finished: processWaitingAssociations(), Model.associations = " + associationsNumber + ", Not connected associations = " + creationPostProcessor.getNumberOfRejectedWaitingAssociations());
    }

    private void createClassesAndLibrariesBasedOnImports() {
		// Create a root package "ExternalLibraries"
		String rootLibraryPackage = "xLibraries";
		createPackage(rootLibraryPackage, "", rootLibraryPackage);
		FamixPackage externalRoot = model.packages.get(rootLibraryPackage);
		externalRoot.external = true;
		// Select all imported types. Note: key of imports is combined from.to.
		HashMap<String, Boolean> completeImportStrings = new HashMap<String, Boolean>();
		for(String importKey : model.imports.keySet()){
			FamixImport foundImport = model.imports.get(importKey);
			completeImportStrings.put(foundImport.completeImportString, foundImport.importsCompletePackage);
		}
		// Get a list of the root units.
		List<AnalysedModuleDTO> rootModules = (new FamixModuleFinder(model)).getRootModules();
		
		// Check for each completeImportString if it is an internal class. If not, create a FamixLibrary or package if it refers to a complete package or namespace.
		for(String completeImportString : completeImportStrings.keySet()){
			boolean importsCompletePackage = completeImportStrings.get(completeImportString);
			if (!completeImportString.contains("*")) { //May be extended, eg: if((!completeImportString.startsWith("java.")) && (!completeImportString.startsWith("javax.")))
				//	Determine if the complete import string starts with a root module and refers to an internal type. 
				boolean isExternal = true;
				String rootModuleUniqueName = "";
				for (AnalysedModuleDTO rootModule : rootModules){
					if (completeImportString.startsWith(rootModule.uniqueName)){
						isExternal =  false;
						rootModuleUniqueName = rootModule.uniqueName;
						break;
					}
				}
				if (isExternal == false) { // completeImportString refers to an internal package of type. If it's not a package, it must be a class (assuming that all packages are created). If the type is not registered yet, create it.
					if (!importsCompletePackage && !model.packages.containsKey(completeImportString)){
						if(!model.classes.containsKey(completeImportString)){
							createClassWithParentsBasedOnImport(completeImportString, rootModuleUniqueName);
						} else {
							// Do nothing: class exists already
						}
					} else {
						// Do nothing: package exists already
					}
				} else { // completeImportString refers to  an external package or type. Create a library with parent Library objects, if not registered already.
					String uniqueExternalName = rootLibraryPackage + "." + completeImportString;
					if(!model.libraries.containsKey(uniqueExternalName)){
						createLibraryPackagewithParents(completeImportString, rootLibraryPackage, importsCompletePackage);
					} else {
						// Do nothing: library exists already
					}
				}
			}
		}
    }
    
    private void createClassWithParentsBasedOnImport(String completeImportString, String rootModuleUniqueName) {
		// 1) Determine parent; 2) if parent is package ...; 3) if parent is class ...; 4) if no parent is found
		// Create package for each substring, except for the last substring 
		String packageName = "";
		String packageUniqueName = "";
		String packageParent = "";
		String className = ""; // short name
		if (completeImportString.contains(".")) {
			if (completeImportString.lastIndexOf('.') != completeImportString.length() - 1) {
				String[] names = completeImportString.split("\\.");
		        className = names[names.length -1];
		        packageParent = names[0];
				for (int i = 1 ; i < (names.length -1); i++){
					packageName = names[i]; 
					packageUniqueName = packageParent + "." + names[i]; 
					// Create a package if it is not registered already
					if (!model.packages.containsKey(packageUniqueName) && !model.classes.containsKey(packageUniqueName)) { // Needed in case the import refers to an inner class
				        createPackage(packageUniqueName, packageParent, packageName);
					}
			        packageParent = packageUniqueName;
				}
			} else {
				// Do nothing: Type name which finishes with a dot 
			}
		} else {
			packageUniqueName = packageParent;
			className = completeImportString;
		}
		//Add  as FamixClass
		FamixClass newClass = new FamixClass();
        newClass.uniqueName = completeImportString;
        // Determine if the new class is an inner class
		if (model.classes.containsKey(packageUniqueName)) { // The class is an inner class
			newClass.belongsToPackage = packageUniqueName.substring(0, packageUniqueName.lastIndexOf("."));
			//String containingClass = packageUniqueName.substring(packageUniqueName.lastIndexOf(".") + 1, packageUniqueName.length());
	        newClass.name = className;
			newClass.isInnerClass = true;
			newClass.belongsToClass = packageUniqueName;
		} else { 
			newClass.belongsToPackage = packageUniqueName;
	        newClass.name = className;
			newClass.isInnerClass = false;
			newClass.belongsToClass = "";
		}
		newClass.isAbstract = false;
		newClass.isInterface = false;
        newClass.visibility = "public";
        addToModel(newClass);
    }
    
    private void createLibraryPackagewithParents(String completeImportString, String rootLibraryPackage, boolean importsCompletePackage) {
		// Create a FamixLibrary for each substring.
		String libraryName = "";
		String libraryUniqueName = ""; // physicalPath prefixed by rootLibraryPackage
		String libraryPhysicalPath = ""; // original path in the source code
		String libraryParentUniqueName = rootLibraryPackage;
		if (completeImportString.lastIndexOf('.') != completeImportString.length() - 1) { // If completeImportString doesn't end with a ".".
			String[] names = completeImportString.split("\\.");
			for (int i = 0 ; i < (names.length); i++){
				libraryName = names[i]; 
				libraryUniqueName = libraryParentUniqueName + "." + libraryName;
				if (i == 0) {
					libraryPhysicalPath = libraryName;
				} else {
					libraryPhysicalPath = libraryPhysicalPath + "." + libraryName;
				}
				// Create a library if it is not registered already
				if (!model.libraries.containsKey(libraryUniqueName)) { 
					//Add completeImportString as FamixLibrary
					FamixLibrary fLibrary = new FamixLibrary();
			        fLibrary.name = libraryName;
			        fLibrary.uniqueName = libraryUniqueName;
					fLibrary.physicalPath = libraryPhysicalPath;
					fLibrary.isPackage = importsCompletePackage;
					fLibrary.belongsToPackage = libraryParentUniqueName;
			        fLibrary.visibility = "public";
			        addToModel(fLibrary);
				}
		        libraryParentUniqueName = libraryUniqueName;
			}
		} else {
			// Do nothing: Type name which finishes with a dot 
		}
    }
    
    private boolean addToModel(FamixObject newObject) {
        try {
        	model.addObject(newObject);
            return true;
        } catch (InvalidAttributesException e) {
        	this.logger.debug(new Date().toString() + e.getMessage());
            return false;
        }
    }

    public String represent() {
        return model.toString();
    }

    @SuppressWarnings("unused")
    @Override
    public void clearMemoryFromObjectsNotUsedAnymore() {
        for (FamixStructuralEntity entity : model.structuralEntities.values()) {
            entity = null;
        }

    }
}
