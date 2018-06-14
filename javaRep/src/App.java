/*
 * BMI Berechnung
 * bmi = Körpergewicht/Körpergrösse ^2
 */

class App {

	public static void main(String[] args) {

			double gewicht = 70.0;
			double groesse = 1.62;
			double bmi = gewicht / (groesse * groesse);
			
			System.out.printf("Der BMI beträgt: %.2f", bmi);
				
	}

}
