

public class Aufgabe5113DreiZahlensortieren {
	
	/**
	 * Idee:
	 * 
	 * check:
	 * a < b < c
	 * a < c < b
	 * b < a < c
	 * b < c < a
	 * c < a < b
	 * c < b < a
	 * 
	 * oder:
	 * 1) zuerst kleinstes suchen und dieses danach mit a tauschen
	 * 2) danach b mit c vergleichen und entsprechend tauschen
	 * 
	 * 
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		int a=11;
		int b=9;
		int c=5;
		
		// Idee1:
		if( a  < b && b < c){
			// tue nichts
		}
		if(a < b && b > c){
			// tausche b mit c
			
		}
		
		// Idee2:
		if(a < b && a < c){
			// tue nichts
		}
		if(b < a && b < c){
			// tausche b mit a
			int t;
			t=a;
			a=b;
			b=t;
		}
		if(c < a && c < b){
			// tausche c mit a
			int t;
			t=a;
			a=c;
			c=t;
		}
		
		if(b < c){
			// tue nichts
		}else{
			int t;
			t=b;
			b=c;
			c=t;
		}
		
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);

	}

}
