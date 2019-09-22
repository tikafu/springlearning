package org.litespring.beans.factory.support;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationgException;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.util.ClassLoaderUtil;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DefaultBeanFactory implements BeanFactory {

    public static final String ID_ATTRIBUTE = "id";
    public static final String CLASS_ATTRIBUTE = "class";

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<String, BeanDefinition>();

    public DefaultBeanFactory(String configFile) {
        loadBeanDefinition(configFile);
    }

    private void loadBeanDefinition(String configFile) {
        ClassLoader cl  = ClassLoaderUtil.getDefaultClassLoader();
        InputStream is = cl.getResourceAsStream(configFile);

        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(is);
            Element root = document.getRootElement();

            Iterator<Element> iterator = root.elementIterator();
            while(iterator.hasNext()) {
                Element ele = iterator.next();
                String beanId  = ele.attributeValue(ID_ATTRIBUTE);
                String beanClassName = ele.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition bd = new DefaultGenericDefinition(beanId, beanClassName);
                this.beanDefinitionMap.put(beanId, bd);
            }
        } catch (Exception e) {
            throw new BeanDefinitionStoreException("fail to load bean definition");
        }
    }


    public BeanDefinition getBeanDefinition(String beanId) {
        return this.beanDefinitionMap.get(beanId);
    }

    public Object getBean(String beanId) {
        BeanDefinition bd = this.getBeanDefinition(beanId);
        if(bd == null) {
            throw new BeanCreationgException("bean definition is null");
        }

        ClassLoader cl = ClassLoaderUtil.getDefaultClassLoader();
        try {
            Class<?> clazz = cl.loadClass(bd.getBeanClassName());
            Object object = clazz.newInstance();
            return object;
        } catch (Exception e) {
            throw new BeanCreationgException("fail to create bean :" + bd.getBeanClassName());
        }
    }
}
