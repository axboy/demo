package cn.axboy.demo.interview.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/5/10 15:55
 */
public class AioServer {

    public AioServer(int port) throws IOException {
        AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(port));
        System.out.println("Listening on " + serverSocketChannel.getLocalAddress());
        serverSocketChannel.accept(this, new MyHandler());
    }

    public static void main(String[] args) throws IOException {
        new AioServer(8000);
    }

    private static class MyHandler implements CompletionHandler<AsynchronousSocketChannel, AioServer> {
        final ByteBuffer buffer = ByteBuffer.allocate(1024);

        @Override
        public void completed(AsynchronousSocketChannel result, AioServer attachment) {
            System.out.println(Thread.currentThread().getName());
            Future<Integer> writeResult = null;
            buffer.clear();
            try {
                result.read(buffer).get(100, TimeUnit.SECONDS);
                buffer.flip();
                writeResult = result.write(buffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            } finally {
                try {

                    writeResult.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void failed(Throwable exc, AioServer attachment) {

        }
    }
}
