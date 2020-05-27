package com.gyh.part2.day37;

import java.util.*;

public class Solution2 {
    public static void main(String[] args) {
        TreeNode lChild = new TreeNode(1, null, null);
        TreeNode rChild = new TreeNode(3, null, null);
        TreeNode lNode = new TreeNode(2, lChild, rChild);
        lChild = new TreeNode(6, null, null);
        rChild = new TreeNode(9, null, null);
        TreeNode rNode = new TreeNode(7, lChild, rChild);
        TreeNode root = new TreeNode(4, lNode, rNode);

        List<Integer> list = postOrder(root);
        for (Integer val : list) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    public static List<Integer> postOrder(TreeNode root) {
        List<Integer> list = new LinkedList();
        if (root == null) return list;

        Deque<TreeNode> stack = new LinkedList<>();
        List<TreeNode> visited = new ArrayList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (visited.contains(node)) {
                list.add(node.val);
            } else {
                stack.push(node);
                visited.add(node);  // 已经入栈过
                if (node.rChild != null) stack.push(node.rChild);
                if (node.lChild != null) stack.push(node.lChild);
            }
        }
        return list;
    }
}

class TreeNode {
    Integer val;
    TreeNode lChild;    // 左孩子
    TreeNode rChild;    // 右孩子

    TreeNode(int val) {
        this.val = val;
    }
    TreeNode(int val, TreeNode lChild, TreeNode rChild) {
        this.val = val;
        this.lChild = lChild;
        this.rChild = rChild;
    }
}
