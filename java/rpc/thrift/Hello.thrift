namespace java cn.axboy.demo.rpc.thrift.generate

service IHelloService {
    string say(1:string name)
}

struct User {
    1: i32 id;
    2: string name;
    3: i32 age;
    4: bool flag;
}

exception DemoException {
    1: i32 code;
    2: string detail;
}

enum Color {
    RED,
    GREEN,
    BLUE
}