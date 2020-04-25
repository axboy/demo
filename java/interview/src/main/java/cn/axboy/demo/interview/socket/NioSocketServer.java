package cn.axboy.demo.interview.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/4/25 20:50
 */
public class NioSocketServer {

    public static void main(String[] args) throws IOException {
        RequestHandler requestHandler = new RequestHandler();
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        List<SocketChannel> clientList = new ArrayList<>();
        //定义serverChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(8888));
        System.out.println("Listening on " + serverSocketChannel.getLocalAddress());

        while (true) {
            SocketChannel client = serverSocketChannel.accept();// 不会阻塞
            if (client != null) {
                client.configureBlocking(false);
                clientList.add(client);
                System.out.println("new client: " + client.socket().getRemoteSocketAddress());
            } else {
                System.out.println("Client is null...");
            }
            //单线程串行处理
            for (SocketChannel c : clientList) {
                int num = c.read(buffer);
                if (num > 0) {
                    new ClientHandler(c.socket(), requestHandler).run();
                }
            }
        }
    }
}
