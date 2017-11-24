package it.siegert.chain.impl;

import it.siegert.chain.api.GenericHandlerChain;
import it.siegert.chain.api.GenericHandlerChainFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;


/**
 * This class is a junit testclass for the GenericHandlerImpl class
 *
 * @author Andre Siegert
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/it/siegert/spring-chain-test.xml"})
public class GenericHandlerChainImplTest {

    @Resource
    private GenericHandlerChainFactory handlerChainFactory;

    @Test
    public void testChain(){
        GenericHandlerChain<Os, Object> chain = handlerChainFactory.createGenericHandlerChain();

        ChainModelImpl<Os, Object> chainModel = new ChainModelImpl<>();
        chainModel.setChainResult(Os.UNKNOWN);

        Os currentOs = chain.doExecuteHandler(chainModel);
        Os expectedOs = getExpectedOs();

        log.info("expectedOs={}", expectedOs);
        assertEquals(expectedOs, currentOs);
    }

    @Test
    public void testChainWithForceOther(){
        GenericHandlerChain<Os, Boolean> chain = handlerChainFactory.createGenericHandlerChain();

        ChainModelImpl<Os, Boolean> chainModel = new ChainModelImpl<>();
        chainModel.setChainResult(Os.UNKNOWN);
        chainModel.addBean(DefaultHandler.FORCE_OTHER, Boolean.TRUE );

        Os currentOs = chain.doExecuteHandler(chainModel);
        Os expectedOs = Os.OTHER;

        log.info("expectedOs={}", expectedOs);
        assertEquals(expectedOs, currentOs);
    }

    private Os getExpectedOs() {
        Os expectedOs = Os.OTHER;
        if(isWindows()){
            expectedOs = Os.WINDOWS;
        }else if(isLinux()){
            expectedOs = Os.LINUX;
        }
        return expectedOs;
    }

    private boolean isWindows() {
        String osName = System.getProperty("os.name");
        if(osName == null){
            return false;
        }
        return osName.toLowerCase().contains("windows");
    }

    private boolean isLinux() {
        String osName = System.getProperty("os.name");
        if(osName == null){
            return false;
        }
        return osName.toLowerCase().contains("linux");
    }

}
