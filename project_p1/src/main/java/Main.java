package main.java;

public class Main {
    public static void main(String[] args) {
        Graph g = new Graph();

        g.parseGraph("input.dot");

        g.addEdge("d","e");

        g.outputDOTGraph("graph.dot");
        g.outputGraphics("graph.png","png");

        System.out.println(g);

    }
}