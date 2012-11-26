package GUI;

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
import Automat.HWLayer;
import Fassade.Fassade;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.text.DefaultCaret;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;


public class SimulationGUI {

	private JFrame frmSimulationhelper;
	private Fassade DieFassade;
	private JComboBox comboBox;
	private JTextArea txtrMonitoring;
	private JToggleButton tglbtnLeuchte;

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
		DieFassade = fassade;
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
		frmSimulationhelper.setBounds(100, 100, 500, 600);
		frmSimulationhelper.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSimulationhelper.getContentPane().setLayout(new MigLayout("", "[144px][144px,grow][193.00px]", "[554.00px,grow]"));
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.LIGHT_GRAY);
		frmSimulationhelper.getContentPane().add(desktopPane, "cell 0 0,grow");
		desktopPane.setLayout(new MigLayout("", "[140px]", "[12px][24px][25px][25px][15px][][][19px][][][][][][][]"));
		
		JLabel lblFlaschenautomat = new JLabel("Flaschenautomat");
		desktopPane.add(lblFlaschenautomat, "cell 0 0,alignx center,aligny center");
		
		String[] comboStrings = new String[FlaschenType.values().length];
		
		comboStrings[0] = FlaschenType.CodeNichtValide.toString();
		comboStrings[1] = FlaschenType.CodeUnlesbar.toString();
		comboStrings[2] = FlaschenType.Mehrweg.toString();
		comboStrings[3] = FlaschenType.PET.toString();
		
		comboBox =  new JComboBox(comboStrings);
		
		desktopPane.add(comboBox, "cell 0 2,growx,aligny center");
		
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
		desktopPane.add(btnFlascheeinlegen, "cell 0 3,alignx left");
		
		JToggleButton tglbtnEingangslichtschranke = new JToggleButton("Eingangslichtschranke");
		tglbtnEingangslichtschranke.setHorizontalAlignment(SwingConstants.LEFT);
		desktopPane.add(tglbtnEingangslichtschranke, "cell 0 4");
		
		JToggleButton tglbtnJustierlichtschranke = new JToggleButton("Justierlichtschranke");
		tglbtnJustierlichtschranke.setHorizontalAlignment(SwingConstants.LEFT);
		desktopPane.add(tglbtnJustierlichtschranke, "cell 0 5");
		
		JToggleButton tglbtnNewToggleButton = new JToggleButton("Ausgangslichtschranke");
		tglbtnNewToggleButton.setHorizontalAlignment(SwingConstants.LEFT);
		desktopPane.add(tglbtnNewToggleButton, "cell 0 6");
		
		JToggleButton tglbtnLichtschrankepet = new JToggleButton("LichtschrankePET");
		tglbtnLichtschrankepet.setHorizontalAlignment(SwingConstants.LEFT);
		desktopPane.add(tglbtnLichtschrankepet, "cell 0 7");
		
		JToggleButton tglbtnLichtschrankemehrweg = new JToggleButton("LichtschrankeMehrweg");
		tglbtnLichtschrankemehrweg.setHorizontalAlignment(SwingConstants.LEFT);
		desktopPane.add(tglbtnLichtschrankemehrweg, "cell 0 8");
		
		JToggleButton tglbtnVordereslaufband = new JToggleButton("Vordereslaufband");
		tglbtnVordereslaufband.setHorizontalAlignment(SwingConstants.LEFT);
		desktopPane.add(tglbtnVordereslaufband, "cell 0 9");
		
		JToggleButton tglbtnDrehlaufband = new JToggleButton("Drehlaufband");
		tglbtnDrehlaufband.setHorizontalAlignment(SwingConstants.LEFT);
		desktopPane.add(tglbtnDrehlaufband, "cell 0 10");
		
		JToggleButton tglbtnHintereslaufband = new JToggleButton("Hintereslaufband");
		tglbtnHintereslaufband.setHorizontalAlignment(SwingConstants.LEFT);
		desktopPane.add(tglbtnHintereslaufband, "cell 0 11");
		
		tglbtnLeuchte = new JToggleButton("Leuchte");
		tglbtnLeuchte.setHorizontalAlignment(SwingConstants.LEFT);
		desktopPane.add(tglbtnLeuchte, "cell 0 12");
		
		JToggleButton tglbtnTrte = new JToggleButton("Tr\u00F6te");
		tglbtnTrte.setHorizontalAlignment(SwingConstants.LEFT);
		desktopPane.add(tglbtnTrte, "cell 0 13");
		
		JScrollPane scrollPane = new JScrollPane();
		frmSimulationhelper.getContentPane().add(scrollPane, "cell 1 0 2 1,grow");
		
		txtrMonitoring = new JTextArea();
		scrollPane.setViewportView(txtrMonitoring);
		DefaultCaret caret = (DefaultCaret)txtrMonitoring.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		frmSimulationhelper.setLocation(490, 30);
	}
	
	public void MonitoringUpdate(String message)
	{
		txtrMonitoring.append(message+'\n');		
	}
	
	public void updateUI(){
		HWLayer HWaccess = HWLayer.getInstance();
		Boolean tmp_bool = new Boolean(false);
		
		HWaccess.read(Automat.Adressen.Leuchte.ordinal(), tmp_bool);
		tglbtnLeuchte.setSelected(tmp_bool);
	}
	

}
