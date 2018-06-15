
public class zahlenSortieren {

	public static void main(String[] args) {
		
		int a = 8;
		int b = 2;
		int c = 7;
		
		if (a < b & b < c) {
			System.out.println(""+a+b+c);
		} else if (a > b & b > c) {
			System.out.println(""+c+b+a);			
		} else if ( a < b & b > c & a < c) {
			System.out.println(""+a+c+b);
		} else if ( a < b & b > c & c < a) {
			System.out.println(""+c+a+b);
		} else if ( a > b & b < c & c < a){
			System.out.println(""+b+c+a);
		} else if ( a > b & b < c & a < c) {
			System.out.println(""+b+a+c);
		} else {
			System.out.println("Dont know");
		}
		
	}

}
