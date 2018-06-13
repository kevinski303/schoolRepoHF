
public class Aufgabe7241 {

	public static void main(String[] args) {
		
		Aufgabe7241 a = new Aufgabe7241();
		
		int n = a.quadrieren( 7 );
		System.out.println( n );
		
		System.out.println( a.quadrieren(9));
		// + 9
		int e = a.addieren(a.quadrieren(7),  9);
	}

	 int addieren(int s1, int s2) {
		 return s1 + s2;
	 }
	int quadrieren( int zahl) {
		return multiplikation(zahl, zahl);
	}
	
	int multiplikation(int f1, int f2) {
		
		for (int i = 0; i < f2; i++) {
			
		}
		return 50;
	}
	
}
