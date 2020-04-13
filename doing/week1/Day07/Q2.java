import java.util.Scanner;

public class Q2 {
    public static void main(String[] args) {
        System.out.print("输入楼梯层数 n：");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();

        System.out.printf("共有 %d 种走法\n", stair(n));
    }

    public static int stair(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        
        return stair(n - 1) + stair(n - 2);
    }
}