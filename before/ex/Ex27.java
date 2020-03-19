/*
【程序 27 求素数】
题目：求 100 之内的素数
*/

public class Ex27 {
	public static boolean isPrime(int n) {
		for(int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				return false;	// 能被整除即非素数
			}
		}
		return true;
	}
	public static void main(String[] args) {
		int i,n;
		for(i = 1; i <= 100; i++) {
			if (isPrime(i)) {	// 如果为素数，就 +1
				System.out.print(i + " ");
			}
		}
	}
}