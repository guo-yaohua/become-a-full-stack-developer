package week2.day16;

import java.util.Scanner;

public class Solution2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.nextLine();
        String s2 = scanner.nextLine();
        scanner.close();

        System.out.println("出现次数：" + occurrenceNumber(s1, s2));
    }

    public static int occurrenceNumber(String s1, String s2) {
        int s1Length = s1.length();
        int s2Length = s2.length();
        int nums = 0;

        for (int i = 0; i + s2Length <= s1Length; i++) {
            if (s1.substring(i, i + s2Length).equals(s2)) { // 先取等长字符串再判断是否相等
                nums++;
            }
        }
        return nums;
    }
}
