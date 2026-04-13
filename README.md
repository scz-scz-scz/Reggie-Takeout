# Reggie-Takeout 瑞吉外卖

一个基于 Spring Boot 的外卖平台系统，实现了秒杀功能和库存扣减，使用延迟双删技术保证缓存一致性。

## 项目概述

这是一个功能完整的外卖系统，采用前后端分离架构，支持用户下单、商家管理、订单处理等完整业务流程。

## 核心技术栈

- **后端框架**: Spring Boot 3.5.0 + Java 21
- **数据库**: MySQL + MyBatis
- **缓存**: Redis（Spring Data Redis + Spring Cache）
- **数据库中间件**: Sharding-JDBC（支持主从复制、读写分离）
- **连接池**: Druid
- **API文档**: Knife4j（集成 Swagger）
- **短信服务**: 阿里云短信服务
- **工具库**: Lombok、FastJSON、Commons Lang

## 主要功能模块

1. **员工管理** (Employee) - 后台员工账号管理
2. **用户管理** (User) - 前台用户管理，支持短信验证码登录
3. **分类管理** (Category) - 菜品和套餐分类
4. **菜品管理** (Dish) - 菜品信息、口味管理
5. **套餐管理** (Setmeal) - 套餐及套餐菜品关联
6. **购物车** (ShoppingCart) - 购物车功能
7. **订单管理** (Order) - 订单和订单明细
8. **地址簿** (AddressBook) - 收货地址管理
9. **文件上传** (Common) - 图片上传功能

## 技术亮点

- 使用 Redis 缓存提升性能
- 延迟双删策略保证缓存一致性
- Sharding-JDBC 实现数据库主从复制和读写分离
- 拦截器实现登录校验
- 全局异常处理
- MyBatis Plus 自动填充创建时间、更新时间等字段
- Knife4j 自动生成 API 文档

## 快速开始

### 环境要求

- JDK 21+
- Maven 3.6+
- MySQL 8.0+
- Redis 6.0+

### 配置说明

1. 修改 `application.yml` 中的数据库连接配置
2. 修改 Redis 连接配置
3. 配置阿里云短信服务（可选）
4. 配置文件上传路径

### 运行项目

```bash
mvn spring-boot:run
```

### 访问地址

- 应用地址: http://localhost:8080
- API 文档: http://localhost:8080/doc.html
