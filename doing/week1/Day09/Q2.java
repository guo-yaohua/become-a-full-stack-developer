public class Q2 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};

        System.out.print("遍历：");
        ArrayTool.traverse(arr);

        System.out.print("最大值：");
        System.out.println(ArrayTool.max(arr));

        System.out.print("最小值：");
        System.out.println(ArrayTool.min(arr));

        System.out.print("倒置：");
        ArrayTool.inversion(arr);
        ArrayTool.traverse(arr);

        System.out.println("元素 3 一次出现的坐标 i = " + ArrayTool.findStart(arr, 3));

        System.out.println("元素 5 最后一次出现的坐标 i = " + ArrayTool.findEnd(arr, 5));
    }
}

class ArrayTool {
    // 遍历
    public static void traverse(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 最大值
    public static int max(int[] arr) {
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        return max;
    }

    // 最小值
    public static int min(int[] arr) {
        int min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (min > arr[i]) {
                min = arr[i];
            }
        }
        return min;
    }

    // 倒置
    public static void inversion(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }

    // 查表 1
    public static int findStart(int[] arr, int num) {
        int res = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                return i;
            }
        }
        return -1;
    }

    // 查表 2
    public static int findEnd(int[] arr, int num) {
        int res = -1;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == num) {
                return i;
            }
        }
        return -1;
    }
}