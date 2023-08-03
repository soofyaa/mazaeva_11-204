public class оо {

    public static int searchInSortedMatrix(int[][] matrix, int x) {
        int n = matrix.length;
        int m = matrix[0].length;
        int row = 0;
        int col = m - 1;
        while (row < n && col >= 0) {
            if (matrix[row][col] == x) {
                return row * m + col;
            } else if (matrix[row][col] < x) {
                // Искомый элемент может быть только в строках ниже текущей
                row++;
            } else {
                // Искомый элемент может быть только в текущей строке
                int lo = 0;
                int hi = col;
                while (lo <= hi) {
                    int mid = (lo + hi) / 2;
                    if (matrix[row][mid] == x) {
                        return row * m + mid;
                    } else if (matrix[row][mid] < x) {
                        lo = mid + 1;
                    } else {
                        hi = mid - 1;
                    }
                }
                // Искомый элемент не найден в текущей строке
                col = hi;
            }
        }
        // Искомый элемент не найден
        return -1;
    }
}
