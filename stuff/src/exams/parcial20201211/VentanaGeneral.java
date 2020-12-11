package exams.parcial20201211;

import java.awt.BorderLayout; 
import java.awt.Color;
import java.awt.List;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.*;

@SuppressWarnings("serial")
public class VentanaGeneral extends JFrame {
	
	private static final Logger LOGGER = Logger.getLogger(VentanaGeneral.class.getName()); //LOGGERS

	public static Color COLOR_GRIS_CLARITO = new Color(220, 220, 220);

	private JDesktopPane desktop;
	private JLabel lMensaje = new JLabel(" ");
	private JMenu menuVentanas;
	private JMenu menuAcciones;
	private ArrayList<JInternalFrame> misSubventanas;
	private Runnable runEnCierre;
	private JMenuItem itemBases;
	private JMenuItem itemOrdenarNombre;

	public VentanaGeneral(ArrayList<Tabla> tablas) {
		
		try {
			FileHandler fH = new FileHandler("LoggerFile.log", 8096, 1, false);
			for (Handler handler : LOGGER.getHandlers()) {
				LOGGER.removeHandler(handler);
			}
			SimpleFormatter formatter = new SimpleFormatter();  
			fH.setFormatter(formatter);
			LOGGER.addHandler(fH);


		} catch (Exception e) {
			e.printStackTrace();
		}

		LOGGER.log(Level.INFO, "Ventana general iniciada");
		
		
		
		
		misSubventanas = new ArrayList<>();
		// Configuración general
		setTitle("Ventana General");
		setSize(1200, 800); // Tamaño por defecto
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// Creación de componentes y contenedores
		desktop = new JDesktopPane();
		add(desktop, BorderLayout.CENTER);
		// setContentPane( desktop );
		lMensaje.setOpaque(true);
		add(lMensaje, BorderLayout.SOUTH);
		// Eventos
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				if (runEnCierre != null)
					runEnCierre.run();
			}
		});
		// Menú y eventos
		JMenuBar menuBar = new JMenuBar();
		menuVentanas = new JMenu("Ventanas");
		menuVentanas.setMnemonic(KeyEvent.VK_V);
		menuBar.add(menuVentanas);

		menuAcciones = new JMenu("Acciones");
		menuAcciones.setMnemonic(KeyEvent.VK_A);
		itemBases = new JMenuItem("Basear");
		menuAcciones.add(itemBases);

		try {

			Class.forName("org.sqlite.JDBC");

		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		itemBases.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int contador = 0;
				String nombreTabla = "";
				for (Tabla tabla : tablas) {

					try {
						Connection conn = DriverManager.getConnection("jdbc:sqlite:alumnitos.db");
						Statement stmt = (Statement) conn.createStatement();

						contador++;
						nombreTabla = "Tabla" + contador;

						String instruccionBorrar = "DROP TABLE IF EXISTS " + nombreTabla + ";";
						int rs = stmt.executeUpdate(instruccionBorrar);
						// System.out.println(instruccionBorrar);

						String instruccionCrear = "CREATE TABLE " + nombreTabla
								+ "(NOMBRE VARCHAR(25), CORREO VARCHAR(50), DURACION VARCHAR(10), HORAU VARCHAR(5), HORAS VARCHAR(5));";
						// System.out.println(instruccionCrear);
						int rs2 = stmt.executeUpdate(instruccionCrear);

						stmt.close();
						conn.close();

					} catch (SQLException e1) {
						e1.printStackTrace();
					}
						
					for (int i = 0; i < tabla.size(); i++) {
							
						try {
							Connection conn = DriverManager.getConnection("jdbc:sqlite:alumnitos.db");
							Statement stmt = (Statement) conn.createStatement();
							String instruccion = "INSERT INTO " + nombreTabla + " VALUES('" + tabla.get(i, 0) + "', '"
									+ tabla.get(i, 1) + "' , '" + tabla.get(i, 2) + "' , '" + tabla.get(i, 3) + "' , '"
									+ tabla.get(i, 4) + "');";
							//System.out.println(instruccion);

							int rs2 = stmt.executeUpdate(instruccion);

						} catch (SQLException e1) {
							e1.printStackTrace();
						}

					}
				}

			}
		});
		
		itemOrdenarNombre = new JMenuItem("Ordenar Nombre");
		itemOrdenarNombre.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for (Tabla tabla : tablas) {
					tabla.ordenaTablaString(0);
					repaint(); //TXALO PARA TY
				}
			
			}
		});
		menuAcciones.add(itemOrdenarNombre);
		
		
		
		
		
		menuBar.add(menuAcciones);

		setJMenuBar(menuBar);

	}

	public void setEnCierre(Runnable runnable) {
		runEnCierre = runnable;
	}

	private ActionListener alMenu = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			String vent = e.getActionCommand();
			for (JInternalFrame vd : misSubventanas)
				if (vd.getName().equals(vent)) {
					vd.setVisible(true);
					vd.moveToFront();
					return;
				}
		}
	};

	public void addVentanaInterna(JInternalFrame f, String codVentana) {
		desktop.add(f);
		JMenuItem menuItem = new JMenuItem(codVentana);
		menuItem.setActionCommand(codVentana);
		menuItem.addActionListener(alMenu);
		menuVentanas.add(menuItem);
		misSubventanas.add(f);
		f.setName(codVentana);
	}

	public void setMensaje(String mens, Color... colorFondo) {
		Color fondo = (colorFondo.length > 0) ? colorFondo[0] : COLOR_GRIS_CLARITO;
		if (mens == null || mens.isEmpty())
			mens = " ";
		lMensaje.setText(mens);
		lMensaje.setBackground(fondo);
	}

	public void setMensajeSinCambioColor(String mens) {
		if (mens == null || mens.isEmpty())
			mens = " ";
		lMensaje.setText(mens);
	}

	public void addMenuAccion(String textoMenu, ActionListener accion) {
		JMenuItem menuItem = new JMenuItem(textoMenu);
		menuItem.setActionCommand(textoMenu);
		menuItem.addActionListener(accion);
		menuAcciones.add(menuItem);
	}

	public ArrayList<JInternalFrame> getSubventanas() {
		return misSubventanas;
	}

	public JDesktopPane getJDesktopPane() {
		return desktop;
	}

}
