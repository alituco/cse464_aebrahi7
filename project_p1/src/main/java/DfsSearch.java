package main.java;

import java.util.LinkedList;

public class DfsSearch extends GraphSearchTemplate {

    @Override
    protected String getNextNode(LinkedList<String> list) {
        return list.removeLast();
    }
}
