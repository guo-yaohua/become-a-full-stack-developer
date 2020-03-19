/*
【程序 28 排序算法】
题目：对 10 个数进行排序
程序分析：可以利用选择法，即从后 9 个比较过程中，选择一个最小的与第一个元素交换， 下次类推，
即用第二个元素与后 8 个进行比较，并进行交换
*/

import java.util.Scanner;

public class Ex28 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] nums = new int[10];
		int i,j,k,min,temp;
		System.out.print("输入 10 个数：");
		for (i = 0; i < 10; i++) {
			nums[i] = sc.nextInt();
		}
		for (i = 0; i < 9; i++) {
			min = i;
			for (j = i + 1; j < 10; j++) {
				if (nums[min] > nums[j]) {
					min = j;
				}
			}
			temp = nums[min];
			nums[min] = nums[i];
			nums[i] = temp;
		}
		System.out.print("从小到大排列：");
		for (i = 0; i < 10; i++) {
			System.out.print(nums[i] + " ");
		}
	}
}