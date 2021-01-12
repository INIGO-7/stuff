package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class JOptionPanes {

	private Window window;
	private String optionPane;
	private JButton button;
	
	public JOptionPanes() {
		
		//llamar al constructor de window vacío para que funcione.
		
		window = new Window();
		
		button = new JButton("pulsa aquí para que sepa tu nombre");
		
		button.addActionListener( new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
			
				optionPane = JOptionPane.showInputDialog( window, "Introduce el nombre para el usuario");
				int resp = JOptionPane.showConfirmDialog( window, "El fichero indicado ya existe. Â¿Sobreescribirlo? ", "AtenciÃ³n", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE );
				if (resp==JOptionPane.CANCEL_OPTION) System.out.println("funciona");
				JOptionPane.showInputDialog(window, "te llamas pedgo jaja");
				JOptionPane.showMessageDialog(window, "Error al generar csv", "Error", JOptionPane.ERROR_MESSAGE );
				System.out.println(optionPane);
				
			}
		} );
		
		window.add(button);
			
	//poner el showconfirmdialog por si existe un usuario en cualquier otro mundo con el mismo nombre: "existe un usuario con el mismo nombre, recomendamos utilice otro nombre (si desea dejarlo como está no
	//hay ningun problema, solo que puede haber confusiones en cuanto al usuario porque no sabe cuál es su mundo)
		
			
	}
	
	public static void main(String[] args) {
		new JOptionPanes();
	}
}