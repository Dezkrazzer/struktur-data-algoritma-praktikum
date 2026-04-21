package pertemuan_6;

import java.util.LinkedList;
import java.util.Queue;

class Node {
    int data;
    Node left, right;

    Node(int val) {
        data = val;
        left = right = null;
    }
}

public class BinaryTree {

    // Inorder traversal
    void inorder(Node temp) {
        if (temp == null) return;

        inorder(temp.left);
        System.out.print(temp.data + " ");
        inorder(temp.right);
    }

    // Insert a node
    Node insert(Node root, int data) {
        if (root == null) return new Node(data);

        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            Node temp = q.poll();

            if (temp.left == null) {
                temp.left = new Node(data);
                break;
            } else {
                q.add(temp.left);
            }

            if (temp.right == null) {
                temp.right = new Node(data);
                break;
            } else {
                q.add(temp.right);
            }
        }

        return root;
    }

    // Delete the deepest node
    private void deleteDeepest(Node root, Node d_node) {
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            Node temp = q.poll();

            if (temp == d_node) {
                return;
            }

            if (temp.right != null) {
                if (temp.right == d_node) {
                    temp.right = null;
                    return;
                } else {
                    q.add(temp.right);
                }
            }

            if (temp.left != null) {
                if (temp.left == d_node) {
                    temp.left = null;
                    return;
                } else {
                    q.add(temp.left);
                }
            }
        }
    }

    // Delete a node
    @SuppressWarnings("unused")
    Node delete(Node root, int key) {
        if (root == null) return null;

        if (root.left == null && root.right == null) {
            if (root.data == key) return null;
            else return root;
        }

        Queue<Node> q = new LinkedList<>();
        q.add(root);

        Node deepestNode = null, keyNode = null;

        while (!q.isEmpty()) {
            deepestNode = q.poll();

            if (deepestNode.data == key) keyNode = deepestNode;

            if (deepestNode.left != null) q.add(deepestNode.left);
            if (deepestNode.right != null) q.add(deepestNode.right);
        }

        if (keyNode != null && deepestNode != null) {
            int x = deepestNode.data;
            deleteDeepest(root, deepestNode);
            keyNode.data = x;
        }

        return root;
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        Node root = new Node(13);
        root.left = new Node(12);
        root.left.left = new Node(4);
        root.left.right = new Node(15);
        root.right = new Node(10);
        root.right.right = new Node(8);

        tree.insert(root, 11);
        System.out.println("hasil insert: ");
        tree.inorder(root);
        System.out.println();
        /* Node root = new Node(10);
        root.left = new Node(11);
        root.left.left = new Node(7);
        root.left.right = new Node(12);
        root.right = new Node(9);
        root.right.left = new Node(15);
        root.right.right = new Node(8);

        System.out.print("Inorder traversal before insertion: ");
        tree.inorder(root);
        System.out.println();

        root = tree.insert(root, 12);
        System.out.print("Inorder traversal after insertion: ");
        tree.inorder(root);
        System.out.println();

        root = tree.delete(root, 11);
        System.out.print("Inorder traversal after deletion: ");
        tree.inorder(root); */
    }
}