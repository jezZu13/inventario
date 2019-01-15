
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField nombre_usuario;
	private Connection conexion;
	private JPasswordField pass_usuario;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
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
	public login() {
		String path2 = "media/logo_ventana.png";
		URL url2 = this.getClass().getResource(path2);
		ImageIcon icon2 = new ImageIcon(url2);
		setIconImage(Toolkit.getDefaultToolkit().getImage("media/logo_ventana.png"));
		setTitle("Login");
		cargarDriver();
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 392, 422);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		String path1 = "media/logo.png";
		URL url1 = this.getClass().getResource(path1);
		ImageIcon icon1 = new ImageIcon(url1);
		label.setIcon(icon1);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(117, 28, 143, 145);
		contentPane.add(label);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(21, 184, 344, 186);
		contentPane.add(panel);
		panel.setLayout(null);
		try {
			conexion=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/qzr440", "root", "");
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, "Ha sido imposible conectar con la base de datos \n"+ "Revise los datos de Conexión.");
			//CÓMO SACAR UN MENSAJE CON EL TIPO DE ERROR --> JOptionPane.showMessageDialog(this, e.getMessage().toString());
		}
		
		JButton btnAcceder = new JButton("Acceder");
		btnAcceder.setBorder(null);
		btnAcceder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				String user=nombre_usuario.getText();
				String pass=pass_usuario.getText();
				String rol=comboBox.getSelectedItem().toString().toLowerCase();
				String sql="select * from `usuarios_app` where `nombre_usuario`='"+ user+"' and `pass_usuario`='"+pass+"' and `rol_usuario`='"+rol+"';";
				
				/*Debug
				System.out.println(sql);
				*/
				
				try {
					Statement comando;
					comando = conexion.createStatement();
					ResultSet registro = comando.executeQuery(sql);
					int i = 0;
					
					for ( i = 0; registro.next(); i++) {}	//CONTADOR DE REGISTRO
					
					/*Debug
					System.out.println(i);
					*/
					if(i>3) {	//CONTROL DE REGISTROS PARA VER SI HAY ALGÚN USUARIO
						JOptionPane.showMessageDialog(null,"Datos erróneos","Error",JOptionPane.ERROR_MESSAGE);
					}else if(i==0){
						JOptionPane.showMessageDialog(null,"No existe ningún usuario con los datos introducidos","Error",JOptionPane.ERROR_MESSAGE);
					}else {
						//JOptionPane.showConfirmDialog(null, "Acceso Correcto");
						JOptionPane.showMessageDialog(null,"Bienvenido a la aplicación","Correcto",JOptionPane.INFORMATION_MESSAGE);
						Inicio inicio = new Inicio();
						inicio.setVisible(true);
						setVisible(false);
						
					}
					
				} catch (SQLException e) {
					// TODO: handle exception
					System.out.println(e.getMessage().toString());
				}
				
				
			}
		});
		btnAcceder.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAcceder.setBounds(90, 141, 145, 34);
		panel.add(btnAcceder);
		btnAcceder.setBackground(Color.ORANGE);
		
		pass_usuario = new JPasswordField();
		pass_usuario.setBounds(127, 52, 143, 20);
		panel.add(pass_usuario);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(10, 28, 124, 17);
		panel.add(lblUsuario);
		lblUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblContrasena = new JLabel("Contrase\u00F1a:");
		lblContrasena.setBounds(10, 55, 124, 17);
		panel.add(lblContrasena);
		lblContrasena.setHorizontalAlignment(SwingConstants.LEFT);
		lblContrasena.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblRol = new JLabel("Rol:");
		lblRol.setBounds(10, 83, 143, 17);
		panel.add(lblRol);
		lblRol.setHorizontalAlignment(SwingConstants.LEFT);
		lblRol.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		nombre_usuario = new JTextField();
		nombre_usuario.setBounds(127, 21, 143, 20);
		panel.add(nombre_usuario);
		nombre_usuario.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setBorder(null);
		comboBox.setBounds(129, 83, 141, 20);
		panel.add(comboBox);
		comboBox.addItem("Administrador");
		comboBox.addItem("Básico");
		
	}
	private void cargarDriver() {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception ex) {
			System.out.println(ex.getMessage().toString());
			
		}
	}
}
