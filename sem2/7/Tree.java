import java.util.*;

    /*
    Модифицировать класс Tree таким образом, чтобы каждый узел еще хранил своего родителя. Реализовать методы:
Tree get(int value) - метод, который возвращает найденное по value Tree
Tree add(int value) - метод, который добавляет новый узел с value
     */

public class Tree {
    private int value;
    private Tree left;
    private Tree right;
    private Tree parent;

    public Tree() {}

    public Tree(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    public Tree get(int value) {
        if (value < this.value) {
            return left != null ? left.get(value) : null;
        }
        if (value > this.value) {
            return right != null ? right.get(value) : null;
        }
        return this;
    }

    public Tree add(int value) {
        if (value < this.value) {
            if (left == null) {
                left = new Tree(value);
                left.parent = this;
                return left;
            }
            return left.add(value);
        } else {
            if (right == null) {
                right = new Tree(value);
                right.parent = this;
                return right;
            }
            return right.add(value);
        }
    }

    @Override
    public String toString() { // in wide
        String s = "Tree{";
        Queue<Tree> queue = new LinkedList<>();
        queue.add(this);

        while (!queue.isEmpty()) {
            Tree current = queue.remove();
            s += " " + current.value;

            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }

        return s += " }";
    }
/*
    public String toString() { // in deep
        String s = "Tree{";
        Stack<Tree> stack = new Stack<>();
        stack.push(this);
        while (!stack.isEmpty()) {
            Tree current = stack.pop();
            s += " " + current.value;

            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }
        return s += " }";
    }
 */
}