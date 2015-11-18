package husacct.graphics.task;

import husacct.ServiceProvider;
import husacct.common.dto.AbstractDTO;
import husacct.common.dto.DependencyDTO;
import husacct.common.dto.ViolationDTO;
import husacct.common.locale.ILocaleService;
import husacct.common.services.IServiceListener;
import husacct.graphics.presentation.Drawing;
import husacct.graphics.presentation.DrawingView;
import husacct.graphics.presentation.GraphicsFrame;
import husacct.graphics.presentation.figures.BaseFigure;
import husacct.graphics.presentation.figures.ClassFigure;
import husacct.graphics.presentation.figures.FigureFactory;
import husacct.graphics.presentation.figures.InterfaceFigure;
import husacct.graphics.presentation.figures.ModuleFigure;
import husacct.graphics.presentation.figures.PackageFigure;
import husacct.graphics.presentation.figures.ParentFigure;
import husacct.graphics.presentation.figures.ProjectFigure;
import husacct.graphics.presentation.figures.RelationFigure;
import husacct.graphics.task.layout.BasicLayoutStrategy;
import husacct.graphics.task.layout.FigureConnectorStrategy;
import husacct.graphics.task.layout.LayeredLayoutStrategy;
import husacct.graphics.task.layout.LayoutStrategy;
import husacct.graphics.task.layout.NoLayoutStrategy;
import husacct.graphics.task.layout.state.DrawingState;
import husacct.graphics.util.DrawingDetail;
import husacct.graphics.util.DrawingLayoutStrategy;
import husacct.graphics.util.FigureMap;
import husacct.graphics.util.threads.DrawingLinesThread;
import husacct.graphics.util.threads.DrawingMultiLevelThread;
import husacct.graphics.util.threads.DrawingSingleLevelThread;
import husacct.graphics.util.threads.ThreadMonitor;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.swing.JInternalFrame;

import org.apache.log4j.Logger;
import org.jhotdraw.draw.Figure;

public abstract class DrawingController extends DrawingSettingsController {
	private static final double					MIN_ZOOMFACTOR	= 0.25;
	private static final double					MAX_ZOOMFACTOR	= 1.75;
	
	protected static final boolean				debugPrint		= true;
	protected DrawingLayoutStrategy				layoutStrategyOption;
	
	private final HashMap<String, DrawingState>	storedStates	= new HashMap<String, DrawingState>();
	
	private Drawing								drawing;
	private DrawingView							drawingView;
	private GraphicsFrame						graphicsFrame;
	
	protected ILocaleService					localeService;
	protected Logger							logger			= Logger.getLogger(DrawingController.class);
	
	private final FigureFactory					figureFactory;
	private final FigureConnectorStrategy		connectionStrategy;
	private LayoutStrategy						layoutStrategy;
	
	protected ThreadMonitor						threadMonitor;
	private final FigureMap						figureMap		= new FigureMap();
	
	public DrawingController() {
		super();
		layoutStrategyOption = DrawingLayoutStrategy.BASIC_LAYOUT;
		
		figureFactory = new FigureFactory();
		connectionStrategy = new FigureConnectorStrategy();
		
		localeService = ServiceProvider.getInstance().getLocaleService();
		localeService.addServiceListener(new IServiceListener() {
			@Override
			public void update() {
				DrawingController.this.refreshFrame();
			}
		});
		
		initializeComponents();
		switchLayoutStrategy();
		loadDefaultSettings();
	}
	
	@Override
	public void changeLayoutStrategy(DrawingLayoutStrategy selectedStrategyEnum) {
		layoutStrategyOption = selectedStrategyEnum;
		switchLayoutStrategy();
		updateLayout();
	}
	
	public void clearDrawing() {
		figureMap.clearAll();
		drawing.clearAll();
		
		drawingView.clearSelection();
		drawingView.invalidate();
	}
	
	public void clearLines() {
		drawing.clearAllLines();
	}
	
	public void drawArchitecture(DrawingDetail detail) {
		drawingView.cannotZoomOut();
	}
	
	@Override
	public void drawingZoomChanged(double zoomFactor) {
		zoomFactor = Math.max(MIN_ZOOMFACTOR, zoomFactor);
		zoomFactor = Math.min(MAX_ZOOMFACTOR, zoomFactor);
		drawingView.setScaleFactor(zoomFactor);
	}
	
	public void drawLinesBasedOnSetting() {
		drawDependenciesAndViolationsForShownModules();
		if (areSmartLinesOn()) drawing.updateLineFigureToContext();
		if (areLinesThick()) drawing.updateLineFigureThicknesses();
	}
	
