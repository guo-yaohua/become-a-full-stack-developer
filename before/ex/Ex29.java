/*
【程序 29 求矩阵对角线之和】
题目：求一个 3*3 矩阵对角线元素之和
程序分析：利用双重 for 循环控制输入二维数组，再将 a[i][i]累加后输出。
*/

import java.util.Scanner;

public class Ex29 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] matrix = new int[3][3];
		int num = 0;

		// 输入矩阵
		System.out.print("输入一个 3 * 3 矩阵：");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				matrix[i][j] = sc.nextInt();
			}
		}
		sc.close();
		
		for (int i = 0; i < 3; i++) {
			num += matrix[i][i];
		}
		System.out.println("对角线之和：" + num);
	}
}