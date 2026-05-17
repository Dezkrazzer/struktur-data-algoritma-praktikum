package pertemuan_8;

class GraphMatrix {

    // Number of vertices
    @SuppressWarnings("FieldMayBeFinal")
    private int n;

    // Adjacency matrix
    @SuppressWarnings("FieldMayBeFinal")
    private int[][] g;

    // Constructor
    GraphMatrix(int x)
    {
        this.n = x;
        g = new int[n][n];
    }

    // Function to display adjacency matrix
    public void displayAdjacencyMatrix()
    {
        // Displaying the 2D matrix
        for (int i = 0; i < n; ++i) {
            System.out.println();
            for (int j = 0; j < n; ++j) {
                System.out.print(" " + g[i][j]);
            }
        }
        System.out.println();
    }

    // Function to update adjacency
    // matrix for edge insertion
    public void addEdge(int x, int y)
    {
        // Checks if the vertices exists
        if ((x < 0) || (x >= n)) {
            System.out.println("Vertex " + x + " does not exist!");
            return;
        }
        if ((y < 0) || (y >= n)) {
            System.out.println("Vertex " + y + " does not exist!");
            return;
        }

        // Checks if it is a self edge
        if (x == y) {
            System.out.println("Same Vertex!");
        } else {
            // Insert edge
            g[y][x] = 1;

            // Diaktifkan untuk Undirected Graph
            g[x][y] = 1;
        }
    }

    // Function to update adjacency
    // matrix for edge removal
    public void removeEdge(int x, int y)
    {
        // Checks if the vertices exists
        if ((x < 0) || (x >= n)) {
            System.out.println("Vertex " + x + " does not exist!");
            return;
        }
        if ((y < 0) || (y >= n)) {
            System.out.println("Vertex " + y + " does not exist!");
            return;
        }

        // Checks if it is a self edge
        if (x == y) {
            System.out.println("Same Vertex!");
        } else {
            // Remove edge
            g[y][x] = 0;

            // Diaktifkan untuk Undirected Graph
            g[x][y] = 0;
        }
    }
}

public class adjacencymatrix {
    public static void main(String[] args)
    {
        // Jumlah vertex disesuaikan dengan gambar (Node 0 sampai 10)
        int N = 11;
        GraphMatrix obj = new GraphMatrix(N);

        // Inserting edges berdasarkan visual graph dari image_864a94.png
        obj.addEdge(0, 1);
        obj.addEdge(0, 2);
        obj.addEdge(0, 3);
        obj.addEdge(0, 4);
        obj.addEdge(0, 8);
        obj.addEdge(1, 6);
        obj.addEdge(2, 6);
        obj.addEdge(3, 4);
        obj.addEdge(3, 8);
        obj.addEdge(4, 9);
        obj.addEdge(5, 6);
        obj.addEdge(5, 10);
        obj.addEdge(6, 7);
        obj.addEdge(7, 9);
        obj.addEdge(7, 10);
        obj.addEdge(9, 10);

        System.out.println("Adjacency matrix after edge insertions:");
        obj.displayAdjacencyMatrix();
        
        // Contoh menghapus salah satu edge (opsional, disesuaikan dengan kebutuhan)
        // obj.removeEdge(0, 8);
        // System.out.println("\nAdjacency matrix after edge removal:");
        // obj.displayAdjacencyMatrix();
    }
}
