## jpa,jooq,mybatis混合使用测试

```text
===> jpa update before
jpa: 96
jooq: 96
mybatis: 96
===> jpa update after
jpa: 95
jooq: 95
mybatis: 96
------------------------------
===> jooq update before
jpa: 95
jooq: 95
mybatis: 95
===> jooq update after
jpa: 95
jooq: 94
mybatis: 95
------------------------------
===> mybatis update before
jpa: 94
jooq: 94
mybatis: 94
===> mybatis update after
jpa: 94
jooq: 93
mybatis: 93
```