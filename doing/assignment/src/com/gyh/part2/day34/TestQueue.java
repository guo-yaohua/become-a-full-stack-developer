package com.gyh.part2.day34;

public class TestQueue {
    public static void main(String[] args) {
        MyQueue<String> queue = new MyQueue<>();

        queue.enqueue("Hello");
        queue.enqueue("World");

        System.out.println(queue.peek());
        System.out.println(queue.dequeue() + " " + queue.dequeue());
        System.out.println(queue.isEmpty());
    }
}
