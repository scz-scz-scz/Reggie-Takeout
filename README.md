# Reggie-Takeout
瑞吉外卖

项目概述
这是一个基于 Spring Boot 3.5.0 的外卖平台系统，主要特点是实现了秒杀功能和库存扣减，使用了延迟双删技术来保证缓存一致性。

核心技术栈
后端框架: Spring Boot 3.5.0 + Java 21
数据库: MySQL + MyBatis
缓存: Redis（Spring Data Redis + Spring Cache）
数据库中间件: Sharding-JDBC（支持主从复制、读写分离）
连接池: Druid
API文档: Knife4j（集成 Swagger）
短信服务: 阿里云短信服务
工具库: Lombok、FastJSON、Commons Lang
主要功能模块
从代码结构看，系统包含以下模块：

员工管理 (Employee) - 后台员工账号管理
用户管理 (User) - 前台用户管理，支持短信验证码登录
分类管理 (Category) - 菜品和套餐分类
菜品管理 (Dish) - 菜品信息、口味管理
套餐管理 (Setmeal) - 套餐及套餐菜品关联
购物车 (ShoppingCart) - 购物车功能
订单管理 (Order) - 订单和订单明细
地址簿 (AddressBook) - 收货地址管理
文件上传 (Common) - 图片上传功能
技术亮点
使用 Redis 缓存提升性能
延迟双删策略保证缓存一致性
Sharding-JDBC 实现数据库主从复制和读写分离
拦截器实现登录校验
全局异常处理
MyBatis Plus 自动填充创建时间、更新时间等字段
Knife4j 自动生成 API 文档
这是一个功能完整的外卖系统，适合学习 Spring Boot 企业级应用开发。
