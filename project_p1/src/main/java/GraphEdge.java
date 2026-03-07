package main.java;

public class GraphEdge {

    String from, to;

    public GraphEdge(String fromNode, String toNode) {
        this.from = fromNode;
        this.to = toNode;
    }

    public String toString() {
        return from + " -> " + to;
    }
}