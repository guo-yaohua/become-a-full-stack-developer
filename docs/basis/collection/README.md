
## 2 数组和链表

### 2.1 数组

常见的数据结构有：数组、链表、栈、队列、树、哈希表和图。

数组的本质是固定大小的连续的内存空间，并且这片连续的内存空间又被分割成等长的小空间。它最主要的特点是随机访问。
- 数组的长度是固定的。

- 数组只能存储同一种数据类型的元素。

注：在 Java 中只有一维数组的内存空间是连续，多维数组的内存空间不一定连续。

数组通过寻址公式实现随机访问。  
寻址公式：i_address = base_address + i * type_length。

数组的基本操作：
- 添加（保证元素的顺序）。  
  最好情况：O(1)；  
  最坏情况：移动 n 个元素，O(n)；   
  平均情况：移动 n/2 个元素，O(n) 。 

- 删除（保证元素的顺序）。  
  最好情况：O(1)；  
  最坏情况：移动 n-1 个元素，O(n)；  
  平均情况：移动 (n-1)/2 个元素，O(n)。  

- 查找。
  - 根据索引查找元素：O(1)。

  - 查找数组中与特定值相等的元素。  
    大小无序：O(n)；  
    大小有序：O(log2n)。  

总结：数组增删慢，查找快。

### 2.2 链表

形象地说，链表就是用一串链子将结点串联起来。

<div align="center">
<img src="./img/p2.png">
</div>

结点：包含数据域和指针域。  
数据域：数据。  
指针域：下一个结点的地址。  

链表分类：
- 单链表：  
  <div align="center">
  <img src="./img/p3.png">
  </div>

- 循环链表：  
  <div align="center">
  <img src="./img/p4.png">
  </div>

- 双向链表：  
  <div align="center">
  <img src="./img/p5.png">
  </div>

- 双向循环链表：  
  <div align="center">
  <img src="./img/p6.png">
  </div>

单链表基本操作：
- 增加（在某个结点后面添加）。

- 删除（在某个结点后面删除）。

- 查找：  
  - 根据索引查找元素。

  - 查找链表中与特定值相等的元素。  
    - 元素大小有序。  

    - 元素大小无序。  

总结：链表增删快，查找慢。

很容易验证，前面那些操作，双向链表和单链表的时间复杂度是一样的。但是双向链表有单链表没有的独特魅力 —— 它有一条指向前驱结点的链接。

双向链表基本操作：
- 增加（在某个结点前面添加元素）。

- 删除（删除该结点）。

- 查找：  
  - 查找前驱结点。  

  - 根据索引查找元素。  

  - 查找链表中与特定值相等的元素：  
    - 元素大小无序。

    - 元素大小有序 。

总结：虽然双向链表更占用内存空间，但是它在某些操作上的性能是优于单链表的。

思想：用空间换取时间。

### 2.3 空间换取时间

缓存就是一种用空间换取时间的技术。内存大小是有限的，所以缓存不能无限大。那么当缓存满的时候，再向缓存中添加数据，就需要采取一些策略。

缓存淘汰策略：
- FIFO（First In First Out）。

- LFU（Least Frequently Used）。

- LRU（Least Recently Used）。

LRU 算法中我们就用到了链表！

添加（认为尾节点是最近最少使用的数据）：
- 如果缓存中已经存在该数据：删除该结点，添加到头结点。

- 如果缓存中不存在该数据：
  - 缓存没满，添加到头结点。

  - 缓存满了，删除尾节点, 在头结点添加。


### 2.4 链表问题

#### 2.4.1 判断链表中是否有环

解决方法：
- 给一个阈值（10ms），如果在遍历链表的过程中 10ms 还没有结束，就认为有环。

- 迷雾森林。  
  `Collection visited = new ArrayList();`
  1. 遍历链表，获取每一个结点。判断结点是否在 visited 集合中存在。  
      - 存在：返回 true。
      - 不存在：将该结点添加到visited中，然后遍历下一个结点。

  2. 遍历结束后，返回 false。  

  示例：  
  ```java
  public static boolean hasCircle(Node head) {
      Collection visited = new HashSet(); // 创建 set 集合
      Node x = head;
      while (x != null) {
          if (visited.contains(x)) return true;
          visited.add(x);
          x = x.next;
      }
      // 遍历结束
      return false;
  }
  ```

- 跑道（快慢指针）。
  1. 创建了快指针和慢指针，快指针每次走两步，慢指针每次走一步
  
  2. 遍历链表，快指针每移动两步，慢指针移动一步。
      - 如果快指针到到终点：返回 false。
      - 如果快指针和慢指针相遇：返回 true。
  
  示例：  
  ```java
  public static boolean hasCircle(Node head) {
      // 创建快慢指针
      Node fast = head;
      Node slow = head;
      
      // 遍历链表
      do {
          // 快指针是否到达终点
          if (fast == null || fast.next == null) return false;
          fast = fast.next.next;
          slow = slow.next;
      } while (fast != slow);
      // fast == slow
      return true;
  }
  ```

