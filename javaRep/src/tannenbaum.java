
public class tannenbaum {

	public static void main(String[] args) {
		
		int hoehe = 8;
		hoehe /=2;
		
		
		//Krone
		for (int i = 1; i <= hoehe; i++) {
			for (int j = 0; j < hoehe-i; j++) {
				System.out.print(" ");
			}
			for (int j = 0; j < 2*i-1; j++) {
				System.out.print("*");
			}
			System.out.println();
	
		}
		//Stam
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
			System.out.print(" ");
			
			}
			System.out.println("I");
		
		}

	} 

}


