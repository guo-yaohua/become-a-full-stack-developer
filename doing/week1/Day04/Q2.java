import java.util.Scanner;

public class Q2 {
    public static void main(String[] args) {
        System.out.print("输入学生成绩（0~100）：");

        Scanner sc = new Scanner(System.in);
        int score = sc.nextInt();
        sc.close();

        switch (score / 10) {
            case 10:
            case 9:
                System.out.println("优");
                break;
            case 8:
                System.out.println("良");
                break;
            case 7:
                System.out.println("中");
                break;
            case 6:
                System.out.println("及格");
                break;
            default:
                System.out.println("不及格");
        }
    }
}