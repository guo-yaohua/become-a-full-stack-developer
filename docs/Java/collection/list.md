## List 接口

```java
public interface List<E> extends Collection<E>
```

List：有序的 Collection（也称为序列）。

此接口的用户可以对列表中每个元素的插入位置进行精确地控制。用户可以根据元素的整数索引（在列表中的位置）访问元素，并搜索列表中的元素。  

与 Set 不同，列表通常允许重复的元素。更确切地讲，列表通常允许满足 `e1.equals(e2)` 的元素对 e1 和 e2，并且如果列表本身允许 null 元素的话，通常它们允许多个 null 元素。

常见 API：
- `void add(int index, E element)`：在列表的指定位置插入指定元素。

- `boolean addAll(int index, Collection c)`：将指定 collection 中的所有元素都插入到列表中的指定位置。

- `E remove(int index)`：移除列表中指定位置的元素。

- `E get(int index)`：返回列表中指定位置的元素。

- `int indexOf(Object o)`：返回此列表中第一次出现的指定元素的索引；如果此列表不包含该元素，则返回 -1。

- `lastIndexOf(Object o)`：返回此列表中最后出现的指定元素的索引；如果列表不包含此元素，则返回 -1。

- `E set(int index, E element)`：用指定元素替换列表中指定位置的元素。

- `List<E> subList(int fromIndex, int toIndex)`：返回列表中指定的 fromIndex（包括）和 toIndex（不包括）之间的部分视图。（如果 fromIndex 和 toIndex 相等，则返回的列表为空）。返回的列表由此列表支持，因此返回列表中的非结构性更改将反映在此列表中，反之亦然。返回的列表支持此列表支持的所有可选列表操作。  
  即修改子列表，原列表也会修改。示例：  
  ```java
  import java.util.ArrayList;
  import java.util.List;

  public class TestDemo {
      public static void main(String[] args) {
          List list = new ArrayList();
          list.add("hello");
          list.add("world");
          list.add("java");

          List subList = list.subList(1, 2);
          System.out.println(subList);

          subList.set(0, "WORLD");
          System.out.println(subList);
          System.out.println(list);
      }
  }

  /* 运行结果：
  [world]
  [WORLD]
  [hello, WORLD, java]
  */
  ```

- `ListIterator<E> listIterator()`：返回此列表元素的列表迭代器（按适当顺序）。

- `ListIterator<E> listIterator(int index)`：返回列表中元素的列表迭代器（按适当顺序），从列表的指定位置开始。 

## ListIterator

```java
public interface ListIterator<E> extends Iterator<E>
```

ListIterator 接口常见 API：
- `boolean hasPrevious()`：如果以逆向遍历列表，列表迭代器有多个元素，则返回 true。

- `E next()`： 返回列表中的下一个元素。

- `boolean hasPrevious()`：如果以逆向遍历列表，列表迭代器有多个元素，则返回 true。

- `E previous()`：返回列表中的前一个元素。

- `int previousIndex()`：返回对 previous 的后续调用所返回元素的索引。

- `int nextIndex()`：返回对 next 的后续调用所返回元素的索引。

- `void add(E e)`：将指定的元素插入列表。

- `void remove()`：从列表中移除由 next 或 previous 返回的最后一个元素。

- `void set(E e)`：用指定元素替换 next 或 previous 返回的最后一个元素。

## ArraysList

```java
public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable
```

特性：
- 底层数据结构是数组，增删慢，查找快。

- 不同步，线程不安全，效率高。

- 存储 null 元素。

构造方法：
- `ArrayList()`：默认初始大小为 10。

- `ArrayList(int initialCapacity)`：可以指定数组的初始大小。

- `ArrayList(Collection c)`： 构造一个包含指定 collection 的元素的列表，这些元素是按照该 collection 的迭代器返回它们的顺序排列的。

API：
- `void ensureCapacity(int minCapacity)`：如有必要，增加此 ArrayList 实例的容量，以确保它至少能够容纳最小容量参数所指定的元素数。  
  避免频繁扩容。

- `void trimToSize()`：将此 ArrayList 实例的容量调整为列表的当前大小。  
  慎用，确保元素不会在添加的情况下用。

## LinkedList

```java
public class LinkedList<E>
    extends AbstractSequentialList<E>
    implements List<E>, Deque<E>, Cloneable, java.io.Serializable
```

Deque 接口：双端队列，可以在两端插入和删除。

LinkedList 特性：
- 底层数据结构是链表，增删快，查找慢。

- 不同步, 线程不安全, 效率高。

- 允许 null 元素。

- 实现了 Deque 这个接口，可以当作栈，队列和双端队列来使用。

构造方法：
- `LinkedList()`。

- `LinkedList(Collection c)`。

API:
- `Iterator<E> descendingIterator()`：返回以逆向顺序在此双端队列的元素上进行迭代的迭代器。

- `boolean removeFirstOccurrence(Object o)`：从此列表中移除第一次出现的指定元素（从头部到尾部遍历列表时）。

- `boolean removeLastOccurrence(Object o)`：从此列表中移除最后一次出现的指定元素（从头部到尾部遍历列表时）。

在两端的操作：
- `boolean offerFirst(E e)`：在此列表的开头插入指定的元素。

- `boolean pollFirst`：获取并移除此列表的头（第一个元素）。

- `boolean peekFirst`：获取但不移除此列表的第一个元素；如果此列表为空，则返回 null。

栈的 API：
- `void push(E e)`。

- `E pop()`。

- `E peek()`。

!> Java 中提供了 Stack 类，但是我们应该优先使用 Deque，而不应该使用 Stack。因为 Stack 同步的，效率相对来说比较低。Stack 继承了 Vector，所以 Stack 拥有 Vector 中所有的方法，使用起来不安全。

## Vector

```java
public class Vector<E>
    extends AbstractList<E>
    implements List<E>, RandomAccess, Cloneable, java.io.Serializable
```

特性：
- 底层是数组，增删慢，查找快。

- 同步，线程安全，效率比较低。

- 存储 null 元素。

API：
- `void addElement(E obj)` --> `void add(E e)`。

- `void copyInto(Object[] anArray)` --> `Object[] toArray()`。

- `E elementAt(int index)` --> `E get(int index)`。

- `void insertElementAt(E obj, int index) ` --> `void add(int index, E e)`。

- `void removeAllElements()` --> `void clear()`。

- `boolean removeElement(Object obj)` --> `boolean remove(Object obj)`。

- `void removeElementAt(int index)` --> `E remove(int index)`。

- `void setElementAt(E obj, int index)` --> `E set(int index)`。

- `Enumeration<E> elements()` --> `Iterator iterator()`。

- `int capacity()`。

- `void setSize(int newSize)`。

- `E firstElement()`。

- `E lastElement()`。

- `int indexOf(Object o, int index)`。

- `int lastIndexOf(Object o, int index)`。

- `Enumeration<E> elements()`。

Enumeration --> Iterator：
- `boolean hasMoreElements()` --> `boolean hasNext()`。

- `E nextElement()` --> `E next()`。

