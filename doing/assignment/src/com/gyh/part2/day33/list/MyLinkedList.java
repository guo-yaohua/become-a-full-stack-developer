package com.gyh.part2.day33.list;

public class MyLinkedList<E> implements MyList<E> {
    //属性
    private Node head;
    private Node tail;
    private int size;
    private int modCount;

    private static class Node {
        Node prev;
        Object value;
        Node next;

        public Node(Object value) {
            this.value = value;
        }

        public Node(Node prev, Object value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }
    //构造方法
    public MyLinkedList() {
        head = new Node(null);  // head 结点不存储信息
        tail = new Node(head, null, null);  // tail 结点也不存储信息
        head.next = tail;
    }


    //方法

    /**
     * 将指定元素添加到此列表的结尾。
     *
     * @param e 待添加的元素
     * @return 如果添加成功返回 true
     */
    @Override
    public boolean add(E e) {
        Node node = new Node(tail.prev, e, tail);
        tail.prev.next = node;
        tail.prev = node;
        return true;
    }

    /**
     * 在线性表中指定的索引位置添加元素
     *
     * @param index 索引
     * @param e 待添加的元素
     */
    @Override
    public void add(int index, E e) {
        if (index == size()) {
            add(e);
            return;
        }
        Node preNode = head;    // 前驱
        for (int i = 0; i < index; i++) {
            preNode = preNode.next;
        }
        Node nextNode = preNode.next;   // 后继

        Node addNode = new Node(preNode, e, nextNode);
        preNode.next = addNode;
        nextNode.prev = addNode;
    }

    /**
     * 清空所有元素
     */
    @Override
    public void clear() {
        head = new Node(null);
        tail = new Node(head, null, null);
        head.next = tail;
    }

    /**
     * 判断集合中是否存在与指定对象相等的元素
     *
     * @param obj 指定对象
     * @return 如果集合中有与指定对象相等的元素, 返回 true, 否则返回 false
     */
    @Override
    public boolean contains(Object obj) {
        for (Node node = head.next; node != tail; node = node.next) {
            if (obj.equals(node.value)) return true;
        }
        return false;
    }

    /**
     * 获取指定索引位置的元素
     *
     * @param index 索引
     * @return 指定索引位置的元素
     */
    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkIndex(index);
        Node node = head.next;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return (E) node.value;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("index=" + index + ", size=" + size());
        }
    }

    /**
     * 获取集合中第一个与指定对象相等元素的索引
     * @param obj 指定对象
     * @return 第一个与指定对象相等元素的索引，如果不存在这样的元素，返回 -1
     */
    @Override
    public int indexOf(Object obj) {
        int index = 0;
        for (Node node = head.next; node != tail; node = node.next, index++) {
            if (obj.equals(node.value)) {
                return index;
            }
        }
        return -1;
    }

    /**
     * 判断集合是否为空集合
     *
     * @return 如果集合是空集合返回 true, 否则返回 false
     */
    @Override
    public boolean isEmpty() {
        return head.next == tail;
    }

    @Override
    public MyIterator iterator() {
        return null;
    }

    @Override
    public MyIterator iterator(int index) {
        return null;
    }

    /**
     * 获取集合中最后一个与指定对象相等元素的索引
     * @param obj 指定对象
     * @return 最后一个与指定对象相等元素的索引, 如果不存在这样的元素, 返回 -1
     */
    @Override
    public int lastIndexOf(Object obj) {
        int index = size() - 1;

        for (Node node = tail.prev; node != head; node = node.prev, index--) {
            if (obj.equals(node.value)) return index;
        }
        return -1;
    }

    /**
     * 删除指定索引位置的元素
     * @param index 索引
     * @return 被删除的元素
     */
    @Override
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        Node preNode = head;
        for (int i = 0; i < index; i++) {
            preNode = preNode.next;
        }
        Node deleteNode = preNode.next;

        Node nextNode = preNode.next.next;
        preNode.next = nextNode;
        nextNode.prev = preNode;
        return (E) deleteNode.value;
    }

    /**
     * 删除第一个与指定对象相等的元素
     * @param obj 指定对象
     * @return 如果删除成功返回true, 否则返回 false
     */
    @Override
    public boolean remove(Object obj) {
        for (Node node = head.next; node != tail; node = node.next) {
            if (obj.equals(node.value)) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
                return true;
            }
        }

        return false;
    }

    /**
     * 替换指定索引位置的元素
     *
     * @param index 索引
     * @param e 新的值
     * @return 该索引位置原来的值
     */
    @Override
    @SuppressWarnings("unchecked")
    public E set(int index, E e) {
        checkIndex(index);
        Node node = head.next;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        E obj = (E) node.value;
        node.prev.next = node.next;
        node.next.prev = node.prev;
        return obj;
    }


    /**
     * 获取线性表中元素的个数
     *
     * @return 元素的个数
     */
    @Override
    public int size() {
        Node node = head.next;
        int size = 0;
        while (node != tail) {
            size++;
            node = node.next;
        }
        return size;
    }

    public void listPrint() {
        Node node = head.next;
        System.out.print("[");
        if (node != tail) {
            System.out.print(node.value);
            node = node.next;
        }
        while (node != tail) {
            System.out.print(", " + node.value);
            node = node.next;
        }
        System.out.println("]");
    }
}

