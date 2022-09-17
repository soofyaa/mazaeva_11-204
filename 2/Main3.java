import java.util.Scanner;

// Посчитать факториал заданного пользователем числа. (Можно целого)

public class Main3 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Factorial: ");
		int x = scanner.nextInt();
		System.out.print(x + "! = ");
		int factorial = 1;
		while (x > 0) {
			factorial *= x;
			x -= 1;
		}
		System.out.println(factorial);
	}
}