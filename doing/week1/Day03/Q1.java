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