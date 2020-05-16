# Day05_MyArrayList

1. 将今天讲的内容总结，如果有不懂的地方，一定要把我上课的代码敲一遍！一遍不够, 那就再来一遍~  

2. 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。  
说明:  
    初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。  
    你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。  
示例:  
输入:  
    ```
    nums1 = [1,2,3,0,0,0], m = 3
    nums2 = [2,5,6],       n = 3
    ```
输出: `[1,2,2,3,5,6]`
思路：  
我们在数组nums1中, 从后往前填充元素。
如果nums1和nums2都有元素, 那么哪个大, 哪个就填充到当前位置。直到一个数组元素都填充完毕。  
这时如果nums2数组中还有元素, 那么就把nums2中的元素复制到nums1中相应的位置。  
    ```
      public void merge(int[] nums1, int m, int[] nums2, int n) {
      }
    ```

3. 合并两个有序的链表, 合并后的链表也是有序的。  
举例：  
    ```
        输入：1 --> 2 --> 5 --> null
    2 --> 4 --> 7 --> null
        输出：1 --> 2 --> 2 --> 4 --> 5 --> 7 --> null
    
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
          }
    ```

4. 尝试自己实现MyArrayList的迭代器(选做)