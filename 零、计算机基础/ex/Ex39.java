/*
【程序 39 分数累加】
题目：编写一个函数，输入 n 为偶数时，调用函数求 1/2+1/4+...+1/n,当输入 n 为奇数时，调用函数
1/1+1/3+...+1/n
*/

import java.util.Scanner;

public class Ex39 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.close();
		
		double sum = 0;
		if (n % 2 == 0) {
			for (int i = 2; i <= n; i = i + 2) {
				sum += 1 / (double)i;	// 需要专制
			}
		} else {
			for (int i = 1; i <= n; i = i + 2) {
				sum += 1 / (double)i;
			}
		}

		System.out.print(sum);
	}
}