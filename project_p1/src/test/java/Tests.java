package test.java;

import main.java.Graph;
import main.java.GraphIO;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {



    @Test
    public void f1test() throws Exception {

        GraphIO graphIO = new GraphIO();
        Graph graph = graphIO.parseGraph("input.dot");

        String output = graph.toString();

        assertTrue(output.contains("a -> b"));
        assertTrue(output.contains("b -> c"));
        assertTrue(output.contains("c -> d"));
    }

    @Test
    public void f2test() {

        Graph graph = new Graph();

        graph.addNode("x");
        graph.addNode("x");

        String[] labels = {"a","b","c","a"};
        graph.addNodes(labels);

        assertTrue(graph.nodes.contains("a") && graph.nodes.contains("b") && graph.nodes.contains("c"));
    }

    @Test
    public void f3test() {

        Graph graph =new Graph();

        graph.addEdge("a", "b");
        graph.addEdge("b", "c");
        graph.addEdge("c", "d");
        graph.addEdge("a", "b");  // duplicate
        assertEquals(3, graph.edges.size());
    }


    @Test
    public void f4test() throws Exception {

        Graph graph = new Graph();
        GraphIO graphIO = new GraphIO();
        graph.addEdge("y","z");

        graphIO.outputDOTGraph(graph, "yz_test.dot");

        String output;
        String expected;
        output = readFile("yz_test.dot");
        expected = readFile("yz_expected.txt");

        assertEquals(expected, output);
    }

    @Test
    public void removeTest() {
        Graph graph = new Graph();
        graph.addEdge("a", "b");
        graph.addEdge("b", "c");
        graph.addEdge("c", "d");
        graph.addEdge("d", "e");

        graph.removeEdge("a", "b");
        graph.removeNode("c");
        graph.removeNodes(new String[]{"d"});

        assertFalse(graph.nodes.contains("c"));
        assertFalse(graph.nodes.contains("d"));
        assertTrue(graph.nodes.contains("a"));
        assertTrue(graph.nodes.contains("b"));
        assertTrue(graph.nodes.contains("e"));
        assertEquals(0, graph.edges.size());
    }

    @Test
    public void removeMissing() {
        Graph graph = new Graph();
        graph.addNode("a");
        graph.addNode("b");

        try {
            graph.removeNode("missing");
            fail();
        } catch (IllegalArgumentException e) {
        }

        try {
            graph.removeNodes(new String[]{"a", "missing"});
            fail();
        } catch (IllegalArgumentException e) {
        }
        assertTrue(graph.nodes.contains("a"));
        assertTrue(graph.nodes.contains("b"));
    }

    @Test
    public void removeEdgeMissing() {
        Graph graph = new Graph();
        graph.addEdge("a", "b");
        graph.addEdge("b", "c");

        try {
            graph.removeEdge("a", "c");
            fail();
        } catch (IllegalArgumentException e) {
        }

        try {
            graph.removeEdge("x", "y");
            fail();
        } catch (IllegalArgumentException e) {
        }
        assertEquals(2, graph.edges.size());
    }

    public String readFile(String path) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String text = "";
        String line = reader.readLine();

        while (line != null) {
            text = text + line + "\n";
            line = reader.readLine();
        }

        reader.close();
        return text;
    }
}
