/*
【程序 6 求最大公约数及最小公倍数】
题目：输入两个正整数 m 和 n，求其最大公约数和最小公倍数。
程序分析：利用辗除法

分析：最小公倍数等于两数之积除以其最大公约数
注：代码规范遵守 《阿里巴巴Java开发手册》
*/

import java.util.Scanner;

public class Ex6 {
	public static int gcd(int a,int b) {
    	int temp;
    	while(b != 0) {	// 辗除法，直到 b == 0；int 无法转换为 boolean
	        temp = b;
	        b = a % b;
	        a = temp;
	    }
    	return a;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("输入两个正整数（用空格隔开）：");
		int m = sc.nextInt();
		int n = sc.nextInt();
		System.out.println("最大公约数为：" + gcd(m, n));
		System.out.println("最小公倍数为：" + m * n / gcd(m, n));
	}
}