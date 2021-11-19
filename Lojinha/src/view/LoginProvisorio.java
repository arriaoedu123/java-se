package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.DAO;

public class LoginProvisorio extends JFrame {
	private JTextField txtLogin;
	private JLabel lblNewLabel_1;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginProvisorio frame = new LoginProvisorio();
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
	public LoginProvisorio() {
		setTitle("Login");
		setResizable(false);
		setBounds(100, 100, 467, 330);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setBounds(86, 83, 46, 14);
		getContentPane().add(lblNewLabel);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(142, 80, 208, 20);
		getContentPane().add(txtLogin);
		txtLogin.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setBounds(86, 130, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(142, 127, 208, 20);
		getContentPane().add(txtSenha);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});
		btnEntrar.setBounds(86, 178, 89, 23);
		getContentPane().add(btnEntrar);

	} // fim do construtor
	
	DAO dao = new DAO();
	
	/**
	 * M�todo respons�vel pela autentica��o do usu�rio
	 */
	private void logar() {
		if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o login", "Aten��o!", JOptionPane.WARNING_MESSAGE);
			// posicionar o cursor no campo ap�s fechar a mensagem
			txtLogin.requestFocus();
		} else if (txtSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a senha", "Aten��o!", JOptionPane.WARNING_MESSAGE);
			// posicionar o cursor no campo ap�s fechar a mensagem
			txtSenha.requestFocus();
		} else {
			try {
				String read = "select * from clientes where email = ? and senha = md5(?)";
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(read);
				pst.setString(1, txtLogin.getText());
				pst.setString(2, txtSenha.getText());
				// a linha abaixo executa a query(instru��o sql) armazenando o resultado no objeto rs
				ResultSet rs = pst.executeQuery();
				// se existir login e senha correspondente
				if (rs.next()) {
					// ir para a �rea do cliente
					AreaCliente cliente = new AreaCliente();
					cliente.setVisible(true);
					// ap�s o login, finalizar o JFrames
					this.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Login e/ou senha inv�lido(s)", "Aten��o!", JOptionPane.WARNING_MESSAGE);
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}
