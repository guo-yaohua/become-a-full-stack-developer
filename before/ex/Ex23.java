/*
【程序 23 求岁数】
题目：有 5 个人坐在一起，问第五个人多少岁？他说比第 4 个人大 2 岁。问第 4 个人岁数，他说比第 3 个
人大 2 岁。问第三个人，又说比第 2 人大两岁。问第 2 个人，说比第一个人大两岁。最后问第一个人，他
说是 10 岁。请问第五个人多大？
程序分析：利用递归的方法，递归分为回推和递推两个阶段。要想知道第五个人岁数，需知道第四人的岁
数，依次类推，推到第一人（10 岁），再往回推。
*/

public class Ex23 {
	public static int age(int n) {
		if (n == 1) return 10;
		return age(n - 1) + 2;
	}
	public static void main(String[] args) {
		for (int i = 1; i <= 5; i++) {
			System.out.printf("第 %d 个人 %d 岁。\n",i,age(i));
		}
	}
}