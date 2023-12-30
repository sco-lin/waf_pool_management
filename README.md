<img src="https://cdn.jsdelivr.net/gh/0Cherish/PicCould/img/202312302008674.png"/>

<h1 align="center">WAF池管理系统</h1>
<p align="center">
  <a href="http://wpa.qq.com/msgrd?v=3&uin=2425074199&site=qq&menu=yes"><img alt="Lin" src="https://img.shields.io/badge/Author-Lin-blue.svg"></a>
  <a href="https://img.shields.io/github/stars/0Cherish/waf_pool_management.svg?style=social&label=Stars"><img alt="笔芯" src="https://img.shields.io/github/stars/0Cherish/waf_pool_management.svg?style=social&label=Stars"></a>

  <br/>


## 🌻前端传送门 ╰( ´・ω・)つ──☆👉🏻️➡<a href="https://github.com/youn0g/backup">backup</a>

## ✨接口文档

Base URLs:

* <a href="http://localhost:8080">后端: http://localhost:8080</a>
* <a href="http://localhost:8080">调度器: http://localhost:8080</a>

# back-end/用户

## POST 登录

POST /login

> Body Parameters

```json
{
  "username": "zhangsan",
  "password": "123456",
  "verifyCode": "934266"
}
```

### Params

| Name         | Location | Type             | Required | Description |
| ------------ | -------- | ---------------- | -------- | ----------- |
| body         | body     | object           | no       | none        |
| » email      | body     | string           | yes      | 邮箱        |
| » password   | body     | string(password) | yes      | 密码        |
| » verifyCode | body     | string           | yes      | 验证码      |

> Response Examples

> 成功

