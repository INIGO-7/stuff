package programas.analizadorDeFrecuencia;

public class AnalyzerException extends Exception {

	public AnalyzerException(String message) {
		super(message);								//lo guarda en el atributo heredado
	}
	
	public AnalyzerException(String message, Throwable e) {
		super(message, e);
	}
	
}
