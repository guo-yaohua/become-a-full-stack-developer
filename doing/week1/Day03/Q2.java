/*
如果 = 1 不是；
当大于 2 时，能对 2 取模为 0 且除以 2 之后仍符合对 2 取模为 0，这样循环判断，直到除至值为 1，则是 2 的整数幂。
*/

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