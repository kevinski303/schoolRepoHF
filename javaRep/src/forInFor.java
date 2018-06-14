
public class forInFor {

	public static void main(String[] args) {
		
		System.out.println("Domino Steine 1-6");
		
		for (int i=1; i<=6; i++) {
			System.out.println("-"+i+"-");
			
			for (int y = 1; y <= 6; y++) {
				
				System.out.println(i + "|" + y);				
				
			}
		}
	}
}
