package pl.nadoba.jvm.instrumentation.classtransformer;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class JavassistClassTransformer implements ClassFileTransformer {

    public byte[] transform(ClassLoader loader, String className,
                            Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
                            byte[] bytes) throws IllegalClassFormatException {

        byte[] result = bytes;

        if (className.contains("HelloWebServlet")) {
            try {
                String dotClassName = className.replace('/', '.');

                ClassPool cp = ClassPool.getDefault();
                CtClass ctClazz = cp.get(dotClassName);

                CtMethod method1 = ctClazz.getDeclaredMethod("getAddressee");

                method1.insertBefore("{ org.apache.log4j.Logger.getRootLogger().info(\"\\n\\n\\n\\nCaptured request = \" + request + \" \\n\\n\\n \"); }");
                result = ctClazz.toBytecode();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}