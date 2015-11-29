package panels;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.FocusListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import utility.Utilities;
public class RectangleMeasurePanel extends JPanel {
  private JTextField lengthText;
  private JTextField widthText;
  private JTextField thicknessText;
  private JTextField ambientTempText;
  private JTextField baseTempText;
  public RectangleMeasurePanel(){
    super(new BorderLayout());
    java.awt.BorderLayout genVar574;
    genVar574=new BorderLayout();
    JPanel panelLeft;
    panelLeft=new JPanel(genVar574);
    java.awt.BorderLayout genVar575;
    genVar575=new BorderLayout();
    JPanel panelRight;
    panelRight=new JPanel(genVar575);
    int genVar576;
    genVar576=5;
    int genVar577;
    genVar577=5;
    int genVar578;
    genVar578=5;
    int genVar579;
    genVar579=5;
    javax.swing.border.Border genVar580;
    genVar580=BorderFactory.createEmptyBorder(genVar576,genVar577,genVar578,genVar579);
    panelLeft.setBorder(genVar580);
    java.lang.String genVar581;
    genVar581="Length";
    JLabel lengthLabel;
    lengthLabel=new JLabel(genVar581);
    int genVar582;
    genVar582=10;
    lengthText=new JTextField(genVar582);
    lengthText.setBorder(Utilities.normalBorder);
    java.lang.String genVar583;
    genVar583="Width";
    JLabel widthLabel;
    widthLabel=new JLabel(genVar583);
    int genVar584;
    genVar584=10;
    widthText=new JTextField(genVar584);
    widthText.setBorder(Utilities.normalBorder);
    java.lang.String genVar585;
    genVar585="Length";
    JLabel thicknessLabel;
    thicknessLabel=new JLabel(genVar585);
    int genVar586;
    genVar586=10;
    thicknessText=new JTextField(genVar586);
    int genVar587;
    genVar587=0;
    int genVar588;
    genVar588=1;
    int genVar589;
    genVar589=1;
    int genVar590;
    genVar590=1;
    java.awt.GridLayout genVar591;
    genVar591=new GridLayout(genVar587,genVar588,genVar589,genVar590);
    JPanel leftLabels;
    leftLabels=new JPanel(genVar591);
    int genVar592;
    genVar592=3;
    int genVar593;
    genVar593=3;
    int genVar594;
    genVar594=3;
    int genVar595;
    genVar595=3;
    javax.swing.border.Border genVar596;
    genVar596=BorderFactory.createEmptyBorder(genVar592,genVar593,genVar594,genVar595);
    leftLabels.setBorder(genVar596);
    int genVar597;
    genVar597=0;
    int genVar598;
    genVar598=1;
    int genVar599;
    genVar599=1;
    int genVar600;
    genVar600=1;
    java.awt.GridLayout genVar601;
    genVar601=new GridLayout(genVar597,genVar598,genVar599,genVar600);
    JPanel leftFields;
    leftFields=new JPanel(genVar601);
    leftLabels.add(lengthLabel);
    leftLabels.add(widthLabel);
    leftLabels.add(thicknessLabel);
    leftFields.add(lengthText);
    leftFields.add(widthText);
    leftFields.add(thicknessText);
    panelLeft.add(leftLabels,BorderLayout.WEST);
    panelLeft.add(leftFields,BorderLayout.EAST);
    thicknessText.setBorder(Utilities.normalBorder);
    lengthText.addFocusListener(Utilities.focusListener);
    widthText.addFocusListener(Utilities.focusListener);
    thicknessText.addFocusListener(Utilities.focusListener);
    int genVar602;
    genVar602=5;
    int genVar603;
    genVar603=5;
    int genVar604;
    genVar604=5;
    int genVar605;
    genVar605=5;
    javax.swing.border.Border genVar606;
    genVar606=BorderFactory.createEmptyBorder(genVar602,genVar603,genVar604,genVar605);
    panelRight.setBorder(genVar606);
    int genVar607;
    genVar607=0;
    int genVar608;
    genVar608=1;
    int genVar609;
    genVar609=1;
    int genVar610;
    genVar610=1;
    java.awt.GridLayout genVar611;
    genVar611=new GridLayout(genVar607,genVar608,genVar609,genVar610);
    JPanel rightLabels;
    rightLabels=new JPanel(genVar611);
    int genVar612;
    genVar612=5;
    int genVar613;
    genVar613=5;
    int genVar614;
    genVar614=5;
    int genVar615;
    genVar615=5;
    javax.swing.border.Border genVar616;
    genVar616=BorderFactory.createEmptyBorder(genVar612,genVar613,genVar614,genVar615);
    rightLabels.setBorder(genVar616);
    int genVar617;
    genVar617=0;
    int genVar618;
    genVar618=1;
    int genVar619;
    genVar619=1;
    int genVar620;
    genVar620=1;
    java.awt.GridLayout genVar621;
    genVar621=new GridLayout(genVar617,genVar618,genVar619,genVar620);
    JPanel rightFields;
    rightFields=new JPanel(genVar621);
    int genVar622;
    genVar622=5;
    int genVar623;
    genVar623=5;
    int genVar624;
    genVar624=5;
    int genVar625;
    genVar625=5;
    javax.swing.border.Border genVar626;
    genVar626=BorderFactory.createEmptyBorder(genVar622,genVar623,genVar624,genVar625);
    rightFields.setBorder(genVar626);
    java.lang.String genVar627;
    genVar627="Ambient Temperature";
    JLabel ambientTempLabel;
    ambientTempLabel=new JLabel(genVar627);
    int genVar628;
    genVar628=10;
    ambientTempText=new JTextField(genVar628);
    ambientTempText.setBorder(Utilities.normalBorder);
    ambientTempText.addFocusListener(Utilities.focusListener);
    java.lang.String genVar629;
    genVar629="Base Temperature";
    JLabel baseTempLabel;
    baseTempLabel=new JLabel(genVar629);
    int genVar630;
    genVar630=10;
    baseTempText=new JTextField(genVar630);
    baseTempText.setBorder(Utilities.normalBorder);
    baseTempText.addFocusListener(Utilities.focusListener);
    rightLabels.add(ambientTempLabel);
    rightLabels.add(baseTempLabel);
    rightFields.add(ambientTempText);
    rightFields.add(baseTempText);
    panelRight.add(rightLabels,BorderLayout.WEST);
    panelRight.add(rightFields,BorderLayout.CENTER);
    panels.RectangleMeasurePanel genVar631;
    genVar631=this;
    genVar631.add(panelLeft,BorderLayout.WEST);
    panels.RectangleMeasurePanel genVar632;
    genVar632=this;
    genVar632.add(panelRight,BorderLayout.EAST);
  }
  public double getLength(){
    java.lang.String genVar633;
    genVar633=lengthText.getText();
    double genVar634;
    genVar634=Double.parseDouble(genVar633);
    return genVar634;
  }
  public double getWidthF(){
    java.lang.String genVar635;
    genVar635=widthText.getText();
    double genVar636;
    genVar636=Double.parseDouble(genVar635);
    return genVar636;
  }
  public double getThickness(){
    java.lang.String genVar637;
    genVar637=thicknessText.getText();
    double genVar638;
    genVar638=Double.parseDouble(genVar637);
    return genVar638;
  }
  public double getAmbientTemp(){
    java.lang.String genVar639;
    genVar639=ambientTempText.getText();
    double genVar640;
    genVar640=Double.parseDouble(genVar639);
    return genVar640;
  }
  public double getBaseTemp(){
    java.lang.String genVar641;
    genVar641=baseTempText.getText();
    double genVar642;
    genVar642=Double.parseDouble(genVar641);
    return genVar642;
  }
  public void setLengthFocus(  FocusListener focusListener){
    lengthText.addFocusListener(focusListener);
  }
  public void setWidthFocus(  FocusListener focusListener){
    widthText.addFocusListener(focusListener);
  }
  public void setThicknessFocus(  FocusListener focusListener){
    thicknessText.addFocusListener(focusListener);
  }
}
