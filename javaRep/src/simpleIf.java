import java.util.Scanner;

public class simpleIf {

	public static void main(String[] args) {
		
		System.out.print("Geben Sie einen INT Wert für die Variable A ein: ");
		Scanner sca = new Scanner(System.in);
		int a = sca.nextInt();
		
		System.out.print("Geben Sie einen INT Wert für die Variable B ein: ");
		Scanner scb = new Scanner(System.in);
		int b = scb.nextInt();
		
		if (a < b) {
			System.out.println("A ist kleiner als B");
		} else if (a == b){
			System.out.println("B ist kleiner als A");
		} else {
			System.out.println("B ist kleiner als A");
		}
	}
}
