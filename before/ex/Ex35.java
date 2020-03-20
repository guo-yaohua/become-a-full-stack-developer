/*
【程序 35 最大最小交换】
题目：输入数组，最大的与第一个元素交换，最小的与最后一个元素交换，输出数组。
*/

import java.util.Scanner;

public class Ex35 {
	public static void main(String[] args) {
		System.out.print("输入一个数组：");
		int[] arr = new int[100];
		int i = 0;

		// 通过设置 2 个 Scanner，达到 「\n 停止输入」 的效果
		Scanner sc1 = new Scanner(System.in);
		String input = sc1.nextLine();
		Scanner sc2 = new Scanner(input);
		while(sc2.hasNextInt()) {
			arr[i] = sc2.nextInt();
			i++;
		}

		int length = i;
		int min = 0,max = 0;
		int temp;
		for (i = 0; i < length; i++) {	// 找到最大值，最小值的坐标
			if (arr[i] < arr[min]) {
				min = i;
			}
			if (arr[i] > arr[max]) {
				max = i;
			}
		}

		// 交换
		temp = arr[0];
		arr[0] = arr[max];
		arr[max] = temp;
		temp = arr[length - 1];
		arr[length - 1] = arr[min];
		arr[min] = temp;

		// 输出
		for (i = 0; i < length; i++) {
			System.out.print(arr[i] + " ");
		}
	}
}