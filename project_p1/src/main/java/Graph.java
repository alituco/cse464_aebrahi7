package main.java;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashSet;


public class Graph {

    private static final EnumMap<Algorithm, SearchStrategy> SEARCH_STRATEGIES = createSearchStrategies();

    public HashSet<String> nodes = new HashSet<String>();
    public ArrayList<GraphEdge> edges = new ArrayList<GraphEdge>();

    public String toString() {
        String result = "nodes: " + nodes.size() + " " + "nodes: " + nodes + " " + "edges: " + edges.size() + "\n" + "edges:\n";

        for (int i = 0; i < edges.size(); i++) {
            result = result + edges.get(i)+ "\n";
        }
        return result;
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
        if (hasEdge(srcLabel, dstLabel)) {
            return;
        }

        nodes.add(srcLabel);
        nodes.add(dstLabel);

        edges.add(new GraphEdge(srcLabel, dstLabel));
    }

    private boolean hasEdge(String srcLabel, String dstLabel) {
        for (int i = 0; i < edges.size(); i++) {
            GraphEdge edge = edges.get(i);

            if (edge.from.equals(srcLabel) && edge.to.equals(dstLabel)) {
                return true;
            }
        }

        return false;
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
        validateNodesExist(label);

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

    public Path GraphSearch(String src, String dst, Algorithm algo) {
        GraphSearchContext context = new GraphSearchContext(getSearchStrategy(algo));
        return context.search(this, src, dst);
    }

    public Path GraphSearch(Node src, Node dst, Algorithm algo) {
        if (src == null || dst == null) {
            return null;
        }

        return GraphSearch(src.label, dst.label, algo);
    }

    private SearchStrategy getSearchStrategy(Algorithm algo) {
        SearchStrategy searchStrategy = SEARCH_STRATEGIES.get(algo);

        if (searchStrategy == null) {
            throw new IllegalArgumentException("unknown algorithm");
        }

        return searchStrategy;
    }

    private static EnumMap<Algorithm, SearchStrategy> createSearchStrategies() {
        EnumMap<Algorithm, SearchStrategy> searchStrategies = new EnumMap<Algorithm, SearchStrategy>(Algorithm.class);

        searchStrategies.put(Algorithm.BFS, new BfsSearch());
        searchStrategies.put(Algorithm.DFS, new DfsSearch());
        searchStrategies.put(Algorithm.RANDOM_WALK, new RandomWalkSearch());

        return searchStrategies;
    }

    private void validateNodesExist(String[] labels) {
        for (int i = 0; i < labels.length; i++) {
            if (!nodes.contains(labels[i])) {
                throw new IllegalArgumentException("node doesn't exist");
            }
        }
    }
}
      
enum Algorithm {
    BFS,
    DFS,
    RANDOM_WALK
}
      
class Node {

    String label;

    public Node(String label) {
        this.label = label;
    }

    public String toString() {
        return "Node{" + label + "}";
    }
}

class Path {

    ArrayList<Node> nodes;

    public Path(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    public String toString() {
        return "Path{nodes=" + nodes + "}";
    }
}
