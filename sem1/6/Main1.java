import java.util.*;

public class Main1 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("enter len of arr: ");
		int len = scanner.nextInt();
		int[] arrForNeOptim = new int[len];
		int[] arrForOptim = new int[len];
		System.out.println("enter elements of arr: ");
		for (int i = 0; i < len; i++) {
			arrForNeOptim[i] = scanner.nextInt();
			arrForOptim[i] = arrForNeOptim[i];
		}

		System.out.println(Arrays.toString(arrForNeOptim));
		System.out.println(Arrays.toString(arrForOptim));

		int cntNeOptim = 0;
		for (int i = 0; i < len; i++) {
			cntNeOptim++;
			for (int j = 0; j < len; j++) {
				cntNeOptim++;
				if (arrForNeOptim[i] < arrForNeOptim[j]) {
					int currEl = arrForNeOptim[i];
					arrForNeOptim[i] = arrForNeOptim[j];
					arrForNeOptim[j] = currEl;
				}
			}
		}
		
		int cntOptim = 0;
		for (int curMin = 0; curMin < len-1; curMin++) {
			cntOptim++;
			int minEl = arrForOptim[curMin];
			for (int current = curMin+1; current < len; current++) {
				cntOptim++;
				if (arrForOptim[current] < arrForOptim[curMin]) {
					minEl = arrForOptim[current];
					arrForOptim[current] = arrForOptim[curMin];
					arrForOptim[curMin] = minEl;
				}
			}
		}

		System.out.println(Arrays.toString(arrForNeOptim) + " || ne optimized sort count iter: " + cntNeOptim);
		System.out.println(Arrays.toString(arrForOptim) + " || optimized sort cnt iter: " + cntOptim);
	}
}