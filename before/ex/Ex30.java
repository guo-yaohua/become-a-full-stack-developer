/*
【程序 30 插入数字】
题目：有一个已经排好序的数组。现输入一个数，要求按原来的规律将它插入数组中。
程序分析：首先判断此数是否大于最后一个数，然后再考虑插入中间的数的情况，插入后此元素之后的
数，依次后移一个位置。
*/

import java.util.Scanner;

public class Ex30 {
	public static void main(String[] args) {
		int[] arr = new int[100];
		System.out.print("输入一个有序数组：");
		int i = 0;

		// 通过设置 2 个 Scanner，达到 「\n 停止输入」 的效果
		Scanner sc1 = new Scanner(System.in);
		String input = sc1.nextLine();
		Scanner sc2 = new Scanner(input);
		while(sc2.hasNextInt()) {
			arr[i] = sc2.nextInt();
			i++;
		}
		int length = i;	// 数组长度

		System.out.print("插入一个数：");
		int n = sc1.nextInt();
		sc1.close();
		sc2.close();

		if (arr[0] > arr[length - 1]) {	// 如果是从大到小
			for (i = length - 1; i >= 0; i--)
				if (arr[i] < n) {
					arr[i + 1] = arr[i];
				} else {
					arr[i + 1] = n;
					break;
				}
		} else {	// 从小到大
			for (i = length - 1; i >= 0; i--)
				if (arr[i] > n) {
					arr[i + 1] = arr[i];
				} else {
					arr[i + 1] = n;
					break;
				}
		}
		
		System.out.print("新的有序数组：");
		for (i = 0; i < length + 1; i++) {
			System.out.print(arr[i] + " ");
		}
	}
}