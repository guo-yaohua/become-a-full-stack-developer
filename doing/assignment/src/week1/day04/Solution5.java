package week1.day04;

public class Solution5 {
    public static void main(String[] args) {
        char[] charArr = {'a', 'b', 'a', '1', 'b', '2'};    // 字符串数组

        int[] arr = new int[130];   // 用一个数组记录出现次数
        for (int i : arr) {
            i = 0;
        }

        for (char ch : charArr) {   // 遍历字符串数组，记录出现次数
            arr[ch + 0]++;
        }

        for (char ch : charArr) {   // 二次遍历，找出第一次只出现一次的字符
            if (arr[ch + 0] == 1) {
                System.out.println(ch);
                break;
            }
        }
    }
}
