import java.util.List;

public class MSTResult {
    public List<Edge> edges;
    public int cost;
    public int operations;
    public long ms;

    public MSTResult(List<Edge> edges, int cost, int operations, long ms) {
        this.edges = edges;
        this.cost = cost;
        this.operations = operations;
        this.ms = ms;
    }
}
