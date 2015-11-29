package panels;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import models.FinInterface;
public class ResultsPanel extends JPanel {
  public ResultsPanel(  FinInterface object){
    super(new BorderLayout());
    java.lang.String genVar668;
    genVar668="WaHS";
    JLabel waHS1;
    waHS1=new JLabel(genVar668);
    java.lang.String genVar669;
    genVar669="WaHS";
    JLabel waHS2;
    waHS2=new JLabel(genVar669);
    java.lang.String genVar670;
    genVar670="WaHS";
    JLabel waHS3;
    waHS3=new JLabel(genVar670);
    java.awt.BorderLayout genVar671;
    genVar671=new BorderLayout();
    JPanel biotNumberPanel;
    biotNumberPanel=new JPanel(genVar671);
    java.lang.String genVar672;
    genVar672="Biot Number";
    JLabel biotNumberLabel;
    biotNumberLabel=new JLabel(genVar672);
    double genVar673;
    genVar673=object.getBiotNumber();
    java.lang.String genVar674;
    genVar674="";
    java.lang.String genVar675;
    genVar675=genVar673 + genVar674;
    JTextField biotNumberText;
    biotNumberText=new JTextField(genVar675);
    int genVar676;
    genVar676=5;
    int genVar677;
    genVar677=5;
    int genVar678;
    genVar678=5;
    int genVar679;
    genVar679=5;
    javax.swing.border.Border genVar680;
    genVar680=BorderFactory.createEmptyBorder(genVar676,genVar677,genVar678,genVar679);
    biotNumberLabel.setBorder(genVar680);
    int genVar681;
    genVar681=10;
    int genVar682;
    genVar682=10;
    int genVar683;
    genVar683=10;
    int genVar684;
    genVar684=10;
    javax.swing.border.Border genVar685;
    genVar685=BorderFactory.createEmptyBorder(genVar681,genVar682,genVar683,genVar684);
    biotNumberPanel.setBorder(genVar685);
    biotNumberPanel.add(biotNumberLabel,BorderLayout.WEST);
    biotNumberPanel.add(biotNumberText,BorderLayout.CENTER);
    panels.ResultsPanel genVar686;
    genVar686=this;
    genVar686.add(biotNumberPanel,BorderLayout.NORTH);
    JPanel finTransferPanel;
    finTransferPanel=new JPanel();
    javax.swing.BoxLayout genVar687;
    genVar687=new BoxLayout(finTransferPanel,BoxLayout.Y_AXIS);
    finTransferPanel.setLayout(genVar687);
    java.lang.String genVar688;
    genVar688="Fin Heat Transfer Rate";
    JLabel finTransferLabel;
    finTransferLabel=new JLabel(genVar688);
    int genVar689;
    genVar689=5;
    int genVar690;
    genVar690=5;
    int genVar691;
    genVar691=5;
    int genVar692;
    genVar692=5;
    javax.swing.border.Border genVar693;
    genVar693=BorderFactory.createEmptyBorder(genVar689,genVar690,genVar691,genVar692);
    finTransferLabel.setBorder(genVar693);
    finTransferPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    java.awt.FlowLayout genVar694;
    genVar694=new FlowLayout();
    JPanel convHeatPanel;
    convHeatPanel=new JPanel(genVar694);
    int genVar695;
    genVar695=10;
    int genVar696;
    genVar696=10;
    int genVar697;
    genVar697=10;
    int genVar698;
    genVar698=10;
    javax.swing.border.Border genVar699;
    genVar699=BorderFactory.createEmptyBorder(genVar695,genVar696,genVar697,genVar698);
    convHeatPanel.setBorder(genVar699);
    java.lang.String genVar700;
    genVar700="Convection Heat Transfer";
    JLabel convHeatLabel;
    convHeatLabel=new JLabel(genVar700);
    double genVar701;
    genVar701=object.getConvHeatT();
    java.lang.String genVar702;
    genVar702="";
    java.lang.String genVar703;
    genVar703=genVar701 + genVar702;
    JTextField convHeatText;
    convHeatText=new JTextField(genVar703);
    int genVar704;
    genVar704=5;
    int genVar705;
    genVar705=5;
    int genVar706;
    genVar706=5;
    int genVar707;
    genVar707=5;
    javax.swing.border.Border genVar708;
    genVar708=BorderFactory.createEmptyBorder(genVar704,genVar705,genVar706,genVar707);
    convHeatLabel.setBorder(genVar708);
    convHeatPanel.add(convHeatLabel);
    convHeatPanel.add(convHeatText);
    convHeatPanel.add(waHS1);
    java.awt.FlowLayout genVar709;
    genVar709=new FlowLayout();
    JPanel adiabaticPanel;
    adiabaticPanel=new JPanel(genVar709);
    int genVar710;
    genVar710=10;
    int genVar711;
    genVar711=10;
    int genVar712;
    genVar712=10;
    int genVar713;
    genVar713=10;
    javax.swing.border.Border genVar714;
    genVar714=BorderFactory.createEmptyBorder(genVar710,genVar711,genVar712,genVar713);
    adiabaticPanel.setBorder(genVar714);
    java.lang.String genVar715;
    genVar715="Adiabatic";
    JLabel adiabaticLabel;
    adiabaticLabel=new JLabel(genVar715);
    double genVar716;
    genVar716=object.getAdiabatic();
    java.lang.String genVar717;
    genVar717="";
    java.lang.String genVar718;
    genVar718=genVar716 + genVar717;
    JTextField adiabaticText;
    adiabaticText=new JTextField(genVar718);
    int genVar719;
    genVar719=5;
    int genVar720;
    genVar720=5;
    int genVar721;
    genVar721=5;
    int genVar722;
    genVar722=5;
    javax.swing.border.Border genVar723;
    genVar723=BorderFactory.createEmptyBorder(genVar719,genVar720,genVar721,genVar722);
    adiabaticLabel.setBorder(genVar723);
    adiabaticPanel.add(adiabaticLabel);
    adiabaticPanel.add(adiabaticText);
    adiabaticPanel.add(waHS2);
    java.awt.FlowLayout genVar724;
    genVar724=new FlowLayout();
    JPanel infinitePanel;
    infinitePanel=new JPanel(genVar724);
    int genVar725;
    genVar725=10;
    int genVar726;
    genVar726=10;
    int genVar727;
    genVar727=10;
    int genVar728;
    genVar728=10;
    javax.swing.border.Border genVar729;
    genVar729=BorderFactory.createEmptyBorder(genVar725,genVar726,genVar727,genVar728);
    infinitePanel.setBorder(genVar729);
    java.lang.String genVar730;
    genVar730="Infinite Length";
    JLabel infiniteLabel;
    infiniteLabel=new JLabel(genVar730);
    double genVar731;
    genVar731=object.getInfiniteLength();
    java.lang.String genVar732;
    genVar732="";
    java.lang.String genVar733;
    genVar733=genVar731 + genVar732;
    JTextField infiniteText;
    infiniteText=new JTextField(genVar733);
    int genVar734;
    genVar734=5;
    int genVar735;
    genVar735=5;
    int genVar736;
    genVar736=5;
    int genVar737;
    genVar737=5;
    javax.swing.border.Border genVar738;
    genVar738=BorderFactory.createEmptyBorder(genVar734,genVar735,genVar736,genVar737);
    infiniteLabel.setBorder(genVar738);
    infinitePanel.add(infiniteLabel);
    infinitePanel.add(infiniteText);
    infinitePanel.add(waHS3);
    finTransferPanel.add(finTransferLabel);
    finTransferPanel.add(convHeatPanel);
    finTransferPanel.add(adiabaticPanel);
    finTransferPanel.add(infinitePanel);
    panels.ResultsPanel genVar739;
    genVar739=this;
    genVar739.add(finTransferPanel,BorderLayout.WEST);
    java.awt.BorderLayout genVar740;
    genVar740=new BorderLayout();
    JPanel finOuterPanel;
    finOuterPanel=new JPanel(genVar740);
    java.awt.BorderLayout genVar741;
    genVar741=new BorderLayout();
    JPanel finEffiPanel;
    finEffiPanel=new JPanel(genVar741);
    java.awt.BorderLayout genVar742;
    genVar742=new BorderLayout();
    JPanel finPerfPanel;
    finPerfPanel=new JPanel(genVar742);
    int genVar743;
    genVar743=10;
    int genVar744;
    genVar744=10;
    int genVar745;
    genVar745=10;
    int genVar746;
    genVar746=10;
    javax.swing.border.Border genVar747;
    genVar747=BorderFactory.createEmptyBorder(genVar743,genVar744,genVar745,genVar746);
    finEffiPanel.setBorder(genVar747);
    java.lang.String genVar748;
    genVar748="Fin Efficency";
    JLabel finEffiLabel;
    finEffiLabel=new JLabel(genVar748);
    int genVar749;
    genVar749=5;
    int genVar750;
    genVar750=5;
    int genVar751;
    genVar751=5;
    int genVar752;
    genVar752=5;
    javax.swing.border.Border genVar753;
    genVar753=BorderFactory.createEmptyBorder(genVar749,genVar750,genVar751,genVar752);
    finEffiLabel.setBorder(genVar753);
    java.awt.BorderLayout genVar754;
    genVar754=new BorderLayout();
    JPanel longFinPanel;
    longFinPanel=new JPanel(genVar754);
    java.awt.BorderLayout genVar755;
    genVar755=new BorderLayout();
    JPanel insulatedFinPanel;
    insulatedFinPanel=new JPanel(genVar755);
    java.lang.String genVar756;
    genVar756="Long Fin";
    JLabel longFinLabel;
    longFinLabel=new JLabel(genVar756);
    int genVar757;
    genVar757=5;
    int genVar758;
    genVar758=5;
    int genVar759;
    genVar759=5;
    int genVar760;
    genVar760=5;
    javax.swing.border.Border genVar761;
    genVar761=BorderFactory.createEmptyBorder(genVar757,genVar758,genVar759,genVar760);
    longFinLabel.setBorder(genVar761);
    double genVar762;
    genVar762=object.getLongFin();
    java.lang.String genVar763;
    genVar763="";
    java.lang.String genVar764;
    genVar764=genVar762 + genVar763;
    JTextField longFinText;
    longFinText=new JTextField(genVar764);
    longFinPanel.add(longFinLabel,BorderLayout.WEST);
    longFinPanel.add(longFinText,BorderLayout.CENTER);
    java.lang.String genVar765;
    genVar765="Insulated Fin";
    JLabel insulatedFinLabel;
    insulatedFinLabel=new JLabel(genVar765);
    int genVar766;
    genVar766=5;
    int genVar767;
    genVar767=5;
    int genVar768;
    genVar768=5;
    int genVar769;
    genVar769=5;
    javax.swing.border.Border genVar770;
    genVar770=BorderFactory.createEmptyBorder(genVar766,genVar767,genVar768,genVar769);
    insulatedFinLabel.setBorder(genVar770);
    double genVar771;
    genVar771=object.getInsulatedFin();
    java.lang.String genVar772;
    genVar772="";
    java.lang.String genVar773;
    genVar773=genVar771 + genVar772;
    JTextField insulatedFinText;
    insulatedFinText=new JTextField(genVar773);
    insulatedFinPanel.add(insulatedFinLabel,BorderLayout.WEST);
    insulatedFinPanel.add(insulatedFinText,BorderLayout.CENTER);
    finEffiPanel.add(finEffiLabel,BorderLayout.NORTH);
    finEffiPanel.add(longFinPanel,BorderLayout.WEST);
    finEffiPanel.add(insulatedFinPanel,BorderLayout.CENTER);
    int genVar774;
    genVar774=10;
    int genVar775;
    genVar775=10;
    int genVar776;
    genVar776=10;
    int genVar777;
    genVar777=10;
    javax.swing.border.Border genVar778;
    genVar778=BorderFactory.createEmptyBorder(genVar774,genVar775,genVar776,genVar777);
    finPerfPanel.setBorder(genVar778);
    java.lang.String genVar779;
    genVar779="Fin Performance";
    JLabel finPerfLabel;
    finPerfLabel=new JLabel(genVar779);
    int genVar780;
    genVar780=5;
    int genVar781;
    genVar781=5;
    int genVar782;
    genVar782=5;
    int genVar783;
    genVar783=5;
    javax.swing.border.Border genVar784;
    genVar784=BorderFactory.createEmptyBorder(genVar780,genVar781,genVar782,genVar783);
    finPerfLabel.setBorder(genVar784);
    java.awt.BorderLayout genVar785;
    genVar785=new BorderLayout();
    JPanel convHeatPerfPanel;
    convHeatPerfPanel=new JPanel(genVar785);
    java.awt.BorderLayout genVar786;
    genVar786=new BorderLayout();
    JPanel adiabaticPerfPanel;
    adiabaticPerfPanel=new JPanel(genVar786);
    java.awt.BorderLayout genVar787;
    genVar787=new BorderLayout();
    JPanel infinitePerfPanel;
    infinitePerfPanel=new JPanel(genVar787);
    java.lang.String genVar788;
    genVar788="Convection Heat Transfer";
    JLabel convHeatPerfLabel;
    convHeatPerfLabel=new JLabel(genVar788);
    int genVar789;
    genVar789=5;
    int genVar790;
    genVar790=5;
    int genVar791;
    genVar791=5;
    int genVar792;
    genVar792=5;
    javax.swing.border.Border genVar793;
    genVar793=BorderFactory.createEmptyBorder(genVar789,genVar790,genVar791,genVar792);
    convHeatPerfLabel.setBorder(genVar793);
    int genVar794;
    genVar794=10;
    JTextField convHeatPerfText;
    convHeatPerfText=new JTextField(genVar794);
    convHeatPerfPanel.add(convHeatPerfLabel,BorderLayout.WEST);
    convHeatPerfPanel.add(convHeatPerfText,BorderLayout.CENTER);
    java.lang.String genVar795;
    genVar795="Adiabatic";
    JLabel adiabaticPerfLabel;
    adiabaticPerfLabel=new JLabel(genVar795);
    int genVar796;
    genVar796=5;
    int genVar797;
    genVar797=5;
    int genVar798;
    genVar798=5;
    int genVar799;
    genVar799=5;
    javax.swing.border.Border genVar800;
    genVar800=BorderFactory.createEmptyBorder(genVar796,genVar797,genVar798,genVar799);
    adiabaticPerfLabel.setBorder(genVar800);
    int genVar801;
    genVar801=10;
    JTextField adiabaticPerfText;
    adiabaticPerfText=new JTextField(genVar801);
    adiabaticPerfPanel.add(adiabaticPerfLabel,BorderLayout.WEST);
    adiabaticPerfPanel.add(adiabaticPerfText,BorderLayout.CENTER);
    java.lang.String genVar802;
    genVar802="Infinite Length";
    JLabel infinitePerfLabel;
    infinitePerfLabel=new JLabel(genVar802);
    int genVar803;
    genVar803=5;
    int genVar804;
    genVar804=5;
    int genVar805;
    genVar805=5;
    int genVar806;
    genVar806=5;
    javax.swing.border.Border genVar807;
    genVar807=BorderFactory.createEmptyBorder(genVar803,genVar804,genVar805,genVar806);
    infinitePerfLabel.setBorder(genVar807);
    int genVar808;
    genVar808=10;
    JTextField infinitePerfText;
    infinitePerfText=new JTextField(genVar808);
    infinitePerfPanel.add(infinitePerfLabel,BorderLayout.WEST);
    infinitePerfPanel.add(infinitePerfText,BorderLayout.CENTER);
    finPerfPanel.add(finPerfLabel,BorderLayout.NORTH);
    finPerfPanel.add(convHeatPerfPanel,BorderLayout.WEST);
    finPerfPanel.add(adiabaticPerfPanel,BorderLayout.CENTER);
    finPerfPanel.add(infinitePerfPanel,BorderLayout.EAST);
    finOuterPanel.add(finEffiPanel,BorderLayout.NORTH);
    finOuterPanel.add(finPerfPanel,BorderLayout.SOUTH);
    panels.ResultsPanel genVar809;
    genVar809=this;
    genVar809.add(finOuterPanel,BorderLayout.SOUTH);
  }
}