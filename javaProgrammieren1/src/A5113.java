
public class A5113 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/**
		 * 1 a<b<c 2 a<c<b 3 b<a<c 4 b<c<a 5 c<a<b 6 c<b<a
		 **/

		int a;
		int b;
		int c;

		a = 6;
		b = 105;
		c = 393;

		if (a < b && b < c) {
			System.out.print(a + "<" + b + "<" + c);
		} else {
			if (a < c && c < b) {
				System.out.print(a + "<" + c + "<" + b);
			} else {
				if (b < a && a < c) {
					System.out.print(b + "<" + a + "<" + c);
				} else {
					if (b < c && c < a) {
						System.out.print(b + "<" + c + "<" + a);
					} else {
						if (c < a && a < b) {
							System.out.print(c + "<" + a + "<" + b);
						} else {
							if (c < b && b < a) {
								System.out.print(c + "<" + b + "<" + a);
							} else {
								System.out.print("Dont know what todo?!?!");
							}
						}
					}
				}
			}
		}
	}
}