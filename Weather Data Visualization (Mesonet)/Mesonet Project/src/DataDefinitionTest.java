import org.junit.Assert;
import org.junit.Test;
/**
 * Tests the DataDefinition class
 * @author Logan Spencer
 * @version Project3
 * 10/25/16
 */
public class DataDefinitionTest
{
    /**
     * Creates a DataDefinition object and ensures 
     * toString method returns correct output
     */
    @Test
    public void test()
    {
        /** Construct object for testing */
        DataDefinition def = new DataDefinition("X", "10",
                "int", true, "test");
        
        /** Test all accessor methods, and toString */
        Assert.assertTrue(def.getVariableName().equals("X"));
        Assert.assertTrue(def.getVariableId().equals("10"));
        Assert.assertTrue(def.getUnit().equals("int"));
        Assert.assertTrue(def.getDescription().equals("test"));
        
        Assert.assertTrue(def.toString().equals("<10>, <X> (<int>)"));
    }
}
