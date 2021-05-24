## 反射定义

Java 的反射是指程序在运行期可以拿到一个对象的所有信息。

反射是为了解决在运行期，对某个实例一无所知的情况下，如何调用其方法。

!> 反射是 Java 中最为重要的特性，几乎所有的开发框架以及应用技术中都是基于反射技术的应用。

## 类加载

当程序要使用某个类时，如果该类还未被加载到内存中，则系统会通过加载、连接、初始化这三步来实现对这个类进行初始化。  

```mermaid
graph LR
A[加载] --> B[连接] --> C[初始化]
```

加载：
- 通过一个类的全限定名来获取定义此类的二进制字节流。  

- 在内存中生成一个代表这个类的 java.lang.Class 对象，作为方法区这个类的各种数据的访问入口。

连接：
- 验证：确保被加载类的正确性。

- 准备：负责为类的静态成员分配内存并设置默认初始化值。  

- 解析：将类中的符号引用替换为直接引用。

初始化：
- 给静态成员变量赋初值，执行静态代码块内容

类加载时机：  
- 创建类的实例（首次创建该类对象）。

- 访问类的静态变量。

- 调用类的静态方法。

- 使用反射方式来强制创建某个类或接口对应的 java.lang.Class 对象。

- 初始化某个类的子类，会先触发父类的加载。

- 直接使用 java.exe 命令来运行某个主类。

类加载器：完成通过一个类的全限定名来获取描述此类的二进制字节流的功能。

类加载器的分类（JDK8）：
- Bootstrap ClassLoader 根类加载器：负责 Java 核心类的加载，JDK 中 JRE 的 lib 目录下 rt.jar。

- Extension ClassLoader 扩展类加载器：负责 JRE 的扩展目录中 jar 包的加载，在 JDK 中 JRE 的 lib 目录下 ext 目录。

- Sysetm ClassLoader 系统类加载器：负责加载自己定义的 Java 类。   


## 获取 Class 对象

通过 Object 类中的 `getClass()` 方法，可以通过对象获取此对象所在类的信息。返回的类型为 java.lang.Class，这是反射操作的源头，即所有的反射操作都需要通过此类开始。这个类有 3 种实例化方式。

调用 Object 类 `getClass()` 方法。示例：  
```java
Date date = new date;
Class<?> cls = date.getClass();
  ```

使用 `类.class` 取得。示例：  
```java
Class<?> cls = java.util.Date.class;
```

调用 Class 类提供的方法：`public static Class<?> forName(String className) throws ClassNotFoundException`。示例：  
```java
Class<?> cls = Class.forName("java.util.Date");
```

!> `类.class` 触发的类加载过程是不完整的类加载过程（静态代码块中的代码不会执行），而 `Class.forName` 触发的是完整的类加载过程。  
Class.forName 方法参数必须是一个类全类名。  
开发中，多用第三种方式来获取某个类的 Class 对象, 因为这种方式很灵活，该方法可以根据不同全类名，加载全类名。

## 获取构造方法

从 Class 对象中获取构造方法信息使用 Constructor 对象。Constructor 专门用来描述构造方法的，一个 Constructor 对象表示一个构造方法。

获取类中多个构造方法：
- `Constructor[] getConstructors()`：只会返回当 Class 对象所表示的类中，定义的 public 访问权限的构造方法。

- `Constructor[] getDeclaredConstructors()`：返回当前 Class 对象所表示的类中，定义的所有构造方法。

获取指定的单个构造方法：
- `Constructor<T> getConstructor(Class... parameterTypes)`：获取 Class 对象所表示类中，具有 public 访问权限的单个指定的构造方法。
    
- `Constructor<T> getDeclaredConstructor(Class... parameterTypes)`：获取 Class 对象所表示类中，具有任意访问权限的单个指定的构造方法。  

使用 Constructor 创建对象：  
- `newInstance(参数列表)`：该方法定义在 Constructor 类中。

- `newInstance(Object... initargs)`：Object... initargs 表示的就是用来初始化对象的初始化参数。


示例：  
```java
Constructor privateConstructor = constructorClass.getDeclaredConstructor(int.class, double.class, String.class);

// 解决权限问题，在 Constructor 对象上调用 setAccessible(true) 方法，该方法就会使得 JVM 绕过权限检查机制（不检查权限），直接执行
privateConstructor.setAccessible(true);

// 使用构造方法，创建对象
Object zhangsan = privateConstructor.newInstance(10, 1.5, "zhangsan");
```

