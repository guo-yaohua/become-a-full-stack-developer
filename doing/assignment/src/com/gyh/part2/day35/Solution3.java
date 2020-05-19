package com.gyh.part2.day35;

import java.util.*;

public class Solution3 {
    public static void main(String[] args) {
        MyStack<String> myStack = new MyStack<>();
        myStack.push("World");
        myStack.push("Hello");
        System.out.println(myStack.pop());
        System.out.println(myStack.peek());
        System.out.println(myStack.isEmpty());
    }
}

class MyStack<E> {
    // 每次 push 时，队列执行 add 操作，之后将新增元素前的所有元素 pop 到队列之后。
    // 当执行 pop 时，队列就是后进先出。

    private Queue<E> queue = new LinkedList<>();

    public void push(E e) {
        int size = queue.size();
        queue.add(e);
        while (size > 0) {
            queue.add(queue.remove());  // 获取并移除此列表的头，即 pop 操作
            size--;
        }
    }

    public E pop() {
        if(queue.isEmpty()) {
            throw new EmptyStackException();
        }
        return queue.remove();
    }

    public E peek() {
        if(queue.isEmpty()) {
            throw new EmptyStackException();
        }
        return queue.peek();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
