package cn.axboy.demo.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/4/23 17:50
 */
public class WsHandler extends SimpleChannelInboundHandler {


    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            //处理http握手
            handHttpRequest(ctx, (FullHttpRequest) msg);
        }
        if (msg instanceof WebSocketFrame) {
            //处理ws连接业务
        }
    }

    private void handHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {
        if(!req.getDecoderResult())
    }

    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse resp) {
        if(resp.getStatus())
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //super.channelActive(ctx);
        NettyConfig.group.add(ctx.channel());
        System.out.println("ChannelActive");
    }

    //Client disconnect
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //super.channelInactive(ctx);
        NettyConfig.group.remove(ctx.channel());
        System.out.println("ChannelInactive");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //super.channelReadComplete(ctx);
        ctx.flush();
        System.out.println("read complete");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        ctx.close();
        System.out.println("caught Exception");
    }
}
