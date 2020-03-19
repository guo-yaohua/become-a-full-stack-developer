/*
【程序 26 求星期】
题目：请输入星期几的第一个字母来判断一下是星期几，如果第一个字母一样，则继续 判断第二个字
母。
程序分析：用情况语句比较好，如果第一个字母一样，则判断用情况语句或 if 语句判断第二个字母。

分析：
	星期一：Monday
	星期二：Tuesday
	星期三：Wednesday
	星期四：Thursday
	星期五：Friday
	星期六：Saturday
	星期日：Sunday
	周一、三、五直接确定，二、四、六、七需要判断 2 次
*/

import java.util.Scanner;

public class Ex26 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("输入第一个字母：");
		String str = sc.nextLine();
		char week = str.charAt(0);
		switch(week) {
			case 'M':
			case 'm':
				System.out.println("是星期一");
				break;
			case 'W':
			case 'w':
				System.out.println("是星期三");
				break;
			case 'F':
			case 'f':
				System.out.println("是星期五");
				break;
			case 'T':
			case 't':
				System.out.print("输入第二个字母：");
				str = sc.nextLine();
				week = str.charAt(0);
				if (week == 'U' || week == 'u') {
					System.out.println("是星期二");
				} else if (week == 'H' || week == 'h') {
					System.out.println("是星期四");
				} else {
					System.out.println("输入有误！");
				}
				break;
			case 'S':
			case 's':
				System.out.print("输入第二个字母：");
				str = sc.nextLine();
				week = str.charAt(0);
				if (week == 'A' || week == 'a') {
					System.out.println("是星期六");
				} else if (week == 'U' || week == 'u') {
					System.out.println("是星期日");
				} else {
					System.out.println("输入有误！");
				}
				break;
			default :
            	System.out.println("输入有误！");
		}
	}
}