import java.util.*;

public class Matrix {
    private int[][] innerMatrix;

    public Matrix(int len){
        innerMatrix = new int[len][len];
    }

    public int getElement(int i, int j){
        return innerMatrix[i][j];
    }

    public void setElement(int i, int j,int value){
        innerMatrix[i][j] = value;
    }

    public int getMatrixSize(){
        return innerMatrix.length;
    }

    public void printMatrix(){
        for (int i = 0; i <innerMatrix.length ; i++) {
            System.out.println(Arrays.toString(innerMatrix[i]));
        }
    }

    public static int determinant(Matrix matrix) {
        if (matrix.getMatrixSize() != 3) {
            throw new RuntimeException("размер матрицы должен быть 3х3");
        } else {
           return  (matrix.getElement(0,0) * matrix.getElement(1,1) * matrix.getElement(2,2) +
                   matrix.getElement(1,0) * matrix.getElement(2,1) * matrix.getElement(0,2) +
                   matrix.getElement(0,1) * matrix.getElement(1,2) * matrix.getElement(2,0) -
                   matrix.getElement(0,2) * matrix.getElement(1,1) * matrix.getElement(2,0) -
                   matrix.getElement(0,0) * matrix.getElement(2,1) * matrix.getElement(1,2) -
                   matrix.getElement(1,0) * matrix.getElement(0,1) * matrix.getElement(2,2));
        }
    }

    public int sumMatrixElements() {
        int sum = 0;
        for (int i = 0; i < innerMatrix.length; i++) {
            for (int j = 0; j < innerMatrix.length; j++) {
                sum += innerMatrix[i][j];
            }
        }
        return sum;
    }
}