package com.gyh.part2.day30;

public class Solution3 {
    public static void main(String[] args) {
        Node head = new Node(4);
        head = new Node(3, head);
        head = new Node(2, head);
        head = new Node(1, head);

        // 当前链表
        for (Node node = head; node != null; node = node.next) {
            System.out.print(node.value + " ");
        }
        System.out.println();

        // 执行方法后的链表
        for (Node node = reverseList(head); node != null; node = node.next) {
            System.out.print(node.value + " ");
        }
        System.out.println();
    }

    public static Node reverseList(Node head) {
        if (head == null || head.next == null) {    // 递归出口
            return head;
        }

        Node next = head.next;
        Node newHead = reverseList(next);   // 递归

        next.next = head;
        head.next = null;
        return newHead;
    }
}

class Node {
    int value;
    Node next;

    public Node(int value) {
        this.value = value;
    }

    public Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}