package it.siegert.chain.api;

/**
 * This interface defines all methods for creating GenericHandlerChain
 */
public interface GenericHandlerChainFactory<T1, T2> {

    GenericHandlerChain<T1, T2> createGenericHandlerChain();

    ChainModel<T1, T2> createChainModel();
}
