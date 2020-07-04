# 项目一 网上商城

## 目录

## 1 项目概述


## 2 已实现功能

/admin：
- /admin/admin：
    - /admin/admin/login：管理员登录
    - /admin/admin/addAdminss：添加管理员
    - /admin/admin/updateAdminss：更新管理员信息
    - /admin/admin/getSearchAdmins：管理员查询
    - /admin/admin/changePwd：修改当前管理员密码
    - **↑ post ↓ get**
    - /admin/admin/allAdmins：获取全部管理员
    - /admin/admin/deleteAdmins：删除指定管理员
    - /admin/admin/getAdminsInfo：获取当前管理员信息

- /admin/user：
    - **↑ post ↓ get**
    - /admin/user/allUser：获取全部用户
    - /admin/user/deleteUser：删除指定用户

/mall：
- /mall/user：
  - /mall/user/signup：用户注册
  - **↑ post ↓ get**




## 3 踩过的坑

### 3.1 文件名红色

问题：在 git 仓库下新建 Maven 项目后，发现 `mall.imi` 和 `pom.xml` 等配置文件的文件名在 IDEA 中显示为红色。

解决：红色表示没有把文件加入版本管理，问题不大。

### 3.2 undefined

情形 1：vue 界面显示 undefined。

可能情况：服务端的变量名书写有误，导致代码能正常运行，但是数据传输时 vue 找不到对应的 key。  
例如：服务端的 message 错误书写为 massage。

情形 2：点击功能后显示 undefined。  

可能情况：servlet 的 url 书写有误。  
例如：将 allUser 功能书写在 /admin/admin/* 此 servlrt 中。