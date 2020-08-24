# activemq demo

- docker启动
```sh
docker run -d --name activemq \
    -p 8161:8161 \
    -p 61616:61616 \
    -e ACTIVEMQ_ADMIN_LOGIN=admin \
    -e ACTIVEMQ_ADMIN_PASSWORD=admin \
    webcenter/activemq

iptables -I INPUT -p TCP --dport 8161 -j ACCEPT
iptables -I INPUT -p TCP --dport 16161 -j ACCEPT
```

- [参考文章](https://blog.csdn.net/qq_26975307/article/details/100705355)