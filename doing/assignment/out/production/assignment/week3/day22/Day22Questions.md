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