package klassen;

public class Fahrzeugpark {

	public static void main(String[] args) {
		
		Fahrzeug pw=new Fahrzeug();
		pw.getriebe=7;
		pw.leistung=150;
		
		pw.getMaxTank();
		
		pw.setFuellstand(500);

		System.out.println( pw.setFuellstand(50) );
	}

}
