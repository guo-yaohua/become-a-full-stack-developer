package com.gyh.part2.day32;

public class Solution3 {
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode node1 = l1;
        ListNode node2 = l2;

        for (ListNode res = head; node1 != null || node2 != null; res = res.next) {
            if (node1 == null) {
                res.next = node2;
                break;
            }
            if (node2 == null) {
                res.next = node1;
                break;
            }
            if (node1.val < node2.val) {
                res.next = new ListNode(node1.val, null);
                node1 = node1.next;
            } else {
                res.next = new ListNode(node2.val, null);
                node2 = node2.next;
            }
        }

        return head.next;
    }

    public static void nodePrint(ListNode head) {
        ListNode node = head;
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1, null);
        l1.next = new ListNode(5, l1.next);
        l1.next = new ListNode(3, l1.next);

        ListNode l2 = new ListNode(2, null);
        l2.next = new ListNode(4, l2.next);
        l2.next = new ListNode(3, l2.next);

        System.out.print("l1：");
        nodePrint(l1);
        System.out.print("l2：");
        nodePrint(l2);
        System.out.print("合并：");
        ListNode res = mergeTwoLists(l1, l2);
        nodePrint(res);
    }
}

class ListNode {
    int val;
    ListNode next;

    public ListNode() {}

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
