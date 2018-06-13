package klassen;

public class Fahrzeug {
	/** Leistung in PS*/
	int leistung;	
	/** die Anzahl der gaenge*/
	int getriebe=4;
	int tueren;
	private double fuellstand=20;
	private int maxTank=50;
	
	public int getMaxTank() {
		return maxTank;
	}
	
	
	double setFuellstand(int menge) {
		if(fuellstand + menge < maxTank) {
			fuellstand += menge;
		}else {
			throw new IllegalArgumentException("Max. füllstamd ist "+maxTank);
		}
		return fuellstand;
	}
	
	
	
}
