package test.java;

import main.java.Graph;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {



    @Test
    public void f1test() throws Exception {

        Graph graph= new Graph();
        graph.parseGraph("input.dot");

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
        graph.addEdge("y","z");

        graph.outputDOTGraph("yz_test.dot");

        String output;
        String expected;
        output = Files.readString(Paths.get("yz_test.dot"));
        expected = Files.readString(Paths.get("yz_expected.txt"));

        assertEquals(expected, output);
    }
}