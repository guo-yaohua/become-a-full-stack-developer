# Day03 Javabasic02

作业：  
1. 有三个int变量，a, b, c假设三个变量中有两个变量的值相同，请问如何快速求出，那个和其他两个变量不同的第三个变量的值？(代码+截图)

2. 任给一个int类型的正整数，如何判断该整数的值，是否是2的整数次幂？(代码+截图)

3. 用位运算符求一个整数(int)的绝对值(要求有代码和截图哈)

答：  
1.  
代码：  
```java
/*
我的代码：
import java.util.Scanner;

public class Q1 {
    public static void main(String[] args) {
        System.out.print("输入三个数，其中两个数相等：");

        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        sc.close();

        System.out.print("与其它两个变量不同的值是：");
        System.out.println((a == b && a != c) ? c : ((a == c && a != b) ? b : a));
    }
}
答案：
*/
int a = 2, b = 2, c = -100;
int result = a ^ b ^ c; // 利用异或的性质
```

2.   
代码：  
```java
/*
我的代码：
import java.util.Scanner;

public class Q2 {
    public static void main(String[] args) {
        System.out.print("输入一个数字：");

        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        sc.close();

        boolean isPow = true;
        if (num == 1) {
            isPow = false;
        }

        while (num > 1) {
            if(num % 2 == 1) {
                isPow = false;
                break;
            }
            num = num / 2;
        }

        if (isPow) {
            System.out.println("该数是 2 的整数次幂");
        } else {
            System.out.println("该数不是 2 的整数次幂");
        }
    }
}

答案：
*/
int n = 16;
int tmp = n & (n - 1);
boolean result = temp == 0;
System.out.println(result);
```

3.   
代码：  
```java
/*
没做出来

答案：
*/
int abs = (intValue ^ (intValue >> 31)) - (intValue >> 31);
```