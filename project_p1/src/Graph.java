import java.util.ArrayList;

import java.util.HashSet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;


public class Graph {

    HashSet<String> nodes = new HashSet<String>();
    ArrayList<GraphEdge> edges = new ArrayList<GraphEdge>();

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
}