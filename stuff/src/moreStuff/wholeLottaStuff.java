package moreStuff;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.util.regex.Pattern;

import javax.swing.TransferHandler;
import javax.swing.TransferHandler.TransferSupport;

public class wholeLottaStuff {

	public static void main(String[] args) {
		
		String text = "hola";
		String line = "Hey muy buenas hola";										//line contains text hola
		System.out.println(Pattern.compile("hola").matcher(line).find());			//returns true, can search literally (with letters input),
		System.out.println(Pattern.compile("[a-z]{5}").matcher(line).find());		//or by a pattern ([a-z]{2}) -> if contains chains of 2 letters from a-z
		
		TransferHandler.TransferSupport support;
		
		Transferable t;										//Transferable is used to represent data that is exchanged via a cut, copy, or paste to/from a clipboard
		
		TransferHandler th; 
		
		DataFlavor df;
		
		TransferSupport ts;
		
		//.javaFileListFlavor
		
		
		
	}
	
}
