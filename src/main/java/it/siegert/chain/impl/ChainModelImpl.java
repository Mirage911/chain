package it.siegert.chain.impl;

import it.siegert.chain.api.ChainModel;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.Map;

/**
 * This class implements all methods for a ChainModel bean.
 *
 * @author Andre Siegert on 28.04.15.
 */
public class ChainModelImpl<T1, T2> implements ChainModel<T1, T2> {

    private T1 chainResult = null;
    private Map<String, T2> beanMap = new HashMap<>(2);


    @Override
    public T1 getChainResult() {
        return chainResult;
    }

    public void setChainResult(T1 chainResult) {
        this.chainResult = chainResult;
    }

    @Override
    public void addBean(String beanName, T2 bean) {
        beanMap.put(beanName, bean);
    }

    @Override
    public void addMvcModelBean(String beanName, Object bean) {
        Model model = (Model) beanMap.get(MVC_MODEL);
        if(model != null){
            model.addAttribute(beanName, bean);
        }
    }

    @Override
    public T2 getBean(String beanName) {
        return beanMap.get(beanName);
    }
}
