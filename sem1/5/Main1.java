import java.util.*;

public class Main1 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter len: ");
		int k = scanner.nextInt();
		int[][] matrix = new int[k][k]; 
		int sum = 0;
		System.out.println("Enter elements: ");
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < k; j++) {
				matrix[i][j] = scanner.nextInt();
				sum += matrix[i][j];
			}
		}
		printMatrix(matrix);
		System.out.println("Sum elements: " + sum);
	}

	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			System.out.println(Arrays.toString(matrix[i]));
		}
	}
}