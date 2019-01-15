package cn.axboy.demo.rpc.thrift;

import cn.axboy.demo.rpc.thrift.generate.IHelloService;
import org.apache.thrift.TException;

import java.util.Date;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2019/1/14 15:16
 */
public class HelloServiceImpl implements IHelloService.Iface {

    @Override
    public String say(String name) throws TException {
        System.out.printf("%s <<< %s\n", new Date(), name);
        if (name == null || name.length() == 0) {
            throw new TException("Error name.");
        }
        return String.format("Hello %s.", name);
    }
}