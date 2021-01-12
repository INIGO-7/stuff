package swing;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;

public class Jlist {

	//llamar al constructor de window vacío para que funcione.
	private Window window;
	DefaultListModel<String> dm;
	private JList<String> lista;
	private JButton jb;
	private Container cont;
	
	public Jlist() {
		window = new Window();
		
		cont = window.getContentPane();
		cont.setLayout(new BorderLayout());
		
		dm = new DefaultListModel<String>();
		dm.addElement("hola soy una lista con un defaultListModel");
		lista = new JList<String>(dm);
		
		cont.add(lista, BorderLayout.EAST);
		
	}

	public static void main(String[] args) {
		new Jlist();
	}
	
}