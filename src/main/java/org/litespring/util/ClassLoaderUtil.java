package org.litespring.util;

public class ClassLoaderUtil {

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        cl = Thread.currentThread().getContextClassLoader();

        if(cl == null) {
            cl = ClassLoaderUtil.class.getClassLoader();

            if(cl == null) {
                cl = ClassLoader.getSystemClassLoader();
            }
        }
        return cl;
    }
}
