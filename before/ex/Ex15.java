/*
【程序 15 排序】
题目：输入三个整数 x,y,z，请把这三个数由小到大输出。
程序分析：我们想办法把最小的数放到 x 上，先将 x 与 y 进行比较，如果 x>y 则将 x 与 y 的值进行交换，
然后再用 x 与 z 进行比较，如果 x>z 则将 x 与 z 的值进行交换，这样能使 x 最小。
*/

import java.util.Scanner;

public class Ex15 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int y = sc.nextInt();
		int z = sc.nextInt();
		int temp;
		if (x > y) {
			temp = x;
			x = y;
			y = temp;
		}
		if (x > z) {
			temp = x;
			x = z;
			z = temp;
		}
		if (y > z) {
			temp = z;
			z = y;
			y = temp;
		}
		System.out.println(x);
		System.out.println(y);
		System.out.println(z);
	}
}