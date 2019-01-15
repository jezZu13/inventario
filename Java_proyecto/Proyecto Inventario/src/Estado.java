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
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;



public class Estado extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel modelo;
	private Connection conexion;
	private JTable table;
	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private String id_estado[];
	private JLabel lblnfila;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Estado frame = new Estado();
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
	public Estado() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("Assets\\logo_ventana.png"));
		setTitle("Estado");
		
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
		modelo.addColumn("Descripcion");
		

		
		rellenarTabla();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 611, 525);
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
				int i=0, sw=1;
				
				
				
				//Con este bucle hemos programado que, independientemente de la columna/celda que selecciones, los datos se carguen correctamente en los text fields correspondientes.
				if((fila>-1)&&(fila<table.getRowCount())) {
					while((i<table.getColumnCount())&&(sw==1)) {
						
						if(i==columna) {
						
						lblnfila.setText(table.getValueAt(fila, columna+(0-i) ).toString());
						txtNombre.setText(table.getValueAt(fila, columna+(1-i) ).toString());
						txtDescripcion.setText(table.getValueAt(fila, columna+(2-i)).toString());
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
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setFont(new Font("Arial", Font.BOLD, 13));
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBorder(null);
		txtDescripcion.setFont(new Font("Arial", Font.PLAIN, 13));
		txtDescripcion.setColumns(10);
		
		lblnfila = new JLabel("");
		lblnfila.setVisible(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.ORANGE);
		separator.setBackground(Color.ORANGE);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.ORANGE);
		separator_1.setBackground(Color.ORANGE);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
					.addGap(2))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addComponent(lblnfila, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(97)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblDescripcion, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(separator, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
								.addComponent(txtDescripcion, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
								.addComponent(txtNombre, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))))
					.addGap(93))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(lblnfila, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addGap(24)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNombre)
							.addGap(36))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
							.addGap(19)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblDescripcion)
							.addGap(79))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
							.addGap(62)))
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
					.addGap(28))
		);
		
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
					.addComponent(btnLimpiar, GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
					.addGap(75))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnLimpiar, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(btnEliminar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnModificar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnInsertar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)))
					.addContainerGap(29, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNombre.setText("");
				txtDescripcion.setText("");
			}
		});
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					String nfila=lblnfila.getText();//Obtenemos el nº de fila del elemento a modificar,
					int indice = Integer.parseInt(nfila);//y luego lo parseamos para poder usarlo como integer.
					
					
					//Antes de eliminar el registro, debemos comprobar si existen dependencias que impedirán su eliminación:
					int contador=0;
					String sql="SELECT * FROM `estado_dispositivo` WHERE `id_estado`= '"+id_estado[indice-1]+"'";
					Statement comando = conexion.createStatement();
					ResultSet registro = comando.executeQuery(sql);
					while(registro.next()) {
						contador++;
					}
					
					//Los estados "Descatalogado" y "Funcionando" no se deben poder eliminar
					String nombre_estado=null;
					sql="SELECT * FROM estado WHERE `id_estado`= '"+id_estado[indice-1]+"';";
					comando = conexion.createStatement();
					registro = comando.executeQuery(sql);
					while(registro.next()) {
						nombre_estado = registro.getString("nombre_estado");
					}
					
					
					if(contador==0) {
						if( (nombre_estado.compareToIgnoreCase("En stock")!=0)&&(nombre_estado.compareToIgnoreCase("Descatalogado")!=0) ){
							sql = "DELETE from estado WHERE `id_estado`= '"+id_estado[indice-1]+"'";
						
							comando = conexion.createStatement();
							comando.executeUpdate(sql);
							JOptionPane.showMessageDialog(null, "Estado eliminado correctamente");
						}
						else {
							JOptionPane.showMessageDialog(null,"No se puede eliminar dicho estado puesto que es usado por el sistema","Error",JOptionPane.ERROR_MESSAGE);
						}
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
					String desc = txtDescripcion.getText();
					
					
					//Los estados "Descatalogado" y "Funcionando" no se deben poder eliminar
					String nombre_estado_actual=null;
					String sql="SELECT * FROM estado WHERE `id_estado`= '"+id_estado[indice-1]+"';";
					Statement comando = conexion.createStatement();
					ResultSet registro = comando.executeQuery(sql);
					while(registro.next()) {
						nombre_estado_actual = registro.getString("nombre_estado");
					}
					
					
					
					if( (nombre_estado_actual.compareToIgnoreCase("En stock")!=0)&&(nombre_estado_actual.compareToIgnoreCase("Descatalogado")!=0) ){
						sql = "UPDATE `estado` SET `nombre_estado`='"+nombre+"',`descripcion_estado`='"+desc+"' WHERE `id_estado`= '"+id_estado[indice-1]+"'";
						comando = conexion.createStatement();
						cargarDriver();
						comando.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "Estado modificado correctamente");
						limpiar();
					}
					else {
						JOptionPane.showMessageDialog(null,"No se puede modificar dicho estado puesto que es usado por el sistema","Error",JOptionPane.ERROR_MESSAGE);
					}
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
					String sql = "INSERT INTO `estado`(`nombre_estado`, `descripcion_estado`) VALUES ('"+nombre+"', '"+desc+"')";
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
		txtDescripcion.setText("");
		//**FIN limpiar
		
	}
	
	public void rellenarTabla() {
		try {
			
			int indice=0;
			Object[] fila=new Object[3]; //Creamos un objeto con tantos parametros como datos retorne cada fila.
			
			String sql = "select * from estado;";
			Statement comando = conexion.createStatement();
			
			//Para conocer el numero de registros:
			ResultSet registro = comando.executeQuery(sql);
			int nregistros=0;
			while(registro.next()) {
				nregistros++;
			}
			
			
			id_estado=new String[nregistros];//Creamos un array del tamaño de nº de registros que tengamos
			
			registro = comando.executeQuery(sql);
			while(registro.next()) {
				fila[0] = indice+1;
				
				fila[1] = registro.getString("nombre_estado");
				if(fila[1]==null) {
					fila[1]="";
				}
				
				fila[2] = registro.getString("descripcion_estado");
				if(fila[2]==null) {
					fila[2]="";
				}
				
				id_estado[indice]=registro.getString("id_estado");
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
