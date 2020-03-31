package cn.axboy.demo.interview.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/3/28 00:00
 */
public class Server {

    public static void main(String[] args) throws IOException {
        RequestHandler requestHandler = new RequestHandler();
        final int port = 6666;

        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Listening on " + serverSocket.getLocalSocketAddress());

        while (true) {
            Socket client = serverSocket.accept();
            System.out.println("Incoming a connection from " + client.getRemoteSocketAddress());
            new ClientHandler(client, requestHandler).run();
        }
    }
}
