package com.gyh.part2.day36;

public class Solution3 {
    public static void main(String[] args) {
        TreeNode lChild = new TreeNode(1, null, null);
        TreeNode rChild = new TreeNode(3, null, null);
        TreeNode lNode = new TreeNode(2, lChild, rChild);
        lChild = new TreeNode(6, null, null);
        rChild = new TreeNode(9, null, null);
        TreeNode rNode = new TreeNode(7, lChild, rChild);
        TreeNode root = new TreeNode(4, lNode, rNode);

        System.out.println("删除前（中序遍历）：");
        TreeNode.treePrint(root);
        root = removeNode(root);
        System.out.println("\n删除后（中序遍历）：");
        TreeNode.treePrint(root);
    }

    public static TreeNode removeNode(TreeNode treeNode) {  // 默认左子树替换，左子树不存在则右子树替换
        if (treeNode.lChild == null && treeNode.rChild == null) { // 没有孩子
            treeNode = null;
        } else if (treeNode.lChild != null) {
            treeNode.val = treeNode.lChild.val;
            treeNode.lChild = removeNode(treeNode.lChild);

        } else {
            treeNode.val = treeNode.rChild.val;
            treeNode.rChild = removeNode(treeNode.rChild);
        }
        return treeNode;
    }
}


