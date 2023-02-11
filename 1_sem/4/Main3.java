import java.util.Scanner;
import java.util.Arrays;

public class Main3 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("ENTER LEN ARRAY: ");
		int len = scanner.nextInt();

		scanner.nextLine();

		System.out.println();
		System.out.println("ARRAY ELEMENTS: ");

		String[] arr = new String[len];
		for (int i = 0; i < len; i++) {
			System.out.print(i+1 + ". ");
   			arr[i] = scanner.nextLine();
		}

		System.out.println();
		System.out.println("STRINGS WITH A CAPITAL LETTER:");

		boolean exist = false;
		for (int i = 0; i < len; i++) {
   			char[] arrChar = arr[i].toCharArray();
   			int ascii = (int) arrChar[0];
   			if (ascii >= 65 && ascii <= 90) {
   				String string = String.valueOf(arrChar);
   				System.out.println(string);
   				exist = true;
   			} 
		}

		if (exist == false) {
			System.out.print("None");
		}
	}
}