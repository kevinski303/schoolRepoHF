
public class A5112 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a;
		int b;
		
		a = 1;
		b = 6;
		
		if (a < b) {
			int tmp;
			
			tmp = b;
			b = a;
			a = tmp;
			
			System.out.println(a);
			System.out.println(b);
		}else {
			System.out.println(a);
			System.out.println(b);
		}
		
	}

}
