package pertemuan_8;
import java.util.LinkedList;

class GraphList {
    // Jumlah vertex (node)
    @SuppressWarnings("FieldMayBeFinal")
    private int V;

    // Array dari LinkedList untuk menyimpan daftar ketetanggaan
    @SuppressWarnings("FieldMayBeFinal")
    private LinkedList<Integer>[] adjList;

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

        // Diaktifkan untuk Undirected Graph
        adjList[destination].add(source);
    }

    // Method untuk menghapus Edge
    public void removeEdge(int source, int destination) {
        if (source < 0 || source >= V || destination < 0 || destination >= V) {
            System.out.println("Vertex tidak valid!");
            return;
        }

        // Menghapus destination dari list source.
        adjList[source].remove((Integer) destination);

        // Diaktifkan untuk Undirected Graph
        adjList[destination].remove((Integer) source);
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

public class adjacencylist {
    public static void main(String[] args) {
        // Kita buat graph dengan 11 vertex sesuai image_85f0b9.png (0 sampai 10)
        int V = 11; 
        GraphList graph = new GraphList(V);

        // Menambahkan edge berdasarkan gambar
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(0, 4);
        graph.addEdge(0, 8);
        
        graph.addEdge(1, 6);
        graph.addEdge(2, 6);
        
        graph.addEdge(3, 4);
        graph.addEdge(3, 8);
        
        graph.addEdge(4, 9);
        
        graph.addEdge(5, 6);
        graph.addEdge(5, 10);
        
        graph.addEdge(6, 7);
        
        graph.addEdge(7, 9);
        graph.addEdge(7, 10);
        
        graph.addEdge(9, 10);

        // Tampilkan graph awal
        graph.displayGraph();

        // Contoh penghapusan edge (opsional, disesuaikan)
        System.out.println("\nMenghapus edge antara 0 dan 8:");
        graph.removeEdge(0, 8);

        // Tampilkan graph setelah penghapusan
        graph.displayGraph();
    }
}