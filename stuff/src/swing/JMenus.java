package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class JMenus {
	
	public static void main(String[] args) {
	
		JMenuItem item1, item2;
		
		JMenu menuE;
		JMenu menuF;
		JMenuBar menuBar = new JMenuBar();
		
		Window window = new Window(400, 400, "JMenu");
		
		menuBar = new JMenuBar();
		
		item1 = new JMenuItem("Hola");
		item2 = new JMenuItem("Programador");
		
		item1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.print("Hola ");
			}
			
		});
		
		item2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.print("programador.");
			}
			
		});
		
		menuE = new JMenu("press Alt+E"); menuE.setMnemonic(KeyEvent.VK_E);
		menuE.add(item1);
		
		menuF = new JMenu("press Alt+F"); menuF.setMnemonic(KeyEvent.VK_F);
		menuF.add(item2);
		
		menuBar.add(menuE);
		menuBar.add(menuF);
		
		window.setJMenuBar( menuBar );
	}
	
}