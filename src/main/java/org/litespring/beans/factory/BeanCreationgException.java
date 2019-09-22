package org.litespring.beans.factory;

import org.litespring.beans.BeansException;

public class BeanCreationgException extends BeansException {
    public BeanCreationgException(String message) {
        super(message);
    }

    public BeanCreationgException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeanCreationgException(Throwable cause) {
        super(cause);
    }
}
