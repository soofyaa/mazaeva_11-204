import java.util.*;

public class Main5 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter len: ");
		int len = scanner.nextInt();
		int[] arr = new int[len];
		System.out.println("Enter elements: ");
		for (int i = 0; i < len; i++) {
			arr[i] = scanner.nextInt();
		}
		System.out.println(Arrays.toString(arr));
		System.out.println(Arrays.toString(sortArray(arr)));
	}

	public static int[] sortArray(int[] array) {
		for (int curMin = 0; curMin < array.length; curMin++) {
			int minEl = array[curMin];
			for (int current = curMin; current < array.length; current++) {
				if (array[current] < array[curMin]) {
					minEl = array[current];
					array[current] = array[curMin];
					array[curMin] = minEl;
				}
			}
		}
		return array;
	}
}