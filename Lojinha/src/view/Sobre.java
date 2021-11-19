package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class Sobre extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sobre dialog = new Sobre();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Sobre() {
		setModal(true);
		setResizable(false);
		setTitle("Sobre");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/icones/pc.png")));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sistema de gest\u00E3o de E-Commerce");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 41, 414, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Autor: Arreaum");
		lblNewLabel_1.setBounds(20, 76, 404, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Sob a licen\u00E7a MIT");
		lblNewLabel_2.setBounds(20, 101, 404, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(Sobre.class.getResource("/icones/mit.png")));
		lblNewLabel_3.setBounds(360, 186, 64, 64);
		getContentPane().add(lblNewLabel_3);

	}

}
