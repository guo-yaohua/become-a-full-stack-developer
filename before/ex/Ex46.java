/*
【程序 46 字符串连接】
题目：两个字符串连接程序,将两个字符串拼接在一起
*/

import java.util.Scanner;

public class Ex46 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("输入两个字符串");
		String str1 = sc.nextLine();
		String str2 = sc.nextLine();
		sc.close();

		String str3 = str1 + str2;
		System.out.println(str3);
	}
}