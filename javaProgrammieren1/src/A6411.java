
public class A6411 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * Es soll aus einem selbstgewählten int Array das Element mit
			dem grössten Zahlenwert gefunden und ausgegeben werden.
		 
		 */
		
		int[] a={19, 16};
		int a0 = a[0];
		int a1 = a[1];
		
		if( a0 > a1) {
			System.out.println(a0 + " > " + a1);
		} else {
			if(a1 > a0) {
				System.out.println(a1 + " > " + a0);
			} else {
				System.out.println(a0 + " = " + a1);
			}
			
		}
		
	}

}



