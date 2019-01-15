package cn.axboy.demo.rpc;

import cn.axboy.demo.rpc.thrift.generate.IHelloService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2019/1/14 下午3:51
 * TODO
 */
public class MainClient {

    private static final String HOST = "localhost";
    private static final int PORT = 8080;
    private static final int TIMEOUT = 5000;

    public static void main(String[] args) {
        //1. 创建Transport
        TTransport transport = new TFramedTransport(new TSocket(HOST, PORT, TIMEOUT));

        //2. 创建TProtocol
        TProtocol protocol = new TBinaryProtocol(transport);

        //3. 基于Transport和TProtocol创建Client
        IHelloService.Client client = new IHelloService.Client(protocol);

        //4. 调用方法
        try {
            transport.open();
            String resp = client.say("zcw");
            System.out.printf(">>> %s\n", resp);
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            transport.close();
        }
    }
}
