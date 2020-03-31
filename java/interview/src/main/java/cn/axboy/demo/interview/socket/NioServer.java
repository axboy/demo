package cn.axboy.demo.interview.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/3/28 01:00
 */
public class NioServer {

    public static void main(String[] args) throws IOException {
        RequestHandler requestHandler = new RequestHandler();
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //定义serverChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(8888));
        System.out.println("Listening on " + serverSocketChannel.getLocalAddress());

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            int selected = selector.select();
            if (selected == 0) {
                continue;
            }
            //
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                keyIterator.remove();

                //对应serverChannel注册op_accept
                if (key.isAcceptable()) {
                    ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();

                    //把clientChannel注册到selector
                    SocketChannel clientChannel = serverChannel.accept();
                    clientChannel.configureBlocking(false);
                    clientChannel.register(selector, SelectionKey.OP_READ);
                    System.out.println("Incoming a connection from " + clientChannel.getRemoteAddress());
                }

                //对应clientChannel注册op_read
                if (key.isReadable()) {
                    SocketChannel clientChannel = (SocketChannel) key.channel();

                    //读取输入，清除缓冲区
                    clientChannel.read(buffer);
                    String request = new String(buffer.array(),
                            buffer.arrayOffset(), buffer.arrayOffset() + buffer.position()
                    ).trim();
                    buffer.clear();

                    System.out.format("Request from %s: %s\n", clientChannel.getRemoteAddress(), request);
                    if (request.equals("quit")) {
                        clientChannel.write(ByteBuffer.wrap("> Bye!\n".getBytes()));
                        clientChannel.finishConnect();
                        clientChannel.close();
                        continue;
                    }
                    String response = requestHandler.handle(request);
                    clientChannel.write(ByteBuffer.wrap(response.getBytes()));
                }
            }
        }
    }
}
