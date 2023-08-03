import java.io.*;
import java.util.*;

public class Tree {
    private int value;
    private Tree left;
    private Tree right;
    private Tree parent;

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

    public void remove(int value) {
        Tree needTree = get(value);

        if (needTree == null) {
            return;
        }

        if (needTree.left == null && needTree.right == null) {
            if (needTree.parent.left == needTree) {
                needTree.parent.left = null;
            } else {
                needTree.parent.right = null;
            }
        }

        else if (needTree.left == null) {
            needTree.value = needTree.right.value;
            needTree.left = needTree.right.left;
            needTree.right = needTree.right.right;
        }

        else if (needTree.right == null) {
            needTree.value = needTree.left.value;
            needTree.right = needTree.left.right;
            needTree.left = needTree.left.left;
        }

        else {
            Tree minTree = needTree.right.getMinTree();
            needTree.value = minTree.value;
            minTree.remove(minTree.value);
        }
    }

    @Override
    public String toString() {
        String result = "";
        Queue<Tree> queue = new LinkedList<>();
        queue.add(this);
        while (!queue.isEmpty()) {
            Tree current = queue.remove();
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
            result += (!(queue.peek() == null) && (current.getLevel() != queue.peek().getLevel())) ?
                    current.value + current.toStringParent() + "\n" :
                    current.value + current.toStringParent();
        }
        return result;
    }

    public void writeToFile(String fileName) {
        RandomAccessFile file = null;
        try {
            file = new RandomAccessFile(fileName, "rw");
            file.writeBytes(this.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private int getLevel() {
        int level = 0;
        Tree currentTree = this;
        while (currentTree.parent != null) {
            level++;
            currentTree = currentTree.parent;
        }
        return level;
    }

    private String toStringParent() {
        String result = "";
        if (this.getLevel() > 1) {
            result += "(" + this.parent.value + ")";
        }
        return result + " ";
    }

    private Tree getMinTree() {
        Tree tree = this;
        while (tree.left != null) {
            tree = tree.left;
        }
        return tree;
    }
}