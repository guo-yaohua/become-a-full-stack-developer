package week2.day16;

import java.util.Scanner;

public class Solution3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        scanner.close();

        System.out.println(longestPalindrome(s));
    }

    public static String longestPalindrome(String s) {
        // 设置记录
        int max = 0;
        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {
            // qwq 情况
            int temp = 0;
            for (int j = 0; ; j++) {
                if (i - j >= 0 && i + j < s.length() && s.charAt(i - j) == s.charAt(i + j)) {
                    temp++; // 对称长度
                } else {
                    break;
                }
            }
            if (temp > max) {
                max = temp;
                start = i - temp + 1;
                end = i + temp - 1;
            }

            // qwwq 情况
            temp = 0;
            for (int j = 0, k = 1; ; j++, k++) {
                if (i - j >= 0 && i + k < s.length() && s.charAt(i - j) == s.charAt(i + k)) {
                    temp++;
                } else {
                    break;
                }
            }
            if (temp >= max) {  // >=
                max = temp;
                start = i - temp + 1;
                end = i + temp;
            }
        }

        return s.substring(start, end + 1);
    }
}
