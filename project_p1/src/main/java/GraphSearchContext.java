package main.java;

public class GraphSearchContext {

    private SearchStrategy searchStrategy;

    public GraphSearchContext(SearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }

    public void setSearchStrategy(SearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }

    public Path search(Graph graph, String src, String dst) {
        return searchStrategy.search(graph, src, dst);
    }
}
