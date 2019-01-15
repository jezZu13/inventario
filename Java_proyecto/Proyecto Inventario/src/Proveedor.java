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

import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JSeparator;



public class Proveedor extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel modelo;
	private Connection conexion;
	private JTable table;
	private JTextField txtNombre;
	private JTextField txtPrsContacto;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JTextField txtEmail;
	private JTextField textDescripcion;
	private String id_prov[];
	private JLabel lblnfila;




	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Proveedor frame = new Proveedor();
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
	public Proveedor() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("Assets\\logo_ventana.png"));
		setTitle("Proveedor");
		
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
		modelo.addColumn("Nombre");
		modelo.addColumn("Persona de contacto");
		modelo.addColumn("Telefono");
		modelo.addColumn("Direccion");
		modelo.addColumn("Email");
		modelo.addColumn("Descripcion");
		

		
		rellenarTabla();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 611, 678);
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
		table.setGridColor(Color.black);
		table.setEnabled(false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = table.rowAtPoint(e.getPoint());
				int columna = table.columnAtPoint(e.getPoint());
				int i=0, sw=1;
				
				/*Debug
				System.out.println("La fila es:"+fila);
				System.out.println("La columna es:"+columna);
				*/
				
				//Con este bucle hemos programado que, independientemente de la columna/celda que selecciones, los datos se carguen correctamente en los text fields correspondientes.
				if((fila>-1)&&(fila<table.getRowCount())) {
					while((i<table.getColumnCount())&&(sw==1)) {
						
						if(i==columna) {
						
						lblnfila.setText(table.getValueAt(fila, columna+(0-i) ).toString());
						txtNombre.setText(table.getValueAt(fila, columna+(1-i) ).toString());
						txtPrsContacto.setText(table.getValueAt(fila, columna+(2-i)).toString());
						txtTelefono.setText(table.getValueAt(fila, columna+(3-i)).toString());
						txtDireccion.setText(table.getValueAt(fila, columna+(4-i)).toString());
						txtEmail.setText(table.getValueAt(fila, columna+(5-i)).toString());
						textDescripcion.setText(table.getValueAt(fila, columna+(6-i)).toString());
						sw=0;
						}
						i++;
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
		
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Arial", Font.BOLD, 13));
		
		txtNombre = new JTextField();
		txtNombre.setBorder(null);
		txtNombre.setFont(new Font("Arial", Font.PLAIN, 13));
		txtNombre.setColumns(10);
		
		JLabel lblperscontact = new JLabel("Persona de contacto");
		lblperscontact.setFont(new Font("Arial", Font.BOLD, 13));
		
		txtPrsContacto = new JTextField();
		txtPrsContacto.setBorder(null);
		txtPrsContacto.setFont(new Font("Arial", Font.PLAIN, 13));
		txtPrsContacto.setColumns(10);
		
		lblnfila = new JLabel("");
		lblnfila.setVisible(false);
		
		txtTelefono = new JTextField();
		txtTelefono.setBorder(null);
		txtTelefono.setFont(new Font("Arial", Font.PLAIN, 13));
		txtTelefono.setColumns(10);
		
		JLabel lbltelefono = new JLabel("Tel\u00E9fono");
		lbltelefono.setFont(new Font("Arial", Font.BOLD, 13));
		
		txtDireccion = new JTextField();
		txtDireccion.setBorder(null);
		txtDireccion.setFont(new Font("Arial", Font.PLAIN, 13));
		txtDireccion.setColumns(10);
		
		JLabel lbldireccion = new JLabel("Direcci\u00F3n");
		lbldireccion.setFont(new Font("Arial", Font.BOLD, 13));
		
		txtEmail = new JTextField();
		txtEmail.setBorder(null);
		txtEmail.setFont(new Font("Arial", Font.PLAIN, 13));
		txtEmail.setColumns(10);
		
		JLabel lblemail = new JLabel("Email");
		lblemail.setFont(new Font("Arial", Font.BOLD, 13));
				
		JLabel lbldescripcion = new JLabel("Descripci\u00F3n");
		lbldescripcion.setFont(new Font("Arial", Font.BOLD, 13));
		
		textDescripcion = new JTextField();
		textDescripcion.setBorder(null);
		textDescripcion.setFont(new Font("Arial", Font.PLAIN, 13));
		textDescripcion.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.ORANGE);
		separator.setForeground(Color.ORANGE);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(Color.ORANGE);
		separator_1.setForeground(Color.ORANGE);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBackground(Color.ORANGE);
		separator_2.setForeground(Color.ORANGE);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBackground(Color.ORANGE);
		separator_3.setForeground(Color.ORANGE);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBackground(Color.ORANGE);
		separator_4.setForeground(Color.ORANGE);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBackground(Color.ORANGE);
		separator_5.setForeground(Color.ORANGE);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
					.addGap(2))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addComponent(lblnfila, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(459))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(49)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblperscontact, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
						.addComponent(lblNombre, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lbltelefono, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lbldireccion, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblemail, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lbldescripcion, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(separator_5, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
						.addComponent(separator_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
						.addComponent(separator_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
						.addComponent(separator_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
						.addComponent(separator_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
						.addComponent(separator, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
						.addComponent(txtNombre, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
						.addComponent(txtPrsContacto, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
						.addComponent(txtTelefono, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
						.addComponent(txtEmail, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
						.addComponent(txtDireccion, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
						.addComponent(textDescripcion, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE))
					.addGap(93))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblnfila, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
							.addGap(24)
							.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNombre))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_5, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGap(16)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblperscontact)
						.addComponent(txtPrsContacto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_4, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGap(16)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbltelefono)
						.addComponent(txtTelefono, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_3, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGap(16)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbldireccion)
						.addComponent(txtDireccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGap(16)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblemail)
						.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGap(16)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbldescripcion)
						.addComponent(textDescripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGap(25)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addGap(173))
		);
		
		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.setBorder(null);
		btnInsertar.setBackground(Color.ORANGE);
		btnInsertar.setForeground(Color.BLACK);
		btnInsertar.setFont(new Font("Arial", Font.BOLD, 14));
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBorder(null);
		btnModificar.setBackground(Color.ORANGE);
		btnModificar.setForeground(Color.BLACK);
		btnModificar.setFont(new Font("Arial", Font.BOLD, 14));
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBorder(null);
		btnEliminar.setBackground(Color.ORANGE);
		btnEliminar.setForeground(Color.BLACK);
		btnEliminar.setFont(new Font("Arial", Font.BOLD, 14));
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBorder(null);
		btnLimpiar.setBackground(Color.ORANGE);
		btnLimpiar.setForeground(Color.BLACK);
		btnLimpiar.setFont(new Font("Arial", Font.BOLD, 14));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(96)
					.addComponent(btnInsertar, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
					.addGap(5)
					.addComponent(btnModificar, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
					.addGap(5)
					.addComponent(btnEliminar, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
					.addGap(5)
					.addComponent(btnLimpiar, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
					.addGap(67))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnLimpiar, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(btnInsertar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
							.addComponent(btnModificar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnEliminar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap(24, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNombre.setText("");
				txtPrsContacto.setText("");
				txtTelefono.setText("");
				txtDireccion.setText("");
				txtEmail.setText("");
				textDescripcion.setText("");
			}
		});
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					String nfila=lblnfila.getText();//Obtenemos el nº de fila del elemento a modificar,
					int indice = Integer.parseInt(nfila);//y luego lo parseamos para poder usarlo como integer.
					
					
					//Antes de eliminar el registro, debemos comprobar si existen dependencias que impedirán su eliminación:
					int contador=0;
					String sql="SELECT * FROM `pedido` WHERE `id_prov`= '"+id_prov[indice-1]+"'";
					Statement comando = conexion.createStatement();
					ResultSet registro = comando.executeQuery(sql);
					while(registro.next()) {
						contador++;
					}
					
					if(contador==0) {
						sql = "DELETE from proveedor WHERE `id_prov`= '"+id_prov[indice-1]+"'";
					
						comando = conexion.createStatement();
						//cargarDriver();
						comando.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "Estado eliminado correctamente");
					}
					else {
						JOptionPane.showMessageDialog(null,"No se puede eliminar el registro seleccionado puesto que existen dependencias","Error",JOptionPane.ERROR_MESSAGE);
					}
					
					limpiar();

				} catch (Exception e) {
					System.out.println("Error");
					e.printStackTrace();
				}

			}
		});
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String nfila=lblnfila.getText();//Obtenemos el nº de fila del elemento a modificar,
				int indice = Integer.parseInt(nfila);//y luego lo parseamos para poder usarlo como integer.
				
				try {
					String nombre = txtNombre.getText();
					String perscont = txtPrsContacto.getText();
					String telefono = txtTelefono.getText();
					String direccion = txtDireccion.getText();
					String email = txtEmail.getText();
					String descripcion = textDescripcion.getText();
					
					String sql = "UPDATE `proveedor` SET `nombre_prov`='"+nombre+"',`persona_contact_prov`='"+perscont+"',`telefono_prov`='"+telefono+"',`direccion_prov`='"+direccion+"',`email_prov`='"+email+"',`descripcion_prov`='"+descripcion+"' WHERE `id_prov`= '"+id_prov[indice-1]+"'";
					Statement comando = conexion.createStatement();
					cargarDriver();
					comando.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Estado modificado correctamente");
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
					String perscont = txtPrsContacto.getText();
					String telefono = txtTelefono.getText();
					String direccion = txtDireccion.getText();
					String email = txtEmail.getText();
					String descripcion = textDescripcion.getText();
					
					
					
					String sql = "INSERT INTO `proveedor`(`nombre_prov`, `persona_contact_prov`, `telefono_prov`, `direccion_prov`, `email_prov`, `descripcion_prov`) VALUES "
							+ "('"+nombre+"', '"+perscont+"','"+perscont+"','"+perscont+"','"+perscont+"','"+perscont+"')";
					Statement comando = conexion.createStatement();
					cargarDriver();
					comando.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Estado agregado correctamente");
					limpiar();
					
				} catch (Exception e) {
					System.out.println("Error");
					e.printStackTrace();
				}
				
			}
		});
		contentPane.setLayout(gl_contentPane);
		
		

	}
	
	public void limpiar() {
		//**COMIENZO limpiar
		int j=0;
		int limite=modelo.getRowCount();
		
		while(j<limite) {
			modelo.removeRow(0);
			j++;
		}
		
		rellenarTabla();
		txtNombre.setText("");
		txtPrsContacto.setText("");
		txtTelefono.setText("");
		txtDireccion.setText("");
		txtEmail.setText("");
		textDescripcion.setText("");
		//**FIN limpiar
		
	}
	
	public void rellenarTabla() {
		try {
			
			int indice=0;
			Object[] fila=new Object[7]; //Creamos un objeto con tantos parametros como datos retorne cada fila.
			
			String sql = "select * from proveedor;";
			Statement comando = conexion.createStatement();
			
			//Para conocer el numero de registros:
			ResultSet registro = comando.executeQuery(sql);
			int nregistros=0;
			while(registro.next()) {
				nregistros++;
			}
			
			
			id_prov=new String[nregistros];//Creamos un array del tamaño de nº de registros que tengamos
			
			registro = comando.executeQuery(sql);
			while(registro.next()) {
				fila[0] = indice+1;
				
				fila[1] = registro.getString("nombre_prov");
				if(fila[1]==null) {
					fila[1]="";
				}
				
				fila[2] = registro.getString("persona_contact_prov");
				if(fila[2]==null) {
					fila[2]="";
				}
				
				fila[3] = registro.getString("telefono_prov");
				if(fila[3]==null) {
					fila[3]="";
				}
				
				fila[4] = registro.getString("direccion_prov");
				if(fila[4]==null) {
					fila[4]="";
				}
				
				fila[5] = registro.getString("email_prov");
				if(fila[5]==null) {
					fila[5]="";
				}
				
				fila[6] = registro.getString("descripcion_prov");
				if(fila[6]==null) {
					fila[6]="";
				}
				
				id_prov[indice]=registro.getString("id_prov");
				modelo.addRow(fila);
				indice++;
			}
			
			
			
			
		} catch (Exception e) {
			
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
