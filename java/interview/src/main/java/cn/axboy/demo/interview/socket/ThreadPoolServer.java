package cn.axboy.demo.interview.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/3/28 00:07
 */
public class ThreadPoolServer {

    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        RequestHandler requestHandler = new RequestHandler();
        final int port = 7777;

        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Listening on " + serverSocket.getLocalSocketAddress());

        while (true) {
            Socket client = serverSocket.accept();
            System.out.println("Incoming a connection from " + client.getRemoteSocketAddress());
            executorService.submit(new ClientHandler(client, requestHandler));
        }
    }
}
