/*
【程序 13 根据条件求数字】
题目：一个整数，它加上 100 后是一个完全平方数，再加上 268 又是一个完全平方数，请问该数是多少？
程序分析：在 10 万以内判断，先将该数加上 100 后再开方，再将该数加上 268 后再开方，如果开方后的
结果满足如下条件，即是结果。
*/

public class Ex13 {
	public static void main(String[] args) {
		for (long i = 0; i <= 100000; i++) {
			long n1 = (long)Math.sqrt(i + 100);
			long n2 = (long)Math.sqrt(i + 368);
			if (Math.pow(n1, 2) == (i + 100) && Math.pow(n2, 2) == (i + 368)) {
				System.out.println(i);
				break;
			}
		}
	}
}