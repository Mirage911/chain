package it.siegert.chain.impl;

import it.siegert.chain.api.ChainModel;
import it.siegert.chain.api.GenericHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * This class is a test and example implementation of the GenericHandler interface.
 * It checks, if the current os is a member of the linux family. If true, it sets the currentOs to LINUX
 *
 * @author Andre Siegert
 */
@Slf4j
public class LinuxHandler implements GenericHandler<Os, Boolean> {

    private boolean isLinux() {
        String osName = System.getProperty("os.name");
        log.info("os.name={}", osName);
        if(osName == null){
            return false;
        }
        return osName.toLowerCase().contains("linux");
    }

    @Override
    public Os doExecute(ChainModel<Os, Boolean> chainModel) {
        if(isLinux()){
            log.info("set to LINUX");
            return Os.LINUX;
        }else {
            log.info("skip");
            return chainModel.getChainResult();
        }
    }
}
