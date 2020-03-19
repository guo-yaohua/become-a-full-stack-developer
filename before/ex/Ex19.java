/*
【程序 19 打印菱形图案】
题目：打印出如下图案（菱形）
    *
   ***
  *****
 *******
  *****
   ***
    *
程序分析：先把图形分成两部分来看待，前四行一个规律，后三行一个规律，利用双重 for 循环，第一
层控制行，第二层控制列。
*/

public class Ex19 {
	public static void main(String[] args) {
		for (int i = 1; i <= 7; i++) {
			if (i <= 4) {
				for (int j = 1; j <= 4 - i; j++) {
					System.out.print(" ");
				}
				for (int j = 1; j <= (2 * i - 1); j++) {
					System.out.print("*");
				}
			} else {
				for (int j = 1; j <= i - 4; j++) {
					System.out.print(" ");
				}
				for (int j = 1; j <= ((8 - i) * 2 - 1); j++) {
					System.out.print("*");
				}
			}
			System.out.println();
		}
	}
}