	protected void drawLinesBasedOnSettingInTask() {
		clearLines();
		setDrawingViewNonVisible();
		runDrawLinesTask();
	}
	
	protected void drawModulesAndLines(AbstractDTO[] modules) {
		runDrawSingleLevelTask(modules);
	}
	
	protected void drawModulesAndLines(HashMap<String, ArrayList<AbstractDTO>> modules) {
		runDrawMultiLevelTask(modules);
	}
	
	public void drawMultiLevel(HashMap<String, ArrayList<AbstractDTO>> modules) {
		graphicsFrame.setUpToDate();
		clearDrawing();
		drawMultiLevelModules(modules);
		updateLayout();
		drawLinesBasedOnSetting();
		graphicsFrame.setCurrentPaths(getCurrentPaths());
		graphicsFrame.updateGUI();
	}
	
	public void drawMultiLevelModules(HashMap<String, ArrayList<AbstractDTO>> modules) {
		graphicsFrame.setUpToDate();
		for (String parentName : modules.keySet()) {
			ParentFigure parentFigure = null;
			if (!parentName.isEmpty()) {
				parentFigure = figureFactory.createParentFigure(parentName);
				drawing.add(parentFigure);
			}
			for (AbstractDTO dto : modules.get(parentName))
				try {
					BaseFigure generatedFigure = figureFactory.createFigure(dto);
					if (parentFigure != null){ 
						parentFigure.add(generatedFigure);
					}
					drawing.add(generatedFigure);
					figureMap.linkModule(generatedFigure, dto);
				} catch (Exception e) {
					logger.error("Could not generate and display figure.", e);
				}
			if (!parentName.isEmpty()) 
				parentFigure.updateLayout();
		}
	}
	
	public void drawSingleLevel(AbstractDTO[] modules) {
		graphicsFrame.setUpToDate();
		drawSingleLevelModules(modules);
		updateLayout();
		
		/*
		 * If we are at root level(projects) if(modules.length != 0 &&
		 * !(modules[0] instanceof ProjectDTO))
		 */
		
		drawLinesBasedOnSetting();
		graphicsFrame.setCurrentPaths(getCurrentPaths());
		graphicsFrame.updateGUI();
	}
	
	public void drawSingleLevelModules(AbstractDTO[] modules) {
		for (AbstractDTO dto : modules)
			try {
				BaseFigure generatedFigure = figureFactory.createFigure(dto);
				drawing.add(generatedFigure);
				figureMap.linkModule(generatedFigure, dto);
			} catch (Exception e) {
				logger.error("Could not generate and display figure.", e);
			}
	}
	
	@Override
	public void exportToImage() {
		drawing.showExportToImagePanel();
	}
	
	@Override
	public void figureDeselected(BaseFigure[] figures) {
		if (drawingView.getSelectionCount() == 0) graphicsFrame.hideProperties();
	}
	
	@Override
	public void figureSelected(BaseFigure[] figures) {
		BaseFigure selectedFigure = figures[0];
		if (figureMap.isViolationLine(selectedFigure)) 
			graphicsFrame.showViolationsProperties(figureMap.getViolationDTOs(selectedFigure));
		else if (figureMap.isDependencyLine(selectedFigure)) 
			graphicsFrame.showDependenciesProperties(figureMap.getDependencyDTOs(selectedFigure));
		else
			graphicsFrame.hideProperties();
	}
	
	public BaseFigure[] getAllFigures() {
		return drawingView.toFigureArray(drawingView.findFigures(drawingView.getBounds()));
	}
	
	public void drawDependenciesAndViolationsForShownModules() {
		BaseFigure[] shownModules = drawing.getShownModules();
		for (BaseFigure figureFrom : shownModules) {
			for (BaseFigure figureTo : shownModules) {
				if (figureFrom != figureTo) {
					DependencyDTO[] dependencies = getDependenciesBetween(figureFrom, figureTo);
					if (areDependenciesShown() && (dependencies.length > 0)) {
						if (areViolationsShown()) {
							ViolationDTO[] violations = getViolationsBetween(figureFrom, figureTo);
							if (violations.length > 0){ 
								figureFrom.addDecorator(figureFactory.createViolationsDecorator());
								drawDependenciesAndViolationsBetween(dependencies, violations, figureFrom, figureTo);
							} else {
								drawDependenciesBetween(dependencies,figureFrom, figureTo);
							}
						} else {
							drawDependenciesBetween(dependencies,figureFrom, figureTo);
						}
					}
				}
			}
		}
	}
	
