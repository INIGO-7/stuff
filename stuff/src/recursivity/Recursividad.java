package recursivity;

public class Recursividad {

	public static int r = 0;
	
	public static int factorial(int n) {
		
		if( n <= 1 ) return n;
		else r = n * factorial( n - 1 );
		
		return r;
	}
	
	public static void main(String[] args) {

		System.out.println(factorial(5));
	
	}
	
}
