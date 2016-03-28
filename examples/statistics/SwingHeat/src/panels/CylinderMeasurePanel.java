package panels;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utility.Utilities;

public class CylinderMeasurePanel extends JPanel {

	private JTextField diameterText;
	private JTextField lengthText;
	private JTextField ambientTempText;
	private JTextField baseTempText;

	public CylinderMeasurePanel() {
		super(new BorderLayout());

		JPanel panelLeft = new JPanel(new BorderLayout());//Ymm
		JPanel panelRight = new JPanel(new BorderLayout());//Ymm

		// LEFT PANEL
		panelLeft.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));//Ymm
		JLabel diameterLabel = new JLabel("Diameter");//Ymm
		diameterText = new JTextField(10);//Ymm
		diameterText.setBorder(Utilities.normalBorder);//Ymm
		diameterText.addFocusListener(Utilities.focusListener);//Y

		JLabel lengthLabel = new JLabel("Length");//Ymm
		lengthText = new JTextField(10);//Ymm
		lengthText.setBorder(Utilities.normalBorder);//Ymm
		lengthText.addFocusListener(Utilities.focusListener);//Y

		JPanel leftLabels = new JPanel(new GridLayout(0, 1, 1, 1));//Ymm
		leftLabels.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));//Ymm
		JPanel leftFields = new JPanel(new GridLayout(0, 1, 1, 1));//Ymm

		leftLabels.add(diameterLabel);//Ymm
		leftLabels.add(lengthLabel);//Ymm

		leftFields.add(diameterText);//Ymm
		leftFields.add(lengthText);//Ymm

		panelLeft.add(leftLabels, BorderLayout.WEST);//Ymm
		panelLeft.add(leftFields, BorderLayout.EAST);//Ymm

		// RIGHT PANEL
		panelRight.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));//Ymm
		JPanel rightLabels = new JPanel(new GridLayout(0, 1, 1, 1));//Ymm
		rightLabels.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));//Ymm
		JPanel rightFields = new JPanel(new GridLayout(0, 1, 1, 1));//Ymm
		rightFields.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));//Ymm

		JLabel ambientTempLabel = new JLabel("Ambient Temperature");//Ymm
		ambientTempText = new JTextField(10);//Ymm
		ambientTempText.setBorder(Utilities.normalBorder);//Ymm
		ambientTempText.addFocusListener(Utilities.focusListener);//Y

		JLabel baseTempLabel = new JLabel("Base Temperature");//Ymm
		baseTempText = new JTextField(10);//Ymm
		baseTempText.setBorder(Utilities.normalBorder);//Ymm
		baseTempText.addFocusListener(Utilities.focusListener);//Y

		rightLabels.add(ambientTempLabel);//Ymm
		rightLabels.add(baseTempLabel);//Ymm

		rightFields.add(ambientTempText);//Ymm
		rightFields.add(baseTempText);//Ymm

		panelRight.add(rightLabels, BorderLayout.WEST);//Ymm
		panelRight.add(rightFields, BorderLayout.CENTER);//Ymm

		this.add(panelLeft, BorderLayout.WEST);//Ymm
		this.add(panelRight, BorderLayout.EAST);//Ymm
	}

	public double getDiameter() {
		return Double.parseDouble(diameterText.getText());
	};

	public double getLength() {
		return Double.parseDouble(lengthText.getText());
	};

	public double getAmbientTemp() {
		return Double.parseDouble(ambientTempText.getText());
	}

	public double getBaseTemp() {
		return Double.parseDouble(baseTempText.getText());
	};

	public void setDiameterFocus(FocusListener focusListener) {
		diameterText.addFocusListener(focusListener);//Y
	}

	public void setLengthFocus(FocusListener focusListener) {
		lengthText.addFocusListener(focusListener);//Y
	}
}
