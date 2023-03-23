import java.util.*;

public class Main2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter len: ");
		int k = scanner.nextInt();
		int[][] matrix = new int[k][k];
		int sum = 0;
		int cnt = 0;
		System.out.println("Enter elements: ");
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < k; j++) {
				System.out.print("[" + i + " " + j + "] ");
				matrix[i][j] = scanner.nextInt();
				if (i % 2 == 0 && j % 2 != 0) {
					sum += matrix[i][j];
					cnt += 1;
				}
			}
		}
		double averValue = sum/cnt;
		System.out.println();
		printMatrix(matrix);
		System.out.println();
		System.out.println("Average value: " + averValue);
	}

	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			System.out.println(Arrays.toString(matrix[i]));
		}
	}
}