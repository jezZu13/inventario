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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JSeparator;



public class Dispositivo extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel modelo;
	private Connection conexion;
	private JTable table;
	private JTextField txtSN;
	private JTextField txtIdentificador;
	private JTextField txtFechaAlta;
	private JTextField txtMAC;
	private JTextField txtObservaciones;
	private JComboBox comboBoxProducto;
	private JComboBox comboBoxOrigen;
	private JComboBox comboBoxEstado;
	private JComboBox comboBoxUbicacion;
	private String id_disp[];
	private String id_marca[];
	private String id_familia_prod[];
	private JLabel lblnfila;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dispositivo frame = new Dispositivo();
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
	public Dispositivo() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("Assets\\logo_ventana.png"));
		setTitle("Dispositivo");
		
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
		modelo.addColumn("Identificador");
		modelo.addColumn("SN");
		modelo.addColumn("Producto");
		modelo.addColumn("Tipo de producto");
		modelo.addColumn("Marca");
		modelo.addColumn("Fecha alta");
		modelo.addColumn("Fecha baja");
		modelo.addColumn("Pedido");
		modelo.addColumn("Origen");
		modelo.addColumn("Estado actual");
		modelo.addColumn("Sala actual");
		modelo.addColumn("MAC");
		modelo.addColumn("Observaciones");
	
		
		rellenarTabla();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 641, 887);
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
						
						txtIdentificador.setText(table.getValueAt(fila, columna+(1-i)).toString());
						
						txtSN.setText(table.getValueAt(fila, columna+(2-i)).toString());
						
						for(int j=0, exit=1;(j<comboBoxProducto.getItemCount())&&(exit==1);j++) {
							String productoSeleccionado=comboBoxProducto.getItemAt(j).toString();
							if(productoSeleccionado.compareToIgnoreCase(table.getValueAt(fila, columna+(3-i) ).toString())==0) {
								comboBoxProducto.setSelectedIndex(j);
								exit=0;
							}
						}
						
						txtFechaAlta.setText(table.getValueAt(fila, columna+(6-i)).toString());
						if(txtFechaAlta.getText().equals("")) {
							txtFechaAlta.setFont(new Font("Arial", Font.ITALIC, 13));
							txtFechaAlta.setText("aaaa-mm-dd");
						}
						else {
							txtFechaAlta.setFont(new Font("Arial", Font.PLAIN, 13));
						}
										
						for(int j=0, exit=1;(j<comboBoxOrigen.getItemCount())&&(exit==1);j++) {
							String origenSeleccionado=comboBoxOrigen.getItemAt(j).toString();
							if(origenSeleccionado.compareToIgnoreCase(table.getValueAt(fila, columna+(9-i) ).toString())==0) {
								comboBoxOrigen.setSelectedIndex(j);
								exit=0;
							}
						}
						
						for(int j=0, exit=1;(j<comboBoxEstado.getItemCount())&&(exit==1);j++) {
							String estadoSeleccionado=comboBoxEstado.getItemAt(j).toString();
							if(estadoSeleccionado.compareToIgnoreCase(table.getValueAt(fila, columna+(10-i) ).toString())==0) {
								comboBoxEstado.setSelectedIndex(j);
								exit=0;
							}
						}
						
						for(int j=0, exit=1;(j<comboBoxUbicacion.getItemCount())&&(exit==1);j++) {
							String ubicacionSeleccionada=comboBoxUbicacion.getItemAt(j).toString();
							if(ubicacionSeleccionada.compareToIgnoreCase(table.getValueAt(fila, columna+(11-i) ).toString())==0) {
								comboBoxUbicacion.setSelectedIndex(j);
								exit=0;
							}
						}
						
						txtMAC.setText(table.getValueAt(fila, columna+(12-i)).toString());
						
						txtObservaciones.setText(table.getValueAt(fila, columna+(13-i)).toString());
						

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
		//Ajustamos el tamaño del resto de columnas:
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		table.getColumnModel().getColumn(4).setPreferredWidth(150);
		table.getColumnModel().getColumn(5).setPreferredWidth(150);
		table.getColumnModel().getColumn(6).setPreferredWidth(80);
		table.getColumnModel().getColumn(7).setPreferredWidth(80);
		table.getColumnModel().getColumn(8).setPreferredWidth(150);
		table.getColumnModel().getColumn(9).setPreferredWidth(150);
		table.getColumnModel().getColumn(10).setPreferredWidth(150);
		table.getColumnModel().getColumn(11).setPreferredWidth(150);
		table.getColumnModel().getColumn(12).setPreferredWidth(150);
		table.getColumnModel().getColumn(13).setPreferredWidth(500);
		//Para permitir ordenar el contenido por columnas:
		table.setAutoCreateRowSorter(true);


		JLabel lblproducto = new JLabel("Producto");
		lblproducto.setFont(new Font("Arial", Font.BOLD, 13));
		
		JLabel lblorigen = new JLabel("Origen");
		lblorigen.setFont(new Font("Arial", Font.BOLD, 13));
		
		lblnfila = new JLabel("");
		lblnfila.setVisible(false);
		
		txtSN = new JTextField();
		txtSN.setBorder(null);
		txtSN.setFont(new Font("Arial", Font.PLAIN, 13));
		txtSN.setColumns(10);
		
		JLabel lblestado = new JLabel("Estado");
		lblestado.setFont(new Font("Arial", Font.BOLD, 13));
		
		txtIdentificador = new JTextField();
		txtIdentificador.setBorder(null);
		txtIdentificador.setFont(new Font("Arial", Font.PLAIN, 13));
		txtIdentificador.setColumns(10);
		
		JLabel lblubicacion = new JLabel("Ubicacion");
		lblubicacion.setFont(new Font("Arial", Font.BOLD, 13));
		
		comboBoxProducto = new JComboBox();
		comboBoxProducto.setFont(new Font("Arial", Font.PLAIN, 13));
		//Rellenar combo box:
		String sql_cobobox="select * from producto;";
		Statement comandocombo;
		try {
			comandocombo=conexion.createStatement();
			ResultSet registro=comandocombo.executeQuery(sql_cobobox);
			//recorremos la columnas de la tabla y recuperamos los valores.
			while(registro.next()) {
				comboBoxProducto.addItem(registro.getString("nombre_prod"));
			}
		}
		catch(Exception e) {
			System.out.println("Error");
		}
		comboBoxProducto.setSelectedIndex(-1);
		
		comboBoxOrigen = new JComboBox();
		comboBoxOrigen.setFont(new Font("Arial", Font.PLAIN, 13));
		//Rellenar combo box:
		sql_cobobox="select * from origen_equipo;";
		try {
			comandocombo=conexion.createStatement();
			ResultSet registro=comandocombo.executeQuery(sql_cobobox);
			//recorremos la columnas de la tabla y recuperamos los valores.
			while(registro.next()) {
				comboBoxOrigen.addItem(registro.getString("nombre_ori_eq"));
			}
		}
		catch(Exception e) {
			System.out.println("Error");
		}
		comboBoxOrigen.setSelectedIndex(-1);
		
		comboBoxEstado = new JComboBox();
		comboBoxEstado.setSelectedIndex(-1);
		comboBoxEstado.setFont(new Font("Arial", Font.PLAIN, 13));
		//Rellenar combo box:
		sql_cobobox="select * from estado;";
		try {
			comandocombo=conexion.createStatement();
			ResultSet registro=comandocombo.executeQuery(sql_cobobox);
			//recorremos la columnas de la tabla y recuperamos los valores.
			while(registro.next()) {
				comboBoxEstado.addItem(registro.getString("nombre_estado"));
			}
		}
		catch(Exception e) {
			System.out.println("Error");
		}
		comboBoxEstado.setSelectedIndex(-1);
		
		comboBoxUbicacion = new JComboBox();
		comboBoxUbicacion.setSelectedIndex(-1);
		comboBoxUbicacion.setFont(new Font("Arial", Font.PLAIN, 13));
		//Rellenar combo box:
		sql_cobobox="select * from sala;";
		try {
			comandocombo=conexion.createStatement();
			ResultSet registro=comandocombo.executeQuery(sql_cobobox);
			//recorremos la columnas de la tabla y recuperamos los valores.
			while(registro.next()) {
				comboBoxUbicacion.addItem(registro.getString("nombre_sala"));
			}
		}
		catch(Exception e) {
			System.out.println("Error");
		}
		comboBoxUbicacion.setSelectedIndex(-1);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		JLabel lblidentificador = new JLabel("Identificador");
		lblidentificador.setFont(new Font("Arial", Font.BOLD, 13));
		
		JLabel lblsn = new JLabel("SN");
		lblsn.setFont(new Font("Arial", Font.BOLD, 13));
		
		JLabel lblFechaAlta = new JLabel("Fecha alta");
		lblFechaAlta.setFont(new Font("Arial", Font.BOLD, 13));
		
		JLabel lblMac = new JLabel("MAC");
		lblMac.setFont(new Font("Arial", Font.BOLD, 13));
		
		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setFont(new Font("Arial", Font.BOLD, 13));
		
		txtFechaAlta = new JTextField();
		txtFechaAlta.setBorder(null);
		txtFechaAlta.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				if(txtFechaAlta.getText().equals("aaaa-mm-dd")) {
					txtFechaAlta.setText("");
					txtFechaAlta.setFont(new Font("Arial", Font.PLAIN, 13));
				}
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				
				if(txtFechaAlta.getText().equals("")) {
					txtFechaAlta.setFont(new Font("Arial", Font.ITALIC, 13));
					txtFechaAlta.setText("aaaa-mm-dd");
				}
			}
		});
		txtFechaAlta.setFont(new Font("Arial", Font.ITALIC, 13));
		txtFechaAlta.setColumns(10);
		txtFechaAlta.setText("aaaa-mm-dd");
		
		
		txtMAC = new JTextField();
		txtMAC.setBorder(null);
		txtMAC.setFont(new Font("Arial", Font.PLAIN, 13));
		txtMAC.setColumns(10);
		
		txtObservaciones = new JTextField();
		txtObservaciones.setBorder(null);
		txtObservaciones.setFont(new Font("Arial", Font.PLAIN, 13));
		txtObservaciones.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.ORANGE);
		separator.setBackground(Color.ORANGE);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.ORANGE);
		separator_1.setBackground(Color.ORANGE);
		
		JSeparator separator_2 = new JSeparator();
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(Color.ORANGE);
		separator_3.setBackground(Color.ORANGE);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(Color.ORANGE);
		separator_4.setBackground(Color.ORANGE);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setForeground(Color.ORANGE);
		separator_5.setBackground(Color.ORANGE);
				
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addComponent(lblnfila, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(588, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
					.addGap(2))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(184)
							.addComponent(separator_3, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(45)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblObservaciones, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblMac, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblFechaAlta, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblsn, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(txtSN, GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
										.addComponent(txtFechaAlta, GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
										.addComponent(txtMAC, GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
										.addComponent(txtObservaciones, GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
										.addComponent(separator, GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
										.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
										.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
										.addComponent(separator_4, GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblubicacion, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblestado, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(lblproducto, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblorigen, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(comboBoxProducto, 0, 354, Short.MAX_VALUE)
										.addComponent(comboBoxOrigen, 0, 354, Short.MAX_VALUE)
										.addComponent(comboBoxEstado, 0, 354, Short.MAX_VALUE)
										.addComponent(comboBoxUbicacion, 0, 354, Short.MAX_VALUE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblidentificador, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(separator_5, GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
										.addComponent(txtIdentificador, GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE))))))
					.addGap(85))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblnfila, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
							.addGap(43)
							.addComponent(txtIdentificador, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblidentificador, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addGap(8)
					.addComponent(separator_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(23)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblsn, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtSN, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_4, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGap(23)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblFechaAlta, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtFechaAlta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(8)
							.addComponent(separator_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(24)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblMac, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtMAC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGap(23)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblObservaciones, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtObservaciones, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(8)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(22)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblproducto)
						.addComponent(comboBoxProducto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblorigen)
						.addComponent(comboBoxOrigen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblestado)
						.addComponent(comboBoxEstado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblubicacion)
						.addComponent(comboBoxUbicacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(48, Short.MAX_VALUE))
		);
		
		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.setBorder(null);
		btnInsertar.setBackground(Color.ORANGE);
		btnInsertar.setForeground(Color.BLACK);
		btnInsertar.setFont(new Font("Arial", Font.BOLD, 14));
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String identificador;
					String sn;
					String fecha_alta=null;
					String mac;
					String observaciones;
					String producto;
					String origen;
					String estado;
					String ubicacion;
					
					String id_producto=null;
					String id_origen=null;
					String id_estado=null;
					String id_ubicacion=null;

					identificador = txtIdentificador.getText();
					sn = txtSN.getText();
					mac = txtMAC.getText();
					observaciones = txtObservaciones.getText();
					
					producto=comboBoxProducto.getSelectedItem().toString();
					origen=comboBoxOrigen.getSelectedItem().toString();
					estado=comboBoxEstado.getSelectedItem().toString();
					ubicacion=comboBoxUbicacion.getSelectedItem().toString();
					

					String sql_fecha="select current_date";
					Statement comando_fecha = conexion.createStatement();
					ResultSet registro_fecha = comando_fecha.executeQuery(sql_fecha);
					while(registro_fecha.next()){
						fecha_alta = registro_fecha.getString("current_date");
					}
					
					

					//Capturamos el ID del producto correspondiente:
					int exit=1;
					String sql="select * from producto;";
					Statement comando = conexion.createStatement();
					ResultSet registro = comando.executeQuery(sql);
					Object[] fila=new Object[2];
					while( (registro.next()) && (exit==1) ){
						fila[0] = registro.getString("id_prod");
						fila[1] = registro.getString("nombre_prod");
						if(producto.compareToIgnoreCase(fila[1].toString())==0) {
							id_producto=fila[0].toString();
						}
					}
					
					
					//Capturamos el ID del origen correspondiente:
					exit=1;
					sql="select * from origen_equipo;";
					comando = conexion.createStatement();
					registro = comando.executeQuery(sql);
					while( (registro.next()) && (exit==1) ){
						fila[0] = registro.getString("id_ori_eq");
						fila[1] = registro.getString("nombre_ori_eq");
						if(origen.compareToIgnoreCase(fila[1].toString())==0) {
							id_origen=fila[0].toString();
						}
					}
					
					
					//Capturamos el ID del estado correspondiente:
					exit=1;
					sql="select * from estado;";
					comando = conexion.createStatement();
					registro = comando.executeQuery(sql);
					while( (registro.next()) && (exit==1) ){
						fila[0] = registro.getString("id_estado");
						fila[1] = registro.getString("nombre_estado");
						if(estado.compareToIgnoreCase(fila[1].toString())==0) {
							id_estado=fila[0].toString();
						}
					}
					

					//Capturamos el ID de la ubicacion correspondiente:
					exit=1;
					sql="select * from sala;";
					comando = conexion.createStatement();
					registro = comando.executeQuery(sql);
					while( (registro.next()) && (exit==1) ){
						fila[0] = registro.getString("id_sala");
						fila[1] = registro.getString("nombre_sala");
						if(ubicacion.compareToIgnoreCase(fila[1].toString())==0) {
							id_ubicacion=fila[0].toString();
						}
					}

										
					//Damos de alta al dispositivo en la tabla de dispositivos:
					sql = "INSERT INTO `dispositivo` (`id_disp`, `identificador_disp`, `sn_disp`, `fecha_alta_disp`, `fecha_baja_disp`, `MAC_disp`, `observaciones_disp`, `id_producto`, `id_ped`, `id_ori_eq`) VALUES ("+null+", '"+identificador+"', '"+sn+"', '"+fecha_alta+"', "+null+", '"+mac+"', '"+observaciones+"', '"+id_producto+"', '3', '"+id_origen+"');"; //El nº 3 es el id del origen Desconocido.
					comando = conexion.createStatement();
					cargarDriver();
					comando.executeUpdate(sql);
					
					//Necesitamos recuperar el id del dispositivo dado de alta:
					String id_disp_actual=null;
					sql="select id_disp from dispositivo order by id_disp;";
					comando = conexion.createStatement();
					registro = comando.executeQuery(sql);
					while(registro.next()){
						id_disp_actual = registro.getString("id_disp");
					}
					
					//Vamos a dar de alta el dispositivo en la tabla de "estado_dispositivo"
					sql="INSERT INTO `estado_dispositivo` (`id_disp`, `id_estado`, `fecha_inicio_est_disp`, `fecha_fin_est_disp`) VALUES ('"+id_disp_actual+"', '"+id_estado+"', '"+fecha_alta+"', "+null+");";
					comando = conexion.createStatement();
					comando.executeUpdate(sql);
					
					//Vamos a dar de alta el dispositivo en la tabla de "ubicacion_dispositivo"
					sql="INSERT INTO `ubicacion_dispositivo` (`id_disp`, `id_sala`, `fecha_entrada_disp_sala`, `fecha_salida_disp_sala`) VALUES ('"+id_disp_actual+"', '"+id_ubicacion+"', '"+fecha_alta+"', "+null+");";
					comando = conexion.createStatement();
					comando.executeUpdate(sql);
					
					JOptionPane.showMessageDialog(null, "Dispositivo agregado correctamente");
					limpiar();
					
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,"Error","Error",JOptionPane.ERROR_MESSAGE);
					//e.printStackTrace();
				}
				
				
			}
		});
		
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
		
		JButton btnDarDeBaja = new JButton("Dar de baja");
		btnDarDeBaja.setBorder(null);
		btnDarDeBaja.setBackground(Color.ORANGE);
		btnDarDeBaja.setForeground(Color.BLACK);
		btnDarDeBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					String nfila=lblnfila.getText();//Obtenemos el nº de fila del elemento a modificar,
					int indice = Integer.parseInt(nfila);//y luego lo parseamos para poder usarlo como integer.
					
					String sql="select * from dispositivo where id_disp='"+id_disp[indice-1]+"';";
					Statement comand = conexion.createStatement();
					ResultSet registro = comand.executeQuery(sql);
					String fecha_baja_disp_actual=null;
					while(registro.next()){
						fecha_baja_disp_actual = registro.getString("fecha_baja_disp");
					}
					
					if((fecha_baja_disp_actual==null)||(fecha_baja_disp_actual=="0000-00-00")) {//Si no está dado de baja, procedemos a darlo de baja.
						
						String sql_fecha="select current_date";
						String fecha_actual=null;
						Statement comando_fecha = conexion.createStatement();
						ResultSet registro_fecha = comando_fecha.executeQuery(sql_fecha);
						while(registro_fecha.next()){
							fecha_actual = registro_fecha.getString("current_date");
						}
						
						String query="UPDATE `dispositivo` SET `fecha_baja_disp` = '"+fecha_actual+"' WHERE `dispositivo`.`id_disp` = '"+id_disp[indice-1]+"' ;";
						Statement comando = conexion.createStatement();
						comando.executeUpdate(query);
						JOptionPane.showMessageDialog(null, "Se ha dado de baja el dispositivo.");
						
						
						//Vamos a capturar la ubicacion actual:
						sql="select * from ubicacion_dispositivo where id_disp='"+id_disp[indice-1]+"' and fecha_salida_disp_sala is null;";
						comando = conexion.createStatement();
						registro = comando.executeQuery(sql);
						String sala_actual=null;
						while(registro.next()){
							sala_actual = registro.getString("id_sala");
						}
						
						//Vamos a capturar el estado actual:
						sql="select * from estado_dispositivo where id_disp='"+id_disp[indice-1]+"' and fecha_fin_est_disp is null;";
						comando = conexion.createStatement();
						registro = comando.executeQuery(sql);
						String estado_actual=null;
						while(registro.next()){
							estado_actual = registro.getString("id_estado");
						}
						
						//Actualizamos el estado del dispositivo:
							//Primero hay que finalizar el estado actual:
							sql = "UPDATE `estado_dispositivo` SET `fecha_fin_est_disp` = '"+fecha_actual+"' WHERE `estado_dispositivo`.`id_disp` = '"+id_disp[indice-1]+"' AND `estado_dispositivo`.`id_estado` = '"+estado_actual+"';";
							comando = conexion.createStatement();
							comando.executeUpdate(sql);
							
							////Después damos de alta al dispositivo con su nuevo estado.
							sql="INSERT INTO `estado_dispositivo` (`id_disp`, `id_estado`, `fecha_inicio_est_disp`, `fecha_fin_est_disp`) VALUES ('"+id_disp[indice-1]+"', '16', '"+fecha_actual+"', "+null+")"; //El 16 es el id del estado "descatalogado"
							comando = conexion.createStatement();
							comando.executeUpdate(sql);

						//Actualizamos la ubicacion del dispositivo, en caso de que haya cambiado:
							//Primero hay que finalizar la ubicación actual:
							sql ="UPDATE `ubicacion_dispositivo` SET `fecha_salida_disp_sala` = '"+fecha_actual+"' WHERE `ubicacion_dispositivo`.`id_disp` = '"+id_disp[indice-1]+"' AND `ubicacion_dispositivo`.`id_sala` = '"+sala_actual+"';";
							comando = conexion.createStatement();
							comando.executeUpdate(sql);
							//Después damos de alta al dispositivo en su nueva ubicacion:
							sql = "INSERT INTO `ubicacion_dispositivo` (`id_disp`, `id_sala`, `fecha_entrada_disp_sala`, `fecha_salida_disp_sala`) VALUES ('"+id_disp[indice-1]+"', '7', '"+fecha_actual+"', "+null+")"; //El 7 es el id de la sala "basura"
							comando = conexion.createStatement();
							comando.executeUpdate(sql);
						
	
						limpiar();
					}
					else {//No se puede dar de baja porque ya está dado de baja.
						JOptionPane.showMessageDialog(null,"No se pude dar de baja el dispositivo puesto que ya se encuentra dado de baja","Error",JOptionPane.ERROR_MESSAGE);
					}
					
				} catch (Exception e2) {
					System.out.println("Error");
					e2.printStackTrace();
				}
				
			}
		});
		btnDarDeBaja.setFont(new Font("Arial", Font.BOLD, 14));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(52)
					.addComponent(btnInsertar, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
					.addGap(5)
					.addComponent(btnModificar, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
					.addGap(5)
					.addComponent(btnDarDeBaja, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
					.addGap(5)
					.addComponent(btnEliminar, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
					.addGap(5)
					.addComponent(btnLimpiar, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
					.addGap(32))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnLimpiar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnInsertar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
						.addComponent(btnModificar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnDarDeBaja, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnEliminar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSN.setText("");
				txtIdentificador.setText("");
				txtFechaAlta.setText("");
				txtMAC.setText("");
				txtObservaciones.setText("");
				comboBoxProducto.setSelectedIndex(-1);
				comboBoxOrigen.setSelectedIndex(-1);
				comboBoxEstado.setSelectedIndex(-1);
				comboBoxUbicacion.setSelectedIndex(-1);

			}
		});
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					String nfila=lblnfila.getText();//Obtenemos el nº de fila del elemento a modificar,
					int indice = Integer.parseInt(nfila);//y luego lo parseamos para poder usarlo como integer.
					
					//Necesitamos ver si el producto tiene pedidos o no:
					String sql="SELECT dispositivo.id_disp, dispositivo.id_producto, dispositivo.id_ori_eq ,dispositivo.id_ped, pedido.id_ped, pedido.codigo_ped FROM dispositivo, pedido WHERE (dispositivo.id_ped=pedido.id_ped) and (id_disp='"+id_disp[indice-1]+"')";
					Statement comando = conexion.createStatement();
					ResultSet registro = comando.executeQuery(sql);
					//Object[] fila=new Object[1];
					String codigo_ped=null, id_producto=null, id_ped=null, id_origen=null;
					while(registro.next()) {						
						codigo_ped = registro.getString("codigo_ped");
						id_producto = registro.getString("id_producto");
						id_ped = registro.getString("id_ped");
						id_origen = registro.getString("id_ori_eq");
					}
					//y si los tiene tenemos que restarle 1 a la cantidad, puesto que hemos eliminado un registro.
					if(codigo_ped.compareToIgnoreCase("Desconocido")!=0) {
												
						String nunidades_prod_ped=null;
						sql="SELECT nunidades_prod_ped FROM producto_en_pedido WHERE id_ped='"+id_ped+"' and id_prod='"+id_producto+"' and id_ori_eq='"+id_origen+"';";
						comando = conexion.createStatement();
						registro = comando.executeQuery(sql);
						while(registro.next()) {						
							nunidades_prod_ped = registro.getString("nunidades_prod_ped");
						}
						int nunidades = Integer.parseInt(nunidades_prod_ped);
						nunidades=nunidades-1;
						sql="UPDATE `producto_en_pedido` SET `nunidades_prod_ped` = '"+nunidades+"' WHERE `producto_en_pedido`.`id_ped` = '"+id_ped+"' AND `producto_en_pedido`.`id_prod` = '"+id_producto+"' AND `producto_en_pedido`.`id_ori_eq` = '"+id_origen+"';";
						comando = conexion.createStatement();
						comando.executeUpdate(sql);
						
					}
					
					
					//Vamos a borrarlo de la tabla estado_dispositivo
					sql="DELETE FROM `estado_dispositivo` WHERE `estado_dispositivo`.`id_disp` = '"+id_disp[indice-1]+"'";
					comando = conexion.createStatement();
					comando.executeUpdate(sql);
					
					//Vamos a borrarlo de la tabla ubicacion_dispositivo
					sql="DELETE FROM `ubicacion_dispositivo` WHERE `ubicacion_dispositivo`.`id_disp` = '"+id_disp[indice-1]+"'";
					comando = conexion.createStatement();
					comando.executeUpdate(sql);
					
					//Ahora, por último, eliminamos el dispositivo de la tabla de dispositivos
					sql = "DELETE from dispositivo WHERE `id_disp`= '"+id_disp[indice-1]+"'";
					comando = conexion.createStatement();
					//cargarDriver();
					comando.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Estado eliminado correctamente");

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
					String identificador;
					String sn;
					String fecha_alta=null;
					String mac;
					String observaciones;
					String producto;
					String origen;
					String estado;
					String ubicacion;
					
					String id_producto=null;
					String id_origen=null;
					String id_estado=null;
					String id_ubicacion=null;

					identificador = txtIdentificador.getText();
					sn = txtSN.getText();
					mac = txtMAC.getText();
					observaciones = txtObservaciones.getText();
					fecha_alta = txtFechaAlta.getText();
					
					producto=comboBoxProducto.getSelectedItem().toString();
					origen=comboBoxOrigen.getSelectedItem().toString();
					estado=comboBoxEstado.getSelectedItem().toString();
					ubicacion=comboBoxUbicacion.getSelectedItem().toString();
					
					
					String sql_fecha="select current_date";
					String fecha_actual=null;
					Statement comando_fecha = conexion.createStatement();
					ResultSet registro_fecha = comando_fecha.executeQuery(sql_fecha);
					while(registro_fecha.next()){
						fecha_actual = registro_fecha.getString("current_date");
					}
					
					
					//Capturamos el ID del producto correspondiente:
					int exit=1;
					String sql="select * from producto;";
					Statement comando = conexion.createStatement();
					ResultSet registro = comando.executeQuery(sql);
					Object[] fila=new Object[2];
					while( (registro.next()) && (exit==1) ){
						fila[0] = registro.getString("id_prod");
						fila[1] = registro.getString("nombre_prod");
						if(producto.compareToIgnoreCase(fila[1].toString())==0) {
							id_producto=fila[0].toString();
						}
					}
					
					
					//Capturamos el ID del origen correspondiente:
					exit=1;
					sql="select * from origen_equipo;";
					comando = conexion.createStatement();
					registro = comando.executeQuery(sql);
					while( (registro.next()) && (exit==1) ){
						fila[0] = registro.getString("id_ori_eq");
						fila[1] = registro.getString("nombre_ori_eq");
						if(origen.compareToIgnoreCase(fila[1].toString())==0) {
							id_origen=fila[0].toString();
						}
					}
					
					
					//Capturamos el ID del estado correspondiente:
					exit=1;
					sql="select * from estado;";
					comando = conexion.createStatement();
					registro = comando.executeQuery(sql);
					while( (registro.next()) && (exit==1) ){
						fila[0] = registro.getString("id_estado");
						fila[1] = registro.getString("nombre_estado");
						if(estado.compareToIgnoreCase(fila[1].toString())==0) {
							id_estado=fila[0].toString();
						}
					}
					

					//Capturamos el ID de la ubicacion correspondiente:
					exit=1;
					sql="select * from sala;";
					comando = conexion.createStatement();
					registro = comando.executeQuery(sql);
					while( (registro.next()) && (exit==1) ){
						fila[0] = registro.getString("id_sala");
						fila[1] = registro.getString("nombre_sala");
						if(ubicacion.compareToIgnoreCase(fila[1].toString())==0) {
							id_ubicacion=fila[0].toString();
						}
					}
					
					//Vamos a capturar la ubicacion actual:
					sql="select * from ubicacion_dispositivo where id_disp='"+id_disp[indice-1]+"' and fecha_salida_disp_sala is null;";
					comando = conexion.createStatement();
					registro = comando.executeQuery(sql);
					String sala_actual=null;
					while(registro.next()){
						sala_actual = registro.getString("id_sala");
					}
					
					//Vamos a capturar el estado actual:
					sql="select * from estado_dispositivo where id_disp='"+id_disp[indice-1]+"' and fecha_fin_est_disp is null;";
					comando = conexion.createStatement();
					registro = comando.executeQuery(sql);
					String estado_actual=null;
					while(registro.next()){
						estado_actual = registro.getString("id_estado");
					}
				
					//Necesitamos ver si el dispositivo tiene pedidos o no:
					sql="SELECT dispositivo.id_disp, dispositivo.id_producto, dispositivo.id_ori_eq ,dispositivo.id_ped, pedido.id_ped, pedido.codigo_ped FROM dispositivo, pedido WHERE (dispositivo.id_ped=pedido.id_ped) and (id_disp='"+id_disp[indice-1]+"')";
					comando = conexion.createStatement();
					registro = comando.executeQuery(sql);
					String codigo_ped=null;
					while(registro.next()) {						
						codigo_ped = registro.getString("codigo_ped");
					}
					
					
					//Actualizamos el estado del dispositivo, en caso de que haya cambiado:
					if(estado_actual.compareTo(id_estado)!=0) {
						//Primero hay que finalizar el estado actual:
						sql = "UPDATE `estado_dispositivo` SET `fecha_fin_est_disp` = '"+fecha_actual+"' WHERE `estado_dispositivo`.`id_disp` = '"+id_disp[indice-1]+"' AND `estado_dispositivo`.`id_estado` = '"+estado_actual+"';";
						comando = conexion.createStatement();
						comando.executeUpdate(sql);
						
						////Después damos de alta al dispositivo con su nuevo estado
						sql="INSERT INTO `estado_dispositivo` (`id_disp`, `id_estado`, `fecha_inicio_est_disp`, `fecha_fin_est_disp`) VALUES ('"+id_disp[indice-1]+"', '"+id_estado+"', '"+fecha_actual+"', "+null+")";
						comando = conexion.createStatement();
						comando.executeUpdate(sql);
					}
					
					
					
					//Actualizamos la ubicacion del dispositivo, en caso de que haya cambiado:
					if(sala_actual.compareTo(id_ubicacion)!=0) {
						//Primero hay que finalizar la ubicación actual:
						sql ="UPDATE `ubicacion_dispositivo` SET `fecha_salida_disp_sala` = '"+fecha_actual+"' WHERE `ubicacion_dispositivo`.`id_disp` = '"+id_disp[indice-1]+"' AND `ubicacion_dispositivo`.`id_sala` = '"+sala_actual+"';";
						comando = conexion.createStatement();
						comando.executeUpdate(sql);
						//Después damos de alta al dispositivo en su nueva ubicacion:
						sql = "INSERT INTO `ubicacion_dispositivo` (`id_disp`, `id_sala`, `fecha_entrada_disp_sala`, `fecha_salida_disp_sala`) VALUES ('"+id_disp[indice-1]+"', '"+id_ubicacion+"', '"+fecha_actual+"', "+null+")";
						comando = conexion.createStatement();
						comando.executeUpdate(sql);
					}
					

					
					//Si el dispositivo NO proviene de ningun pedido, ejecuto la primera query:
					if(codigo_ped.compareToIgnoreCase("Desconocido")==0) {
						
						//Por ultimo modificamos el dispositivo en su tabla:
						sql = "UPDATE `dispositivo` SET `identificador_disp`='"+identificador+"',`sn_disp`='"+sn+"',`fecha_alta_disp`='"+fecha_alta+"',`MAC_disp`='"+mac+"',`observaciones_disp`='"+observaciones+"',`id_producto`='"+id_producto+"',`id_ori_eq`='"+id_origen+"' WHERE `id_disp`= '"+id_disp[indice-1]+"'";
						comando = conexion.createStatement();
						comando.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "Estado modificado correctamente");
					}
					else {//y si no ejecutamos esta otra:
						sql = "UPDATE `dispositivo` SET `identificador_disp`='"+identificador+"',`sn_disp`='"+sn+"',`MAC_disp`='"+mac+"',`observaciones_disp`='"+observaciones+"' WHERE `id_disp`= '"+id_disp[indice-1]+"'";
						comando = conexion.createStatement();
						comando.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "Estado modificado correctamente." + "\n\nPuesto que el dispositivo actual se dió de alta automáticamente a raíz de un pedido, \nno se permite la modificación de la fecha de alta, el producto al que corresponde, \nni tampoco el origen por el que se adquirió");
					}
					
					
					
					limpiar();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,"El formato fecha introducido no es correcto","Error",JOptionPane.ERROR_MESSAGE);
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
		txtSN.setText("");
		txtIdentificador.setText("");
		comboBoxProducto.setSelectedIndex(-1);
		comboBoxOrigen.setSelectedIndex(-1);
		comboBoxEstado.setSelectedIndex(-1);
		comboBoxUbicacion.setSelectedIndex(-1);
		
		//**FIN limpiar
		
	}
	
	public void rellenarTabla() {
		try {
			
			int indice=0;
			Object[] fila=new Object[14]; //Creamos un objeto con tantos parametros como datos retorne cada fila.
			
			//String sql="SELECT dispositivo.*, producto.nombre_prod, pedido.id_ped, pedido.codigo_ped, origen_equipo.nombre_ori_eq, estado_dispositivo.id_estado, estado.nombre_estado, estado_dispositivo.fecha_fin_est_disp, ubicacion_dispositivo.id_sala, sala.nombre_sala FROM dispositivo, producto, pedido, origen_equipo, estado_dispositivo, estado, ubicacion_dispositivo, sala WHERE (dispositivo.id_producto=producto.id_prod) && (dispositivo.id_ped=pedido.id_ped) && (dispositivo.id_ori_eq=origen_equipo.id_ori_eq) && (estado_dispositivo.id_disp=dispositivo.id_disp) && (estado_dispositivo.id_estado=estado.id_estado) && (estado_dispositivo.fecha_fin_est_disp is NULL) && (ubicacion_dispositivo.id_disp=dispositivo.id_disp) && (ubicacion_dispositivo.fecha_salida_disp_sala is NULL)&&(ubicacion_dispositivo.id_sala=sala.id_sala) ORDER BY producto.nombre_prod;";
			String sql="SELECT dispositivo.*, producto.nombre_prod, pedido.id_ped, pedido.codigo_ped, origen_equipo.nombre_ori_eq, estado_dispositivo.id_estado, estado.nombre_estado, estado_dispositivo.fecha_fin_est_disp, ubicacion_dispositivo.id_sala, sala.nombre_sala, producto.id_marca, marca.id_marca, marca.nombre_marca, producto.id_familia_prod, familia_producto.id_familia_prod, familia_producto.nombre_familia_prod "+
			"FROM dispositivo, producto, pedido, origen_equipo, estado_dispositivo, estado, ubicacion_dispositivo, sala, marca, familia_producto "+
			"WHERE (dispositivo.id_producto=producto.id_prod) && (dispositivo.id_ped=pedido.id_ped) && (dispositivo.id_ori_eq=origen_equipo.id_ori_eq) && (estado_dispositivo.id_disp=dispositivo.id_disp) && (estado_dispositivo.id_estado=estado.id_estado) && (estado_dispositivo.fecha_fin_est_disp is NULL) && (ubicacion_dispositivo.id_disp=dispositivo.id_disp) && (ubicacion_dispositivo.fecha_salida_disp_sala is NULL)&&(ubicacion_dispositivo.id_sala=sala.id_sala) && (producto.id_familia_prod=familia_producto.id_familia_prod) && (producto.id_marca=marca.id_marca) ORDER BY producto.nombre_prod;";
			
			
			Statement comando = conexion.createStatement();
			
			//Para conocer el numero de registros:
			ResultSet registro = comando.executeQuery(sql);
			int nregistros=0;
			while(registro.next()) {
				nregistros++;
			}
						
			id_disp=new String[nregistros];//Creamos un array del tamaño de nº de registros que tengamos
			
			registro = comando.executeQuery(sql);
			while(registro.next()) {
				fila[0] = indice+1;
				
				fila[1] = registro.getString("identificador_disp");
				if(fila[1]==null) {
					fila[1]="";
				}
				
				fila[2] = registro.getString("sn_disp");
				if(fila[2]==null) {
					fila[2]="";
				}
				
				fila[3] = registro.getString("nombre_prod");
				if(fila[3]==null) {
					fila[3]="";
				}
				
				fila[4] = registro.getString("nombre_familia_prod");
				if(fila[4]==null) {
					fila[4]="";
				}
				
				fila[5] = registro.getString("nombre_marca");
				if(fila[5]==null) {
					fila[5]="";
				}
				
				fila[6] = registro.getString("fecha_alta_disp");
				if(fila[6]==null) {
					fila[6]="";
				}
				
				fila[7] = registro.getString("fecha_baja_disp");
				if(fila[7]==null) {
					fila[7]="";
				}
				
				fila[8] = registro.getString("codigo_ped");
				if(fila[8].toString().compareToIgnoreCase("Desconocido")==0) {
					fila[8]="";
				}
				
				fila[9] = registro.getString("nombre_ori_eq");
				if(fila[9]==null) {
					fila[9]="";
				}
				
				fila[10] = registro.getString("nombre_estado");
				if(fila[10]==null) {
					fila[10]="";
				}
				
				fila[11] = registro.getString("nombre_sala");
				if(fila[11]==null) {
					fila[11]="";
				}
				
				fila[12] = registro.getString("MAC_disp");
				if(fila[12]==null) {
					fila[12]="";
				}
				
				fila[13] = registro.getString("observaciones_disp");
				if(fila[13]==null) {
					fila[13]="";
				}			
				
				id_disp[indice]=registro.getString("id_disp");
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
