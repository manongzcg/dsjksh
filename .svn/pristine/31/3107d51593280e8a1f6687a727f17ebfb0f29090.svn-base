package cn.epi.common.utils;

import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import java.io.IOException;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.net.URI;
/**
 * @author     : jialin
 * @group      : THS_JAVA_PLATFORM
 * @Date       : 2014-10-25 涓婂崍10:49:07
 * @Comments   : 鐢ㄤ簬浼犻�掓簮绋嬪簭
 * @Version    : 1.0.0
 */
public class JavaClassObject extends SimpleJavaFileObject {
 
    protected final ByteArrayOutputStream bos =
        new ByteArrayOutputStream();
 
 
    public JavaClassObject(String name, JavaFileObject.Kind kind) {
        super(URI.create("string:///" + name.replace('.', '/')
            + kind.extension), kind);
    }
 
 
    public byte[] getBytes() {
        return bos.toByteArray();
    }
 
    @Override
    public OutputStream openOutputStream() throws IOException {
        return bos;
    }
}