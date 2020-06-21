/*
【程序 47 打印星号】
题目：读取 7 个数（1—50）的整数值，每读取一个值，程序打印出该值个数的＊。
*/

import java.util.Scanner;

public class Ex47 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int i = 1; i <= 7; i++) {
			System.out.print("输入第 " + i + " 个数（1-50）:");
			float num = sc.nextFloat();
			if (num > 50 || num < 1) {
				System.out.println("输入有误！");
				i--;	// 输错的话，本次不算
				continue;
			}
			for (int j = 0; j < (int)num; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		sc.close();
	}
}