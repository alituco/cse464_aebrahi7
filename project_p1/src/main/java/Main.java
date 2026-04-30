package main.java;

public class Main {
    public static void main(String[] args) {
        GraphIO graphIO = new GraphIO();
        Graph graph = graphIO.parseGraph("input.dot");

        for (int i = 0; i < 3; i++) {
            Path path = null;

            while (path == null) {
                System.out.println("random testing");
                path = graph.GraphSearch("a", "c", Algorithm.RANDOM_WALK);
            }

            System.out.println(path);
        }
    }
}
