import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class Main1 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int sum = 0;
		System.out.print("len arr: ");
		int len = scanner.nextInt();
		int[] arr = new int[len];
		System.out.println("elements arr: ");
		for (int i = 0; i < len; i++) {
			arr[i] = scanner.nextInt();
			if (arr[i] % 2 == 1) {
				sum += arr[i];
			}
		}
		
		System.out.println("summa nechet elem arr: " + sum);
	}
}