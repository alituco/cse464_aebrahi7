package main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class GraphIO {

    public Graph parseGraph(String filepath) {
        Graph graph = new Graph();

        try {
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();

            while (line != null) {
                line = line.trim();

                if (line.contains("->")) {
                    line = line.replace(";", "");
                    String[] parts = line.split("->");
                    String from = parts[0].trim();
                    String to = parts[1].trim();
                    graph.addEdge(from, to);
                }

                line = br.readLine();
            }

            br.close();
        } catch (Exception e) {
            System.out.println("file err");
        }

        return graph;
    }

    public void outputGraph(Graph graph, String filepath) {
        try {
            FileWriter writer = new FileWriter(filepath);
            writer.write(graph.toString());
            writer.close();
        } catch (Exception e) {
            System.out.println("error writing");
        }
    }

    public void outputDOTGraph(Graph graph, String path) {
        try {
            FileWriter writer = new FileWriter(path);

            writer.write("digraph G {\n");

            for (int i = 0; i < graph.edges.size(); i++) {
                writer.write(graph.edges.get(i) + ";\n");
            }

            writer.write("}\n");
            writer.close();
        } catch (Exception e) {
            System.out.println("dot file error");
        }
    }

    public void outputGraphics(Graph graph, String path, String format) {
        try {
            outputDOTGraph(graph, "res.dot");

            ProcessBuilder processBuilder = new ProcessBuilder(
                    "dot", "-T" + format, "res.dot", "-o", path
            );

            processBuilder.start().waitFor();
        } catch (Exception e) {
            System.out.println("error");
        }
    }
}
