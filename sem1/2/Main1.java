import java.util.Scanner;

// Поменять местами значения двух числовых переменных без использования третьей

public class Main1 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int a = scanner.nextInt();
		int b = scanner.nextInt();
		System.out.println("Before: a = " + a + ", b = " + b);
		a = a + b;
		b = a - b;
		a = a - b;
		System.out.println("After: a = " + a + ", b = " + b);
		System.out.println("After: a = " + a + ", b = " + b);

		System.out.println("After: a = " + a + ", b = " + b);

		System.out.println("After: a = " + a + ", b = " + b);

		System.out.println("After: a = " + a + ", b = " + b);

		System.out.println("After: a = " + a + ", b = " + b);

		System.out.println("After: a = " + a + ", b = " + b);

	}


}