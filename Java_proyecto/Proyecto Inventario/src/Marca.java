import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.border.LineBorder;
import java.awt.Frame;
import java.awt.Window.Type;
import java.awt.Toolkit;



public class Marca extends JFrame {

	private JPanel lblnombre;
	private DefaultTableModel modelo;
	private Connection conexion;
	private JTable table;
	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private String id_marca[];
	private JLabel lblnfila;
	Color gris = new Color(97,97,97);
	Color naranja = new Color(245,124,0);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Marca frame = new Marca();
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
	public Marca() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("Assets\\logo_ventana.png"));
		setTitle("Marca");
		
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
		modelo.addColumn("Nombre Marca");
		modelo.addColumn("Descripcion");
		
		

		
		rellenarTabla();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 611, 529);
		lblnombre = new JPanel();
		lblnombre.setForeground(Color.WHITE);
		lblnombre.setBackground(Color.WHITE);
		lblnombre.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(lblnombre);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setBackground(Color.white);
		
		table = new JTable();
		table.setBorder(null);
		JTableHeader header = table.getTableHeader();
	    header.setBackground(Color.ORANGE);
	    header.setFont(new Font("Roboto", Font.BOLD, 13));
		table.setGridColor(Color.GRAY);
		table.setForeground(Color.BLACK);
		table.setFont(new Font("Arial", Font.PLAIN, 12));
		table.setBackground(Color.white);
		table.setGridColor(Color.black);
		table.setEnabled(false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = table.rowAtPoint(e.getPoint());
				int columna = table.columnAtPoint(e.getPoint());
				
				if((fila>-1) && (columna==0)) {
					
					lblnfila.setText(table.getValueAt(fila, columna+0).toString());
					txtNombre.setText(table.getValueAt(fila, columna+1).toString());
					txtDescripcion.setText(table.getValueAt(fila, columna+2).toString());
				}
				
				if((fila>-1)&&(columna==1)) {
						lblnfila.setText(table.getValueAt(fila, columna-1).toString());
						txtNombre.setText(table.getValueAt(fila, columna+0).toString());
						txtDescripcion.setText(table.getValueAt(fila, columna+1).toString());
				}
				
				if((fila>-1)&&(columna==2)) {
					lblnfila.setText(table.getValueAt(fila, columna-2).toString());
					txtNombre.setText(table.getValueAt(fila, columna-1).toString());
					txtDescripcion.setText(table.getValueAt(fila, columna+0).toString());
				}
				
			}
		});
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
		lblNombre.setBackground(Color.BLACK);
		lblNombre.setForeground(Color.BLACK);
		lblNombre.setFont(new Font("Roboto", Font.BOLD, 13));
		
		txtNombre = new JTextField();
		txtNombre.setBorder(null);
		txtNombre.setFont(new Font("Arial", Font.PLAIN, 13));
		txtNombre.setForeground(Color.black);
		txtNombre.setBackground(Color.WHITE);
		txtNombre.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBackground(Color.BLACK);
		lblDescripcion.setForeground(Color.BLACK);
		lblDescripcion.setFont(new Font("Roboto Light", Font.BOLD, 13));
		
		txtDescripcion = new JTextField();
		txtDescripcion.setForeground(Color.black);
		txtDescripcion.setBorder(null);
		txtDescripcion.setFont(new Font("Arial", Font.PLAIN, 13));
		txtDescripcion.setBackground(Color.WHITE);
		txtDescripcion.setColumns(10);
		
		lblnfila = new JLabel("");
		
		JPanel panel = new JPanel();
		panel.setFont(new Font("Arial", Font.PLAIN, 22));
		panel.setBackground(Color.WHITE);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(naranja);
		separator.setBackground(naranja);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(naranja);
		separator_1.setBackground(naranja);
		GroupLayout gl_lblnombre = new GroupLayout(lblnombre);
		gl_lblnombre.setHorizontalGroup(
			gl_lblnombre.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_lblnombre.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
					.addGap(2))
				.addGroup(gl_lblnombre.createSequentialGroup()
					.addGap(10)
					.addComponent(lblnfila, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_lblnombre.createSequentialGroup()
					.addGap(54)
					.addGroup(gl_lblnombre.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblNombre, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblDescripcion, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_lblnombre.createParallelGroup(Alignment.TRAILING)
						.addComponent(separator_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
						.addComponent(txtNombre, GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
						.addComponent(separator, GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
						.addComponent(txtDescripcion, Alignment.LEADING, 309, 317, Short.MAX_VALUE))
					.addGap(116))
				.addGroup(Alignment.LEADING, gl_lblnombre.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_lblnombre.setVerticalGroup(
			gl_lblnombre.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_lblnombre.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(lblnfila, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addGap(43)
					.addGroup(gl_lblnombre.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGap(9)
					.addGroup(gl_lblnombre.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDescripcion, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(54)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.setBorder(null);
		btnInsertar.setPreferredSize(new Dimension(39, 9));
		btnInsertar.setBackground(Color.ORANGE);
		btnInsertar.setFont(new Font("Arial", Font.BOLD, 14));
		btnInsertar.setForeground(Color.BLACK);
		btnInsertar.setIcon(null);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBorder(null);
		btnModificar.setIcon(null);
		btnModificar.setForeground(Color.BLACK);
		btnModificar.setPreferredSize(new Dimension(39, 9));
		btnModificar.setBackground(Color.ORANGE);
		btnModificar.setFont(new Font("Arial", Font.BOLD, 14));
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(Color.BLACK);
		btnEliminar.setBackground(Color.ORANGE);
		btnEliminar.setBorder(null);
		btnEliminar.setMaximumSize(new Dimension(73, 23));
		btnEliminar.setPreferredSize(new Dimension(39, 9));
		btnEliminar.setIcon(null);
		btnEliminar.setFont(new Font("Arial", Font.BOLD, 14));
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setForeground(Color.BLACK);
		btnLimpiar.setBackground(Color.ORANGE);
		btnLimpiar.setBorder(null);
		btnLimpiar.setPreferredSize(new Dimension(39, 9));
		btnLimpiar.setIcon(null);
		btnLimpiar.setFont(new Font("Arial", Font.BOLD, 14));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(72)
					.addComponent(btnInsertar, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnModificar, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnEliminar, GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnLimpiar, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
					.addGap(78))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(14)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnEliminar, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
							.addComponent(btnLimpiar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnInsertar, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
							.addComponent(btnModificar, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)))
					.addContainerGap(24, Short.MAX_VALUE))
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
					String sql="SELECT * FROM `producto` WHERE `id_marca`= '"+id_marca[indice-1]+"'";
					Statement comando = conexion.createStatement();
					ResultSet registro = comando.executeQuery(sql);
					while(registro.next()) {
						contador++;
					}
					
					if(contador==0) {
						sql = "DELETE from marca WHERE `id_marca`= '"+id_marca[indice-1]+"'";
					
						comando = conexion.createStatement();
						//cargarDriver();
						comando.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "Marca eliminada correctamente");
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
					String sql = "UPDATE `marca` SET `nombre_marca`='"+nombre+"',`descripcion_marca`='"+desc+"' WHERE `id_marca`= '"+id_marca[indice-1]+"'";
					Statement comando = conexion.createStatement();
					cargarDriver();
					comando.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Marca modificada correctamente");
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
					String desc = txtDescripcion.getText();
					String sql = "INSERT INTO `marca`(`nombre_marca`, `descripcion_marca`) VALUES ('"+nombre+"', '"+desc+"')";
					Statement comando = conexion.createStatement();
					cargarDriver();
					comando.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Marca agregada correctamente");
					limpiar();
					
				} catch (Exception e) {
					System.out.println("Error");
					e.printStackTrace();
				}
				
			}
		});
		lblnombre.setLayout(gl_lblnombre);
		lblnfila.setVisible(false);
		
		

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
			
			String sql = "select * from marca;";
			Statement comando = conexion.createStatement();
			
			//Para conocer el numero de registros:
			ResultSet registro = comando.executeQuery(sql);
			int nregistros=0;
			while(registro.next()) {
				nregistros++;
			}
			
			
			id_marca=new String[nregistros];//Creamos un array del tamaño de nº de registros que tengamos
			
			registro = comando.executeQuery(sql);
			while(registro.next()) {
				fila[0] = indice+1;
				fila[1] = registro.getString("nombre_marca");
				fila[2] = registro.getString("descripcion_marca");
				
				id_marca[indice]=registro.getString("id_marca");
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
