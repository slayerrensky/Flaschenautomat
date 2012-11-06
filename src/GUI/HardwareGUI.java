package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextPane;


public class HardwareGUI {

	private JFrame frame;

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
		frame.getContentPane().setLayout(new MigLayout("", "[grow][grow]", "[][][][][][grow][][][grow]"));
		
		JLabel lblFlaschenautomat = new JLabel("Flaschenautomat");
		frame.getContentPane().add(lblFlaschenautomat, "cell 0 0,alignx center");
		
		JButton btnBonDrucken = new JButton("Bon Drucken");
		btnBonDrucken.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//.PressedBonButton();
			}
		});
		
		JLabel lblDrucker = new JLabel("Druckerausgabe:");
		frame.getContentPane().add(lblDrucker, "cell 1 0");
		frame.getContentPane().add(btnBonDrucken, "cell 0 1,alignx center");
		
		JLabel lblNachrichten = new JLabel("Display:");
		frame.getContentPane().add(lblNachrichten, "cell 0 2,alignx center");
		
		JTextPane textPane = new JTextPane();
		frame.getContentPane().add(textPane, "cell 0 3 1 6,grow");
		
		JTextPane textPane_1 = new JTextPane();
		frame.getContentPane().add(textPane_1, "cell 1 1 1 8,grow");
	}
}
