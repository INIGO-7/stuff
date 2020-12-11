package swing;

import java.awt.event.KeyEvent;

import javax.swing.*;

public class JMenus {
	
	public static void main(String[] args) {
	
		JMenu menuE;
		JMenu menuF;
		JMenuBar menuBar = new JMenuBar();
		
		Window window = new Window(400, 400, "JMenu");
		
		menuBar = new JMenuBar();
		
		menuE = new JMenu("press Alt+E"); menuE.setMnemonic(KeyEvent.VK_E);
		menuBar.add(menuE);
		menuF = new JMenu("press Alt+F"); menuF.setMnemonic(KeyEvent.VK_F);
		menuBar.add(menuF);
		
		window.setJMenuBar( menuBar );
	}
	
}