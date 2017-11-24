package it.siegert.chain.impl;

import it.siegert.chain.api.ChainModel;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

/**
 * this simple testcase tests the ChainModelImpl class.
 *
 * @author Andre Siegert on 28.04.15.
 */
public class ChainModelImplTest {

    static final String MODEL_KEY = "modelKey";

    Date now;
    ChainModelImpl testee;

    @Before
    public void setUp(){
        now = new Date();
        testee = new ChainModelImpl<Date, String>();
    }

    @Test
    public void testMvcBean(){
        Model model = createModel();
        testee.addBean(ChainModel.MVC_MODEL, model);
        testee.addMvcModelBean(MODEL_KEY, now);

        assertNull(testee.getBean(MODEL_KEY));
        assertEquals(now, ((Model) testee.getBean(ChainModel.MVC_MODEL)).asMap().get(MODEL_KEY));
    }

    @Test(expected = NullPointerException.class)
    public void testMvcBeanWithoutModel(){
        testee.addMvcModelBean(MODEL_KEY, now);

        assertNull(testee.getBean(MODEL_KEY));
        assertEquals(now, ((Model) testee.getBean(ChainModel.MVC_MODEL)).asMap().get(MODEL_KEY));
    }

    @Test
    public void testAll(){
        String s1 = "Bla";
        String s2 = "Blup";

        testee.setChainResult(now);
        testee.addBean("s1", s1);
        testee.addBean("s2", s2);
        testee.addBean("s3", s1);

        assertEquals("invalid chainResult", now, testee.getChainResult());
        assertEquals("invalid bean s1", s1, testee.getBean("s1"));
        assertEquals("invalid bean s2", s2, testee.getBean("s2"));
        assertNotEquals("invalid bean s3", s2, testee.getBean("s3"));

    }

    private Model createModel() {
        Map<String, Object> map = new HashMap<>();
        map.put(MODEL_KEY, now);

        Model model = createMock(Model.class);
        expect(model.addAttribute(MODEL_KEY, now)).andReturn(model).times(1);
        expect(model.asMap()).andReturn(map).times(1);
        replay(model);
        return model;
    }

}
