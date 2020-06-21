/*
【程序 8 输入数字求和】
题目：求 s=a+aa+aaa+aaaa+aa...a 的值，其中 a 是一个数字。例如 2+22+222+2222+22222(此时共有 5 个
数相加)，几个数相加有键盘控制。
程序分析：关键是计算出每一项的值。

注：代码规范遵守 《阿里巴巴Java开发手册》
*/

import java.util.Scanner;

public class Ex8 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("输入数字及项数：");
		int a = sc.nextInt();
		int n = sc.nextInt();
		sc.close();

		int s = 0;
		int aN = 0;	// 第 N 项
		int carry = 1;	// 单位 1 10 100 1000
		for (int i = 1; i <= n; i++) {
			aN = aN + a * carry;	// 求第 N 项的值
			s += aN;
			carry *= 10;
 		}

		System.out.println("和值：" + s);
	}
}