#### 2.4.2 反转单链表

示例：
```java
public class TestDemo {
    public static Node reverse(Node head) {
        if (head.next == null) return head;
        // 如果链表不止一个结点，反转 head.next
        Node reversed = reverse(head.next);
        // 反转head结点
        head.next.next = head;
        head.next = null;
        return reversed;
    }
    public static void print(Node head) {
        Node x = head;
        while (x != null) {
            System.out.print(x.value);
            if (x.next != null) System.out.print(" --> ");
            x = x.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(3);
        head = new Node(2, head);
        head = new Node(1, head);
        print(head);
        head = reverse(head);
        print(head);
    }
}
```

### 2.5 数组 VS 链表

<div align="center">
<img src="./img/p7.png">
</div>

数组和链表的插入、删除和随机访问操作的时间复杂度刚好相反。

- 数组使用的是连续的内存空间，可以利用 CPU 的高速缓存预读数据。  
  链表的内存空间不是连续的，不能有效预读数据。当然如果数组过大，系统没有足够的连续内存空间，会抛出 OOM。

- 数组的缺点是大小固定，没法动态的调整大小。如果要存储一些对象，如果数组太大，浪费内存空间；如果数组太小，我们需要重新申请一个更大数组，并将数据拷贝过去，耗时。

- 如果业务对内存的使用非常苛刻，数组更适合。因为结点有指针域，更消耗内存。而且对链表的频繁插入和删除，会导致结点对象的频繁创建和销毁，有可能会导致频繁的 GC 活动。


## 3 List



List：有序的 Collection（也称为序列）。此接口的用户可以对列表中每个元素的插入位置进行精确地控制。用户可以根据元素的整数索引（在列表中的位置）访问元素，并搜索列表中的元素。

与 Set 不同，列表通常允许重复的元素。更确切地讲，列表通常允许满足 e1.equals(e2) 的元素对 e1 和 e2，并且如果列表本身允许 null 元素的话，通常它们允许多个 null 元素。难免有人希望通过在用户尝试插入重复元素时抛出运行时异常的方法来禁止重复的列表，但我们希望这种用法越少越好。


### 3.4 List 常见问题

#### 3.4.1 去重

输入：[a, b, c, a, a, b, c]  
输出：[a, b, c]

思路 1：
1. 新键一个 `List result = new ArrayList();`；

2. 遍历原来的 list，判断元素是否在 result 中存在：
   - 存在：不添加。
   - 不存在：添加。

3. 遍历结束后，返回 result。


思路 2：
1. 逆序遍历 list；

2. 获取元素，截取从 0 到 nextIndex 的子列表，判断元素是否在子列表中存在：
   - 存在：删除元素。
   - 不存在：继续遍历。

3. 遍历结束后，完成去重。

示例：  
```java
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class TestDemo {
    // 思路 1
    public static List disctinct1(List list) {
        List result = new ArrayList();
        for(Iterator it = list.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (!result.contains(obj)) result.add(obj);
        }
        return result;
    }

    // 思路 2
    public static void disctinct2(List list) {
        for(ListIterator it = list.listIterator(list.size()); it.hasPrevious(); ) {
            Object element = it.previous();
            List subList = list.subList(0, it.nextIndex());
            if (subList.contains(element)) it.remove();
        }
    }

    public static void main(String[] args) {
        // [a, b, c, a, a, b, c]
        List list = new ArrayList();
        list.add('a');
        list.add('b');
        list.add('c');
        list.add('a');
        list.add('a');
        list.add('b');
        list.add('c');

        System.out.println(disctinct1(list));

        disctinct2(list);
        System.out.println(list);
    }
}

/* 运行结果：
[a, b, c]
[a, b, c]
*/
```

#### 3.4.2 用 ArrayList 实现栈数据结构
```java
import java.util.ArrayList;
import java.util.EmptyStackException;


public class MyStack {
    private ArrayList list; //组合
    private String str;

    public MyStack(ArrayList list) {
        this.list = list;
    }

    public void push(Object obj) {
        list.add(obj);
    }

    public Object pop() {
        if (isEmpty()) throw new EmptyStackException();
        return list.remove(list.size() - 1);
    }

    public Object peek() {
        if (isEmpty()) throw new EmptyStackException();
        return list.get(list.size() - 1);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}
```

注：
-  如果一个类持有某个类的成员，那么就能够「拥有」这个类的所有公共方法。  
  但是我们可以对这些方法进行限制。

-  组合还可以「加强」另一个的方法。

- 可以组合多个对象。

「加强」一个方法：
- 继承。

- 组合。

设计原则：优先使用组合，而不是继承。  
如果两个类之间有「is a」的关系，可以使用继承。


