package week3.day19;

import java.io.*;

public class Solution1 {
    public static void main(String[] args) throws IOException {
        String[] words = {"verb:eat","verb:drink","verb:sleep","verb:play",
                "noun:rice","noun:meat","noun:hand","noun:hair"};

        OutputStream outVerb = new BufferedOutputStream(
                new FileOutputStream("verb.txt"));

        OutputStream outNoun = new BufferedOutputStream(
                new FileOutputStream("noun.txt"));

        for (String s : words) {
            if (s.substring(0, 4).equals("verb")) { // 判断
                byte[] bytesVerb = (s.substring(5, s.length()) + '\n').getBytes();
                outVerb.write(bytesVerb);
            } else {
                byte[] bytesNoun = (s.substring(5, s.length()) + '\n').getBytes();
                outNoun.write(bytesNoun);
            }
        }

        outVerb.close();
        outNoun.close();
    }
}
