package klassen;

public class Fahrzeugpark {

	public static void main(String[] args) {
		
		Fahrzeug pw = new Fahrzeug();
		pw.leistung = 130;
		pw.fuellStand = 4;
		pw.farbe = "rot";
		
		System.out.println("Fahrzeuginfos");
		pw.printOut();
		
		
		//Muss das Fahrzeug betankt werden?
		if (pw.fuellStand <= 5) {
			
			//Betanken mit 50 liter Benzin
			pw.betanken(20);
			System.out.println("------");
			System.out.println("Das auto wurde betankt.");
			System.out.println("Neuer Füllstand: "+pw.fuellStand);
			
		} else {
			System.out.println("------");
			System.out.println("Genügend Benzin vorhanden.");
			
		}

		
		
		
		
		/*
		
		// Parkplatz Array Aufgabe 7.1.1.3
		Fahrzeug parkplatz[] = new Fahrzeug[10];
		
		for (int i = 0; i < parkplatz.length; i++) {
			parkplatz[i] = new Fahrzeug();
			System.out.println(parkplatz[i]);
		}
	
		*/
		
		
		
		
	}

}
