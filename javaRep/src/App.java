/*
 * BMI Berechnung
 * bmi = K�rpergewicht/K�rpergr�sse ^2
 */

class App {

	public static void main(String[] args) {

			double gewicht = 70.0;
			double groesse = 1.62;
			double bmi = gewicht / (groesse * groesse);
			
			System.out.printf("Der BMI betr�gt: %.2f", bmi);
				
	}

}
