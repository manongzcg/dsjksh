package cn.epi.common.utils;

import java.net.URLClassLoader;
import java.net.URL;
 
/**
 * @author     : jialin
 * @group      : THS_JAVA_PLATFORM
 * @Date       : 2014-10-25 涓婂崍10:56:43
 * @Comments   : 鑷畾涔夌被鍔犺浇鍣�
 * @Version    : 1.0.0
 */
public class DynamicClassLoader extends URLClassLoader {
    public DynamicClassLoader(ClassLoader parent) {
        super(new URL[0], parent);
    }
 
    public Class findClassByClassName(String className) throws ClassNotFoundException {
        return this.findClass(className);
    }
 
    public Class loadClass(String fullName, JavaClassObject jco) {
        byte[] classData = jco.getBytes();
        return this.defineClass(fullName, classData, 0, classData.length);
    }
}