/*
【程序 11 求不重复数字】
题目：有 1、2、3、4 个数字，能组成多少个互不相同且无重复数字的三位数？都是多少？
程序分析：可填在百位、十位、个位的数字都是 1、2、3、4。组成所有的排列后再去 掉不满足条件的排
列。
*/

public class Ex11 {
	public static void main(String[] args) {
		System.out.print("能组成的三位数：");
		int num = 0;
		for (int i = 1; i <= 4; i++) {	// 穷举
			for (int j = 1; j <= 4; j++) {
				if (i == j) {	// 重复就跳过
					continue;
				}
				for (int k = 1; k <= 4; k++) {
					if (k == j || k == i) {	// 重复就跳过
						continue;
					}
					System.out.print((i * 100 + j * 10 + k) + " ");
					num++;
				}
			}
		}

		System.out.println("\n共 " + num + " 个");
	}
}