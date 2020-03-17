/*
【程序 1 不死神兔】
题目：古典问题：有一对兔子，从出生后第 3 个月起每个月都生一对兔子，小兔子长到第三个月后每个月
又生一对兔子，假如兔子都不死，问每个月的兔子对数为多少？
程序分析： 兔子的规律为数列 1,1,2,3,5,8,13,21.... 

分析：斐波那契数列，每一项为前两项的和
方法：递归
*/

import java.util.Scanner;

public class Ex1{
	public static int Fib(int n) {
        if(n == 1||n == 2) {
            return 1;
        }
        else{
            return Fib(n-1) + Fib(n-2);  
        }  
    }  
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("输入月份：");
		int n = sc.nextInt();
		System.out.println("第" + n + "月，兔子的对数为：" +Fib(n));
	}
}