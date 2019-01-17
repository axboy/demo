# Influxdb

Influxdb是一个开源的时序型数据库，使用Go语言编写，被广泛应用于存储系统的监控数据、IoT行业的实时数据等场景。
有以下特性：

- 部署简单，无外部依赖

- 内置http支持，使用http读写

- 类sql的灵活查询(max,min,sum等)

### 关键概念

- database: 数据库

- measurement: 表

- points: 一行数据

    - tags: 各种有索引的属性
    
    - fields: 各种记录的值
    
    - time: 数据记录的时间戳，自动建索引
