package com.gyh.part2.day37;

import java.util.Arrays;
import java.util.List;

public class Solution3 {
    public static void main(String[] args) {
        List<Integer> inOrder = Arrays.asList(1, 2, 3, 4, 6, 7, 9);
        List<Integer> postOrder = Arrays.asList(1, 3, 2, 6, 9, 7, 4);
        TreeNode root = build(inOrder, postOrder);
        treeNodePrint(root);
    }

    public static TreeNode build(List<Integer> inOrder, List<Integer> postOrder) {
        if (inOrder == null || inOrder.isEmpty()) return null;

        Integer key = postOrder.get(postOrder.size() - 1);
        TreeNode node = new TreeNode(key.intValue());

        int index = inOrder.indexOf(key);

        // 构建左子树
        List<Integer> lChildInOrder = inOrder.subList(0, index);
        List<Integer> lChildPostOrder = postOrder.subList(0, index);
        node.lChild = build(lChildInOrder, lChildPostOrder);

        // 构建右子树
        List<Integer> rChildInOrder = inOrder.subList(index + 1, inOrder.size());
        List<Integer> rChildPostOrder = postOrder.subList(index, postOrder.size() - 1);
        node.rChild = build(rChildInOrder, rChildPostOrder);
        return node;
    }

    public static void treeNodePrint(TreeNode root) {
        if (root == null) return;
        if (root.lChild != null) treeNodePrint(root.lChild);
        System.out.print(root.val + " ");
        if (root.rChild != null) treeNodePrint(root.rChild);
    }
}
