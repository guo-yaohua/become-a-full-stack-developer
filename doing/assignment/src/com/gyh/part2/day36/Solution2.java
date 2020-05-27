package com.gyh.part2.day36;

public class Solution2 {
    public static void main(String[] args) {
        TreeNode lChild = new TreeNode(1, null, null);
        TreeNode rChild = new TreeNode(3, null, null);
        TreeNode lNode = new TreeNode(2, lChild, rChild);
        lChild = new TreeNode(6, null, null);
        rChild = new TreeNode(9, null, null);
        TreeNode rNode = new TreeNode(7, lChild, rChild);
        TreeNode root = new TreeNode(4, lNode, rNode);

        System.out.println("反转前（中序遍历）：");
        TreeNode.treePrint(root);
        root = reverTree(root);
        System.out.println("\n反转后（中序遍历）");
        TreeNode.treePrint(root);
    }

    public static TreeNode reverTree(TreeNode treeNode){
        if (treeNode == null || (treeNode.lChild == null && treeNode.rChild == null)) {   // 自身为 null 或者左右孩子都不存在则直接返回
            return treeNode;
        } else {
            treeNode.lChild = reverTree(treeNode.lChild);
            treeNode.rChild = reverTree(treeNode.rChild);

            TreeNode temp = treeNode.lChild;
            treeNode.lChild = treeNode.rChild;
            treeNode.rChild = temp;
            return treeNode;
        }
    }

}

class TreeNode {
    int val;
    TreeNode lChild;    // 左孩子
    TreeNode rChild;    // 右孩子

    TreeNode(int val, TreeNode lChild, TreeNode rChild) {
        this.val = val;
        this.lChild = lChild;
        this.rChild = rChild;
    }


    public static void treePrint(TreeNode treeNode) {
        if (treeNode == null) return;
        if (treeNode.lChild != null) treePrint(treeNode.lChild);
        System.out.print(treeNode.val + " ");
        if (treeNode.rChild != null) treePrint(treeNode.rChild);
    }
}