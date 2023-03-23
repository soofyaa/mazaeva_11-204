import java.util.Scanner;

 // Найти k-ый член арифметической прогрессии по первым двум членам

public class Main2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("1 element: ");
		int a1 = scanner.nextInt();
		System.out.print("2 element: ");
		int a2 = scanner.nextInt();
		System.out.print("Item number: ");
		int number = scanner.nextInt();
		int d = a2 - a1;
		int need_element = a1 + d*(number - 1);
		System.out.print(number + " element: " + need_element);
	}
}