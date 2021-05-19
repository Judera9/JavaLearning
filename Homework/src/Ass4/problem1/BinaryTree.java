// package Ass4.problem1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors; // FIXME: too new function?

public class BinaryTree {
    public TreeNode root;

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        TreeNode node0 = new TreeNode(3);
        binaryTree.root = node0;
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        binaryTree.AddLeft(node0, node1);
        binaryTree.AddRight(node0, node2);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);
        binaryTree.AddLeft(node1, node3);
        binaryTree.AddRight(node1, node4);
        System.out.println(binaryTree.TraversalLevelOrder());
        // binaryTree.root = binaryTree.buildTree(new int[] { 100, -69, -138, 118, 0, -121, 248, -88, 224, -2, -135, -60,
        //         1, 7, 293, 40, -29, 46, 148, -62, -177, 36, 104, 282, 212, -200, 288, 130, 272, 113, -166, 258, -187,
        //         266, 206, -75, 42, -96, -157, 182, -43, -26, 242, -50, 66, -87, 48, -86, 52, 71, 3, 208, -94, -85, -140,
        //         255, 43, -122, -105, -144, 264, 205, 222, 230, 111, 199, -27, 68, -145, 225, 268, 149, 228, -113, 263,
        //         -24, 15, 56, 275, 23, -165, 164, -22, 78, -13, -170, -91, 137, -6, 160, 162, 262, -45, 69, 39, -77, -81,
        //         -33, -118, -4, -84, -51, 296, 172, 276, 54, -104, -189, -82, -74, -58, 129, -66, 90, -193, 128, 21, 105,
        //         291, 81, -90, -151, 143, -156, 45, 259, -155, -107, -16, -179, 147, 80, 184, 117, 58, -174, 29, 155,
        //         200, 297, 207, -171, -52, -71, -176, -114, 234, 107, 82, 278, 175, 298, 77, 196, 209, -185, -32, -41,
        //         -103, -186, -117, 204, 265, -188, 185, 289, 157, 195, 91, 2, -162, -79, 283, 61, 44, -192, -30, 177,
        //         -10, -164, 202, 269, 26, 125, -70, -89, 114, 121, -139, 246, -125, 229, -167, -158, -169, -159, 60, 217,
        //         -93, 96, 189, -168, -150, 159, 193, -31, 237, -130, 99, -152, 251, 169, 134, 72, -142, -111, 102, -14,
        //         231, -131, 171, -198, 20, -28, -163, -21, -100, 103, -110, 252, 101, 16, 92, -124, 140, -126, 219, 6,
        //         -101, -42, 32, 64, 14, 214, -132, 166, -35, 109, 187, -8, -11, 70, 33, 239, 138, -44, 25, -108, 9, 271,
        //         -116, 106, -57, -67, 299, -15, 218, 213, 168, 211, 161, 167, -133, 238, 95, 97, -49, 232, -183, 49, -40,
        //         -3, 11, -76, 136, -97, -65, 227, -68, 79, 93, -137, -46, 76, 158, 108, 126, -38, 120, 173, 223, -1,
        //         -178, 47, 178, 233, 94, 30, 191, -112, 197, 5, -136, 87, 153, 22, -37, 176, 201, -12, 192, 12, -36, 142,
        //         41, -5, 181, 17, 270, 279, -17, -161, -73, 51, -175, -18, 74, -180, 216, -95, -147, 257, 260, -127, -80,
        //         -172, 194, 295, 13, 150, 122, -146, -181, 247, 53, -78, -98, 119, -154, -128, 144, -134, -106, -9, 152,
        //         203, -195, -55, 116, 215, 75, -92, -190, -25, -47, 281, 86, 190, 188, -72, 285, -39, 292, 127, -194,
        //         -153, -7, 156, 57, 284, -48, -141, 274, 38, 18, 110, 84, 37, 261, 88, 154, -197, 115, 4, 73, -148, -109,
        //         19, 236, 290, 294, 85, -53, -99, -19, 250, 63, 50, -34, 245, 27, 220, 139, -63, 83, 151, -184, 179, -64,
        //         253, 67, 34, -160, 210, 59, -115, -196, 174, -102, 28, 186, -23, 241, 62, 221, 267, 124, 146, -199, 286,
        //         112, 256, 135, 183, 243, 65, 254, 31, 8, -182, -119, 145, -61, -173, 10, 131, 89, -83, 277, 24, 55, 98,
        //         280, -20, 35, 249, -149, 244, -59, 165, -56, -143, -129, 273, -123, -191, 226, -120, 133, 198, 235, 180,
        //         141, 132, 170, -54, 287, 123, 240, 163 },
        //         new int[] { -121, -88, 248, 0, 224, 118, 293, 40, 7, 1, 148, 46, -29, 36, 104, -177, -62, -60, -166,
        //                 113, 272, 130, 258, 288, -187, 206, -75, 266, 42, -200, 182, -157, -43, 242, -26, -96, -87, 66,
        //                 -50, 48, -86, 212, 52, 71, 282, 208, -94, -85, 3, -140, -122, -105, -144, 43, 255, 230, 222,
        //                 111, 205, 199, -27, 264, -145, 225, 68, 149, 268, -135, 263, 56, 275, 15, 23, -165, -24, -22,
        //                 164, 78, -113, -170, -6, 137, 160, 262, 162, -91, -13, 69, -45, 39, 228, -33, -81, -118, -77,
        //                 -2, -84, 296, 172, -51, 276, -4, -104, -82, -58, -74, -189, 129, -66, 54, 90, -138, 128, 105,
        //                 21, 81, 291, -151, -90, -193, -156, -107, -155, 147, 184, 117, 58, 80, -179, -174, -171, 207,
        //                 -176, -71, -52, -114, 297, 200, 155, 234, 29, -16, 107, 278, 82, 259, 175, 45, 77, 196, 209,
        //                 298, -32, -103, -41, -185, -117, 204, -186, 265, 185, -188, 143, 2, 91, 195, 283, 61, -79, 44,
        //                 -162, 157, 177, -164, -10, 202, -30, 269, 26, -70, 125, 114, -89, -192, -125, 246, 229, -139,
        //                 -167, 121, -169, -159, 60, -158, 217, 289, 189, 96, 193, 159, -31, -150, -130, 99, 237, -168,
        //                 -152, -93, 134, 72, 169, 251, -111, 102, -142, -69, 20, -198, -28, 171, -110, 101, 252, 16,
        //                 -124, 92, 103, -100, -21, -126, 219, 140, 6, -163, -42, -101, 32, -131, 214, 14, -132, 64, 109,
        //                 187, -35, 166, 33, 70, 138, -44, 239, 25, 9, -108, 271, -11, -67, -57, 106, 299, -15, 213, 218,
        //                 -116, -8, 211, 161, 168, 167, -133, 231, -183, 232, -49, 49, 97, -3, -76, 136, 11, -97, -40,
        //                 -65, -68, 227, 79, 95, 93, 238, -46, -137, 126, 108, 158, 76, -38, -14, -178, -1, 178, 47, 94,
        //                 233, 223, 30, 197, -112, -136, 5, 191, 87, 22, 153, 173, -37, 176, 120, 12, 192, -12, 142, 41,
        //                 -5, 181, -36, 201, 270, 279, 17, -161, -17, 100, -180, 74, 260, 257, -147, -95, 216, -80, -127,
        //                 -18, 295, 122, -146, 150, -181, 247, 53, 13, -98, -78, 194, -154, 119, -128, -172, -9, 152, 203,
        //                 -106, -55, -195, 215, 75, 116, -25, -190, 281, -47, -92, 188, 190, -72, -39, 285, 292, 86, -134,
        //                 127, 144, -194, -175, 156, 57, 284, -7, 38, 274, -141, -48, -153, 110, 84, 18, 4, 115, -148, 73,
        //                 -197, 19, -109, 154, 88, 290, 236, 261, 294, 37, 63, 250, 50, -19, 245, 27, -34, 220, -99, 139,
        //                 -53, 83, 151, -184, 179, -63, 85, -64, 67, 253, 51, 210, -196, -115, 59, -160, -102, 174, 28,
        //                 186, -23, 34, 62, 221, 241, -199, 146, 124, 267, 286, 112, -73, 31, 254, 65, -182, 8, 243, -119,
        //                 183, 145, -61, 135, 89, -83, 131, 277, 10, -173, 98, 55, -20, 280, 35, 24, 249, -149, 244, 256,
        //                 -143, -56, 165, 226, -191, -123, 133, -120, 235, 198, 273, 141, 170, 132, 180, -54, -129, 123,
        //                 287, -59, 240, 163 });
        System.out.println(binaryTree.TraversalPreOrder());
    }

    public boolean AddLeft(TreeNode parent, TreeNode left) {
        if (parent == null) {
            System.out.println(">> parent node is null in Add left");
            return false;
        }

        if (parent.left != null) {
            System.out.println(">> left node already exists");
            return false;
        }

        if (left == null) {
            System.out.println(">> left node is null in Add left");
            return false;
        }

        parent.left = left;
        return true;
    }

    public boolean AddRight(TreeNode parent, TreeNode right) {
        if (parent == null) {
            System.out.println(">> parent node is null in Add right");
            return false;
        }

        if (parent.right != null) {
            System.out.println(">> right node already exists");
            return false;
        }

        if (right == null) {
            System.out.println(">> right node is null in Add right");
            return false;
        }

        parent.right = right;
        return true;

    }

    public String TraversalInOrder() {
        ArrayList<Integer> list = new ArrayList<>();
        reCurIn(this.root, list);
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < list.size() - 1; i++) {
            buffer.append(list.get(i) + " ");
        }
        buffer.append(list.get(list.size() - 1));
        return buffer.toString(); // buffer string's toString could be used here
    }

    public void reCurIn(TreeNode curNode, ArrayList<Integer> list) { // use recursion to simplify code
        if (curNode == null)
            return;

        reCurIn(curNode.left, list);
        list.add(curNode.val);
        reCurIn(curNode.right, list);
    }

    public String TraversalPreOrder() {
        ArrayList<Integer> list = new ArrayList<>();
        reCurPre(this.root, list);
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < list.size() - 1; i++) {
            buffer.append(list.get(i) + " ");
        }
        buffer.append(list.get(list.size() - 1));
        return buffer.toString();
    }

    public void reCurPre(TreeNode curNode, ArrayList<Integer> list) {
        if (curNode == null)
            return;

        list.add(curNode.val); // just change the position of the recursion
        reCurPre(curNode.left, list);
        reCurPre(curNode.right, list);
    }

    public String TraversalPostOrder() {
        ArrayList<Integer> list = new ArrayList<>();
        reCurPost(this.root, list);
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < list.size() - 1; i++) {
            buffer.append(list.get(i) + " ");
        }
        buffer.append(list.get(list.size() - 1));
        return buffer.toString();
    }

    public void reCurPost(TreeNode curNode, ArrayList<Integer> list) {
        if (curNode == null)
            return;

        reCurPost(curNode.left, list);
        reCurPost(curNode.right, list);
        list.add(curNode.val);
    }

    public String TraversalLevelOrder() { // use BPS to search
        List<TreeNode> nodeList = new ArrayList<>();
        StringBuffer buffer = new StringBuffer();
        nodeList.add(this.root.left);
        nodeList.add(this.root.right);
        buffer.append(this.root.val + " ");
        buffer.append(this.root.left.val + " ");
        buffer.append(this.root.right.val + " ");

        while (true) {
            if (nodeList.isEmpty()) { // if no more modes to be added, end the loop
                break;
            }
            List<TreeNode> newNodeList = new ArrayList<>(); // update in the loop
            StringBuffer newBuffer = new StringBuffer(); // update in the loop
            for (int i = 0; i < nodeList.size(); i++) { // find the new node list and buffer
                if (nodeList.get(i).left != null) {
                    newNodeList.add(nodeList.get(i).left);
                    newBuffer.append(nodeList.get(i).left.val + " ");
                }
                if (nodeList.get(i).right != null) {
                    newNodeList.add(nodeList.get(i).right);
                    newBuffer.append(nodeList.get(i).right.val + " ");
                }
            }
            buffer.append(newBuffer); // add the new list into list
            nodeList = newNodeList;
        }

        // turn the result to String
        buffer.deleteCharAt(buffer.length() - 1);
        return buffer.toString();
    }

    public String PreIn2Post(String preTraversal, String inTraversal) { // string is hard to recurse, so I use List
        String[] splitPreString = preTraversal.split(" ");
        String[] splitInString = inTraversal.split(" ");

        List<Integer> preList = new ArrayList<>();
        List<Integer> inList = new ArrayList<>();
        for (int i = 0; i < preTraversal.length(); i++) {
            preList.add(Integer.parseInt(splitPreString[i]));
            inList.add(Integer.parseInt(splitInString[i]));
        }
        root = preIn2PostRecur(preList, inList);
        return TraversalPostOrder();
    }

    public TreeNode preIn2PostRecur(List<Integer> preList, List<Integer> inList) { // use recursion
        if (preList.isEmpty())
            return null;
        TreeNode thisRoot = new TreeNode(preList.get(0));
        if (preList.size() == 1) {
            return thisRoot;
        }
        for (int i = 0; i < inList.size(); i++) {
            if (inList.get(i).equals(preList.get(0))) { // find the root in the two list
                thisRoot.left = preIn2PostRecur(preList.subList(1, i + 1), inList.subList(0, i));
                thisRoot.right = preIn2PostRecur(preList.subList(i + 1, preList.size()),
                        inList.subList(i + 1, inList.size()));
                break;
            }
        }
        return thisRoot;
    }

    // convert arrays into Tree, return the root [used for testing]
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        List<Integer> preList = Arrays.stream(preorder).boxed().collect(Collectors.toList());
        List<Integer> inList = Arrays.stream(inorder).boxed().collect(Collectors.toList());
        return preIn2PostRecur(preList, inList);
    }

    public String InPost2Pre(String inTraversal, String postTraversal) {
        String[] splitInString = inTraversal.split(" ");
        String[] splitPostString = postTraversal.split(" ");

        List<Integer> inList = new ArrayList<>();
        List<Integer> postList = new ArrayList<>();
        for (int i = 0; i < inTraversal.length(); i++) {
            inList.add(Integer.parseInt(splitInString[i]));
            postList.add(Integer.parseInt(splitPostString[i]));
        }
        root = preIn2PostRecur(inList, postList);
        return TraversalPreOrder();
    }

    public TreeNode inPost2PreRecur(List<Integer> inList, List<Integer> postList) { // use recursion
        if (inList.isEmpty())
            return null;
        TreeNode thisRoot = new TreeNode(postList.get(postList.size() - 1));
        if (inList.size() == 1) {
            return thisRoot;
        }
        for (int i = 0; i < inList.size(); i++) {
            if (inList.get(i).equals(postList.get(postList.size() - 1))) { // find the root in the two list
                thisRoot.left = inPost2PreRecur(inList.subList(0, i), postList.subList(0, i));
                thisRoot.right = inPost2PreRecur(inList.subList(i + 1, inList.size()),
                        postList.subList(i, postList.size() - 1));
                break;
            }
        }
        return thisRoot;
    }
}