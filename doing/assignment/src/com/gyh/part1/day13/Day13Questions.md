# Day 13 JavaObject03

## 作业
1. 为某研究所编写一个通用程序，用来计算每一种交通工具运行 1000 公里所需的时间。已知每种交通工具的参数都是 3 个整数常量 A、B、C 的表达式。  
  现有两种工具：Car 和 Plane，  
  其中 Car 的速度运算公式为：A * B / C，  
  Plane 的速度运算公式为  ：A + B + C。  
  要求在未来如果增加第 3 种交通工具（如 Ship）的时候，可以做到复用已有代码。  
  Ship 的速度运算公式为  ：A + B / C。  
  要求自己设计该系统，并测试。
  
2. 完成课堂上没有讲完的案例：  
  教练和运动员案例  
  乒乓球运动员和篮球运动员。  
  乒乓球教练和篮球教练。  
  为了出国交流，跟乒乓球相关的人员都需要学习英语。  
  请用所学知识：  
  分析，这个案例中有哪些抽象类，哪些接口，哪些具体类  
    ```
    乒乓球用运动员 属性  name age salary learnEnglish()
                行为 eat() sleep() train() match()
    
    篮球运动员   属性  name age salary
                行为 eat() sleep() train() match()
    
    乒乓球教练:  nama age salary bonus
                eat()，sleep(), teach()，learnEnglish()
    
    篮球教练：   nama age salary bonus
                eat()，sleep(), teach()
    ```

## 答
1. 见代码

2. 抽象类：Human、Coach、Athlete；  
  接口：SpecialSkill；  
  具体类：TableTennisAthlete、TableTennisCoach、BasketballAthlete、BasketballCoach。