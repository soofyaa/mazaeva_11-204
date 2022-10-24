import java.util.*;

public class Main4 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
        
		System.out.print("enter len of matrix (len >= 5): ");
		int len = scanner.nextInt();
		int[][] matrix = new int[len][len];
		System.out.println("enter elements of arr: ");
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				matrix[i][j] = scanner.nextInt();
			}
		}

		System.out.println();
		printMatrix(matrix);
		System.out.println();

		System.out.print("write color (green / yellow / red): ");
		scanner.nextLine();
        String str = scanner.nextLine();

        switch (str) {
            case "green":
            	System.out.println(green(matrix));
                break;

            case "yellow":
            	System.out.println(yellow(matrix));
                break;

            case "red":
            	System.out.println(red(matrix));
                break;

            default:
            	System.out.println("something wrong");
            	break;
        }
	}

	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			System.out.println(Arrays.toString(matrix[i]));
		}
	}

	public static double green(int[][] matrix) {
		int sum = 0;
		int cnt = 0;
		double k = matrix.length/2;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				if (i < j && i < k && j < k) {
					sum += matrix[i][j];
					cnt++;
				}
			}
		}
		return (double)sum/cnt;
	}

	public static double yellow(int[][] matrix) {
		int sum = 0;
		int cnt = 0;
		double k = matrix.length/2;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				if (i+j > matrix.length-1 && i < k && j > k) {
					sum += matrix[i][j];
					cnt++;
				}
			}
		}
		return (double)sum/cnt;
	}

	public static double red(int[][] matrix) {
		int sum = 0;
		int cnt = 0;
		double k = matrix.length/2;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				if (i+j > matrix.length-1 && i > k && j < k) {
					sum += matrix[i][j];
					cnt++;
				}
			}
		}
		return (double)sum/cnt;
	}
}