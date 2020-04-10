public class Q2 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 4, 5, 5, 5, 6, 7, 7};

        int arrLen = arr.length;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == arr[i + 1]) { // 因为有序，所以重复元素连在一起
                arrLen--;
            }
        }
        System.out.println("数组的有效长度：" + arrLen);
    }
}