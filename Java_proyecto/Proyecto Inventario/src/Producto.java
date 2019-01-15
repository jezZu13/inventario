import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSeparator;



public class Producto extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel modelo;
	private Connection conexion;
	private JTable table;
	private JTextField txtNombre;
	private JTextField txtCaracteristicas;
	private String id_prod[];
	private String id_marca[];
	private String id_familia_prod[];
	private JLabel lblnfila;
	private JComboBox comboBoxMarca;
	private JComboBox comboBoxTipo;




	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Producto frame = new Producto();
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
	public Producto() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("Assets\\logo_ventana.png"));
		setTitle("Producto");
		
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
		modelo.addColumn("Marca");
		modelo.addColumn("Tipo");
		modelo.addColumn("Nombre");
		modelo.addColumn("Características");
		
	
		
		rellenarTabla();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 641, 592);
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
								
				//Con este bucle hemos programado que, independientemente de la columna/celda que selecciones, los datos se carguen correctamente en los text fields correspondientes.
				if((fila>=-1)&&(fila<table.getRowCount())) {
					while((i<table.getColumnCount())&&(sw==1)) {
						
						if(i==columna) {
						
						lblnfila.setText(table.getValueAt(fila, columna+(0-i) ).toString());
						
						for(int j=0, exit=1;(j<comboBoxMarca.getItemCount())&&(exit==1);j++) {
							String marcaSeleccionada=comboBoxMarca.getItemAt(j).toString();
							if(marcaSeleccionada.compareToIgnoreCase(table.getValueAt(fila, columna+(1-i) ).toString())==0) {
								comboBoxMarca.setSelectedIndex(j);
								exit=0;
							}
						}
						
						
						for(int j=0, exit=1;(j<comboBoxTipo.getItemCount())&&(exit==1);j++) {
							String tipoSeleccionado=comboBoxTipo.getItemAt(j).toString();
							if(tipoSeleccionado.compareToIgnoreCase(table.getValueAt(fila, columna+(2-i) ).toString())==0) {
								comboBoxTipo.setSelectedIndex(j);
								exit=0;
							}
						}
						
						txtNombre.setText(table.getValueAt(fila, columna+(3-i)).toString());
						txtCaracteristicas.setText(table.getValueAt(fila, columna+(4-i)).toString());

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
		
		
		JLabel lblmarca = new JLabel("Marca");
		lblmarca.setFont(new Font("Arial", Font.BOLD, 13));
		
		JLabel lbltipo = new JLabel("Tipo");
		lbltipo.setFont(new Font("Arial", Font.BOLD, 13));
		
		lblnfila = new JLabel("");
		lblnfila.setVisible(false);
		
		txtNombre = new JTextField();
		txtNombre.setBorder(null);
		txtNombre.setFont(new Font("Arial", Font.PLAIN, 13));
		txtNombre.setColumns(10);
		
		JLabel lblnombre = new JLabel("Nombre");
		lblnombre.setFont(new Font("Arial", Font.BOLD, 13));
		
		txtCaracteristicas = new JTextField();
		txtCaracteristicas.setBorder(null);
		txtCaracteristicas.setFont(new Font("Arial", Font.PLAIN, 13));
		txtCaracteristicas.setColumns(10);
		
		JLabel lblcaracteristicas = new JLabel("Caracteristicas");
		lblcaracteristicas.setFont(new Font("Arial", Font.BOLD, 13));
		
		comboBoxMarca = new JComboBox();
		comboBoxMarca.setFont(new Font("Arial", Font.PLAIN, 13));
		//Rellenar combo box MARCA:
		String sql_cobobox="select * from marca;";
		Statement comando2;
		try {
			comando2=conexion.createStatement();
			ResultSet registro=comando2.executeQuery(sql_cobobox);
			//recorremos la columnas de la tabla y recuperamos los valores.
			while(registro.next()) {
				comboBoxMarca.addItem(registro.getString("nombre_marca"));
			}
		}
		catch(Exception e) {
			System.out.println("Error");
		}
		comboBoxMarca.setSelectedIndex(-1);
		
		comboBoxTipo = new JComboBox();
		comboBoxTipo.setFont(new Font("Arial", Font.PLAIN, 13));
		//Rellenar combo box TIPO:
		String sql_coboboxTipo="select * from familia_producto;";
		Statement comando3;
		try {
			comando3=conexion.createStatement();
			ResultSet registro2=comando3.executeQuery(sql_coboboxTipo);
			//recorremos la columnas de la tabla y recuperamos los valores.
			while(registro2.next()) {
				comboBoxTipo.addItem(registro2.getString("nombre_familia_prod"));
			}
		}
		catch(Exception e) {
			System.out.println("Error");
		}
		comboBoxTipo.setSelectedIndex(-1);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.ORANGE);
		separator.setBackground(Color.ORANGE);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(Color.ORANGE);
		separator_1.setForeground(Color.ORANGE);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
					.addGap(2))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addComponent(lblnfila, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(588, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(38)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(66)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblmarca, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lbltipo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblnombre, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblcaracteristicas, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(separator_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
						.addComponent(txtNombre, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
						.addComponent(txtCaracteristicas, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
						.addComponent(separator, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
						.addComponent(comboBoxTipo, 0, 338, Short.MAX_VALUE)
						.addComponent(comboBoxMarca, Alignment.LEADING, 0, 338, Short.MAX_VALUE))
					.addGap(76))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(lblnfila, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxMarca, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblmarca))
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxTipo, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbltipo))
					.addGap(9)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblnombre))
					.addGap(3)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtCaracteristicas, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblcaracteristicas))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(46, Short.MAX_VALUE))
		);
		
		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.setBorder(null);
		btnInsertar.setBackground(Color.ORANGE);
		btnInsertar.setFont(new Font("Arial", Font.BOLD, 14));
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String nombreprod;
					String caracteristicas;
					String marca;
					String tipo;
					String id_marca=null;
					String id_tipo=null;
					
					nombreprod = txtNombre.getText();
					caracteristicas = txtCaracteristicas.getText();
					marca=comboBoxMarca.getSelectedItem().toString();
					tipo=comboBoxTipo.getSelectedItem().toString();
					

					//Capturamos el ID de la marca correspondiente:
					int exit=1;
					String sql="select * from marca;";
					Statement comando = conexion.createStatement();
					ResultSet registro = comando.executeQuery(sql);
					Object[] fila=new Object[2];
					while( (registro.next()) && (exit==1) ){
						fila[0] = registro.getString("id_marca");
						fila[1] = registro.getString("nombre_marca");
						if(marca.compareToIgnoreCase(fila[1].toString())==0) {
							id_marca=fila[0].toString();
						}
					}
					
					
					//Capturamos el ID del tipo correspondiente:
					exit=1;
					sql="select * from familia_producto;";
					comando = conexion.createStatement();
					registro = comando.executeQuery(sql);
					while( (registro.next()) && (exit==1) ){
						fila[0] = registro.getString("id_familia_prod");
						fila[1] = registro.getString("nombre_familia_prod");
						if(tipo.compareToIgnoreCase(fila[1].toString())==0) {
							id_tipo=fila[0].toString();
						}
					}
					
					sql = "INSERT INTO `producto`(`nombre_prod`, `descripcion_prod`, `id_marca`, `id_familia_prod`) VALUES "
							+ "('"+nombreprod+"', '"+caracteristicas+"','"+id_marca+"','"+id_tipo+"')";
					comando = conexion.createStatement();
					cargarDriver();
					comando.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Estado agregado correctamente");
					limpiar();
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,"Para poder cursar el alta deberá rellenar los campos marca, tipo y nombre","Error",JOptionPane.ERROR_MESSAGE);
					//e.printStackTrace();
				}
				
				
			}
		});
		
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
					.addGap(97)
					.addComponent(btnInsertar, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
					.addGap(5)
					.addComponent(btnModificar, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
					.addGap(5)
					.addComponent(btnEliminar, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
					.addGap(5)
					.addComponent(btnLimpiar, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
					.addGap(68))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnLimpiar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnModificar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnEliminar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnInsertar, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNombre.setText("");
				txtCaracteristicas.setText("");
				comboBoxMarca.setSelectedIndex(-1);
				comboBoxTipo.setSelectedIndex(-1);
			}
		});
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					String nfila=lblnfila.getText();//Obtenemos el nº de fila del elemento a modificar,
					int indice = Integer.parseInt(nfila);//y luego lo parseamos para poder usarlo como integer.
					
					
					//Antes de eliminar el registro, debemos comprobar si existen dependencias que impedirán su eliminación:
					int contador1=0;
					String sql="SELECT * FROM `dispositivo` WHERE `id_producto`= '"+id_prod[indice-1]+"'";
					Statement comando = conexion.createStatement();
					ResultSet registro = comando.executeQuery(sql);
					while(registro.next()) {
						contador1++;
					}
					
					int contador2=0;
					sql="SELECT * FROM `producto_en_pedido` WHERE `id_prod`= '"+id_prod[indice-1]+"'";
					comando = conexion.createStatement();
					registro = comando.executeQuery(sql);
					while(registro.next()) {
						contador2++;
					}
					
					if((contador1==0)&&(contador2==0)) {
						sql = "DELETE from producto WHERE `id_prod`= '"+id_prod[indice-1]+"'";
					
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
					String caracteristicas = txtCaracteristicas.getText();
					String marca=comboBoxMarca.getSelectedItem().toString();
					String tipo=comboBoxTipo.getSelectedItem().toString();
					String id_marca="";
					String id_tipo="";
					
					//Capturamos el ID de la marca correspondiente:
					int exit=1;
					String sql="select * from marca;";
					Statement comando = conexion.createStatement();
					ResultSet registro = comando.executeQuery(sql);
					Object[] fila=new Object[2];
					while( (registro.next()) && (exit==1) ){
						fila[0] = registro.getString("id_marca");
						fila[1] = registro.getString("nombre_marca");
						if(marca.compareToIgnoreCase(fila[1].toString())==0) {
							id_marca=fila[0].toString();
						}
					}
					
					//Capturamos el ID del tipo correspondiente:
					exit=1;
					sql="select * from familia_producto;";
					comando = conexion.createStatement();
					registro = comando.executeQuery(sql);
					while( (registro.next()) && (exit==1) ){
						fila[0] = registro.getString("id_familia_prod");
						fila[1] = registro.getString("nombre_familia_prod");
						if(tipo.compareToIgnoreCase(fila[1].toString())==0) {
							id_tipo=fila[0].toString();
						}
					}
					
					sql = "UPDATE `producto` SET `nombre_prod`='"+nombre+"',`descripcion_prod`='"+caracteristicas+"',`id_marca`='"+id_marca+"',`id_familia_prod`='"+id_tipo+"' WHERE `id_prod`= '"+id_prod[indice-1]+"'";
					comando = conexion.createStatement();
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
		txtCaracteristicas.setText("");
		comboBoxMarca.setSelectedIndex(-1);
		comboBoxTipo.setSelectedIndex(-1);
		//**FIN limpiar
		
	}
	
	public void rellenarTabla() {
		try {
			
			int indice=0;
			Object[] fila=new Object[5]; //Creamos un objeto con tantos parametros como datos retorne cada fila.
			
			String sql = "select producto.id_prod, producto.nombre_prod, producto.descripcion_prod, producto.id_marca, producto.id_familia_prod, marca.nombre_marca, familia_producto.nombre_familia_prod from producto, marca, familia_producto where (producto.id_marca=marca.id_marca) and (producto.id_familia_prod=familia_producto.id_familia_prod);";
			Statement comando = conexion.createStatement();
			
			//Para conocer el numero de registros:
			ResultSet registro = comando.executeQuery(sql);
			int nregistros=0;
			while(registro.next()) {
				nregistros++;
			}
			
			
			id_prod=new String[nregistros];//Creamos un array del tamaño de nº de registros que tengamos
			
			registro = comando.executeQuery(sql);
			while(registro.next()) {
				fila[0] = indice+1;
				
				fila[1] = registro.getString("nombre_marca");
				if(fila[1]==null) {
					fila[1]="";
				}
				
				fila[2] = registro.getString("nombre_familia_prod");
				if(fila[2]==null) {
					fila[2]="";
				}
				
				fila[3] = registro.getString("nombre_prod");
				if(fila[3]==null) {
					fila[3]="";
				}
				
				fila[4] = registro.getString("descripcion_prod");
				if(fila[4]==null) {
					fila[4]="";
				}
			
				id_prod[indice]=registro.getString("id_prod");
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
