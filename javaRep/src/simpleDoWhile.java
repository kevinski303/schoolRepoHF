
public class simpleDoWhile {

	public static void main(String[] args) {
		
		/**
		 * Der Body wird mindestens ein mal ausgef�hrt da die pr�fung erst am ende erfolgt
		 */
		
		int i = 0;
		
		do {
			System.out.println(i);
			i++;
		} while (i <= 10);
	}
}
