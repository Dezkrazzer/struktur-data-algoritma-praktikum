/*
Nama : Lazuardi Akbar Imani
NIM  : L0125105
Kelas: Informatika 2025D
*/
package pertemuan_10;
import java.util.*;

public class D_PSDA10_L0125105_LazuardiAkbarImani_k1 {

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
            // Prioritas tertinggi diberikan kepada node dengan jarak terkecil
            return this.jarak - other.jarak;
        }
    }

    static void dijkstra(List<List<Edge>> graph, int source) {
        int V = graph.size();
        int[] distance = new int[V];

        // Inisialisasi jarak awal dengan tak terhingga
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        // Menggunakan Priority Queue untuk efisiensi pemilihan node terdekat
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(source, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.vertex;

            for (Edge edge : graph.get(u)) {
                int v = edge.tujuan;
                int bobot = edge.bobot;

                // Proses Pembaruan Jarak (Relaxation)
                if (distance[u] + bobot < distance[v]) {
                    distance[v] = distance[u] + bobot;
                    pq.add(new Node(v, distance[v]));
                }
            }
        }

        System.out.println("=== Hasil Jarak Terpendek dari Node " + source + " (Kasus 1) ===");
        for (int i = 0; i < V; i++) {
            System.out.println("Jarak ke node " + i + " = " + distance[i]);
        }
    }

    public static void main(String[] args) {
        int V = 6;
        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        // Memasukkan data edge
        graph.get(0).add(new Edge(1, 4));
        graph.get(0).add(new Edge(2, 2));
        graph.get(1).add(new Edge(2, 5));
        graph.get(1).add(new Edge(3, 10));
        graph.get(2).add(new Edge(4, 3));
        graph.get(4).add(new Edge(3, 4));
        graph.get(3).add(new Edge(5, 11));
        graph.get(4).add(new Edge(5, 2));
        graph.get(2).add(new Edge(5, 9));
        graph.get(0).add(new Edge(5, 20));

        /* * ANALISIS SINGKAT:
         * Menggunakan pendekatan Greedy.
         * Dari Node 0, jarak awal ke 5 tercatat langsung sebesar 20.
         * Saat algoritma mengeksplorasi Node 2 (karena jaraknya paling dekat, yaitu 2),
         * Akan menemukan Node 4 (jarak total 2+3=5), yang kemudian mengarah langsung ke Node 5 (jarak total 5+2=7).
         * Melalui proses Relaxation, nilai jarak ke Node 5 yang awalnya 20 diperbarui 
         * menjadi 7 karena merupakan jalur terpendek (0 -> 2 -> 4 -> 5).
         */

        dijkstra(graph, 0);
    }
}