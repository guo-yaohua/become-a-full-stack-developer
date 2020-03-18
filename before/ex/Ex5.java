/*
【程序 5 判断分数等级】
题目：利用条件运算符的嵌套来完成此题：学习成绩>=90 分的同学用 A 表示，60-89 分之间的用 B 表示，
60 分以下的用 C 表示。
程序分析：(a>b)?a:b 这是条件运算符的基本例子

注：代码规范遵守 《阿里巴巴Java开发手册》
*/

import java.util.Scanner;

public class Ex5 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("输入学生成绩：");
		int n = sc.nextInt();
		System.out.println((n < 60) ? "C": (n < 90) ? "B" : "A");
	}
}