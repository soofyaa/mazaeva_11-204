import java.util.*;

public class Main1 {
	public static void main(Strings[] args) {
		Scanner scanner = new Scanner(System.in);
		double x = scanner.nextDouble();
		double y = scanner.nextDouble();
		System.out.print("Tochka popala? ");
		if ((2/x + y*y) == 0) {
			System.out.println("Popala");
		} else {
			System.out.println("Ne popala");
		}
	}
}