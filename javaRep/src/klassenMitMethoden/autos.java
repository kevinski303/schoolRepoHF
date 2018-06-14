package klassenMitMethoden;

public class autos {

	public static void main(String[] args) {
		
		// Wir erstellen ein neues Fahrzeug
		fahrzeug A3 = new fahrzeug();
		System.out.println("Das Auto besitzt folgende Attribute");
		A3.printOut();
		System.out.println("------------------------------");
		
		// Mit der getter Methode getFarbe können wir die Farbe aufrufen
		System.out.println("Die Farbe ist: " + A3.farbe);
		System.out.println("------------------------------");
		
		//Die Farbe ändern wir nun auf Blau
		A3.farbe = "Blau";
		System.out.println("Die neue Farbe ist: " + A3.getFarbe());
		System.out.println("------------------------------");		
		// können aber auch die setter methode verwenden
		A3.setFarbe("Rot");
		System.out.println("Die Farbe " + A3.getFarbe() + " wurde mit dem setter gesetzt");
		System.out.println("------------------------------");
		// Rufen wir nun die printOut Methode auf, werden die Werte aus der A3 Attribute ausgegeben.
		//A3.printOut();
		
		// Das Fahrzeug befüllen wir nun mit der Methode betanken und geben den INT wert 5 mit
		A3.betanken(5);
		System.out.println("Das Auto wurde mit "+ A3.fuellStand + " betankt.");
		System.out.println("------------------------------");
		int neueAchsen = A3.achsenAendern(4);
		System.out.println(neueAchsen +" Achsen wurden hinzugefügt!");
		System.out.println("------------------------------");
		
		System.out.println("Das Auto besitzt nun folgende Attribute");
		A3.printOut(); // Der A3 hat nun 5 liter benzin im Tank und 4 Achsen

	}

}
