# Day22_JDBC

1. 总结和整理今天上课讲的内容，把上课演示的demo自己敲一遍。

2. 在product_comment表中, 我们给comment_time字段创建了索引。请问执行下面查询的时候，索引是否会失效？如果失效，应该怎样重写？请用今天学的知识验证以下。  
    ```sql
    SELECT * FROM product_comment 
    WHERE DATE(comment_time) >= '2018-10-01 10:00:00' AND comment_time <= '2018-10-02 10:00:00'
    ```