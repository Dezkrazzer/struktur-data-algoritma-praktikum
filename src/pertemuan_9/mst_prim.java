package pertemuan_9;
import java.util.*;

class Edge {
    String destination;
    int weight;

    public Edge(String destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }
}

public class mst_prim {
    @SuppressWarnings("FieldMayBeFinal")
    private static Map<String, List<Edge>> graph = new HashMap<>();

    public static void addEdge(String source, String destination, int weight) {

        graph.putIfAbsent(source, new ArrayList<>());
        graph.putIfAbsent(destination, new ArrayList<>());

        graph.get(source).add(new Edge(destination, weight));
        graph.get(destination).add(new Edge(source, weight));
    }

    public static void prim(String start) {

        Set<String> visited = new HashSet<>();

        PriorityQueue<Edge> pq =
            new PriorityQueue<>((a, b) -> a.weight - b.weight);

        visited.add(start);

        pq.addAll(graph.get(start));

        int totalWeight = 0;

        System.out.println("Minimum Spanning Tree (Prim's Algorithm):");

        while (!pq.isEmpty()) {

            Edge current = pq.poll();

            if (visited.contains(current.destination)) {
                continue;
            }

            visited.add(current.destination);

            System.out.println(
                "Ke " + current.destination +
                " dengan bobot " + current.weight
            );

            totalWeight += current.weight;

            for (Edge next : graph.get(current.destination)) {

                if (!visited.contains(next.destination)) {
                    pq.offer(next);
                }
            }
        }

        System.out.println("Total Bobot: " + totalWeight);
    }
    public static void main(String[] args) {

        // Menambahkan edge berdasarkan gambar graph
        addEdge("0", "3", 3);
        addEdge("1", "3", 6);
        addEdge("1", "6", 4);
        addEdge("2", "5", 8);
        addEdge("3", "5", 7);
        addEdge("3", "7", 7);
        addEdge("4", "6", 7);
        addEdge("4", "7", 7);

        // Memulai algoritma Prim dari node "0"
        prim("0");
    }
}
