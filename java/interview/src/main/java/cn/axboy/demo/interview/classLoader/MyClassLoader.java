package cn.axboy.demo.interview.classLoader;

import java.io.*;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/5/8 13:30
 * 自定义类加载器
 */
public class MyClassLoader extends ClassLoader {

    //要加载的Java类的classPath路径
    private String classPath;

    public MyClassLoader(String classPath) {
        super(ClassLoader.getSystemClassLoader());
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = this.loadClassData(name);
        //return super.findClass(name);
        return this.defineClass(name, data, 0, data.length);
    }

    //
    private byte[] loadClassData(String name) {
        try {
            name = name.replace(".", "/");
            FileInputStream fis = new FileInputStream(new File(classPath + name + ".class"));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int b = 0;
            while ((b = fis.read()) != -1) {
                baos.write(b);
            }
            fis.close();
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
