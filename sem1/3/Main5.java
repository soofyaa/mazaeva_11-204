import java.util.Scanner;

public class Main5 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter a number: ");
		int n = scanner.nextInt();
		boolean canBe = false;

		for (int sqr1 = 1; sqr1 < n; sqr1++) {
			for (int sqr2 = 1; sqr2 <= sqr1; sqr2++) {
				for (int sqr3 = 1; sqr3 <= sqr2; sqr3++) {
					if ((Math.pow(sqr1, 2) + Math.pow(sqr2, 2) + Math.pow(sqr3, 2)) == n) {
						System.out.println("It can be represented as the sum of three squares:");
						System.out.println(sqr1 + "^2 + " + sqr2 + "^2 + " + sqr3 + "^2" + " = " + n);
						System.out.println("Three numbers: " + sqr1 + ", " + sqr2 + ", " + sqr3);
						canBe = true;
						break;
					}
				}
			}
		}

		if (canBe == false) {
			System.out.println("It can not be represented as the sum of three squares.");
		}

	}
}