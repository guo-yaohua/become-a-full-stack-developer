package com.gyh.part2.day34;

public class TestStack {
    public static void main(String[] args) {
        MyStack<String> stack = new MyStack<>();
        stack.push("No.1");
        stack.push("Java");
        stack.push("Hello World");
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.isEmpty());
    }
}
