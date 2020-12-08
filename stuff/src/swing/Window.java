package swing;

import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class Window extends JFrame{

	public int width, height;
	public String title;
	
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
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	//menuVentanas.setMnemonic( KeyEvent.VK_V );
	
	
	
}
