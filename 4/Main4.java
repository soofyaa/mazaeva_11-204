import java.util.Scanner;
import java.util.Arrays;

public class Main4 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter len array: ");
		int len = scanner.nextInt();

		System.out.println("Array elements: ");
		int[] arr = new int[len];
		for (int i = 0; i < len; i++) {
   			arr[i] = scanner.nextInt();
		}

		System.out.println((progression(arr))? "Arithmetic progression" : "Not arithmetic progression");
	}

	public static boolean progression(int[] arr) {
		int d = arr[1] - arr[0];
		for (int i = 0; i < arr.length-1; i++) {
			if ((arr[i+1] - arr[i]) != d) {
				return false;
			}
		}
		return true;
	}
}