
public class zahlenRotieren {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] intarray = {77, 5, 13, 9, 63, 2};
		
		int hintersterwert = intarray[intarray.length -1];
		
		//verschiebe werte
		for (int i = intarray.length - 1; i > 0; i--) {
			intarray[i] = intarray[i-1];
		}
		
		//erster wert aus <- nachtragen
		intarray[0] = hintersterwert;
		
		//durch paarweises veraurschen von rechts nach links
		for (int i = intarray.length -1; i > 0; i--) {
			int tmp = intarray[i - 1];
			intarray[i-1] = intarray[i];
			intarray[i] = tmp;
		}
	}
}
