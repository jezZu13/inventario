import java.awt.Color;
import java.awt.EventQueue;
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
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Sala extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel modelo;
	private Connection conexion;
	private JTable table;
	private JTextField txtNombre;
	private JTextField txtUsuarioSala;
	private JTextField txtCursoAsignado;
	private JTextField txtDescSala;
	private JComboBox comboBoxEdificio;
	private JComboBox comboBoxTipoSala;
	private String id_sala[];
	private JLabel lblnfila;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sala frame = new Sala();
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
	public Sala() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("Assets\\logo_ventana.png"));
		setTitle("Sala");
		
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
		modelo.addColumn("Nombre Sala");
		modelo.addColumn("Usuario Sala");
		modelo.addColumn("Curso Asignado");
		modelo.addColumn("Descripcion");
		modelo.addColumn("Edificio");
		modelo.addColumn("Tipo Sala");
		

		
		rellenarTabla();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 656, 685);
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
						txtUsuarioSala.setText(table.getValueAt(fila, columna+(2-i)).toString());
						txtCursoAsignado.setText(table.getValueAt(fila, columna+(3-i)).toString());
						txtDescSala.setText(table.getValueAt(fila, columna+(4-i)).toString());
						
						for(int j=0, exit=1;(j<comboBoxEdificio.getItemCount())&&(exit==1);j++) {
							String edificioSeleccionado=comboBoxEdificio.getItemAt(j).toString();
							if(edificioSeleccionado.compareToIgnoreCase(table.getValueAt(fila, columna+(5-i) ).toString())==0) {
								comboBoxEdificio.setSelectedIndex(j);
								exit=0;
							}
						}
						
						for(int j=0, exit=1;(j<comboBoxTipoSala.getItemCount())&&(exit==1);j++) {
							String tipoSalaSeleccionada=comboBoxTipoSala.getItemAt(j).toString();
							if(tipoSalaSeleccionada.compareToIgnoreCase(table.getValueAt(fila, columna+(6-i) ).toString())==0) {
								comboBoxTipoSala.setSelectedIndex(j);
								exit=0;
							}
						}
						
						
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
		
		
		JLabel lblNombre = new JLabel("Nombre Sala");
		lblNombre.setFont(new Font("Arial", Font.BOLD, 13));
		
		txtNombre = new JTextField();
		txtNombre.setBorder(null);
		txtNombre.setFont(new Font("Arial", Font.PLAIN, 13));
		txtNombre.setColumns(10);
		
		lblnfila = new JLabel("");
		
		JLabel lblUsuarioSala = new JLabel("Usuario Sala");
		lblUsuarioSala.setFont(new Font("Arial", Font.BOLD, 13));
		
		JLabel lblCursoAsignado = new JLabel("Curso Asignado");
		lblCursoAsignado.setFont(new Font("Arial", Font.BOLD, 13));
		
		JLabel lblDescripcionSala = new JLabel("Descripcion Sala");
		lblDescripcionSala.setFont(new Font("Arial", Font.BOLD, 13));
		
		JLabel lblNombreEdif = new JLabel("Nombre Edificio");
		lblNombreEdif.setFont(new Font("Arial", Font.BOLD, 13));
		
		JLabel lblTipo = new JLabel("Tipo Sala");
		lblTipo.setFont(new Font("Arial", Font.BOLD, 13));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		comboBoxEdificio = new JComboBox();
		comboBoxEdificio.setFont(new Font("Arial", Font.PLAIN, 13));
		//Rellenar combo box:
		String sql_cobobox="select * from edificio;";
		try {
			Statement comandocombo=conexion.createStatement();
			ResultSet registro=comandocombo.executeQuery(sql_cobobox);
			//recorremos la columnas de la tabla y recuperamos los valores.
			while(registro.next()) {
				comboBoxEdificio.addItem(registro.getString("nombre_edif"));
			}
		}
		catch(Exception e) {
			System.out.println("Error");
		}
		comboBoxEdificio.setSelectedIndex(-1);
		
		
		comboBoxTipoSala = new JComboBox();
		comboBoxTipoSala.setFont(new Font("Arial", Font.PLAIN, 13));
		//Rellenar combo box:
		sql_cobobox="select * from tipo_sala;";
		try {
			Statement comandocombo=conexion.createStatement();
			ResultSet registro=comandocombo.executeQuery(sql_cobobox);
			//recorremos la columnas de la tabla y recuperamos los valores.
			while(registro.next()) {
				comboBoxTipoSala.addItem(registro.getString("nombre_tipo_sala"));
			}
		}
		catch(Exception e) {
			System.out.println("Error");
		}
		comboBoxTipoSala.setSelectedIndex(-1);
		
		
		
		txtUsuarioSala = new JTextField();
		txtUsuarioSala.setBorder(null);
		txtUsuarioSala.setFont(new Font("Arial", Font.PLAIN, 13));
		txtUsuarioSala.setColumns(10);
		
		txtCursoAsignado = new JTextField();
		txtCursoAsignado.setBorder(null);
		txtCursoAsignado.setFont(new Font("Arial", Font.PLAIN, 13));
		txtCursoAsignado.setColumns(10);
		
		txtDescSala = new JTextField();
		txtDescSala.setBorder(null);
		txtDescSala.setFont(new Font("Arial", Font.PLAIN, 13));
		txtDescSala.setColumns(10);
		
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
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
					.addGap(2))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addComponent(lblnfila, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(459))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(44)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUsuarioSala, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCursoAsignado, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDescripcionSala, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNombreEdif, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTipo, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
					.addGap(24)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(separator_5, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
						.addComponent(separator_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
						.addComponent(separator_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
						.addComponent(separator_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
						.addComponent(txtNombre, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
						.addComponent(txtUsuarioSala, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
						.addComponent(txtCursoAsignado, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
						.addComponent(txtDescSala, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
						.addComponent(comboBoxEdificio, Alignment.LEADING, 0, 370, Short.MAX_VALUE)
						.addComponent(comboBoxTipoSala, Alignment.LEADING, 0, 370, Short.MAX_VALUE))
					.addGap(71))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblnfila, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
							.addGap(34)
							.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNombre))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_5, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGap(23)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblUsuarioSala)
						.addComponent(txtUsuarioSala, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_4, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblCursoAsignado)
						.addComponent(txtCursoAsignado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_3, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGap(23)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblDescripcionSala)
						.addComponent(txtDescSala, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGap(22)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNombreEdif)
						.addComponent(comboBoxEdificio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(25)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblTipo)
						.addComponent(comboBoxTipoSala, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addGap(230))
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
					.addGap(123)
					.addComponent(btnInsertar, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
					.addGap(5)
					.addComponent(btnModificar, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
					.addGap(5)
					.addComponent(btnEliminar, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
					.addGap(5)
					.addComponent(btnLimpiar, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
					.addGap(88))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnLimpiar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
						.addComponent(btnEliminar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
						.addComponent(btnModificar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
						.addComponent(btnInsertar, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNombre.setText("");
				txtUsuarioSala.setText("");
				txtCursoAsignado.setText("");
				txtDescSala.setText("");
				comboBoxEdificio.setSelectedIndex(-1);
				comboBoxTipoSala.setSelectedIndex(-1);
			}
		});
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					String nfila=lblnfila.getText();//Obtenemos el nº de fila del elemento a modificar,
					int indice = Integer.parseInt(nfila);//y luego lo parseamos para poder usarlo como integer.
					
					
					//Antes de eliminar el registro, debemos comprobar si existen dependencias que impedirán su eliminación:
					int contador=0;
					String sql="SELECT * FROM `ubicacion_dispositivo` WHERE `id_sala`= '"+id_sala[indice-1]+"'";
					Statement comando = conexion.createStatement();
					ResultSet registro = comando.executeQuery(sql);
					while(registro.next()) {
						contador++;
					}
					
					//Las salas "Basura" y "Almacen" no se deben poder eliminar
					String nombre_sala_actual=null;
					sql="SELECT * FROM sala WHERE `id_sala`= '"+id_sala[indice-1]+"';";
					comando = conexion.createStatement();
					registro = comando.executeQuery(sql);
					while(registro.next()) {
						nombre_sala_actual = registro.getString("nombre_sala");
					}
					
					if(contador==0) {
						if( (nombre_sala_actual.compareToIgnoreCase("Basura")!=0)&&(nombre_sala_actual.compareToIgnoreCase("Almacen")!=0) ) {
						sql = "DELETE from sala WHERE `id_sala`= '"+id_sala[indice-1]+"'";
					
						comando = conexion.createStatement();
						comando.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "Tipo de sala eliminada correctamente");
						}
						else {
							JOptionPane.showMessageDialog(null,"No se puede eliminar dicha sala puesto que es usada por el sistema","Error",JOptionPane.ERROR_MESSAGE);
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
					String usuarioSala = txtUsuarioSala.getText();
					String cursoAsignado = txtCursoAsignado.getText();
					String descSala = txtDescSala.getText();
					String edificio=comboBoxEdificio.getSelectedItem().toString();
					String tipo_sala=comboBoxTipoSala.getSelectedItem().toString();
					
					String id_edificio=null;
					String id_tipo_sala=null;
					
					
					//Capturamos el ID del edificio correspondiente:
					int exit=1;
					String sql="select * from edificio;";
					Statement comando = conexion.createStatement();
					ResultSet registro = comando.executeQuery(sql);
					Object[] fila=new Object[2];
					while( (registro.next()) && (exit==1) ){
						fila[0] = registro.getString("id_edif");
						fila[1] = registro.getString("nombre_edif");
						if(edificio.compareToIgnoreCase(fila[1].toString())==0) {
							id_edificio=fila[0].toString();
						}
					}
					
					
					//Capturamos el ID del tipo de sala correspondiente:
					exit=1;
					sql="select * from tipo_sala;";
					comando = conexion.createStatement();
					registro = comando.executeQuery(sql);
					while( (registro.next()) && (exit==1) ){
						fila[0] = registro.getString("id_tipo_sala");
						fila[1] = registro.getString("nombre_tipo_sala");
						if(tipo_sala.compareToIgnoreCase(fila[1].toString())==0) {
							id_tipo_sala=fila[0].toString();
						}
					}
					
					//Las salas "Basura" y "Almacen" no se deben poder modificar
					String nombre_sala_actual=null;
					sql="SELECT * FROM sala WHERE `id_sala`= '"+id_sala[indice-1]+"';";
					comando = conexion.createStatement();
					registro = comando.executeQuery(sql);
					while(registro.next()) {
						nombre_sala_actual = registro.getString("nombre_sala");
					}
					
					if( (nombre_sala_actual.compareToIgnoreCase("Basura")!=0)&&(nombre_sala_actual.compareToIgnoreCase("Almacen")!=0) ){
						sql = "UPDATE `sala` SET `nombre_sala`='"+nombre+"',`usuario_sala`='"+usuarioSala+"',`curso_asignado_sala`='"+cursoAsignado+"',`descripcion_sala`='"+descSala+"',`id_edif`='"+id_edificio+"',`id_tipo_sala`='"+id_tipo_sala+"' WHERE `id_sala`= '"+id_sala[indice-1]+"'";
						comando = conexion.createStatement();
						comando.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "Sala modificada correctamente");
						limpiar();
					}
					else {
						JOptionPane.showMessageDialog(null,"No se puede modificar dicho sala puesto que es usado por el sistema","Error",JOptionPane.ERROR_MESSAGE);
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
					String usuarioSala = txtUsuarioSala.getText();
					String cursoAsignado = txtCursoAsignado.getText();
					String descSala = txtDescSala.getText();
					String edificio=comboBoxEdificio.getSelectedItem().toString();
					String tipo_sala=comboBoxTipoSala.getSelectedItem().toString();
					
					String id_edificio=null;
					String id_tipo_sala=null;
					
					
					//Capturamos el ID del edificio correspondiente:
					int exit=1;
					String sql="select * from edificio;";
					Statement comando = conexion.createStatement();
					ResultSet registro = comando.executeQuery(sql);
					Object[] fila=new Object[2];
					while( (registro.next()) && (exit==1) ){
						fila[0] = registro.getString("id_edif");
						fila[1] = registro.getString("nombre_edif");
						if(edificio.compareToIgnoreCase(fila[1].toString())==0) {
							id_edificio=fila[0].toString();
						}
					}
					
					
					//Capturamos el ID del tipo de sala correspondiente:
					exit=1;
					sql="select * from tipo_sala;";
					comando = conexion.createStatement();
					registro = comando.executeQuery(sql);
					while( (registro.next()) && (exit==1) ){
						fila[0] = registro.getString("id_tipo_sala");
						fila[1] = registro.getString("nombre_tipo_sala");
						if(tipo_sala.compareToIgnoreCase(fila[1].toString())==0) {
							id_tipo_sala=fila[0].toString();
						}
					}
					
					
					sql = "INSERT INTO `sala`(`nombre_sala`, `usuario_sala`, `curso_asignado_sala`, `descripcion_sala`, `id_edif`, `id_tipo_sala`) VALUES ('"+nombre+"', '"+usuarioSala+"', '"+cursoAsignado+"', '"+descSala+"', '"+id_edificio+"', '"+id_tipo_sala+"')";
					comando = conexion.createStatement();
					cargarDriver();
					comando.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Tipo de sala agregada correctamente");
					limpiar();
					
				} catch (Exception e) {
					//System.out.println("Error");
					JOptionPane.showMessageDialog(null,"Es necesario que, al menos, rellene los campos nombre sala, nombre edificio y tipo sala","Error",JOptionPane.ERROR_MESSAGE);
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
		txtUsuarioSala.setText("");
		txtCursoAsignado.setText("");
		txtDescSala.setText("");
		comboBoxEdificio.setSelectedIndex(-1);
		comboBoxTipoSala.setSelectedIndex(-1);
		//**FIN limpiar
		
	}
	
	public void rellenarTabla() {
		try {
			
			int indice=0;
			Object[] fila=new Object[7]; //Creamos un objeto con tantos parametros como datos retorne cada fila.
			
			String sql = "SELECT edificio.id_edif, edificio.nombre_edif, tipo_sala.id_tipo_sala, tipo_sala.nombre_tipo_sala, sala.id_sala, sala.nombre_sala, sala.usuario_sala, sala.curso_asignado_sala, sala.descripcion_sala FROM sala, edificio, tipo_sala where edificio.id_edif = sala.id_edif and tipo_sala.id_tipo_sala = sala.id_tipo_sala";
			Statement comando = conexion.createStatement();
			
			//Para conocer el numero de registros:
			ResultSet registro = comando.executeQuery(sql);
			int nregistros=0;
			while(registro.next()) {
				nregistros++;
			}
			
			
			id_sala=new String[nregistros];//Creamos un array del tamaño de nº de registros que tengamos
			
			registro = comando.executeQuery(sql);
			while(registro.next()) {
				fila[0] = indice+1;
				fila[1] = registro.getString("nombre_sala");
				
				fila[2] = registro.getString("usuario_sala");
				if(fila[2]==null) {
					fila[2]="";
				}
				
				fila[3] = registro.getString("curso_asignado_sala");
				if(fila[3]==null) {
					fila[3]="";
				}
				
				fila[4] = registro.getString("descripcion_sala");
				if(fila[4]==null) {
					fila[4]="";
				}
				
				fila[5] = registro.getString("nombre_edif");
				if(fila[5]==null) {
					fila[5]="";
				}
				
				fila[6] = registro.getString("nombre_tipo_sala");
				if(fila[6]==null) {
					fila[6]="";
				}
				
				id_sala[indice]=registro.getString("id_sala");
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
