package com.gyh.part2.day34;

import java.util.*;

public class MyQueue<E> {
    // 属性
    // 在表的前端（front）进行删除操作，而在表的后端（rear）进行插入操作
    private Node front;
    private Node rear;
    private int size;
    private int modCount;

    private static class Node {
        Object value;
        Node pre;
        Node next;


        public Node(Object value) {
            this.value = value;
        }

        public Node(Node pre, Object value, Node next) {
            this.pre = pre;
            this.value = value;
            this.next = next;
        }
    }

    // 构造方法
    public MyQueue() {
        front = new Node(null);
        rear = new Node(front, null, null);
        front.next = rear;
    }

    // 方法

    /**
     * 在队尾（rear）添加元素
     * @param e 要添加的元素
     */
    public void enqueue(E e) {
        Node node = new Node(rear.pre, e, rear);
        rear.pre.next = node;
        rear.pre = node;

        size++;
        modCount++;
    }

    /**
     * 从队头（front）删除元素
     * @return 删除的元素
     */
    @SuppressWarnings("unchecked")
    public E dequeue() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        Node node = front.next;
        front.next = node.next;
        node.next.pre = front;

        size--;
        modCount++;

        return (E) node.value;
    }


    /**
     * 判断队列是否为空
     *
     * @return 空是 true，非空则 false
     */
    public boolean isEmpty() {
        return front.next == rear;
    }

    /**
     * 长度
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 查看队头元素
     *
     * @return 头元素
     */
    @SuppressWarnings("unchecked")
    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return (E) front.next.value;
    }
}
