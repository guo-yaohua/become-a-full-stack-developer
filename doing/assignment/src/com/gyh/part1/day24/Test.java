package com.gyh.part1.day24;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Test {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress gyh = InetAddress.getByName("127.0.0.1");
        System.out.println(gyh.getHostAddress() + "--" + gyh.getHostName());

    }
}
