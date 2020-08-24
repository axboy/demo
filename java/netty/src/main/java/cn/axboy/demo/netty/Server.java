package cn.axboy.demo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/4/23 18:26
 */
public class Server {

    public static final int PORT = 8888;

    public static void main(String[] args) {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workGroup);

        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.childHandler(new WsChannelHandler());
        System.out.println("Server start");
        Channel channel = bootstrap.bind(PORT).sync().channel();
        channel.closeFuture().sync();

        bossGroup.shutdownGracefully();
        workGroup.shutdownGracefully();
    }
}
