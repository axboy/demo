package cn.axboy.demo.netty;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/4/23 17:48
 * 存储整个工程的全局变量
 */
public class NettyConfig {

    //存储每一个客户端接入的配置对象
    public static ChannelGroup group  = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
}
