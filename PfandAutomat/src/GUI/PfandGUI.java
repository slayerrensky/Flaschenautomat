package GUI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;


public class PfandGUI {

	private JFrame frame;
	private JTextField txtText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PfandGUI window = new PfandGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PfandGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[144px][144px][149px]", "[278px]"));
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(desktopPane, "cell 0 0,grow");
		desktopPane.setLayout(new MigLayout("", "[140px]", "[12px][24px][25px][25px][15px][19px][][][][]"));
		
		JLabel lblFlaschenautomat = new JLabel("Flaschenautomat");
		desktopPane.add(lblFlaschenautomat, "cell 0 0,alignx center,aligny center");
		
		String[] comboStrings = { "Mehrweg", "PET", "CodeNichtValide", "CodeUnlesbar"};
		JComboBox comboBox =  new JComboBox(comboStrings);
		

		desktopPane.add(comboBox, "cell 0 2,growx,aligny center");
		
		JButton btnFlascheeinlegen = new JButton("FlascheEinlegen");		
		btnFlascheeinlegen.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent arg0) {
			}
			});
		
		desktopPane.add(btnFlascheeinlegen, "cell 0 3,alignx center,aligny center");
		
		JButton btnBondrucken = new JButton("BonDrucken");
		btnBondrucken.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		
		JTextPane txtpnMonitoring = new JTextPane();
		txtpnMonitoring.setText("Monitoring");
		frame.getContentPane().add(txtpnMonitoring, "cell 2 0,grow");
	}

}
