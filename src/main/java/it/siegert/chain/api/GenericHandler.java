package it.siegert.chain.api;

/**
 * This interface defines all methods for a handler in the GenericHandlerChain.
 */
public interface GenericHandler<T1, T2> {

    T1 doExecute(ChainModel<T1, T2> chainModel);
}
