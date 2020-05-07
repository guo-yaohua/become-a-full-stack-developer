# Day26 reflection02

1. 设计一个方法如下，要求该方法能修改任意对象中，指定成员变量的值.
    ```
    public class Work1 {
        /**
        *
        * @param targetObj  要修改成员变量值的目标对象
        * @param fieldName  对象中要修改的成员变量的名字
        * @param newValue   要修改成员变量值的新值
        */
        public static void setAll(Object targetObj, String fieldName, Object newValue) {
    }
    ```
  
2.  完成如下方法，要求该方法能调用，指定配置文件中指定类，指定的普通成员方法(无参方法)。假设指定类中一定有默认构造方法：
    ```
    public class Work2 {
        /**
        *      
        * @param configFilePath  表示配置文件的路径
        */
        public void callTargetMethod(String configFilePath) {
     }  
    }
    ```