import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

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

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSeparator;



public class Incidencias extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel modelo;
	private Connection conexion;
	private JTable table;
	private JComboBox<Object> comboUbicacion;
	private JTextField txtid_incidencia;
	private String id_disp;
	private JDateChooser dateChooser;
	private JComboBox<String> comboBoxEstado;
	private String estado_disp;
	private String estado_nuevo;
	private String sala_nueva;
	private JTextPane textPaneSolucion;
	/**
	 *
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Incidencias frame = new Incidencias();
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
	public Incidencias() {
		setTitle("Incidencias Pendientes");
		
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
		modelo.addColumn("Nº Incidencia");
		modelo.addColumn("Fecha Apertura");
		modelo.addColumn("Fecha Cierre");
		modelo.addColumn("Solución");
		modelo.addColumn("Descripción");
		modelo.addColumn("Identificador");
		modelo.addColumn("Número de Serie");
		modelo.addColumn("Ubicación");

		
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
		JTableHeader header = table.getTableHeader();
	    header.setBackground(Color.ORANGE);
	    header.setFont(new Font("Roboto", Font.BOLD, 13));
		table.setGridColor(Color.GRAY);
		table.setForeground(Color.BLACK);
		table.setFont(new Font("Arial", Font.PLAIN, 12));
		table.setBackground(Color.white);
		table.setGridColor(Color.black);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = table.rowAtPoint(e.getPoint());
				int columna = table.columnAtPoint(e.getPoint());
				
				
				if (columna==0) {
					txtid_incidencia.setText((String) modelo.getValueAt(fila, columna));
					comboUbicacion.setSelectedItem(modelo.getValueAt(fila, columna+7));
				} else if(columna==1){
					txtid_incidencia.setText((String) modelo.getValueAt(fila, columna-1));
					comboUbicacion.setSelectedItem(modelo.getValueAt(fila, columna+6));
				}else if(columna==2) {
					txtid_incidencia.setText((String) modelo.getValueAt(fila, columna-2));
					comboUbicacion.setSelectedItem(modelo.getValueAt(fila, columna+5));
				}else if (columna==3) {
					txtid_incidencia.setText((String) modelo.getValueAt(fila, columna-3));
					comboUbicacion.setSelectedItem(modelo.getValueAt(fila, columna+4));
				}else if (columna==4) {
					txtid_incidencia.setText((String) modelo.getValueAt(fila, columna-4));
					comboUbicacion.setSelectedItem(modelo.getValueAt(fila, columna+3));
				}else if(columna==5) {
					txtid_incidencia.setText((String) modelo.getValueAt(fila, columna-5));
					comboUbicacion.setSelectedItem(modelo.getValueAt(fila, columna+2));
				}else if (columna==6) {
					txtid_incidencia.setText((String) modelo.getValueAt(fila, columna-6));
					comboUbicacion.setSelectedItem(modelo.getValueAt(fila, columna+1));
				}else if (columna==7) {
					txtid_incidencia.setText((String) modelo.getValueAt(fila, columna-7));
					comboUbicacion.setSelectedItem(modelo.getValueAt(fila, columna));
				}
				buscaDispositivo(txtid_incidencia.getText().toString());
				buscaEstado();
				comboBoxEstado.setSelectedItem(estado_disp);
				
				
				
				
			}
		});
		table.setFillsViewportHeight(true);
		table.setBackground(Color.WHITE);
		table.setModel(modelo);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(panel, GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
									.addGap(10))
								.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE))
							.addGap(0))))
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JLabel lblSolucin = new JLabel("Soluci\u00F3n: ");
		
		textPaneSolucion = new JTextPane();
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.ORANGE);
		separator.setBackground(Color.ORANGE);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(151)
					.addComponent(lblSolucin, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addGap(226)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(separator, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
						.addComponent(textPaneSolucion, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE))
					.addGap(130))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(24)
							.addComponent(textPaneSolucion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(30)
							.addComponent(lblSolucin)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(13, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.setBorder(null);
		btnInsertar.setBackground(Color.ORANGE);
		
				btnInsertar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							//FORMATEAR FECHA
							SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
							String Fecha_correcta=formateador.format(dateChooser.getDate());
							
							//debugger System.out.println(Fecha_correcta);
							if(textPaneSolucion.getText().toString().length()>255) {
								JOptionPane.showMessageDialog(null, "Solución demasiado extensa");
							}
							// CERRAR INCIDENCIA
								//1. Buscar id_estado nuevo
								id_estado();
								//2. Buscar id_ubicacion nueva
								id_ubicacion();
								//3. Cerrar la anterior ubicación y el anterior estado.
								update_Estado_Ubicacion(Fecha_correcta);
								//4 Insertar el nuevo estado
								insert_Estado(Fecha_correcta);
								//5. Insertar la nueva ubicación;
								insert_Ubicacion(Fecha_correcta);
								//6. Cerrar Incidencia
								cierre_Incidencia(Fecha_correcta);

							//Rellenar tabla de nuevo
								limpiar();
								
							
							
						} catch (Exception e) {
							System.out.println(e.getMessage().toString());
							e.printStackTrace();
						}
						
					}
				});
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBorder(null);
		btnLimpiar.setBackground(Color.ORANGE);
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textPaneSolucion.setText("");
				txtid_incidencia.setText("");
				comboUbicacion.setSelectedIndex(0);
				comboBoxEstado.setSelectedIndex(0);
			}
		});
		JButton btnVerDetalles = new JButton("Ver Detalles");
		btnVerDetalles.setBorder(null);
		btnVerDetalles.setBackground(Color.ORANGE);
		btnVerDetalles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id_inci=txtid_incidencia.getText().toString();
				if(id_inci.equals("")) {
					Detalles_incidencias verDetalles = new Detalles_incidencias();
					verDetalles.setVisible(true);
				}else {
					Detalles_incidencias verDetalles = new Detalles_incidencias(id_inci);
					verDetalles.setVisible(true);
					
				}
				
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addGap(121)
					.addComponent(btnInsertar, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
					.addGap(5)
					.addComponent(btnLimpiar, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnVerDetalles, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
					.addGap(124))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(btnInsertar, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnLimpiar, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnVerDetalles, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)))
					.addGap(25))
		);
		panel_1.setLayout(gl_panel_1);
		contentPane.setLayout(gl_contentPane);
		
		txtid_incidencia = new JTextField();
		txtid_incidencia.setEnabled(false);
		txtid_incidencia.setColumns(10);
		txtid_incidencia.setVisible(false);
		
		JLabel lblFechaDeCierre = new JLabel("Fecha de Cierre");
		
		dateChooser = new JDateChooser();
				
		JLabel lblNuevaUbicacin = new JLabel("Nueva Ubicaci\u00F3n");
		lblNuevaUbicacin.setHorizontalAlignment(SwingConstants.CENTER);
		
		comboUbicacion = new JComboBox<Object>();
		
		JLabel lblNuevoEstado = new JLabel("Nuevo Estado:");
		
		comboBoxEstado = new JComboBox<String>();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFechaDeCierre, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
						.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(119)
							.addComponent(txtid_incidencia, GroupLayout.PREFERRED_SIZE, 6, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(159)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNuevaUbicacin)
								.addComponent(lblNuevoEstado))
							.addGap(23)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBoxEstado, Alignment.TRAILING, 0, 166, Short.MAX_VALUE)
								.addComponent(comboUbicacion, Alignment.TRAILING, 0, 166, Short.MAX_VALUE))))
					.addGap(146))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblFechaDeCierre, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
								.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE))
							.addGap(25)
							.addComponent(txtid_incidencia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(45))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboUbicacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNuevaUbicacin))
							.addGap(18)))
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(comboBoxEstado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNuevoEstado)))
		);
		panel.setLayout(gl_panel);
		cargarComboUbicacion();
		cargarComboEstado();
		

	}
	
	public void rellenarTabla() {
		

		try {
			
			Object[] fila=new Object[8]; //Creamos un objeto con tantos parametros como datos retorne cada fila.
			
			String sql = "select incidencia.*, dispositivo.identificador_disp, dispositivo.sn_disp, sala.nombre_sala FROM incidencia, dispositivo, sala, ubicacion_dispositivo WHERE (fecha_cierre_inc is null) && (incidencia.id_disp=dispositivo.id_disp) && (sala.id_sala=ubicacion_dispositivo.id_sala) && (dispositivo.id_disp=ubicacion_dispositivo.id_disp) && (ubicacion_dispositivo.fecha_salida_disp_sala is NULL);";
			
			Statement comando = conexion.createStatement();
			ResultSet registro = comando.executeQuery(sql);


			int i=0;
			while(registro.next()) {
				
				fila[0] = registro.getString("numero_inc");
				fila[1] = registro.getString("fecha_apertura_inc");
				fila[2] = registro.getString("fecha_cierre_inc");
				fila[3] = registro.getString("solucion_inc");
				fila[4] = registro.getString("descripcion_inc");
				fila[5] = registro.getString("identificador_disp");	
				fila[6] = registro.getString("sn_disp");	
				fila[7] = registro.getString("nombre_sala");	
				
				if(fila[i]==null) {
					fila[i]=" ";
				}
				
				modelo.addRow(fila);
				i++;
			}
			
			
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}		
	}
	
	public void cargarComboUbicacion(){
		try {
			String sql = "Select `nombre_sala` from `sala`;";
			
			Statement comando = conexion.createStatement();
			ResultSet registro = comando.executeQuery(sql);
			registro = comando.executeQuery(sql);
			
			while(registro.next()) {
				comboUbicacion.addItem(registro.getString("nombre_sala"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
		
		
	}
	public void cargarComboEstado() {
		String sql ="Select nombre_estado from `estado`;";
		
		try {
			
			Statement comando = conexion.createStatement();
			ResultSet registro = comando.executeQuery(sql);
			registro = comando.executeQuery(sql);
			
			while(registro.next()) {
				comboBoxEstado.addItem(registro.getString("nombre_estado"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
	}
	public void buscaDispositivo(String id_inci) {
		String sql ="SELECT id_disp from incidencia WHERE numero_inc='"+id_inci+"';";
		try {
			
			Statement comando = conexion.createStatement();
			ResultSet registro = comando.executeQuery(sql);
			registro = comando.executeQuery(sql);
			
			while(registro.next()) {
				id_disp=registro.getString("id_disp");
				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
		
		
	}
	public void buscaEstado() {
		String sql ="SELECT estado.nombre_estado FROM estado, estado_dispositivo WHERE (estado_dispositivo.id_disp='"+id_disp+"') && (estado_dispositivo.fecha_fin_est_disp is null) && (estado.id_estado=estado_dispositivo.id_estado);";
		try {
			
			Statement comando = conexion.createStatement();
			ResultSet registro = comando.executeQuery(sql);
			registro = comando.executeQuery(sql);
			
			while(registro.next()) {
				estado_disp=registro.getString("nombre_estado").toString();
				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
	}
	
	public void id_estado() {
		estado_nuevo= comboBoxEstado.getSelectedItem().toString();
		
		String sql ="SELECT id_estado from estado WHERE nombre_estado='"+estado_nuevo+"';";
		
try {
			
			Statement comando = conexion.createStatement();
			ResultSet registro = comando.executeQuery(sql);
			registro = comando.executeQuery(sql);
			
			while(registro.next()) {
				estado_nuevo=registro.getString("id_estado").toString();
				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
	}
	public void id_ubicacion() {
		sala_nueva= comboUbicacion.getSelectedItem().toString();
		
		String sql ="SELECT id_sala from sala WHERE nombre_sala='"+sala_nueva+"';";
		
		try {
			
			Statement comando = conexion.createStatement();
			ResultSet registro = comando.executeQuery(sql);
			registro = comando.executeQuery(sql);
			
			while(registro.next()) {
				sala_nueva=registro.getString("id_sala").toString();
				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
	}
	
	public void update_Estado_Ubicacion(String Fecha_correcta) {
		
		String sql="UPDATE estado_dispositivo "
				+ "INNER JOIN ubicacion_dispositivo "
				+ "ON estado_dispositivo.id_disp=ubicacion_dispositivo.id_disp "
				+ "SET estado_dispositivo.fecha_fin_est_disp='"+Fecha_correcta+"', "
						+ "ubicacion_dispositivo.fecha_salida_disp_sala='"+Fecha_correcta+"'"
				+ "WHERE (estado_dispositivo.id_disp='"+id_disp+"') && (estado_dispositivo.fecha_fin_est_disp is null) && (ubicacion_dispositivo.fecha_salida_disp_sala is null);";
		try {
			
			Statement comando = conexion.createStatement();
			comando.executeUpdate(sql);
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage().toString()+"Error update_estado_ubicacion");
		}
	}
	public void insert_Estado(String Fecha_correcta) {
		String sql="INSERT into estado_dispositivo(id_disp, id_estado, fecha_inicio_est_disp) VALUES ('"+id_disp+"', '"+estado_nuevo+"','"+Fecha_correcta+"') ";
		try {
			
			Statement comando = conexion.createStatement();
			comando.executeUpdate(sql);
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage().toString()+ "Error insert_Estado");
		}
	}
	public void insert_Ubicacion(String Fecha_correcta) {
		String sql="INSERT into ubicacion_dispositivo(id_disp, id_sala, fecha_entrada_disp_sala) VALUES ('"+id_disp+"', '"+sala_nueva+"','"+Fecha_correcta+"') ";
		try {
			
			Statement comando = conexion.createStatement();
			comando.executeUpdate(sql);
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage().toString()+ "Error insert_ubicacion");
		}
	}
	public void cierre_Incidencia(String Fecha_correcta) {
		String sql="UPDATE incidencia SET fecha_cierre_inc='"+Fecha_correcta+"', solucion_inc='"+textPaneSolucion.getText().toString()+"' where numero_inc='"+txtid_incidencia.getText().toString()+"';";
		try {
			
			Statement comando = conexion.createStatement();
			comando.executeUpdate(sql);
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage().toString()+"Error cierre");
		}
	}
	protected static void cargarDriver() {
		try {
			Class.forName("com.msqly.jdbc.Driver");
		} catch (Exception ex) {
			// setTitle(ex.toString());
		}
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
		txtid_incidencia.setText("");
		textPaneSolucion.setText("");
		//**FIN limpiar
		
	}
}
