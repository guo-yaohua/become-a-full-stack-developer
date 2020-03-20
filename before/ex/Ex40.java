/*
【程序 40 字符串排序】 输入一个字符串数组，按照字母表的降序对这些字符串进行排序。
题目：字符串排序。

分析：把字符比大小，用冒泡排序法
*/

import java.util.Scanner;

public class Ex40 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("输入一个字符串：");
		String input = sc.nextLine();
		int length = input.length();
		char[] str = new char[length];
		for (int i = 0; i < length; i++) {
			str[i] = input.charAt(i);
		}

		char temp;
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length - i - 1; j++) {
				// 大写字母 -'A'，小写字母 -'a'
				if (((str[j] > 'a' && str[j] < 'z') ? (str[j] - 'a') : (str[j] - 'A')) > ((str[j + 1] > 'a' && str[j + 1] < 'z') ? (str[j + 1] - 'a') : (str[j + 1] - 'A'))) {
					temp = str[j];
					str[j] = str[j + 1];
					str[j + 1] = temp;
				}
			}
		}

		System.out.print("俺字母表排序：");
		for (int i = 0; i < length; i++) {
			System.out.print(str[i]);
		}
	}
}