import java.util.Scanner;

public class array {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("----");
		System.out.println("Gewünschte Array Version wählen (1, 2, 3): " );
		System.out.println("----");
		Scanner sc = new Scanner(System.in);
		int v = sc.nextInt();
		
		//Version 1 Aufzählung der Elemente
		if (v == 1) {
			System.out.println("----");
			System.out.println("Welcher Array Index soll ausgegeben werden?(0-4)");
			System.out.println("----");
			Scanner sc1 = new Scanner(System.in);
			int v1index = sc1.nextInt();
			
			int[] einArray = {8, 6, 12, 3, 5};
			
			System.out.println("Index " + v1index + " hat den INT Wert " + einArray[v1index]);
			
			sc1.close();
			
		} else {
			
			// Version 2 new Operator
			if (v == 2) {
				System.out.println("----");
				System.out.println("Welcher Array Index soll ausgegeben werden?(0-4)");
				System.out.println("----");
				Scanner sc2 = new Scanner(System.in);
				int v2index = sc2.nextInt();
				
				int[] array = new int[5]; // 5 Positionen (Index 0-4)
				array[0] = 1; // Index 0 bzw Position 1
				array[1] = 2; // Index 1 bzw Position 2
				array[2] = 3; // Index 2 bzw Positon 3
				array[3] = 4; // Index 3 bzw Position 4
				array[4] = 5; // Index 4 bzw Positon 5
				
				System.out.println("Index " + v2index + " hat den INT Wert bzw Position " + array[v2index]);
				
				sc2.close();
				
			} else {
				
				// Traversieren
				if ( v == 3) {
					String[] wt = {"Mo", "Di", "Mi", "Do", "Fr", "Sa", "So"};
					
					for (int i = 0; i < wt.length; i++) {
						System.out.println(wt[i]);
						System.out.println("--");
					}
					
				} else {
					
					System.out.println("Version " + v + " existiert nicht.");
					
					
					
				}
				
			}
					
		}sc.close();
		
	}

}
