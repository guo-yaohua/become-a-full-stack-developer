/*
 【程序 37 报数】
题目：有 n 个人围成一圈，顺序排号。从第一个人开始报数（从 1 到 3 报数），凡报到 3 的人退出圈子，
问最后留下的是原来第几号的那位。
*/

import java.util.Scanner;

public class Ex37 {
	// 输出数组中的第 k 个元素
	public static void del(int[] arr,int n,int k) {
		for (int i = k; i < n - 1; i++) {
			arr[i] = arr[i + 1];
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[n];
		
		/*
		角标：0 1 2 3 4...n-1
		赋值：1 2 3 4 5...n
		*/
		for (int i = 0; i < n; i++) {
			arr[i] = i + 1;
		}

		int k = 2;	// 从角标 2 开始删除
		while(true) {
			if (n == 1) {
				break;
			}
			k = k % n;
			del(arr,n,k);
			n--;
			k += 2;	// 删除操作后，每次角标前进 2 位即可
		}

		System.out.print("最后留下的是第 " + arr[0] + " 号");
	}
}