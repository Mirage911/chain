package it.siegert.chain.impl;

import it.siegert.chain.api.ChainModel;
import it.siegert.chain.api.GenericHandler;
import it.siegert.chain.api.GenericHandlerChain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class is the implementation for the GenericHandlerChain
 *
 * @author Andre Siegert
 */
public class GenericHandlerChainImpl<T1, T2> implements GenericHandlerChain<T1, T2> {

    private List<GenericHandlerWrappper> handlerList;

    public GenericHandlerChainImpl(final List<GenericHandler> handlerList) {
        setHandlerList(handlerList);
    }

    public GenericHandlerChainImpl() {
        this.handlerList = new ArrayList<>(4);
    }

    private void setHandlerList(final List<GenericHandler> srcList) {
        final List<GenericHandlerWrappper> list = new ArrayList<GenericHandlerWrappper>(srcList.size());
        for (GenericHandler handler : srcList) {
            list.add(new GenericHandlerWrappper(handler));
        }
        this.handlerList = Collections.unmodifiableList(list);
    }

    protected List<GenericHandlerWrappper> getHandlerList() {
        return handlerList;
    }

    @Override
    public T1 doExecuteHandler(ChainModel<T1, T2> chainModel) {
        for (GenericHandlerWrappper<T1, T2> genericHandlerWrappper : getHandlerList()) {
            T1 chainResult = genericHandlerWrappper.doExecute(chainModel);
            if (chainResult != null) {
                chainModel.setChainResult(chainResult);
            }
        }

        return chainModel.getChainResult();
    }

    @Override
    public void addHandler(GenericHandler genericHandler) {
        handlerList.add(new GenericHandlerWrappper(genericHandler));
    }

    class GenericHandlerWrappper<T1, T2> implements GenericHandler<T1, T2> {

        private final GenericHandler<T1, T2> genericHandler;
        private boolean executed = false;

        GenericHandlerWrappper(final GenericHandler<T1, T2> genericHandler) {
            this.genericHandler = genericHandler;
        }

        public boolean isExecuted() {
            return executed;
        }

        public void setExecuted(final boolean executed) {
            this.executed = executed;
        }

        @Override
        public T1 doExecute(ChainModel<T1, T2> chainModel) {
            setExecuted(true);
            return genericHandler.doExecute(chainModel);
        }
    }

}
