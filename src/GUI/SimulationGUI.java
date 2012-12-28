package GUI;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Automat.FlaschenType;
import Automat.HWSimulation;
import Fassade.Fassade;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.text.DefaultCaret;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JCheckBox;


public class SimulationGUI {

	private JFrame frmSimulationhelper;
	private Fassade DieFassade;
	private JComboBox comboBox;
	private JTextArea txtrMonitoring;
	private JToggleButton tglbtnLeuchte;
	private JToggleButton tglbtnEingangslichtschranke;
	private JToggleButton tglbtnJustierlichtschranke;
	private JToggleButton tglbtnNewToggleButton;
	private JToggleButton tglbtnEingangAuswahlklappe;
	private JToggleButton tglbtnAusgangslichtschranke;
	private JToggleButton tglbtnLichtschrankepet;
	private JToggleButton tglbtnLichtschrankemehrweg;
	private JToggleButton tglbtnTrte;
	private int logLevel;
	private HWSimulation HWaccess;
	private JPanel pVorderesLaufband;
	private JRadioButton rdbtnVorderesStop;
	private JRadioButton rdbtnVorderesVorwaerts;
	private JRadioButton rdbtnVorderesRueckwaerts;
	private JCheckBox chckbxVorderesGesperrt;
	private JLabel lblVordereslaufband;
	private JPanel pDrehLaufband;
	private JLabel lableDreh;
	private JCheckBox chckbxDrehGesperrt;
	private JRadioButton rdbtnDrehStop;
	private JRadioButton rdbtnDrehRechts;
	private JRadioButton rdbtnDrehLinks;
	private JPanel pHinteresLaufband;
	private JLabel lblHintereslaufband;
	private JCheckBox chckbxHinteresGesperrt;
	private JRadioButton rdbtnHinteresStop;
	private JRadioButton rdbtnHinteresVorwaerts;
	private JRadioButton rdbtnHinteresRueckwaerts;
	private JButton btnEingangslichtschrankeAuswahlklappe;
	private JPanel pEndbehaelter;
	private JLabel lblEndbehaelter;
	private JToggleButton tglbtnPetBehlter;
	private JToggleButton tglbtnMehrwegBehlter;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Fassade DieFassade =  new Fassade();
//					SimulationGUI window = new SimulationGUI(DieFassade);
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public SimulationGUI(Fassade fassade) {
		HWaccess = HWSimulation.getInstance();
		DieFassade = fassade;
		logLevel = 0;
		initialize();
		this.frmSimulationhelper.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSimulationhelper = new JFrame();
		frmSimulationhelper.setResizable(false);
		frmSimulationhelper.setTitle("Simulation");
		frmSimulationhelper.setBounds(100, 100, 669, 890);
		frmSimulationhelper.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSimulationhelper.getContentPane().setLayout(new MigLayout("", "[289.00px][144px,grow][193.00px]", "[675.00px,grow]"));
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.LIGHT_GRAY);
		frmSimulationhelper.getContentPane().add(desktopPane, "cell 0 0,grow");
		desktopPane.setLayout(new MigLayout("", "[140px,grow]", "[12px][24px][25px][25px][15px][][][19px][][][][][][][][][][][][][][][][][][][][][][][][][][][][][grow][grow][grow][]"));
		
		JLabel lblFlaschenautomat = new JLabel("Flaschenautomat");
		desktopPane.add(lblFlaschenautomat, "cell 0 0,alignx center,aligny center");
		
		String[] comboStrings = new String[FlaschenType.values().length];
		
		comboStrings[0] = FlaschenType.CodeNichtValide.toString();
		comboStrings[1] = FlaschenType.CodeUnlesbar.toString();
		comboStrings[2] = FlaschenType.Mehrweg.toString();
		comboStrings[3] = FlaschenType.PET.toString();
		
		comboBox =  new JComboBox(comboStrings);
		
		desktopPane.add(comboBox, "cell 0 1,growx,aligny center");
		
