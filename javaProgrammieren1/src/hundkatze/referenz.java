package hundkatze;

public class referenz {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		tiny t1, t2;
		
		t1 = new tiny();
		t2 = new tiny();
		t1.a = "Hund";
		t2 = t1;
		t2.a = "Katze";
		System.out.println(t1.a);
		
	}

}
