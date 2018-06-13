
public class A5514 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// http://www.sitesbay.com/java-program/java-triangle-of-star
		
		char c1 = '*';
		char c2 = '|'; 
		
		for(int i = 1; i <= 7;) {
			for(int ii=1; ii<=i;) {	
				System.out.print(c1);
				ii++;
			}
			i+=2;
			System.out.println();
		}
		for (int i =1; i<=3;) {
			System.out.println(c2);
			i++;
		}
	}
}