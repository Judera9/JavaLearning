// package Ass4.problem1;

public class BinaryTreeTest {
    public static void main(String[] args){
        BinaryTree binaryTree=new BinaryTree();
        TreeNode node0=new TreeNode(3);
        binaryTree.root=node0;
        TreeNode node1=new TreeNode(9);
        TreeNode node2=new TreeNode(20);
        binaryTree.AddLeft(node0,node1);
        binaryTree.AddRight(node0,node2);
        TreeNode node3=new TreeNode(15);
        TreeNode node4=new TreeNode(7);
        binaryTree.AddLeft(node1,node3);
        binaryTree.AddRight(node1,node4);
        System.out.println("Inorder:"+binaryTree.TraversalInOrder());
        System.out.println("preorder:"+binaryTree.TraversalPreOrder());
        System.out.println("postorder:"+binaryTree.TraversalPostOrder());
        System.out.println("leverorder:"+binaryTree.TraversalLevelOrder());
        System.out.println(binaryTree.PreIn2Post(binaryTree.TraversalPreOrder(),binaryTree.TraversalInOrder()));
        System.out.println(binaryTree.InPost2Pre(binaryTree.TraversalInOrder(),binaryTree.TraversalPostOrder()));
    }
}