## Object

Object 类是所有类的父类，也就是说任何一个类在定义时如果没有明确地继承一个父类，那它就是 Object 类的子类。  
- 每个类都使用 Object 作为超类。在设计代码时，如果不确定参数类型，可以使用 Object 类。因为它可以通过向上转型来接收全部类的对象。  

- 所有对象（包括数组）都有这个类的方法。

构造方法：
- `Object()`：因此所有类都会有一个默认的无参构造方法。

常见成员方法：
- `final Class getClass()`：返回此 Object 的运行时类。返回的 Class 对象是由所表示类的 static synchronized 方法锁定的对象。  
  Class 对象代表一个类，表示的就是所有类的共性：构造方法、成员变量、成员方法。

- `String toString()`：返回该对象的字符串表示。  
  通常 toString 方法会返回一个「以文本方式表示」此对象的字符串，结果应是一个简明但易于读懂的信息表达式。  
  该字符串由类名（对象是该类的一个实例）、标记符（@）和此对象哈希码的无符号十六进制表示组成。换句话说，该方法返回一个字符串，它的值等于：`getClass().getName() + '@' + Integer.toHexString(hashCode())`。

  !> 建议所有子类都重写此方法。  

- `boolean equals(Object obj)`：指示其他某个对象是否与此对象「相等」。  
  equals 方法用于检查一个对象是否等于另一个对象。在 Object 类中，这个方法将判断两个对象是否具有相同的引用。   
  格式：`对象.equals(比较对象)`。  

  equals 方法在非空对象引用上实现相等关系： 
  - 自反性：对于任何非空引用值 x，x.equals(x) 都应返回 true。 

  - 对称性：对于任何非空引用值 x 和 y，当且仅当 y.equals(x) 返回 true 时，x.equals(y) 才应返回 true。 

  - 传递性：对于任何非空引用值 x、y 和 z，如果 x.equals(y) 返回 true，并且 y.equals(z) 返回 true，那么 x.equals(z) 应返回 true。 

  - 一致性：对于任何非空引用值 x 和 y，多次调用 x.equals(y) 始终返回 true 或始终返回 false，前提是对象上 equals 比较中所用的信息没有被修改。 

  - 对于任何非空引用值 x，x.equals(null) 都应返回 false。 

