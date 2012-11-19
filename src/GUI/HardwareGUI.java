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

import Fassade.Fassade;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JSeparator;


public class HardwareGUI {

	private JFrame frmHardware;
	private Fassade DieFassade;
	private JTextPane txtdisplay;
	private JTextArea druckerausgabe;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					HardwareGUI window = new HardwareGUI();
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
	public HardwareGUI(Fassade fassade) {
		DieFassade = fassade;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHardware = new JFrame();
		frmHardware.setVisible(true);
		frmHardware.setResizable(false);
		frmHardware.setTitle("Hardware");
		frmHardware.setBounds(100, 100, 450, 300);
		frmHardware.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHardware.getContentPane().setLayout(new MigLayout("", "[grow][grow]", "[][][][][][grow][grow][][][grow]"));
		
		JLabel lblFlaschenautomat = new JLabel("Flaschenautomat");
		frmHardware.getContentPane().add(lblFlaschenautomat, "cell 0 0,alignx center");
		
		JButton btnBonDrucken = new JButton("Bon Drucken");
		btnBonDrucken.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DieFassade.bonAnfordern();
			}
		});
		btnBonDrucken.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//.PressedBonButton();
			}
		});
		
		JLabel lblLeuchte = new JLabel("Leuchte:");
		frmHardware.getContentPane().add(lblLeuchte, "flowx,cell 1 0,alignx center");
		frmHardware.getContentPane().add(btnBonDrucken, "cell 0 1,alignx center");
		
		JLabel lblNachrichten = new JLabel("Display:");
		frmHardware.getContentPane().add(lblNachrichten, "cell 0 2,alignx center");
		
		JLabel lblDrucker = new JLabel("Druckerausgabe:");
		frmHardware.getContentPane().add(lblDrucker, "cell 1 2,alignx center,aligny center");
		
		druckerausgabe = new JTextArea();
		druckerausgabe.setEditable(false);
		frmHardware.getContentPane().add(druckerausgabe, "cell 1 3 1 7,grow");
		
		txtdisplay = new JTextPane();
		txtdisplay.setEditable(false);
		frmHardware.getContentPane().add(txtdisplay, "cell 0 3 1 7,grow");
		
		Component horizontalStrut = Box.createHorizontalStrut(40);
		frmHardware.getContentPane().add(horizontalStrut, "cell 1 0");
		
		JLabel lblTte = new JLabel("Tr\u00F6te:");
		frmHardware.getContentPane().add(lblTte, "cell 1 0,alignx center");
		
		frmHardware.setLocation(20, 30);
	}
	
	public void updateDisplay(String message){
		txtdisplay.setText(message);
	}
	
	public void drucken(String message){
		druckerausgabe.setText(message);
	}
}
