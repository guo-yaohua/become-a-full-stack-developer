/*
【程序 32 左移右移】
题目：取一个整数 a 从右端开始的 4～7 位。
程序分析：可以这样考虑：
*/

import java.util.Scanner;

public class Ex32 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("输入一个数（位数大于7）：");
		long num = sc.nextLong();
		sc.close();
		
		num = num / 1000;
		num = num % 10000;
		System.out.println("4~7 位为：" + num);
	}
}