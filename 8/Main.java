public class Main {
    public static void main(String[] args) {
        Tree tree = new Tree(3);
        tree.add(1);
        tree.add(9);
        tree.add(1);
        tree.add(7);
        tree.add(10);
        tree.add(6);
        tree.add(8);

        System.out.println(tree);
       tree.writeToFile("test.txt");
        System.out.println("--------------");
        tree.remove(0);
        System.out.println(tree);
    }
}
