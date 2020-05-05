package com.gyh.part1.day25.imgup;

import java.io.*;
import java.net.Socket;

public class TCPSender {
    public static void main(String[] args) throws IOException, InterruptedException {
        String[] files = {"a.png", "b.png", "c.png"};

        for (int i = 0; i < files.length; i++) {
            new UploadSenderThread(files[i]).start();
        }
    }
}

class UploadSenderThread extends Thread {
    public String fileName;

    public UploadSenderThread (String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void run() {
        Socket socket = null;
        byte[] buffer = new byte[1024];
        BufferedInputStream bis = null;

        try {
            socket = new Socket("localhost", 11111);

            System.out.println(socket.getLocalAddress() + ":" + socket.getLocalPort());

            // 输出流
            OutputStream out = socket.getOutputStream();
            BufferedOutputStream bw = new BufferedOutputStream(out);

            // 输入流
            FileInputStream fis = new FileInputStream(fileName);
            bis = new BufferedInputStream(fis);


            int len;
            while ((len = bis.read(buffer)) != -1) {
                bw.write(buffer, 0, len);
            }

            bw.flush();

            // 禁用此套接字的输出流。对于 TCP 套接字，任何以前写入的数据都将被发送，并且后跟 TCP 的正常连接终止序列
            socket.shutdownOutput();

            // 接收服务器端的反馈
            InputStream inputStream = socket.getInputStream();
            // 如果服务器端没有发送反馈，发送端，会卡在这里
            buffer = new byte[1024];
            len = inputStream.read(buffer);
            System.out.println(new String(buffer, 0, len));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

