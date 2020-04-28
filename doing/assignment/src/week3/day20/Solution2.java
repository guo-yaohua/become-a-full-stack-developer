package week3.day20;

import java.io.*;

public class Solution2 {
    public static void main(String[] args) throws IOException {
        Reader reader = new FileReader("day20text.txt");
        Writer writer = new FileWriter("day20text.txt", true);

        char[] chars = new char[26];
        reader.read(chars);
        alphabetSort(chars);
        writer.write("\n");
        writer.write(chars);

        reader.close();
        writer.close();
    }

    public static void alphabetSort(char[] chars) {
        char temp;
        for (int i = 0; i < chars.length - 1; i++) {
            for (int j = 0; j < chars.length - i - 1; j++) {
                if (chars[j] > chars[j + 1]) {
                    temp = chars[j];
                    chars[j] = chars[j + 1];
                    chars[j + 1] = temp;
                }
            }
        }
    }
}
