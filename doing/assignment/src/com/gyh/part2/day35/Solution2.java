package com.gyh.part2.day35;

import java.util.Stack;

public class Solution2 {
    public static void main(String[] args) {
        MyQueue<String> myQueue = new MyQueue<>();
        myQueue.push("Hello");
        myQueue.push("World");
        System.out.println(myQueue.peek());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.isEmpty());
    }
}

class MyQueue<E> {
    // 定义两个栈，push 存入栈 1，当栈 2 为空时，将栈 1 传入栈 2
    // 因此队列的队头元素为栈 2 的栈顶。队尾元素为栈 1 的栈顶

    private Stack<E> s1 = new Stack<>();
    private Stack<E> s2 = new Stack<>();

    public void push(E e) {
        s1.push(e);
    }

    public E pop() {
        if(s2.isEmpty()) {
            while(!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        return s2.pop();
    }

    public E peek() {
        if(s2.isEmpty()) {
            while(!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        return s2.peek();
    }

    public boolean isEmpty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}
