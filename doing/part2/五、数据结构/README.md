# 五、数据结构

## 目录

## Java数据结构

数据结构是相互之间存在一种或多种特定关系的数据元素的集合。  

公式：数据结构 = 数据 + 结构  
- 数据：用类型表示。

- 结构：在任何问题中，数据元素都不是孤立存在的，它们之间都存在着某种关系，这种数据元素相互之间的关系称为结构。

元素之间，通常有以下四种基本结构：
- 集合。

- 线性结构。

- 树形结构。

- 图或网状结构。

前面分类中定义的关系，描述的是数据元素间的逻辑关系，因此又称为逻辑结构。  

但是仅仅知道数据元素间的逻辑关系是不够的，因为我们得实现自己的数据结构。因此，我们得关注数据结构在计算机底层是如何表示。

数据结构在计算机中的表示，称为数据的物理结构，又称为存储结构或者映像。

数据的表示很简单，可以分为两种：顺序存储结构（顺序映像）和链式存储结构（非顺序映像）。
- 顺序映像：借助元素在存储器中的相对位置来表示数据元素之间的逻辑关系。（数组）

- 非顺序映像：借助指示元素存储地址的”指针”，来表示数据元素的逻辑关系。（链表）

## 线性表

### 逻辑结构

线性表：n 个数据元素的有序序列。
- 线性表中元素的个数是有限的。

- 线性表中元素是有序的。

「有序」：
- 指如果以 ai 表示数据元素，则线性表可以记为 {a1, … , ai-1, ai, ai+1, … , an}。  
  表中, ai-1 在 ai 之前，同时 ai+1 在 ai 之后，我们称 ai-1 是 ai 的直接前驱，ai+1 是 ai 的直接后继。

- 除表头和表尾元素外，其它元素都有唯一前驱和唯一后继，其唯一前驱或唯一后继确定了该元素在线性表中的位置。

- 表头元素有唯一后继，无前驱，表尾元素有唯一前驱，无后继。

- 因此，线性表中每个数据元素都有一个确定的位序，这个确定的位序我们称之为索引。

线性表的实现有两种：
- 顺序映像（ArrayList）；

- 非顺序映像（LinkedList）。

### 顺序印象实现

结构：
```
.
|- exception
|  |- ArrayOverflowException.java
|
|- list
|  |- MyArrayList.java
|  |
|  |- MyIterator.java
|  |
|  |- MyList.java
```

ArrayOverflowException 异常：
```java
public class ArrayOverflowException extends RuntimeException {
    public ArrayOverflowException() {
    }

    public ArrayOverflowException(String message) {
        super(message);
    }
}
```

MyList：
```java
public interface MyList<E> extends Iterable<E> {
    boolean add(E e);

    void add(int index, E e);

    void clear();

    boolean contains(Object obj);

    E get(int index);

    int indexOf(Object obj);

    boolean isEmpty();

    MyIterator iterator();

    MyIterator iterator(int index);

    int lastIndexOf(Object obj);

    E remove(int index);

    boolean remove(Object obj);

    E set(int index, E e);

    int size();
}
```

MyInterator：
```java
import java.util.Iterator;

public interface MyIterator<E> extends Iterator<E> {
    boolean hasNext();

    boolean hasPrevious();

    E next();

    E previous();

    int nextIndex();

    int previousIndex();

    void add(E e);

    void remove();

    void set(E e);
}
```

