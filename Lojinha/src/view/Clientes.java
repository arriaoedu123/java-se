package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Atxy2k.CustomTextField.RestrictedTextField;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//import javax.swing.JPanel;
//import java.awt.SystemColor;
import javax.swing.JPasswordField;
import javax.swing.JButton;
//import javax.swing.border.BevelBorder;

import model.DAO;
import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JDesktopPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class Clientes extends JDialog {
	private JTextField txtPesquisar;
	private JTextField txtId;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JPasswordField txtSenha;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clientes dialog = new Clientes();
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
	public Clientes() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Clientes.class.getResource("/icones/pc.png")));
		setTitle("Clientes");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 627, 383);
		getContentPane().setLayout(null);

		txtPesquisar = new JTextField();
		txtPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// evento disparado ao soltar a tecla
				pesquisarCliente();
			}
		});
		txtPesquisar.setBounds(10, 11, 207, 32);
		getContentPane().add(txtPesquisar);
		txtPesquisar.setColumns(10);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Clientes.class.getResource("/icones/pesquisar.png")));
		lblNewLabel.setBounds(227, 11, 32, 32);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(10, 165, 20, 14);
		getContentPane().add(lblNewLabel_1);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(40, 162, 98, 20);
		getContentPane().add(txtId);
		txtId.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Nome");
		lblNewLabel_2.setBounds(148, 165, 39, 14);
		getContentPane().add(lblNewLabel_2);

		txtNome = new JTextField();
		txtNome.setBounds(197, 162, 404, 20);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setBounds(10, 206, 39, 14);
		getContentPane().add(lblNewLabel_3);

		txtEmail = new JTextField();
		txtEmail.setBounds(50, 203, 272, 20);
		getContentPane().add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Senha");
		lblNewLabel_4.setBounds(332, 206, 39, 14);
		getContentPane().add(lblNewLabel_4);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(381, 203, 220, 20);
		getContentPane().add(txtSenha);

		btnAdicionar = new JButton("");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarCliente();
			}
		});
		btnAdicionar.setIcon(new ImageIcon(Clientes.class.getResource("/icones/adicionar.png")));
		btnAdicionar.setBounds(156, 253, 80, 80);
		getContentPane().add(btnAdicionar);

		btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarCliente();
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.setIcon(new ImageIcon(Clientes.class.getResource("/icones/editar.png")));
		btnEditar.setBounds(246, 253, 80, 80);
		getContentPane().add(btnEditar);

		btnExcluir = new JButton("");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirCliente();
			}
		});
		btnExcluir.setEnabled(false);
		btnExcluir.setIcon(new ImageIcon(Clientes.class.getResource("/icones/excluir.png")));
		btnExcluir.setBounds(336, 253, 80, 80);
		getContentPane().add(btnExcluir);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(10, 51, 591, 102);
		getContentPane().add(desktopPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 591, 102);
		desktopPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// evento disparado ao clicar na linha da tabela
				setarCampos();
				setarSenha();
			}
		});
		scrollPane.setViewportView(table);

		// uso da biblioteca atxy2k para validações
		RestrictedTextField nome = new RestrictedTextField(this.txtNome);
		nome.setLimit(50);
		RestrictedTextField email = new RestrictedTextField(this.txtEmail);
		email.setLimit(50);
		RestrictedTextField senha = new RestrictedTextField(this.txtSenha);
		senha.setLimit(50);

	} // fim do construtor

	// criando um objeto para acessar a classe DAO
	DAO dao = new DAO();
	private JButton btnEditar;
	private JButton btnAdicionar;
	private JButton btnExcluir;

	/**
	 * Método responsável pela pesquisa do cliente com uso da biblioteca rs2xml
	 */
	private void pesquisarCliente() {
		// ? -> parâmetro
		String read = "select idcli as ID, nome as Cliente, email as Email from clientes where nome like ?";
		try {
			// abrir a conexao com o banco
			Connection con = dao.conectar();
			// preparar a query(instrucao sql) para pesquisar no banco
			PreparedStatement pst = con.prepareStatement(read);
			// substituir o parametro(?) Atencao ao % para completar a query
			// 1 -> parâmetro
			pst.setString(1, txtPesquisar.getText() + "%");
			// executar a query e obter os dados do banco (resultado)
			ResultSet rs = pst.executeQuery();
			// popular(preencher) a tabela com os dados do banco
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Método responsável por adicionar um cliente no banco de dados
	 */
	private void adicionarCliente() {
		// validação de campos obrigatórios
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome", "Atenção!", JOptionPane.WARNING_MESSAGE);
			// posicionar o cursor no campo após fechar a mensagem
			txtNome.requestFocus();
		} else if (txtEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o email", "Atenção!", JOptionPane.WARNING_MESSAGE);
			// posicionar o cursor no campo após fechar a mensagem
			txtEmail.requestFocus();
		} else if (txtSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a senha", "Atenção!", JOptionPane.WARNING_MESSAGE);
			// posicionar o cursor no campo após fechar a mensagem
			txtSenha.requestFocus();
		} else {
			// inserir o cliente no banco
			String create = "insert into clientes (nome,email,senha) values (?,?,md5(?))";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(create);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtEmail.getText());
				pst.setString(3, txtSenha.getText());
				// criando uma variável que irá executar a query e receber o valor1 em caso
				// positivo (inserção do cliente no banco de dados)
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
					limpar();
				}
				con.close();
				// o catch abaixo se refere ao valor duplicado de email(UNIQUE)
			} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Email já existente\nCadastre outro Email", "Atenção!",
						JOptionPane.WARNING_MESSAGE);
				txtEmail.setText(null);
				txtEmail.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(null, "Erro ao adicionar o cliente", "Atenção!",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	/**
	 * Método responsável por setar os campos da tabelo no formulário
	 */
	private void setarCampos() {
		// a linha abaixo obtem o conteúdo da linha da tabela
		// int (índice) [0], [1], [2],...
		int setar = table.getSelectedRow();
		// setar os campos
		txtId.setText(table.getModel().getValueAt(setar, 0).toString());
		txtNome.setText(table.getModel().getValueAt(setar, 1).toString());
		txtEmail.setText(table.getModel().getValueAt(setar, 2).toString());
		// gerenciar os botões
		btnAdicionar.setEnabled(false);
		btnEditar.setEnabled(true);
		btnExcluir.setEnabled(true);
	}

	/**
	 * Método específico para setar a senha
	 */
	private void setarSenha() {
		String read2 = "select senha from clientes where idcli = ?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, txtId.getText());
			// a linha abaixo executa a instrução sql e armazena o resultado no objeto rs
			ResultSet rs = pst.executeQuery();
			// a linha abaixo verifica se existe uma senha para o idcli
			if (rs.next()) {
				txtSenha.setText(rs.getString(1));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Método responsável pela edição dos dados do cliente
	 */
	private void editarCliente() {
		// validação de campos obrigatórios
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome", "Atenção!", JOptionPane.WARNING_MESSAGE);
			// posicionar o cursor no campo após fechar a mensagem
			txtNome.requestFocus();
		} else if (txtEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o email", "Atenção!", JOptionPane.WARNING_MESSAGE);
			// posicionar o cursor no campo após fechar a mensagem
			txtEmail.requestFocus();
		} else if (txtSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a senha", "Atenção!", JOptionPane.WARNING_MESSAGE);
			// posicionar o cursor no campo após fechar a mensagem
			txtSenha.requestFocus();
		} else {
			// editar o cliente no banco
			String update = "update clientes set nome = ?, email = ?, senha = md5(?) where idcli = ?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(update);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtEmail.getText());
				pst.setString(3, txtSenha.getText());
				pst.setString(4, txtId.getText());
				// criando uma variável que irá executar a query e receber o valor1 em caso
				// positivo (inserção do cliente no banco de dados)
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Dados do cliente editado com sucesso", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
					limpar();
				}
				con.close();
				// o catch abaixo se refere ao valor duplicado de email(UNIQUE)
			} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Email já existente\nCadastre outro Email", "Atenção!",
						JOptionPane.WARNING_MESSAGE);
				txtEmail.setText(null);
				txtEmail.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(null, "Erro ao editar os dados do cliente", "Atenção!",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	/**
	 * Método responsável por excluir o cliente do banco de dados
	 */
	private void excluirCliente() {
		// confimação de exclusão
		int confirma = JOptionPane.showConfirmDialog(null, "Deseja excluir este cliente?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from clientes where idcli = ?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtId.getText());
				int excluir = pst.executeUpdate();
				if (excluir == 1) {
					limpar();
					JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
				}
				con.close();
			} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Erro ao excluir\nCliente possui pedido em aberto", "Atenção!",
						JOptionPane.WARNING_MESSAGE);
			} catch (Exception e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(null, "Erro ao excluir o cliente", "Atenção!",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	/**
	 * Método responsável por limpar os campos e gereniar os botões
	 */
	private void limpar() {
		txtId.setText(null);
		txtNome.setText(null);
		txtEmail.setText(null);
		txtSenha.setText(null);
		// limpar a tabela
		((DefaultTableModel) table.getModel()).setRowCount(0);
		btnAdicionar.setEnabled(true);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);
	}
}
