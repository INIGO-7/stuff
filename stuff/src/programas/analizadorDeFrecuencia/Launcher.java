package programas.analizadorDeFrecuencia;

import java.io.File;

public class Launcher {

	public static void main(String[] args) throws AnalyzerException {
		
		if(args.length == 0) {
			System.out.println("El fichero a analizar ha de ser escrito después de la clase. Inténtalo otra vez.");
			
		}else {
			
			File file = new File(args[0]);
			
			
			FreqAnalysis analyzer = new FreqAnalysis(file);		//no capturamos el error porque estamos haciendo una prueba, en un programa deberíamos capturarlo.
			analyzer.Analyze();
			
			System.out.println(analyzer.getLettersMap());
			System.out.println("Numero total de letras: " + analyzer.getLetrasTotal());
			
			analyzer.printRelativeFreq(analyzer.getLettersMap());
			analyzer.topDiez();
			System.out.println(analyzer.getTop());
		}
	}
}