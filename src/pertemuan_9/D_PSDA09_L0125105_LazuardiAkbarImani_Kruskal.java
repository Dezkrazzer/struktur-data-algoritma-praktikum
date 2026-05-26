package pertemuan_9;
import java.util.*;

class Edge implements Comparable<Edge> {
    String source;
    String destination;
    int weight;

    public Edge(String source, String destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

class DisjointSet {
    @SuppressWarnings("FieldMayBeFinal")
    private Map<String, String> parent = new HashMap<>();

    public void makeSet(String node) {
        parent.put(node, node);
    }

    public String find(String node) {
        if (!parent.get(node).equals(node)) {
            parent.put(node, find(parent.get(node)));
        }
        return parent.get(node);
    }

    public void union(String a, String b) {
        String rootA = find(a);
        String rootB = find(b);

        if (!rootA.equals(rootB)) {
            parent.put(rootA, rootB);
        }
    }
}

public class D_PSDA09_L0125105_LazuardiAkbarImani_Kruskal {
    public static void main(String[] args) {

        List<Edge> edges = new ArrayList<>();

        // Menambahkan edge
        edges.add(new Edge("0", "3", 3));
        edges.add(new Edge("1", "3", 6));
        edges.add(new Edge("1", "6", 4));
        edges.add(new Edge("2", "5", 8));
        edges.add(new Edge("3", "5", 7));
        edges.add(new Edge("3", "7", 7));
        edges.add(new Edge("4", "6", 7));
        edges.add(new Edge("4", "7", 7));

        // Mengurutkan edge berdasarkan bobot (ascending)
        Collections.sort(edges);

        DisjointSet ds = new DisjointSet();

        // Node pada graph adalah 0 sampai 7
        String[] nodes = {"0", "1", "2", "3", "4", "5", "6", "7"};

        for (String node : nodes) {
            ds.makeSet(node);
        }

        List<Edge> mst = new ArrayList<>();
        int totalWeight = 0;

        for (Edge edge : edges) {
            String rootSource = ds.find(edge.source);
            String rootDestination = ds.find(edge.destination);

            // Jika belum terhubung (tidak membentuk cycle), maka ambil edge
            if (!rootSource.equals(rootDestination)) {
                mst.add(edge);
                totalWeight += edge.weight;
                ds.union(edge.source, edge.destination);
            }
        }

        System.out.println("Minimum Spanning Tree:");
        for (Edge edge : mst) {
            System.out.println(
                edge.source + " - " +
                edge.destination + " : " +
                edge.weight
            );
        }

        System.out.println("Total Bobot: " + totalWeight);
    }
}
