/*
【程序 20 求前 20 项之和】
题目：有一分数序列：2/1，3/2，5/3，8/5，13/8，21/13...求出这个数列的前 20 项之和。
程序分析：请抓住分子与分母的变化规律。

分析：分子和分母都是斐波那契数列
*/

public class Ex20 {
	// 斐波那契数列
	public static int fib(int n) {
        if (n == 1 || n == 2) {
            return 1;
        } else {
            return fib(n - 1) + fib(n - 2);  
        }  
    } 

	public static void main(String[] args) {
		double sum = 0;
		for (int i = 1 ; i <= 20; i++) {
			sum += (double)fib(i + 2) / (double)fib(i + 1);	// 需要强转
		}
		System.out.println(sum);
	}
}