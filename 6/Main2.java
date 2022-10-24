import java.util.*;

public class Main2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("len array: ");
		int len = scanner.nextInt();

		scanner.nextLine();

		System.out.println("strings: ");
		String[] arr = new String[len];
		for (int i = 0; i < len; i++) {
			System.out.print(i+1 + ". ");
   			arr[i] = scanner.nextLine();
		}

		System.out.println();

		boolean checkMain = false;

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (arr[i].length() <= arr[j].length() && i != j) {
					char[] arrI = arr[i].toCharArray();
					char[] arrJ = arr[j].toCharArray();
					boolean check = false;
					for (int k = 0; k < arrI.length; k++) {
						if (arrI[k] == arrJ[k]) {
							check = true;
						} else {
							check = false;
							break;
						}
					}
					if (check) {
						checkMain = true;
						System.out.println(arr[i] + " in " + arr[j]);
					}
				}
			}
		}

		if (!checkMain) {
			System.out.println("no strings");
		}
 	}
}