package pertemuan_8;
import java.util.LinkedList;

class GraphList {
    // Jumlah vertex (node)
    final private int V;

    // Array dari LinkedList untuk menyimpan daftar ketetanggaan
    final private LinkedList<Integer>[] adjList;

    // Constructor
    @SuppressWarnings("unchecked")
    public GraphList(int vertices) {
        this.V = vertices;

        // Inisialisasi ukuran array sebanyak jumlah vertex
        adjList = new LinkedList[V];

        // Inisialisasi LinkedList kosong untuk setiap elemen array
        for (int i = 0; i < V; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    // Method untuk menambahkan Edge
    public void addEdge(int source, int destination) {
        // Cek validitas node
        if (source < 0 || source >= V || destination < 0 || destination >= V) {
            System.out.println("Vertex tidak valid!");
            return;
        }

        // Tambahkan destination ke list milik source
        adjList[source].add(destination);

        // Gunakan dibawah jika Undirected
        // adjList[destination].add(source);
    }

    // Method untuk menghapus Edge
    public void removeEdge(int source, int destination) {
        if (source < 0 || source >= V || destination < 0 || destination >= V) {
            System.out.println("Vertex tidak valid!");
            return;
        }

        // Menghapus destination dari list source.
        adjList[source].remove((Integer) destination);

        // Gunakan dibawah jika Undirected
        // adjList[destination].remove((Integer) source);
    }

    // Method untuk menampilkan Graph
    public void displayGraph() {
        System.out.println("Representasi Adjacency List:");
        for (int i = 0; i < V; i++) {
            System.out.print("Vertex " + i + " terhubung ke: ");

            // Loop melalui isi LinkedList pada index i
            for (Integer node : adjList[i]) {
                System.out.print(node + " ");
            }
            System.out.println();
        }
    }
}


public class adjacency {
public static void main(String[] args) {
        int V = 5; // Kita buat graph dengan 5 vertex
        GraphList graph = new GraphList(V);

        // Menambahkan edge
        graph.addEdge(0, 1);
        graph.addEdge(0, 4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        // Tampilkan graph awal
        graph.displayGraph();

        System.out.println("\nMenghapus edge antara 1 dan 4:");
        graph.removeEdge(1, 4);

        // Tampilkan graph setelah penghapusan
        graph.displayGraph();
    }
}