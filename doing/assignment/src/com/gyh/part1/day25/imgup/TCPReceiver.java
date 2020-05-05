package com.gyh.part1.day25.imgup;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPReceiver {
    static long fileId = 0L;

    public static void main(String[] args) throws IOException {

        boolean flag = true;

        ServerSocket serverSocket = new ServerSocket(11111);

        Socket socket = null;
        while (flag) {
            socket = serverSocket.accept();
            String uploadFileName = System.currentTimeMillis() + "-" + fileId++;

            new UploadReceiverThread(socket, uploadFileName).start();
        }

        serverSocket.close();
    }
}

class UploadReceiverThread extends Thread {
    private Socket socket;
    private String fileName;

    public UploadReceiverThread(Socket socket, String fileName) {
        this.socket = socket;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        InputStream inputStream;
        byte[] buffer = new byte[1024];
        BufferedOutputStream bos = null;

        try {
            // 读取
            inputStream = socket.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(inputStream);

            // 输出
            FileOutputStream fos = new FileOutputStream(fileName + ".png");
            bos = new BufferedOutputStream(fos);

            int len;
            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }

            bos.flush();

            // 反馈
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("文件上传完毕".getBytes());

            System.out.println("已经接收一个文件，来着端口号：" + socket.getLocalPort());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //关闭socket
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
