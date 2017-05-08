import org.junit.Assert;
import org.junit.Test;

/**
 * Test KeyConstraints class
 * @author Logan Spencer
 * @version 12-5-2016 
 */
public class KeyConstraintsTest 
{
    /**
     * Tests constructors and adding values.
     * Finally it tests addEnd and ensures the
     * lists are properly linked
     */
    @Test
    public void keyConstraintsTest() 
    {
        // Set up first list and add a value
        KeyConstraints constraints = new KeyConstraints();
        constraints.add(5);
        
        // Set up second list and add a value
        KeyConstraints constraints2 = new KeyConstraints();
        constraints.add(32);
        
        // Add the second list to the first list
        constraints.addEnd(constraints2);
        
        // Ensure that the expected value is produced
        Assert.assertEquals("[5, 32]", constraints.toString());
    }
}
