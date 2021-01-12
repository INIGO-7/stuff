package swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Layouts {

	private Window window;
	
	private Container cont;
	
	public Layouts() {
		window = new Window();
	}
	
	public void borderLayout() {
		JPanel abajo = new JPanel(new BorderLayout());
		
		abajo.add(new JLabel("Pte dcha de la ventana."), BorderLayout.EAST);
		abajo.add(new JLabel("Pte izqda de la ventana."), BorderLayout.WEST);
		
		cont = window.getContentPane();
		cont.setLayout(new BorderLayout());
		
		cont.add(abajo, BorderLayout.SOUTH);
	}
	
	public void gridLayout() {
		JPanel arriba = new JPanel(new GridLayout(5, 1));
		
		arriba.add(new JButton("hola"), 0);
		arriba.add(new JButton("--------------------tierra de nadie--------------------"), -1);
		
		cont = window.getContentPane();
		cont.setLayout(new BorderLayout());
		
		cont.add(arriba, BorderLayout.NORTH);
	}
	
	public void gridBagLayout() {
		
		 GridBagLayout lay = new GridBagLayout();
	        GridBagConstraints gbc = new GridBagConstraints();
	        
	        JPanel panel = new JPanel(lay);
	        JPanel pnl;
	        
	        gbc.weightx = 1;
	        gbc.weighty = 1;
	        gbc.fill = GridBagConstraints.BOTH;
	        
	        //Fila 1
	        pnl = new JPanel();
	        pnl.setBackground(Color.RED);
	        gbc.gridx = 0;
	        gbc.gridy = 0;
	        gbc.gridwidth = 1;
	        gbc.gridheight = 1;
	        panel.add(pnl, gbc);
	        
	        pnl = new JPanel();
	        pnl.setBackground(Color.ORANGE);
	        gbc.gridx = 1;
	        gbc.gridy = 0;
	        gbc.gridwidth = 2;
	        gbc.gridheight = 1;
	        panel.add(pnl, gbc);
	        
	        //Fila 2
	        pnl = new JPanel();
	        pnl.setBackground(Color.YELLOW);
	        gbc.gridx = 0;
	        gbc.gridy = 1;
	        gbc.gridwidth = 1;
	        gbc.gridheight = 1;
	        panel.add(pnl, gbc);
	        
	        pnl = new JPanel();
	        pnl.setBackground(Color.GREEN);
	        gbc.gridx = 1;
	        gbc.gridy = 1;
	        gbc.gridwidth = 1;
	        gbc.gridheight = 1;
	        panel.add(pnl, gbc);
	        
	        pnl = new JPanel();
	        pnl.setBackground(Color.CYAN);
	        gbc.gridx = 2;
	        gbc.gridy = 1;
	        gbc.gridwidth = 1;
	        gbc.gridheight = 2;
	        panel.add(pnl, gbc);
	        
	        //Fila 3
	        pnl = new JPanel();
	        pnl.setBackground(Color.BLUE);
	        gbc.gridx = 0;
	        gbc.gridy = 2;
	        gbc.gridwidth = 1;
	        gbc.gridheight = 1;
	        panel.add(pnl, gbc);
	        
	        pnl = new JPanel();
	        pnl.setBackground(Color.MAGENTA);
	        gbc.gridx = 1;
	        gbc.gridy = 2;
	        gbc.gridwidth = 1;
	        gbc.gridheight = 1;
	        panel.add(pnl, gbc);
	        
	        window.add(panel);
	}
	
	public static void main(String[] args) {
		Layouts lout = new Layouts();
		lout.gridBagLayout();
	}
	
}
