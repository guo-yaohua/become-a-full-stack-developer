/*
【程序 10 自由落体】
题目：一球从 100 米高度自由落下，每次落地后反跳回原高度的一半；求它在 第 10 次落地时，共经过多
少米？第 10 次反弹多高？
*/

public class Ex10 {
	public static void main(String[] args) {
		float h = 100;
		float s = -100;	// 设起点为地面
		for (int i = 0; i < 10; i++) {
			s = s + h * 2;
			h = h / 2;
		}

		System.out.println("第 10 次落地时，共经过 " + s + " 米");
		System.out.println("第 10 次反弹 " + h + " 米");
	}
}