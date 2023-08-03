public class MatrixCode {
    private static final int MATRIX_SIZE = 3;

    private Node head;

    private static class Node {
        int i;
        int j;
        int value;
        Node next;

        Node(int i, int j, int value) {
            this.i = i;
            this.j = j;
            this.value = value;
        }
    }

    public MatrixCode(int[][] matrix) {
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                if (matrix[i][j] != 0) {
                    insert(i, j, matrix[i][j]);
                }
            }
        }
    }

    public int[][] decode() {
        int[][] matrix = new int[MATRIX_SIZE][MATRIX_SIZE];

        Node current = head;
        while (current != null) {
            matrix[current.i][current.j] = current.value;
            current = current.next;
        }
        return matrix;
    }

    public void insert(int i, int j, int value) {
        Node newNode = new Node(i, j, value);
        Node previous = null;
        Node current = head;

        while (current != null && current.i < i) {
            previous = current;
            current = current.next;
        }

        while (current != null && current.i == i && current.j < j) {
            previous = current;
            current = current.next;
        }

        if (current != null && current.i == i && current.j == j) {
            current.value = value;
        } else {
            newNode.next = current;
            if (previous == null) {
                head = newNode;
            } else {
                previous.next = newNode;
            }
        }
    }

    public void delete(int i, int j) {
        Node previous = null;
        Node current = head;

        while (current != null && current.i < i) {
            previous = current;
            current = current.next;
        }

        while (current != null && current.i == i && current.j < j) {
            previous = current;
            current = current.next;
        }

        if (current != null && current.i == i && current.j == j) {
            if (previous == null) {
                head = current.next;
            } else {
                previous.next = current.next;
            }
        }
    }

    public int sum() {
        int sum = 0;

        Node current = head;
        while (current != null) {
            if (current.i == current.j) {
                sum += current.value;
            }
            current = current.next;
        }

        return sum;
    }
}