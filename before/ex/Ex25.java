/*
【程序 25 求回文数】
题目：一个 5 位数，判断它是不是回文数。即 12321 是回文数，个位与万位相同，十位与千位相同。
*/

import java.util.Scanner;

public class Ex25 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("输入一个 5 位数：");
		int n = sc.nextInt();
		sc.close();

		int[] num = new int[5];	// 数组记录每一位
		for (int i = 0; i < 5; i++) {
			num[i] = n % 10;
			n = n / 10;
		}

		if (num[0] == num[4] && num[1] == num[3]) {
			System.out.println("它是回文数");
		} else {
			System.out.println("它不是回文数");
		}
	}
}