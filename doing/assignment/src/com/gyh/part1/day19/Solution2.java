package com.gyh.part1.day19;

import java.io.*;
import java.util.Arrays;

public class Solution2 {
    public static void main(String[] args) throws IOException {
        File file = new File("D:firstLevel");
        copyAllJavaText(file);

        File file2 = new File("D:copy");
        System.out.println("复制的所有文件：" + Arrays.toString(file2.list()));
    }

    // 递归复制
    public static void copyAllJavaText(File file) throws IOException {
        for (File sonFile : file.listFiles()) {
            if (sonFile.isDirectory()) {
                copyAllJavaText(sonFile);
            } else {
                // 还需要判断是否是 java 后缀
                if (sonFile.getName().endsWith(".java")) {
                    copyFileByBufferBytes(sonFile.getAbsolutePath(), "d:copy/" + sonFile.getName());
                }
            }
        }
    }

    // 复制功能
    public static void copyFileByBufferBytes(String srcPath, String destPath) throws IOException {
        InputStream in = new BufferedInputStream(
                new FileInputStream(srcPath));

        OutputStream out = new BufferedOutputStream(
                new FileOutputStream(destPath));

        int len;
        byte[] buffer = new byte[1024];

        while((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }

        in.close();
        out.close();
    }
}
