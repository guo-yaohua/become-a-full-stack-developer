import java.util.Scanner;

public class Q3 {
	public static void main(String[] args) {
		System.out.print("输入一个数字（int 类型）：");
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();

		num = (num >> 31) ^ ((num >> 31) + num);
		System.out.println(num);
	}
}