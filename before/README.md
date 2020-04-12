# 入营作业

## 目录

- [50题](#50题)

- [编程基础知识](#编程基础知识)  
  - [知识点要求](#知识点要求)  
  - [知识点整理](#知识点整理)

## 50题

### Ex1

程序 1 不死神兔  
题目：古典问题：有一对兔子，从出生后第 3 个月起每个月都生一对兔子，小兔子长到第三个月后每个月
又生一对兔子，假如兔子都不死，问每个月的兔子对数为多少？  
程序分析：兔子的规律为数列 1，1，2，3，5，8，13，21...   

源代码：  
```java
/*
分析：斐波那契数列，每一项为前两项的和
方法：递归
注：代码规范遵守 《阿里巴巴Java开发手册》
*/

import java.util.Scanner;

public class Ex1 {
	// 递归
	public static int fib(int n) {
        if (n == 1 || n == 2) {
            return 1;
        } else {
            return fib(n - 1) + fib(n - 2);  
        }  
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("输入月份：");
		int n = sc.nextInt();
		sc.close();

		System.out.println("第 " + n + " 月，兔子的对数为：" + fib(n));
	}
}
```

运行截图：  
![Ex1](./img/Ex1.png)  

### Ex2

程序 2 输出素数  
题目：判断 101-200 之间有多少个素数，并输出所有素数。  
程序分析：判断素数的方法：用一个数分别去除 2 到 sqrt(这个数)，如果能被整除，则表明此数不是素
数，反之是素数。  

源代码  
```java
/*
方法：循环判断。
注：代码规范遵守 《阿里巴巴Java开发手册》
*/

public class Ex2 {
	// 判断是否是素数
	public static boolean isPrime(int n) {
		for(int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				return false;	// 能被整除即非素数
			}
		}
		return true;
	}

	public static void main(String[] args) {
		int i,n,num;	// num 记录个数

		for(i = 101,num = 0; i <= 200; i++) {
			if (isPrime(i)) {	// 如果为素数，就 +1
				System.out.print(i + " ");
				num++;
			}
		}
		System.out.printf("\n共 %d 个\n", num);
	}
}
```

运行截图：  
![Ex2](./img/Ex2.png)  

### Ex3

程序 3 水仙花数  
题目：打印出所有的"水仙花数"，所谓"水仙花数"是指一个三位数，其各位数字立方和等于该数本身。例如：153 是一个"水仙花数"，因为 153=1 的三次方＋5 的三次方＋3 的三次方。  
程序分析：利用 for 循环控制 100-999 个数，每个数分解出个位，十位，百位。  

源代码：
```java
public class Ex3{
	public static boolean isNarcissistic(int n) {
		int a, b, c;	// 个位，十位，百位
		a = n % 10;
		b = (n / 10) % 10;
		c = n / 100;

		if (Math.pow(a, 3) + Math.pow(b, 3) + Math.pow(c, 3) == n) {
			return true;
		} else
			return false;
	}

	public static void main(String[] args) {
		for (int i = 100; i <= 999; i++) {
			if (isNarcissistic(i)) {
				System.out.println(i);
			}
		}
	}
}
```

运行截图：  
![Ex3](./img/Ex3.png)  

### Ex4

程序 4 分解质因数  
题目：将一个大于 2 正整数分解质因数。例如：输入 3, 3=3, 输入 6, 6=2\*3,输入 90, 90=2\*3\*3\*5。  
程序分析：对 n 进行分解质因数，应先找到一个最小的质数 k，然后按下述步骤完成：  
1. 如果这个质数恰等于 n，则说明分解质因数的过程已经结束，打印出即可。  
2. 如果 n<>k，但 n 能被 k 整除，则应打印出 k 的值，并用 n 除以 k 的商,作为新的正整数 n,重复执行第
一步。  
3. 如果 n 不能被 k 整除，则用 k+1 作为 k 的值,重复执行第一步。

源代码：
```java
import java.util.Scanner;

public class Ex4 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("输入一个正整数：");
		int n = sc.nextInt();
		sc.close();

		System.out.print("分解质因数：" + n + " =");
		for (int i = 2; i <= n; i++) {
			if (n % i == 0) {
				System.out.print(i);
				n = n / i;
				i = 1;	// 整除一次后，i 重置为 2（还有一个 i++）
				if (n != 0 && n != 1) {	// 如果还能分解加一个乘号
					System.out.print(" * ");
				}
			}
		}
	}
}
```

运行截图：  
![Ex4](./img/Ex4.png)  

### Ex5

程序 5 判断分数等级  
题目：利用条件运算符的嵌套来完成此题：学习成绩>=90 分的同学用 A 表示，60-89 分之间的用 B 表示，
60 分以下的用 C 表示。  
程序分析：(a>b)?a:b 这是条件运算符的基本例子  

源代码：  
```java
import java.util.Scanner;

public class Ex5 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("输入学生成绩：");
		int n = sc.nextInt();
		sc.close();

		System.out.println((n < 60) ? "C": (n < 90) ? "B" : "A");
	}
}
```

运行截图  
![Ex5](./img/Ex5.png)  

### Ex6

程序 6 求最大公约数及最小公倍数  
题目：输入两个正整数 m 和 n，求其最大公约数和最小公倍数。  
程序分析：利用辗除法  

源代码：
```java
import java.util.Scanner;

public class Ex6 {
	// 求最大公约数
	public static int gcd(int a,int b) {
    	int temp;
    	while(b != 0) {	// 辗除法，直到 b == 0；int 无法转换为 boolean，不能用 (b) 判断
	        temp = b;
	        b = a % b;
	        a = temp;
	    }
    	return a;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("输入两个正整数：");
		int m = sc.nextInt();
		int n = sc.nextInt();
		sc.close();

		System.out.println("最大公约数为：" + gcd(m, n));
		System.out.println("最小公倍数为：" + m * n / gcd(m, n));
	}
}
```

运行截图：  
![Ex6](./img/Ex6.png)  

### Ex7

程序 7 处理字符串  
题目：输入一行字符，分别统计出其中英文字母、空格、数字和其它字符的个数。  
程序分析：利用 while 语句，条件为输入的字符不为'\n'。  

源代码：
```java
import java.util.Scanner;

public class Ex7 {
	public static void main(String[] args) {
		int spaceNum = 0;
		int charNum = 0;
		int numberNum = 0;
		int otherNum = 0;

		System.out.print("输入一行字符：");
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		sc.close();

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == ' ') {
				spaceNum++;
			} else if ((str.charAt(i) >= 'a' && str.charAt(i) <= 'z') || (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z')) {
				charNum++;
			} else if (str.charAt(i)>= '0' && str.charAt(i) <= '9') {
				numberNum++;
			} else {
				otherNum++;
			}
		}

		System.out.println("英文字母个数：" + charNum + "\n空格个数：" + spaceNum 
			+ "\n数字个数：" + numberNum + "\n其它字符个数：" + otherNum);
	}
}
```

运行截图：  
![Ex7](./img/Ex7.png)  

### Ex8

程序 8 输入数字求和  
题目：求 s=a+aa+aaa+aaaa+aa...a 的值，其中 a 是一个数字。例如 2+22+222+2222+22222(此时共有 5 个
数相加)，几个数相加有键盘控制。  
程序分析：关键是计算出每一项的值。  

源代码：
```java
import java.util.Scanner;

public class Ex8 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("输入数字及项数：");
		int a = sc.nextInt();
		int n = sc.nextInt();
		sc.close();

		int s = 0;
		int aN = 0;	// 第 N 项
		int carry = 1;	// 单位 1 10 100 1000
		for (int i = 1; i <= n; i++) {
			aN = aN + a * carry;	// 求第 N 项的值
			s += aN;
			carry *= 10;
 		}

		System.out.println("和值：" + s);
	}
}
```

运行截图：  
![Ex8](./img/Ex8.png)  

### Ex9

程序 9 求完数  
题目：一个数如果恰好等于它的所有因子之和，这个数就称为"完数"。例如 6=1＋2＋3。编程找出 1000 以内的所有完数。  

源代码：  
```java
public class Ex9 {
	public static void main(String[] args) {
		System.out.print("1000 以内所有完数：");
		for (int j = 1; j <= 1000; j++) {	// 遍历 1~1000
			int s = 0;
			for (int i = 1; i < j; i++) {	// 求出所有因子之和
				if (j % i == 0) {
					s += i;
				}
			}

			if (s == j) {	// 如果是完数
				System.out.print(s + " ");		
			}
		}
	}
}
```

运行截图：  
![Ex9](./img/Ex9.png)

### Ex10

程序 10 自由落体  
题目：一球从 100 米高度自由落下，每次落地后反跳回原高度的一半；求它在 第 10 次落地时，共经过多少米？第 10 次反弹多高？

源代码：
```java
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
```

运行截图：  
![Ex10](./img/Ex10.png)

### Ex11

程序 11 求不重复数字  
题目：有 1、2、3、4 个数字，能组成多少个互不相同且无重复数字的三位数？都是多少？  
程序分析：可填在百位、十位、个位的数字都是 1、2、3、4。组成所有的排列后再去 掉不满足条件的排列。  

源代码：
```java
public class Ex11 {
	public static void main(String[] args) {
		System.out.print("能组成的三位数：");
		int num = 0;
		for (int i = 1; i <= 4; i++) {	// 穷举
			for (int j = 1; j <= 4; j++) {
				if (i == j) {	// 重复就跳过
					continue;
				}
				for (int k = 1; k <= 4; k++) {
					if (k == j || k == i) {	// 重复就跳过
						continue;
					}
					System.out.print((i * 100 + j * 10 + k) + " ");
					num++;
				}
			}
		}

		System.out.println("\n共 " + num + " 个");
	}
}
```

运行截图：  
![Ex11](./img/Ex11.png)  

### Ex12

程序 12 计算奖金  
题目：企业发放的奖金根据利润提成。利润(I)低于或等于 10 万元时，奖金可提 10%；利润高于 10 万元，低于 20 万元时，低于 10 万元的部分按 10%提成，高于 10 万元的部分，可可提成 7.5%；20 万到 40 万之间时，高于 20 万元的部分，可提成 5%；40 万到 60 万之间时高于 40 万元的部分，可提成 3%；60 万到 100 万之间时，高于 60 万元的部分，可提成 1.5%，高于 100 万元时，超过 100 万元的部分按 1%提成，从键盘输入当月利润 I，求应发放奖金总数？  
程序分析：请利用数轴来分界，定位。注意定义时需把奖金定义成长整型。

源代码：
```java
import java.util.Scanner;

public class Ex12 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("输入当月利润（元）：");
		long n = sc.nextInt();
		sc.close();
	
		double bonus;
		// 分段函数
		if (n <= 100000) {
			bonus = n * 0.1;
		} else if (n <= 200000) {
			bonus = 100000 * 0.1 + (n - 100000) * 0.075;
		} else if (n <= 400000) {
			bonus = 100000 * 0.1 + 100000 * 0.075 + (n - 200000) * 0.05;
		} else if (n <= 600000) {
			bonus = 100000 * 0.1 + 100000 * 0.075 + 200000 * 0.05 + (n - 400000) * 0.03;
		} else if (n <= 1000000) {
			bonus = 100000 * 0.1 + 100000 * 0.075 + 200000 * 0.05 + 200000 * 0.03 + (n - 600000) * 0.015 ;
		} else {
			bonus = 100000 * 0.1 + 100000 * 0.075 + 200000 * 0.05 + 200000 * 0.03 + 400000 * 0.015 + (n - 1000000) * 0.01 ;
		}

		System.out.println("应发奖金 " + bonus + "元");
	}
}
```

运行截图：  
![Ex12](./img/Ex12.png)

### Ex13

程序 13 根据条件求数字  
题目：一个整数，它加上 100 后是一个完全平方数，再加上 268 又是一个完全平方数，请问该数是多少？  
程序分析：在 10 万以内判断，先将该数加上 100 后再开方，再将该数加上 268 后再开方，如果开方后的结果满足如下条件，即是结果。  

源代码：
```java
public class Ex13 {
	public static void main(String[] args) {
		for (int i = 0; i <= 100000; i++) {
			int n1 = (int)Math.sqrt(i + 100);
			int n2 = (int)Math.sqrt(i + 368);

			if (Math.pow(n1, 2) == (i + 100) && Math.pow(n2, 2) == (i + 368)) {
				System.out.println(i);
				break;
			}
		}
	}
}
```

运行截图：
![Ex13](./img/Ex13.png)  

### Ex14

程序 14 求日期  
题目：输入某年某月某日，判断这一天是这一年的第几天？
程序分析：以 3 月 5 日为例，应该先把前两个月的加起来，然后再加上 5 天即本年的第几天，特殊情况，闰年且输入月份大于 3 时需考虑多加一天。  

源代码：
```java
import java.util.Scanner;

public class Ex14 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("输入年月日：");
		int year = sc.nextInt();
		int month = sc.nextInt();
		int day = sc.nextInt();
		sc.close();

		int dayTh = 0;
		for (int i = 1; ; i++) {
			if (i == month) {	// 第本月，则加上日期并退出
				dayTh += day;
				break;
			} else{
				if (i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12) {	// 31天
					dayTh += 31;
				} else if (i == 2) {	// 2 月份
					// 不能被 4 整除的是平年，能被 100 整除 且不能被 400 整除的也是平年
					if ((year % 4 != 0) || (year % 100 == 0 && year % 400 != 0)) {
						dayTh += 28;
					} else {
						dayTh += 29;
					}
				} else {	// 30 天
					dayTh += 30;
				}
			}
		}

		System.out.println("该天是该年的第 " + dayTh + " 天");
	}
}
```

运行截图：  
![Ex14](./img/Ex14.png)

### Ex15

程序 15 排序  
题目：输入三个整数 x,y,z，请把这三个数由小到大输出。  
程序分析：我们想办法把最小的数放到 x 上，先将 x 与 y 进行比较，如果 x>y 则将 x 与 y 的值进行交换，
然后再用 x 与 z 进行比较，如果 x>z 则将 x 与 z 的值进行交换，这样能使 x 最小。  

源代码：  
```java
import java.util.Scanner;

public class Ex15 {
	public static void main(String[] args) {
		System.out.print("输入三个数：");
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int y = sc.nextInt();
		int z = sc.nextInt();
		sc.close();

		int temp;
		if (x > y) {
			temp = x;
			x = y;
			y = temp;
		}
		if (x > z) {
			temp = x;
			x = z;
			z = temp;
		}
		if (y > z) {
			temp = z;
			z = y;
			y = temp;
		}

		System.out.println("递增排序后：" + x + " " + y + " " + z);
	}
}
```

运行截图：  
![Ex15](./img/Ex15.png)

### Ex16

程序 16 输入 9*9 表  
题目：输出 9*9 口诀。  
程序分析：分行与列考虑，共 9 行 9 列，i 控制行，j 控制列。  

源代码：
```java
public class Ex16 {
	public static void main(String[] args) {
		for (int i = 1; i < 10; i++) {
			for (int j = 1; j <= i; j++) {
				// System.out.print(i + " + " + j + "=" + i * j);
				System.out.printf("%d * %d = %2d\t",i, j, i * j);	// java 中也有 printf
			}
			System.out.println("");	// 换行
		}
	}
}
```

运行截图：  
![Ex16](./img/Ex16.png)  

### Ex17

程序 17 猴子吃桃问题  
题目：猴子吃桃问题：猴子第一天摘下若干个桃子，当即吃了一半，还不瘾，又多吃了一个 第二天早上
又将剩下的桃子吃掉一半，又多吃了一个。以后每天早上都吃了前一天剩下的一半零一个。到第 10 天早上想再吃时，见只剩下一个桃子了。求第一天共摘了多少。  
程序分析：采取逆向思维的方法，从后往前推断。

源代码：
```java
public class Ex17 {
	public static void main(String[] args) {
		int todayEat = 1;
		for (int i = 9; i >= 1; i--) {
			todayEat = (todayEat + 1) * 2;	// 第 i 天还有多少
		}
		
		System.out.println("第一天摘了 " + todayEat + " 个");
	}
}
```

运行截图：  
![Ex17](./img/Ex17.png)

### Ex18

程序 18 乒乓球赛  
题目：两个乒乓球队进行比赛，各出三人。甲队为 a,b,c 三人，乙队为 x,y,z 三人。已抽签决定比赛名单。有人向队员打听比赛的名单。a 说他不和 x 比，c 说他不和 x,z 比，请编程序找出三队赛手的名单。  

源代码：
```java
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
```

运行截图：  
![Ex18](./img/Ex18.png)  

### Ex19

程序 19 打印菱形图案 
题目：打印出如下图案（菱形）  
![rhombus](./img/rhombus.png)  
程序分析：先把图形分成两部分来看待，前四行一个规律，后三行一个规律，利用双重 for 循环，第一
层控制行，第二层控制列。

源代码：
```java
public class Ex19 {
	public static void main(String[] args) {
		for (int i = 1; i <= 7; i++) {	// 7 行
			if (i <= 4) {	// 前 4 行
				for (int j = 1; j <= 4 - i; j++) {
					System.out.print(" ");
				}
				for (int j = 1; j <= (2 * i - 1); j++) {
					System.out.print("*");
				}
			} else {	// 后 3行
				for (int j = 1; j <= i - 4; j++) {
					System.out.print(" ");
				}
				for (int j = 1; j <= ((8 - i) * 2 - 1); j++) {
					System.out.print("*");
				}
			}
			System.out.println();	// 每行结束需要换行
		}
	}
}
```

运行截图：  
![Ex19](./img/Ex19.png)  

### Ex20

程序 20 求前 20 项之和  
题目：有一分数序列：2/1，3/2，5/3，8/5，13/8，21/13...求出这个数列的前 20 项之和。
程序分析：请抓住分子与分母的变化规律。

源代码：
```java
/*
分析：分子和分母都是斐波那契数列
*/

public class Ex20 {
	// 斐波那契数列
	public static int fib(int n) {
        if (n == 1 || n == 2) {
            return 1;
        } else {
            return fib(n - 1) + fib(n - 2);  
        }  
    } 

	public static void main(String[] args) {
		double sum = 0;
		for (int i = 1 ; i <= 20; i++) {
			sum += (double)fib(i + 2) / (double)fib(i + 1);	// 需要强转
		}
		System.out.println(sum);
	}
}
```

运行截图：  
![Ex20](./img/Ex20.png)

### Ex21

程序 21 求阶乘  
题目：求 1+2!+3!+...+20!的和  
程序分析：此程序只是把累加变成了累乘。

源代码：
```java
public class Ex21 {
	// 阶乘
	public static long factorial(int n) {
		long fac = 1L;
		for (int i = 1; i <= n; i++) {
			fac *= i;
		}
		return fac;
	}

	public static void main(String[] args) {
		long sum = 0L;	// 需要定义为 long 型
		for (int i = 1; i <= 20; i++) {
			sum += factorial(i);
		}
		System.out.println(sum);
	}
}
```

运行截图：  
![Ex21](./img/Ex21.png)  

### Ex22

程序 22 递归求阶乘  
题目：利用递归方法求 5!。  
程序分析：递归公式：fn=fn_1*4!  

源代码：
```java
public class Ex22 {
	// 递归
	public static int fn(int n){
		if (n == 1) {
			return 1;
		} else{
			return n * fn(n - 1);
		}
	}

	public static void main(String[] args) {
		System.out.println("5! = " + fn(5));
	}
}
```

运行截图：  
![Ex22](./img/Ex22.png)  

### Ex23

【程序 23 求岁数】  
题目：有 5 个人坐在一起，问第五个人多少岁？他说比第 4 个人大 2 岁。问第 4 个人岁数，他说比第 3 个人大 2 岁。问第三个人，又说比第 2 人大两岁。问第 2 个人，说比第一个人大两岁。最后问第一个人，他说是 10 岁。请问第五个人多大？  
程序分析：利用递归的方法，递归分为回推和递推两个阶段。要想知道第五个人岁数，需知道第四人的岁数，依次类推，推到第一人（10 岁），再往回推。

源代码：
```java
public class Ex23 {
	// 递归
	public static int age(int n) {
		if (n == 1) return 10;
		return age(n - 1) + 2;	// 第 i 个人比第 i - 1 个人大 2 岁
	}

	public static void main(String[] args) {
		System.out.printf("第 5 个人 %d 岁。\n",age(5));
	}
}
```

运行截图：  
![Ex23](./img/Ex23.png)  

### Ex24

程序 24 根据输入求输出  
题目：给一个不多于 5 位的正整数，要求：一、求它是几位数，二、逆序打印出各位数字。  

源代码：
```java
import java.util.Scanner;

public class Ex24 {
	static int n = 0;	// 全局遍历记录位数

	// 递归打印
	public static void reverse(int num) {
		if (num != 0) {
			System.out.print(num % 10 + " ");
			n++;
			reverse(num / 10);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("输入一个不多于 5 位的正整数：");
		int num = sc.nextInt();
		sc.close();

		System.out.print("它的逆序为：");
		reverse(num);
		System.out.printf("\n它是 %d 位数\n",n);
	}
}
```

运行截图：  
![Ex24](./img/Ex24.png)  

### Ex25

程序 25 求回文数  
题目：一个 5 位数，判断它是不是回文数。即 12321 是回文数，个位与万位相同，十位与千位相同。

源代码：
```java
import java.util.Scanner;

public class Ex25 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("输入一个 5 位数：");
		int n = sc.nextInt();
		sc.close();

		int[] num = new int[5];	// 数组记录每一位
		for (int i = 0; i < 5; i++) {
			num[i] = n % 10;
			n = n / 10;
		}

		if (num[0] == num[4] && num[1] == num[3]) {
			System.out.println("它是回文数");
		} else {
			System.out.println("它不是回文数");
		}
	}
}
```

运行截图：  
![Ex25](./img/Ex25.png)  

### Ex26

程序 26 求星期  
题目：请输入星期几的第一个字母来判断一下是星期几，如果第一个字母一样，则继续 判断第二个字
母。  
程序分析：用情况语句比较好，如果第一个字母一样，则判断用情况语句或 if 语句判断第二个字母。  

源代码：  
```java
/*
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
		switch(week) {	// 考虑大小写
			case 'M':
			case 'm':
				System.out.println("Monday 星期一");
				break;
			case 'W':
			case 'w':
				System.out.println("Wednesday 是星期三");
				break;
			case 'F':
			case 'f':
				System.out.println("Friday 星期五");
				break;
			case 'T':
			case 't':
				System.out.print("输入第二个字母：");
				str = sc.nextLine();
				week = str.charAt(0);
				if (week == 'U' || week == 'u') {
					System.out.println("Tuesday 星期二");
				} else if (week == 'H' || week == 'h') {
					System.out.println("Thursday 是星期四");
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
					System.out.println("Saturday 星期六");
				} else if (week == 'U' || week == 'u') {
					System.out.println("Sunday 星期日");
				} else {
					System.out.println("输入有误！");
				}
				break;
			default :
            	System.out.println("输入有误！");
		}
		
		sc.close();	// 可能会有第二次输入，放在最后关闭
	}
}
```

运行截图：  
![Ex26](./img/Ex26.png)  

### Ex27

程序 27 求素数  
题目：求 100 之内的素数  

源代码：
```java
public class Ex27 {
	// 判断是否是素数
	public static boolean isPrime(int n) {
		for(int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				return false;	// 能被整除即非素数
			}
		}
		return true;
	}
	public static void main(String[] args) {
		int i,n;
		System.out.print("100 以内的素数有：");
		
		for(i = 1; i <= 100; i++) {
			if (isPrime(i)) {	// 如果为素数，就 +1
				System.out.print(i + " ");
			}
		}
	}
}
```

运行截图：  
![Ex27](./img/Ex27.png)  

### Ex28

程序 28 排序算法  
题目：对 10 个数进行排序  
程序分析：可以利用选择法，即从后 9 个比较过程中，选择一个最小的与第一个元素交换， 下次类推，即用第二个元素与后 8 个进行比较，并进行交换

源代码：  
```java
import java.util.Scanner;

public class Ex28 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] nums = new int[10];
		int i,j,k,min,temp;
		System.out.print("输入 10 个数：");
		for (i = 0; i < 10; i++) {
			nums[i] = sc.nextInt();
		}
		sc.close();

		// 选择排序
		for (i = 0; i < 9; i++) {
			min = i;
			for (j = i + 1; j < 10; j++) {
				if (nums[min] > nums[j]) {
					min = j;
				}
			}
			temp = nums[min];
			nums[min] = nums[i];
			nums[i] = temp;
		}
		
		System.out.print("从小到大排列：");
		for (i = 0; i < 10; i++) {
			System.out.print(nums[i] + " ");
		}
	}
}
```

运行截图：  
![Ex28](./img/Ex28.png)  

### Ex29

程序 29 求矩阵对角线之和  
题目：求一个 3*3 矩阵对角线元素之和  
程序分析：利用双重 for 循环控制输入二维数组，再将 a[i][i]累加后输出。

源代码：
```java
import java.util.Scanner;

public class Ex29 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] matrix = new int[3][3];
		int num = 0;

		// 输入矩阵
		System.out.print("输入一个 3 * 3 矩阵：");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				matrix[i][j] = sc.nextInt();
			}
		}
		sc.close();
		
		for (int i = 0; i < 3; i++) {
			num += matrix[i][i];
		}
		System.out.println("对角线之和：" + num);
	}
}
```

运行截图：  
![Ex29](./img/Ex29.png)  

### Ex30

程序 30 插入数字  
题目：有一个已经排好序的数组。现输入一个数，要求按原来的规律将它插入数组中。  
程序分析：首先判断此数是否大于最后一个数，然后再考虑插入中间的数的情况，插入后此元素之后的
数，依次后移一个位置。  

源代码：  
```java
import java.util.Scanner;

public class Ex30 {
	public static void main(String[] args) {
		int[] arr = new int[100];
		System.out.print("输入一个有序数组：");
		int i = 0;

		// 通过设置 2 个 Scanner，达到 「\n 停止输入」 的效果
		Scanner sc1 = new Scanner(System.in);
		String input = sc1.nextLine();
		Scanner sc2 = new Scanner(input);
		while(sc2.hasNextInt()) {
			arr[i] = sc2.nextInt();
			i++;
		}
		int length = i;	// 数组长度

		System.out.print("插入一个数：");
		int n = sc1.nextInt();
		sc1.close();
		sc2.close();

		if (arr[0] > arr[length - 1]) {	// 如果是从大到小
			for (i = length - 1; i >= 0; i--)
				if (arr[i] < n) {
					arr[i + 1] = arr[i];
				} else {
					arr[i + 1] = n;
					break;
				}
		} else {	// 从小到大
			for (i = length - 1; i >= 0; i--)
				if (arr[i] > n) {
					arr[i + 1] = arr[i];
				} else {
					arr[i + 1] = n;
					break;
				}
		}
		
		System.out.print("新的有序数组：");
		for (i = 0; i < length + 1; i++) {
			System.out.print(arr[i] + " ");
		}
	}
}
```

运行截图：  
![Ex30](./img/Ex30.png)  

### Ex31

程序 31 数组逆序  
题目：将一个数组逆序输出。  
程序分析：用第一个与最后一个交换。  

源代码：  
```java
public class Ex31 {
    public static void main(String[] args) {
        int[] arr = {1, 11, 5, 15, 8, 9, 10};
        System.out.print("原本顺序：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

        for (int temp,i = 0; i < (arr.length + 1) / 2; i++) {   // 第一个与最后一个交换
            temp = arr[i]; 
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 -i] = temp;
        }

        System.out.print("\n逆序：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
```

运行截图：  
![Ex31](./img/Ex31.png)  

### Ex32

程序 32 左移右移  
题目：取一个整数 a 从右端开始的 4～7 位。  

源代码：
```java
import java.util.Scanner;

public class Ex32 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("输入一个数（位数大于7）：");
		long num = sc.nextLong();
		sc.close();
		
		num = num / 1000;
		num = num % 10000;
		System.out.println("4~7 位为：" + num);
	}
}
```

运行截图：  
![Ex32](./img/Ex32.png)  

### Ex33

程序 33 杨辉三角  
题目：打印出杨辉三角形（要求打印出 10 行如下图）
程序分析：  
![yh](./img/yh.png)  

源代码：  
```java
public class Ex33 {
	public static final int N = 10;	// 10 行

	public static void main(String[] args) {
		// 初始化
		int[][] arr = new int[N][N];
		for (int i = 0; i < N; i++) {	// 每行的第一和最后一个数字是 0
			arr[i][0] = 1;
			arr[i][i] = 1;
		}

		// 求出其余值
		for (int i = 2; i < N; i++) {
			for (int j = 1; j < i; j++) {
				arr[i][j] = arr[i - 1][j - 1] + arr[i - 1][j];	// 杨辉三角每个元素等于父母之和
			}
		}

		// 规律输出
		for (int i = 0; i < N; i++) {
			for (int k = 0; k <= (N - i - 1) * 2; k++) {	// 下面控制每个元素占 4 个字格，则每行递减缩进 2 的倍数即可达到金字塔形状
				System.out.print(" ");
			}

			for (int j = 0; j <= i; j++) {
				System.out.printf("%3d ",arr[i][j]);	// 每项占 4 个字格
			}
			
			System.out.println();
		}
	}
}
```

运行截图：  
![Ex33](./img/Ex33.png)  

### Ex34

程序 34 三个数排序  
题目：输入 3 个数 a,b,c，按大小顺序输出。  
程序分析：利用指针方法。  

源代码：  
```java
/*
分析：java 里没有指针，那就尝试用不改变本身的方法输出
*/

import java.util.Scanner;

public class Ex34 {
	public static void main(String[] args) {
		System.out.print("输入三个数：");
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		sc.close();

		System.out.print("递减顺序：");
		System.out.print((a > b && a > c) ? a : (b > c) ? b : c);	// 找出最大的
		System.out.print(" ");
		System.out.print(((a > b && a < c) || (a < b && a > c)) ? a : ((b > c && b < a) || (b > a && b < c)) ? b : c);	// 找出中间的
		System.out.print(" ");
		System.out.print((a < b && a < c) ? a : (b < c) ? b : c);	// 找出最小的
	}
}
```

运行截图：  
![Ex34](./img/Ex34.png)  

### Ex35

程序 35 最大最小交换  
题目：输入数组，最大的与第一个元素交换，最小的与最后一个元素交换，输出数组。  

源代码：  
```java
import java.util.Scanner;

public class Ex35 {
	public static void main(String[] args) {
		System.out.print("输入一个数组：");
		int[] arr = new int[100];
		int i = 0;

		// 通过设置 2 个 Scanner，达到 「\n 停止输入」 的效果
		Scanner sc1 = new Scanner(System.in);
		String input = sc1.nextLine();
		Scanner sc2 = new Scanner(input);
		while(sc2.hasNextInt()) {
			arr[i] = sc2.nextInt();
			i++;
		}
		sc1.close();
		sc2.close();

		int length = i;	// 数组长度
		int min = 0,max = 0;
		int temp;
		for (i = 0; i < length; i++) {	// 找到最大值，最小值的坐标
			if (arr[i] < arr[min]) {
				min = i;
			}
			if (arr[i] > arr[max]) {
				max = i;
			}
		}

		// 交换
		if (min + max == length - 1) {	// 如果最大值，最小值在两边，互换一次即可
			temp = arr[0];
			arr[0] = arr[max];
			arr[max] = temp;
		} else {
			temp = arr[0];
			arr[0] = arr[max];
			arr[max] = temp;
			temp = arr[length - 1];
			arr[length - 1] = arr[min];
			arr[min] = temp;
		}

		// 输出
		System.out.print("最大最小交换后：");
		for (i = 0; i < length; i++) {
			System.out.print(arr[i] + " ");
		}
	}
}
```

运行截图：  
![Ex35](./img/Ex35.png)  

### Ex36

程序 36 移动位置  
题目：有 n 个整数，使其前面各数顺序向后移 m 个位置，最后 m 个数变成最前面的 m 个数,比如输入数字
为 1 2 3 4 5 6 7 8 9 0，m=4，则结果为 7 8 9 0 1 2 3 4 5 6   

源代码：
```java
/*
分析：
1. 分成两部分，分别转置。 6 5 4 3 2 1，0 9 8 7
2. 合并。 6 5 4 3 2 1 0 9 8 7
3. 转置。 7 8 9 0 1 2 3 4 5 6
*/

import java.util.Scanner;

public class Ex36 {
	// 转置数组
	public static void trans(int[] arr,int a,int b) {
		for (int temp,i = 0; i <= (b - a) / 2; i++) {
			temp = arr[a + i];
			arr[a + i] = arr[b - i];
			arr[b - i] = temp;
		}
	}

	public static void main(String[] args) {
		int[] arr = new int[100];
		int length = 0;

		System.out.print("输入一个数组：");
		// 通过设置 2 个 Scanner，达到 「\n 停止输入」 的效果
		Scanner sc1 = new Scanner(System.in);
		String input = sc1.nextLine();
		Scanner sc2 = new Scanner(input);
		while(sc2.hasNextInt()) {
			arr[length] = sc2.nextInt();
			length++;	// 记录数组长度
		}

		System.out.print("向后移动多少位：");
		Scanner sc = new Scanner(System.in);
		int m = sc.nextInt();

		trans(arr,0,length - m -1 );	// 转置前半段
		trans(arr,length - m,length - 1);	// 转置后半段
		trans(arr,0,length - 1);	// 全部转置

		System.out.printf("向后移动 %d 位后：",m);
		for (int i = 0; i < length; i++) {
			System.out.print(arr[i] + " ");
		}

		sc1.close();
		sc2.close();
		sc.close();
	}
}
```

运行截图：  
![Ex36](./img/Ex36.png)

### Ex37

程序 37 报数  
题目：有 n 个人围成一圈，顺序排号。从第一个人开始报数（从 1 到 3 报数），凡报到 3 的人退出圈子，问最后留下的是原来第几号的那位。  

源代码：  
```java
/*
分析：通过 % 达到绕圈的效果
*/

import java.util.Scanner;

public class Ex37 {
	// 删除数组中的第 k 个元素
	public static void del(int[] arr,int n,int k) {
		for (int i = k; i < n - 1; i++) {
			arr[i] = arr[i + 1];
		}
	}

	public static void main(String[] args) {
		System.out.print("一共多少人：");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.close();

		int[] arr = new int[n];
		/*
		角标：0 1 2 3 4...n-1
		赋值：1 2 3 4 5...n
		*/
		for (int i = 0; i < n; i++) {
			arr[i] = i + 1;
		}

		int k = 2;	// 从角标 2 开始删除
		while(true) {
			if (n == 1) {
				break;
			}
			k = k % n;
			del(arr,n,k);
			n--;
			k += 2;	// 删除操作后，之前的元素后退 1 位，因此每次角标前进 2 位即可
		}

		System.out.print("最后留下的是第 " + arr[0] + " 号");
	}
}
```

运行截图：  
![Ex37](./img/Ex37.png)

### Ex38

程序 38 求字符串长度  
题目：写一个函数，求一个字符串的长度，在 main 函数中输入字符串，并输出其长度。

源代码：  
```java
import java.util.Scanner;

public class Ex38 {
	public static void main(String[] args) {
		System.out.print("输入一个字符串：");
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		sc.close();
		System.out.println("字符串长度：" + str.length());
	}
}
```

运行截图：  
![Ex38](./img/Ex38.png)  

### Ex39

程序 39 分数累加  
题目：编写一个函数，输入 n 为偶数时，调用函数求 1/2+1/4+...+1/n,当输入 n 为奇数时，调用函数 1/1+1/3+...+1/n

源代码：  
```java
import java.util.Scanner;

public class Ex39 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.close();
		
		double sum = 0;
		if (n % 2 == 0) {
			for (int i = 2; i <= n; i = i + 2) {
				sum += 1 / (double)i;	// 需要专制
			}
		} else {
			for (int i = 1; i <= n; i = i + 2) {
				sum += 1 / (double)i;
			}
		}

		System.out.print(sum);
	}
}
```

运行截图：  
![Ex39](./img/Ex39.png)  

### Ex40

程序 40 字符串排序  
输入一个字符串数组，按照字母表的降序对这些字符串进行排序。  
题目：字符串排序。

源代码：
```java
/*
分析：把字符比大小，用冒泡排序法
*/

import java.util.Scanner;

public class Ex40 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("输入一个字符串：");
		String input = sc.nextLine();
		sc.close();

		// 字符串转为 char 数组
		int length = input.length();
		char[] str = new char[length];
		for (int i = 0; i < length; i++) {
			str[i] = input.charAt(i);
		}

		// 冒泡排序
		char temp;
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length - i - 1; j++) {
				// 大写字母 -'A'，小写字母 -'a'
				if (((str[j] > 'a' && str[j] < 'z') ? (str[j] - 'a') : (str[j] - 'A')) > ((str[j + 1] > 'a' && str[j + 1] < 'z') ? (str[j + 1] - 'a') : (str[j + 1] - 'A'))) {
					temp = str[j];
					str[j] = str[j + 1];
					str[j + 1] = temp;
				}
			}
		}

		System.out.print("按字母表排序：");
		for (int i = 0; i < length; i++) {
			System.out.print(str[i]);
		}
	}
}
```

运行截图：  
![Ex40](./img/Ex40.png)

### Ex41

程序 41 猴子分桃  
题目：海滩上有一堆桃子，五只猴子来分。第一只猴子把这堆桃子凭据分为五份，多了一个，这只猴子把多的一个扔入海中，拿走了一份。第二只猴子把剩下的桃子又平均分成五份，又多了一个，它同样把多的一个扔入海中，拿走了一份，第三、第四、第五只猴子都是这样做的，问海滩上原来最少有多少个桃子？

源代码：  
```java
public class Ex41 {
	public static void main(String[] args) {
		int i,n,peach;
		n = 1;
		while(true) {
			peach = n;

			// 5 次达标才符合
			for (i = 1; i <= 5; i++) {
				if ((peach - 1) % 5 != 0) {	// 扔掉 1 个，是否能分成 5 份
					break;
				} else {
					peach = (peach - 1) / 5 * 4;
				}
			}

			if (i == 6) {
				System.out.print("桃子数目：" + n);
				break;
			}
			n++;
		}
	}
}
```

运行截图：  
![Ex41](./img/Ex41.png)

### Ex42

程序 42 求数字  
题目：809*??=800*??+9*??+1  
其中??代表的两位数,8*??的结果为两位数，9*??的结果为 3 位数。求??代表的两位数，及 809*??后的结果

源代码：  
```java
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
```

运行截图：  
![Ex42](./img/Ex42.png)

### Ex43

程序 43 求奇数个数  
题目：求 0—7 所能组成的奇数个数。  

源代码：  
```java
/*
分析：
	可以是 1 位数，2 位数...8 位数，但个位都是奇数
	最高位都不为 0

	似乎可以用递归简化代码
*/

public class Ex43 {
	// 设置一个函数，判断最新元素是否与之前元素重复
	public static boolean isSame(int[] num,int n) {
		for (int i = 0; i < n; i++) {
			if (num[i] == num[n]) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int all = 0;
		int[] num = new int[8];	// 存储 8 位，num[0] 存储个位
		// 个位需要判断是否为奇数
		for (num[0] = 0; num[0] < 8; num[0]++) {
			if (num[0] % 2 == 0) {
				continue;
			}
			all++;	// 1 位数

			// 其余位需要判断是否与之前数字重复
			for (num[1] = 0; num[1] < 8; num[1]++) {
				if (isSame(num,1))	continue;
				if (num[1] != 0)	all++;	// 2 位数

				for (num[2] = 0; num[2] < 8; num[2]++) {
					if (isSame(num,2))	continue;
					if (num[2] != 0)	all ++;	// 3 位数

					for (num[3] = 0; num[3] < 8; num[3] ++) {
						if (isSame(num,3))	continue;
						if (num[3] != 0)	all++;	// 4 位

						for (num[4] = 0; num[4] < 8; num[4]++) {
							if (isSame(num,4))	continue;
							if (num[4] != 0)	all++;	// 5 位

							for (num[5] = 0; num[5] < 8; num[5]++) {
								if (isSame(num,5))	continue;
								if (num[5] != 0)	all++;	// 6 位

								for (num[6] = 0; num[6] < 8; num[6]++) {
									if (isSame(num,6))	continue;
									if (num[6] != 0)	all++;	// 7 位

									for (num[7] = 0; num[7] < 8; num[7]++) {
										if (isSame(num,7))	continue;
										if (num[7] != 0)	all++;	// 8 位
									}
								}
							}
						}
					}
				}
			}
		}
		System.out.println("共有奇数 " + all + " 个");
	}
}
```

运行截图：  
![Ex43](./img/Ex43.png)  

### Ex44

程序 44 偶数的素数和  
题目：一个偶数总能表示为两个素数之和  

源代码：  
```java
import java.util.Scanner;

public class Ex44 {
	// 判断是否为素数
	public static boolean isPrime(int n) {
		for(int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				return false;	// 能被整除即非素数
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("输入一个偶数：");
		int num = sc.nextInt();
		boolean isOk = false;	// 设置一个开关，默认不能，如果能表示，就打开
		sc.close();

		for (int i = 1; i <= num/2; i++) {
			if (isPrime(i) && isPrime(num - i)) {
				System.out.printf("%d = %d + %d\n",num, i, num - i);
				isOk = true;
			}
		}
		if (isOk == false) {
			System.out.println("该偶数不能表示为两个奇数之和");
		}
	}
}
```

运行截图：  
![Ex44](./img/Ex44.png)  

### Ex45

程序 45 被 9 整除  
题目：判断一个数能被几个 9 整除  

源代码：  
```java
import java.util.Scanner;

public class Ex45 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("输入一个数：");
		int num = sc.nextInt();
		sc.close();

		int s = 0;
		int n = num;
		while(n % 9 == 0) {
			s++;
			n = n / 9;
		}

		if (s == 0) {	// 仍为 0，则不能被 9 整除
			System.out.printf("%d 不能被 9 整除\n",num);
		} else {
			System.out.printf("%d 能被 %d 个 9 整除\n",num,s);
		}
	}
}
```

运行截图：  
![Ex45](./img/Ex45.png)  

### Ex46

程序 46 字符串连接  
题目：两个字符串连接程序,将两个字符串拼接在一起  

源代码：  
```java
import java.util.Scanner;

public class Ex46 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("输入两个字符串：");
		String str1 = sc.nextLine();
		String str2 = sc.nextLine();
		sc.close();

		String str3 = str1 + str2;
		System.out.println("拼接后：" + str3);
	}
}
```

运行截图：  
![Ex46](./img/Ex46.png)  

### Ex47

程序 47 打印星号  
题目：读取 7 个数（1—50）的整数值，每读取一个值，程序打印出该值个数的＊。

源代码：  
```java
import java.util.Scanner;

public class Ex47 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int i = 1; i <= 7; i++) {
			System.out.print("输入第 " + i + " 个数（1-50）:");
			float num = sc.nextFloat();
			if (num > 50 || num < 1) {
				System.out.println("输入有误！");
				i--;	// 输错的话，本次不算
				continue;
			}
			for (int j = 0; j < (int)num; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		sc.close();
	}
}
```

运行截图：  
![Ex47](./img/Ex47.png)  

### Ex48

程序 48 加密  
题目：某个公司采用公用电话传递数据，数据是四位的整数，在传递过程中是加密的，加密  
规则如下：每位数字都加上 5,然后用和除以 10 的余数代替该数字，再将第一位和第四位交换，第二位和第三位交换。

源代码：  
```java
import java.util.Scanner;

public class Ex48 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("输入数据（4 位数）：");
		int input = sc.nextInt();
		sc.close();

		// 转换成数组
		int[] date = new int[4];
		for (int i = 0; i < 4; i++) {
			date[i] = ((input % 10) + 5) % 10;
			input /= 10;
		}

		System.out.print("加密后：" );
		for (int i = 0; i < 4; i++) {
			System.out.print(date[i]);
		}
	}
}
```

运行截图：  
![Ex48](./img/Ex48.png)

### Ex49

程序 49 子串出现的个数  
题目：计算字符串中子串出现的次数  


源代码：  
```java
import java.util.Scanner;

public class Ex49 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("输入字符串：");
		String str1 = sc.nextLine();
		System.out.print("输入子串：");
		String str2 = sc.nextLine();
		sc.close();

		int i, j, sum = 0;
		for (i = 0; i <= str1.length() - str2.length(); i++) {
			for (j = 0; j < str2.length(); j++) {	// 与字串第一个字符相同他，就对比
				if (str1.charAt(i + j) != str2.charAt(j)) {
					break;
				}
			}
			if (j == str2.length()) {	// 字串能遍历一次都相同，即出现一次
				sum++;
			}
		}
		
		System.out.println("子串出现了 " + sum + " 次");
	}
}
```

运行截图：  
![Ex49](./img/Ex49.png)  

### Ex50

程序 50 文件 IO  
题目：有五个学生，每个学生有 3 门课的成绩，从键盘输入以上数据（包括学生号，姓名，三门课成绩），计算出平均成绩，将原有的数据和计算出的平均分数存放在磁盘文件"stud"中。  

源代码：
```java
/*
IO 流控制写入


学号    姓名    课1    课2    课3
001		小明		90    90    90
002		小芳		100   100   100
003     小张    90    90    90
004     小李    90    90    90
005     小王    90    90    90
                90    90    90
*/


import java.io.*;
import java.util.Scanner;

public class Ex50 {
	public static void main(String[] args) throws IOException {
		// 学生信息用 4 个数组保存
		String[] studentId = new String[5];	// 学号
		String[] studentName = new String[5];	// 姓名
		int[][] studentCourse = new int[5][3];	// 三门课
		float[] averageScore = new float[3];	// 每门课的平均成绩

		// 输入
		Scanner sc = new Scanner(System.in);
		int i, j;
		for (i = 0; i < 5; i++) {
			System.out.printf("现在输入第 %d 个学生的信息\n学号：", i+1);
			studentId[i] = sc.nextLine();

			System.out.print("姓名：");
			studentName[i] = sc.nextLine();

			for (j = 0; j < 3; j++) {
				System.out.printf("课 %d 的成绩：", j + 1);
				studentCourse[i][j] = sc.nextInt();
			}
			sc.nextLine();	// nextInt 后回车，会跳过一个 nextLine
		}

		// 计算
		System.out.println();
		for (i = 0; i < 3; i++) {
			averageScore[i] = 0;
			for (j = 0; j < 5; j++) {
				averageScore[i] += studentCourse[j][i];
			}
			averageScore[i] /= 5;
			System.out.printf("课 %d 的平均成绩为：%.2f\n", i + 1, averageScore[i]);
		}

		// 写入文件
		File file = new File("stud.txt");
		file.createNewFile();
		FileWriter out = new FileWriter(file);

		// String 是最大的类型，由小到大可以自动转换
		out.write("学号\t姓名\t课1\t课2\t课3\n");
		for (i = 0; i < 5; i++) {
			out.write(studentId[i] + "\t" + studentName[i] + "\t");
			for (j = 0; j < 3; j++) {
				out.write(studentCourse[i][j] + "\t");
			}
			out.write("\n");
		}
		for (i = 0; i < 3; i++) {
			out.write("课 " + (i + 1) + " 的平均成绩：" + averageScore[i] + "\n");
		}
		out.flush();
		out.close();
	}
}
```

运行截图：  
![Ex50](./img/Ex50.png)  


## 编程基础知识

### 知识点要求

注：  
- **了解** —— 知道大概，自己明白就好。
- **理解** —— 自己明白，给别人能说明白。
- **掌握** —— 可以动手实践，可以解决相关问题。

计算机体系结构及组成原理：  

1. **了解**计算机的基本体系结构。计算机的组成部分及各个部分的作用。

2. **掌握**二进制表示数。给出一个任意的十进制数，会算它的二进制数。

3. **掌握**八进制数，十六进制数，掌握他们和 2 二进制的转换关系。

4. **了解**程序编译运行的基本流程。如何从源代码变为机器码，最终在计算机上执行。

操作系统：

1. **理解**计算机操作系统的概念。操作系统的作用，设计目标和具体功能。
2. **了解**进程和线程的概念。
3. **了解**计算机操作系统内存管理的概念。要明白为什么要有内存管理。（有兴趣的同学请研究内存管理算法）
4. **了解**计算机文件系统的概念。**了解**磁盘存储介质。
5. **了解**计算机 IO 管理主要的工作。**了解**缓存及缓冲区的概念。

Java 语言  

了解 Java 语言的基本信息，扒一扒发展历程等感兴趣的资料。  

1. 学会配置 Java 开发环境（SDK+eclipse）。**掌握** Java SDK 配置环境变量的意义。  

2. 会使用命令行编译运行 Java 程序。

3. 会使用 eclipse 编译运行 Java 程序。  

4. **掌握** Java 的基本数据类型，**掌握**每种类型的表示的数据及其范围。  

5. **掌握** Java 的基本运算符的意义及用法。如说出 & 和 && 的区别等。  

6. **掌握** Java 最基本的流程控制语句用法。包括条件语句和循环语句。  

7. **掌握**一维数组的创建及使用。  

8. **理解**类和对象的基本概念。  

数据结构：  
1. **理解**线性表的概念。

2. **掌握**线性表的两种实现，顺序实现及链式实现。

3. **理解**栈和队列的基本概念（深刻理解）。

4. **理解**栈和队列的顺序存储实现和链式存储实现。

5. **理解**数组和矩阵的概念。

6. **掌握**常见的内部排序方法（插入排序、冒泡排序、选择排序、快速排序、堆排序、希尔排序、归并排序、基数排序等）。

7. **了解**算法时间复杂度的概念。**了解**每种排序算法的时间复杂度。

### 知识点梳理

计算机体系结构及组成原理  

Q1：了解计算机的基本体系结构。  

A1：计算机体系结构是程序员所看到的计算机的属性，即计算机的逻辑结构和功能特征，包括其各个硬部件和软部件之间的相互关系。  
经典的冯·诺伊曼体系结构：  
- 采用存储程序方式，指令和数据不加区别混合存储在同一个存储器中，数据和程序在内存中是没有区别的，它们都是内存中的数据，当 EIP 指针指向哪，CPU 就加载那段内存中的数据，如果是不正确的指令格式，CPU 就会发生错误中断。  
指令和数据都可以送到运算器进行运算，即由指令组成的程序是可以修改的。  

- 存储器是按地址访问的线性编址的一维结构，每个单元的位数是固定的。  

- 指令由操作码和地址码组成。操作码指明本指令的操作类型，地址码指明操作数和地址。操作数本身无数据类型的标志，它的数据类型由操作码确定。  

- 通过执行指令直接发出控制信号控制计算机的操作。指令在存储器中按其执行顺序存放，由指令计数器指明要执行的指令所在的单元地址。指令计数器只有一个，一般按顺序递增，但执行顺序可按运算结果或当时的外界条件而改变。  

- 以运算器为中心，I/O 设备与存储器间的数据传送都要经过运算器。  

- 数据以二进制表示。  

Q2：计算机的组成部分及各个部分的作用。  

A2：计算机的主要组成部分可以归纳为五个部分：控制器、运算器、存储器、输入设备、和输出设备。
- 控制器  
  是整个计算机的中枢神经，其功能是对程序规定的控制信息进行解释，根据其要求进行控制，调度程序、数据、地址，协调计算机各部分工作及内存与外设的访问等。

- 运算器  
  运算器的功能是对数据进行各种算术运算和逻辑运算，即对数据进行加工处理。

- 存储器  
  存储器的功能是存储程序、数据和各种信号、命令等信息，并在需要时提供这些信息。

- 输入设备  
  输入设备是计算机的重要组成部分，输入设备与输出设备合你为外部设备，简称外设。输入设备的作用是将程序、原始数据、文字、字符、控制命令或现场采集的数据等信息输入到计算机。

- 输出设备  
  输出设备与输入设备同样是计算机的重要组成部分，它把外算机的中间结果或最后结果、机内的各种数据符号及文字或各种控制信号等信息输出出来。


Q3：关于进制。  

A3：二进制记数只用 0 和 1 两个符号，无需其他符号。同理，八进制计数只用 0~7 八个符号，十六进制计数使用 0~9 A B C D E F 十六个符号。  

- 二进制转十进制  
  方法：「按权展开求和」。  
  规则：个位上的数字的次数是 0，十位上的数字的次数是 1，......，依次递增，而十分位的数字的次数是 -1，百分位上数字的次数是 -2，......，依次递减。  
  例如：二进制数 1101 转十进制  
  1\*2^0 + 0\*2^1 + 1\*2^2 + 1\*2^3 = 1 + 0 + 4 + 8 = 13

- 十进制转二进制  
  方法：整数部分采用「除 2 取余，逆序排列」，小数部分采用「乘 2 取整法」。  
  规则：用 2 整除十进制整数，可以得到一个商和余数，再用 2 去除商，又会得到一个商和余数，如此进行，直到商为小于 1 时为止，然后把先得到的余数作为二进制数的低位有效位，后得到的余数作为二进制数的高位有效位，依次排列起来即得到所对应的十进制整数。  
  用十进制的小数乘以 2 并取走结果的整数（必是 0 或 1），然后再用剩下的小数重复刚才的步骤，直到剩余的小数为 0 时停止，最后将每次得到的整数部分按先后顺序从左到右排列即得到所对应二进制小数。  
  例如：十进制数 125.8125 转二进制为 1111101.1101  
  ![整数部分](./img/d1.png)   
  ![小数部分](./img/d2.png)   

- 八进制转二进制  
  方法 1：转十进制再转二进制  
  方法 2：每一位单独按十进制转二进制的方式转为二进制，二进制为 3 位，不足时以 0 补齐，即得。  
  例如：八进制数 226 转二进制  
  2 > 010; 2 > 010; 6 > 110; 结果为 10010110。

- 二进制转八进制  
  方法 1：转十进制再转八进制  
  方法 2：从末尾起，每三位分为一组，不足三位以 0 补齐，每组按二进制转十进制方式处理，即得。  
  例如：二进制数 10010110 转八进制  
  补0 > 010010110; 010 > 2; 010 > 2; 110 > 6; 结果为 226。

- 十六进制转二进制  
  方法 1：转十进制再转二进制  
  方法 2：每一位单独按十进制转二进制的方式转为二进制，二进制为 4 位，不足时以 0 补齐，即得。  
  例如：十六进制数 12C 转二进制  
  1 > 0001; 2 > 0010; C > 1100; 结果为 100101100。

- 二进制转十六进制  
  方法 1：转十进制再转十六进制  
  方法 2：从末尾起，每四位分为一组，不足四位以 0 补齐，每组按二进制转十进制方式处理，即得。  
  例如：二进制数 100101100 转十六进制  
  补0 > 000100101100; 0001 > 1; 0010 > 2; 1100 > C; 结果为 12C。

Q4：了解程序编译运行的基本流程。如何从源代码变为机器码，最终在计算机上执行。  

A4：一般高级语言程序编译的过程分为四个阶段：预处理、编译、汇编、链接。以 C 语言为例
- 预处理  
  编译器对各种预处理命令进行处理，包括头文件的包含、宏定义的扩展、条件编译的选择等。
- 编译  
  将预处理完的文件进行一系列词法分析、语法分析、语义分析及优化后，产生相应的汇编代码文件。
- 汇编  
  把作为中间结果的汇编代码翻译成了机器代码，即目标代码，不过它还不可以运行。
- 链接  
  通过链接器将一个个目标文件（或许还会有库文件）链接在一起生成一个完整的可执行程序。  


操作系统

Q1：理解计算机操作系统的概念。操作系统的作用，设计目标和具体功能。

A1：操作系统（Operating System，缩写：OS）是管理计算机硬件与软件资源的系统软件，同时也是计算机系统的内核与基石。操作系统需要处理如管理与配置内存、决定系统资源供需的优先次序、控制输入与输出设备、操作网络与管理文件系统等基本事务。操作系统也提供一个让用户与系统交互的操作界面。

Q2：了解进程和线程的概念。  

A2：进程（process），是指计算机中已运行的程序。  
线程（thread）是操作系统能够进行运算调度的最小单位。大部分情况下，它被包含在进程之中，是进程中的实际运作单位。一条线程指的是进程中一个单一顺序的控制流，一个进程中可以并发多个线程，每条线程并行执行不同的任务。  

Q3：了解计算机操作系统内存管理的概念。要明白为什么要有内存管理。  

A3：内存管理，是指软件运行时对计算机内存资源的分配和使用的技术。其最主要的目的是如何高效、快速的分配，并且在适当的时候释放和回收内存资源。  

Q4：了解计算机文件系统的概念。了解磁盘存储介质。  

A4：文件系统，通常指称管理磁盘数据的系统，可将数据以目录或文件的型式存储。  

Q5：了解计算机 IO 管理主要的工作。了解缓存及缓冲区的概念。  

A5：I/O（Input/Output），即输入 / 输出，通常指数据在存储器（内部和外部）或其他周边设备之间的输入和输出，是信息处理系统（例如计算机）与外部世界（可能是人类或另一信息处理系统）之间的通信。输入是系统接收的信号或数据，输出则是从其发送的信号或数据。  
高速缓存（英语：cache）简称缓存，原始意义是指访问速度比一般随机存取存储器（RAM）快的一种 RAM。如今缓存的概念已被扩充，不仅在 CPU 和主内存之间有 Cache，而且在内存和硬盘之间也有 Cache（磁盘缓存），乃至在硬盘与网络之间也有某种意义上的 Cache ──称为 Internet 临时文件夹或网络内容缓存等。凡是位于速度相差较大的两种硬件之间，用于协调两者数据传输速度差异的结构，均可称之为 Cache。  
缓冲器为暂时置放输出或输入数据的存储器。  


数据结构

Q1：线性表的概念。  

A1：线性表（Linear List）是由 n（n≥0）个数据元素（结点）a[0]，a[1]，a[2]…，a[n-1] 组成的有限序列。  

Q2：掌握线性表的两种实现，顺序实现及链式实现。  

A2：顺序表：顺序表是在计算机内存中以数组的形式保存的线性表，是指用一组地址连续的存储单元依次存储数据元素的线性结构。  
链表：链表（Linked list）不会按线性的顺序存储数据，而是在每一个节点里存到下一个节点的指针(Pointer)。  

Q3：理解栈和队列的基本概念（深刻理解）  

A3：栈（stack）是计算机科学中的一种抽象数据类型，只允许在有序的线性数据集合的一端（称为堆栈顶端）进行加入数据（push）和移除数据（pop）的运算。因而按照后进先出（LIFO, Last In First Out）的原理运作。  
队列（queue）是先进先出（FIFO, First-In-First-Out）的线性表。在具体应用中通常用链表或者数组来实现。队列只允许在后端（称为rear）进行插入操作，在前端（称为front）进行删除操作。  
 

Q4：理解数组和矩阵的概念。  

A4：数组数据结构（array data structure），简称数组（Array），是由相同类型的元素（element）的集合所组成的数据结构，分配一块连续的内存来存储。利用元素的索引（index）可以计算出该元素对应的存储地址。  
矩阵，可以理解为二维的数组。

Q5：时间复杂度  

A5：算法的时间复杂度（Time complexity）是一个函数，它定性描述该算法的运行时间。这是一个代表算法输入值的字符串的长度的函数。时间复杂度常用大 O 符号表述，不包括这个函数的低阶项和首项系数。使用这种方式时，时间复杂度可被称为是渐近的，亦即考察输入值大小趋近无穷时的情况。

Q6：掌握常见的内部排序方法。  

A6：插入排序，冒泡排序，选择排序，快速排序，堆排序，希尔排序，归并排序，基数排序  
- 插入排序  
  插入排序（Insertion Sort）是一种简单直观的排序算法。它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。插入排序在实现上，通常采用in-place排序（即只需用到 O(1) 的额外空间的排序），因而在从后向前扫描过程中，需要反复把已排序元素逐步向后挪位，为最新元素提供插入空间。  
  插入排序时间复杂度为 O(n^2)。
  ```c
  void insertion_sort(int arr[], int len) {
        int i,j,key;
        for (i=1;i<len;i++) {
                key = arr[i];
                j=i-1;
                while((j>=0) && (arr[j]>key)) {
                        arr[j+1] = arr[j];
                        j--;
                }
                arr[j+1] = key;
        }
  }
  ```

- 冒泡排序  
  冒泡排序（Bubble Sort）是一种简单的排序算法。它重复地走访过要排序的数列，一次比较两个元素，如果他们的顺序错误就把他们交换过来。走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。这个算法的名字由来是因为越小的元素会经由交换慢慢“浮”到数列的顶端。  
  冒泡排序时间复杂度为 O(n^2)。
  ```c
  void bubbleSort (int arr[], int len) {
    int i, j,temp;
	  Boolean exchanged = true;
    for (i=0; exchanged && i<len-1; i++)
      for (j=0; j<len-1-i; j++) {
        exchanged = false;
        if (arr[j] > arr[j+1]) {
          temp = arr[j];
          arr[j] = arr[j+1];
          arr[j+1] = temp;
          exchanged = true;
        }
      }
  }
  ```

- 选择排序  
  选择排序（Selection sort）是一种简单直观的排序算法。它的工作原理如下：首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。  
  选择排序时间复杂度为 O(n^2)。  
  ```c
  void selection_sort(int a[], int len) {
    int i,j,temp;
    for (i = 0 ; i < len - 1 ; i++) {
		  int min = i;
		  for (j = i + 1; j < len; j++) {
			  if (a[j] < a[min]) {
				  min = j; 
			  }
		  }
		  if(min != i) {
        temp=a[min];
        a[min]=a[i];
        a[i]=temp;
		  }
  	}
  }
  ```

- 快速排序  
  快速排序（Quicksort）使用分治法（Divide and conquer）策略来把一个序列（list）分为较小和较大的2个子序列，然后递归地排序两个子序列。  
  步骤为：
  1. 挑选基准值：从数列中挑出一个元素，称为“基准”（pivot）。
  2. 分割：重新排序数列，所有比基准值小的元素摆放在基准前面，所有比基准值大的元素摆在基准后面（与基准值相等的数可以到任何一边）。在这个分割结束之后，对基准值的排序就已经完成。
  3. 递归排序子序列：递归地将小于基准值元素的子序列和大于基准值元素的子序列排序。

  递归到最底部的判断条件是数列的大小是零或一，此时该数列显然已经有序。  
  快速排序的时间复杂度为O(n*logn)。
  ```c
  void swap(int *x, int *y) {
    int t = *x;
    *x = *y;
    *y = t;
  }

  void quick_sort_recursive(int arr[], int start, int end) {
    if (start >= end)
      return;
    int mid = arr[end];
    int left = start, right = end - 1;
    while (left < right) {
      while (arr[left] < mid && left < right)
          left++;
      while (arr[right] >= mid && left < right)
          right--;
      swap(&arr[left], &arr[right]);
    }
    if (arr[left] >= arr[end])
      swap(&arr[left], &arr[end]);
    else{
      left++;
      swap(&arr[left], &arr[end]);
    }
    if (left)
      quick_sort_recursive(arr, start, left - 1);
    quick_sort_recursive(arr, left + 1, end);
  }

  void quick_sort(int arr[], int len) {
    quick_sort_recursive(arr, 0, len - 1);
  }
  ```

- 堆排序  
  堆排序（Heapsort）是指利用堆这种数据结构所设计的一种排序算法。堆是一个近似完全二叉树的结构，并同时满足堆积的性质：即子节点的键值或索引总是小于（或者大于）它的父节点。  
  堆排序的时间复杂度为O(n*logn)。
  ```c
  #include <stdio.h>
  #include <stdlib.h>

  void swap(int *a, int *b) {
    int temp = *b;
    *b = *a;
    *a = temp;
  }

  void max_heapify(int arr[], int start, int end) {
    // 建立父节点下标和子节点下标
    int dad = start;
    int son = dad * 2 + 1;
    while (son <= end) { // 若子节点在范围内才做比较
      if (son + 1 <= end && arr[son] < arr[son + 1]) // 先比较两个子节点大小，选择最大的
        son++;
      if (arr[dad] > arr[son]) //如果父节点大于子节点代表调整完毕，直接跳出函数
        return;
      else { // 否则交换父子内容再继续子节点和孙子节点比较
        swap(&arr[dad], &arr[son]);
        dad = son;
        son = dad * 2 + 1;
      }
    }
  }

  void heap_sort(int arr[], int len) {
    int i;
    // 初始化，i 从最后一个父节点开始调整
    for (i = len / 2 - 1; i >= 0; i--)
      max_heapify(arr, i, len - 1);
    // 先将第一个元素和已排好元素前一位做交换，再重新调整，直到排序完毕
    for (i = len - 1; i > 0; i--) {
      swap(&arr[0], &arr[i]);
      max_heapify(arr, 0, i - 1);
    }
  }

  int main() {
    int arr[] = { 3, 5, 3, 0, 8, 6, 1, 5, 8, 6, 2, 4, 9, 4, 7, 0, 1, 8, 9, 7, 3, 1, 2, 5, 9, 7, 4, 0, 2, 6 };
    int len = (int) sizeof(arr) / sizeof(*arr);
    heap_sort(arr, len);
    int i;
    for (i = 0; i < len; i++)
      printf("%d ", arr[i]);
    printf("\n");
    return 0;
  }
  ```

- 希尔排序  
  希尔排序（Shellsort）通过将比较的全部元素分为几个区域来提升插入排序的性能。这样可以让一个元素可以一次性地朝最终位置前进一大步。然后算法再取越来越小的步长进行排序，算法的最后一步就是普通的插入排序，但是到了这步，需排序的数据几乎是已排好的了（此时插入排序较快）。  
  希尔排序的时间复杂度为O(n*(logn)^2)。 
  ```c
  void shell_sort(int arr[], int len) {
    int gap, i, j;
    int temp;
    for (gap = len >> 1; gap > 0; gap >>= 1)
      for (i = gap; i < len; i++) {
        temp = arr[i];
        for (j = i - gap; j >= 0 && arr[j] > temp; j -= gap)
          arr[j + gap] = arr[j];
        arr[j + gap] = temp;
     }
  }
  ```

- 归并排序  
  归并排序（英语：Merge sort，或mergesort），是创建在归并操作上的一种有效的排序算法。  
  采用分治法:
  - 分割：递归地把当前序列平均分割成两半。
  - 集成：在保持元素顺序的同时将上一步得到的子序列集成到一起（归并）。

  归并排序的时间复杂度为O(n*logn)。  
  ```c
  int min(int x, int y) {
    return x < y ? x : y;
  }
  void merge_sort(int arr[], int len) {
    int *a = arr;
    int *b = (int *) malloc(len * sizeof(int));
    int seg, start;
    for (seg = 1; seg < len; seg += seg) {
      for (start = 0; start < len; start += seg * 2) {
        int low = start, mid = min(start + seg, len), high = min(start + seg * 2, len);
        int k = low;
        int start1 = low, end1 = mid;
        int start2 = mid, end2 = high;
        while (start1 < end1 && start2 < end2)
            b[k++] = a[start1] < a[start2] ? a[start1++] : a[start2++];
        while (start1 < end1)
            b[k++] = a[start1++];
        while (start2 < end2)
            b[k++] = a[start2++];
        }
        int *temp = a;
        a = b;
        b = temp;
    }
    if (a != arr) {
      int i;
      for (i = 0; i < len; i++)
        b[i] = a[i];
      b = a;
    }
    free(b);
  }
  ```

- 基数排序  
  基数排序（Radix sort）是一种非比较型整数排序算法，其原理是将整数按位数切割成不同的数字，然后按每个位数分别比较。由于整数也可以表达字符串（比如名字或日期）和特定格式的浮点数，所以基数排序也不是只能使用于整数。  
  基数排序的时间复杂度是 O(k*n)，其中 n 是排序元素个数，k 是数字位数。
  ```c
  #include<stdio.h>
  #define MAX 20
  //#define SHOWPASS
  #define BASE 10

  void print(int *a, int n) {
    int i;
    for (i = 0; i < n; i++) {
      printf("%d\t", a[i]);
    }
  }

  void radixsort(int *a, int n) {
    int i, b[MAX], m = a[0], exp = 1;

    for (i = 1; i < n; i++) {
      if (a[i] > m) {
        m = a[i];
      }
    }

    while (m / exp > 0) {
      int bucket[BASE] = { 0 };

      for (i = 0; i < n; i++) {
        bucket[(a[i] / exp) % BASE]++;
      }

      for (i = 1; i < BASE; i++) {
        bucket[i] += bucket[i - 1];
      }

      for (i = n - 1; i >= 0; i--) {
        b[--bucket[(a[i] / exp) % BASE]] = a[i];
      }

      for (i = 0; i < n; i++) {
        a[i] = b[i];
      }

      exp *= BASE;

  #ifdef SHOWPASS
      printf("\nPASS   : ");
      print(a, n);
  #endif
    }
  }

  int main() {
    int arr[MAX];
    int i, n;

    printf("Enter total elements (n <= %d) : ", MAX);
    scanf("%d", &n);
    n = n < MAX ? n : MAX;

    printf("Enter %d Elements : ", n);
    for (i = 0; i < n; i++) {
      scanf("%d", &arr[i]);
    }

    printf("\nARRAY  : ");
    print(&arr[0], n);

    radixsort(&arr[0], n);

    printf("\nSORTED : ");
    print(&arr[0], n);
    printf("\n");

    return 0;
  }
  ```