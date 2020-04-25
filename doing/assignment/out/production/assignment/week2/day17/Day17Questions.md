# Day 17 exception

## 作业

1. final，finally 和 finalize的区别
  
2. 定义三种新类型的异常。  
   写一个类，在该类的三个方法中抛出三种不同的异常。  
   然后在 mian 方法中调用这个类的不同方法，尝试用 try catch 捕获你写的异常。
   
## 答

1. 
  1. final 最终的意思，修饰类，变量(成员变量和局部变量)，成员方法。  
  修饰类之后，该类不能被继承。
  修饰变量之后，该变量变成自定义常量。
  修饰方法之后，该方法部类被子类覆盖。
  2. finally 修饰代码块
  finally 代码块的执行特征是：  
  对于 try-catch-finnally 代码块而言，finally 代码块中的代码，不管是否发生异常，finally 代码块中的代码，最后都会执行。  
  即使在 finally 代码块之前，有 return 语句，finally 代码块，仍然会执行。  
  特殊情况：在执行到 finally 之前 JVM 退出了（比如System.exit(0)）。
     
  3. finalize() 是 Object 类中的一个方法。
  该方法在对象变成垃圾，并且被垃圾回收期调用之前，JVM 会在该对象上调用 finalize() 方法一次且仅一次。
  
2. 