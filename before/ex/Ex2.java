/*
【程序 2 输出素数】
题目：判断 101-200 之间有多少个素数，并输出所有素数。
程序分析：判断素数的方法：用一个数分别去除 2 到 sqrt(这个数)，如果能被整除，则表明此数不是素
数，反之是素数。

方法：循环判断。
注：代码规范遵守 《阿里巴巴Java开发手册》
*/

public class Ex2 {
	public static boolean isPrime(int n) {
		for(int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				return false;	// 能被整除即非素数
			}
		}
		return true;
	}
	public static void main(String[] args) {
		int i,n,num;
		for(i = 101,num = 0; i <= 200; i++) {
			if (isPrime(i)) {	// 如果为素数，就 +1
				System.out.print(i + " ");
				num++;
			}
		}
		System.out.printf("\n共 %d 个\n", num);
	}
}