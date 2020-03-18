/*
【程序 14 求日期】
题目：输入某年某月某日，判断这一天是这一年的第几天？
程序分析：以 3 月 5 日为例，应该先把前两个月的加起来，然后再加上 5 天即本年的第几天，特殊情况，
闰年且输入月份大于 3 时需考虑多加一天。
*/

import java.util.Scanner;

public class Ex14 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("输入年月日：");
		int year = sc.nextInt();
		int month = sc.nextInt();
		int day = sc.nextInt();
		int dayTh = 0;
		for (int i = 1; ; i++) {
			if (i == month) {	// 第 i 月，加上日期并退出
				dayTh += day;
				break;
			} else{
				if (i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12) {	// 31天
					dayTh += 31;
				} else if (i == 2) {	// 2 月份
					// 不能被 4 整除的是平年，能被 100 整除 且不能被 400 整除的也是平年
					if ((year % 4 != 0) || (year % 100 == 0 && year % 400 != 0)) {
						dayTh += 28;
					} else {
						dayTh += 29;
					}
				} else {	// 30 天
					dayTh += 30;
				}
			}
		}
		System.out.println(dayTh);
	}
}