package com.gyh.part2.day41;

import java.util.LinkedList;
import java.util.Queue;

/**
 *        1
 *       / \
 *      2   2
 *     / \ / \
 *    3  4 4  3
 *
 *        1
 *       / \
 *      2   2
 *       \   \
 *        3   3
 */
public class Solution2 {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(2);
        root1.left.left = new TreeNode(3);
        root1.left.right = new TreeNode(4);
        root1.right.left = new TreeNode(4);
        root1.right.right = new TreeNode(3);

        System.out.println(isSymmetry(root1) ? "对称" : "不对称");

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(2);
        root2.left.right = new TreeNode(3);
        root2.right.right = new TreeNode(3);
        System.out.println(isSymmetry(root2) ? "对称" : "不对称");
    }

    public static boolean isSymmetry(TreeNode root) {
        if (root == null || (root.right == null && root.left == null))  return true;

        if (!(root.right != null && root.left != null)) return false;   // 左右子树都存在

        Queue<TreeNode> lQueue = new LinkedList<>();
        Queue<TreeNode> rQueue = new LinkedList<>();
        lQueue.add(root.left);
        rQueue.add(root.right);
        while (!lQueue.isEmpty()) {
            TreeNode lNode = lQueue.poll();
            TreeNode rNode = rQueue.poll();
            if (lNode.val != rNode.val) return false;   // 值不同不对称

            if (lNode.left != null && rNode.right != null) {
                lQueue.add(lNode.left);
                rQueue.add(rNode.right);
            } else if (lNode.left == null && rNode.right == null) {
            } else return false;

            if (lNode.right != null && rNode.left != null) {
                lQueue.add(lNode.right);
                rQueue.add(rNode.left);
            } else if (lNode.right == null && rNode.left == null) {
            } else return false;
        }

        return true;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}