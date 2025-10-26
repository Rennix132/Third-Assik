import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.*;

public class GraphLoader {

    public static List<Graph> load(String filename) throws Exception {

        String text = Files.readString(Paths.get(filename));

        // Извлекаем массив узлов
        Pattern nodesPattern = Pattern.compile("\"nodes\":\\s*\\[(.*?)\\]", Pattern.DOTALL);
        // Извлекаем массив ребер
        Pattern edgesPattern = Pattern.compile("\"edges\":\\s*\\[(.*?)\\]", Pattern.DOTALL);

        Matcher mNodes = nodesPattern.matcher(text);
        Matcher mEdges = edgesPattern.matcher(text);

        List<Graph> graphs = new ArrayList<>();

        while (mNodes.find() && mEdges.find()) {

            String nodesBlock = mNodes.group(1);
            String edgesBlock = mEdges.group(1);

            // Парсим узлы
            String[] nodeStrings = nodesBlock.replace("\"", "").split(",");
            int n = nodeStrings.length;

            Graph g = new Graph(n);
            g.names = new String[n];

            Map<String, Integer> map = new HashMap<>();

            for (int i = 0; i < n; i++) {
                String name = nodeStrings[i].trim();
                g.names[i] = name;
                map.put(name, i);
            }

            // Парс ребер
            String[] rawEdges = edgesBlock.split("\\},");
            for (String e : rawEdges) {
                e = e.replace("{", "").replace("}", "");

                String[] parts = e.split(",");

                String from = parts[0].split(":")[1].replace("\"", "").trim();
                String to = parts[1].split(":")[1].replace("\"", "").trim();
                int w = Integer.parseInt(parts[2].split(":")[1].trim());

                g.addEdge(map.get(from), map.get(to), w);
            }

            graphs.add(g);
        }

        return graphs;
    }
}
