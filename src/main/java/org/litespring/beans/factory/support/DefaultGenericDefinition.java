package org.litespring.beans.factory.support;

import org.litespring.beans.BeanDefinition;

public class DefaultGenericDefinition implements BeanDefinition {
    private String beanId;
    private String beanClassName;


    public DefaultGenericDefinition(String beanId, String beanClassName) {
        this.beanId = beanId;
        this.beanClassName = beanClassName;
    }

    public String getBeanClassName() {
        return this.beanClassName;
    }
}