!> 在获取单个构造方法的时候，必须指明构造方法的参数列表，通过构造方法的参数列表来区分不同的构造方法。  
在反射中，所有的数据类型都是用数据类型对应的 Class 表示的。  
parameterTypes 用来指明指定构造方法的参数列表的（参数类型用对应的 Class 对象表示）。  
`数据类型...` 这种方法参数是可变参数, 就是说该类型的参数对应的参数个数是可变的。一个方法中，只能定义一个可变参数，而且该参数必须位于参数列表的最后一个位置。 

## 获取成员变量

Field 类专门用来表述成员变量，一个 Field 对象，就表示一个成员变量。

获取类中定义的多个成员变量：
- `Field[] getFields()`：获取当前 Class 对象所表示的类中定义的 public 成员变量，同时还可以通过该方法获取到该类的父类中定义 public 成员变量。

- `Field[] getDeclaredFields()`：获取当前 Class 对象所表示的类中定义的所有成员变量，但是不包括父类中定义的成员变量。

获取单个指定的成员变量：
- `Field getField(String name)`：可以通过指定成员变量的变量名，从 Class 对象对应的类中获取指定名称的 public 成员变量（Field 对象表示），还可以获取到父类中定义的同名 public 成员变量。  
  该方法，在类中查找指定名称的 public 的成员变量时，顺序是先子类，如果子类中没有，再在父类中找。如果子类、父类中都没找到指定名称的成员变量，此时抛出异常 `NoSuchFieldException`。

- `Field getDeclaredField(String name)`：在 Class 对象表示的类中，获取任意权限的指定名称的成员变量（不包括父类中定义的成员变量）。

使用获取到的成员变量（Field 类中定义的方法）： 
- `Object get(Object obj)`：可以在该类的任意对象是上获取该成员变量的值。
          
- `Field对象.get(指定对象)`：在指定对象上获取该成员变量的值。  
  虽然这里返回的是一个 Object 对象，但是对于基本数据类型，也是可以这样的方式返回的。

- `void set(Object obj, Object newValue)`：可以在该类的任意对象上，设置该成员变量的值。

- `Field对象.set(目标对象， 要修改的新值)`：在指定对象上，将该成员变量的值修改为新的值。

示例：  

<!-- tabs:start -->

#### **TestDemo.java**

```java
import java.lang.reflect.Field;

public class TestDemo {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        // 测试针对基本数据类型
        Student student = new Student("zhang3", 15);

        Class<?> studentClassClass = student.getClass();

        // 获取 student 类中目标私有成员变量
        Field studentAge = studentClassClass.getDeclaredField("age");

        // 解决权限问题，使 JVM 绕过权限检查机制
        studentAge.setAccessible(true);

        // Object get(Object obj)
        int i = (int) studentAge.get(student);
        System.out.println(i);

        // 在指定对象上，将该成员变量的值修改为新的值。
        studentAge.set(student, 10);
        System.out.println(student);

        Student student2 = new Student("li4", 18);
        studentAge.set(student2, 20);
        System.out.println(student2);
    }
}


/* 输出：
15
Student{name='zhang3', age=10}
Student{name='li4', age=20}
*/
```

#### **Student.java**

```java
public class Student {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```

<!-- tabs:end -->

## 获取成员方法

Method 专门用来描述方法，一个 Method 对象就代表类中定义的一个方法。  

获取类中定义的多个方法：
- `Method[] getMethods()`：获取当前 Class 对象表示类中的 public 成员方法，也可以获取到当前类的父类中定义的 public 成员方法。
    
- `Method[] getDeclaredMethods()`：获取当前 Class 对象表示类中所有的成员方法（不包括父类中定义的）。

获取单个指定的方法（通过指定方法名 + 参数列表）：
- `Method getMethod(String name, Class... parameterTypes)`：可以获取当前类及其父类中定义的 public 方法。  

- `Method getDeclaredMethod(String name, Class... parameterTypes)`：只能获取当前类中定义的任意访问权限的方法（不包括父类中定义的）。

通过反射获取到的方法的使用（就是在指定对象上传递实际参数，并调用该方法）：  
- `Object invoke(Object obj, Object... args)`：定义在 Method 类中。invoke 方法的返回值，代表的是目标方法的返回值。

示例：

<!-- tabs:start -->

#### **TestDemo.java**

```java
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestDemo {
    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        
        Class<?> studentClassClass = Class.forName("com.gyh.Student");
        
        Method sayHello = studentClassClass.getDeclaredMethod("sayHello");

        Student student = new Student("li4", 18);
        
        sayHello.invoke(student);
    }
}

/* 输出：
Hello li4
*/
```

#### **Student.java**

```java
public class Student {
    private String name;
    private int age;

    public void sayHello() {
        System.out.println("Hello " + name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```

<!-- tabs:end -->