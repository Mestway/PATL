package husacct.graphics.task;

import husacct.ServiceProvider;
import husacct.analyse.IAnalyseService;
import husacct.common.dto.AbstractDTO;
import husacct.common.dto.DependencyDTO;
import husacct.common.dto.ModuleDTO;
import husacct.common.dto.ViolationDTO;
import husacct.common.services.IServiceListener;
import husacct.define.IDefineService;
import husacct.graphics.presentation.figures.BaseFigure;
import husacct.graphics.util.DrawingDetail;
import husacct.validate.IValidateService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class DefinedController extends DrawingController {
	protected IAnalyseService			analyseService;
	protected IDefineService			defineService;
	protected IValidateService			validateService;
	
	private HashMap<String, BaseFigure>	definedFigures;
	
	public DefinedController() {
		super();
		initializeServices();
	}
	
	@Override
	public void drawArchitecture(DrawingDetail detail) {
		super.drawArchitecture(getCurrentDrawingDetail());
		super.notifyServiceListeners();

		ModuleDTO[] modules = defineService.getModule_AllRootModules();
		if (!areExternalLibrariesShown) {
			int nrOfInternalModules = 0;
			for (ModuleDTO module : modules){
				if (!module.type.toLowerCase().equals("externallibrary"))
					nrOfInternalModules++;
			}
			ModuleDTO[] internalModules = new ModuleDTO[nrOfInternalModules];
			int i = 0;
			for (ModuleDTO module : modules){
				if (!module.type.toLowerCase().equals("externallibrary")) {
					internalModules[i] = module;
					i++;	
				}
			}
			modules = internalModules;
		}
		resetCurrentPaths();
		if (DrawingDetail.WITH_VIOLATIONS == detail) 
			showViolations();
		drawModulesAndLines(modules);
	}
	
	private void getAndDrawModulesIn(String parentName) {
		if (parentName.equals("") || parentName.equals("**")) drawArchitecture(getCurrentDrawingDetail());
		else {
			ModuleDTO[] children = defineService.getModule_TheChildrenOfTheModule(parentName);
			if (children.length > 0) {
				setCurrentPaths(new String[] { parentName });
				drawModulesAndLines(children);
			} else
				logger.warn("Tried to draw modules for \"" + parentName + "\", but it has no children.");
		}
	}
	
	private void getAndDrawModulesIn(String[] parentNames) {
		if (parentNames.length == 0) drawArchitecture(getCurrentDrawingDetail());
		else {
			HashMap<String, ArrayList<AbstractDTO>> allChildren = new HashMap<String, ArrayList<AbstractDTO>>();
			for (String parentName : parentNames) {
				ModuleDTO[] children = defineService.getModule_TheChildrenOfTheModule(parentName);
				if (parentName.equals("") || parentName.equals("**")) {
					drawArchitecture(getCurrentDrawingDetail());
					continue;
				} else if (children.length > 0) {
					ArrayList<AbstractDTO> knownChildren = new ArrayList<AbstractDTO>();
					for (AbstractDTO child : children)
						knownChildren.add(child);
					allChildren.put(parentName, knownChildren);
				} else {
					AbstractDTO value = getFigureMap().getModuleDTO(definedFigures.get(parentName));
					ArrayList<AbstractDTO> tmpList = new ArrayList<AbstractDTO>();
					tmpList.add(value);
					allChildren.put("", tmpList);
					logger.warn("Tried to draw modules for \"" + parentName + "\", but it has no children.");
				}
			}
			setCurrentPaths(parentNames);
			
			Set<String> parentNamesKeySet = allChildren.keySet();
			if (parentNamesKeySet.size() == 1) {
				String onlyParentModule = parentNamesKeySet.iterator().next();
				ArrayList<AbstractDTO> onlyParentChildren = allChildren.get(onlyParentModule);
				drawModulesAndLines(onlyParentChildren.toArray(new AbstractDTO[] {}));
			} else
				drawModulesAndLines(allChildren);
		}
	}
	
	@Override
	protected DependencyDTO[] getDependenciesBetween(BaseFigure figureFrom, BaseFigure figureTo) {
		ModuleDTO dtoFrom = (ModuleDTO) getFigureMap().getModuleDTO(figureFrom);
		HashSet<String> physicalClassPathsFrom = defineService.getModule_AllPhysicalClassPathsOfModule(dtoFrom.logicalPath);
		ModuleDTO dtoTo = (ModuleDTO) getFigureMap().getModuleDTO(figureTo);
		HashSet<String> physicalClassPathsTo = defineService.getModule_AllPhysicalClassPathsOfModule(dtoTo.logicalPath);
		ArrayList<DependencyDTO> dependencies = new ArrayList<DependencyDTO>();
		if (!figureFrom.equals(figureTo) && null != dtoFrom && null != dtoTo) 
			for (String physicalClassPathFrom : physicalClassPathsFrom){
				for (String physicalClassPathTo : physicalClassPathsTo) {
					DependencyDTO[] foundDependencies = analyseService.getDependenciesFromTo(physicalClassPathFrom, physicalClassPathTo);
					for (DependencyDTO tempDependency : foundDependencies)
						dependencies.add(tempDependency);
				}
			}
		return dependencies.toArray(new DependencyDTO[] {});
	}
	
	@Override
	protected ViolationDTO[] getViolationsBetween(BaseFigure figureFrom, BaseFigure figureTo) {
		ModuleDTO dtoFrom = (ModuleDTO) getFigureMap().getModuleDTO(figureFrom);
		ModuleDTO dtoTo = (ModuleDTO) getFigureMap().getModuleDTO(figureTo);
		ViolationDTO[] returnValue = validateService.getViolationsByLogicalPath(dtoFrom.logicalPath, dtoTo.logicalPath);
		return returnValue;
	}
	
	private void initializeServices() {
		analyseService = ServiceProvider.getInstance().getAnalyseService();
		
		defineService = ServiceProvider.getInstance().getDefineService();
		defineService.addServiceListener(new IServiceListener() {
			@Override
			public void update() {
				refreshDrawing();
			}
		});
		validateService = ServiceProvider.getInstance().getValidateService();
		validateService.addServiceListener(new IServiceListener() {
			@Override
			public void update() {
				if (areViolationsShown()) refreshDrawing();
			}
		});
	}
	
	@Override
	public void moduleOpen(String[] paths) {
		super.notifyServiceListeners();
		if (paths.length == 0) drawArchitecture(getCurrentDrawingDetail());
		else
			getAndDrawModulesIn(paths);
	}
	
	@Override
	public void moduleZoom(BaseFigure[] figures) {
		super.notifyServiceListeners();
		definedFigures = new HashMap<String, BaseFigure>();
		ArrayList<String> parentNames = new ArrayList<String>();
		for (BaseFigure figure : figures)
			if (figure.isModule()) try {
				ModuleDTO parentDTO = (ModuleDTO) getFigureMap().getModuleDTO(figure);
				parentNames.add(parentDTO.logicalPath);
				definedFigures.put(parentDTO.logicalPath, figure);
			} catch (Exception e) {
				logger.warn("Could not zoom on this object: " + figure.getName() + ". Expected a different DTO type.");
				//e.printStackTrace();
			}
			else
				logger.warn("Could not zoom on this object: " + figure.getName() + ". Not a module to zoom on.");
		
		if (parentNames.size() > 0) {
			saveSingleLevelFigurePositions();
			getAndDrawModulesIn(parentNames.toArray(new String[] {}));
		}
	}
	
	@Override
	public void moduleZoomOut() {
		super.notifyServiceListeners();
		if (getCurrentPaths().length > 0) {
			saveSingleLevelFigurePositions();
			String firstCurrentPaths = getCurrentPaths()[0];
			String parentPath = defineService.getModule_TheParentOfTheModule(firstCurrentPaths);
			if (parentPath != null) getAndDrawModulesIn(parentPath);
			else
				moduleZoomOutFailed();
		} else
			moduleZoomOutFailed();
	}
	
	public void moduleZoomOutFailed() {
		drawArchitecture(getCurrentDrawingDetail());
	}
	
	@Override
	public void refreshDrawing() {
		super.notifyServiceListeners();
		getAndDrawModulesIn(getCurrentPaths());
	}
	
	@Override
	public void hideLibraries() {
		super.hideLibraries();
		refreshDrawing();
	}

	@Override
	public void showLibraries() {
		super.showLibraries();
		refreshDrawing();
	}

	@Override
	public void showViolations() {
		if (validateService.isValidated()) super.showViolations();
	}
	
	@Override
	public void moduleZoom(String zoomType) {
		// Unused, does not need implementation. AnalyseController has implementation
	}
}
