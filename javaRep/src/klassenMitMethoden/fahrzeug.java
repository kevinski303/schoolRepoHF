package klassenMitMethoden;

public class fahrzeug {    
	double verbrauch;
	double fuellStand;
	int achsenAnzahl;
	String farbe = "Standard";
	
	//Wir bauen eine Methode die alle Attribute eines Fahrzeuges ausgibt
	void printOut() { 
		System.out.println("Fahrzeug");
		System.out.println("--------");
		System.out.println("- Verbrauch: " + verbrauch);
		System.out.println("- Füllstand: " + fuellStand);
		System.out.println("- Anzahl Achsen: " + achsenAnzahl);
		System.out.println("- Farbe: " + farbe);
	}
	
	// Mittels dieser Methode kann das Fahrzeug nun betankt werden
	void betanken(int mengeBenzin) { //dabei muss uns der wert menge als INT übergeben werden
		fuellStand=fuellStand+mengeBenzin; //dannach addieren wir die menge zum gegebenen fuellstand (füllstand wird zu füllstand plus menge)
		
	}
	
	// geben wir der methode einen datentyp der nicht void ist, müssen wir einen rückgabewert zurückgeben.
	int achsenAendern (int anzahlAchsen) {
		return achsenAnzahl = anzahlAchsen;
	}
	
	// Getter methode für die Farbe
	public String getFarbe() {
		return farbe;
	}
	
	//Setter methode für die Farbe. Mittels dieser Methode kann die Farbe geändert werden
	public String setFarbe(String farbeNeu) {
		return this.farbe = farbeNeu;
	}
}