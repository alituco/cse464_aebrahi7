public class Main {
    public static void main(String[] args) {
        Graph g = new Graph();

        g.parseGraph("input.dot");
        g.addNode("e");

        String[] newNodes = {"f", "g", "a"};
        g.addNodes(newNodes);
        g.addEdge("d", "e");
        g.addEdge("a", "b");

        System.out.println(g);

    }
}