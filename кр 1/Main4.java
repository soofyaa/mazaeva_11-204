import java.util.*;

public class Main4 {
	public static void main(Strings[] args) {
		Scanner scanner = new Scanner(System.in);
		int k = scanner.nextInt();
		int[][] matrix = new int[k][k];
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < k; j++) {
				matrix[i][j] = scanner.nextInt();
			}
		}

		int sumEl = 0;
		int cntEl = 0;

		for (int i = 0; i < k; i++) {
			for (int j = 0; j < k; j++) {
				if ((((i+j) > (k-1)/2) && (i > j)) || (((i+j) < (k-1)/2) && (i < j))) {
					sumEl += matrix[i][j];
					cntEl++;
				}

				if (i == j) {
					sumEl += matrix[i][j];
					cntEl++;
				}
			}
		}

		for (int i = 0; i < k; i+=3) {
			if (i != j) {
				sumEl += matrix[i][j];
				cntEl++;
			}	
		}

		double srZnach = sumEl/cntEl;
		System.out.println(srZnach);
	}
}