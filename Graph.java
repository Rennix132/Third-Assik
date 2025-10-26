import java.util.*;

public class Graph {
    public int vertices;
    public List<Edge> edges;
    public List<List<Edge>> adjacency;
    public String[] names;

    public Graph(int vertices) {
        this.vertices = vertices;
        edges = new ArrayList<>();
        adjacency = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjacency.add(new ArrayList<>());
        }
    }

    public void addEdge(int src, int dest, int w) {
        Edge e = new Edge(src, dest, w);
        edges.add(e);
        adjacency.get(src).add(e);
        adjacency.get(dest).add(e);
    }
}

