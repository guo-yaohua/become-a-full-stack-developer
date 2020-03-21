/*
【程序 48 加密】
题目：某个公司采用公用电话传递数据，数据是四位的整数，在传递过程中是加密的，加密
规则如下：每位数字都加上 5,然后用和除以 10 的余数代替该数字，再将第一位和第四位交
换，第二位和第三位交换。
*/

import java.util.Scanner;

public class Ex48 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("输入数据（4 位数）：");
		int input = sc.nextInt();
		sc.close();

		int[] date = new int[4];

		for (int i = 0; i < 4; i++) {
			date[i] = ((input % 10) + 5) % 10;
			input /= 10;
		}

		System.out.print("加密后：" );
		for (int i = 0; i < 4; i++) {
			System.out.print(date[i]);
		}
	}
}