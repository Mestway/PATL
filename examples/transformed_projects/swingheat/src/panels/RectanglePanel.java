package panels;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

import borderlayout.BorderData;
import borderlayout.BorderLayout;

//import java.awt.BorderLayout;
//import java.awt.Dimension;
//import java.awt.event.ActionListener;
//import java.awt.event.FocusListener;
//
//import javax.swing.BorderFactory;
//import javax.swing.ImageIcon;
//import javax.swing.JLabel;
//import javax.swing.JPanel;

import models.RectangularFin;

public class RectanglePanel extends Composite {
	private RectangleMeasurePanel measurePanel;
	private ValuesPanel valuesPanel;
	
	protected void checkSubclass(){
		
	}
	
	public RectanglePanel(Composite parent, int style){
		super(parent, style | SWT.BORDER);
		this.setLayout(new BorderLayout());
		
		Image image = new Image(Display.getCurrent(), "resources/rect.jpg");
		
		Label imageL = new Label(this, SWT.NULL);
		imageL.setImage(image);
		imageL.setLayoutData(BorderData.CENTER);
		 measurePanel=new RectangleMeasurePanel(this, SWT.NULL);
		 measurePanel.setLayoutData(BorderData.NORTH);
		 
		 valuesPanel=new ValuesPanel(this, SWT.NULL);
		 valuesPanel.setLayoutData(BorderData.SOUTH);
	}
	
	public void addActionListenerButton(SelectionListener actionListener){
		valuesPanel.getAnalyzeButton().addSelectionListener(actionListener);
	}

	public RectangularFin getRectangleFin(){
		return new RectangularFin(measurePanel.getLength(),measurePanel.getWidthF(),measurePanel.getThickness(),measurePanel.getAmbientTemp(),measurePanel.getBaseTemp(),valuesPanel.getNumberFins(),valuesPanel.getCondHeat(),valuesPanel.getThermalCond());
	}

	public void setImage(Image image){

		BorderLayout layout = (BorderLayout) this.getLayout();
		layout.getControl(BorderData.CENTER).dispose();;
		
		Label imageL = new Label(this, SWT.NULL);
		imageL.setImage(image);
		imageL.setLayoutData(BorderData.CENTER);
		imageL.setSize(new Point(200, 300));
		
		this.pack();
	}
	
	public void setLengthFocus(FocusListener focusListener){
		measurePanel.setLengthFocus(focusListener);
	}
	
	public void setWidthFocus(FocusListener focusListener){
		measurePanel.setWidthFocus(focusListener);
	}
	
	public void setThicknessFocus(FocusListener focusListener){
		measurePanel.setThicknessFocus	(focusListener);
	}
}
