import java.util.*;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("len matrix1: ");
        Matrix matrix = new Matrix(scanner.nextInt());
        System.out.println("elements matrix1: ");
        for (int i = 0; i < matrix.getMatrixSize(); i++) {
            for (int j = 0; j <matrix.getMatrixSize(); j++) {
                matrix.setElement(i, j, scanner.nextInt());
            }
        }
        matrix.printMatrix();

        System.out.println("summa elementov = " + matrix.sumMatrixElements());
        System.out.println("determinant = " + Matrix.determinant(matrix));
    }
}