# [thrift](http://thrift.apache.org/) demo

### 文档

- [download](http://thrift.apache.org/download)
- [github](https://github.com/apache/thrift)

### 安装

```sh
brew install bison
cd thrift-0.11.0
./configure
make
make install

### or

brew install thrift
```

### demo

- 创建thrift文件

```thrift
namespace java cn.axboy.demo.rpc.thrift.generate

service IHelloService {

    string say(1:string name)
}
```

- 生成代码

```sh
thrift -r -gen java Hello.thrift
```

- 实现接口

```java
public class HelloServiceImpl implements IHelloService.Iface {

    @Override
    public String say(String name) throws TException {
        if (name == null || name.length() == 0) {
            throw new TException("Error name.");
        }
        return String.format("Hello %s.", name);
    }
}
```

### 数据类型映射

- 基本类型

Type    |value          |java type
:-------|:--------------|:---------
bool    |true/false     |boolean
byte    |8位有符号整数    |byte
i16     |16位有符号整数   |short
i32     |32位有符号整数   |int
i64     |64位有符号整数   |long
double  |64位浮点数      |double
string  |文本、字符串     |String

- 容器类型

Type    |java type
:-------|:---------
list    |ArrayList
set     |HashSet
map     |HashMap

- 枚举类型

同java的enum

```
enum Color {
    RED,
    GREEN,
    BLUE
}
```

- struct类型

对象，类似c语言的结构体，java的类

```
struct User {
    1: i32 id;
    2: string name;
    3: i32 age;
    4: bool flag;
}
```

- union类型

同c中union类型

- exception类型

同java的Exception

```
exception DemoException {
　　1:i32 code;
　　2:string detail;
}
```

- service类型

定义接口

```
service IHelloService {

    string say(1:string name)
}
```

### 传输协议

- TBinaryProtocol

    使用二进制编码格式传输,是thrift的默认传输协议

- TCompactProtocol

    使用压缩格式传输

- TJSONProtocol

    使用JSON格式传输

- TDebugProtocol

    使用易懂可读的文本格式进行传输，以便于debug

- TSimpleJSONProtocol

    提供JSON只写的协议，适用于通过脚本语言解析

### 传输模式

- TSocket

    阻塞式IO的Transport实现,用在客户端.

- TServerSocket

    非阻塞式Socket,用于服务器端,用于监听TSocket.

- TNonblockingSocket

    非阻塞式IO的实现

- TMemoryInputTransport

    封装了一个字节数组byte[]来做输入流的封装

- TFramedTransport

    同样使用非阻塞方式，按块的大小进行传输,输入流封装了TMemoryInputTransport

### 服务模型

#### 阻塞服务

- TSimpleServer

    简单的单线程服务模型，主要用于测试

- TThreadPoolServer

    多线程服务模型，使用标准的阻塞式IO，预先创建一组线程处理请求

#### 非阻塞服务

- TNonblockingServer

    多线程服务模型，使用非阻塞式IO（需使用TFramedTransport数据传输方式）,只有一个线程来处理消息

- THsHaServer

    半同步半异步的服务模型，一个单独的线程用来处理网络I/O，一个worker线程池用来进行消息的处理

- TThreadedSelectorServer

    允许你用多个线程来处理网络I/O。它维护了两个线程池，一个用来处理网络I/O，另一个用来进行请求的处理

### 参考博客

- [Thrift协议的服务模型](https://blog.csdn.net/xuemengrui12/article/details/60876260)