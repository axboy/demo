# Android 7+ Charles https抓包问题

## 手机抓包的一般操作

1. 下载并安装根证书

```
Help -> SSL Proxying -> Save charles Root Certificate
```

将会保存一个pem文件，想办法弄到手机上安装，部分手机需要换成crt格式，重命名即可。

2. 打开代理

```
Proxy -> Proxy Settings
```

打开http代理，默认端口8888

3. Https代理配置

```
Proxy -> SSL Proxying Settings
```

打开ssl代理，并添加对应的规则，*为通配符

4. 手机代理设置

在前面的步骤已经装了证书了，打开手机wifi设置，高级选项，手动代理，配置对应的代理，电脑ip和端口。

配置完成，开始使用吧，上述操作仅限android7.0以下，不包含7.0。

## 7.0+的通用操作

1. 新建src/main/res/xml/network_security_config.xml文件，文件名随意，内容如下。

```xml
<?xml version="1.0" encoding="utf-8"?>
<network-security-config>
    <base-config>
        <trust-anchors>
            <certificates src="system" overridePins="true"/>
            <certificates src="user" overridePins="true" />
        </trust-anchors>
    </base-config>
</network-security-config>
```

上面内容为为通用操作，信任一切（系统根证书和用户安装的根证书）

2. 修改AndroidManifest.xml文件

application上增加一行

```
android:networkSecurityConfig="@xml/network_security_config"
```

重新打包app，安装根证书即可使用

## 7.0+的自定义操作

1. 将基本操作里的证书文件复制到src/main/res/raw/下，文件名随意

2. 上述network_security_config.xml文件内容酌情修改

```xml
<?xml version="1.0" encoding="utf-8"?>
<network-security-config>
    <domain-config>
        <domain includeSubdomains="true">axboy.cn</domain>
        <domain includeSubdomains="true">github.io</domain>
        <trust-anchors>
            <!--信任系统证书-->
            <certificates src="system" overridePins="true"/>

            <!--信任用户安装的根证书，这里注释，不信任-->
            <!--<certificates src="user" overridePins="true" />-->

            <!--信任指定证书-->
            <certificates src="@raw/charles"/>
        </trust-anchors>
    </domain-config>
</network-security-config>
```

只信任系统根证书和自己指定的证书，并限定域名，
这样的好处是用户使用系统证书能正常使用，我们自己可以使用指定证书抓包，但别的开发者没有私钥，不能用。
清单文件和上面一样增加一行配置，重新打包app即可，由于包中指定了根证书，手机上可不用再安装根证书。

## 自定义进阶

按照上述自定义操作后有一个问题，我们需要抓包的时候，只能使用自己的电脑，证书是从我们电脑导出来的。
如果在团队或者公司里，或者电脑重装系统了，这就有问题了。
所以我们需要把私钥导出来，给有权限有需要的人使用，本项目的私钥放为根目录下的charles-ssl-proxying.p12文件，供各位测试，密码为123。

- 怎么用

Proxy -> SSL Proxying Settings -> Root Certificate

选择对应的文件，输入密码，保存即可。

ps: 若不可用，重启charles，这个大坑，在这里试了一天，最后发现是charles没有实时生效，调试的时候时好时坏，然后导致不清楚哪一步是对的。

若想免密，可参考文档3

## 参考文档

1. [Charles proxy](https://www.charlesproxy.com/documentation/using-charles/ssl-certificates/)

1. [Android developer](https://developer.android.com/training/articles/security-config)

1. [Custom SSL Certificate With Charles Web Proxy](http://codeblog.shape.dk/blog/2014/01/06/custom-ssl-certificate-with-charles-web-proxy/)