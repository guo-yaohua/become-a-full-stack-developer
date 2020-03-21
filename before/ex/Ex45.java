/*
【程序 45 被 9 整除】
题目：判断一个数能被几个 9 整除
*/

import java.util.Scanner;

public class Ex45 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("输入一个数：");
		int num = sc.nextInt();
		sc.close();
		int s = 0;
		int n = num;

		while(n % 9 == 0) {
			s++;
			n = n / 9;
		}
		if (s == 0) {
			System.out.printf("%d 不能被 9 整除\n",num);
		} else {
			System.out.printf("%d 能被 %d 个 9 整除\n",num,s);
		}
	}
}