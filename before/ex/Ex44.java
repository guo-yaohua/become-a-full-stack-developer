/*
【程序 44 偶数的素数和】
题目：一个偶数总能表示为两个素数之和
*/

import java.util.Scanner;

public class Ex44 {
	// 判断是否为素数
	public static boolean isPrime(int n) {
		for(int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				return false;	// 能被整除即非素数
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("输入一个偶数：");
		int num = sc.nextInt();
		boolean isOk = false;	// 设置一个开关，默认不能，如果能表示，就打开
		sc.close();

		for (int i = 1; i <= num/2; i++) {
			if (isPrime(i) && isPrime(num - i)) {
				System.out.printf("该偶数 = %d + %d\n",i,num - i);
				isOk = true;
			}
		}
		if (isOk == false) {
			System.out.println("该偶数不能表示为两个奇数之和");
		}
	}
}