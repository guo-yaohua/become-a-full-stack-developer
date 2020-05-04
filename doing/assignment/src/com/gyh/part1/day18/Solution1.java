package com.gyh.part1.day18;

import java.io.File;
import java.util.Arrays;

public class Solution1 {
    public static void main(String[] args) {
        File file = new File("D:firstLevel");
        System.out.println("当前目录下文件：" + Arrays.toString(file.list()));
        DeepDelete(file);
        System.out.println("删除后：" + Arrays.toString(file.list()));
    }

    public static void DeepDelete(File file) {
        for(File sonFile : file.listFiles()) {
            if (sonFile.isDirectory()) {
                DeepDelete(sonFile);
            } else {
                sonFile.delete();
            }
        }

        // 经过循环删除后目录以为空，直接删除当前目录
        file.delete();
        return;
    }
}


