package klassen;

public class objektVariablenZuweisen {

	public static void main(String[] args) {
		
		/*Initialisierung eines neuen Fahrzeuges mit dem namen f
		 * Dem Fahrzeug f weisen wir die Farbe Rot zu
		 */
		fahrzeug f = new fahrzeug();
		f.farbe = "Rot";
		
		/*
		 * Wir deklarieren nun ein fahrzeug x dieses besitzt aber noch keine attribute aus der Klasse fahrzeug
		 */
		fahrzeug x;
		
		/*
		 * Wenn nun das Fahrzeug x sämtlicher attribute dem fahrzeug f zugewisen wid, so zeigt java nun auf den gleichen Speicherplatzhalter
		 * Ergo sollte fahrzeug x nun die genau gleichen Attributwerte haben wie das fahrzeug f
		 */
		x = f;
		
		System.out.println(x.farbe); // Es wird also ROT ausgegeben!

	}

}
