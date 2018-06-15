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
		System.out.println("- F�llstand: " + fuellStand);
		System.out.println("- Anzahl Achsen: " + achsenAnzahl);
		System.out.println("- Farbe: " + farbe);
	}
	
	// Mittels dieser Methode kann das Fahrzeug nun betankt werden
	void betanken(int mengeBenzin) { //dabei muss uns der wert menge als INT �bergeben werden
		fuellStand=fuellStand+mengeBenzin; //dannach addieren wir die menge zum gegebenen fuellstand (f�llstand wird zu f�llstand plus menge)
		
	}
	
	// geben wir der methode einen datentyp der nicht void ist, m�ssen wir einen r�ckgabewert zur�ckgeben.
	int achsenAendern (int anzahlAchsen) {
		return achsenAnzahl = anzahlAchsen;
	}
	
	// Getter methode f�r die Farbe
	public String getFarbe() {
		return farbe;
	}
	
	//Setter methode f�r die Farbe. Mittels dieser Methode kann die Farbe ge�ndert werden
	public String setFarbe(String farbeNeu) {
		return this.farbe = farbeNeu;
	}
}