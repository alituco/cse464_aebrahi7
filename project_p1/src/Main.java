public class Main {
    public static void main(String[] args) {
        Graph g = new Graph();
        g.parseGraph("input.dot");
        System.out.println(g);
        g.outputGraph("output.txt");
    }
}