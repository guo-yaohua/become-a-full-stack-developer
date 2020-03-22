/*
【程序 36 移动位置】
题目：有 n 个整数，使其前面各数顺序向后移 m 个位置，最后 m 个数变成最前面的 m 个数,比如输入数字
为 1 2 3 4 5 6 7 8 9 0，m=4，则结果为 7 8 9 0 1 2 3 4 5 6 

分析：
1. 分成两部分，分别转置。 6 5 4 3 2 1，0 9 8 7
2. 合并。 6 5 4 3 2 1 0 9 8 7
3. 转置。 7 8 9 0 1 2 3 4 5 6
*/

import java.util.Scanner;

public class Ex36 {
	// 转置数组
	public static void trans(int[] arr,int a,int b) {
		for (int temp,i = 0; i <= (b - a) / 2; i++) {
			temp = arr[a + i];
			arr[a + i] = arr[b - i];
			arr[b - i] = temp;
		}
	}

	public static void main(String[] args) {
		int[] arr = new int[100];
		int length = 0;

		System.out.print("输入一个数组：");
		// 通过设置 2 个 Scanner，达到 「\n 停止输入」 的效果
		Scanner sc1 = new Scanner(System.in);
		String input = sc1.nextLine();
		Scanner sc2 = new Scanner(input);
		while(sc2.hasNextInt()) {
			arr[length] = sc2.nextInt();
			length++;	// 记录数组长度
		}

		System.out.print("向后移动多少位：");
		Scanner sc = new Scanner(System.in);
		int m = sc.nextInt();

		trans(arr,0,length - m -1 );	// 转置前半段
		trans(arr,length - m,length - 1);	// 转置后半段
		trans(arr,0,length - 1);	// 全部转置

		System.out.printf("向后移动 %d 位后：",m);
		for (int i = 0; i < length; i++) {
			System.out.print(arr[i] + " ");
		}

		sc1.close();
		sc2.close();
		sc.close();
	}
}

