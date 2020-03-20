/*
【程序 34 三个数排序】
题目：输入 3 个数 a,b,c，按大小顺序输出。
程序分析：利用指针方法。

分析：java 里没有指针，那就用不改变本身的方法
*/

import java.util.Scanner;

public class Ex34 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		System.out.println((a > b && a > c) ? a : (b > c) ? b : c);	// 找出最大的
		System.out.println(((a > b && a < c) || (a < b && a > c)) ? a : ((b > c && b < a) || (b > a && b < c)) ? b : c);	// 找出中间的
		System.out.println((a < b && a < c) ? a : (b < c) ? b : c);	// 找出最小的
	}
}