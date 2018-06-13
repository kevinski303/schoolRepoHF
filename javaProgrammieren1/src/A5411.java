public class A5411 {

	public static void main(String[] args) {

		int i;
		int ii;
		int iii;

		i = 1;
		ii = 1;
		iii = 0;
		
		do{
			System.out.print(iii+",");
			iii = i + ii;
			i = ii;
			ii = iii;
		}while (i < 100 || ii < 100 || iii < 100);
	}
}