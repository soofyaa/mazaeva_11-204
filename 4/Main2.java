import java.util.Scanner;
import java.util.Arrays;

public class Main2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter string: ");
		String string1 = scanner.nextLine();

		char[] arr1 = string1.toCharArray();
		char[] arr2 = new char[arr1.length];
		for (int i = 0; i < arr1.length; i++) {
			arr2[i] = arr1[arr1.length-1-i];
		}

		String string2 = String.valueOf(arr2);
		System.out.println("Reverse string: " + string2);
	}
}