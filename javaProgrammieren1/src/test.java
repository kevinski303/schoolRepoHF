class Fahrzeug{
	int weight;
}


public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Fahrzeug[] pw = new Fahrzeug[100];
		pw[4].weight = 1400;
		
		for (int i=0; i < pw.length; i++) {
			System.out.println(pw[i]);
		}
		
		}
	}
