
public class casts {

	public static void main(String[] args) {
		
		//Expliziter cast ->> ganzzahl zu fliesskoma (nachkomastelle wird hinzugefügt)
		System.out.println("Expliziter Cast");
		System.out.println("---------------");
		
		int i = 10;
		System.out.println(i);
		
		double d;
		d = i;
		System.out.println(d);
		
		d = d + 1.5;
		System.out.println(d + " // " + i);
		
		System.out.println("---------------");
				
		
		// Impliziter cast --> fliesskoma zu ganzzahl (nachkomastellen werden abgeschnitten)
		System.out.println("Expliziter Cast");
		System.out.println("---------------");
		
		i = (int) d;
		
		System.out.println(i);

	}

}
