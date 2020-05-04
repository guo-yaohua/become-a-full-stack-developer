package com.gyh.part1.day24.talking;

import java.net.*;
import java.io.*;
import java.util.concurrent.TimeUnit;

public class Li4 {
    static boolean isSenderOffLine; // 是否继续通信

    public static void main(String[] args) throws SocketException, UnknownHostException {
        DatagramSocket datagramSocket = new DatagramSocket(10004);  // 李四端口

        AnotherReceiverTask receiverTask = new AnotherReceiverTask(datagramSocket);

        // 向指定端口发送数据
        int port = 10003;
        InetAddress localhost = InetAddress.getByName("localhost");
        AnotherSenderTask senderTask = new AnotherSenderTask(datagramSocket, localhost, port);

        new Thread(senderTask).start();
        new Thread(receiverTask).start();
    }
}

class AnotherSenderTask implements Runnable{
    private DatagramSocket senderSocket;

    private InetAddress address;
    private int targetPort;

    public AnotherSenderTask(DatagramSocket senderSocket, InetAddress address, int port) {
        this.senderSocket = senderSocket;
        this.address = address;
        this.targetPort = port;
    }

    @Override
    public void run() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String data;

        try {
            while ((data = br.readLine()) != null) {
                byte[] dataBytes = data.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(dataBytes, 0, dataBytes.length, address,
                        targetPort);

                //发送数据报包
                senderSocket.send(sendPacket);

                if ("告辞".equals(data)) {
                    Li4.isSenderOffLine = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class AnotherReceiverTask implements Runnable{
    DatagramSocket receiverSocket;

    public AnotherReceiverTask(DatagramSocket receiverSocket) {
        this.receiverSocket = receiverSocket;
    }

    @Override
    public void run() {
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length);

        try {
            while (true) {
                receiverSocket.receive(packet);

                // 从数据报包中，取出接收到的数据，并解析
                byte[] data = packet.getData();
                int offset = packet.getOffset();
                int length = packet.getLength();

                String s = new String(data, offset, length);
                System.out.println("张三：" + s);

                if ("告辞".equals(s)) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            while (!Li4.isSenderOffLine) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            receiverSocket.close();
        }
    }
}
