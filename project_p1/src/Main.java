public class Main {
    public static void main(String[] args) {
        Graph g = new Graph();

        g.parseGraph("input.dot");

        g.addNode("e");

        String[] newNodes = {"f", "g", "a"};
        g.addNodes(newNodes);

        System.out.println(g);

    }
}