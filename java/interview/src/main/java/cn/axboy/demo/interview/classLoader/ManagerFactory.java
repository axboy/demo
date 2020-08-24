package cn.axboy.demo.interview.classLoader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/5/8 13:47
 * 加载manager的工厂
 */
public class ManagerFactory {

    private static final Map<String, LoadInfo> loadClassMap = new HashMap<>();
    public static final String CLASS_PATH = "/Users/zcw/Work/axboy.repo/demo/java/interview/target/classes/";

    //实现热加载的类的全名称
    public static final String MY_MANAGER = "cn.axboy.demo.interview.classLoader.MyManager";

    public static BaseManager getManager(String className) {
        File file = new File(CLASS_PATH + className.replaceAll("\\.", "/") + ".class");
        long lastModified = file.lastModified();
        if (loadClassMap.get(className) == null ||
                loadClassMap.get(className).getLoadTime() != lastModified) {
            load(className, lastModified);
        }
        return loadClassMap.get(className).getBaseManager();
    }

    private static void load(String className, long lastModified) {
        ClassLoader classLoader = new MyClassLoader(CLASS_PATH);
        Class<?> loadClass = null;
        try {
            loadClass = classLoader.loadClass(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        BaseManager manager = newInstance(loadClass);
        LoadInfo loadInfo = new LoadInfo(classLoader, lastModified);
        loadInfo.setBaseManager(manager);
        loadClassMap.put(className, loadInfo);
    }

    private static BaseManager newInstance(Class<?> loadClass) {
        try {
            return (BaseManager) loadClass.getConstructor(new Class[]{}).newInstance(new Object[]{});
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
