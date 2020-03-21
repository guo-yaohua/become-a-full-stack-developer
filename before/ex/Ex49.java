/*
【程序 49 子串出现的个数】
题目：计算字符串中子串出现的次数
*/

import java.util.Scanner;

public class Ex49 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("输入字符串：");
		String str1 = sc.nextLine();
		System.out.print("输入子串：");
		String str2 = sc.nextLine();
		sc.close();

		int i, j, sum = 0;
		for (i = 0; i <= str1.length() - str2.length(); i++) {
			for (j = 0; j < str2.length(); j++) {
				if (str1.charAt(i + j) != str2.charAt(j)) {
					break;
				}
			}
			if (j == str2.length()) {
				sum++;
			}
		}
		System.out.println("字符串出现了 " + sum + " 次");
	}
}