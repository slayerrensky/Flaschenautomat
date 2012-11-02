package GUI;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Automat.FlaschenType;
import Fassade.Fassade;

public class SimulationGUI {

	public JFrame frame;
	private JTextField txtText;
	private Fassade DieFassade;
	private JComboBox comboBox;
	private JTextPane txtpnMonitoring;

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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 451, 299);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[144px][144px][149px]", "[278px]"));
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(desktopPane, "cell 0 0,grow");
		desktopPane.setLayout(new MigLayout("", "[140px]", "[12px][24px][25px][25px][15px][19px][][][][]"));
		
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
		btnFlascheeinlegen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String Flasche = comboBox.getSelectedItem().toString();
				DieFassade.simFlascheEingelegt(FlaschenType.valueOf(Flasche));
				//Glue.InsertBottle();
			}
		});
		desktopPane.add(btnFlascheeinlegen, "cell 0 3,alignx center,aligny center");
		
		JButton btnBondrucken = new JButton("BonDrucken");
		btnBondrucken.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Glue.PressedBonButton();
			}
		});
		desktopPane.add(btnBondrucken, "cell 0 5,alignx center,aligny center");
		
		JLabel lblNachrichten = new JLabel("Nachrichten");
		desktopPane.add(lblNachrichten, "cell 0 7,alignx center,aligny center");
		
		txtText = new JTextField();
		txtText.setText("Text");
		desktopPane.add(txtText, "cell 0 8,growx,aligny center");
		txtText.setColumns(10);
		
		JTextPane txtpnBondrucker = new JTextPane();
		txtpnBondrucker.setText("BonDrucker");
		frame.getContentPane().add(txtpnBondrucker, "cell 1 0,grow");
		
		txtpnMonitoring = new JTextPane();
		txtpnMonitoring.setText("Monitoring");
		frame.getContentPane().add(txtpnMonitoring, "cell 2 0,grow");
	}
	
	public void MonitoringUpdate(String message)
	{
		txtpnMonitoring.setText(message);
		
	}
	

}
