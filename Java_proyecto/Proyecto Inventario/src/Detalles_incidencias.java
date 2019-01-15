import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

public class Detalles_incidencias extends JFrame  implements ActionListener{

	private JPanel contentPane;
	private JTextField textApertura;
	private Connection conexion;
	private JComboBox<Object> comboBox;
	private JTextArea textAreaSolucion;
	private JTextArea textAreaDescripcion;
	private String id_sala;
	private String numero_inc;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Detalles_incidencias frame = new Detalles_incidencias();
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
	public Detalles_incidencias() {
		cargarDriver();
		
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/qzr440","root","");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Ha sido imposible conectar a la base de datos");
			JOptionPane.showMessageDialog(this, e.getMessage().toString());
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 619, 447);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		JLabel lblModificacinIncidenciaDispositivo = new JLabel("Modificaci\u00F3n Incidencia Dispositivo: ");
		lblModificacinIncidenciaDispositivo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblModificacinIncidenciaDispositivo.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(37)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
					.addGap(41))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(144)
					.addComponent(lblModificacinIncidenciaDispositivo, GroupLayout.PREFERRED_SIZE, 11, Short.MAX_VALUE)
					.addGap(128))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblModificacinIncidenciaDispositivo, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
					.addContainerGap())
		);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 225, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel = new JLabel("Fecha Apertura: ");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		textApertura = new JTextField();
		textApertura.setBackground(Color.LIGHT_GRAY);
		textApertura.setEnabled(false);
		GridBagConstraints gbc_textApertura = new GridBagConstraints();
		gbc_textApertura.fill = GridBagConstraints.HORIZONTAL;
		gbc_textApertura.insets = new Insets(0, 0, 5, 5);
		gbc_textApertura.gridx = 2;
		gbc_textApertura.gridy = 1;
		panel.add(textApertura, gbc_textApertura);
		textApertura.setColumns(10);
		
		JLabel lblUbicacin = new JLabel("Ubicaci\u00F3n:");
		GridBagConstraints gbc_lblUbicacin = new GridBagConstraints();
		gbc_lblUbicacin.insets = new Insets(0, 0, 5, 5);
		gbc_lblUbicacin.anchor = GridBagConstraints.EAST;
		gbc_lblUbicacin.gridx = 1;
		gbc_lblUbicacin.gridy = 3;
		panel.add(lblUbicacin, gbc_lblUbicacin);
		
		JComboBox<Object> comboBox = new JComboBox<Object>();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 3;
		panel.add(comboBox, gbc_comboBox);
		
		JLabel lblSolucin = new JLabel("Soluci\u00F3n:");
		GridBagConstraints gbc_lblSolucin = new GridBagConstraints();
		gbc_lblSolucin.insets = new Insets(0, 0, 5, 5);
		gbc_lblSolucin.gridx = 1;
		gbc_lblSolucin.gridy = 4;
		panel.add(lblSolucin, gbc_lblSolucin);
		
		textAreaSolucion = new JTextArea();
		textAreaSolucion.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_textAreaSolucion = new GridBagConstraints();
		gbc_textAreaSolucion.insets = new Insets(0, 0, 5, 5);
		gbc_textAreaSolucion.fill = GridBagConstraints.BOTH;
		gbc_textAreaSolucion.gridx = 2;
		gbc_textAreaSolucion.gridy = 4;
		panel.add(textAreaSolucion, gbc_textAreaSolucion);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n");
		GridBagConstraints gbc_lblDescripcin = new GridBagConstraints();
		gbc_lblDescripcin.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcin.gridx = 1;
		gbc_lblDescripcin.gridy = 5;
		panel.add(lblDescripcin, gbc_lblDescripcin);
		
		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_textAreaDescripcion = new GridBagConstraints();
		gbc_textAreaDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_textAreaDescripcion.fill = GridBagConstraints.BOTH;
		gbc_textAreaDescripcion.gridx = 2;
		gbc_textAreaDescripcion.gridy = 5;
		panel.add(textAreaDescripcion, gbc_textAreaDescripcion);
		
		JButton btnModificar = new JButton("Modificar");
		GridBagConstraints gbc_btnModificar = new GridBagConstraints();
		gbc_btnModificar.insets = new Insets(0, 0, 0, 5);
		gbc_btnModificar.gridx = 2;
		gbc_btnModificar.gridy = 6;
		panel.add(btnModificar, gbc_btnModificar);
		contentPane.setLayout(gl_contentPane);
		btnModificar.setEnabled(false);
	}
	public Detalles_incidencias( String id_inci) {
		numero_inc=id_inci;
		cargarDriver();
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/qzr440","root","");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Ha sido imposible conectar a la base de datos");
			JOptionPane.showMessageDialog(this, e.getMessage().toString());
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 619, 447);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		JLabel lblModificacinIncidenciaDispositivo = new JLabel("Modificaci\u00F3n Incidencia Dispositivo: ");
		lblModificacinIncidenciaDispositivo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblModificacinIncidenciaDispositivo.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(37)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
					.addGap(41))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(144)
					.addComponent(lblModificacinIncidenciaDispositivo, GroupLayout.PREFERRED_SIZE, 11, Short.MAX_VALUE)
					.addGap(128))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblModificacinIncidenciaDispositivo, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
					.addContainerGap())
		);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 225, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel = new JLabel("Fecha Apertura: ");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		textApertura = new JTextField();
		textApertura.setEnabled(false);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		panel.add(textApertura, gbc_textField);
		textApertura.setColumns(10);
		
		
		JLabel lblUbicacin = new JLabel("Ubicaci\u00F3n:");
		GridBagConstraints gbc_lblUbicacin = new GridBagConstraints();
		gbc_lblUbicacin.insets = new Insets(0, 0, 5, 5);
		gbc_lblUbicacin.anchor = GridBagConstraints.EAST;
		gbc_lblUbicacin.gridx = 1;
		gbc_lblUbicacin.gridy = 3;
		panel.add(lblUbicacin, gbc_lblUbicacin);
		
		comboBox = new JComboBox<Object>();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 3;
		panel.add(comboBox, gbc_comboBox);
		comboBox.setEnabled(false);
		
		JLabel lblSolucin = new JLabel("Soluci\u00F3n:");
		GridBagConstraints gbc_lblSolucin = new GridBagConstraints();
		
		gbc_lblSolucin.insets = new Insets(0, 0, 5, 5);
		gbc_lblSolucin.gridx = 1;
		gbc_lblSolucin.gridy = 4;
		panel.add(lblSolucin, gbc_lblSolucin);
		
		textAreaSolucion = new JTextArea();
		GridBagConstraints gbc_textAreaSolucion = new GridBagConstraints();
		textAreaSolucion.setBackground(Color.LIGHT_GRAY);
		gbc_textAreaSolucion.insets = new Insets(0, 0, 5, 5);
		gbc_textAreaSolucion.fill = GridBagConstraints.BOTH;
		gbc_textAreaSolucion.gridx = 2;
		gbc_textAreaSolucion.gridy = 4;
		panel.add(textAreaSolucion, gbc_textAreaSolucion);
		textAreaSolucion.setEditable(true);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n");
		GridBagConstraints gbc_lblDescripcin = new GridBagConstraints();
		gbc_lblDescripcin.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcin.gridx = 1;
		gbc_lblDescripcin.gridy = 5;
		panel.add(lblDescripcin, gbc_lblDescripcin);
		

		textAreaDescripcion = new JTextArea();
		GridBagConstraints gbc_textAreaDescripcion = new GridBagConstraints();
		textAreaDescripcion.setBackground(Color.LIGHT_GRAY);
		gbc_textAreaDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_textAreaDescripcion.fill = GridBagConstraints.BOTH;
		gbc_textAreaDescripcion.gridx = 2;
		gbc_textAreaDescripcion.gridy = 5;
		panel.add(textAreaDescripcion, gbc_textAreaDescripcion);
		textAreaDescripcion.setEditable(true);
		
		
		JButton btnModificar = new JButton("Modificar");
		GridBagConstraints gbc_btnModificar = new GridBagConstraints();
		gbc_btnModificar.insets = new Insets(0, 0, 0, 5);
		gbc_btnModificar.gridx = 2;
		gbc_btnModificar.gridy = 6;
		panel.add(btnModificar, gbc_btnModificar);
		btnModificar.addActionListener(this);
		
		
		
		
		contentPane.setLayout(gl_contentPane);
		rellenaDatos(id_inci);
		cargarComboUbicacion();
	}
	
	public void rellenaDatos(String id_inci) {
		try {
			//System.out.println("-"+id_inci+"-");
			//rellenar datos de incidencia
			String sql = "select incidencia.*, dispositivo.identificador_disp, dispositivo.sn_disp, sala.id_sala ,sala.nombre_sala FROM incidencia, dispositivo, sala, ubicacion_dispositivo WHERE (incidencia.numero_inc='"+id_inci+"') && (incidencia.id_disp=dispositivo.id_disp) && (sala.id_sala=ubicacion_dispositivo.id_sala) && (dispositivo.id_disp=ubicacion_dispositivo.id_disp) && (ubicacion_dispositivo.fecha_salida_disp_sala is NULL);";
			
			Statement comando = conexion.createStatement();
			ResultSet registro = comando.executeQuery(sql);
			//registro = comando.executeQuery(sql);
			
			while(registro.next()) {
				
				
					textApertura.setText(registro.getString("fecha_apertura_inc"));
					textAreaDescripcion.setText(registro.getString("descripcion_inc"));
					textAreaSolucion.setText(registro.getString("solucion_inc"));
					//System.out.println(4);
					comboBox.addItem(registro.getString("nombre_sala")+" (ACTUAL)");
					id_sala=registro.getString("id_sala");
					
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
				comboBox.addItem(registro.getString("nombre_sala"));
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



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stu
		System.out.println(e.getActionCommand().toString());
		if(e.getActionCommand().toString().equals("Modificar")) {
			try {
				//ACTUALIZAR INCIDENCIA, UBICACIÓN DISPOSITIVO
				if (textAreaDescripcion.getText().toString().length()<250 && textAreaSolucion.getText().toString().length()<250) {
					String sql = "UPDATE incidencia SET descripcion_inc='"+textAreaDescripcion.getText().toString()+"', solucion_inc='"+textAreaSolucion.getText().toString()+"' WHERE numero_inc='"+numero_inc+"';";
					Statement comando = conexion.createStatement();
					comando.executeUpdate(sql);
					System.out.println("PASA");
				} else {
					JOptionPane.showMessageDialog(null, "Descripción y/o Solución demasiado larga.");
				}
				
			} catch (Exception e2) {
				// TODO: handle exception
				System.out.println(e2.getMessage().toString());
			}
		}
		
	}
}
