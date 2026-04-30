package main.java;

import java.util.LinkedList;

public class BfsSearch extends GraphSearchTemplate {

    @Override
    protected String getNextNode(LinkedList<String> list) {
        return list.removeFirst();
    }
}
