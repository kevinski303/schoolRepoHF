
public class arrays {

	public static void main(String[] args) {
		
		//Möglichkeit aufzählung der elemente
		int [] einArray = {0,1,2,3,4};
		
		//Möglichkeit mit dem new opearator
		String[] wochenTage = new String[7]; //erzeugz ein array mit dem datentyp string und sieben indexen
		
		//Nun werden string werte in die indexe eingefüllt
		wochenTage[0] = "Montag";
		wochenTage[1] = "Dienstag";
		wochenTage[2] = "Mittwoch";
		wochenTage[3] = "Donnerstag";		
		wochenTage[4] = "Freitag";
		wochenTage[5] = "Samstag";
		wochenTage[6] = "Sonntag";
	
		System.out.println(wochenTage[1]); //Gibt das element aus dem Array wochenTage in index 1 aus
	
	
		//Traversieren mit for
		
		String[] wt = {"Mo","Di","Mi","Do","Fr","Sa","So"};
		for (int i = 0; i < wt.length; i++) {
			System.out.println(wt[i]);
		}
	
	}

}
