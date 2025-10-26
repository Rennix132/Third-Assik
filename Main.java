import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        List<Graph> graphs = GraphLoader.load("input.json");

        List<Map<String, Object>> results = new ArrayList<>();
        int id = 1;

        for (Graph g : graphs) {

            MSTResult prim = Prim.run(g);
            MSTResult kr = Kruskal.run(g);

            Map<String, Object> entry = new LinkedHashMap<>();

            entry.put("graph_id", id++);
            entry.put("input_stats", Map.of(
                    "vertices", g.vertices,
                    "edges", g.edges.size()
            ));

            entry.put("prim", convert(g, prim));
            entry.put("kruskal", convert(g, kr));

            results.add(entry);
        }

        OutputWriter.write(results, "output.json");
        System.out.println("OUTPUT saved to output.json");
    }

    static Map<String, Object> convert(Graph g, MSTResult r) {
        List<Map<String, Object>> list = new ArrayList<>();
        for (Edge e : r.edges) {
            list.add(Map.of(
                    "from", g.names[e.src],
                    "to", g.names[e.dest],
                    "weight", e.weight
            ));
        }
        return Map.of(
                "mst_edges", list,
                "total_cost", r.cost,
                "operations_count", r.operations,
                "execution_time_ms", r.ms
        );
    }
}
