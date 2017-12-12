package cn.epi.common.utils;

import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import java.net.URI;
 
/**
 * @author     : jialin
 * @group      : THS_JAVA_PLATFORM
 * @Date       : 2014-10-25 涓婂崍10:57:54
 * @Comments   : 鐢ㄤ簬浼犻�掓簮绋嬪簭
 * @Version    : 1.0.0
 */
public class CharSequenceJavaFileObject extends SimpleJavaFileObject {
 
    private CharSequence content;
 
 
    public CharSequenceJavaFileObject(String className,
                                      CharSequence content) {
        super(URI.create("string:///" + className.replace('.', '/')
                + JavaFileObject.Kind.SOURCE.extension), JavaFileObject.Kind.SOURCE);
        this.content = content;
    }
 
    @Override
    public CharSequence getCharContent(
            boolean ignoreEncodingErrors) {
        return content;
    }
}