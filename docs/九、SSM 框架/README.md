# 九、SSM 框架

## 目录

- [九、SSM 框架](#九ssm-框架)
  - [目录](#目录)
  - [1 设计模式](#1-设计模式)
    - [1.1 软件设计的原则](#11-软件设计的原则)
    - [1.2 单例](#12-单例)
    - [1.3 工厂](#13-工厂)
      - [1.3.1 简单工厂](#131-简单工厂)
      - [1.3.2 工厂方法](#132-工厂方法)
    - [1.4 代理](#14-代理)
      - [1.4.1 静态代理](#141-静态代理)
      - [1.4.2 动态代理](#142-动态代理)
    - [1.5 建造者](#15-建造者)



## 1 设计模式

设计模式是一套被反复使用的、多数人知晓的、经过分类编目的、代码设计经验的总结。

### 1.1 软件设计的原则

设计模式的五个原则 solid：
- S（single）：单一职责原则。

- O（open）：开放封闭原则。对新增代码开放，对修改代码封闭。

- L（Liskov）：里氏替代原则。用父类来接收子类的实例。

- I（isolation）：接口隔离原则。不同的功能放到不同的接口中。

- D：依赖倒置原则。先定义要做的事情是什么样子，然后再进行具体的实现。

### 1.2 单例

单例：单一实例，全局只有一个。

使用场景：
- Servlet

- ServletContext

特点：
- 构造方法私有。

- 包含自己的成员变量。

- 提供静态方法供人调用。 


示例：
```java
/**
 * 线程不安全的懒加载
 */
public class Singleton {

    private Singleton(){}

    static Singleton singleton;

    public static Singleton getInstance(){

        if (singleton == null){
            singleton = new Singleton();
        }
        return singleton;
    }
}
```

```java
/**
 * 线程安全的懒加载
 */
public class Singleton2 {

    private Singleton2(){}

    static Singleton2 singleton2;

    public synchronized static Singleton2 getInstance(){

        if (singleton2 == null){
            singleton2 = new Singleton2();
        }
        return singleton2;
    }
}
```

懒加载和立即加载：
- 懒加载在调用 getInstance 方法时才初始化示例
- 立即加载在还未调用 getInstance 方法的时候就已经完成了实例的初始化。

```java
/**
 * 立即加载
 */
public class Singleton3 {

    private Singleton3(){}

    static Singleton3 singleton3 = new Singleton3();

    public static Singleton3 getInstance(){
        return singleton3;
    }
}
```

```java
/**
 * 立即加载
 * 这个是 singleton3 的一个变种
 */
public class Singleton4 {

    private Singleton4(){}

    static Singleton4 singleton4;

    // 静态代码块
    static {
        singleton4 = new Singleton4();
    }

    public static Singleton4 getInstance(){


        return singleton4;
    }
}
```

```java
/**
 * 静态内部类实现线程安全的懒加载
 * 将实例初始化的过程放在静态内部类中
 */
public class Singleton5 {
    private Singleton5(){}

    static class Inner{
        static Singleton5 singleton5 = new Singleton5();

        public static Singleton5 provideSingleton5(){
            return singleton5;
        }
    }

    public static Singleton5 getInstance(){
        return Inner.provideSingleton5();
    }
}
```

### 1.3 工厂

在工厂模式中，我们在创建对象时不会对客户端暴露创建逻辑，并且是通过使用一个共同的接口来指向新创建的对象。

去生产实例，工厂要提供 create 方法。  
通过工厂可以把实例的实例化细节隐藏起来。  

注：XXXFactory 就是获得 xxx 实例。当看到类型是 XXXFactory 的时候，就要意识到这是一个工厂的设计模式。

#### 1.3.1 简单工厂

通过给工厂的生产方法传入不同的内容，工厂生产的内容就有所区别。

示例：
```java
public class AnimalFactory {

    // 提供一个返回值为 animal 的方法
    public Animal create(String name){
        Animal animal = null;

        if ("bull".equals(name)){
            animal = new Bull();
        } else if ("pig".equals(name)){
            animal = new Pig();
        } else if ("rabbit".equals(name)){
            animal = new Rabbit();
        } else if ("mouse".equals(name)){
            animal = new Mouse();
        }

        return animal;
    }
}
```

#### 1.3.2 工厂方法  

**（1）实例工厂**  

先对工厂进行实例化，然后通过工厂对象来调用生产方法。  

当需要新的生产实例的时候，新增对应的工厂实例即可。

```java
public interface CarFactory {

    public Car createCar();
}
```

```java
public class AudiCarFactory implements CarFactory{
    @Override
    public Car createCar() {
        return new AudiCar();
    }
}
```

```java
public class BydCarFactory implements CarFactory{
    @Override
    public Car createCar() {
        return new BydCar();
    }
}
```

**（2）静态工厂**

提供静态方法获得实例的工厂，称为静态工厂。  

工厂中的方法是静态方法，可以直接调用。

```java
public class DreamOneCarFactory implements CarFactory{
    @Override
    public Car createCar() {
        return null;
    }

    public static Car staticFactoryCreate(){
        return new DreamOneCar();
    }
}
```

### 1.4 代理

代理：为某个对象提供一个代理对象以控制对这个对象的访问。

代理对象：中介。

代理类：负责为委托类预处理消息，过滤消息并转发消息，以及进行消息被委托类执行后的后续处理。

被代理对象（委托类）：业主。

代理的设计模式优点：可以在目标对象实现的基础上，增强额外的功能，扩展目标对象的功能。

#### 1.4.1 静态代理  

静态代理需要我们自己来定义类的代理。  

**（1）委托类成员变量**  

```java
public class HouseOwner {

    public boolean rentHouse(Integer money){
        if (money >= 1500){
            return true;
        }
        return false;
    }
}
```

```java
public class HouseProxy {

    HouseOwner houseOwner = new HouseOwner();

    public boolean rentHouse(Integer money){
        money = money - 500;
        return houseOwner.rentHouse(money);
    }
}
```

**（2）代理类继承委托类**

```java
public class HouseProxy2 extends HouseOwner{

    public boolean rentHouse(Integer money){
        money = money - 500;
        return super.rentHouse(money);
    }
}
```

#### 1.4.2 动态代理

**（1）JDK 动态代理**

JDK 动态代理:
- JDK 提供的 API 来获得代理对象（增强对象）。

- JDK 动态代理使用要有接口的实现，并且要用接口来接收。

```java
public interface HelloService {
    public void sayHello();
}
```

```java
public class HelloServiceImpl implements HelloService{
    @Override
    public void sayHello() {
        System.out.println("hello world");
    }
}
```

```java
public class ReflectTest {

    @Test
    public void mytest() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class helloServiceClass = HelloServiceImpl.class;
        HelloService helloService = (HelloService) helloServiceClass.newInstance();

        Method method = helloServiceClass.getDeclaredMethod("sayHello", null);

        method.invoke(helloService, null);
    }
}
```

```java
/**
 *动态代理
 */
public class ProxyTest {

    /**
     * 没有使用动态代理
     */
    @Test
    public void mytest(){
        // 获得委托类对象
        HelloService helloService = new HelloServiceImpl();
        System.out.println("起床");
        helloService.sayHello();
        System.out.println("编程");
    }

    /**
     * 使用 jdk 动态代理
     */
    @Test
    public void mytest2(){
        // 先获得委托类对象的实例
        HelloService helloService = new HelloServiceImpl();
        
        // jdk 动态代理获得增强对象（代理对象）
        HelloService helloServiceProxy = (HelloService) Proxy.newProxyInstance(HelloServiceImpl.class.getClassLoader(),
                helloService.getClass().getInterfaces(), new InvocationHandler() {
            
            // invoke 中
            // 1 是要去执行委托类的方法
            // 2 可以去做增强
            // 返回值：Object 对应委托类方法执行的返回值
            // 参数：
            // proxy :代理对象
            // method: 委托类方法的 method
            // args: 委托类方法的参数
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("起床"); // 前面的增强
                
                // invoke 方法中可以使用反射来执行委托类的方法
                // 第一个参数是委托类对象，而不是代理对象
                Object invoke = method.invoke(helloService, args);
                
                System.out.println("编程"); // 后面的增强

                return invoke;
            }
        });

        // helloServiceProxy.sayHello(); // 使用代理对象去执行才会增强

        // helloService.sayHello(); // 只会输出hello world

    }
}
```
注：JDK 动态代理不能够使用实现类来接收代理对象。

**（2）cglib 动态代理**

cglib 动态代理：
- 不需要有接口的实现。  

- 基于继承去实现，proxy 对象是继承委托类对象。

- 可以用接口，也可以用实现类来接收。

- 代码写起来基本是一样，获得代理对象的过程使用 api 不同。

```java
/**
 * 用 cglib 单元测试使用
 * 这个类没有接口的实现
 */
public class HelloService2 {

    public void sayHello2(){
        System.out.println("hello world2");
        
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

```java
public class CglibProxyTest {

    /**
     * 计算helloService2中的方法的执行时间
     */
    @Test
    public void mytest1(){
        // jdk 没有提供，导包
        HelloService2 helloService2 = new HelloService2();
        // 第二个参数也是 InvocationHandler，和 jdk 动态代理的 InvocationHandler 不同
        HelloService2 helloService2Proxy = (HelloService2) Enhancer.create(HelloService2.class, new InvocationHandler() {
            // 这三个参数和 jdk 动态代理是一样的
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                long start = System.currentTimeMillis();
                Object invoke = method.invoke(helloService2, args);
                long end = System.currentTimeMillis();
                long cost = end - start;
                System.out.println(method.getName() + "执行时间是：" + cost);
                return invoke;
            }
        });
        helloService2Proxy.sayHello2();
    }
}
```

### 1.5 建造者

建造者（builder）也是生产实例，更侧重参数的设置。

```java
public class Foot {
    Integer size;

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Foot{" +
                "size=" + size +
                '}';
    }
}
```

```java
public class Head {
    Integer iq;
    Integer eq;
    String name;

    public Integer getIq() {
        return iq;
    }

    public void setIq(Integer iq) {
        this.iq = iq;
    }

    public Integer getEq() {
        return eq;
    }

    public void setEq(Integer eq) {
        this.eq = eq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Head{" +
                "iq=" + iq +
                ", eq=" + eq +
                ", name='" + name + '\'' +
                '}';
    }
}
```

```java
public class Human {
    Foot foot = new Foot();
    Head head = new Head();
    Leg leg = new Leg();

    public Foot getFoot() {
        return foot;
    }

    public void setFoot(Foot foot) {
        this.foot = foot;
    }

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public Leg getLeg() {
        return leg;
    }

    public void setLeg(Leg leg) {
        this.leg = leg;
    }

    @Override
    public String toString() {
        return "Human{" +
                "foot=" + foot +
                ", head=" + head +
                ", leg=" + leg +
                '}';
    }
}
```

```java
public class Leg {

    Integer length;
    boolean strong;

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public boolean isStrong() {
        return strong;
    }

    public void setStrong(boolean strong) {
        this.strong = strong;
    }

    @Override
    public String toString() {
        return "Leg{" +
                "length=" + length +
                ", strong=" + strong +
                '}';
    }
}
```


```java
public class HumanBuilder {

    Human human = new Human(); // 保证了 new 一个 builder 的时候同时 new 了一个 human ，就是空白的 human

    public HumanBuilder setFootSize(Integer size){
        human.getFoot().setSize(size);
        return this;
    }

    // 需要保证都是同一个 head
    public HumanBuilder setHeadIq(Integer iq){
        human.getHead().setIq(iq);
        return this;
    }

    public HumanBuilder setHeadEq(Integer eq){
        human.getHead().setEq(eq);
        return this;
    }

    public HumanBuilder setHeadName(String name){
        human.getHead().setName(name);
        return this;
    }

    public HumanBuilder setLegLength(Integer length){
        human.getLeg().setLength(length);
        return this;
    }

    public HumanBuilder setLegStrong(boolean strong){
        human.getLeg().setStrong(strong);
        return this;
    }

    public Human build(){
        return human;
    }

    public Human getHuman() {
        return human;
    }

    public void setHuman(Human human) {
        this.human = human;
    }
}
```

```java
public class MyTest {

    @Test
    public void mytest(){
        HumanBuilder humanBuilder = new HumanBuilder();
        humanBuilder.setLegLength(120)
                    .setHeadIq(150)
                    .setHeadEq(150)
                    .setHeadName("zhang3")
                    .setFootSize(44)
                    .setLegStrong(true);

        Human human = humanBuilder.build();
        System.out.println(human);
    }
}
```