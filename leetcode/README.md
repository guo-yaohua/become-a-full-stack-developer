# LeetCode

打开 LeetCode 发现有每日一题挑战，尝试一下，提升自己。
![活动](./img/p1.png)  

## 目录
- [Day01](#Day01)

## Day01

题目：  
![d1](./img/d1.jpg)  

算法：  
没有算法全靠观察  

思路：  
观察之后发现主对角线元素按 x，y 轴对称变换，其余位置元素按对角线对称变换。  
再观察之后发现左右对称变换之后再按斜对角线（/）对称变换即可。  
写代码时发现按斜对角线（/）变换代码麻烦一些，发现先按斜对角线（\）变换再左右变换也可。

代码：
```java
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
    	for (int i = 0, temp; i < n; i++) {	// 斜对角线（\）变换
    		for (int j = 0; j < i; j++) {
        		temp = matrix[i][j];
        		matrix[i][j] = matrix[j][i];
        		matrix[j][i] = temp;
    		}
    	}
        for (int i = 0, temp; i < n; i++) {	// 左右对称变换
        	for (int j = 0; j < n / 2; j++) {
        		temp = matrix[i][j];
        		matrix[i][j] = matrix[i][n - j - 1];
        		matrix[i][n - j - 1] = temp;
        	}
        }
    }
}
```
