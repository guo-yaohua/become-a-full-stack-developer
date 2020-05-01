# Day22 Thread01-2

## 作业
1. 自己利用线程的第一种实现方式，实现如下功能(多线程引例)：  
  a. 程序不停地在屏幕上输出一句问候的语句(比如“你好”)  
     (时间间隔一定要大些比如3s(或大于3s)，因为在控制台窗口，输入和输出不能同时发生，  
     我们只能在两次输出“你好”的间隙，从键盘输入数据，才能保证键盘输入被正确接收)  
  b.同时，当我通过键盘输入固定响应的时候，程序停止向屏幕输出问候的语句     
  
2. 阅读如下代码：   
  1.回答输出的是什么内容  
     输出的是：Thread匿名子类的run方法。
  2.为什么
    ```
    new Thread(new Runnable() {
         @Override
         public void run() {
           System.out.println("Runnable匿名子类的run方法");
         }
       }) {
         @Override
         public void run() {
           System.out.println("Thread匿名子类的run方法");
         }
       }.start();
    ```
   
## 答

1. 见代码

2. 原因如下：
   
   1. 首先，我们创建了一个 Thread 的匿名内部类对象；
   
   2. 该匿名 Thread 子类对象接收一个 Ruannable 接口子类的匿名内部类对象；
   
   3. 在 Thread 子类的匿名内部类定义中，我们覆盖了父类(Thread)的 run 方法；
   
   4. 接着我们在 Thread 的匿名内部类对象上调用了 start() 方法，启动该 Thread 匿名内部类对象所表示的子线程；
   
   5. 在该子线程(即 Thread 的匿名内部类对象)上调用 start() 方法，start() 方法会调用 Thread 类的 run() 方法，但这个 run 方法在
    Thread 的匿名子类定义中被子类覆盖了。所以实际在子线程中执行并非是 Thread 类的 run() 方法，而是 Thread 的匿名子类中定义的 
    run()方法(即多态效果)。