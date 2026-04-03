package main.java;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.HashMap;
import java.util.LinkedList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;


public class Graph {

    public HashSet<String> nodes = new HashSet<String>();
    public ArrayList<GraphEdge> edges = new ArrayList<GraphEdge>();

    public void parseGraph(String filepath) {
        try {
            FileReader fr = new FileReader(filepath);
            BufferedReader br =new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                line = line.trim();
                if (line.contains("->")) {
                    line = line.replace(";", "");
                    String[] parts = line.split("->");
                    String from = parts[0].trim();
                    String to = parts[1].trim();
                    nodes.add(from);
                    nodes.add(to);
                    GraphEdge e = new GraphEdge(from, to);
                    edges.add(e);
                }
                line = br.readLine();
            }
            br.close();
        }

        catch (Exception e) {
            System.out.println("file err");
        }

    }

    public String toString() {
        String result = "nodes: " + nodes.size() + " " + "nodes: " + nodes + " " + "edges: " + edges.size() + "\n" + "edges:\n";

        for (int i = 0; i < edges.size(); i++) {
            result = result + edges.get(i)+ "\n";
        }
        return result;
    }

    public void outputGraph(String filepath) {
        try {
            FileWriter writer = new FileWriter(filepath);
            writer.write(toString());
            writer.close();
        } catch (Exception e) {
            System.out.println("error writing");
        }
    }

    public void addNode(String label) {
        if (nodes.contains(label)) {
            System.out.println("can't create the same node again: ");
        } else {
            nodes.add(label);
        }

    }

    public void addNodes(String[] label) {

        for (int i = 0; i < label.length; i++) {
            addNode(label[i]);
        }

    }

    public void addEdge(String srcLabel, String dstLabel) {

        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).from.equals(srcLabel) && edges.get(i).to.equals(dstLabel)) {
                return;
            }
        }
        nodes.add(srcLabel);
        nodes.add(dstLabel);

        edges.add(new GraphEdge(srcLabel, dstLabel));
    }

    public void removeNode(String label) {
        if (!nodes.contains(label)) {
            throw new IllegalArgumentException("node doesn't exist");
        }

        nodes.remove(label);
        for (int i = edges.size() - 1; i >= 0; i--) {
            GraphEdge edge = edges.get(i);
            if (edge.from.equals(label) || edge.to.equals(label)) {
                edges.remove(i);
            }
        }
    }

    public void removeNodes(String[] label) {
        for (int i = 0; i < label.length; i++) {
            if (!nodes.contains(label[i])) {
                throw new IllegalArgumentException("node doesn't exist");
            }
        }

        for (int i = 0; i < label.length; i++) {
            removeNode(label[i]);
        }
    }

    public void removeEdge(String srcLabel, String dstLabel) {
        for (int i = 0; i < edges.size(); i++) {
            GraphEdge edge = edges.get(i);
            if (edge.from.equals(srcLabel) && edge.to.equals(dstLabel)) {
                edges.remove(i);
                return;
            }
        }

        throw new IllegalArgumentException("edge doesn't exist");
    }

    public void outputDOTGraph(String path) {

        try {
            FileWriter w = new FileWriter(path);

            w.write("digraph G {\n");

            for (int i = 0; i < edges.size(); i++) {
                w.write(edges.get(i) + ";\n");
            }

            w.write("}\n");

            w.close();

        } catch (Exception e) {
            System.out.println("dot file error");
        }
    }

    public void outputGraphics(String path, String format) {

        try {
            outputDOTGraph("res.dot");
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "dot", "-T" + format, "res.dot", "-o", path
            );

            processBuilder.start().waitFor();

        } catch (Exception e) {
            System.out.println("error");
        }
    }

    public Path GraphSearch(Node src, Node dst, Algorithm algo) {
      
        if (src == null || dst == null) {
            return null;
        }

        if (!nodes.contains(src.label) || !nodes.contains(dst.label)) {
            return null;
        }

        LinkedList<String> list = new LinkedList<String>();
        HashSet<String> visited = new HashSet<String>();
        HashMap<String, String> parent = new HashMap<String, String>();

        list.add(src.label);
        parent.put(src.label, null);

        while (!list.isEmpty()) {
            String current;

            if (algo == Algorithm.DFS) {
                current = list.removeLast();
            } else {
                current = list.removeFirst();
            }

            if (visited.contains(current)) {
                continue;
            }

            visited.add(current);

            if (current.equals(dst.label)) {
                ArrayList<Node> pathNodes = new ArrayList<Node>();
                String pathNode = dst.label;

                while (pathNode != null) {
                    pathNodes.add(0, new Node(pathNode));
                    pathNode = parent.get(pathNode);
                }

                return new Path(pathNodes);
            }

            for (int i = 0; i < edges.size(); i++) {
                GraphEdge edge = edges.get(i);

                if (edge.from.equals(current) && !visited.contains(edge.to) && !parent.containsKey(edge.to)) {
                    parent.put(edge.to, current);
                    list.add(edge.to);
                }
            }
        }

        return null;
    }
}
      
enum Algorithm {
    BFS,
    DFS
}
      
class Node {

    String label;

    public Node(String label) {
        this.label = label;
    }

    public String toString() {
        return label;
    }
}

class Path {

    ArrayList<Node> nodes;

    public Path(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    public String toString() {
        String result = "";

        for (int i = 0; i < nodes.size(); i++) {
            result = result + nodes.get(i);

            if (i < nodes.size() - 1) {
                result = result + " -> ";
            }
        }

        return result;
    }
}
