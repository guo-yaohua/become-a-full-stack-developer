/*
【程序 17 猴子吃桃问题】
题目：猴子吃桃问题：猴子第一天摘下若干个桃子，当即吃了一半，还不瘾，又多吃了一个 第二天早上
又将剩下的桃子吃掉一半，又多吃了一个。以后每天早上都吃了前一天剩下的一半零一个。到第 10 天早
上想再吃时，见只剩下一个桃子了。求第一天共摘了多少。
程序分析：采取逆向思维的方法，从后往前推断。
*/

public class Ex17 {
	public static void main(String[] args) {
		int todayEat = 1;
		for (int i = 9; i >= 1; i--) {
			todayEat = (todayEat + 1) * 2;	// 第 i 天还有多少
		}
		
		System.out.println("第一天摘了 " + todayEat + " 个");
	}
}