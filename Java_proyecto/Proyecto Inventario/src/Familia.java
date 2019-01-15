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
import java.awt.Font;
import javax.swing.JSeparator;



public class Familia extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel modelo;
	private Connection conexion;
	private JTable table;
	private JTextField txtDescripcion;
	private JTextField txtNombre;
	private String idSelected;
	private JTextField txtid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Familia frame = new Familia();
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
	public Familia() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("Assets\\logo_ventana.png"));
		setTitle("Familia");
		
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
		modelo.addColumn("Nombre Familia");
		modelo.addColumn("Descripción");

		
		rellenarTabla();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 611, 744);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setBackground(Color.white);
		
		table = new JTable();
		table.setEnabled(false);
		table.setFont(new Font("Arial", Font.PLAIN, 13));
		JTableHeader header = table.getTableHeader();
	    header.setBackground(Color.ORANGE);
	    header.setFont(new Font("Roboto", Font.BOLD, 13));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = table.rowAtPoint(e.getPoint());
				int columna = table.columnAtPoint(e.getPoint());
				
				if (columna==1) {
					txtid.setText((String) table.getValueAt(fila, columna-1));
					txtNombre.setText(table.getValueAt(fila, columna).toString());
					txtDescripcion.setText(table.getValueAt(fila, columna+1).toString());
					
				} else if(columna==2){
					txtid.setText((String) table.getValueAt(fila, columna-2));
					txtNombre.setText(table.getValueAt(fila, columna-1).toString());
					txtDescripcion.setText(table.getValueAt(fila, columna).toString());
										
				}else{
					txtNombre.setText(table.getValueAt(fila, columna+1).toString());
					txtDescripcion.setText(table.getValueAt(fila, columna+2).toString());
					txtid.setText((String) table.getValueAt(fila, columna));
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
		
		JLabel lblNombreFamilia = new JLabel("Nombre Familia");
		lblNombreFamilia.setFont(new Font("Arial", Font.BOLD, 13));
		
		txtNombre = new JTextField();
		txtNombre.setBorder(null);
		txtNombre.setFont(new Font("Arial", Font.PLAIN, 13));
		txtNombre.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripci\u00F3n");
		lblDescripcion.setFont(new Font("Arial", Font.BOLD, 13));
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBorder(null);
		txtDescripcion.setFont(new Font("Arial", Font.PLAIN, 13));
		txtDescripcion.setColumns(10);
		
		txtid = new JTextField();
		txtid.setEnabled(false);
		txtid.setColumns(10);
		txtid.setVisible(false);
		
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
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNombre.setText("");
				txtDescripcion.setText("");
				txtid.setText("");
			}
		});
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					idSelected= txtid.getText().toString();
					String sql = "DELETE from `familia_producto` WHERE `id_familia_prod`= '"+idSelected+"'";
					Statement comando = conexion.createStatement();
					cargarDriver();
					comando.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Familia eliminada correctamente");
					ñapa();

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
					String desc = txtDescripcion.getText();
					idSelected=txtid.getText().toString();
					
					
					String sql = "UPDATE `familia_producto` SET `nombre_familia_prod`='"+nombre+"',`descripcion_familia_prod`='"+desc+"' WHERE `id_familia_prod`= '"+idSelected+"'";
					Statement comando = conexion.createStatement();
					cargarDriver();
					comando.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Familia modificada correctamente");
					ñapa();
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
					String desc = txtDescripcion.getText();
					
					String sql = "INSERT INTO `familia_producto`(`nombre_familia_prod`, `descripcion_familia_prod`) VALUES ('"+nombre+"', '"+desc+"')";
					
					Statement comando = conexion.createStatement();
					cargarDriver();
					comando.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Familia agregada correctamente");
					ñapa();
					
				} catch (Exception e) {
					System.out.println(e.getMessage().toString());
					e.printStackTrace();
				}
				
			}
		});
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.ORANGE);
		separator.setBackground(Color.ORANGE);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.ORANGE);
		separator_1.setBackground(Color.ORANGE);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(txtid, GroupLayout.PREFERRED_SIZE, 6, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(38)
									.addComponent(lblNombreFamilia))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(39)
									.addComponent(lblDescripcion)))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(txtNombre, GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
								.addComponent(txtDescripcion, GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
								.addComponent(separator, GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
								.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE))))
					.addGap(108))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(txtid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(68)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNombreFamilia)
								.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGap(23)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblDescripcion)
						.addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGap(42)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addGap(243))
		);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(96)
					.addComponent(btnInsertar, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
					.addGap(5)
					.addComponent(btnModificar, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
					.addGap(5)
					.addComponent(btnEliminar, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
					.addGap(5)
					.addComponent(btnLimpiar, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
					.addGap(66))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(btnLimpiar, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(btnInsertar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
							.addComponent(btnModificar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnEliminar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		contentPane.setLayout(gl_contentPane);
		

	}
	
	public void ñapa() {
		//**COMIENZO ÑAPA
		int j=0;
		int limite=modelo.getRowCount();
		
		while(j<limite) {
			modelo.removeRow(0);
			j++;
		}
		
		rellenarTabla();
		txtNombre.setText("");
		txtDescripcion.setText("");
		//**FIN ÑAPA
		
	}
	

	public void rellenarTabla() {
		try {
			
			Object[] fila=new Object[4]; //Creamos un objeto con tantos parametros como datos retorne cada fila.
			
			String sql = "select * from `familia_producto`;";
			Statement comando = conexion.createStatement();
			
		
			ResultSet registro = comando.executeQuery(sql);
			registro = comando.executeQuery(sql);
			while(registro.next()) {
				fila[0] = registro.getString("id_familia_prod");
				fila[1] = registro.getString("nombre_familia_prod");
				if(fila[1]==null) {
					fila[1]="";
				}
				
				fila[2] = registro.getString("descripcion_familia_prod");
				if(fila[2]==null) {
					fila[2]="";
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
