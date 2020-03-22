/*
【程序 3 水仙花数】
题目：打印出所有的"水仙花数"，所谓"水仙花数"是指一个三位数，其各位数字立方和等于该数本身。例
如：153 是一个"水仙花数"，因为 153=1 的三次方＋5 的三次方＋3 的三次方。
程序分析：利用 for 循环控制 100-999 个数，每个数分解出个位，十位，百位。

注：代码规范遵守 《阿里巴巴Java开发手册》
*/

public class Ex3{
	public static boolean isNarcissistic(int n) {
		int a, b, c;	// 个位，十位，百位
		a = n % 10;
		b = (n / 10) % 10;
		c = n / 100;

		if (Math.pow(a, 3) + Math.pow(b, 3) + Math.pow(c, 3) == n) {
			return true;
		} else
			return false;
	}

	public static void main(String[] args) {
		for (int i = 100; i <= 999; i++) {
			if (isNarcissistic(i)) {
				System.out.println(i);
			}
		}
	}
}