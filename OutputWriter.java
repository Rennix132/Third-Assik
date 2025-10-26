import java.io.FileWriter;
import java.util.*;

public class OutputWriter {

    public static void write(List<Map<String, Object>> results, String filename) throws Exception {

        StringBuilder sb = new StringBuilder();
        sb.append("{\n  \"results\": [\n");

        for (int i = 0; i < results.size(); i++) {
            Map<String, Object> r = results.get(i);

            sb.append("    {\n");
            sb.append("      \"graph_id\": " + r.get("graph_id") + ",\n");

            // input stats
            Map<String, Object> stats = (Map<String, Object>) r.get("input_stats");
            sb.append("      \"input_stats\": {\n");
            sb.append("        \"vertices\": " + stats.get("vertices") + ",\n");
            sb.append("        \"edges\": " + stats.get("edges") + "\n");
            sb.append("      },\n");

            // Prim
            sb.append(writeAlgorithm(r, "prim"));
            sb.append(",\n");

            // Kruskal
            sb.append(writeAlgorithm(r, "kruskal"));
            sb.append("\n    }");

            if (i < results.size() - 1) sb.append(",\n");
            else sb.append("\n");
        }

        sb.append("  ]\n}");
        FileWriter fw = new FileWriter(filename);
        fw.write(sb.toString());
        fw.close();
    }

    private static String writeAlgorithm(Map<String, Object> r, String key) {

        StringBuilder sb = new StringBuilder();
        sb.append("      \"" + key + "\": {\n");

        Map<String, Object> algo = (Map<String, Object>) r.get(key);

        // edges
        List<Map<String, Object>> edges = (List<Map<String, Object>>) algo.get("mst_edges");
        sb.append("        \"mst_edges\": [\n");

        for (int i = 0; i < edges.size(); i++) {
            Map<String, Object> e = edges.get(i);
            sb.append("          {\"from\": \"" + e.get("from") + "\", \"to\": \"" + e.get("to") + "\", \"weight\": " + e.get("weight") + "}");
            if (i < edges.size() - 1) sb.append(",\n");
            else sb.append("\n");
        }

        sb.append("        ],\n");
        sb.append("        \"total_cost\": " + algo.get("total_cost") + ",\n");
        sb.append("        \"operations_count\": " + algo.get("operations_count") + ",\n");
        sb.append("        \"execution_time_ms\": " + algo.get("execution_time_ms") + "\n");
        sb.append("      }");

        return sb.toString();
    }
}

