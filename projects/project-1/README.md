# 项目一 网上商城

## 目录

## 1 项目概述


## 2 已实现功能

/admin：后台管理
- /admin/admin：管理管理员
  - /admin/admin/login：管理员登录
  - /admin/admin/addAdminss：添加管理员
  - /admin/admin/updateAdminss：更新管理员信息
  - /admin/admin/getSearchAdmins：搜索管理员
  - /admin/admin/changePwd：修改当前管理员密码
  - /admin/admin/logoutAdmin：管理员退出登录
  - **↑ post ↓ get**
  - /admin/admin/allAdmins：获取全部管理员
  - /admin/admin/deleteAdmins：删除指定管理员
  - /admin/admin/getAdminsInfo：获取当前管理员信息

- /admin/user：管理用户
  - **↑ post ↓ get**
  - /admin/user/allUser：获取全部用户
  - /admin/user/deleteUser：删除指定用户
  - /admin/user/searchUser：搜索用户

- /admin/goods：管理商品
  - /admin/goods/imgUpload：上传图片
  - /admin/goods/addGoods：新增商品
  - /admin/goods/addType：添加类目
  - /admin/goods/addSpec：添加规格
  - /admin/goods/deleteSpec：删除规格
  - /admin/goods/updateGoods：更新商品
  - /admin/goods/reply：回复留言
  - **↑ post ↓ get**
  - /admin/goods/getType：获取商品种类
  - /admin/goods/getGoodsByType：获取某个分类下的商品信息
  - /admin/goods/getGoodsInfo：获取商品信息
  - /admin/goods/deleteType：删除指定类目，并删除与其关联的 goods、spec
  - /admin/goods/deleteGoods：删除指定商品，及其关联的 spec
  - /admin/goods/repliedMsg：获取已经回复的消息
  - /admin/goods/noReplyMsg：获取未得回复的消息

- /admin/order：管理订单
  - /admin/order/ordersByPage：分页显示订单
  - /admin/order/changeOrder：修改订单
  - **↑ post ↓ get**
  - /admin/order/order：获取订单详情
  - /admin/order/deleteOrder：删除订单


/mall：前台界面
- /mall/user：用户操作
  - /mall/user/signup：用户注册
  - /mall/user/login：用户登录
  - /mall/user/updateUserData：修改用户信息
  - /mall/user/updatePwd：修改用户密码
  - **↑ post ↓ get**
  - /mall/user/data：获取用户信息

- /mall/order：订单操作
  - /mall/order/addOrder：下单
  - /mall/order/settleAccounts：付款
  - /mall/order/sendComment：评论
  - **↑ post ↓ get**
  - /mall/order/getOrderByState：查看购物车
  - /mall/order/confirmReceive：确认收货
  - /mall/order/pay：确认付款
  - /mall/order/deleteOrder：删除订单

- /mall/index：商店页面
  - **↑ post ↓ get**
  - /mall/index/getType：获取商品分类

- /mall/goods：商品界面
  - /mall/goods/askGoodsMsg：商品提问
  - **↑ post ↓ get**
  - /mall/goods/getGoodsByType：获取商品列表
  - /mall/goods/getGoodsInfo：获取商品信息
  - /mall/goods/getGoodsMsg：获取商品留言
  - /mall/goods/getGoodsComment：获得商品评价

other：
- 后台权限管理
- 前台管理权限

## 3 踩过的坑

### 3.1 文件名红色

问题：在 git 仓库下新建 Maven 项目后，发现 `mall.imi` 和 `pom.xml` 等配置文件的文件名在 IDEA 中显示为红色。

解决：红色表示没有把文件加入版本管理，问题不大。

### 3.2 undefined

情形 1：vue 界面显示 `xxx undefined`。

可能情况 1：服务端的变量名书写有误，导致代码能正常运行，但是数据传输时 vue 找不到对应的 key。  
示例 1：服务端的 message 错误书写为 massage。

可能情况 2：list 类型没有指定实现类。  
示例 1：List<Integer> idList = null;

情形 2：点击功能后显示 `undefined`。  

可能情况：servlet 的 url 书写有误。  
- 示例 1：将 allUser 功能书写在 /admin/admin/* 此 servlrt 中。

- 示例 2：将应该在 get 请求中的方法写入 post。

### 3.3 Tomcat 启动失败

情形 1：刚调整包结构，再次编译后就不能运行，代码没有报错。

可能情况：调整前生成的 class 文件并没有删除，再次编译后 class 会产生冲突。  
解决：将 /target/classes 目录删除，再重新编译。