MyArrayList：  
```java
import ArrayOverflowException;

import java.util.*;

public class MyArrayList<E> implements MyList<E> {
    private final static int DEFAULT_CAPACITY = 10;
    private final static int MAX_CAPACITY = Integer.MAX_VALUE - 8;

    // 属性
    private Object[] elements;
    private int size;
    private int modCount; // 统计集合结果被修改的次数，主要是用来处理并发修改异常的

    // 构造方法
    public MyArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity <= 0 || initialCapacity > MAX_CAPACITY) {
            throw new IllegalArgumentException("initialCapacity=" + initialCapacity);
        }
        elements = new Object[initialCapacity];
    }

    // 方法

    /**
     * 在线性表最后添加元素
     *
     * @param e 待添加的元素
     * @return 如果添加成功返回true
     */
    @Override
    public boolean add(E e) {
        add(size, e);
        return true;
    }

    /**
     * 在线性表中指定的索引位置添加元素
     *
     * @param index 索引
     * @param e     待添加的元素
     */
    @Override
    public void add(int index, E e) {
        checkIndexForAdd(index);
        // 判断是否需要扩容
        if (size == elements.length) {
            // 计算newCapacity
            int minCapacity = size + 1;
            int newCapacity = calculateCapacity(minCapacity);
            // 扩容
            grow(newCapacity);
        }
        // 添加元素
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = e;
        size++;
        modCount++;
    }

    private void grow(int newCapacity) {
        Object[] newArr = new Object[newCapacity];
        // 复制元素
        for (int i = 0; i < size; i++) {
            newArr[i] = elements[i];
        }
        // elements指向新数组
        elements = newArr; // Caution!
    }

    private int calculateCapacity(int minCapacity) {
        if (minCapacity > MAX_CAPACITY || minCapacity < 0) {
            throw new ArrayOverflowException();
        }
        // 一定能够容纳下minCapacity个元素
        int newCapacity = elements.length + (elements.length >> 1);
        if (newCapacity > MAX_CAPACITY || newCapacity < 0) {
            newCapacity = MAX_CAPACITY;
        }
        // 返回minCapacity和newCapacity的最大值
        return Math.max(minCapacity, newCapacity);
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index=" + index + ", size=" + size);
        }
    }

    /**
     * 清空所有元素
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
        modCount++;
    }

    /**
     * 判断集合中是否存在与指定对象相等的元素
     *
     * @param obj 指定对象
     * @return 如果集合中有与指定对象相等的元素, 返回true, 否则返回false
     */
    @Override
    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    /**
     * 获取指定索引位置的元素
     *
     * @param index 索引
     * @return 指定索引位置的元素
     */
    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkIndex(index);
        return (E) elements[index];
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index=" + index + ", size=" + size);
        }
    }

    /**
     * 获取集合中第一个与指定对象相等元素的索引
     *
     * @param obj 指定对象
     * @return 第一个与指定对象相等元素的索引, 如果不存在这样的元素, 返回-1
     */
    @Override
    public int indexOf(Object obj) {
        if (obj == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (obj.equals(elements[i])) return i;
            }
        }
        return -1;
    }

    /**
     * 判断集合是否为空集合
     *
     * @return 如果集合是空集合返回true, 否则返回false
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * 获取集合中最后一个与指定对象相等元素的索引
     *
     * @param obj 指定对象
     * @return 最后一个与指定对象相等元素的索引, 如果不存在这样的元素, 返回-1
     */
    @Override
    public int lastIndexOf(Object obj) {
        if (obj == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (elements[i] == null) return i;
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (obj.equals(elements[i])) return i;
            }
        }
        return -1;
    }

    /**
     * 删除指定索引位置的元素
     *
     * @param index 索引
     * @return 被删除的元素
     */
    @Override
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        checkIndex(index);
        E removeValue = (E) elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        // 将最后一个元素置为null, 避免内存泄漏
        elements[size - 1] = null;
        size--;
        modCount++;
        return removeValue;
    }

    /**
     * 删除第一个与指定对象相等的元素
     *
     * @param obj 指定对象
     * @return 如果删除成功返回true, 否则返回false
     */
    @Override
    public boolean remove(Object obj) {
        int index = indexOf(obj);
        if (index == -1) return false;
        remove(index);
        return true;
    }

    /**
     * 替换指定索引位置的元素
     *
     * @param index 索引
     * @param e     新值
     * @return 该索引位置原来的值
     */
    @Override
    @SuppressWarnings("unchecked")
    public E set(int index, E e) {
        checkIndex(index);
        E oldValue = (E) elements[index];
        elements[index] = e;
        // 我们只是替换元素的值，集合的结构没有发生改变, 不需要modCount++
        return oldValue;
    }

    /**
     * 获取线性表中元素的个数
     *
     * @return 元素的个数
     */
    @Override
    public int size() {
        return size;
    }


    /**
     * 获取迭代器, 默认下一个元素的索引为0
     *
     * @return 迭代器
     */
    @Override
    public MyIterator<E> iterator() {
        return new Itr();
    }

    /**
     * 获取迭代器, 默认下一个元素的索引为index
     *
     * @param index 下一个元素的索引
     * @return 迭代器
     */
    @Override
    public MyIterator<E> iterator(int index) {
        checkIndexForAdd(index);
        return new Itr(index);
    }

    private class Itr implements MyIterator<E> {
        // 属性
        int cursor; // 光标后面元素的索引
        int expModCount = modCount;
        int lastRet = -1; //最近返回元素的索引, -1表示最近没有返回元素,或者是最近返回的元素失效了。

        // 构造方法
        Itr() {
        }

        Itr(int index) {
            cursor = index;
        }

        /**
         * 判断光标后面是否还有元素
         *
         * @return 如果光标后面还有元素返回true, 否则返回false
         */
        @Override
        public boolean hasNext() {
            // return cursor < size;
            return cursor != size;
        }

        /**
         * 判断光标前面是否还有元素
         *
         * @return 如果光标前面还有元素返回true, 否则返回false
         */
        @Override
        public boolean hasPrevious() {
            // return cursor > 0;
            return cursor != 0;
        }

        /**
         * 将光标往后移动一个位置，并把被光标越过的元素返回
         *
         * @return 被光标越过的元素
         */
        @Override
        @SuppressWarnings("unchecked")
        public E next() {
            // 判断迭代器是否有效
            checkConModException();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            // 移动光标
            /*E retValue = (E)elements[cursor];
            lastRet = cursor;
            cursor++;
            return retValue;*/

            lastRet = cursor;
            return (E) elements[cursor++];
        }

        private void checkConModException() {
            if (expModCount != modCount) {
                throw new ConcurrentModificationException();
            }
        }

        /**
         * 将光标往前移动一个位置，并把被光标越过的元素返回
         *
         * @return 被光标越过的元素
         */
        @Override
        @SuppressWarnings("unchecked")
        public E previous() {
            // 判断迭代器是否有效
            checkConModException();
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            // 移动光标
            /*E retValue = (E) elements[cursor - 1];
            lastRet = cursor - 1;
            cursor--;
            return retValue;*/
            lastRet = --cursor;
            return (E) elements[cursor];
        }

        /**
         * 光标后面元素的索引
         *
         * @return 光标后面元素的索引
         */
        @Override
        public int nextIndex() {
            return cursor;
        }

        /**
         * 光标前面元素的索引
         *
         * @return 光标前面元素的索引
         */
        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        /**
         * 在光标后面添加元素
         * @param e 待添加的元素
         */
        @Override
        public void add(E e) {
            // 判断迭代器是否有效
            checkConModException();
            MyArrayList.this.add(cursor, e);
            expModCount = modCount;
            lastRet = -1;
            cursor++;
        }

        /**
         * 删除最近返回的元素
         */
        @Override
        public void remove() {
            checkConModException();
            if (lastRet == -1) {
                throw new IllegalStateException();
            }
            // 删除最近返回的元素
            MyArrayList.this.remove(lastRet);
            // 更改迭代器的属性
            expModCount = modCount;
            cursor = lastRet; // Caution!
            lastRet = -1;
        }

        /**
         * 替换最近返回的元素
         * @param e 新值
         */
        @Override
        public void set(E e) {
            checkConModException();
            if (lastRet == -1) {
                throw new IllegalStateException();
            }
            elements[lastRet] = e;
            lastRet = -1;
        }
    }

    @Override
    public String toString() {
        //[a, b, c]
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            // if (i != 0) sb.append(", ");
            sb.append(elements[i]).append(", ");
            // if (i != size - 1) sb.append(", ");
        }
        if (size != 0) sb.delete(sb.length() - 2, sb.length());
        return sb.append("]").toString();
    }

    public static void main(String[] args) {
        /*MyArrayList<String> list = new MyArrayList<>();
        // System.out.println(list);
        list.add("hello");
        list.add("world");
        list.add("java");*/
        /*System.out.println(list);
        System.out.println(list.size());*/

        // 自动扩容
        /*for (int i = 0; i < 30; i++) {
            list.add("" + i);
        }
        System.out.println(list);
        System.out.println(list.size());*/

        // void add(int index, E e)
        // list.add(0, null);
        // list.add(1, null);
        // list.add(list.size(), null);
        // list.add(-1, null);
        // list.add(list.size() + 1, null);
        // System.out.println(list);

        // void clear(), boolean isEmpty(), int size()
        /*System.out.println(list);
        System.out.println(list.size());
        System.out.println(list.isEmpty());
        list.clear();
        System.out.println(list);
        System.out.println(list.size());
        System.out.println(list.isEmpty());*/

        // boolean contains(Object obj)
        /*System.out.println(list.contains("hello"));
        System.out.println(list.contains("helloKitty"));
        System.out.println(list.contains(null));
        list.add(null);
        System.out.println(list.contains(null));*/

        // int indexOf(Object obj)
        /*list.add("hello");
        System.out.println(list.indexOf("hello")); // 0
        System.out.println(list.indexOf(null));  // -1
        list.add(null);
        list.add(1, null);
        System.out.println(list.indexOf(null)); // 1*/

        // int lastIndexOf(Object obj)
        /*list.add("hello");
        System.out.println(list.lastIndexOf("hello")); // 3
        System.out.println(list.lastIndexOf(null)); // -1
        list.add(null);
        list.add(1, null);
        System.out.println(list.lastIndexOf(null)); // 5*/

        // E get(int index)
        /*String s = list.get(1);
        System.out.println(s);*/
        // System.out.println(list.get(-1));
        // System.out.println(list.get(list.size()));

        // E remove(int index)
        // System.out.println(list.remove(1));
        // System.out.println(list.remove(-1));
        // System.out.println(list.remove(list.size()));
        // System.out.println(list);

        // boolean remove(Object obj)
        // list.add("hello");
        // System.out.println(list.remove("hello"));
        // System.out.println(list.remove(null));
        /*list.add(null);
        list.add(0, null);
        System.out.println(list);
        System.out.println(list.remove(null));
        System.out.println(list);*/

        // E set(int index, E e)
        // System.out.println(list.set(1, "WORLD"));
        // System.out.println(list.set(-1, "WORLD"));
        /*System.out.println(list.set(list.size(), "WORLD"));
        System.out.println(list);*/

        /*****************************************************
         *                   Iterator
         *****************************************************/
        /*MyArrayList<String> list = new MyArrayList<>();
        list.add("hello");
        list.add("world");
        list.add("java");*/

        // boolean hasNext(), boolean hasPrevious()
        // MyIterator<String> it = list.iterator();
        // MyIterator<String> it = list.iterator(list.size());
        // MyIterator<String> it = list.iterator(1);
        // System.out.println(it.hasPrevious());
        // System.out.println(it.hasNext());

        // E next()
        /*MyIterator<String> it = list.iterator();
        System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(it.next());*/
        // System.out.println(it.next()); NoSuchElementException

        // E previous()
        /*MyIterator<String> it = list.iterator(list.size());
        System.out.println(it.previous());
        System.out.println(it.previous());
        System.out.println(it.previous());*/
        // System.out.println(it.previous()); NoSuchElementException

        // int nextIndex(), int previousIndex()
        /*MyIterator<String> it = list.iterator();
        System.out.println(it.previousIndex()); //-1
        System.out.println(it.nextIndex()); // 0
        it.next();
        System.out.println(it.previousIndex()); // 0
        System.out.println(it.nextIndex());*/ // 1

        /*MyIterator<String> it = list.iterator(list.size());
        System.out.println(it.previousIndex()); // 2
        System.out.println(it.nextIndex()); // 3
        it.previous();
        System.out.println(it.previousIndex()); //1
        System.out.println(it.nextIndex()); // 2*/

        // void add(E e)
        /*MyIterator<String> it = list.iterator();
        it.next();
        it.add("wuhan");
        it.add("beijing");
        System.out.println(list);*/

        /*for(MyIterator<String> it = list.iterator(); it.hasNext(); ) {
            String s = it.next();
            if ("hello".equals(s)) {
                int index = it.nextIndex();
                list.add(index, "wuhan");
            }
        }
        System.out.println(list);*/

        // void remove()
        /*MyIterator<String> it = list.iterator();
        // it.remove(); // IllegalStateException
        it.next();
        it.remove();
        System.out.println(list);
        // it.remove(); IllegalStateException*/

        /*MyIterator<String> it = list.iterator(list.size());
        // it.remove();
        it.previous();
        it.remove();
        System.out.println(list);
        // it.remove();*/

        // ConcurrentModificationException
        /*for(MyIterator<String> it = list.iterator(); it.hasNext(); ) {
            String s = it.next();
            if ("hello".equals(s)) {
                int index = it.previousIndex();
                list.remove(index);
            }
        }
        System.out.println(list);*/


        // void set(E e)
        /*MyIterator<String> it = list.iterator();
        // it.set("HELLO");
        it.next();
        it.set("HELLO");
        System.out.println(list);
        // it.set("hello");
        System.out.println(it.nextIndex());*/

        /*for(MyIterator<String> it = list.iterator(); it.hasNext(); ) {
            String s = it.next();
            if ("hello".equals(s)) {
                int index = it.previousIndex();
                list.set(index, "HELLO");
            }
        }
        System.out.println(list);*/

        /*for(MyIterator<String> it = list.iterator(); it.hasNext(); ) {
            String s = it.next();
            if ("java".equals(s)) {
                int index = it.previousIndex();
                list.remove(index);
            }
        }
        System.out.println(list);*/

        ArrayList<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");
        list.add("java");

        for(ListIterator<String> it = list.listIterator(); it.hasNext(); ) {
            String s = it.next();
            if ("java".equals(s)) {
                int index = it.previousIndex();
                list.remove(index);
            }
        }
        System.out.println(list);
    }
}
```

