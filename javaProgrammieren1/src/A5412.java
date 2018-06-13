import java.util.Random;
import java.util.Scanner;

public class A5412 {

	private static Scanner scanner;

	public static void main(String[] args) {
		
		Random random=new Random();
		int zufall=random.nextInt(10)+1;
		scanner = new Scanner(System.in);
		int versuch;
		
		System.out.println("Zufallszahl zwischen 1 und 10 wurde generiert!");
		do {
			System.out.println("Errate die Zufallszahl.");
			versuch=scanner.nextInt();
			if(versuch != zufall) {
				System.out.println("Leider flasch.");
			}
		}while(versuch != zufall);
		System.out.println("Richtig!");
	}

}
