/*
【程序 42 求数字】
题目：809*??=800*??+9*??+1
其中??代表的两位数,8*??的结果为两位数，9*??的结果为 3 位数。求??代表的两位数，及 809*??后的结
果
*/

public class Ex42 {
	public static void main(String[] args) {
		int num;
		for (num = 10; num <= 99; num++) {
			if ((809*num == 800*num + 9 * num + 1) && (8*num < 100) && (9*num >= 100) ) {
				System.out.println("??为：" + num);
				break;
			}
		}
		if (num == 100) {
			System.out.print("无解");
		}

	}
}