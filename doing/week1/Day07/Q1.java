import java.util.Scanner;

public class Q1 {
    public static void main(String[] args) {
        int m, n, p;
        System.out.print("矩阵的大小（m * n 和 n * p 里的 m，n，p分别是）：");
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        p = sc.nextInt();

        int[][] matrix1 = new int[m][n];
        int[][] matrix2 = new int[n][p];
        int[][] matrixRes = new int[m][p];

        System.out.printf("输入 %d * %d 矩阵：\n", m, n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix1[i][j] = sc.nextInt();
            }
        }
        
        System.out.printf("输入 %d * %d 矩阵：\n", n, p);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < p; j++) {
                matrix2[i][j] = sc.nextInt();
            }
        }

        // 计算
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < p; j++) {
                for (int k = 0; k < n; k++) {
                    matrixRes[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }

        // 输出
        System.out.println("乘积：");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < p; j++) {
                System.out.printf("%2d ",matrixRes[i][j]);
            }
            System.out.println();
        }

        sc.close();
    }

    /* 
    不知道为啥这样会报错
    public static void input(int[][] arr, int x, int y) {
        System.out.printf("输入 %d * %d 矩阵：\n", x, y);
        Scanner sc2 = new Scanner(System.in);

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                arr[i][j] = sc2.nextInt();
            }
        }

        sc2.close();
    }*/
}