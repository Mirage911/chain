package it.siegert.chain.impl;

import it.siegert.chain.api.ChainModel;
import it.siegert.chain.api.GenericHandlerChain;
import it.siegert.chain.api.GenericHandlerChainFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * This is a factory class for loading GenericHandlerChain instances via spring.
 * The given springFactoryBeanName specifies the name of the corresponding spring definition.
 * This definition is a prototype definition.
 *
 * @author Andre Siegert
 */
public class GenericHandlerChainFactoryImpl<T1, T2> implements GenericHandlerChainFactory<T1, T2>, BeanFactoryAware {

    private final String springFactoryBeanName;
    private final String springModelBeanName;
    private BeanFactory beanFactory;

    public GenericHandlerChainFactoryImpl(String springFactoryBeanName, String springModelBeanName) {
        this.springFactoryBeanName = springFactoryBeanName;
        this.springModelBeanName = springModelBeanName;
    }

    public GenericHandlerChain<T1, T2> createGenericHandlerChain() {
        return getBeanFactory().getBean(getSpringFactoryBeanName(), GenericHandlerChain.class);
    }

    @Override
    public ChainModel<T1, T2> createChainModel() {
        return getBeanFactory().getBean(getSpringModelBeanName(), ChainModel.class);
    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    protected BeanFactory getBeanFactory() {
        return beanFactory;
    }

    protected String getSpringFactoryBeanName() {
        return springFactoryBeanName;
    }

    protected String getSpringModelBeanName() {
        return springModelBeanName;
    }
}