```json
{
  "code": 200,
  "msg": "操作成功",
  "status": "success",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMDc1YmNlNjYzYWY0YmVlODdkOGRmOWZhMTNhYjkyMyIsInN1YiI6IjEiLCJpc3MiOiJtYW94aWFuIiwiaWF0IjoxNzAzODUyODIzLCJleHAiOjE3MDQ0NTc2MjN9.08U__nnOS2N7_z8XsAGcoOUmxSCNN57owfBSqMJp2pk",
    "userInfo": {
      "id": 1,
      "username": "zhangsan",
      "email": "zls2434474199@163.com",
      "status": 0,
      "roles": [
        "super"
      ],
      "permissions": [
        "user",
        "role",
        "perm",
        "waf"
      ]
    }
  }
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema |
| ---------------- | ------------------------------------------------------- | ----------- | ----------- |
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功        | Inline      |

### Responses Data Schema

HTTP Status Code **200**

| Name            | Type                        | Required | Restrictions | Title | description |
| --------------- | --------------------------- | -------- | ------------ | ----- | ----------- |
| » code          | integer                     | true     | none         |       | 状态码      |
| » msg           | string                      | true     | none         |       | 提示信息    |
| » status        | string                      | true     | none         |       | 状态        |
| » data          | object                      | false    | none         |       | 数据        |
| »» token        | string                      | true     | none         |       | token       |
| »» userInfo     | [UserInfo](#schemauserinfo) | true     | none         |       | 用户信息    |
| »»» id          | integer                     | true     | none         |       | id          |
| »»» username    | string                      | true     | none         |       | 用户名      |
| »»» email       | string(email)               | true     | none         |       | 邮箱        |
| »»» status      | string                      | true     | none         |       | 状态        |
| »»» roles       | [string]                    | true     | none         |       | 角色信息    |
| »»» permissions | [string]                    | true     | none         |       | 权限信息    |

#### Enum

| Name   | Value   |
| ------ | ------- |
| status | success |
| status | fail    |

## DELETE 删除用户

DELETE /user/{id}

### Params

| Name  | Location | Type    | Required | Description |
| ----- | -------- | ------- | -------- | ----------- |
| id    | path     | integer | yes      | none        |
| token | header   | string  | yes      | none        |

> Response Examples

> 成功

```json
{
  "code": 200,
  "msg": "操作成功",
  "status": "success"
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema                         |
| ---------------- | ------------------------------------------------------- | ----------- | ----------------------------------- |
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功        | [BaseResponse](#schemabaseresponse) |

## GET 注销

GET /logout

### Params

| Name  | Location | Type   | Required | Description |
| ----- | -------- | ------ | -------- | ----------- |
| token | header   | string | yes      | token       |

> Response Examples

> 成功

```json
{
  "code": 200,
  "msg": "操作成功",
  "status": "success"
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema                         |
| ---------------- | ------------------------------------------------------- | ----------- | ----------------------------------- |
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功        | [BaseResponse](#schemabaseresponse) |

## GET 查询所有用户信息

GET /user

### Params

| Name  | Location | Type   | Required | Description |
| ----- | -------- | ------ | -------- | ----------- |
| token | header   | string | yes      | none        |

> Response Examples

> 成功

```json
{
  "code": 200,
  "msg": "操作成功",
  "status": "success",
  "data": [
    {
      "id": 1,
      "username": "zhangsan",
      "email": "zls2434474199@163.com",
      "status": 0,
      "roles": [
        "super"
      ],
      "permissions": [
        "user",
        "role",
        "perm",
        "waf"
      ]
    },
    {
      "id": 5,
      "username": "lisi",
      "email": "sdfasfsa@qq.com",
      "status": 0,
      "roles": [
        "admin"
      ],
      "permissions": [
        "user",
        "waf"
      ]
    },
    {
      "id": 8,
      "username": "lili",
      "email": "shdfksf",
      "status": 1,
      "roles": [
        "user"
      ],
      "permissions": [
        "user"
      ]
    },
    {
      "id": 15,
      "username": "袁磊",
      "email": "f.xbnxipdq@qq.com",
      "status": 0,
      "roles": [
        "user"
      ],
      "permissions": [
        "user"
      ]
    }
  ]
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema |
| ---------------- | ------------------------------------------------------- | ----------- | ----------- |
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功        | Inline      |

### Responses Data Schema

HTTP Status Code **200**

| Name           | Type                          | Required | Restrictions | Title | description |
| -------------- | ----------------------------- | -------- | ------------ | ----- | ----------- |
| » code         | integer                       | true     | none         |       | 状态码      |
| » msg          | string                        | true     | none         |       | 提示信息    |
| » status       | string                        | true     | none         |       | 状态        |
| » data         | [[UserInfo](#schemauserinfo)] | false    | none         |       | 数据        |
| »» id          | integer                       | true     | none         |       | id          |
| »» username    | string                        | true     | none         |       | 用户名      |
| »» email       | string(email)                 | true     | none         |       | 邮箱        |
| »» status      | string                        | true     | none         |       | 状态        |
| »» roles       | [string]                      | true     | none         |       | 角色信息    |
| »» permissions | [string]                      | true     | none         |       | 权限信息    |

#### Enum

| Name   | Value   |
| ------ | ------- |
| status | success |
| status | fail    |

## PUT 修改用户信息

PUT /user

修改用户基本信息

> Body Parameters

```json
{
  "id": 15,
  "username": "袁磊",
  "email": "f.xbnxipdq@qq.com"
}
```

### Params

| Name       | Location | Type          | Required | Description |
| ---------- | -------- | ------------- | -------- | ----------- |
| token      | header   | string        | yes      | none        |
| body       | body     | object        | no       | none        |
| » id       | body     | integer       | yes      | id          |
| » username | body     | string        | no       | 用户名      |
| » email    | body     | string(email) | no       | 邮箱        |

> Response Examples

> 成功

```json
{
  "code": 200,
  "msg": "操作成功",
  "status": "success"
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema |
| ---------------- | ------------------------------------------------------- | ----------- | ----------- |
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功        | Inline      |

### Responses Data Schema

HTTP Status Code **200**

| Name     | Type    | Required | Restrictions | Title | description |
| -------- | ------- | -------- | ------------ | ----- | ----------- |
| » code   | integer | true     | none         |       | 状态码      |
| » msg    | string  | true     | none         |       | 提示信息    |
| » status | string  | true     | none         |       | 状态        |
| » data   | object  | false    | none         |       | 数据        |

#### Enum

| Name   | Value   |
| ------ | ------- |
| status | success |
| status | fail    |

## POST 注册

POST /user/register

> Body Parameters

```json
{
  "username": "韩伟",
  "email": "c.obwa@qq.com",
  "password": "123456"
}
```

### Params

| Name       | Location | Type   | Required | Description |
| ---------- | -------- | ------ | -------- | ----------- |
| token      | header   | string | yes      | none        |
| body       | body     | object | no       | none        |
| » username | body     | string | yes      | none        |
| » email    | body     | string | yes      | none        |
| » password | body     | string | yes      | none        |

> Response Examples

> 成功

```json
{
  "code": 200,
  "msg": "操作成功",
  "status": "success"
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema |
| ---------------- | ------------------------------------------------------- | ----------- | ----------- |
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功        | Inline      |

### Responses Data Schema

HTTP Status Code **200**

| Name     | Type    | Required | Restrictions | Title | description |
| -------- | ------- | -------- | ------------ | ----- | ----------- |
| » code   | integer | true     | none         |       | 状态码      |
| » msg    | string  | true     | none         |       | 提示信息    |
| » status | string  | true     | none         |       | 状态        |
| » data   | object  | false    | none         |       | 数据        |

#### Enum

| Name   | Value   |
| ------ | ------- |
| status | success |
| status | fail    |

## PUT 当前用户修改密码

PUT /user/password

提供给当前用户修改密码，需要用户提供旧密码和新密码

> Body Parameters

```json
{
  "id": 0,
  "oldPassword": "string",
  "newPassword": "string"
}
```

### Params

| Name          | Location | Type    | Required | Description |
| ------------- | -------- | ------- | -------- | ----------- |
| token         | header   | string  | yes      | none        |
| body          | body     | object  | no       | none        |
| » id          | body     | integer | yes      | none        |
| » oldPassword | body     | string  | yes      | none        |
| » newPassword | body     | string  | yes      | none        |

> Response Examples

> 成功

```json
{
  "code": 200,
  "msg": "magna quis occaecat minim exercitation",
  "status": "success",
  "data": {}
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema |
| ---------------- | ------------------------------------------------------- | ----------- | ----------- |
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功        | Inline      |

### Responses Data Schema

HTTP Status Code **200**

| Name     | Type    | Required | Restrictions | Title | description |
| -------- | ------- | -------- | ------------ | ----- | ----------- |
| » code   | integer | true     | none         |       | 状态码      |
| » msg    | string  | true     | none         |       | 提示信息    |
| » status | string  | true     | none         |       | 状态        |
| » data   | object  | false    | none         |       | 数据        |

#### Enum

| Name   | Value   |
| ------ | ------- |
| status | success |
| status | fail    |

# back-end/邮箱

## GET 发送邮箱验证码

GET /email/code/{email}

### Params

| Name  | Location | Type   | Required | Description |
| ----- | -------- | ------ | -------- | ----------- |
| email | path     | string | yes      | none        |

> Response Examples

> 成功

```json
{
  "code": 200,
  "msg": "操作成功",
  "status": "success"
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema                         |
| ---------------- | ------------------------------------------------------- | ----------- | ----------------------------------- |
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功        | [BaseResponse](#schemabaseresponse) |

# back-end/WAF

## GET 上线waf

GET /waf/online/{id}

### Params

| Name  | Location | Type    | Required | Description |
| ----- | -------- | ------- | -------- | ----------- |
| id    | path     | integer | yes      | none        |
| token | header   | string  | yes      | none        |

> Response Examples

> 200 Response

```json
{
  "code": 0,
  "msg": "string",
  "status": "success"
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema |
| ---------------- | ------------------------------------------------------- | ----------- | ----------- |
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功        | Inline      |

### Responses Data Schema

HTTP Status Code **200**

| Name     | Type    | Required | Restrictions | Title | description |
| -------- | ------- | -------- | ------------ | ----- | ----------- |
| » code   | integer | true     | none         |       | 状态码      |
| » msg    | string  | true     | none         |       | 提示信息    |
| » status | string  | true     | none         |       | 状态        |

#### Enum

| Name   | Value   |
| ------ | ------- |
| status | success |
| status | fail    |

## GET 下线waf

GET /waf/offline/{id}

### Params

| Name  | Location | Type    | Required | Description |
| ----- | -------- | ------- | -------- | ----------- |
| id    | path     | integer | yes      | none        |
| token | header   | string  | yes      | none        |

> Response Examples

> 成功

```json
{
  "code": 200,
  "msg": "eu et pariatur aliquip",
  "status": "fail",
  "data": {}
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema                         |
| ---------------- | ------------------------------------------------------- | ----------- | ----------------------------------- |
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功        | [BaseResponse](#schemabaseresponse) |

## POST 增加waf

POST /waf

> Body Parameters

```json
{
  "name": "string",
  "ip": "192.168.0.1",
  "port": 65535,
  "configUrl": "string",
  "type": 0,
  "weight": 0,
  "description": "string",
  "imageId": 0,
  "containerId": "string",
  "createTime": "string",
  "updateTime": "string"
}
```

### Params

| Name          | Location | Type         | Required | Description             |
| ------------- | -------- | ------------ | -------- | ----------------------- |
| token         | header   | string       | yes      | none                    |
| body          | body     | object       | no       | none                    |
| » name        | body     | string       | yes      | waf名称                 |
| » ip          | body     | string(ipv4) | yes      | ip地址                  |
| » port        | body     | integer      | yes      | 端口号                  |
| » configUrl   | body     | string       | yes      | 配置地址                |
| » type        | body     | integer      | yes      | 0 docker 1 软件 2 其他  |
| » weight      | body     | integer      | yes      | 权重                    |
| » description | body     | string¦null  | no       | 描述信息                |
| » imageId     | body     | integer      | yes      | docker形式的waf的镜像id |
| » containerId | body     | string       | yes      | docker形式的waf的容器id |
| » createTime  | body     | string       | yes      | none                    |
| » updateTime  | body     | string       | yes      | none                    |

> Response Examples

> 成功

```json
{
  "code": 200,
  "msg": "操作成功",
  "status": "success"
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema                         |
| ---------------- | ------------------------------------------------------- | ----------- | ----------------------------------- |
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功        | [BaseResponse](#schemabaseresponse) |

## PUT 修改waf 基本信息

PUT /waf

修改 waf 的基本信息
name, ip, port, configUrl, desc

> Body Parameters

```json
{
  "id": 31,
  "description": "开联何商积步质物铁性治备做段声花意任。群非自气消变先图公三局具市较可干。条复给前关前记音时分资门共活议则飞到。放周油放习极战又百命亲治南。除动统但除接共处话上直十影音。革特同如历眼经得教院采体水。",
  "configUrl": "http://gotu.mm/fabgmniey",
  "port": 12063,
  "enable": "0",
  "ip": "26.134.220.59",
  "name": "个上解或"
}
```

### Params

| Name          | Location | Type         | Required | Description                     |
| ------------- | -------- | ------------ | -------- | ------------------------------- |
| token         | header   | string       | yes      | none                            |
| body          | body     | object       | no       | none                            |
| » id          | body     | integer      | yes      | id                              |
| » name        | body     | string       | no       | waf名称                         |
| » ip          | body     | string(ipv4) | no       | ip地址                          |
| » port        | body     | integer      | no       | 端口号                          |
| » configUrl   | body     | string       | no       | 配置地址                        |
| » status      | body     | string(char) | no       | 0表示上线，1表示下线，2表示移除 |
| » description | body     | string¦null  | no       | 描述信息                        |

> Response Examples

> 成功

```json
{
  "code": 200,
  "msg": "操作成功",
  "status": "success"
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema                         |
| ---------------- | ------------------------------------------------------- | ----------- | ----------------------------------- |
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功        | [BaseResponse](#schemabaseresponse) |

## GET 查询所有waf

GET /waf

查询所有waf的基本信息

### Params

| Name  | Location | Type   | Required | Description |
| ----- | -------- | ------ | -------- | ----------- |
| token | header   | string | yes      | none        |

> Response Examples

> 成功

```json
{
  "code": 200,
  "msg": "操作成功",
  "status": "success",
  "data": {
    "pageNum": 1,
    "pageSize": 5,
    "list": [
      {
        "id": 1,
        "name": "jiwi se",
        "ip": "54.243.255.4",
        "port": 6696,
        "configUrl": "https://www.shifeng.cn/HealthBabyCare",
        "enable": "0",
        "status": "1",
        "cpu": 14.63,
        "memory": 73.01,
        "startTime": "2023-11-02T16:25:14",
        "upTime": 5968,
        "description": "jga6KzVr7h"
      },
      {
        "id": 2,
        "name": "Grape",
        "ip": "216.144.184.255",
        "port": 38311,
        "configUrl": "http://image.nakamorikazuma215.biz/Others",
        "enable": "1",
        "status": "0",
        "cpu": 61.44,
        "memory": 15.28,
        "startTime": "2023-11-03T22:15:10",
        "upTime": 4171,
        "description": "K3FcFcizZN"
      },
      {
        "id": 3,
        "name": "Grahe se",
        "ip": "251.118.255.69",
        "port": 48803,
        "configUrl": "https://video.cindymorg220.xyz/ToysGames",
        "enable": "0",
        "status": "0",
        "cpu": 75.36,
        "memory": 33.35,
        "startTime": "2023-11-01T03:05:23",
        "upTime": 6553,
        "description": "mXNJetpB1j"
      },
      {
        "id": 4,
        "name": "Raspberry",
        "ip": "6.5.111.0",
        "port": 58842,
        "configUrl": "https://drive.mowaisan818.info/BaggageTravelEquipment",
        "enable": "0",
        "status": "0",
        "cpu": 89.24,
        "memory": 93.87,
        "startTime": "2023-11-03T21:47:46",
        "upTime": 9206,
        "description": "IxryeEY1Rp"
      },
      {
        "id": 5,
        "name": "Charry pro",
        "ip": "255.222.34.141",
        "port": 7506,
        "configUrl": "http://auth.wa517.co.jp/Food",
        "enable": "0",
        "status": "0",
        "cpu": 34.2,
        "memory": 89.82,
        "startTime": "2023-11-01T22:57:39",
        "upTime": 2863,
        "description": "8mRSAyJMPF"
      }
    ],
    "total": 30
  }
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema |
| ---------------- | ------------------------------------------------------- | ----------- | ----------- |
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功        | Inline      |

### Responses Data Schema

HTTP Status Code **200**

| Name           | Type         | Required | Restrictions | Title | description               |
| -------------- | ------------ | -------- | ------------ | ----- | ------------------------- |
| » code         | integer      | true     | none         |       | 状态码                    |
| » msg          | string       | true     | none         |       | 提示信息                  |
| » status       | string       | true     | none         |       | 状态                      |
| » data         | [object]     | false    | none         |       | 数据                      |
| »» id          | integer      | true     | none         |       | id                        |
| »» name        | string       | true     | none         |       | waf名称                   |
| »» ip          | string(ipv4) | true     | none         |       | ip地址                    |
| »» port        | integer      | true     | none         |       | 端口号                    |
| »» configUrl   | string       | true     | none         |       | 配置地址                  |
| »» status      | integer      | true     | none         |       | 1 上线，0 下线，2 异常    |
| »» weight      | integer      | true     | none         |       | 权重                      |
| »» type        | integer      | true     | none         |       | 0 docker 1 软件Waf 2 其他 |
| »» description | string¦null  | false    | none         |       | 描述信息                  |
| »» imageId     | integer      | true     | none         |       | docker形式的waf的镜像id   |
| »» containerId | string       | true     | none         |       | docker形式的waf的容器id   |
| »» createTime  | string       | true     | none         |       | none                      |
| »» updateTime  | string       | true     | none         |       | none                      |

#### Enum

| Name   | Value   |
| ------ | ------- |
| status | success |
| status | fail    |

## PUT 修改waf的权重

PUT /weight

> Body Parameters

```json
{
  "id": 0,
  "weight": 0
}
```

### Params

| Name     | Location | Type    | Required | Description       |
| -------- | -------- | ------- | -------- | ----------------- |
| body     | body     | object  | no       | none              |
| » id     | body     | integer | yes      | 需要修改的waf ID  |
| » weight | body     | integer | yes      | 修改后的权值 1-10 |

> Response Examples

> 成功

```json
{
  "code": 200,
  "msg": "in officia incididunt",
  "status": "success"
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema |
| ---------------- | ------------------------------------------------------- | ----------- | ----------- |
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功        | Inline      |

### Responses Data Schema

HTTP Status Code **200**

| Name     | Type    | Required | Restrictions | Title | description |
| -------- | ------- | -------- | ------------ | ----- | ----------- |
| » code   | integer | true     | none         |       | 状态码      |
| » msg    | string  | true     | none         |       | 提示信息    |
| » status | string  | true     | none         |       | 状态        |

#### Enum

| Name   | Value   |
| ------ | ------- |
| status | success |
| status | fail    |

## GET 根据镜像创建waf实例

GET /waf/add/{name}/{imageId}

### Params

| Name    | Location | Type    | Required | Description |
| ------- | -------- | ------- | -------- | ----------- |
| name    | path     | string  | yes      | none        |
| imageId | path     | integer | yes      | none        |
| token   | header   | string  | yes      | none        |

> Response Examples

> 200 Response

```json
{
  "code": 0,
  "msg": "string",
  "status": "success",
  "data": {}
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema                         |
| ---------------- | ------------------------------------------------------- | ----------- | ----------------------------------- |
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功        | [BaseResponse](#schemabaseresponse) |

## GET 查询waf监控数据

GET /waf/monitor/{id}

通过wafId查询

### Params

| Name  | Location | Type   | Required | Description |
| ----- | -------- | ------ | -------- | ----------- |
| id    | path     | string | yes      | none        |
| token | header   | string | yes      | none        |

> Response Examples

> 成功

```json
{
  "code": 200,
  "msg": "操作成功",
  "status": "success",
  "data": {
    "id": 1,
    "cpu": 77,
    "memory": 11,
    "startTime": "2023-11-10T01:27:51",
    "upTime": 147,
    "wafId": 1
  }
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema |
| ---------------- | ------------------------------------------------------- | ----------- | ----------- |
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功        | Inline      |

### Responses Data Schema

HTTP Status Code **200**

| Name          | Type                            | Required | Restrictions | Title | description |
| ------------- | ------------------------------- | -------- | ------------ | ----- | ----------- |
| » code        | integer                         | true     | none         |       | 状态码      |
| » msg         | string                          | true     | none         |       | 提示信息    |
| » status      | string                          | true     | none         |       | 状态        |
| » data        | [WafMonitor](#schemawafmonitor) | false    | none         |       | 数据        |
| »» id         | integer                         | true     | none         |       | id          |
| »» cpu        | string                          | true     | none         |       | cpu占用率   |
| »» memory     | string                          | true     | none         |       | 内存占用率  |
| »» wafId      | integer                         | true     | none         |       | wafId       |
| »» createTime | string                          | true     | none         |       | none        |
| »» updateTime | string                          | true     | none         |       | none        |

#### Enum

| Name   | Value   |
| ------ | ------- |
| status | success |
| status | fail    |

# back-end/镜像

## POST 导入镜像

POST /image/upload

> Body Parameters

```yaml
image: string

```

### Params

| Name    | Location | Type           | Required | Description |
| ------- | -------- | -------------- | -------- | ----------- |
| token   | header   | string         | no       | none        |
| body    | body     | object         | no       | none        |
| » image | body     | string(binary) | no       | none        |

> Response Examples

> 200 Response

```json
{
  "code": 0,
  "msg": "string",
  "status": "success",
  "data": {}
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema                         |
| ---------------- | ------------------------------------------------------- | ----------- | ----------------------------------- |
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功        | [BaseResponse](#schemabaseresponse) |

## GET 查询所有镜像

GET /image

### Params

| Name  | Location | Type   | Required | Description |
| ----- | -------- | ------ | -------- | ----------- |
| token | header   | string | no       | none        |

> Response Examples

> 200 Response

```json
{
  "code": 0,
  "msg": "string",
  "status": "success",
  "data": [
    {
      "id": 0,
      "name": "string",
      "tag": "string",
      "imageId": "string",
      "createTime": "string",
      "updateTime": "string"
    }
  ]
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema |
| ---------------- | ------------------------------------------------------- | ----------- | ----------- |
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功        | Inline      |

### Responses Data Schema

HTTP Status Code **200**

| Name          | Type                    | Required | Restrictions | Title | description |
| ------------- | ----------------------- | -------- | ------------ | ----- | ----------- |
| » code        | integer                 | true     | none         |       | 状态码      |
| » msg         | string                  | true     | none         |       | 提示信息    |
| » status      | string                  | true     | none         |       | 状态        |
| » data        | [[Image](#schemaimage)] | false    | none         |       | 数据        |
| »» id         | integer                 | true     | none         |       | none        |
| »» name       | string                  | true     | none         |       | 镜像名      |
| »» tag        | string                  | true     | none         |       | 镜像标签    |
| »» imageId    | string                  | true     | none         |       | 镜像id      |
| »» createTime | string                  | true     | none         |       | none        |
| »» updateTime | string                  | true     | none         |       | none        |

#### Enum

| Name   | Value   |
| ------ | ------- |
| status | success |
| status | fail    |

## DELETE 移除镜像

DELETE /image/{id}

### Params

| Name  | Location | Type    | Required | Description |
| ----- | -------- | ------- | -------- | ----------- |
| id    | path     | integer | yes      | none        |
| token | header   | string  | yes      | none        |

> Response Examples

> 成功

```json
{
  "code": 200,
  "msg": "commodo ut aliqua",
  "status": "success",
  "data": {}
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema |
| ---------------- | ------------------------------------------------------- | ----------- | ----------- |
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功        | Inline      |

### Responses Data Schema

HTTP Status Code **200**

| Name     | Type    | Required | Restrictions | Title | description |
| -------- | ------- | -------- | ------------ | ----- | ----------- |
| » code   | integer | true     | none         |       | 状态码      |
| » msg    | string  | true     | none         |       | 提示信息    |
| » status | string  | true     | none         |       | 状态        |
| » data   | object  | false    | none         |       | 数据        |

#### Enum

| Name   | Value   |
| ------ | ------- |
| status | success |
| status | fail    |

# back-end/站点

## GET 查询所有站点

GET /site

### Params

| Name  | Location | Type   | Required | Description |
| ----- | -------- | ------ | -------- | ----------- |
| token | header   | string | yes      | none        |

> Response Examples

> 200 Response

```json
{
  "code": 0,
  "msg": "string",
  "status": "success",
  "data": [
    {
      "id": 0,
      "domain": "string",
      "upstreamServer": "string",
      "comment": "string",
      "createTime": "string",
      "updateTime": "string"
    }
  ]
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema |
| ---------------- | ------------------------------------------------------- | ----------- | ----------- |
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功        | Inline      |

### Responses Data Schema

HTTP Status Code **200**

| Name              | Type                  | Required | Restrictions | Title | description  |
| ----------------- | --------------------- | -------- | ------------ | ----- | ------------ |
| » code            | integer               | true     | none         |       | 状态码       |
| » msg             | string                | true     | none         |       | 提示信息     |
| » status          | string                | true     | none         |       | 状态         |
| » data            | [[Site](#schemasite)] | false    | none         |       | 数据         |
| »» id             | integer               | false    | none         |       | none         |
| »» domain         | string                | true     | none         |       | 域名         |
| »» upstreamServer | string                | true     | none         |       | 防护的服务器 |
| »» comment        | string                | false    | none         |       | 备注         |
| »» createTime     | string                | false    | none         |       | none         |
| »» updateTime     | string                | false    | none         |       | none         |

#### Enum

| Name   | Value   |
| ------ | ------- |
| status | success |
| status | fail    |

## POST 增加站点

POST /site

> Body Parameters

```json
{
  "domain": "string",
  "upstreamServer": "string",
  "comment": "string"
}
```

### Params

| Name             | Location | Type   | Required | Description  |
| ---------------- | -------- | ------ | -------- | ------------ |
| token            | header   | string | yes      | none         |
| body             | body     | object | no       | none         |
| » domain         | body     | string | yes      | 域名         |
| » upstreamServer | body     | string | yes      | 防护的服务器 |
| » comment        | body     | string | no       | 备注         |

> Response Examples

> 200 Response

```json
{
  "code": 0,
  "msg": "string",
  "status": "success",
  "data": {}
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema                         |
| ---------------- | ------------------------------------------------------- | ----------- | ----------------------------------- |
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功        | [BaseResponse](#schemabaseresponse) |

## PUT 更新站点

PUT /site

> Body Parameters

```json
{
  "id": 0,
  "domain": "string",
  "upstreamServer": "string",
  "comment": "string"
}
```

### Params

| Name             | Location | Type    | Required | Description  |
| ---------------- | -------- | ------- | -------- | ------------ |
| token            | header   | string  | yes      | none         |
| body             | body     | object  | no       | none         |
| » id             | body     | integer | yes      | none         |
| » domain         | body     | string  | yes      | 域名         |
| » upstreamServer | body     | string  | yes      | 防护的服务器 |
| » comment        | body     | string  | no       | 备注         |

> Response Examples

> 200 Response

```json
{
  "code": 0,
  "msg": "string",
  "status": "success",
  "data": {}
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema                         |
| ---------------- | ------------------------------------------------------- | ----------- | ----------------------------------- |
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功        | [BaseResponse](#schemabaseresponse) |

## PUT 修改站点的防护模式

PUT /site/{id}/{mode}

> Body Parameters

```json
{
  "id": 0,
  "method": 0
}
```

### Params

| Name     | Location | Type    | Required | Description                     |
| -------- | -------- | ------- | -------- | ------------------------------- |
| id       | path     | string  | yes      | none                            |
| mode     | path     | string  | yes      | none                            |
| token    | header   | string  | yes      | none                            |
| body     | body     | object  | no       | none                            |
| » id     | body     | integer | yes      | waf ID                          |
| » method | body     | integer | yes      | 防护模式， 0表示并行，1表示串行 |

> Response Examples

> 200 Response

```json
{}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema |
| ---------------- | ------------------------------------------------------- | ----------- | ----------- |
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功        | Inline      |

### Responses Data Schema

## DELETE 删除站点

DELETE /site/{id}

### Params

| Name  | Location | Type    | Required | Description |
| ----- | -------- | ------- | -------- | ----------- |
| id    | path     | integer | yes      | none        |
| token | header   | string  | yes      | none        |

> Response Examples

> 200 Response

```json
{
  "code": 0,
  "msg": "string",
  "status": "success",
  "data": {}
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema                         |
| ---------------- | ------------------------------------------------------- | ----------- | ----------------------------------- |
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功        | [BaseResponse](#schemabaseresponse) |

# back-end/请求信息

## GET 查询所有请求

GET /request

### Params

| Name     | Location | Type    | Required | Description |
| -------- | -------- | ------- | -------- | ----------- |
| pageNum  | query    | integer | no       | none        |
| pageSize | query    | integer | no       | none        |
| uuid     | query    | string  | no       | none        |
| token    | header   | string  | yes      | none        |

> Response Examples

> 成功

```json
{
  "code": 200,
  "msg": "操作成功",
  "status": "success",
  "data": {
    "pageNum": 1,
    "pageSize": 5,
    "list": [
      {
        "id": 1,
        "uuid": "3f656abe-f537-f50e-08bb-5c5c7af4a57c",
        "method": "GET",
        "sourceIp": "89.47.252.22",
        "url": "http://video.tamsw8.biz/IndustrialScientificSupplies",
        "mode": 1,
        "startTime": "2023-11-04T20:45:46",
        "time": 7803,
        "status": "0"
      },
      {
        "id": 2,
        "uuid": "8ec03057-0ff8-9906-0184-e70b6c3d68c8",
        "method": "DELETE",
        "sourceIp": "6.7.1.0",
        "url": "http://drive.sato8.co.jp/Books",
        "mode": 0,
        "startTime": "2023-11-02T06:45:53",
        "time": 4623,
        "status": "1"
      },
      {
        "id": 3,
        "uuid": "a3a22dbe-2ed1-8bd4-ddc8-d3bfd3a8cebd",
        "method": "DELETE",
        "sourceIp": "7.38.253.78",
        "url": "http://auth.jonathanw.xyz/CellPhonesAccessories",
        "mode": 1,
        "startTime": "2023-11-03T08:44:47",
        "time": 5861,
        "status": "0"
      },
      {
        "id": 4,
        "uuid": "e328f2a9-f5ac-19ff-287f-9c73cd442029",
        "method": "PATCH",
        "sourceIp": "69.255.254.219",
        "url": "http://drive.syng.info/ToolsHomeDecoration",
        "mode": 0,
        "startTime": "2023-11-01T17:29:09",
        "time": 7167,
        "status": "0"
      },
      {
        "id": 5,
        "uuid": "f7dab3cb-a715-893b-d501-b5db2d975c6e",
        "method": "GET",
        "sourceIp": "250.181.55.119",
        "url": "https://image.shizita83.cn/ToolsHomeDecoration",
        "mode": 0,
        "startTime": "2023-11-04T16:46:52",
        "time": 2349,
        "status": "1"
      }
    ],
    "total": 100
  }
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema |
| ---------------- | ------------------------------------------------------- | ----------- | ----------- |
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功        | Inline      |

### Responses Data Schema

HTTP Status Code **200**

| Name          | Type     | Required | Restrictions | Title | description |
| ------------- | -------- | -------- | ------------ | ----- | ----------- |
| » code        | integer  | true     | none         |       | 状态码      |
| » msg         | string   | true     | none         |       | 提示信息    |
| » status      | string   | true     | none         |       | 状态        |
| » data        | object   | false    | none         |       | 数据        |
| »» pageNum    | integer  | true     | none         |       | 第几页      |
| »» pageSize   | integer  | true     | none         |       | 页面大小    |
| »» list       | [object] | true     | none         |       | 查询信息    |
| »»» id        | integer  | true     | none         |       | id          |
| »»» method    | string   | true     | none         |       | 请求方式    |
| »»» sourceIp  | string   | true     | none         |       | 源IP        |
| »»» startTime | string   | true     | none         |       | 请求时间    |
| »»» time      | integer  | true     | none         |       | 请求时间    |
| »»» status    | string   | true     | none         |       | 请求状态    |
| »» total      | integer  | true     | none         |       | 总数        |

#### Enum

| Name   | Value   |
| ------ | ------- |
| status | success |
| status | fail    |

## GET 查询请求的调度记录

GET /request/schedule/{requestId}

### Params

| Name      | Location | Type    | Required | Description |
| --------- | -------- | ------- | -------- | ----------- |
| requestId | path     | integer | yes      | none        |
| token     | header   | string  | yes      | none        |

> Response Examples

> 成功

```json
{
  "code": 200,
  "msg": "操作成功",
  "status": "success",
  "data": {
    "mode": 1,
    "request": [
      {
        "id": 104,
        "status": "0",
        "time": 843,
        "wafId": 3,
        "requestId": 1
      },
      {
        "id": 108,
        "status": "1",
        "time": 655,
        "wafId": 4,
        "requestId": 1
      },
      {
        "id": 155,
        "status": "1",
        "time": 418,
        "wafId": 1,
        "requestId": 1
      }
    ]
  }
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema |
| ---------------- | ------------------------------------------------------- | ----------- | ----------- |
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功        | Inline      |

### Responses Data Schema

HTTP Status Code **200**

| Name           | Type                                      | Required | Restrictions | Title | description |
| -------------- | ----------------------------------------- | -------- | ------------ | ----- | ----------- |
| » code         | integer                                   | true     | none         |       | 状态码      |
| » msg          | string                                    | true     | none         |       | 提示信息    |
| » status       | string                                    | true     | none         |       | 状态        |
| » data         | object                                    | false    | none         |       | 数据        |
| »» mode        | integer                                   | true     | none         |       | 模式        |
| »» request     | [[ScheduleRecord](#schemaschedulerecord)] | true     | none         |       | 请求        |
| »»» id         | integer                                   | true     | none         |       | none        |
| »»» time       | integer                                   | true     | none         |       | 请求时间    |
| »»» pass       | integer                                   | true     | none         |       | 是否放行    |
| »»» wafId      | integer                                   | true     | none         |       | waf的id     |
| »»» requestId  | integer                                   | true     | none         |       | 请求id      |
| »»» createTime | string                                    | true     | none         |       | none        |
| »»» updateTime | string                                    | true     | none         |       | none        |

#### Enum

| Name   | Value   |
| ------ | ------- |
| status | success |
| status | fail    |

# back-end/统计数据

## GET 请求统计

GET /stat/traffic

统计时间待定

### Params

| Name  | Location | Type   | Required | Description |
| ----- | -------- | ------ | -------- | ----------- |
| token | header   | string | yes      | none        |

> Response Examples

> 成功

```json
{
  "code": 13,
  "msg": "Excepteur ad eu",
  "status": "success",
  "data": {
    "total": 30,
    "malicious": 12
  }
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema |
| ---------------- | ------------------------------------------------------- | ----------- | ----------- |
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功        | Inline      |

### Responses Data Schema

HTTP Status Code **200**

| Name         | Type    | Required | Restrictions | Title | description |
| ------------ | ------- | -------- | ------------ | ----- | ----------- |
| » code       | integer | true     | none         |       | 状态码      |
| » msg        | string  | true     | none         |       | 提示信息    |
| » status     | string  | true     | none         |       | 状态        |
| » data       | object  | false    | none         |       | 数据        |
| »» total     | integer | true     | none         |       | 总请求      |
| »» malicious | integer | true     | none         |       | 恶意请求    |

#### Enum

| Name   | Value   |
| ------ | ------- |
| status | success |
| status | fail    |

## GET 请求时间统计

GET /stat/time

计算一分钟之内的请求处理时间，如果没有访问数据，返回上一次返回的数据

### Params

| Name  | Location | Type   | Required | Description |
| ----- | -------- | ------ | -------- | ----------- |
| token | header   | string | yes      | none        |

> Response Examples

> 成功

```json
{
  "code": 40,
  "msg": "exercitation aute do",
  "status": "success",
  "data": {
    "time": 1574
  }
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema |
| ---------------- | ------------------------------------------------------- | ----------- | ----------- |
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功        | Inline      |

### Responses Data Schema

HTTP Status Code **200**

| Name     | Type    | Required | Restrictions | Title | description      |
| -------- | ------- | -------- | ------------ | ----- | ---------------- |
| » code   | integer | true     | none         |       | 状态码           |
| » msg    | string  | true     | none         |       | 提示信息         |
| » status | string  | true     | none         |       | 状态             |
| » data   | integer | false    | none         |       | 平均请求响应时间 |

#### Enum

| Name   | Value   |
| ------ | ------- |
| status | success |
| status | fail    |

# back-end/规则

## GET 获取防护规则信息（黑白名单）

GET /rule

### Params

| Name  | Location | Type   | Required | Description |
| ----- | -------- | ------ | -------- | ----------- |
| token | header   | string | yes      | none        |

> Response Examples

> 200 Response

```json
{
  "code": 0,
  "msg": "string",
  "status": "success",
  "data": [
    {}
  ]
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema |
| ---------------- | ------------------------------------------------------- | ----------- | ----------- |
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功        | Inline      |

### Responses Data Schema

HTTP Status Code **200**

| Name     | Type     | Required | Restrictions | Title | description |
| -------- | -------- | -------- | ------------ | ----- | ----------- |
| » code   | integer  | true     | none         |       | 状态码      |
| » msg    | string   | true     | none         |       | 提示信息    |
| » status | string   | true     | none         |       | 状态        |
| » data   | [object] | false    | none         |       | 数据        |

#### Enum

| Name   | Value   |
| ------ | ------- |
| status | success |
| status | fail    |

# scheduler/WAF

## DELETE 删除waf

DELETE /waf/{id}

### Params

| Name | Location | Type    | Required | Description |
| ---- | -------- | ------- | -------- | ----------- |
| id   | path     | integer | yes      | none        |

> Response Examples

> 200 Response

```json
{
  "code": 0,
  "msg": "string",
  "status": "success",
  "data": {}
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema                         |
| ---------------- | ------------------------------------------------------- | ----------- | ----------------------------------- |
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功        | [BaseResponse](#schemabaseresponse) |

# scheduler/镜像

## DELETE 移除镜像

DELETE /image

### Params

| Name  | Location | Type    | Required | Description |
| ----- | -------- | ------- | -------- | ----------- |
| id    | query    | integer | yes      | 镜像ID      |
| token | header   | string  | yes      | none        |

> Response Examples

> 成功

```json
{
  "code": 200,
  "msg": "commodo ut aliqua",
  "status": "success",
  "data": {}
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema |
| ---------------- | ------------------------------------------------------- | ----------- | ----------- |
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | 成功        | Inline      |

### Responses Data Schema

HTTP Status Code **200**

| Name     | Type    | Required | Restrictions | Title | description |
| -------- | ------- | -------- | ------------ | ----- | ----------- |
| » code   | integer | true     | none         |       | 状态码      |
| » msg    | string  | true     | none         |       | 提示信息    |
| » status | string  | true     | none         |       | 状态        |
| » data   | object  | false    | none         |       | 数据        |

#### Enum

| Name   | Value   |
| ------ | ------- |
| status | success |
| status | fail    |

# Data Schema

<h2 id="tocS_Site">Site</h2>

<a id="schemasite"></a>
<a id="schema_Site"></a>
<a id="tocSsite"></a>
<a id="tocssite"></a>

```json
{
  "id": 0,
  "domain": "string",
  "upstreamServer": "string",
  "comment": "string",
  "createTime": "string",
  "updateTime": "string"
}

```

### Attribute

| Name           | Type    | Required | Restrictions | Title | Description  |
| -------------- | ------- | -------- | ------------ | ----- | ------------ |
| id             | integer | false    | none         |       | none         |
| domain         | string  | true     | none         |       | 域名         |
| upstreamServer | string  | true     | none         |       | 防护的服务器 |
| comment        | string  | false    | none         |       | 备注         |
| createTime     | string  | false    | none         |       | none         |
| updateTime     | string  | false    | none         |       | none         |

<h2 id="tocS_Image">Image</h2>

<a id="schemaimage"></a>
<a id="schema_Image"></a>
<a id="tocSimage"></a>
<a id="tocsimage"></a>

```json
{
  "id": 0,
  "name": "string",
  "tag": "string",
  "imageId": "string",
  "createTime": "string",
  "updateTime": "string"
}

```

### Attribute

| Name       | Type    | Required | Restrictions | Title | Description |
| ---------- | ------- | -------- | ------------ | ----- | ----------- |
| id         | integer | true     | none         |       | none        |
| name       | string  | true     | none         |       | 镜像名      |
| tag        | string  | true     | none         |       | 镜像标签    |
| imageId    | string  | true     | none         |       | 镜像id      |
| createTime | string  | true     | none         |       | none        |
| updateTime | string  | true     | none         |       | none        |

<h2 id="tocS_WafMonitor">WafMonitor</h2>

<a id="schemawafmonitor"></a>
<a id="schema_WafMonitor"></a>
<a id="tocSwafmonitor"></a>
<a id="tocswafmonitor"></a>

```json
{
  "id": 0,
  "cpu": "string",
  "memory": "string",
  "wafId": 0,
  "createTime": "string",
  "updateTime": "string"
}

```

### Attribute

| Name       | Type    | Required | Restrictions | Title | Description |
| ---------- | ------- | -------- | ------------ | ----- | ----------- |
| id         | integer | true     | none         |       | id          |
| cpu        | string  | true     | none         |       | cpu占用率   |
| memory     | string  | true     | none         |       | 内存占用率  |
| wafId      | integer | true     | none         |       | wafId       |
| createTime | string  | true     | none         |       | none        |
| updateTime | string  | true     | none         |       | none        |

<h2 id="tocS_ScheduleRecord">ScheduleRecord</h2>

<a id="schemaschedulerecord"></a>
<a id="schema_ScheduleRecord"></a>
<a id="tocSschedulerecord"></a>
<a id="tocsschedulerecord"></a>

```json
{
  "id": 0,
  "time": 0,
  "pass": 0,
  "wafId": 0,
  "requestId": 0,
  "createTime": "string",
  "updateTime": "string"
}

```

### Attribute

| Name       | Type    | Required | Restrictions | Title | Description |
| ---------- | ------- | -------- | ------------ | ----- | ----------- |
| id         | integer | true     | none         |       | none        |
| time       | integer | true     | none         |       | 请求时间    |
| pass       | integer | true     | none         |       | 是否放行    |
| wafId      | integer | true     | none         |       | waf的id     |
| requestId  | integer | true     | none         |       | 请求id      |
| createTime | string  | true     | none         |       | none        |
| updateTime | string  | true     | none         |       | none        |

<h2 id="tocS_RequestResult">RequestResult</h2>

<a id="schemarequestresult"></a>
<a id="schema_RequestResult"></a>
<a id="tocSrequestresult"></a>
<a id="tocsrequestresult"></a>

```json
{
  "uuid": "095be615-a8ad-4c33-8e9c-c7612fbf6c9f",
  "method": "string",
  "srcIp": "string",
  "desIp": "string",
  "url": "string",
  "mode": 0,
  "time": 0,
  "pass": 0,
  "createTime": "string",
  "updateTime": "string"
}

```

### Attribute

| Name       | Type         | Required | Restrictions | Title | Description    |
| ---------- | ------------ | -------- | ------------ | ----- | -------------- |
| uuid       | string(uuid) | true     | none         |       | 请求的唯一标识 |
| method     | string       | true     | none         |       | 请求方式       |
| srcIp      | string       | true     | none         |       | 源IP           |
| desIp      | string       | true     | none         |       | 目的IP         |
| url        | string       | true     | none         |       | 请求路径       |
| mode       | integer      | true     | none         |       | 调度方式       |
| time       | integer      | true     | none         |       | 请求处理时间   |
| pass       | integer      | true     | none         |       | 是否放行       |
| createTime | string       | true     | none         |       | none           |
| updateTime | string       | true     | none         |       | none           |

<h2 id="tocS_RequestChainResult">RequestChainResult</h2>

<a id="schemarequestchainresult"></a>
<a id="schema_RequestChainResult"></a>
<a id="tocSrequestchainresult"></a>
<a id="tocsrequestchainresult"></a>

```json
{
  "time": 0,
  "pass": 0,
  "wafId": 0,
  "createTime": "string",
  "updateTime": "string",
  "children": [
    {
      "time": 0,
      "pass": 0,
      "wafId": 0,
      "createTime": "string",
      "updateTime": "string",
      "children": [
        {
          "time": 0,
          "pass": 0,
          "wafId": 0,
          "createTime": "string",
          "updateTime": "string",
          "children": [
            {}
          ]
        }
      ]
    }
  ]
}

```

### Attribute

| Name       | Type                                              | Required | Restrictions | Title | Description |
| ---------- | ------------------------------------------------- | -------- | ------------ | ----- | ----------- |
| time       | integer                                           | true     | none         |       | 请求时间    |
| pass       | integer                                           | true     | none         |       | 是否放行    |
| wafId      | integer                                           | true     | none         |       | waf的id     |
| createTime | string                                            | true     | none         |       | none        |
| updateTime | string                                            | true     | none         |       | none        |
| children   | [[RequestChainResult](#schemarequestchainresult)] | true     | none         |       | none        |

<h2 id="tocS_RequestRecord">RequestRecord</h2>

<a id="schemarequestrecord"></a>
<a id="schema_RequestRecord"></a>
<a id="tocSrequestrecord"></a>
<a id="tocsrequestrecord"></a>

```json
{
  "id": 0,
  "uuid": "095be615-a8ad-4c33-8e9c-c7612fbf6c9f",
  "method": "string",
  "srcIp": "string",
  "desIp": "string",
  "url": "string",
  "mode": 0,
  "time": 0,
  "pass": 0,
  "createTime": "string",
  "updateTime": "string"
}

```

### Attribute

| Name       | Type         | Required | Restrictions | Title | Description    |
| ---------- | ------------ | -------- | ------------ | ----- | -------------- |
| id         | integer      | true     | none         |       | id             |
| uuid       | string(uuid) | true     | none         |       | 请求的唯一标识 |
| method     | string       | true     | none         |       | 请求方式       |
| srcIp      | string       | true     | none         |       | 源IP           |
| desIp      | string       | true     | none         |       | 目的IP         |
| url        | string       | true     | none         |       | 请求路径       |
| mode       | integer      | true     | none         |       | 调度方式       |
| time       | integer      | true     | none         |       | 请求处理时间   |
| pass       | integer      | true     | none         |       | 是否放行       |
| createTime | string       | true     | none         |       | none           |
| updateTime | string       | true     | none         |       | none           |

<h2 id="tocS_UserInfo">UserInfo</h2>

<a id="schemauserinfo"></a>
<a id="schema_UserInfo"></a>
<a id="tocSuserinfo"></a>
<a id="tocsuserinfo"></a>

```json
{
  "id": 0,
  "username": "string",
  "email": "user@example.com",
  "status": "string",
  "roles": [
    "string"
  ],
  "permissions": [
    "string"
  ]
}

```

用户信息

### Attribute

| Name        | Type          | Required | Restrictions | Title | Description |
| ----------- | ------------- | -------- | ------------ | ----- | ----------- |
| id          | integer       | true     | none         |       | id          |
| username    | string        | true     | none         |       | 用户名      |
| email       | string(email) | true     | none         |       | 邮箱        |
| status      | string        | true     | none         |       | 状态        |
| roles       | [string]      | true     | none         |       | 角色信息    |
| permissions | [string]      | true     | none         |       | 权限信息    |

<h2 id="tocS_LoginRequest">LoginRequest</h2>

<a id="schemaloginrequest"></a>
<a id="schema_LoginRequest"></a>
<a id="tocSloginrequest"></a>
<a id="tocsloginrequest"></a>

```json
{
  "username": "string",
  "password": "pa$$word",
  "verifyCode": "string"
}

```

登录信息

### Attribute

| Name       | Type             | Required | Restrictions | Title | Description |
| ---------- | ---------------- | -------- | ------------ | ----- | ----------- |
| username   | string           | true     | none         |       | 用户名      |
| password   | string(password) | true     | none         |       | 密码        |
| verifyCode | string           | true     | none         |       | 验证码      |

<h2 id="tocS_Waf">Waf</h2>

<a id="schemawaf"></a>
<a id="schema_Waf"></a>
<a id="tocSwaf"></a>
<a id="tocswaf"></a>

```json
{
  "id": 0,
  "name": "string",
  "ip": "192.168.0.1",
  "port": 65535,
  "configUrl": "string",
  "status": 0,
  "type": 0,
  "weight": 0,
  "description": "string",
  "imageId": 0,
  "containerId": "string",
  "createTime": "string",
  "updateTime": "string"
}

```

waf

### Attribute

| Name        | Type         | Required | Restrictions | Title | Description                  |
| ----------- | ------------ | -------- | ------------ | ----- | ---------------------------- |
| id          | integer      | true     | none         |       | id                           |
| name        | string       | true     | none         |       | waf名称                      |
| ip          | string(ipv4) | true     | none         |       | ip地址                       |
| port        | integer      | true     | none         |       | 端口号                       |
| configUrl   | string       | true     | none         |       | 配置地址                     |
| status      | integer      | true     | none         |       | 是否上线（1：上线，0：下线） |
| type        | integer      | true     | none         |       | 0 docker 1 软件 2 其他       |
| weight      | integer      | true     | none         |       | 权重                         |
| description | string¦null  | false    | none         |       | 描述信息                     |
| imageId     | integer      | true     | none         |       | docker形式的waf的镜像id      |
| containerId | string       | true     | none         |       | docker形式的waf的容器id      |
| createTime  | string       | true     | none         |       | none                         |
| updateTime  | string       | true     | none         |       | none                         |

<h2 id="tocS_Perm">Perm</h2>

<a id="schemaperm"></a>
<a id="schema_Perm"></a>
<a id="tocSperm"></a>
<a id="tocsperm"></a>

```json
{
  "id": 0,
  "name": "string",
  "perm": "string"
}

```

权限

### Attribute

| Name | Type    | Required | Restrictions | Title | Description |
| ---- | ------- | -------- | ------------ | ----- | ----------- |
| id   | integer | true     | none         |       | id          |
| name | string  | true     | none         |       | 权限名      |
| perm | string  | true     | none         |       | 权限        |

<h2 id="tocS_Role">Role</h2>

<a id="schemarole"></a>
<a id="schema_Role"></a>
<a id="tocSrole"></a>
<a id="tocsrole"></a>

```json
{
  "id": 0,
  "name": "string",
  "roleKey": "string"
}

```

角色

### Attribute

| Name    | Type    | Required | Restrictions | Title | Description |
| ------- | ------- | -------- | ------------ | ----- | ----------- |
| id      | integer | true     | none         |       | id          |
| name    | string  | true     | none         |       | 角色名      |
| roleKey | string  | true     | none         |       | 角色关键词  |

<h2 id="tocS_User">User</h2>

<a id="schemauser"></a>
<a id="schema_User"></a>
<a id="tocSuser"></a>
<a id="tocsuser"></a>

```json
{
  "id": 0,
  "username": "string",
  "password": "pa$$word",
  "email": "user@example.com",
  "status": "string"
}

```

角色

### Attribute

| Name     | Type             | Required | Restrictions | Title | Description |
| -------- | ---------------- | -------- | ------------ | ----- | ----------- |
| id       | integer          | true     | none         |       | id          |
| username | string           | true     | none         |       | 用户名      |
| password | string(password) | true     | none         |       | 密码        |
| email    | string(email)    | true     | none         |       | 邮箱        |
| status   | string           | true     | none         |       | 状态        |

<h2 id="tocS_PageReuslt">PageReuslt</h2>

<a id="schemapagereuslt"></a>
<a id="schema_PageReuslt"></a>
<a id="tocSpagereuslt"></a>
<a id="tocspagereuslt"></a>

```json
{
  "pageNum": 1,
  "pageSize": 1,
  "list": [
    "string"
  ],
  "total": 0
}

```

查询返回

### Attribute

| Name     | Type     | Required | Restrictions | Title | Description |
| -------- | -------- | -------- | ------------ | ----- | ----------- |
| pageNum  | integer  | true     | none         |       | 第几页      |
| pageSize | integer  | true     | none         |       | 页面大小    |
| list     | [string] | true     | none         |       | 查询信息    |
| total    | integer  | true     | none         |       | 总数        |

<h2 id="tocS_BaseResponse">BaseResponse</h2>

<a id="schemabaseresponse"></a>
<a id="schema_BaseResponse"></a>
<a id="tocSbaseresponse"></a>
<a id="tocsbaseresponse"></a>

```json
{
  "code": 0,
  "msg": "string",
  "status": "success",
  "data": {}
}

```

响应

### Attribute

| Name   | Type    | Required | Restrictions | Title | Description |
| ------ | ------- | -------- | ------------ | ----- | ----------- |
| code   | integer | true     | none         |       | 状态码      |
| msg    | string  | true     | none         |       | 提示信息    |
| status | string  | true     | none         |       | 状态        |
| data   | object  | false    | none         |       | 数据        |

#### Enum

| Name   | Value   |
| ------ | ------- |
| status | success |
| status | fail    |

