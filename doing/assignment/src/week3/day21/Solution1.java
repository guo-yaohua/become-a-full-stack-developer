package week3.day21;

import java.io.*;

public class Solution1 {
    public static void main(String[] args) throws IOException {
        File file1 = new File("D:dir1/dir");
        File file2 = new File("D:dir2/dir");
        copyDir(file1, file2);
    }

    public static void copyDir(File file1, File file2) throws IOException {
        // 目标目录不存在就创建
        if (!file2.exists()) {
            file2.mkdirs();
        }

        File[] files = file1.listFiles();
        for (File sonFile : files) {
            if (sonFile.isDirectory()) {    // 目录就递归复制
                copyDir(sonFile, new File(file2, sonFile.getName()));
            }
            if (sonFile.isFile() && sonFile.getName().endsWith(".java")) {  // .java 文件就复制
                copyFile(sonFile, new File(file2, sonFile.getName()));
            }
        }
    }

    public static void copyFile(File file1, File file2) throws IOException {
        if (!file2.exists()) {
            file2.createNewFile();
        }

        Reader reader = new FileReader(file1);
        Writer writer = new FileWriter(file2);

        char[] chars = new char[1024];
        int len;
        while ((len = reader.read(chars)) != -1) {
            writer.write(chars, 0, len);
        }

        reader.close();
        writer.close();
    }
}
