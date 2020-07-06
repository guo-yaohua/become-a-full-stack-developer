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
  - **↑ post ↓ get**
  - /admin/goods/getType：获取商品种类
  - /admin/goods/getGoodsByType：获取某个分类下的商品信息
  - /admin/goods/getGoodsInfo：获取商品信息


/mall：前台界面
- /mall/user：用户操作
  - /mall/user/signup：用户注册
  - **↑ post ↓ get**




## 3 踩过的坑

### 3.1 文件名红色

问题：在 git 仓库下新建 Maven 项目后，发现 `mall.imi` 和 `pom.xml` 等配置文件的文件名在 IDEA 中显示为红色。

解决：红色表示没有把文件加入版本管理，问题不大。

### 3.2 undefined

情形 1：vue 界面显示 `xxx undefined`。

可能情况：服务端的变量名书写有误，导致代码能正常运行，但是数据传输时 vue 找不到对应的 key。  
示例 1：服务端的 message 错误书写为 massage。

情形 2：点击功能后显示 `undefined`。  

可能情况：servlet 的 url 书写有误。  
- 示例 1：将 allUser 功能书写在 /admin/admin/* 此 servlrt 中。

- 示例 2：将应该在 get 请求中的方法写入 post。

### 3.3 Tomcat 启动失败

情形 1：刚调整包结构，再次编译后就不能运行，代码没有报错。

可能情况：调整前生成的 class 文件并没有删除，再次编译后 class 会产生冲突。  
解决：将 /target/classes 目录删除，再重新编译。