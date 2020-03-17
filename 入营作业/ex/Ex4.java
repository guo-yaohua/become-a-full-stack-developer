/*
【程序 4 分解质因数】
题目：将一个大于 2 正整数分解质因数。例如：输入 3, 3=3, 输入 6, 6=2*3,输入 90, 90=2*3*3*5。
程序分析：对 n 进行分解质因数，应先找到一个最小的质数 k，然后按下述步骤完成：
(1)如果这个质数恰等于 n，则说明分解质因数的过程已经结束，打印出即可。
(2)如果 n<>k，但 n 能被 k 整除，则应打印出 k 的值，并用 n 除以 k 的商,作为新的正整数 n,重复执行第
一步。
(3)如果 n 不能被 k 整除，则用 k+1 作为 k 的值,重复执行第一步。

*/

import java.util.Scanner;

public class Ex4 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("输入一个正整数：");
		int n = sc.nextInt();
		System.out.print(n + "=");
		for (int i = 2; i <= n; i++) {
			if (n%i == 0) {
				System.out.print(i);
				n = n/i;
				i = 1;
				if (n != 0 && n!= 1) {
					System.out.print("*");
				}
			}
		}
	}
}