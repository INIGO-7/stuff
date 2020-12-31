package swing;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

public class Window extends JFrame{

	public int width, height;
	public String title;
	private Canvas canvas;
	
	public Window(int width, int height, String title) {
		
		this.width = width;
		this.height = height;
		this.title = title;
		
		createWindow(width, height, title);
	}
	
	public void createWindow(int width, int height, String title) {
		
		setTitle(title);
		setSize(width, height);
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		
		add(canvas);
		pack();	
		canvas.setBackground(Color.WHITE);
//		setLocationRelativeTo(null); //para que la ventana esté en el centro
		
		//
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	//menuVentanas.setMnemonic( KeyEvent.VK_V );
	
}
