import java.util.Scanner;

public class Main4 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("a - the beginning of the range, b - the end of the range.");
		System.out.println("Condition: a <= b");
		System.out.print("a = ");
		int a = scanner.nextInt();
		System.out.print("b = ");
		int b = scanner.nextInt();
		
		int cnt = 0;

		if (a > b) {
			System.out.print("Error. Condition: a <= b");
		} else {

			System.out.println("Prime numbers, belonging to the range from " + a + " to " + b + ":");
			for (int i = a; i <= b; i++) {
				if (isProst(i)) {
					System.out.print(i + " ");
					cnt++;
				}
			}
		}
		if (cnt == 0) {
			System.out.print("There are no primes.");
		}
	}

	public static boolean isProst(int x) {
		boolean prost = true;
		for (int i = 2; i < x; i++) {
			if (x % i == 0) {
				prost = false;
			}
		}
		return prost;
	}
}