
public class fibonacci {

	public static void main(String[] args) {
		int a = 1;
		int i = 1;
		int z = 100;
		System.out.println(i);
		
		do {
			System.out.println(i);
			i = a + i;
			a = i - a;
		} while(i < z);

	}

}