	public void drawDependenciesBetween(DependencyDTO[] dependencies, BaseFigure figureFrom, BaseFigure figureTo) {
		RelationFigure dependencyFigure = null;
		try {
			dependencyFigure = figureFactory.createRelationFigure(dependencies);
		} catch (Exception e) {
			logger.error("Could not create a dependency figure.", e);
		}
		if (dependencyFigure != null) {
			figureMap.linkDependencies(dependencyFigure, dependencies);
			connectionStrategy.connect(dependencyFigure, figureFrom, figureTo);
			drawing.add(dependencyFigure);
		}
	}
	
	public void drawDependenciesAndViolationsBetween(DependencyDTO[] dependencies, ViolationDTO[] violations, BaseFigure figureFrom, BaseFigure figureTo) {
		RelationFigure violationFigure = null;
		try {
			violationFigure = figureFactory.createRelationFigureWithViolations(dependencies, violations);
		} catch (Exception e) {
			logger.error("Could not create a violation line between figures.", e);
		}
		if (violationFigure != null) {
			figureMap.linkViolations(violationFigure, violations);
			connectionStrategy.connect(violationFigure, figureFrom, figureTo);
			drawing.add(violationFigure);
		}
	}
	
	protected abstract DependencyDTO[] getDependenciesBetween(
			BaseFigure figureFrom, BaseFigure figureTo);
	
	public Drawing getDrawing() {
		return drawing;
	}
	
	public FigureMap getFigureMap() {
		return figureMap;
	}
	
	public JInternalFrame getGUI() {
		return graphicsFrame;
	}
	
	public DrawingLayoutStrategy getLayoutStrategy() {
		return layoutStrategyOption;
	}
	
	public BaseFigure[] getSelectedFigures() {
		return drawingView.toFigureArray(drawingView.getSelectedFigures());
	}
	
	protected abstract ViolationDTO[] getViolationsBetween(BaseFigure figureFrom, BaseFigure figureTo);
	protected boolean hasDependencyBetween(BaseFigure figureFrom, BaseFigure figureTo){
		boolean b = false;
		return b;
	}	
	
	protected boolean hasSavedFigureStates(String paths) {
		return storedStates.containsKey(paths);
	}
	
	@Override
	public void hideDependencies() {
		super.hideDependencies();
		graphicsFrame.turnOffDependencies();
	}
	
	@Override
	public void hideModules() {
		drawingView.hideSelectedFigures();
	}
	
	@Override
	public void hideSmartLines() {
		super.hideSmartLines();
		graphicsFrame.turnOffSmartLines();
	}
	
	@Override
	public void hideViolations() {
		super.hideViolations();
		graphicsFrame.turnOffViolations();
	}
	
	private void initializeComponents() {
		drawing = new Drawing();
		drawing.setFigureMap(figureMap);
		drawingView = new DrawingView(drawing);
		drawingView.addListener(this);
		
		graphicsFrame = new GraphicsFrame(drawingView);
		graphicsFrame.addListener(this);
		graphicsFrame.setSelectedLayout(layoutStrategyOption);
		
		threadMonitor = new ThreadMonitor(this);
	}
	
	public boolean isDrawingVisible() {
		return drawingView.isVisible();
	}
	
	@Override
	public void moduleZoom() {
		Set<Figure> selection = drawingView.getSelectedFigures();
		
		if (selection.size() > 0) {
			ArrayList<BaseFigure> figures = new ArrayList<BaseFigure>();
			java.util.Collections.addAll(figures, selection.toArray(new BaseFigure[selection.size()]));
			
			for (BaseFigure f : figures)
				if(f instanceof ParentFigure)
					figures.remove(f);
				else
					f.setContext(false); // minimizing potential side effects
			
			if(super.isZoomWithContextOn()){
				drawingView.clearSelection();
				drawingView.selectAll();
				List<BaseFigure> allFigures = Arrays.asList(drawingView.getSelectedFigures().toArray(new BaseFigure[0]));
				List<BaseFigure> contextModules = new ArrayList<BaseFigure>();
				for(BaseFigure fig : allFigures){
					if(fig instanceof PackageFigure || fig instanceof ModuleFigure || fig instanceof ClassFigure || fig instanceof InterfaceFigure || fig instanceof ProjectFigure)
						contextModules.add(fig);
				}
				drawingView.clearSelection();
				drawingView.addToSelection(selection);
				
				for(BaseFigure selected : figures){
					for (BaseFigure module : contextModules){
						if (!module.equals(selected))
							if(hasDependencyBetween(selected, module))
								module.setContext(true);
					}
				}
				
				for(BaseFigure contextModule : contextModules){
					if(contextModule.isContext())
						figures.add(contextModule);
				}
			}
			
			BaseFigure[] selectedFigures = figures.toArray(new BaseFigure[figures.size()]);
			this.moduleZoom(selectedFigures);
		}
	}
	
