
public class Aufgabe6411 {

	public static void main(String[] args) {

		int a[] = {-3, -2, -5, -1, -7, -9, -11, -4};
		int max=Integer.MIN_VALUE;
		int pos = -1;
		System.out.println(max);
		
		for (int i = 0; i < a.length; i++) {
			
			if(a[i] > max) {
				max = a[i];
				pos = i;
			}
		}
		System.out.println(max);
		System.out.println(pos);
	}

}
