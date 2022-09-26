import java.util.Scanner;

public class Main1 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int x = scanner.nextInt();
		System.out.println((x <= 0)? (int) 0 : (x > 1)? (int) Math.pow(x, 4) : (int) x);
	}
}