	protected void printFigures(String msg) {
		if (!debugPrint) return;
		
		System.out.println(msg);
		
		for (Figure f : drawing.getChildren()) {
			BaseFigure bf = (BaseFigure) f;
			Rectangle2D.Double bounds = bf.getBounds();
			
			String rect = String.format(Locale.US,
					"[x=%1.2f,y=%1.2f,w=%1.2f,h=%1.2f]", bounds.x, bounds.y,
					bounds.width, bounds.height);
			if (bf.getName().equals("Main")) System.out.println(String.format(
					"%s: %s", bf.getName(), rect));
		}
	}
	
	public void refreshDrawing(){
		drawing.restoreHiddenFigures();
	}
	
	public void refreshFrame() {
		graphicsFrame.refreshFrame();
	}
	
	protected void resetAllFigurePositions() {
		storedStates.clear();
	}
	
	protected void restoreFigurePositions(String paths) {
		if (storedStates.containsKey(paths)) {
			DrawingState state = storedStates.get(paths);
			state.restore(figureMap);
			drawingView.setHasHiddenFigures(state.hasHiddenFigures());
		}
	}
	
	@Override
	public void restoreModules() {
		drawingView.restoreHiddenFigures();
	}
	
	private void runDrawLinesTask() {
		runThread(new DrawingLinesThread(this));
	}
	
	private void runDrawMultiLevelTask(
			HashMap<String, ArrayList<AbstractDTO>> modules) {
		runThread(new DrawingMultiLevelThread(this, modules));
	}
	
	private void runDrawSingleLevelTask(AbstractDTO[] modules) {
		runThread(new DrawingSingleLevelThread(this, modules));
	}
	
	private void runThread(Runnable runnable) {
		if (!threadMonitor.add(runnable)) {
			//logger.warn("A drawing thread is already running. Wait until it has finished before running another.");
			graphicsFrame.setOutOfDate();
		}
	}
	
	protected void saveFigurePositions() {
		String paths = getCurrentPathsToString();
		DrawingState state;
		if (storedStates.containsKey(paths)) state = storedStates.get(paths);
		else
			state = new DrawingState(drawing);
		
		state.save(figureMap);
		storedStates.put(paths, state);
	}
	
	public void saveSingleLevelFigurePositions() {
		if (getCurrentPaths().length < 2) saveFigurePositions();
	}
	
	@Override
	public void setCurrentPaths(String[] paths) {
		super.setCurrentPaths(paths);
		if (!getCurrentPaths()[0].isEmpty()) drawingView.canZoomOut();
		else
			drawingView.cannotZoomOut();
	}
	
	public void setDrawingViewNonVisible() {
		drawingView.setVisible(false);
		graphicsFrame.showLoadingScreen();
	}
	
	public void setDrawingViewVisible() {
		graphicsFrame.hideLoadingScreen();
		drawingView.setVisible(true);
	}
	
	@Override
	public void setZoomSlider(double zoomFactor) {
		graphicsFrame.setZoomSlider(zoomFactor);
	}
	
	@Override
	public void showDependencies() {
		super.showDependencies();
		graphicsFrame.turnOnDependencies();
	}
	
	@Override
	public void showSmartLines() {
		super.showSmartLines();
		graphicsFrame.turnOnSmartLines();
	}
	
	@Override
	public void showViolations() {
		super.showViolations();
		graphicsFrame.turnOnViolations();
	}
	
	private void switchLayoutStrategy() {
		switch (layoutStrategyOption) {
			case BASIC_LAYOUT:
				layoutStrategy = new BasicLayoutStrategy(drawing);
				break;
			case LAYERED_LAYOUT:
				layoutStrategy = new LayeredLayoutStrategy(drawing);
				break;
			case NO_LAYOUT:
				layoutStrategy = new NoLayoutStrategy();
				break;
			default:
				layoutStrategy = new BasicLayoutStrategy(drawing);
				break;
		}
	}
	
	protected void updateLayout() {
		layoutStrategy.doLayout();
		drawingView.setHasHiddenFigures(false);
		drawing.updateLines();
	}
	
	@Override
	public void usePanTool() {
		drawingView.usePanTool();
	}
	
	@Override
	public void useSelectTool() {
		drawingView.useSelectTool();
	}
}
