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

    public void testApp()
    {
        assertTrue( true );
    }
}
