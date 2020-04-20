package week1.day04;

public class Solution4 {
    public static void main(String[] args) {
        for (int i = 1; i <= 99; i++) {
            if (i < 10) {
                if (Math.pow(i, 2) % 10 == i) {
                    System.out.println(i + " ");
                }
            } else {
                if (Math.pow(i, 2) % 100 == i) {
                    System.out.println(i + " ");
                }
            }
        }
    }
}
