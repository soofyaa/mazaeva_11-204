import java.util.Scanner;

/*
На вход подается целочисленное число в десятичной системе счисления. 
Вычислить его двоичную запись (тип int). 
Найти наибольшую длину последовательности единиц в двоичной записи.
*/

public class Main4 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int x = scanner.nextInt();
		System.out.print("Bin " + x + ": ");
        int cnt = 0;
        int mcnt = 0;
        String bin = "";
        while (x > 0) {
            bin = (x%2) + bin;
            if (x % 2 == 1) {
                cnt += 1;
                if (cnt > mcnt) {
                    mcnt = cnt;
                }
            } else {
                cnt = 0;
            }
            x /= 2;      
        }
        int bin_int = Integer.parseInt(bin);
        System.out.println(bin_int);
        System.out.println("Max count 1: " + mcnt); 
	}
}