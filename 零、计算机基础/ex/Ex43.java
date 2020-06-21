/*
【程序 43 求奇数个数】
题目：求 0—7 所能组成的奇数个数。

分析：
	可以是 1 位数，2 位数...8 位数，但个位都是奇数
	最高位都不为 0

	似乎可以用递归简化代码
*/

public class Ex43 {
	// 设置一个函数，判断最新元素是否与之前元素重复
	public static boolean isSame(int[] num,int n) {
		for (int i = 0; i < n; i++) {
			if (num[i] == num[n]) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int all = 0;
		int[] num = new int[8];	// 存储 8 位，num[0] 存储个位
		// 个位需要判断是否为奇数
		for (num[0] = 0; num[0] < 8; num[0]++) {
			if (num[0] % 2 == 0) {
				continue;
			}
			all++;	// 1 位数

			// 其余位需要判断是否与之前数字重复
			for (num[1] = 0; num[1] < 8; num[1]++) {
				if (isSame(num,1))	continue;
				if (num[1] != 0)	all++;	// 2 位数

				for (num[2] = 0; num[2] < 8; num[2]++) {
					if (isSame(num,2))	continue;
					if (num[2] != 0)	all ++;	// 3 位数

					for (num[3] = 0; num[3] < 8; num[3] ++) {
						if (isSame(num,3))	continue;
						if (num[3] != 0)	all++;	// 4 位

						for (num[4] = 0; num[4] < 8; num[4]++) {
							if (isSame(num,4))	continue;
							if (num[4] != 0)	all++;	// 5 位

							for (num[5] = 0; num[5] < 8; num[5]++) {
								if (isSame(num,5))	continue;
								if (num[5] != 0)	all++;	// 6 位

								for (num[6] = 0; num[6] < 8; num[6]++) {
									if (isSame(num,6))	continue;
									if (num[6] != 0)	all++;	// 7 位

									for (num[7] = 0; num[7] < 8; num[7]++) {
										if (isSame(num,7))	continue;
										if (num[7] != 0)	all++;	// 8 位
									}
								}
							}
						}
					}
				}
			}
		}
		System.out.println("共有奇数 " + all + " 个");
	}
}