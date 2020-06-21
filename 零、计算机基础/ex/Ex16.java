/*
【程序 16 输入 9*9 表】
题目：输出 9*9 口诀。
程序分析：分行与列考虑，共 9 行 9 列，i 控制行，j 控制列。
*/

public class Ex16 {
	public static void main(String[] args) {
		for (int i = 1; i < 10; i++) {
			for (int j = 1; j <= i; j++) {
				// System.out.print(i + " + " + j + "=" + i * j);
				System.out.printf("%d * %d = %2d\t",i, j, i * j);	// java 中也有 printf
			}
			System.out.println("");	// 换行
		}
	}
}