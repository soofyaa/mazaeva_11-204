import java.util.*;

public class Main3 {
	public static void main(Strings[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("n = ");
		double n = scanner.nextDouble();
		System.out.print("x = ");
		double x = scanner.nextDouble();
		
		double sum = 0;
		for (int i = 0; i < n; i++) {
			int k = i+1;
			sum += (Math.pow(-1, k) * (Math.pow(x, 2*k+1) / (fact(k) + x)));
		}
		System.out.println(sum);
	}

	public static int fact(int k) {
		if (k == 0) {
			return 1;
		} else {
			return fact(k-1) * k;
		}
	}
}