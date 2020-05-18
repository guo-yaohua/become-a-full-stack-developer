package com.gyh.part2.day34;


import java.util.*;

class ArrayOverflowException extends RuntimeException {
    public ArrayOverflowException() {
    }

    public ArrayOverflowException(String message) {
        super(message);
    }
}

public class MyStack<E> {
    private final static int DEFAULT_CAPACITY = 10;
    private final static int MAX_CAPACITY = Integer.MAX_VALUE - 8;

    // 属性
    private Object[] elements;
    private int size;
    private int modCount;

    // 构造方法
    public MyStack() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    // 方法

    /**
     * 入栈, 在栈顶添加元素
     * @param e 待添加的元素
     */
    public void push(E e) {
        if (size == elements.length) {
            if (size == MAX_CAPACITY) {
                throw new ArrayOverflowException();
            }
            // 扩容 1.5 倍
            int newCapacity = size + (size >> 1);   // 最大栈容量
            if (newCapacity > MAX_CAPACITY) {
                newCapacity = MAX_CAPACITY;
            }
            grow(newCapacity);
        }

        elements[size] = e;
        size++;
        modCount++;
    }

    // 数组扩容
    private void grow(int newCapacity) {
        Object[] newArr = new Object[newCapacity];

        System.arraycopy(elements, 0, newArr, 0, size);

        elements = newArr;
    }

    /**
     * 出栈, 删除栈顶元素
     * @return 被删除的元素
     */
    @SuppressWarnings("unchecked")
    public E pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        size--;
        E removeValue = (E) elements[size];
        elements[size] = null;
        return removeValue;
    }

    /**
     * 查看栈顶元素
     *
     * @return 栈顶元素
     */
    @SuppressWarnings("unchecked")
    public E peek() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        return (E) elements[size - 1];
    }

    /**
     * 判断栈是否为空
     * @return 如果栈为空返回 true, 否则返回 false
     */
    public boolean isEmpty() {
        return size == 0;
    }
}
