package returner;

public class Test {

	static int methode(int count) {
		count = count *2;
		System.out.println(count);
		return count;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int x = 3;
		x = methode(x);
		System.out.println(x);
		
	}
}
