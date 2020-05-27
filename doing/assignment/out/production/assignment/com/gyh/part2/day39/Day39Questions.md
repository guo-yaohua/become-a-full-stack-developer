# Day12_Map

1. 讲今天讲的内容总结，如果有不懂的地方，一定要把我上课的demo敲一遍。

2. 求字符串中每一个字母出现的次数，并字母从小到大的顺序排列。
输入："aababcabcdabcde"
输出："a(5)b(4)c(3)d(2)e(1)"

3. 给定一棵二叉搜索树，它的根结点为root。求这棵树中不同结点的最小差值。
    ```
    public class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode(int x) { val = x; }
    }
    ```
    比如：
    ```
        4
       / \
      2   6
     / \    
    1   3  
    ```
    这棵树中，最小差值为1. (2结点和1结点，3结点和2结点)