		tglbtnEingangslichtschranke = new JToggleButton("Eingangslichtschranke");
		tglbtnEingangslichtschranke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        HWaccess.write(Automat.Adressen.Eingangslichtschranke.ordinal(), abstractButton.getModel().isSelected());
			}
		});
		
		JButton btnFlascheeinlegen = new JButton("FlascheEinlegen");
		btnFlascheeinlegen.setHorizontalAlignment(SwingConstants.LEFT);
		btnFlascheeinlegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnFlascheeinlegen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String Flasche = comboBox.getSelectedItem().toString();
				DieFassade.simFlascheEinlegen(FlaschenType.valueOf(Flasche));
				//Glue.InsertBottle();
			}
		});
		desktopPane.add(btnFlascheeinlegen, "cell 0 2,alignx left");
		tglbtnEingangslichtschranke.setHorizontalAlignment(SwingConstants.LEFT);
		desktopPane.add(tglbtnEingangslichtschranke, "cell 0 4");
		
		tglbtnJustierlichtschranke = new JToggleButton("Justierlichtschranke");
		tglbtnJustierlichtschranke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        HWaccess.write(Automat.Adressen.Justierlichtschranke.ordinal(), abstractButton.getModel().isSelected());
			}
		});
		tglbtnJustierlichtschranke.setHorizontalAlignment(SwingConstants.LEFT);
		desktopPane.add(tglbtnJustierlichtschranke, "cell 0 5");
		
		tglbtnAusgangslichtschranke = new JToggleButton("Ausgangslichtschranke");
		tglbtnAusgangslichtschranke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        HWaccess.write(Automat.Adressen.Ausgangslichtschranke.ordinal(), abstractButton.getModel().isSelected());
			}
		});
		tglbtnAusgangslichtschranke.setHorizontalAlignment(SwingConstants.LEFT);
		desktopPane.add(tglbtnAusgangslichtschranke, "cell 0 6");
		

		tglbtnEingangAuswahlklappe = new JToggleButton("Eingangslichtschranke Auswahlklappe");
		tglbtnEingangAuswahlklappe.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent actionEvent) {
        		AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        HWaccess.write(Automat.Adressen.AuswahlklappeEingangslichtschranke.ordinal(), abstractButton.getModel().isSelected());
        	}
        });
		tglbtnEingangAuswahlklappe.setHorizontalAlignment(SwingConstants.LEFT);
        desktopPane.add(tglbtnEingangAuswahlklappe, "cell 0 11");
        
		ButtonGroup groupL1 = new ButtonGroup();
		
		ButtonGroup groupL2 = new ButtonGroup();
		
		ButtonGroup groupL3 = new ButtonGroup();
        
        
        tglbtnLichtschrankepet = new JToggleButton("LichtschrankePET");
        tglbtnLichtschrankepet.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent actionEvent) {
        		AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
                HWaccess.write(Automat.Adressen.UebergabelichtschrankePET.ordinal(), abstractButton.getModel().isSelected());
        	}
        });
        tglbtnLichtschrankepet.setHorizontalAlignment(SwingConstants.LEFT);
        desktopPane.add(tglbtnLichtschrankepet, "cell 0 12");
        
        tglbtnLichtschrankemehrweg = new JToggleButton("LichtschrankeMehrweg");
        tglbtnLichtschrankemehrweg.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent actionEvent) {
        		AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
                HWaccess.write(Automat.Adressen.UebergabelichtschrankeMehrweg.ordinal(), abstractButton.getModel().isSelected());
        	}
        });
        tglbtnLichtschrankemehrweg.setHorizontalAlignment(SwingConstants.LEFT);
        desktopPane.add(tglbtnLichtschrankemehrweg, "cell 0 13");
        
        tglbtnLeuchte = new JToggleButton("Leuchte");
        tglbtnLeuchte.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent actionEvent) {
        		AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
                HWaccess.write(Automat.Adressen.Leuchte.ordinal(), abstractButton.getModel().isSelected());
        	}
        });
        tglbtnLeuchte.setHorizontalAlignment(SwingConstants.LEFT);
        desktopPane.add(tglbtnLeuchte, "cell 0 17");
        
        tglbtnTrte = new JToggleButton("Tr\u00F6te");
        tglbtnTrte.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent actionEvent) {
        		AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
                HWaccess.write(Automat.Adressen.Troete.ordinal(), abstractButton.getModel().isSelected());
        	}
        });
        tglbtnTrte.setHorizontalAlignment(SwingConstants.LEFT);
        desktopPane.add(tglbtnTrte, "cell 0 19");
        
        pVorderesLaufband = new JPanel();
        desktopPane.add(pVorderesLaufband, "cell 0 20,growx,aligny top");
        pVorderesLaufband.setLayout(new GridLayout(5, 0, 0, 0));
        
        lblVordereslaufband = new JLabel("Vorderes-Laufband");
        pVorderesLaufband.add(lblVordereslaufband);
        
        chckbxVorderesGesperrt = new JCheckBox("gesperrt");
        chckbxVorderesGesperrt.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent actionEvent) {
        		AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
        		
                if(abstractButton.getModel().isSelected()){
                	rdbtnVorderesStop.setEnabled(false);
                	rdbtnVorderesVorwaerts.setEnabled(false);
                	rdbtnVorderesRueckwaerts.setEnabled(false);
                	HWaccess.write(Automat.Adressen.LaufbandEingang.ordinal(), -1);
                }else{
                	rdbtnVorderesStop.setEnabled(true);
                	rdbtnVorderesVorwaerts.setEnabled(true);
                	rdbtnVorderesRueckwaerts.setEnabled(true);
                	HWaccess.write(Automat.Adressen.LaufbandEingang.ordinal(), 0);
                }
        	}
        });
        pVorderesLaufband.add(chckbxVorderesGesperrt);
        
        rdbtnVorderesStop = new JRadioButton("stop");
        rdbtnVorderesStop.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		HWaccess.write(Automat.Adressen.LaufbandEingang.ordinal(), 0);
        	}
        });
        pVorderesLaufband.add(rdbtnVorderesStop);
        
        rdbtnVorderesVorwaerts = new JRadioButton("vorw\u00E4rts");
        rdbtnVorderesVorwaerts.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		HWaccess.write(Automat.Adressen.LaufbandEingang.ordinal(), 1);
        	}
        });
        pVorderesLaufband.add(rdbtnVorderesVorwaerts);
        
        rdbtnVorderesRueckwaerts = new JRadioButton("r\u00FCckw\u00E4rts");
        rdbtnVorderesRueckwaerts.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		HWaccess.write(Automat.Adressen.LaufbandEingang.ordinal(), 2);
        	}
        });
        pVorderesLaufband.add(rdbtnVorderesRueckwaerts);
        groupL1.add(rdbtnVorderesStop);
        groupL1.add(rdbtnVorderesVorwaerts);
        groupL1.add(rdbtnVorderesRueckwaerts);
        
        pDrehLaufband = new JPanel();
        desktopPane.add(pDrehLaufband, "cell 0 21,growx,aligny top");
        pDrehLaufband.setLayout(new GridLayout(5, 0, 0, 0));
        
        lableDreh = new JLabel("Dreh-Laufband");
        pDrehLaufband.add(lableDreh);
        
        chckbxDrehGesperrt = new JCheckBox("gesperrt");
        chckbxDrehGesperrt.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent actionEvent) {
        		AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
        		
                if(abstractButton.getModel().isSelected()){
                	rdbtnDrehStop.setEnabled(false);
                	rdbtnDrehRechts.setEnabled(false);
                	rdbtnDrehLinks.setEnabled(false);
                	HWaccess.write(Automat.Adressen.LaufbandDrehen.ordinal(), -1);
                }else{
                	rdbtnDrehStop.setEnabled(true);
                	rdbtnDrehRechts.setEnabled(true);
                	rdbtnDrehLinks.setEnabled(true);
                	HWaccess.write(Automat.Adressen.LaufbandDrehen.ordinal(), 0);
                }
        	}
        });
        pDrehLaufband.add(chckbxDrehGesperrt);
        
        rdbtnDrehStop = new JRadioButton("stop");
        rdbtnDrehStop.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		HWaccess.write(Automat.Adressen.LaufbandDrehen.ordinal(), 0);
        	}
        });
        pDrehLaufband.add(rdbtnDrehStop);
        
        rdbtnDrehRechts = new JRadioButton("drehe rechts");
        rdbtnDrehRechts.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		HWaccess.write(Automat.Adressen.LaufbandDrehen.ordinal(), 1);
        	}
        });
        pDrehLaufband.add(rdbtnDrehRechts);
        
        rdbtnDrehLinks = new JRadioButton("drehe links");
        rdbtnDrehLinks.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		HWaccess.write(Automat.Adressen.LaufbandDrehen.ordinal(), 2);
        	}
        });
        pDrehLaufband.add(rdbtnDrehLinks);
        groupL2.add(rdbtnDrehStop);
        groupL2.add(rdbtnDrehRechts);
        groupL2.add(rdbtnDrehLinks);
		
		JScrollPane scrollPane = new JScrollPane();
		frmSimulationhelper.getContentPane().add(scrollPane, "cell 1 0 2 1,grow");
		
		txtrMonitoring = new JTextArea();
		scrollPane.setViewportView(txtrMonitoring);
		DefaultCaret caret = (DefaultCaret)txtrMonitoring.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		frmSimulationhelper.setLocation(490, 30);
        
        pHinteresLaufband = new JPanel();
        desktopPane.add(pHinteresLaufband, "cell 0 23,grow");
        pHinteresLaufband.setLayout(new GridLayout(0, 1, 0, 0));
        
        lblHintereslaufband = new JLabel("Hinteres-Laufband");
        pHinteresLaufband.add(lblHintereslaufband);
        
        chckbxHinteresGesperrt = new JCheckBox("gesperrt");
        chckbxHinteresGesperrt.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent actionEvent) {
        		AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
        		
                if(abstractButton.getModel().isSelected()){
                	rdbtnHinteresStop.setEnabled(false);
                	rdbtnHinteresVorwaerts.setEnabled(false);
                	rdbtnHinteresRueckwaerts.setEnabled(false);
                	HWaccess.write(Automat.Adressen.LaufbandAusgang.ordinal(), -1);
                }else{
                	rdbtnHinteresStop.setEnabled(true);
                	rdbtnHinteresVorwaerts.setEnabled(true);
                	rdbtnHinteresRueckwaerts.setEnabled(true);
                	HWaccess.write(Automat.Adressen.LaufbandAusgang.ordinal(), 0);
                }
        	}
        });
        pHinteresLaufband.add(chckbxHinteresGesperrt);
        
        rdbtnHinteresStop = new JRadioButton("stop");
        rdbtnHinteresStop.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		HWaccess.write(Automat.Adressen.LaufbandAusgang.ordinal(), 0);
        	}
        });
        pHinteresLaufband.add(rdbtnHinteresStop);
        
        rdbtnHinteresVorwaerts = new JRadioButton("vorw\u00E4rts");
        rdbtnHinteresVorwaerts.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		HWaccess.write(Automat.Adressen.LaufbandAusgang.ordinal(), 1);
        	}
        });
        pHinteresLaufband.add(rdbtnHinteresVorwaerts);
        
        rdbtnHinteresRueckwaerts = new JRadioButton("r\u00FCckw\u00E4rts");
        rdbtnHinteresRueckwaerts.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		HWaccess.write(Automat.Adressen.LaufbandAusgang.ordinal(), 2);
        	}
        });
        pHinteresLaufband.add(rdbtnHinteresRueckwaerts);
        groupL3.add(rdbtnHinteresStop);
        groupL3.add(rdbtnHinteresVorwaerts);
        groupL3.add(rdbtnHinteresRueckwaerts);
		
		
		
        pEndbehaelter = new JPanel();
        desktopPane.add(pEndbehaelter, "cell 0 28,grow");
        pEndbehaelter.setLayout(new GridLayout(0, 1, 0, 0));
        
        lblEndbehaelter = new JLabel("Endbehälter");
        pEndbehaelter.add(lblEndbehaelter);
        
        tglbtnPetBehlter = new JToggleButton("PET");
        tglbtnPetBehlter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        HWaccess.write(Automat.Adressen.FuellstandPET.ordinal(), abstractButton.getModel().isSelected());
			}
		});
        pEndbehaelter.add(tglbtnPetBehlter);
        
        tglbtnMehrwegBehlter = new JToggleButton("Mehrweg");
        tglbtnMehrwegBehlter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        HWaccess.write(Automat.Adressen.FuellstandMehrweg.ordinal(), abstractButton.getModel().isSelected());
			}
		});
        pEndbehaelter.add(tglbtnMehrwegBehlter);
        
	}
	
	public void MonitoringUpdate(String message)
	{
		txtrMonitoring.append(message+'\n');		
	}
	
	public void updateUI(ArrayList<Comparable> list){
		//HWLayer HWaccess = HWLayer.getInstance();
		//Boolean tmp_bool = new Boolean(false);
		// sollte lieber so sein
		//HWaccess.read(Automat.Adressen.Leuchte.ordinal(), tmp_bool);
		
		tglbtnLeuchte.setSelected((Boolean)list.get(Automat.Adressen.Leuchte.ordinal()));
		tglbtnEingangslichtschranke.setSelected((Boolean)list.get(Automat.Adressen.Eingangslichtschranke.ordinal()));;
		tglbtnJustierlichtschranke.setSelected((Boolean)list.get(Automat.Adressen.Justierlichtschranke.ordinal()));;
		tglbtnAusgangslichtschranke.setSelected((Boolean)list.get(Automat.Adressen.Ausgangslichtschranke.ordinal()));;
		tglbtnEingangAuswahlklappe.setSelected((Boolean)list.get(Automat.Adressen.AuswahlklappeEingangslichtschranke.ordinal()));;
		tglbtnLichtschrankepet.setSelected((Boolean)list.get(Automat.Adressen.UebergabelichtschrankePET.ordinal()));;
		tglbtnLichtschrankemehrweg.setSelected((Boolean)list.get(Automat.Adressen.UebergabelichtschrankeMehrweg.ordinal()));;
		tglbtnTrte.setSelected((Boolean)list.get(Automat.Adressen.Troete.ordinal()));;
		tglbtnMehrwegBehlter.setSelected((Boolean)list.get(Automat.Adressen.FuellstandMehrweg.ordinal()));;
		tglbtnPetBehlter.setSelected((Boolean)list.get(Automat.Adressen.FuellstandPET.ordinal()));;
		
		switch ((Integer)list.get(Automat.Adressen.LaufbandEingang.ordinal())) {
		default:chckbxVorderesGesperrt.setSelected(true); 
		case 0: rdbtnVorderesStop.setSelected(true);			
			break;

		case 1: rdbtnVorderesVorwaerts.setSelected(true);
			break;
		case -1: rdbtnVorderesRueckwaerts.setSelected(true);
			break;
		}		

		switch ((Integer)list.get(Automat.Adressen.LaufbandDrehen.ordinal())) {
		default:chckbxDrehGesperrt.setSelected(true);
		case 0: rdbtnDrehStop.setSelected(true);			
			break;

		case 1: rdbtnDrehRechts.setSelected(true);
			break;
		case -1: rdbtnDrehLinks.setSelected(true);
			break;
		}
		
		switch ((Integer)list.get(Automat.Adressen.LaufbandAusgang.ordinal())) {
		default:chckbxHinteresGesperrt.setSelected(true);
		case 0: rdbtnHinteresStop.setSelected(true);			
			break;

		case 1: rdbtnHinteresVorwaerts.setSelected(true);
			break;
		case -1: rdbtnHinteresRueckwaerts.setSelected(true);
			break;
		}	
	}
	

}
