package main.java;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();

        graph.addEdge("y", "z");

        graph.outputDOTGraph("yz_test.dot");
        graph.outputGraphics("graph.png", "png");

        System.out.println("files created.");
    }


}