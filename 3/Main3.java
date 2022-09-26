import java.util.Scanner;

public class Main3 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter a number: ");
		int x = scanner.nextInt();
		System.out.print("Choose the number system: ");
		int k = scanner.nextInt();
		System.out.println((k > 10)? "Error. The chosen number system must be less than 10 :(" : (cnt1(x, k) > 3)? "Count more than 3" : "Count less than 3");

	}

	public static int cnt1(int x, int k) {
		int cnt = 0;
		while (x > 0) {
            if (x % k == 1) {
                cnt += 1;
            }
            x /= k;      
        }
        return cnt;
	}
}