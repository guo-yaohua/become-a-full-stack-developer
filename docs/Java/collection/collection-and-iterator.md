## Collection 接口

Collection 是层次结构中的根接口。Collection 表示一组对象，这些对象也称为元素。

Collection 常见接口 API：
- `boolean add(E e)`：将指定元素添加到此 collection 中。  
  如果此 collection 由于调用而发生更改，则返回 true。如果此 collection 不允许有重复元素，并且已经包含了指定的元素，则返回 false。

- `boolean remove(Object o)`：如果此 collection 包含一个或多个满足 `o == null ? e == null : o.equals(e)` 的元素 e，则移除这样的元素。  
  如果此 collection 包含指定的元素（或者此 collection 由于调用而发生更改），则返回 true 。 

- `void clear()`：移除此 collection 中的所有元素。  
  如果此 collection 不支持 clear 操作，此方法抛出一个异常： `UnsupportedOperationException`。


- `boolean contains(Object o)`：当且仅当此 collection 至少包含一个满足 `o == null ? e == null : o.equals(e)` 的元素 e 时，返回 true。 

- `boolean isEmpty()`：如果此 collection 不包含元素，则返回 true。

- `int size()`： 返回此 collection 中的元素数。  
  如果此 collection 包含的元素大于 Integer.MAX_VALUE，则返回 Integer.MAX_VALUE。 

- `boolean addAll(Collection c)`：将指定 collection 中的所有元素都添加到此 collection 中。

- `boolean removeAll(Collection c)`：移除此 collection 中那些也包含在指定 collection 中的所有元素。

- `boolean containsAll(Collection c)`：如果此 collection 包含指定 collection 中的所有元素，则返回 true。

- `boolean retainAll(Collection c)`：仅保留此 collection 中那些也包含在指定 collection 的元素。  
  如果此 collection 由于调用而发生更改，则返回 true 


- `Object[] toArray()`：返回包含此 collection 中所有元素的数组。  
  返回的数组将是「安全的」，因为此 collection 并不维护对返回数组的任何引用。（换句话说，即使 collection 受到数组的支持，此方法也必须分配一个新的数组）。因此，调用者可以随意修改返回的数组。 

- `Iterator iterator()`：返回在此 collection 的元素上进行迭代的迭代器。  
  关于元素返回的顺序没有任何保证（除非此 collection 是某个能提供保证顺序的类实例）。 

## Iterator 接口

Iterator 接口是对集合进行迭代的迭代器，依赖于集合对象存在。

Iterator 接口 API：
- `boolean hasNext()`：如果仍有元素可以迭代，则返回 true。

- `E next()`：返回迭代的下一个元素。

- `void remove()`：从迭代器指向的 collection 中移除迭代器返回的最后一个元素。

示例：  
```java
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class TestDemo {
    public static void main(String[] args) {
        Collection c = new ArrayList();

        c.add("Hello");
        c.add("World");

        Iterator it = c.iterator();
        while (it.hasNext()) {
            String s = (String) it.next();
            System.out.println(s);
        }
    }
}

/* 运行结果：
Hello
World
*/
```

!> 用集合的 API 修改集合的结构，所有的迭代器会失效。用迭代器的 API 修改集合的结构，其它迭代器会失效。因此用迭代器遍历集合的时候，不要使用 while 循环，可以使用 for 循环，最好使用 for each 循环。

例如，将上面的循环代码修改为：  
```java
while (it.hasNext()) {
    String s = (String) it.next();
    if ("World".equals(s)) {
        c.remove(s);
    }
}
```
就会报错：`java.util.ConcurrentModificationException`。