- `int hashCode()`：取得对象哈希码。  
  支持此方法是为了提高哈希表（例如 java.util.Hashtable 提供的哈希表）的性能。   

  hashCode 的常规协定是： 
  - 在 Java 应用程序执行期间，在对同一对象多次调用 hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行 equals 比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。 

  - 如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。 

  - 如果根据 equals(Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是 coder 应该意识到为不相等的对象生成不同整数结果可以提高哈希表的性能。 

  !> 实际上，由 Object 类定义的 hashCode 方法确实会针对不同的对象返回不同的整数。这一般是通过将该对象的内部地址转换成一个整数来实现的，但是 Java 编程语言不需要这种实现技巧。  

- `protected Object clone()`：创建并返回此对象的一个副本。「副本」的准确含义可能依赖于对象的类。  
  对于任何对象 x，表达式：
  - `x.clone() != x` 为 true 说明 clone 创建了一个新的对象。

  - `x.clone().getClass() == x.getClass()` 为 true 说明 clone 创建的对象和原对象是同一个类的对象。

  - `x.clone().equals(x)` 为 true 说明复制对象和原对象的内容（成员变量值）也相同。  

  !> 被 clone() 方法复制的对象，所属的类必须实现一个接口 Cloneable。  

  Cloneable 接口是个空接口。
  ```java
  public interface Cloneable {}
  ```

  > 空接口也被称之为标记接口：做标记（数据类型层面的标记）。对于 clone 方法而言，Cloneable 就是一个标记，因为 clone 只会复制，实现类了 Cloneable 接口的类的对象。  
  利用 instanceof 运算符判断：`对象  instanceof  Cloneable`。

  浅拷贝（Shallow Clone）：被复制对象的所有变量都含有与原来对象相同的值，而所有对其它对象的引用仍然指向原来的对象。换而言之，浅拷贝仅仅复制所考虑的对象，而不复制它所引用的对象。  

  深拷贝（Deep Clone）：被复制对象的所有变量都含有与原来对象相同的值，除去那些引用其它对象的变量。那些引用其它对象的变量将指向被复制的新对象，而不再是原有的那些被引用的对象。换而言之，深拷贝把复制的对象所引用的对象都复制了一遍。  

- `protected void finalize()`：当垃圾回收器确定不存在对该对象的更多引用时，由对象的垃圾回收器调用此方法。  
  子类重写 finalize 方法以配置系统资源或执行其他清除。  

  finalize 的常规协定是：当 JVM 已确定尚未终止的任何线程无法再通过任何方法访问此对象时，将调用此方法，除非由于准备终止的其他某个对象或类的终结操作执行了某个操作。

  finalize 方法可以采取任何操作，其中包括再次使此对象对其他线程可用；不过，finalize 的主要目的是在不可撤消地丢弃对象之前执行清除操作。
  比如：当执行一些 IO 或者是网络通信的功能的时候，JVM 是借助操作系统的内核完成的，所以执行这些功能时，Java 程序需要占用一定的操作系统资源，当程序使用完操作系统资源的时候，即时释放资源。当使用资源的对象变成垃圾，才能安全的释放系统资源。  
  finalize 方法刚刚好就是在对象变成垃圾，并且被垃圾回收器回收的时候会调用这个方法。但是回收时机不确定。


## String

Java 没有内置的字符串类型，而是标准 Java 类库中提供了一个预定义类 String。每个用双引号括起来的字符串都是 String 类的一个实例。  

常用构造方法：
- `String()`：初始化一个新创建的 String 对象，使其表示一个空字符序列 `""`。

- `String(byte[] bytes)`：利用字节数组创建字节数组所表示的字符串。  
  字符是以数值形式存储（如：'a' -> 97），所以可以用多个字节值表示多个字符，即字符序列。

- `String(byte[] bytes, int offset, int length)`：利用字节数数组的一部分，创建字符序列，从 byte 数组的 offset 开始的 length 个字节值。

- `String(char[] value)`：利用一个字符数组创建字符串。

- `String(String original)`：初始化一个新创建的 String 对象，使其表示一个与参数相同的字符序列；换句话说，新创建的字符串是该参数字符串的副本。

  !> 字符串是常量，它的值在创建之后不能更改。

String 类的判断功能：  
- `boolean equals(Object obj)`：比较字符串内容。

- `boolean equalsIgnoreCase(String str)`：比较字符串内容，但是忽略字符串大小写。

- `boolean contains(String str)`：判断当前字符串中是否包含参数字符串。

- `boolean startsWith(String str)`：判断字符串是否以参数字符串开头。

- `boolean endsWith(String str)`：判断字符串是否以参数字符串结尾。

- `boolean isEmpty()`：判断字符串是否为空串。

String 类的的获取功能：  
- `int length()`：返回当前字符串中包含的字符个数。

- `char charAt(int index)`：获取字符串指定位置的字符（字符串中的字符，从左到右，从 0 开始编号）。

- `int indexOf(int ch)`：在当前字符串中查找参数字符，如果当前字符串中存在则返回首次出现的位置，若不存在则返回 -1。

- `int indexOf(int ch, int fromIndex)`：指定从字符串的 fromIndex 位置开始，向后查找指定字符。找到，则返回其从 formIndex 开始首次出现的位置，否则返回 -1。

- `int indexOf(String str)`：在当前字符串中，查找参数字符串首次出现的位置。

- `String substring(int start)`：生成当前字符串的子串，字串的取值是原字符串的 [start, length()-1]。

- `String substring(int start, int end)`：生成当前字符串的子串，字串的取值是原字符串的 [start, end - 1]。

String 类的的转换功能：
- `byte[] getBytes()`： 使用平台的默认字符集将此 String 编码为 byte 序列，并将结果存储到一个新的 byte 数组中。

- `char[] toCharArray()`：将此字符串转换为一个新的字符数组。

- `static String valueOf(char[] chs)`：返回 char 数组参数的字符串表示形式。

- `static String valueOf(int i)`：返回 int 参数的字符串表示形式。

- `String toLowerCase()`：使用默认语言环境的规则将此 String 中的所有字符都转换为小写。

- `String toUpperCase()`：使用默认语言环境的规则将此 String 中的所有字符都转换为大写。

- `String concat(String str)`：将指定字符串连接到此字符串的结尾。

String 类的替换功能：  
- `String replace(char oldChar, char newChar)`：返回一个新的字符串，它是通过用 newChar 替换此字符串中出现的所有 oldChar 得到的。

- `String replace(CharSequence target, CharSequence replacement)`：使用指定的字面值替换序列替换此字符串所有匹配字面值目标序列的子字符串。

String 类去除空字符串：  
- `String trim()`：返回字符串的副本，忽略前导空白和尾部空白。 

String 类的比较功能：
- `int compareTo(String str)`： 按字典顺序比较两个字符串。  
  返回值：如果参数字符串等于此字符串，则返回值 0；如果此字符串按字典顺序小于字符串参数，则返回一个小于 0 的值；如果此字符串按字典顺序大于字符串参数，则返回一个大于 0 的值。

- `int compareToIgnoreCase(String str)`：按字典顺序比较两个字符串，不考虑大小写。

## StringBuffer

StringBuffer：线程安全的可变字符序列。一个类似于 String 的字符串缓冲区，但不能修改。虽然在任意时间点上它都包含某种特定的字符序列，但**通过某些方法调用可以改变该序列的长度和内容**。  

可将字符串缓冲区安全地用于多个线程。可以在必要时对这些方法进行同步，因此任意特定实例上的所有操作就好像是以串行顺序发生的，该顺序与所涉及的每个线程进行的方法调用顺序一致。 

构造方法：  
- `StringBuffer()`：构造一个其中不带字符的字符串缓冲区，其初始容量为 16 个字符。

- `StringBuffer(int capacity)`：构造一个不带字符，但具有指定初始容量的字符串缓冲区。

- `StringBuffer(String str)`：构造一个字符串缓冲区，并将其内容初始化为指定的字符串内容。该字符串的初始容量为 16 加上字符串参数的长度。 

示例：
```java
public class TestDemo {

    public static void main(String[] args) {

        // public StringBuffer()
        StringBuffer stringBuffer = new StringBuffer();
        System.out.println(stringBuffer.capacity());  // 16
        System.out.println(stringBuffer.length());  // 0

        // public StringBuffer(int capacity)
        stringBuffer = new StringBuffer(20);
        System.out.println(stringBuffer.capacity());  // 20
        System.out.println(stringBuffer.length());  // 0

        // public StringBuffer(String str)
        stringBuffer = new StringBuffer("HelloWorld");
        System.out.println(stringBuffer.capacity());  // 26
        System.out.println(stringBuffer.length());  // 10
    }
    
}
```

添加功能：
- `StringBuffer append(String str)`：将指定的字符串追加到此字符序列。

- `StringBuffer insert(int offset, String str)`：将字符串插入此字符序列中。

示例：  
```java
public class TestDemo {

    public static void main(String[] args) {

        StringBuffer stringBuffer = new StringBuffer("start：");
        System.out.println(stringBuffer.toString());
        System.out.println("capacity：" + stringBuffer.capacity());

        // append
        String s1 = "12345";
        stringBuffer.append(s1);
        System.out.println(stringBuffer.toString());
        System.out.println("capacity：" + stringBuffer.capacity());

        int i = 6;
        stringBuffer.append(i);
        System.out.println(stringBuffer.toString());
        System.out.println("capacity：" + stringBuffer.capacity());

        boolean b = true;
        stringBuffer.append(b);
        System.out.println(stringBuffer.toString());
        System.out.println("capacity：" + stringBuffer.capacity());

        // insert
        String s2 = "0";
        stringBuffer.insert(6, s2);
        System.out.println(stringBuffer.toString());
        System.out.println("capacity：" + stringBuffer.capacity());

        // 链式调用
        stringBuffer.insert(13, '7')
                .append("ABC")
                .append(false);
        System.out.println(stringBuffer.toString());
        System.out.println("capacity：" + stringBuffer.capacity());
    }
}

/* 运行结果：
start：
capacity：22
start：12345
capacity：22
start：123456
capacity：22
start：123456true
capacity：22
start：0123456true
capacity：22
start：01234567trueABCfalse
capacity：46
*/
```

注：
- 不管任何数据类型都可以被添加到字符缓冲区中，如果该类型不是字符串类型会自动转换为字符串类型。

- 虽然 StringBuffer 可以自动扩容，但是在开发中如果能有效的预估字符缓冲区所需的长度，建议使用 `StringBuffer(int capacity)` ，因为每一次扩容都比较耗时。

删除功能：
- `StringBuffer deleteCharAt(int index)`：移除此序列指定位置的 char。。

- `StringBuffer delete(int start, int end)`。

替换功能：
- `StringBuffer replace(int start, int end, String str)`：使用给定 String 中的字符替换此序列的子字符串中的字符。

示例：  
```java
public class TestDemo {

    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer("012345");
        StringBuffer abcd = stringBuffer.replace(0, 2, "abcd");
        System.out.println(abcd.toString());
    }

}

/* 运行结果：
abcd2345
*/
```

反转功能：
- `StringBuffer reverse()`：将此字符序列用其反转形式取代。

截取功能：
- `String substring(int start)`：返回一个新的 String，它包含此字符序列当前所包含的字符子序列。

!> StringBuffer 针对多线程运行环境。从 JDK 5 开始，为 StringBuffer 补充了一个单个线程使用的等价类 StringBuilder。StringBuilder 针对单线程运行环境，它的 api 和 StringBuffer 几乎没有差别。


## Date

Date 表示特定的瞬间，精确到毫秒。 

构造方法：
- `Date()`：分配 Date 对象并初始化此对象，以表示分配它的时间（精确到毫秒）。  

- `Date(long date)`：分配 Date 对象并初始化此对象，以表示自从标准基准时间（称为「历元（epoch）」，即 1970 年 1 月 1 日 00:00:00 GMT）以来的指定毫秒数。

常见方法：
- `long getTime()`：返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。

示例：  
```java
import java.util.Date;

public class TestDemo {

    public static void main(String[] args) {
        Date date = new Date();
        System.out.println("当前：" + date);

        long time = date.getTime();
        System.out.println("此时距离历元：" + time + " 秒");

        date = new Date(date.getTime() + 24 * 60 * 60 * 1000L);   // 一天后
        System.out.println("一天后：" + date);
    }

}

/* 运行结果：
当前：Sun May 10 21:12:28 CST 2020
此时距离历元：1589116348785 秒
一天后：Mon May 11 21:12:28 CST 2020
*/
```

## DateFormat

DateFormat 类：
- 是日期 / 时间格式化子类的抽象类。  

- 它以与语言无关的方式格式化并解析日期或时间。

- 因为是抽象类，所以实际使用的是 SimpleDateFormat 这个实现子类。

构造方法：
- `SimpleDateFormat()`：用默认的模式和默认语言环境的日期格式符号构造 SimpleDateFormat。

- `SimpleDateFormat(String pattern)`：用给定的模式和默认语言环境的日期格式符号构造 SimpleDateFormat。  
  - y：年。
  - M：月。
  - d：月。
  - H：小时。
  - m：分钟。
  - s：秒。

常用方法：
- `Date parse(String source)`：解析字符串的文本，生成 Date。如果发生错误，则返回 null。  

- `final String format(Date date)`：把一个 Date 对象表示成一个指定格式的表示时间的字符串。

示例：  
```java
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDemo {

    public static void main(String[] args) throws ParseException {
        Date date = new Date();
        System.out.println("默认：" + date);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        System.out.println("格式处理：" + simpleDateFormat.format(date));

        date = simpleDateFormat.parse("2020/01/10 17:06:00");
        System.out.println("解析字符串：" + date);
    }

}

/* 运行结果：
默认：Sun May 10 21:48:57 CST 2020
格式处理：2020/05/10 21:48:57
解析字符串：Fri Jan 10 17:06:00 CST 2020
*/
```

## Math

Math 类包含用于执行基本数学运算的方法，如初等指数、对数、平方根和三角函数。 

常见方法：
- `static int abs(int a)`：求绝对值。

- `static double ceil(double a)`：向上取整。

- `static double floor(double a)`：向下取整。 

- `static int max(int a, int b)`：返回两个 int 值中较大的一个。

- `static double pow(double a, double b)`：返回第一个参数的第二个参数次幂的值。

- `static double random()`：返回带正号的 double 值，该值是 [0.0 1.0) 范围的随机数。

- `static int round(float a)`：返回最接近参数的 int。

- `static double sqrt(double a)`：返回正确舍入的 double 值的正平方根。