# Day14_RedBlackTree01

1. 讲今天讲的内容总结，如果有不懂的地方，一定要把我上课的demo敲一遍。

2. 给定一棵二叉树，判断它是不是镜像对称的。  
    比如：
    ```
       1
      / \
     2   2
    / \ / \
    3  4 4  3
    ```
    这是镜像对称的
    ```
       1
      / \
     2   2
      \   \
       3   3
    ```
    这不是镜像对称的
    ```
    public class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode(int x) { val = x; }
    }
    ```
   
3. 试着自己实现红黑树的delete(key), ceiling(key), floor(key), rank(key), select(k)方法 (选做)。