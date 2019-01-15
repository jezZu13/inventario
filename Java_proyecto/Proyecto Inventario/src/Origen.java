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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSeparator;



public class Origen extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel modelo;
	private Connection conexion;
	private JTable table;
	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private String id_ori_eq[];
	private JLabel lblnfila;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Origen frame = new Origen();
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
	public Origen() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("Assets\\logo_ventana.png"));
		setTitle("Origen");
		
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
		setBounds(100, 100, 611, 584);
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
				int i=0;
				int sw=1;

				if((fila>-1)&&(fila<table.getRowCount())) {
				while((i<table.getColumnCount())&&(sw==1)) {
					if(i==columna) {
						lblnfila.setText(table.getValueAt(fila, columna+(0-i)).toString());
						txtNombre.setText(table.getValueAt(fila, columna+(1-i)).toString());
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
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(56)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblNombre, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblDescripcion, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
					.addGap(16)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(txtDescripcion, 322, 322, Short.MAX_VALUE)
						.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
						.addComponent(separator, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
						.addComponent(txtNombre, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE))
					.addGap(91))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(lblnfila, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addGap(43)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNombre))
					.addGap(4)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDescripcion))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(50)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addGap(77))
		);
		
		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.setForeground(Color.BLACK);
		btnInsertar.setBorder(null);
		btnInsertar.setBackground(Color.ORANGE);
		btnInsertar.setFont(new Font("Arial", Font.BOLD, 14));
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setForeground(Color.BLACK);
		btnModificar.setBackground(Color.ORANGE);
		btnModificar.setBorder(null);
		btnModificar.setFont(new Font("Arial", Font.BOLD, 14));
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(Color.BLACK);
		btnEliminar.setBorder(null);
		btnEliminar.setBackground(Color.ORANGE);
		btnEliminar.setFont(new Font("Arial", Font.BOLD, 14));
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setForeground(Color.BLACK);
		btnLimpiar.setBackground(Color.ORANGE);
		btnLimpiar.setBorder(null);
		btnLimpiar.setFont(new Font("Arial", Font.BOLD, 14));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(83)
					.addComponent(btnInsertar, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnModificar, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEliminar, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnLimpiar, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
					.addGap(64))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnInsertar, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
						.addComponent(btnModificar, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
						.addComponent(btnEliminar, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
						.addComponent(btnLimpiar, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
					.addContainerGap())
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
					String sql="SELECT * FROM `dispositivo` WHERE `id_ori_eq`= '"+id_ori_eq[indice-1]+"'";
					Statement comando = conexion.createStatement();
					ResultSet registro = comando.executeQuery(sql);
					while(registro.next()) {
						contador++;
					}
					
					if(contador==0) {
						sql = "DELETE from origen_equipo WHERE `id_ori_eq`= '"+id_ori_eq[indice-1]+"'";
					
						comando = conexion.createStatement();
						//cargarDriver();
						comando.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "Origen de equipo eliminado correctamente");
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
					String sql = "UPDATE `origen_equipo` SET `nombre_ori_eq`='"+nombre+"',`descripcion_ori_eq`='"+desc+"' WHERE `id_ori_eq`= '"+id_ori_eq[indice-1]+"'";
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
					String sql = "INSERT INTO `origen_equipo`(`nombre_ori_eq`, `descripcion_ori_eq`) VALUES ('"+nombre+"', '"+desc+"')";
					Statement comando = conexion.createStatement();
					cargarDriver();
					comando.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Origen agregado correctamente");
					limpiar();
					
				} catch (Exception e) {
					System.out.println("Error");
					e.printStackTrace();
				}
				
			}
		});
		contentPane.setLayout(gl_contentPane);
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
			
			String sql = "select * from origen_equipo;";
			Statement comando = conexion.createStatement();
			
			//Para conocer el numero de registros:
			ResultSet registro = comando.executeQuery(sql);
			int nregistros=0;
			while(registro.next()) {
				nregistros++;
			}
			
			
			id_ori_eq=new String[nregistros];//Creamos un array del tamaño de nº de registros que tengamos
			
			registro = comando.executeQuery(sql);
			while(registro.next()) {
				fila[0] = indice+1;
				fila[1] = registro.getString("nombre_ori_eq");
				
				fila[2] = registro.getString("descripcion_ori_eq");
				if(fila[2]==null) {
					fila[2]="";
				}
				
				id_ori_eq[indice]=registro.getString("id_ori_eq");
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
