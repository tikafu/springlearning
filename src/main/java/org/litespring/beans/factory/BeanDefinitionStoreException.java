package org.litespring.beans.factory;

import org.litespring.beans.BeansException;

public class BeanDefinitionStoreException extends BeansException {
    public BeanDefinitionStoreException(String message) {
        super(message);
    }

    public BeanDefinitionStoreException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeanDefinitionStoreException(Throwable cause) {
        super(cause);
    }
}
