/*
Nama : Lazuardi Akbar Imani
NIM  : L0125105
Kelas: Informatika 2025D
*/
package pertemuan_10;
import java.util.*;

public class D_PSDA10_L0125105_LazuardiAkbarImani_k2 {

    static class Edge {
        int tujuan;
        int bobot;

        Edge(int tujuan, int bobot) {
            this.tujuan = tujuan;
            this.bobot = bobot;
        }
    }

    static class Node implements Comparable<Node> {
        int vertex;
        int jarak;

        Node(int vertex, int jarak) {
            this.vertex = vertex;
            this.jarak = jarak;
        }

        @Override
        public int compareTo(Node other) {
            return this.jarak - other.jarak;
        }
    }

    static void dijkstra(List<List<Edge>> graph, int source) {
        int V = graph.size();
        int[] distance = new int[V];

        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(source, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.vertex;

            for (Edge edge : graph.get(u)) {
                int v = edge.tujuan;
                int bobot = edge.bobot;

                // Relaxation
                if (distance[u] + bobot < distance[v]) {
                    distance[v] = distance[u] + bobot;
                    pq.add(new Node(v, distance[v]));
                }
            }
        }

        System.out.println("=== Hasil Jarak Terpendek dari Node " + source + " (Kasus 2) ===");
        for (int i = 0; i < V; i++) {
            System.out.println("Jarak ke node " + i + " = " + distance[i]);
        }
    }

    public static void main(String[] args) {
        int V = 7;
        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        // Memasukkan data edge
        graph.get(0).add(new Edge(1, 6));
        graph.get(0).add(new Edge(2, 1));
        graph.get(1).add(new Edge(3, 5));
        graph.get(2).add(new Edge(1, 2));
        graph.get(2).add(new Edge(4, 7));
        graph.get(3).add(new Edge(5, 3));
        graph.get(4).add(new Edge(5, 1));
        graph.get(5).add(new Edge(6, 4));
        graph.get(1).add(new Edge(6, 15));
        graph.get(0).add(new Edge(6, 25));

        /* * ANALISIS SINGKAT:
         * Menunjukkan bagaimana jalur tidak langsung bisa jauh lebih cepat.
         * Jalur langsung dari Node 0 ke 6 memiliki bobot 25.
         * Jalur langsung dari Node 0 ke 1 memiliki bobot 6. 
         * Dijkstra menemukan bahwa ke Node 1 lebih murah melewati Node 2 (0 -> 2 -> 1 dengan total bobot 3).
         * Melalui eksplorasi lebih lanjut dengan Priority Queue, jalur paling optimal ke Node 6
         * tidak melalui jalur langsung, melainkan melalui 0 -> 2 -> 1 -> 3 -> 5 -> 6
         * (total jarak: 1 + 2 + 5 + 3 + 4 = 15).
         */

        dijkstra(graph, 0);
    }
}