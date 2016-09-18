package jp.rsks.myapplication.datasource;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.util.logging.Logger;

import static org.junit.Assert.*;

/**
 * Created by rsk on 2016/09/18.
 */
public class NhkProgramDataLoaderTest {

    NhkProgramDataLoader nhk;

    @Before
    public void setup () {
        nhk = new NhkProgramDataLoader();
    }

    @Test
    public void testGetProgramList() throws Exception {
        NhkProgramList list = nhk.getProgramList();
        System.out.println(list.toString());
        assertTrue(list.list.g1.size() > 0);
        assertNotNull(list.list.g1.get(0).id);
    }
}
