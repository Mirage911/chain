package it.siegert.chain.api;

/**
 * This interface defines all methods for controlling a chain of generic handlers
 */
public interface GenericHandlerChain<T1, T2> {

    T1 doExecuteHandler(ChainModel<T1, T2> chainModel);

    void addHandler(GenericHandler genericHandler);
}
