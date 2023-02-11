import java.util.Scanner;

public class Main2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter a number: ");
		int x = scanner.nextInt();
		System.out.print("Choose the number system: ");
		int k = scanner.nextInt();
		System.out.println((k > 10)? "Error. The chosen number system must be less than 10 :(" : "Result: " + numSystem(x,k));

	}

	public static int numSystem(int x, int k) {
		int result = 0;
		int degree = 0;
		while (x > 0) {
			result += (int)(x % k) * Math.pow(10, degree);
			x = x/k;
			degree++;
		}
		return result;
	}
}