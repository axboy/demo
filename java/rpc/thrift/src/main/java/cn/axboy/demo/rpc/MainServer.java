package cn.axboy.demo.rpc;

import cn.axboy.demo.rpc.thrift.HelloServiceImpl;
import cn.axboy.demo.rpc.thrift.generate.IHelloService;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2019/1/14 15:17
 */
public class MainServer {

    private static final int PORT = 8080;

    public static void main(String[] args) {
        MainServer.start();
    }

    private static void start() {
        System.out.println("Starting Thrift Server......");
        try {
            //1. 创建TProcessor
            TProcessor processor = new IHelloService.Processor<IHelloService.Iface>(new HelloServiceImpl());

            //2. 创建TServerTransport
            TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(PORT);

            //3. 创建TProtocol
            TThreadedSelectorServer.Args tArgs = new TThreadedSelectorServer.Args(serverTransport);
            tArgs.protocolFactory(new TBinaryProtocol.Factory());
            tArgs.transportFactory(new TFramedTransport.Factory());
            tArgs.processor(processor);

            //4. 创建TServer
            TServer server = new TThreadedSelectorServer(tArgs);

            //5. 启动Server
            server.serve();
        } catch (TTransportException e) {
            System.out.printf("Starting Thrift Server......Error!!!\n%s\n", e);
        }
    }
}
