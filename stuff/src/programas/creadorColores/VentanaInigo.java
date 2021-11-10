package programas.creadorColores;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;

public class VentanaInigo extends JFrame{

	//as this is done without threads, and we want to modify the slider when the spinner value is changed,
	//and viceversa, we have to check the previous values of both.
			
	private int previousRedVal = 0;
	private int previousBlueVal = 0;
	private int previousGreenVal = 0;
	
	public VentanaInigo() {
		
		JPanel izquierda = new JPanel(new GridLayout(2, 1));
		JPanel derecha = new JPanel(new BorderLayout());
		
		Color color = new Color(0, 0, 0);
		
		DefaultListModel<Color> dm = new DefaultListModel<Color>();
		JList<Color> listaColores = new JList<Color>(dm);
		
		JScrollPane scrollColores = new JScrollPane(listaColores);
		
		scrollColores.setPreferredSize(new Dimension(200, 200));
		
		JMenuBar barra = new JMenuBar();
		JMenu menu = new JMenu("menu de colores");
		JMenuItem rojo = new JMenuItem("rojo");
		JMenuItem verde = new JMenuItem("verde");
		JMenuItem azul = new JMenuItem("azul");
		
		menu.add(rojo);
		menu.add(verde);
		menu.add(azul);
		barra.add(menu);
		
		JLabel lRojo = new JLabel("rojo: ");
		JLabel lVerde = new JLabel("verde: ");
		JLabel lAzul = new JLabel("azul: ");
		
		JSpinner spinRojo = new JSpinner();
		JSpinner spinVerde = new JSpinner();
		JSpinner spinAzul = new JSpinner();
		
		JSlider slideRojo = new JSlider(0,255);
		JSlider slideVerde = new JSlider(0,255);
		JSlider slideAzul = new JSlider(0,255);
		
		slideRojo.setValue((int) spinRojo.getValue());
		slideVerde.setValue((int) spinVerde.getValue());
		slideAzul.setValue((int) spinAzul.getValue());
		
		JPanel ensenyarColor = new JPanel();
		JPanel cambiarColor = new JPanel(new GridLayout(4, 1));
		JPanel todosColores = new JPanel();
		
		JButton eliminarColor = new JButton("eliminar");
		eliminarColor.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int index = listaColores.getSelectedIndex();
				try {
					dm.removeElementAt(index);
				}catch (Exception ex) {
					System.out.println("array vac�o");
				}
			}
			
		});
		JButton actualizar = new JButton("Actualizar color");
		actualizar.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent arg0) {
				
				int r, g, b;
				
				if((int)spinRojo.getValue() != previousRedVal) {
					r = (int) spinRojo.getValue();
					slideRojo.setValue(r);
				}else {
					r = slideRojo.getValue();
					spinRojo.setValue(r);
				}
				
				if((int)spinVerde.getValue() != previousGreenVal) {
					g = (int) spinVerde.getValue();
					slideVerde.setValue(g);
				}else {
					g = slideVerde.getValue();
					spinVerde.setValue(g);
				}
				
				if((int)spinAzul.getValue() != previousBlueVal) {
					b = (int) spinAzul.getValue();
					slideAzul.setValue(b);
				}else {
					b = slideAzul.getValue();
					spinAzul.setValue(b);
				}
				
				previousRedVal = r; previousGreenVal = g; previousBlueVal = b;
				
				ensenyarColor.setBackground(new Color(r, g, b));
				
				Color anyadoColor = new Color(r, g, b);
				
				dm.addElement(anyadoColor);
				
			}
			
		});
		
		rojo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				ensenyarColor.setBackground(new Color(255, 0, 0));
				
				Color anyadoColor = new Color(255, 0, 0);
				
				dm.addElement(anyadoColor);
				
				slideRojo.setValue(255);
				slideVerde.setValue(0);
				slideAzul.setValue(0);
				
				spinRojo.setValue(255);
				spinVerde.setValue(0);
				spinAzul.setValue(0);
				
			}
			
		});
		
		verde.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				ensenyarColor.setBackground(new Color(0, 255, 0));
				
				Color anyadoColor = new Color(0, 255, 0);
				
				dm.addElement(anyadoColor);
				
				slideRojo.setValue(0);
				slideVerde.setValue(255);
				slideAzul.setValue(0);
				
				spinRojo.setValue(0);
				spinVerde.setValue(255);
				spinAzul.setValue(0);
			}
			
		});
		
		azul.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ensenyarColor.setBackground(new Color(0, 0, 255));
				
				Color anyadoColor = new Color(0, 0, 255);
				

				dm.addElement(anyadoColor);
				
				slideRojo.setValue(0);
				slideVerde.setValue(0);
				slideAzul.setValue(255);
				
				spinRojo.setValue(0);
				spinVerde.setValue(0);
				spinAzul.setValue(255);
			}
			
		});
	
		Container c = this.getContentPane();
		
		c.setLayout(new BorderLayout());
		c.add(izquierda, BorderLayout.WEST);
		c.add(derecha, BorderLayout.EAST);
		
		this.setJMenuBar(barra);
		
		ensenyarColor.setBackground(color);
	
		
		cambiarColor.add(lRojo);
		cambiarColor.add(spinRojo);
		cambiarColor.add(slideRojo);
		cambiarColor.add(lVerde);
		cambiarColor.add(spinVerde);
		cambiarColor.add(slideVerde);
		cambiarColor.add(lAzul);
		cambiarColor.add(spinAzul);
		cambiarColor.add(slideAzul);
		cambiarColor.add(actualizar);
		
		izquierda.add(ensenyarColor);
		izquierda.add(cambiarColor);
		
		
		//todosColores.add(listaColores);
		derecha.add(scrollColores, BorderLayout.NORTH);
		derecha.add(eliminarColor, BorderLayout.SOUTH);
		derecha.add(todosColores);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("VentanaColor");
		
		this.pack();
	}
	
	public static void main(String args []) {
		new VentanaInigo();
	}
	
}
