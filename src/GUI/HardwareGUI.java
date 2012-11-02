package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class HardwareGUI {

	private JFrame frame;
	private JTextField txtText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HardwareGUI window = new HardwareGUI();
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
	public HardwareGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow]", "[][][][][][][]"));
		
		JLabel lblFlaschenautomat = new JLabel("Flaschenautomat");
		frame.getContentPane().add(lblFlaschenautomat, "cell 0 1,alignx center");
		
		JButton btnBonDrucken = new JButton("Bon Drucken");
		btnBonDrucken.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Glue.PressedBonButton();
			}
		});
		frame.getContentPane().add(btnBonDrucken, "cell 0 3,alignx center");
		
		JLabel lblNachrichten = new JLabel("Nachrichten:");
		frame.getContentPane().add(lblNachrichten, "cell 0 5,alignx center");
		
		txtText = new JTextField();
		txtText.setText("Text");
		frame.getContentPane().add(txtText, "cell 0 6,growx");
		txtText.setColumns(10);
	}
}
