package klassen;

public class AutoVergleich {

	public static void main(String[] args) {
		// Wir rufen nun die Klasse Fahrzeug mit deren Attribute auf. Dabei wird der Variable vwGolf und miniCooper dem Typ Fahrzeug zugewiesen.
		
		fahrzeug vwGolf = new fahrzeug();
		fahrzeug miniCooper = new fahrzeug();
		
		vwGolf.fuellStand = 10.5;
		miniCooper.fuellStand = 8.4;
		
		System.out.println("Benizinfüllstand Golf: " + vwGolf.fuellStand + " liter");
		System.out.println("Benizinfüllstand Mini: " + miniCooper.fuellStand + " liter");
		
		if (vwGolf.fuellStand > miniCooper.fuellStand) {
			System.out.println("Der VW Golf hat einen höheren Füllstand");
		} else if (vwGolf.fuellStand == miniCooper.fuellStand) {
			System.out.println("Beide Fahrzeuge haben gleich viel Benzin");
		} else {
			System.out.println("Der Mini hat einen höheren Füllstand");
		}

	}

}
