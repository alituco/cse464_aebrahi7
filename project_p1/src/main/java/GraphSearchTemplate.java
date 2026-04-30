package main.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public abstract class GraphSearchTemplate {

    public Path search(Graph graph, Node src, Node dst) {
        if (src == null || dst == null) {
            return null;
        }

        if (!graph.nodes.contains(src.label) || !graph.nodes.contains(dst.label)) {
            return null;
        }

        LinkedList<String> list = new LinkedList<String>();
        HashSet<String> visited = new HashSet<String>();
        HashMap<String, String> parent = new HashMap<String, String>();

        list.add(src.label);
        parent.put(src.label, null);

        while (!list.isEmpty()) {
            String current = getNextNode(list);

            if (visited.contains(current)) {
                continue;
            }

            visited.add(current);

            if (current.equals(dst.label)) {
                return buildPath(parent, dst.label);
            }

            for (int i = 0; i < graph.edges.size(); i++) {
                GraphEdge edge = graph.edges.get(i);

                if (edge.from.equals(current) && !visited.contains(edge.to) && !parent.containsKey(edge.to)) {
                    parent.put(edge.to, current);
                    list.add(edge.to);
                }
            }
        }

        return null;
    }

    protected abstract String getNextNode(LinkedList<String> list);

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
