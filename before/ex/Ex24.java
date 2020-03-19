/*
【程序 24 根据输入求输出】
题目：给一个不多于 5 位的正整数，要求：一、求它是几位数，二、逆序打印出各位数字。
*/

import java.util.Scanner;

public class Ex24 {
	static int n = 0;
	public static void reverse(int num) {
		if (num != 0) {
			System.out.print(num % 10 + " ");
			n++;
			reverse(num/10);
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("输入一个不多于 5 位的正整数：");
		int num = sc.nextInt();
		System.out.print("它的逆序为：");
		reverse(num);
		System.out.printf("\n它是 %d 位数\n",n);
	}
}