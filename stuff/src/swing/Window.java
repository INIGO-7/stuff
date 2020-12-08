package swing;

import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class Window extends JFrame{

	public int width, height;
	public String title;
	
	//swing
	private JMenu menuE, menuF;
	
	public Window(int width, int height, String title) {
		
		this.width = width;
		this.height = height;
		this.title = title;
		
		createWindow(width, height, title);
	}
	
	public void createWindow(int width, int height, String title) {
		
		setTitle(title);
		setSize(width, height);
		
//		setLocationRelativeTo(null); //para que la ventana esté en el centro
		
		JMenuBar menuBar = new JMenuBar();
		menuE = new JMenu( "Presiona Alt+E" ); menuE.setMnemonic( KeyEvent.VK_E );
		menuBar.add( menuE );
		menuF = new JMenu( "Presiona Alt+F" ); menuF.setMnemonic( KeyEvent.VK_F );
		menuBar.add( menuF );
		
		setJMenuBar( menuBar );
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	//menuVentanas.setMnemonic( KeyEvent.VK_V );
	
	
	
}
