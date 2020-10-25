import java.io.Serializable;
import java.util.Scanner;

/**
 * THIS IS A BINARY TREE CLASS FROM THE BOOK! ( didn't write a lot of javadoc for it since it is all in the book... )
 * @param <E> GENERIC ELEMENT
 */
public class BinaryTree<E> implements Serializable {
    // Insert inner class Node<E> here
    protected Node<E> root;
    public BinaryTree() {
        root = null;
    }
    protected BinaryTree(Node<E> root) {
        this.root = root;
    }
    public BinaryTree(E data, BinaryTree<E> leftTree,
                      BinaryTree<E> rightTree) {
        root = new Node<E>(data);
        if (leftTree != null) {
            root.left = leftTree.root;
        } else {
            root.left = null;
        }
        if (rightTree != null) {
            root.right = rightTree.root;
        } else {
            root.right = null;
        }
    }

    public E getData(){
        if( root != null )
            return root.data;
        else
            return null;
    }
    /**
     * Returns left subtree
     * @return left subtree
     */
    public BinaryTree<E> Subtree() {
        if (root != null && root.left != null) {
            return new BinaryTree<E>(root.left);
        }
        else {
            return null;
        }
    }
    /**
     * Returns right subtree
     * @return right subtree
     */
    public BinaryTree<E> getRightSubtree() {
        if (root != null && root.right != null) {
            return new BinaryTree<E>(root.right);
        }
        else {
            return null;
        }
    }

    /**
     * Returns left subtree
     * @return left subtree
     */
    public BinaryTree<E> getLeftSubtree() {
        if (root != null && root.left != null) {
            return new BinaryTree<E>(root.left);
        }
        else {
            return null;
        }
    }
    /**
     * @return returns true if node is leaf and false otherwise
     */
    public boolean isLeaf() {
        if( root != null )
            return (root.left == null && root.right == null);
        else
            return false;
    }

    /**
     * uses preOrederTraverse
     * @return returns string representation of the tree
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, sb);
        return sb.toString();
    }
    private void preOrderTraverse(Node<E> node, StringBuilder sb) {
            if( node == null )
                return;
            sb.append(node.toString() + " ");

            preOrderTraverse(node.left, sb);
            preOrderTraverse(node.right, sb);

    }
    public static BinaryTree<String>
    readBinaryTree(Scanner scan) {
        String data = scan.next();
        if (data.equals("null")) {
            return null;
        } else {
            BinaryTree<String> leftTree = readBinaryTree(scan);
            BinaryTree<String> rightTree = readBinaryTree(scan);
            return new BinaryTree<String>(data, leftTree,
                    rightTree);
        }
    }
    protected static class Node<E>
            implements Serializable {
        protected E data;
        protected Node<E> left;
        protected Node<E> right;
        public Node(E data) {
            this.data = data;
            left = null;
            right = null;
        }
        public String toString() {
            return data.toString();
        }
    }
}
