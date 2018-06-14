/*
 * BMI Berechnung
 * bmi = K�rpergewicht/K�rpergr�sse ^2
 */

class App3 {

	public static void main(String[] args) {

		double gewicht = 75.0;
		double groesse = 1.62;
		double bmi = gewicht / (groesse * groesse);
		
		System.out.println("Der BMI betr�gt: " + bmi);
					
		if (bmi >= 25) {
			System.out.println("�bergewicht");
		
		} else if (bmi <= 18.5) {
			System.out.println("Untergewicht");
		
		} else if (bmi >= 30) {
			System.out.println("Starkes �bergewicht");
		
		} else {
			System.out.println("Normalgewicht");
			
		}
	
	}

}
