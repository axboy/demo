package cn.axboy.demo.interview.socket;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/3/28 00:29
 */
public class ClientHandler implements Runnable {

    private final Socket client;

    private final RequestHandler requestHandler;

    public ClientHandler(Socket client, RequestHandler requestHandler) {
        this.client = client;
        this.requestHandler = requestHandler;
    }

    public void run() {
        try {
            while (true) {
                Scanner input = new Scanner(client.getInputStream());
                String request = input.nextLine();
                System.out.format("Request from %s: %s\n", client.getRemoteSocketAddress(), request);

                if (request.equals("quit")) {
                    client.getOutputStream().write("> Bye bye\n".getBytes());
                    System.out.format("Client %s quit.\n", client.getRemoteSocketAddress());
                    client.close();
                    break;
                }

                String response = requestHandler.handle(request);
                client.getOutputStream().write(response.getBytes());
            }
        } catch (IOException e) {
            System.out.println("Caught exception: " + e);
            throw new RuntimeException(e);
        }
    }
}
