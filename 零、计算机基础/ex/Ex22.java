/*
【程序 22 递归求阶乘】
题目：利用递归方法求 5!。
程序分析：递归公式：fn=fn_1*4!
*/

public class Ex22 {
	// 递归
	public static int fn(int n){
		if (n == 1) {
			return 1;
		} else{
			return n * fn(n - 1);
		}
	}

	public static void main(String[] args) {
		System.out.println("5! = " + fn(5));
	}
}