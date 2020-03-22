/*
【程序 33 杨辉三角】
题目：打印出杨辉三角形（要求打印出 10 行如下图）
程序分析：
     1
    1 1
   1 2 1
  1 3 3  1
 1 4 6  4  1
1 5 10 10 5 1 
*/

public class Ex33 {
	public static final int N = 10;	// 10 行

	public static void main(String[] args) {
		// 初始化
		int[][] arr = new int[N][N];
		for (int i = 0; i < N; i++) {	// 每行的第一和最后一个数字是 0
			arr[i][0] = 1;
			arr[i][i] = 1;
		}

		// 求出其余值
		for (int i = 2; i < N; i++) {
			for (int j = 1; j < i; j++) {
				arr[i][j] = arr[i - 1][j - 1] + arr[i - 1][j];	// 杨辉三角每个元素等于父母之和
			}
		}

		// 规律输出
		for (int i = 0; i < N; i++) {
			for (int k = 0; k <= (N - i - 1) * 2; k++) {	// 下面控制每个元素占 4 个字格，则每行递减缩进 2 的倍数即可达到金字塔形状
				System.out.print(" ");
			}

			for (int j = 0; j <= i; j++) {
				System.out.printf("%3d ",arr[i][j]);	// 每项占 4 个字格
			}
			
			System.out.println();
		}
	}
}