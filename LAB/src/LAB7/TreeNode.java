package LAB7;

import java.util.Stack;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    public static void main(String[] args) {

        // specify a tree
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.right.right = new TreeNode(5);
        node.left.left = new TreeNode(4);
        node.left.left.right = new TreeNode(6);
        node.left.left.right.left = new TreeNode(7);
        node.left.left.right.right = new TreeNode(8);

        middleOrder(node);
    }

    // recurse to print from the root (l -> r)
    public static void recursionPreOrder(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + " ");
            recursionPreOrder(root.left);
            recursionPreOrder(root.right);
        }
    }

    // use stack to print pre order traversal instead of recursion
    public static void preOrder(TreeNode root) {
        Stack<TreeNode> treeNodeStack = new Stack<>();
        TreeNode node = root;

        // end condition 1: stack is empty -> no need to find right node
        // end condition 2: <pre>node.left & <pre>node.right are both null
        while (node != null || !treeNodeStack.isEmpty()) {

            while (node != null) {
                System.out.print(node.val + " ");
                treeNodeStack.push(node);
                node = node.left;
            }
            if (!treeNodeStack.isEmpty()) {
                node = treeNodeStack.pop();
                node = node.right;
            }
        }
    }

    // middle order print from the first left end node (l -> r)
    public static void recursionMiddleOrder(TreeNode root) {
        if (root != null) {
            recursionMiddleOrder(root.left);
            System.out.print(root.val + " ");
            recursionMiddleOrder(root.right);
        }
    }

    // similar with pre order, consider find from the very left side to right side
    public static void middleOrder(TreeNode root) {
        Stack<TreeNode> treeNodeStack = new Stack<>();
        TreeNode node = root;
        while (node != null || !treeNodeStack.isEmpty()) {

            // always consider left nodes first
            while (node != null) {
                treeNodeStack.push(node);
                node = node.left;
            }
            if (!treeNodeStack.isEmpty()) {
                node = treeNodeStack.pop();
                System.out.print(node.val + " ");
                node = node.right;
            }
        }
    }

    // post order recursion is also very clean and direct
    public static void recursionPostOrder(TreeNode root) {
        if (root != null) {
            recursionPostOrder(root.left);
            recursionPostOrder(root.right);
            System.out.print(root.val + " ");
        }
    }

    public static void postOrder(TreeNode root) {
        Stack<TreeNode> treeNodeStack = new Stack<>();
        TreeNode node = root;
        TreeNode lastVisit = root;
        while (node != null || !treeNodeStack.isEmpty()) {
            while (node != null) {
                treeNodeStack.push(node);
                node = node.left;
            }

            node = treeNodeStack.peek();
            if (node)
        }
    }
}