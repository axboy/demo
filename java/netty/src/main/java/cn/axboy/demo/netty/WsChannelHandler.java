package cn.axboy.demo.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/4/23 18:23
 */
public class WsChannelHandler extends ChannelInitializer<SocketChannel> {

    public static final int MAX_LENGTH = 65536;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast("http-codec", new HttpServerCodec());
        socketChannel.pipeline().addLast("aggregator", new HttpObjectAggregator(MAX_LENGTH));
        socketChannel.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
        socketChannel.pipeline().addLast("hhandler", new WsHandler());
    }
}
