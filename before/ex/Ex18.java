/*
【程序 18 乒乓球赛】
题目：两个乒乓球队进行比赛，各出三人。甲队为 a,b,c 三人，乙队为 x,y,z 三人。已抽签决定比赛名
单。有人向队员打听比赛的名单。a 说他不和 x 比，c 说他不和 x,z 比，请编程序找出三队赛手的名单。
*/

public class Ex18 {
	public static void main(String[] args) {
		char[] team1 = {'x','y','z'};	// 0, 1, 2 对应 x, y, z

		int a, b, c;
		for (a = 0; a <= 2; a++) {
			for (b = 0; b <= 2; b++) {
				if (a == b) {	// 不能重复
					continue;
				}
				for (c = 0; c <= 2; c++) {
					if (a == c || b == c) {	// 不能重复
						continue;
					}
					if ((a != 0) && (c != 0) && (c != 2)) {	// a 不和 x，c 不和 x 和 z
						System.out.println("a 和 " + team1[a] + " 比赛");
						System.out.println("b 和 " + team1[b] + " 比赛");
						System.out.println("c 和 " + team1[c] + " 比赛");
						break;
					}
				}
			}
		}
	}
}