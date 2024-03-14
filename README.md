### 概述

Smart是基于SpringBoot构建的微服务增强框架。它不仅统一了异常、国际化、文档、校验等处理规范，还引入了对配置文件进行分拆的新型开发方法。使用它作为技术底座的多模块应用，前期可以将模块组合实现单体部署。跟随业务的发展，在无代码改动的情况下，又具备将模块独立成微服务的能力，实现分阶降本增效。

### 组件介绍

  ```
smart                                         应用名称
├── smart-core                                核心模块，统一响应报文
├── smart-starter                             装配父模块
│   ├── smart-starter-exception               异常模块，统一异常基类，拦截结果处理，国际化支持
│   ├── smart-starter-i18n                    国际化模块，多国家语言处理
│   ├── smart-starter-swagger                 文档模块，Swagger规范
│   ├── smart-starter-validation              校验模块，集成SpringBoot Validation，国际化支持
├── smart-starter-web                         组装模块，SpringBoot Web基础和装配模块公共能力的组装
├── smart-starter-web-mybatisplus             组装扩展MybatisPlus模块，贯标分页实体、公共字段及公共字段的自动赋值能力
  ```

### 依赖关系

![](./relationship.png)

### 技术选型

| 技术                   | 版本    | 说明             |
| ---------------------- | ------- | ---------------- |
| spring-boot             | 2.5.13   | 容器+MVC框架     |
| mybatis                | 3.5.10   | ORM框架          |
| mybatis-plus           | 3.5.2   | MyBatis增强工具  |
| hibernate-validator    | 6.2.3.Final   | 校验  |
| swagger      | 3.0.0   | 文档     |

### 特色
1.统一响应报文、异常、国际化、文档、校验等处理规范

2.标准化ORM层公共字段及其变更自动赋值能力

3.解决ORM框架分页实现各行其道带来的RPC调用分页实体转换、前端多套分页逻辑等问题

4.引入模块对配置文件进行分拆的新型开发方法，以便应用能按需选择单体还是微服务的部署方式

### 扩展性
任意smart-starter-xxx模块装配的Bean都支持自定义扩展覆盖的能力

### 示例
示例工程[smart-sample](https://github.com/a466350665/smart-sample)