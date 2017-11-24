package it.siegert.chain.impl;

import it.siegert.chain.api.ChainModel;
import it.siegert.chain.api.GenericHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * This class is a test and example implementation of the GenericHandler interface.
 * It checks, if the current os is a member of the windows family. If true, it sets the currentOs to Windows
 *
 * @author Andre Siegert
 */
@Slf4j
public class WindowsHandler implements GenericHandler<Os, Boolean> {

    private boolean isWindows() {
        String osName = System.getProperty("os.name");
        log.info("os.name={}", osName);
        if(osName == null){
            return false;
        }
        return osName.toLowerCase().contains("windows");
    }

    @Override
    public Os doExecute(ChainModel<Os, Boolean> chainModel) {
        if(isWindows()){
            log.info("set to WINDOWS");
            return Os.WINDOWS;
        }else {
            log.info("skip");
            return chainModel.getChainResult();
        }
    }
}
