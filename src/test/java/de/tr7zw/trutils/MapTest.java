package de.tr7zw.trutils;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class MapTest 
    extends TestCase
{

    public MapTest( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( MapTest.class );
    }

    public void testListMappings()
    {
    	TRMap<Integer, String> map = TRUtils.createList(10, 20, 30, 40).map(i -> i*i).computeKeyMap(i -> Integer.toHexString(i));
    	assertEquals(map.size(), 4);
    	assertEquals(map.get(400), "190");
    	assertEquals(map.get(1600), "640");
    	assertEquals(map.get(100), "64");
    	assertEquals(map.get(900), "384");
    }
}
