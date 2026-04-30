package main.java;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        GraphIO graphIO = new GraphIO();

        graph.addEdge("y", "z");

        graphIO.outputDOTGraph(graph, "yz_test.dot");
        graphIO.outputGraphics(graph, "graph.png", "png");

        System.out.println("files created.");
    }


}
