import java.util.*;

public class Prim {
    public static MSTResult run(Graph g) {
        long start = System.currentTimeMillis();

        boolean[] visited = new boolean[g.vertices];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        List<Edge> mst = new ArrayList<>();
        int ops = 0;

        visited[0] = true;
        pq.addAll(g.adjacency.get(0));

        while (!pq.isEmpty() && mst.size() < g.vertices - 1) {
            Edge e = pq.poll();
            ops++;

            if (visited[e.src] && visited[e.dest]) continue;

            mst.add(e);
            int next = visited[e.src] ? e.dest : e.src;
            visited[next] = true;

            for (Edge ed : g.adjacency.get(next)) {
                if (!visited[ed.src] || !visited[ed.dest]) {
                    pq.add(ed);
                }
            }
        }

        long end = System.currentTimeMillis();
        int cost = mst.stream().mapToInt(x -> x.weight).sum();

        return new MSTResult(mst, cost, ops, end - start);
    }
}
