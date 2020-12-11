package scanner;

import java.util.Scanner;

public class Scanners {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Escribe una frase: ");
		String frase = sc.next();
		System.out.print("Escribe un número: ");
		int num = sc.nextInt();
		
		System.out.println("Tu frase es: " + frase + " y tu numero es: " + num);
		
	}
	
}
