package it.siegert.chain.impl;

import it.siegert.chain.api.ChainModel;
import it.siegert.chain.api.GenericHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * This class is a test and example implementation of the GenericHandler interface.
 * It sets the bean to OTHER, if no other handler has modified the bean.
 *
 * @author Andre Siegert
 */
@Slf4j
public class DefaultHandler implements GenericHandler<Os, Boolean> {

    public static final String FORCE_OTHER = "forceOther";

    @Override
    public Os doExecute(ChainModel<Os, Boolean> chainModel) {
        Os currentOs = chainModel.getChainResult();
        Boolean forceIsActive = chainModel.getBean(FORCE_OTHER);
        if (Os.UNKNOWN.equals(currentOs)) {
            log.info("set to OTHER");
            return Os.OTHER;
        }else if (Boolean.TRUE.equals(forceIsActive)) {
            log.info("set to OTHER");
            return Os.OTHER;
        } else {
            log.info("skip");
            return currentOs;
        }
    }
}
