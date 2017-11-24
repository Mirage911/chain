package it.siegert.chain.api;

/**
 * This interface defines all methods for the ChainModel bean.
 */
public interface ChainModel<T1, T2> {

    String MVC_MODEL = "mvcModel";

    T1 getChainResult();

    void setChainResult(T1 chainResult);

    void addBean(String beanName, T2 bean);

    void addMvcModelBean(String beanName, Object bean);

    T2 getBean(String beanName);
}
