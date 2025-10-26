import java.util.*;

public class Kruskal {
    public static MSTResult run(Graph g) {
        long start = System.currentTimeMillis();

        Collections.sort(g.edges);
        DisjointSet ds = new DisjointSet(g.vertices);

        List<Edge> mst = new ArrayList<>();

        for (Edge e : g.edges) {
            int a = ds.find(e.src);
            int b = ds.find(e.dest);

            if (a != b) {
                mst.add(e);
                ds.union(a, b);
            }
            if (mst.size() == g.vertices - 1) break;
        }

        long end = System.currentTimeMillis();
        int cost = mst.stream().mapToInt(x -> x.weight).sum();

        return new MSTResult(mst, cost, ds.operations, end - start);
    }
}
