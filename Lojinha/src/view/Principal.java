
package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DAO;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Principal extends JFrame {

	private JPanel contentPane;
	private JLabel lblData;
	private JLabel lblStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				// evento disparado ao ativar o JFrame
				setarData();
				status();
			}
		});
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/icones/pc.png")));
		setTitle("Lojinha");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 430);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.scrollbar);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		panel.setBounds(0, 343, 604, 48);
		contentPane.add(panel);
		panel.setLayout(null);

		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(Principal.class.getResource("/icones/dbof.png")));
		lblStatus.setBounds(10, 8, 32, 32);
		panel.add(lblStatus);

		lblData = new JLabel("");
		lblData.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblData.setForeground(Color.WHITE);
		lblData.setBounds(180, 16, 312, 14);
		panel.add(lblData);

		JButton btnNewButton = new JButton("");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBackground(SystemColor.scrollbar);
		btnNewButton.setBorder(null);
		btnNewButton.setToolTipText("Estoque");
		btnNewButton.setIcon(new ImageIcon(Principal.class.getResource("/icones/estoque.png")));
		btnNewButton.setBounds(10, 65, 128, 128);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setBackground(SystemColor.scrollbar);
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setToolTipText("Relat\u00F3rios");
		btnNewButton_1.setIcon(new ImageIcon(Principal.class.getResource("/icones/relatorios.png")));
		btnNewButton_1.setBounds(10, 204, 128, 128);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// clicar no botao
				Clientes clientes = new Clientes(); // criar objeto
				clientes.setVisible(true); // exibir o JDialog Sobre
			}
		});
		btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_2.setBackground(SystemColor.scrollbar);
		btnNewButton_2.setBorder(null);
		btnNewButton_2.setToolTipText("Clientes");
		btnNewButton_2.setIcon(new ImageIcon(Principal.class.getResource("/icones/clientes.png")));
		btnNewButton_2.setBounds(235, 65, 128, 128);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// clicar no botao
				Sobre sobre = new Sobre(); // criar objeto
				sobre.setVisible(true); // exibir o JDialog Sobre
			}
		});
		btnNewButton_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_3.setBackground(SystemColor.scrollbar);
		btnNewButton_3.setBorder(null);
		btnNewButton_3.setIcon(new ImageIcon(Principal.class.getResource("/icones/sobre.png")));
		btnNewButton_3.setToolTipText("Sobre");
		btnNewButton_3.setBounds(466, 65, 128, 128);
		contentPane.add(btnNewButton_3);

		JLabel lblNewLabel_1 = new JLabel("Lojinha");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(SystemColor.textHighlight);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_1.setBounds(10, 11, 584, 29);
		contentPane.add(lblNewLabel_1);
	} // fim do construtor

	/**
	 * Metodo responsavel por setar a data e hora no rodape
	 */
	private void setarData() {
		// as linhas abaixo sao usadas para obter e formatar a hora do sistema
		Date dataLabel = new Date();
		DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);

		// a linha abaixo substitui a label do rodape pela data
		lblData.setText(formatador.format(dataLabel));
	}

	/**
	 * Método responsável pela exibição do status de conexão
	 */
	private void status() {
		// criar um objeto de nome DAO para acessar o método de conexão na classe DAO
		DAO dao = new DAO();
		try {

			// abrir a conexão com o banco de dados
			Connection con = dao.conectar();

			// a linha abaixo exibe o retorno da conexão
			System.out.println("Conectado com sucesso");

			// mudando o ícone do rodapé no caso do banco e dados estar disponível
			if (con != null) {
				lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/dbon.png")));
			} else {
				lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/dbof.png")));
			}

			/// IMPORTANTE! sempre encerrar a conexão
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
