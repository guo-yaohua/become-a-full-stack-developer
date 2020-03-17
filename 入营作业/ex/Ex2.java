/*
【程序 2 输出素数】
题目：判断 101-200 之间有多少个素数，并输出所有素数。
程序分析：判断素数的方法：用一个数分别去除 2 到 sqrt(这个数)，如果能被整除，则表明此数不是素
数，反之是素数。

方法：循环判断。
*/

public class Ex2 {
	public static boolean IsPrime(int n) {
		int i;
		boolean IsP = true;
		for(i = 2;i <= Math.sqrt(n);i++) {
			if(n%i == 0){
				IsP = false;
				break;
			}
		}
		return IsP;
	}
	public static void main(String[] args) {
		int i,n,num;
		for(i = 101,num = 0; i<=200; i++) {
			if(IsPrime(i)) {	//如果为素数，就 +1
				num++;
			}
		}
		System.out.println(num);
	}
}