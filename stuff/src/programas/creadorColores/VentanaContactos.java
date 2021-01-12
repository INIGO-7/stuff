package programas.creadorColores;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

public class VentanaContactos extends JFrame {
	private JPanel panelNombre = new JPanel();
	private JTextField textoNombre = new JTextField();
	private JPanel panelTelefono = new JPanel();
	private JTextField textoTelefono = new JTextField();
	private JPanel panelMail = new JPanel();
	private JTextField textoMail= new JTextField();
	private JPanel panelApellido = new JPanel();
	private JTextField textoApellido = new JTextField();
	private JPanel panelTwitter = new JPanel();
	private JTextField textoTwitter = new JTextField();
	private JPanel panelInstagram = new JPanel();
	private JTextField textoInstagram = new JTextField();
	private JButton grabar = new JButton("Grabar");
	private JButton borrar = new JButton("Borrar");
	private JButton siguiente = new JButton("Siguiente");
	private JButton anterior = new JButton("Anterior");
	private JMenuBar menu = new JMenuBar();
	private JMenu fichero = new JMenu("Fichero");
	private JMenuItem salir = new JMenuItem("Salir");
	private static Contacto contactoActual = new Contacto();
	private static int pagina = 0;
	private int paginasTotales = 5;
	private Thread hilo = new Thread();
	private static HashMap<Integer, Contacto> hashContactos = new HashMap<>();
	
	
	
	public VentanaContactos() {
		setTitle("Menu");
		setSize(310,550);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(11,1));
		
		salir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		fichero.add(salir);
		menu.add(fichero);
		add(menu);
		
		
		panelNombre.add(new JLabel("Nombre: "));
		textoNombre.setPreferredSize(new Dimension(200, 25));
		panelNombre.add(textoNombre);
		add(panelNombre);
		
		panelApellido.add(new JLabel("Apellido: "));
		textoApellido.setPreferredSize(new Dimension(200, 25));
		panelApellido.add(textoApellido);
		add(panelApellido);
		
		panelTelefono.add(new JLabel("Teléfono: "));
		panelTelefono.add(textoTelefono);
		textoTelefono.setPreferredSize(new Dimension(200, 25));
		add(panelTelefono);
		
		panelMail.add(new JLabel("e-mail: "));
		panelMail.add(textoMail);
		textoMail.setPreferredSize(new Dimension(200, 25));
		add(panelMail);
		
		panelTwitter.add(new JLabel("Twitter: "));
		panelTwitter.add(textoTwitter);
		textoTwitter.setPreferredSize(new Dimension(200, 25));
		add(panelTwitter);
		
		panelInstagram.add(new JLabel("Instagram: "));
		textoInstagram.setPreferredSize(new Dimension(200, 25));
		panelInstagram.add(textoInstagram);
		add(panelInstagram);
		
		add(grabar);
		add(anterior);
		add(siguiente);
		add(borrar);	
		
		
		
		for (int i = 0; i < paginasTotales; i++) {
			Contacto c = new Contacto();
			hashContactos.put(i, c);
		}
		
		siguiente.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(pagina < paginasTotales - 1) {
					pagina++;
				}
				contactoActual = hashContactos.get(pagina);
				
				
				textoNombre.setText(contactoActual.getNombre());
				textoApellido.setText(contactoActual.getApellido());
				textoTelefono.setText(contactoActual.getTelefono());
				textoMail.setText(contactoActual.getMail());
				textoTwitter.setText(contactoActual.getTwitter());
				textoInstagram.setText(contactoActual.getInstagram());
				repaint();
				validate();
				
			}
		});
		
		
		anterior.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(pagina > 0) {
					pagina--;
				}
				contactoActual = hashContactos.get(pagina);
				
				textoNombre.setText(contactoActual.getNombre());
				textoApellido.setText(contactoActual.getApellido());
				textoTelefono.setText(contactoActual.getTelefono());
				textoMail.setText(contactoActual.getMail());
				textoTwitter.setText(contactoActual.getTwitter());
				textoInstagram.setText(contactoActual.getInstagram());
				repaint();
				validate();
				
			}
		});
		
		
		grabar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				contactoActual.setNombre(textoNombre.getText());
				contactoActual.setApellido(textoApellido.getText());
				contactoActual.setTelefono(textoTelefono.getText());
				contactoActual.setMail(textoMail.getText());
				contactoActual.setTwitter(textoTwitter.getText());
				contactoActual.setInstagram(textoInstagram.getText());	
				
				hashContactos.put(pagina, contactoActual);
			}
		});
		
		
		borrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				contactoActual = new Contacto();
				
				contactoActual.setNombre("");
				contactoActual.setApellido("");
				contactoActual.setTelefono("");
				contactoActual.setMail("");
				contactoActual.setTwitter("");
				contactoActual.setInstagram("");	
				
				hashContactos.put(pagina, contactoActual);
				
				textoNombre.setText(contactoActual.getNombre());
				textoApellido.setText(contactoActual.getApellido());
				textoTelefono.setText(contactoActual.getTelefono());
				textoMail.setText(contactoActual.getMail());
				textoTwitter.setText(contactoActual.getTwitter());
				textoInstagram.setText(contactoActual.getInstagram());
				repaint();
				validate();
				
			}
		});
		
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new VentanaContactos();
	
	}
}