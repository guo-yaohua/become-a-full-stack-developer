/*
【程序 9 求完数】
题目：一个数如果恰好等于它的所有因子之和，这个数就称为"完数"。例如 6=1＋2＋3.编程找出 1000 以
内的所有完数。

注：代码规范遵守 《阿里巴巴Java开发手册》
*/

public class Ex9 {
	public static void main(String[] args) {
		System.out.print("1000 以内所有完数：");
		for (int j = 1; j <= 1000; j++) {	// 遍历 1~1000
			int s = 0;
			for (int i = 1; i < j; i++) {	// 求出所有因子之和
				if (j % i == 0) {
					s += i;
				}
			}

			if (s == j) {	// 如果是完数
				System.out.print(s + " ");		
			}
		}
	}
}