package main.java;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RandomWalkSearch extends GraphSearchTemplate {

    private final Random random = new Random();

    @Override
    protected String getNextNode(LinkedList<String> list) {
        return list.removeFirst();
    }

    @Override
    protected void onVisit(Path path) {
        System.out.println("visiting " + path);
    }

    @Override
    protected void addNextNodes(
            Graph graph,
            String current,
            LinkedList<String> list,
            HashSet<String> visited,
            HashMap<String, String> parent
    ) {
        List<String> neighbors = getAvailableNeighbors(graph, current, visited, parent);

        if (neighbors.isEmpty()) {
            return;
        }

        String nextNode = neighbors.get(random.nextInt(neighbors.size()));
        parent.put(nextNode, current);
        list.add(nextNode);
    }
}
