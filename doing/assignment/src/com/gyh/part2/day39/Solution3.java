package com.gyh.part2.day39;

/**
 *
 *     4
 *    / \
 *   2   6
 *  / \
 * 1   3
 *
 */
public class Solution3 {
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.right = new TreeNode(1);
        root.left.right = new TreeNode(3);
        System.out.println(minLen(root));
    }

    public static int minLen(TreeNode root) {
        if (root.left == null && root.right == null) return Integer.MAX_VALUE;
        if (root.left != null) {
            min = Math.min(min, root.val - root.left.val);
            min = Math.min(min, minLen(root.left));
        }
        if (root.right != null){
            min = Math.min(min, root.right.val - root.val);
            min = Math.min(min, minLen(root.right));
        }

        return min;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}