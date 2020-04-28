package week3.day20;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Solution1 {
    public static void main(String[] args) throws IOException {
        Reader reader = new FileReader("a.txt");
        char[] chars = new char[1024];
        int len;
        int baseNum = 0;
        int alphabeNum = 0;
        int numberNum = 0;
        while ((len = reader.read(chars)) != -1) {
            for (int i = 0; i < len; i++) {
                if (chars[i] == ' ') baseNum++;
                if ((chars[i] >= 'a' && chars[i] <= 'z') || (chars[i] >= 'A' && chars[i] <= 'Z')) alphabeNum++;
                if (chars[i] >= '0' && chars[i] <= '9') numberNum++;
            }
        }

        System.out.println("英文字母：" + alphabeNum);
        System.out.println("空格：" + baseNum);
        System.out.println("数字：" + numberNum);

        reader.close();
    }
}
