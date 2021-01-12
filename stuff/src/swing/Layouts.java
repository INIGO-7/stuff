package swing;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Layouts {

	private Window window;
	
	private Container cont;
	
	JPanel arriba;
	JPanel abajo;
	
	public Layouts() {
		window = new Window();
		
		arriba = new JPanel(new GridLayout(5, 1));
		abajo = new JPanel(new BorderLayout());
		
		arriba.add(new JButton("hola"), 0);
		arriba.add(new JButton("--------------------tierra de nadie--------------------"), -1);
		abajo.add(new JLabel("Pte dcha de la ventana."), BorderLayout.EAST);
		abajo.add(new JLabel("Pte izqda de la ventana."), BorderLayout.WEST);
		
		cont = window.getContentPane();
		cont.setLayout(new BorderLayout());
		
		cont.add(arriba, BorderLayout.NORTH);
		cont.add(abajo, BorderLayout.SOUTH);
		
		
		
	}
	
	public static void main(String[] args) {
		new Layouts();
	}
	
}
