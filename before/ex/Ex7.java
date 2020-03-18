/*
【程序 7 处理字符串】
题目：输入一行字符，分别统计出其中英文字母、空格、数字和其它字符的个数。
程序分析：利用 while 语句，条件为输入的字符不为'\n'。

注：代码规范遵守 《阿里巴巴Java开发手册》
*/

import java.util.Scanner;

public class Ex7 {
	public static void main(String[] args) {
		int spaceNum = 0;
		int charNum = 0;
		int numberNum = 0;
		int otherNum = 0;
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == ' ') {
				spaceNum++;
			} else if ((str.charAt(i) >= 'a' && str.charAt(i) <= 'z') || (str.charAt(i) >= 'A' || str.charAt(i) >= 'Z')) {
				charNum++;
			} else if (str.charAt(i)>='0' && str.charAt(i) <='9') {
				numberNum++;
			} else {
				otherNum++;
			}
		}
		System.out.println("英文字母个数：" + charNum);
		System.out.println("空格个数：" + spaceNum);
		System.out.println("数字个数：" + numberNum);
		System.out.println("其它字符个数：" + otherNum);
	}
}