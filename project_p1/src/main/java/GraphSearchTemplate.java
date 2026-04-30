package main.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public abstract class GraphSearchTemplate implements SearchStrategy {

    @Override
    public Path search(Graph graph, String src, String dst) {
        if (src == null || dst == null) {
            return null;
        }

        if (!graph.nodes.contains(src) || !graph.nodes.contains(dst)) {
            return null;
        }

        LinkedList<String> list = new LinkedList<String>();
        HashSet<String> visited = new HashSet<String>();
        HashMap<String, String> parent = new HashMap<String, String>();

        list.add(src);
        parent.put(src, null);

        while (!list.isEmpty()) {
            String current = getNextNode(list);

            if (visited.contains(current)) {
                continue;
            }

            visited.add(current);
            onVisit(buildPath(parent, current));

            if (current.equals(dst)) {
                return buildPath(parent, dst);
            }

            addNextNodes(graph, current, list, visited, parent);
        }

        return null;
    }

    protected abstract String getNextNode(LinkedList<String> list);

    protected void onVisit(Path path) {
    }

    protected void addNextNodes(
            Graph graph,
            String current,
            LinkedList<String> list,
            HashSet<String> visited,
            HashMap<String, String> parent
    ) {
        List<String> neighbors = getAvailableNeighbors(graph, current, visited, parent);

        for (int i = 0; i < neighbors.size(); i++) {
            String nextNode = neighbors.get(i);
            parent.put(nextNode, current);
            list.add(nextNode);
        }
    }

    protected List<String> getAvailableNeighbors(
            Graph graph,
            String current,
            HashSet<String> visited,
            HashMap<String, String> parent
    ) {
        ArrayList<String> neighbors = new ArrayList<String>();

        for (int i = 0; i < graph.edges.size(); i++) {
            GraphEdge edge = graph.edges.get(i);

            if (edge.from.equals(current) && !visited.contains(edge.to) && !parent.containsKey(edge.to)) {
                neighbors.add(edge.to);
            }
        }

        return neighbors;
    }

    private Path buildPath(HashMap<String, String> parent, String dstLabel) {
        ArrayList<Node> pathNodes = new ArrayList<Node>();
        String pathNode = dstLabel;

        while (pathNode != null) {
            pathNodes.add(0, new Node(pathNode));
            pathNode = parent.get(pathNode);
        }

        return new Path(pathNodes);
    }
}
