package com.gyh.part2.day29;

public class Solution2 {
    public static void main(String[] args) {
        Node head = new Node();
        Node node = head;
        for (int i = 1; i <= 100; i++) {    // 添加 100 个节点
            node.next = new Node(i);
            node = node.next;
        }

        for (node = head; node.next != null; node = node.next) {
            System.out.print(node.next.value + " ");
        }

        Node.addNode(head, 2);
        System.out.println("\n添加存在的节点：");
        for (node = head; node.next != null; node = node.next) {
            System.out.print(node.next.value + " ");
        }

        Node.addNode(head, 0);
        System.out.println("\n添加结点超出缓存后：");
        for (node = head; node.next != null; node = node.next) {
            System.out.print(node.next.value + " ");
        }
    }
}

class Node {
    public static final int MaxLen = 100;

    int value;

    Node next;

    public Node() {
    }
    public Node(int value) {
        this.value = value;
    }
    public Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }

    public static void addNode(Node head, int num) {
        // 头结点不存储数据
        Node p = head.next;
        head.next = new Node(num, p);   // 添加在头部

        int len = 1;    // 长度
        Node node = head.next.next;  // 从第二个有效结点开始遍历
        p = head.next;  // 记录 node 的前驱结点

        while (node != null) {
            if (node.value == num || len >= Node.MaxLen) {   // 重复或者超出缓存，就删除 node 节点
                p.next = node.next;
                return;
            }

            p = node;
            node = node.next;

            len++;
        }
    }
}
