import java.util.*;

public class Main2 {
	public static void main(Strings[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Len arr: ");
		int n = scanner.nextInt();
		int[] arr = new int[n];

		System.out.println("Elements arr: ");
		for (int i = 0; i < n; i++) {
			arr[i] = scanner.nextInt();
		}

		boolean chetEl = false;
		for (int i = 0; i < n; i++) {
			if (onlyChet(arr[i])) {
				chetEl = true;
				break;
			}
		}

		System.out.print("Chet in arr? ");

		if (chetEl) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
	}

	public static boolean onlyChet(int x) {
		while (x > 0) {
			if ((x % 10) % 2 != 0) {
				return false;
			}
			x /= 10;
		}
		return true;
	}
}