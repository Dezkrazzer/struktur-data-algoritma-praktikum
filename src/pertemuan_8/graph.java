package pertemuan_8;

class GraphMatrix {

    // Number of vertices
    final private int n;

    // Adjacency matrix
    final private int[][] g;

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
            // Insert edge (Directed Graph)
            g[y][x] = 1;

            // Delete comment below for Undirected Graph
            // g[x][y] = 1;
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

            // Delete comment below for Undirected Graph
            // g[x][y] = 0;
        }
    }
}

public class graph {
    public static void main(String[] args)
    {
        int N = 6;
        GraphMatrix obj = new GraphMatrix(N);

        // Inserting edges
        obj.addEdge(0, 1);
        obj.addEdge(0, 2);
        obj.addEdge(0, 3);
        obj.addEdge(0, 4);
        obj.addEdge(1, 3);
        obj.addEdge(2, 3);
        obj.addEdge(2, 4);
        obj.addEdge(2, 5);
        obj.addEdge(3, 5);

        System.out.println("Adjacency matrix after edge insertions:");
        obj.displayAdjacencyMatrix();

        obj.removeEdge(2, 3);

        System.out.println("\nAdjacency matrix after edge removal:");
        obj.displayAdjacencyMatrix();
    }
}