### 非顺序印象实现

结构：
```
.
|- list
|  |- MyArrayList.java
|  |
|  |- MyIterator.java
|  |
|  |- MyList.java
```

MyList：
```java
public interface MyList<E> extends Iterable<E> {
    boolean add(E e);

    void add(int index, E e);

    void clear();

    boolean contains(Object obj);

    E get(int index);

    int indexOf(Object obj);

    boolean isEmpty();

    MyIterator iterator();

    MyIterator iterator(int index);

    int lastIndexOf(Object obj);

    E remove(int index);

    boolean remove(Object obj);

    E set(int index, E e);

    int size();
}
```

MyInterator：
```java
import java.util.Iterator;

public interface MyIterator<E> extends Iterator<E> {
    boolean hasNext();

    boolean hasPrevious();

    E next();

    E previous();

    int nextIndex();

    int previousIndex();

    void add(E e);

    void remove();

    void set(E e);
}
```

MyLinkedList
```java
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements MyList<E> {
    //属性
    private Node head;
    private Node tail;
    private int size;
    private int modCount;

    private static class Node {
        Node prev;
        Object value;
        Node next;

        public Node(Object value) {
            this.value = value;
        }

        public Node(Node prev, Object value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    //构造方法
    public MyLinkedList() {
        head = new Node(null);
        tail = new Node(head, null, null);
        head.next = tail;
    }

    /**
     * 在线性表最后添加元素
     *
     * @param e 待添加的元素
     * @return 如果添加成功，返回true
     */
    @Override
    public boolean add(E e) {
        add(size, e);
        return true;
    }

    /**
     * 在线性表的指定位置添加元素
     *
     * @param index 索引位置
     * @param e     待添加的元素
     */
    @Override
    public void add(int index, E e) {
        checkIndexForAdd(index);
        if (index == size) { //Caution!
            Node nodeToAdd = new Node(tail.prev, e, tail);
            tail.prev.next = nodeToAdd;
            tail.prev = nodeToAdd;
        } else {
            Node node = getNode(index);
            Node nodeToAdd = new Node(node.prev, e, node);
            node.prev.next = nodeToAdd;
            node.prev = nodeToAdd;
        }
        size++;
        modCount++;
    }

    /*
    A --> B --> C   size=3
    A --> B --> C --> D size=4
     */
    private Node getNode(int index) {
        if (2 * index < size) {
            Node x = head.next;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else {
            Node x = tail.prev;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index=" + index + ", size=" + size);
        }
    }

    /**
     * 清空所有的元素
     */
    @Override
    public void clear() {
        head.next = tail;
        tail.prev = head;
        size = 0;
        modCount++;
    }

    /**
     * 判断线性表中是否有和指定对象相等的元素
     *
     * @param obj 指定对象
     * @return 如果存在这样的元素返回true, 否则返回false
     */
    @Override
    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    /**
     * 获取指定索引位置的元素
     *
     * @param index 索引
     * @return 指定索引位置的元素
     */
    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkIndex(index);
        return (E) getNode(index).value;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index=" + index + ", size=" + size);
        }
    }

    /**
     * 获取线性表中第一个和指定对象相等元素的索引
     *
     * @param obj 指定对象
     * @return 第一个和指定对象相等元素的索引，如果不存在这样的元素返回-1
     */
    @Override
    public int indexOf(Object obj) {
        if (obj == null) {
            Node x = head.next;
            for (int i = 0; i < size; i++) {
                if (x.value == null) return i;
                x = x.next;
            }
        } else {
            Node x = head.next;
            for (int i = 0; i < size; i++) {
                if (obj.equals(x.value)) return i;
                x = x.next;
            }
        }
        return -1;
    }

    /**
     * 判断线性表是否为空
     *
     * @return 如果线性表为空返回true, 否则返回false
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 获取最后一个与指定对象相等元素的索引
     *
     * @param obj 指定对象
     * @return 最后一个与指定对象相等元素的索引，如果不存在这样的元素返回-1
     */
    @Override
    public int lastIndexOf(Object obj) {
        if (obj == null) {
            Node x = tail.prev;
            for (int i = size - 1; i >= 0; i--) {
                if (x.value == null) return i;
                x = x.prev;
            }
        } else {
            Node x = tail.prev;
            for (int i = size - 1; i >= 0; i--) {
                if (obj.equals(x.value)) return i;
                x = x.prev;
            }
        }
        return -1;
    }

    /**
     * 删除指定索引位置的元素，并把被删除的元素返回
     *
     * @param index 索引
     * @return 被删除的元素
     */
    @Override
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        checkIndex(index);
        Node node = getNode(index);
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
        modCount++;
        return (E) node.value;
    }

    /**
     * 删除第一个与指定对象相等的元素
     *
     * @param obj 指定对象
     * @return 如果删除成功返回true, 否则返回false
     */
    @Override
    public boolean remove(Object obj) {
        if (obj == null) {
            Node x = head.next;
            for (int i = 0; i < size; i++) {
                if (x.value == null) {
                    // 删除x结点
                    x.prev.next = x.next;
                    x.next.prev = x.prev;
                    size--;
                    modCount++;
                    return true;
                }
                x = x.next;
            }
        } else {
            Node x = head.next;
            for (int i = 0; i < size; i++) {
                if (obj.equals(x.value)) {
                    // 删除x结点
                    x.prev.next = x.next;
                    x.next.prev = x.prev;
                    size--;
                    modCount++;
                    return true;
                }
                x = x.next;
            }
        }
        return false;
    }

    /**
     * 替换指定索引位置的元素
     *
     * @param index 索引
     * @param e     新值
     * @return 旧值
     */
    @Override
    @SuppressWarnings("unchecked")
    public E set(int index, E e) {
        checkIndex(index);
        Node node = getNode(index);
        E retValue = (E) node.value;
        node.value = e;
        return retValue;
    }

    /**
     * 获取线性表中元素的个数
     *
     * @return 元素的个数
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * 获取迭代器,光标后面元素的索引为0
     *
     * @return 迭代器
     */
    @Override
    public MyIterator<E> iterator() {
        return new Itr();
    }

    /**
     * 获取迭代器,光标后面元素的索引为 index
     *
     * @param index 光标后面元素的索引
     * @return 迭代器
     */
    @Override
    public MyIterator<E> iterator(int index) {
        checkIndexForAdd(index);
        return new Itr(index);
    }

    private class Itr implements MyIterator<E> {
        // 属性
        int cursor; //光标后面元素的索引
        Node node; //光标后面的结点
        int expModCount = modCount;
        Node lastRet = null;

        // 构造方法
        Itr() {
            node = head.next;
        }

        Itr(int index) {
            cursor = index;
            if (index == size) node = tail;
            else node = getNode(index);
        }

        /**
         * 判断光标后面是否还有元素
         *
         * @return 如果光标后面有元素返回true, 否则返回false
         */
        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        /**
         * 判断光标前面是否还有元素
         *
         * @return 如果光标前面有元素返回true, 否则返回false
         */
        @Override
        public boolean hasPrevious() {
            return cursor != 0;
        }

        /**
         * 将光标往后移动一个位置, 并把被光标越过的元素返回
         *
         * @return 被光标越过的元素
         */
        @Override
        @SuppressWarnings("unchecked")
        public E next() {
            checkConModException();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E retValue = (E) node.value;
            cursor++;
            lastRet = node;
            node = node.next;
            return retValue;
        }

        private void checkConModException() {
            if (expModCount != modCount) {
                throw new ConcurrentModificationException();
            }
        }

        /**
         * 将光标往前移动一个位置，并把被光标越过的元素返回
         *
         * @return 被光标越过的元素
         */
        @Override
        @SuppressWarnings("unchecked")
        public E previous() {
            checkConModException();
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            cursor--;
            node = node.prev;
            lastRet = node;
            return (E) node.value;
        }

        /**
         * 获取光标后面元素的索引
         *
         * @return 光标后面元素的索引
         */
        @Override
        public int nextIndex() {
            return cursor;
        }

        /**
         * 获取光标前面元素的索引
         *
         * @return
         */
        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        /**
         * 在光标后面添加元素
         * @param e 待添加的元素
         */
        @Override
        public void add(E e) {
            Node nodeToAdd = new Node(node.prev, e, node);
            node.prev.next = nodeToAdd;
            node.prev = nodeToAdd;
            size++;
            cursor++;
            lastRet = null;
            expModCount = ++modCount; //Caution!
        }

        /**
         * 删除最近返回的元素
         */
        @Override
        public void remove() {
            checkConModException();
            if (lastRet == null) {
                throw new IllegalStateException();
            }
            lastRet.prev.next = lastRet.next;
            lastRet.next.prev = lastRet.prev;
            size--;
            expModCount = ++modCount;
            if (lastRet == node) node = node.next; // 逆向遍历
            else cursor--; // 正向遍历
            lastRet = null;
        }

        /**
         * 替换最近返回的元素
         * @param e 替换后的值
         */
        @Override
        public void set(E e) {
            checkConModException();
            if (lastRet == null) {
                throw new IllegalStateException();
            }
            lastRet.value = e;
            lastRet = null;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node x = head.next;
        while (x != tail) {
            sb.append(x.value).append(", ");
            x = x.next;
        }
        if (size != 0) sb.delete(sb.length() - 2, sb.length());
        return sb.append("]").toString();
    }

    public static void main(String[] args) {
        // MyLinkedList<String> list = new MyLinkedList<>();
        // System.out.println(list);
        // list.add("hello");
        // list.add("world");
        // list.add("java");
        // System.out.println(list);

        // void add(int index, E e)
        // list.add(0, "A");
        // list.add(1, "A");
        // list.add(list.size(), "A");
        // list.add(list.size() + 1, "A");
        // list.add(-1, "A");
        // System.out.println(list);

        // void clear(), int size(), boolean isEmpty()
        /*System.out.println(list);
        System.out.println(list.size());
        System.out.println(list.isEmpty());
        list.clear();
        System.out.println(list);
        System.out.println(list.size());
        System.out.println(list.isEmpty());*/

        // boolean contains(Object obj)
        /*System.out.println(list.contains("java")); //true
        System.out.println(list.contains("wuhan")); //false
        System.out.println(list.contains(null));//false
        list.add(null);
        System.out.println(list.contains(null));//true*/

        // int indexOf(Object obj)
        /*list.add("hello");
        System.out.println(list);
        System.out.println(list.indexOf("hello"));
        System.out.println(list.indexOf(null));
        list.add(0, null);
        list.add(null);
        System.out.println(list);
        System.out.println(list.indexOf(null));*/

        // int lastIndexOf(Object obj)
        /*list.add("hello");
        System.out.println(list);
        System.out.println(list.lastIndexOf("hello"));
        System.out.println(list.lastIndexOf(null));
        list.add(0, null);
        list.add(null);
        System.out.println(list);
        System.out.println(list.lastIndexOf(null));*/

        // E get(int index)
        // System.out.println(list.get(1));
        // System.out.println(list.get(-1));
        // System.out.println(list.get(list.size()));

        // E remove(index)
        // System.out.println(list.remove(1));
        // System.out.println(list.remove(-1));
        // System.out.println(list.remove(list.size()));
        // System.out.println(list);

        // boolean remove(Object)
        /*list.add("hello");
        System.out.println(list.remove("hello"));
        System.out.println(list);
        System.out.println(list.remove(null));
        System.out.println(list);
        list.add(0, null);
        list.add(null);
        System.out.println(list);
        System.out.println(list.remove(null));
        System.out.println(list);*/

        // E set(int index, E e)
        // System.out.println(list.set(2, "JAVA"));
        // System.out.println(list.set(-1, "JAVA"));
        // System.out.println(list.set(list.size(), "JAVA"));
        // System.out.println(list);

        /*****************************************************
         *                 Iterator
         *****************************************************/
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add("hello");
        list.add("world");
        list.add("java");

        // boolean hasPrevious(), boolean hasNext()
        // MyIterator<String> it = list.iterator();
        // MyIterator<String> it = list.iterator(list.size());
        // MyIterator<String> it = list.iterator(1);
        // System.out.println(it.hasPrevious());
        // System.out.println(it.hasNext());

        // E previous(), E next()
        /*MyIterator<String> it = list.iterator();
        System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(it.next());*/

        /*MyIterator<String> it = list.iterator(list.size());
        System.out.println(it.previous());
        System.out.println(it.previous());
        System.out.println(it.previous());
        System.out.println(it.previous());*/

        /*MyIterator<String> it = list.iterator();
        System.out.println(it.previousIndex());
        System.out.println(it.nextIndex());
        it.next();
        System.out.println(it.previousIndex());
        System.out.println(it.nextIndex());
        it.next();
        System.out.println(it.previousIndex());
        System.out.println(it.nextIndex());
        it.next();
        System.out.println(it.previousIndex());
        System.out.println(it.nextIndex());*/

        // void add(E e)
        /*MyIterator<String> it = list.iterator(1);
        it.add("A");
        it.add("B");
        it.add("C");
        System.out.println(list);
        System.out.println(it.next());*/

        // 在"hello"后面添加元素
        /*for (MyIterator<String> it = list.iterator(); it.hasNext(); ) {
            String s = it.next();
            if ("hello".equals(s)) {
                int index = it.nextIndex();
                list.add(index, "A");
            }
        }
        System.out.println(list);*/

        // void remove()
        /*MyIterator<String> it = list.iterator();
        // it.remove();
        it.next();
        it.remove();
        System.out.println(list);*/
        // MyIterator<String> it = list.iterator(list.size());
        // it.previous();
        // it.add("HELLO");
        // it.remove();
        // System.out.println(list);
        // it.remove();

        // 删除"hello"
        /*for(MyIterator<String> it = list.iterator(); it.hasNext(); ) {
            String s = it.next();
            if ("hello".equals(s)) {
                int index = it.previousIndex();
                list.remove(index);
            }
        }
        System.out.println(list);*/

        /*MyIterator<String> it = list.iterator();
        // it.set("HELLO");
        it.next();
        it.set("HELLO");
        // it.set("HeLLo");
        System.out.println(list);*/

        /*MyIterator<String> it = list.iterator(list.size());
        it.previous();
        it.set("JAVA");
        System.out.println(list);*/
    }
}
```

