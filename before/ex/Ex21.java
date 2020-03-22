/*
【程序 21 求阶乘】
题目：求 1+2!+3!+...+20!的和
程序分析：此程序只是把累加变成了累乘。
*/

public class Ex21 {
	// 阶乘
	public static long factorial(int n) {
		long fac = 1L;
		for (int i = 1; i <= n; i++) {
			fac *= i;
		}
		return fac;
	}

	public static void main(String[] args) {
		long sum = 0L;	// 需要定义为 long 型
		for (int i = 1; i <= 20; i++) {
			sum += factorial(i);
		}
		System.out.println(sum);
	}
}