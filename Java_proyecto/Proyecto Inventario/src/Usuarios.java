import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JCheckBox;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;



public class Usuarios extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel modelo;
	private Connection conexion;
	private JTable table;
	private JTextField txtPass;
	private JTextField txtNombre;
	private JCheckBox administrador;
	private JCheckBox basico;
	private String idSelected;
	private JTextField txtid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuarios frame = new Usuarios();
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
	public Usuarios() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("Assets\\logo_ventana.png"));
		setTitle("Usuarios");
		
		cargarDriver();
		
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/qzr440","root","");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Ha sido imposible conectar a la base de datos");
			JOptionPane.showMessageDialog(this, e.getMessage().toString());
			e.printStackTrace();
		}
		
		//Datos para el modelo de la tabla.
		
		modelo = new DefaultTableModel();
		modelo.addColumn("");
		modelo.addColumn("Nombre Usuario");
		modelo.addColumn("Contraseña");
		modelo.addColumn("Rol");
		
		rellenarTabla();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 611, 578);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setBackground(Color.white);
		
		table = new JTable();
		table.setFont(new Font("Arial", Font.PLAIN, 12));
		JTableHeader header = table.getTableHeader();
	    header.setBackground(Color.ORANGE);
	    header.setFont(new Font("Roboto", Font.BOLD, 13));
		table.setEnabled(false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = table.rowAtPoint(e.getPoint());
				int columna = table.columnAtPoint(e.getPoint());
				String admin="administrador";
				
				if (columna==1) {
					txtid.setText((String) table.getValueAt(fila, columna-1));
					txtNombre.setText(table.getValueAt(fila, columna).toString());
					txtPass.setText(table.getValueAt(fila, columna+1).toString());
					
					if(admin.equalsIgnoreCase((String) table.getValueAt(fila, columna+2))) {
						
						administrador.setSelected(true);
						basico.setSelected(false);
					}else {
						basico.setSelected(true);
						administrador.setSelected(false);
					}
				} else if(columna==2){
					txtid.setText((String) table.getValueAt(fila, columna-2));
					txtNombre.setText(table.getValueAt(fila, columna-1).toString());
					txtPass.setText(table.getValueAt(fila, columna).toString());
					if((admin.equalsIgnoreCase((String) table.getValueAt(fila, columna+1))) ) {
						administrador.setSelected(true);
						basico.setSelected(false);
					}else {
						basico.setSelected(true);
						administrador.setSelected(false);
					}					
				}else if(columna==3){
					txtid.setText((String) table.getValueAt(fila, columna-3));
					txtNombre.setText(table.getValueAt(fila, columna-2).toString());
					txtPass.setText(table.getValueAt(fila, columna-1).toString());
					if((admin.equalsIgnoreCase((String) table.getValueAt(fila, columna))) ) {
						administrador.setSelected(true);
						basico.setSelected(false);
					}else {
						basico.setSelected(true);
						administrador.setSelected(false);
					}
				}else{
					txtNombre.setText(table.getValueAt(fila, columna+1).toString());
					txtPass.setText(table.getValueAt(fila, columna+2).toString());
					txtid.setText((String) table.getValueAt(fila, columna));
					if(admin.equalsIgnoreCase((String) table.getValueAt(fila, columna+3)))  {
						administrador.setSelected(true);
						basico.setSelected(false);
					}else {
						basico.setSelected(true);
						administrador.setSelected(false);
					}
				}
			}
		});
		table.setBackground(Color.WHITE);
		table.setModel(modelo);
		scrollPane.setViewportView(table);
		//Expandir toda la tabla sobre el scrollPane.
		table.setFillsViewportHeight(true);
		//Para ocultar la primera columna:
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setMinWidth(-1);
		table.getColumnModel().getColumn(0).setMaxWidth(-1);
		table.getColumnModel().getColumn(0).setPreferredWidth(-1);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		//Para permitir ordenar el contenido por columnas:
		table.setAutoCreateRowSorter(true);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		
		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.setBorder(null);
		btnInsertar.setBackground(Color.ORANGE);
		btnInsertar.setFont(new Font("Arial", Font.BOLD, 14));
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBorder(null);
		btnModificar.setBackground(Color.ORANGE);
		btnModificar.setFont(new Font("Arial", Font.BOLD, 14));
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBorder(null);
		btnEliminar.setBackground(Color.ORANGE);
		btnEliminar.setFont(new Font("Arial", Font.BOLD, 14));
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBorder(null);
		btnLimpiar.setBackground(Color.ORANGE);
		btnLimpiar.setFont(new Font("Arial", Font.BOLD, 14));
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNombre.setText("");
				txtPass.setText("");
				administrador.setSelected(false);
				basico.setSelected(false);
				txtid.setText("");
			}
		});
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					idSelected= txtid.getText().toString();
					String sql = "DELETE from `usuarios_app` WHERE `id_usuario`= '"+idSelected+"'";
					Statement comando = conexion.createStatement();
					cargarDriver();
					comando.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente");
					limpiar();

				} catch (Exception e) {
					System.out.println("Error");
					e.printStackTrace();
				}

			}
		});
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					String nombre = txtNombre.getText();
					String pass = txtPass.getText();
					String rol;
					idSelected=txtid.getText().toString();
					if(administrador.isSelected()) {
						rol="administrador";
					}else {
						rol="basico";
					}
					
					String sql = "UPDATE `usuarios_app` SET `nombre_usuario`='"+nombre+"',`pass_usuario`='"+pass+"', `rol_usuario`='"+rol+"' WHERE `id_usuario`= '"+idSelected+"'";
					Statement comando = conexion.createStatement();
					cargarDriver();
					comando.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Usuario modificado correctamente");
					limpiar();
				} catch (Exception e) {
					System.out.println("Error");
					e.printStackTrace();
				}

			}
		});
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String nombre = txtNombre.getText();
					String pass = txtPass.getText();
					String rol;
					if(administrador.isSelected()) {
						rol="administrador";
					}else {
						rol="basico";
					}
					
					String sql = "INSERT INTO `usuarios_app`(`nombre_usuario`, `pass_usuario`, `rol_usuario`) VALUES ('"+nombre+"', '"+pass+"','"+rol+"')";
					
					Statement comando = conexion.createStatement();
					cargarDriver();
					comando.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Usuario agregado correctamente");
					limpiar();
					
				} catch (Exception e) {
					System.out.println(e.getMessage().toString());
					e.printStackTrace();
				}
				
			}
		});
		
		JLabel lblNombreUsuario = new JLabel("Nombre Usuario:");
		lblNombreUsuario.setFont(new Font("Arial", Font.BOLD, 13));
		
		JLabel lblContrasena = new JLabel("Contrase\u00F1a:");
		lblContrasena.setFont(new Font("Arial", Font.BOLD, 13));
		
		txtNombre = new JTextField();
		txtNombre.setBorder(null);
		txtNombre.setFont(new Font("Arial", Font.PLAIN, 13));
		txtNombre.setColumns(10);
		
		txtPass = new JTextField();
		txtPass.setBorder(null);
		txtPass.setFont(new Font("Arial", Font.PLAIN, 13));
		txtPass.setColumns(10);
		
		JLabel lblRolDeUsuario = new JLabel("Rol de Usuario:");
		lblRolDeUsuario.setFont(new Font("Arial", Font.BOLD, 13));
		
		administrador = new JCheckBox("Administrador");
		administrador.setBackground(Color.WHITE);
		administrador.setFont(new Font("Arial", Font.PLAIN, 12));
		administrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				basico.setSelected(false);
			}
		});
		
		administrador.setHorizontalAlignment(SwingConstants.LEFT);
		
		basico = new JCheckBox("B\u00E1sico");
		basico.setBackground(Color.WHITE);
		basico.setFont(new Font("Arial", Font.PLAIN, 12));
		basico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				administrador.setSelected(false);
			}
		});
		
		txtid = new JTextField();
		txtid.setVisible(false);
		
		txtid.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.ORANGE);
		separator.setForeground(Color.ORANGE);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(Color.ORANGE);
		separator_1.setForeground(Color.ORANGE);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(497, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
					.addGap(23))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(50)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblContrasena)
						.addComponent(lblRolDeUsuario)
						.addComponent(lblNombreUsuario))
					.addGap(22)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(separator_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
						.addComponent(separator, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
						.addComponent(txtPass, Alignment.LEADING, 358, 358, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(administrador)
							.addGap(18)
							.addComponent(basico))
						.addComponent(txtNombre, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE))
					.addGap(57))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(txtid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(44)
							.addComponent(lblNombreUsuario))
						.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblContrasena)
						.addComponent(txtPass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGap(24)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRolDeUsuario)
						.addComponent(administrador)
						.addComponent(basico))
					.addGap(49)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addGap(127))
		);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(89)
					.addComponent(btnInsertar, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
					.addGap(5)
					.addComponent(btnModificar, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
					.addGap(5)
					.addComponent(btnEliminar, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
					.addGap(5)
					.addComponent(btnLimpiar, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
					.addGap(61))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnLimpiar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
						.addComponent(btnEliminar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
						.addComponent(btnModificar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
						.addComponent(btnInsertar, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		contentPane.setLayout(gl_contentPane);
		

	}
	
	public void limpiar() {
		int j=0;
		int limite=modelo.getRowCount();
		
		while(j<limite) {
			modelo.removeRow(0);
			j++;
		}
		
		rellenarTabla();
		txtNombre.setText("");
		txtPass.setText("");
		administrador.setSelected(false);
		basico.setSelected(false);
	}
	

	public void rellenarTabla() {
		try {
			
			Object[] fila=new Object[4]; //Creamos un objeto con tantos parametros como datos retorne cada fila.
			
			String sql = "select * from `usuarios_app`;";
			Statement comando = conexion.createStatement();
			
		
			ResultSet registro = comando.executeQuery(sql);
			registro = comando.executeQuery(sql);
			while(registro.next()) {
				fila[0] = registro.getString("id_usuario");
				fila[1] = registro.getString("nombre_usuario");
				if(fila[1]==null) {
					fila[1]="";
				}
				
				fila[2] = registro.getString("pass_usuario");
				if(fila[2]==null) {
					fila[2]="";
				}
				fila[3] = registro.getString("rol_usuario");
				if(fila[3]==null) {
					fila[3]="";
				}
				
				modelo.addRow(fila);
				
			}
			
			
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}		
	}
	
	protected static void cargarDriver() {
		try {
			Class.forName("com.msqly.jdbc.Driver");
		} catch (Exception ex) {
			// setTitle(ex.toString());
		}
	}
}
