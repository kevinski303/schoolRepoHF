package klassen;

public class Fahrzeug {
	
	int leistung;
	int fuellStand;
	String farbe;
	
	//Gibt alle Attributwerte des Fahrzeuges aus.
	void printOut() {
		System.out.println("Fahrzeug");
		System.out.println("Leistung: "+leistung);
		System.out.println("F�llstand: "+fuellStand);
		System.out.println("Farbe: "+farbe);
	
	}
	
	//Tankt den F�ellstand des Fahrzeuges mit der angegebenen Menge.
	void betanken (int menge) {
		fuellStand=fuellStand+menge;
	}
	

}