## 栈

### 栈的结构

栈是一种「操作受限」的线性表，体现在只能在一端插入删除数据，符合 FILO 的特性。

<div align="center">
<img src="./img/p1.png">
</div>

栈的 API:
- 入栈（push）：在栈顶添加元素，O（1）。

- 出栈（pop）：从栈顶删除元素，O（1）。

- 查看栈顶元素（peek）：访问栈顶元素但不弹出，O（1）。

- 判空（isEmpty）：判断栈是否为空, 方便遍历，O（1）。

实现：
- 顺序映像。

- 非顺序映像。

### 顺序印象实现

### 非顺序印象实现

MyStack：
```java
import java.util.EmptyStackException;

/*
栈的非顺序映像
API:
    void push(E e)
    E pop()
    E peek()
    boolean isEmpty()
 */
public class MyStack<E> {
    // 属性
    private Node top;

    private static class Node {
        Object value;
        Node next;

        public Node (Object value) {
            this.value = value;
        }

        public Node(Object value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    /**
     * 入栈, 在栈顶添加元素
     *
     * @param e 待添加的元素
     */
    public void push(E e) {
        top = new Node(e, top);
    }

    /**
     * 出栈, 删除栈顶元素
     *
     * @return 被删除的元素
     */
    @SuppressWarnings("unchecked")
    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        E removeValue = (E) top.value;
        top = top.next;
        return removeValue;
    }

    /**
     * 查看栈顶元素
     *
     * @return 栈顶元素
     */
    @SuppressWarnings("unchecked")
    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return (E) top.value;
    }

    /**
     * 判断栈是否为空
     *
     * @return 如果栈为空返回true, 否则返回false
     */
    public boolean isEmpty() {
        return top == null;
    }

}
```