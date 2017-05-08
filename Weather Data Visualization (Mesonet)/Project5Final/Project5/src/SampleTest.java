import static org.junit.Assert.*;

import org.junit.Test;
/**
 * 
 * @author Logan Spencer.   Modified by: Skylar Smith
 * @version Project1
 * 10/09/16
 *
 * Simple test class for a sample object
 */
public class SampleTest 
{
    /**
     * Method to test all functions and
     * components of the Sample class
     */
    @Test
    public void testAll() 
    {
        //Create two Sample objects for testing
        Sample mySample = new Sample(200.324245);
        Sample mySample2 = new Sample(); //Should be invalid
        
        //Test toString methods and getters
        assertEquals("200.3242", mySample.toString());
        assertEquals(200.324245, mySample.getValue(), 0.1);
        assertFalse(mySample2.isValid());
        
        //Create a sample object with invalid value for testing
        Sample mySample3 = new Sample(-1000.324245);
        
        // Test isLessThan and isGreaterThan
        assertEquals(true, mySample.isGreaterThan(new Sample(100)));
        assertEquals(true, mySample.isLessThan(new Sample(300)));
        assertEquals(false, mySample.isLessThan(new Sample(50)));
        assertEquals(true, mySample.isLessThan(new Sample()));
        assertEquals(true, mySample2.isLessThan(new Sample()));
        
        assertEquals("invalid", mySample3.toString());
        
    }

}
