public class Main1 {
    public class GraphCode {
        private static final int SIZE = 3;
        private Node firstNode;
        private Node lastNode;
        private int pointer = 0;

        private class Node {
            private int i;
            private int j;
            private int rebro;

            public Node getNext() {
                return next;
            }

            private Node next;

            public void setNext(Node next) {
                this.next = next;
            }

            public Node(int i, int j) {
                this.i = i;
                this.j = j;
                if (i == j) {
                    rebro = 2;
                } else {
                    rebro = 1;
                }
                this.next = null;
            }
        }
        public GraphCode() {}

        public GraphCode(int[][] matrix) {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (matrix[i][j] != 0) {
                        insert(i, j);
                    }
                }
            }
        }

        public boolean insert(int i, int j) {
            Node newNode = new Node(i, j);
            Node previous = null;
            Node current = firstNode;


            while (current != null && current.i == i && current.j == j) {
                previous = current;
                current = current.next;
            }

            while (current != null && current.i == i && current.j < j) {
                previous = current;
                current = current.next;
            }

            if (current != null && current.i == 1 && current.j == j) {
                return false;
            }

            if (firstNode == null) {
                firstNode = newNode;
                lastNode = firstNode;
            } else {
                lastNode.setNext(newNode);
                lastNode = lastNode.getNext();
            }

            pointer++;
            return true;
        }

        public boolean delete(int i, int j) {
            Node previous = null;
            Node current = firstNode;

            while (current != null && current.i == i && current.j == j) {
                previous = current;
                current = current.next;
            }

            while (current != null && current.i == i && current.j < j) {
                previous = current;
                current = current.next;
            }

            if (current != null && current.i == 1 && current.j == j) {
                current = current.next;
                return true;
            }
            return false;
        }

        public GraphCode returnVersh(int i) {
            Node previous = null;
            Node current = firstNode;
            GraphCode graphCode = new GraphCode();


            while (current != null && current.i != i) {
                previous = current;
                current = current.next;
            }

            for (int j = 0; j < SIZE; j++) {
                if (current.rebro != 0) {
                    graphCode.insert(i, j);
                }
            }

            return graphCode;
        }

        public void modify






    }
}
