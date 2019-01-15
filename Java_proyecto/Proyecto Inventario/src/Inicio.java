
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Toolkit;
import java.awt.Window.Type;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Dimension;
import javax.swing.JSeparator;

public class Inicio extends JFrame{

	private String opcion;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnAbrir;
	private JMenuItem mntmDispositivo;
	private JMenuItem mntmFamilia; 
	private JMenuItem mntmUsuario;
	private JMenu mnUbicacion;
	private JMenuItem mntmEdificio;
	private JMenuItem mntmSala;
	private JMenuItem mntmTipoSala;
	private JMenuItem mntmProveedor;
	private JMenuItem mntmMarca;
	private JMenuItem mntmIncidencia;
	private JMenuItem mntmEstado;
	private JMenuItem mntmProducto;
	private JMenuItem mntmOrigen;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
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
	public Inicio() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("Assets\\logo_ventana.png"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 335);
		
		menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setBackground(Color.WHITE);
		setJMenuBar(menuBar);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("");
		mntmNewMenuItem.setPreferredSize(new Dimension(0, 13));
		mntmNewMenuItem.setBackground(Color.WHITE);
		menuBar.add(mntmNewMenuItem);
		
		mnAbrir = new JMenu("Mantenimiento");
		mnAbrir.setBackground(Color.WHITE);
		menuBar.add(mnAbrir);
		
		mntmProducto = new JMenuItem("Producto");
		mntmProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Producto producto = new Producto();
				producto.setVisible(true);
			}
		});
		mnAbrir.add(mntmProducto);
		
		

		
		mntmFamilia = new JMenuItem("Familia");
		mntmFamilia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Familia familia = new Familia();
				familia.setVisible(true);
			}
		});
		
		mnAbrir.add(mntmFamilia);
		
		
		mntmUsuario = new JMenuItem("Usuario");
		mntmUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuarios usuario = new Usuarios();
				
				usuario.setVisible(true);
			}
		});
		mnAbrir.add(mntmUsuario);
		
		
		mnUbicacion = new JMenu("Ubicacion");
		mnAbrir.add(mnUbicacion);
		
		mntmEdificio = new JMenuItem("Edificio");
		mntmEdificio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Edificio edificio = new Edificio();
				
				edificio.setVisible(true);
			}
		});
		mnUbicacion.add(mntmEdificio);
		
		
		mntmSala = new JMenuItem("Sala");
		mntmSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sala sala=new Sala();
				sala.setVisible(true);
			}
		});
		mnUbicacion.add(mntmSala);
		
		
		mntmTipoSala = new JMenuItem("Tipo Sala");
		mntmTipoSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tipo_sala tipo_sala = new Tipo_sala();
				tipo_sala.setVisible(true);
			}
		});
		mnUbicacion.add(mntmTipoSala);
		
		
		mntmProveedor = new JMenuItem("Proveedor");
		mntmProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Proveedor proveedor = new Proveedor();
				proveedor.setVisible(true);
			}
		});
		mnAbrir.add(mntmProveedor);
		
		
		mntmMarca = new JMenuItem("Marca");
		mntmMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Marca marca = new Marca();
				marca.setVisible(true);
			}
		});
		mnAbrir.add(mntmMarca);
		
		mntmOrigen = new JMenuItem("Origen");
		mntmOrigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Origen origen = new Origen();
				origen.setVisible(true);
			}
		});
		mnAbrir.add(mntmOrigen);
		
		mntmIncidencia = new JMenuItem("Incidencia");
		mntmIncidencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Incidencias incidencia = new Incidencias();
				incidencia.setVisible(true);
			}
		});
		mnAbrir.add(mntmIncidencia);
		
		
		mntmEstado = new JMenuItem("Estado");
		mntmEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Estado estado=new Estado();
				estado.setVisible(true);
			}
		});
		mnAbrir.add(mntmEstado);
		
		mntmDispositivo = new JMenuItem("Dispositivo");
		mntmDispositivo.setBackground(Color.WHITE);
		menuBar.add(mntmDispositivo);
		mntmDispositivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dispositivo dispositivo = new Dispositivo();
				dispositivo.setVisible(true);
			}
		});
		
		
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setBackground(Color.WHITE);
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		String path1 = "media/logo.png";
		URL url1 = this.getClass().getResource(path1);
		ImageIcon icon1 = new ImageIcon(url1);
		lblLogo.setIcon(icon1);
		lblLogo.setBounds(0, 0, 344, 289);
		contentPane.add(lblLogo);
				
	}
}
