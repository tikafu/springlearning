package org.litespring.test.v1;

import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationgException;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.service.v1.PetStoreService;

public class BeanFactoryTest {

    @Test
    public void testGetBean() {
        BeanFactory factory = new DefaultBeanFactory("petstore-v1.xml");
        BeanDefinition bd  = factory.getBeanDefinition("petStore");

        Assert.assertEquals("org.litespring.service.v1.PetStoreService", bd.getBeanClassName());

        PetStoreService petStore = (PetStoreService) factory.getBean("petStore");
        assertNotNull(petStore);

    }

    @Test
    public void testInvalidBean() {
        BeanFactory factory = new DefaultBeanFactory("petstore-v1.xml");
        try {
            PetStoreService petStoreService = (PetStoreService) factory.getBean("XXX");
        } catch (BeanCreationgException e) {
            return;
        }
        Assert.fail("expect beanCreationException");
    }
    @Test
    public void testInvalidXml() {
        try {
            BeanFactory factory = new DefaultBeanFactory("xxx.xml");
            factory.getBeanDefinition("petStore");
        } catch (BeanDefinitionStoreException e) {
            return;
        }
        Assert.fail("expect beanDefinitionStoreException");
    }
}
