/*
【程序 31 数组逆序】
题目：将一个数组逆序输出。
程序分析：用第一个与最后一个交换。
*/

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