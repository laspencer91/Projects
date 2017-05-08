import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
/** 
 * Tests the DataDefinitionList class
 * @author Logan Spencer
 * @version Project3
 * 10/25/16
 */
public class DataDefinitionListTest
{
    /** 
     * Create a DataDefinitionList using the 
     * DataTranslation.csv file, and ensure all
     * methods execute correctly.
     * 
     * @throws IOException In case of error reading file
     */
    @Test
    public void test() throws IOException
    {
        DataDefinitionList list = 
                new DataDefinitionList("src/data/DataTranslationTest.csv");
        
        // Create an array list with all the variable id's for testing
        ArrayList<String> ids = list.getVariableIds();
        
        // Test methods
        for (String id: ids)
        {
            if (id.equals("TAVG"))
            {
                Assert.assertTrue(true);
            }
            else if (id.equals("TMIN"))
            {
                Assert.assertTrue(true);
            }
            else if (id.equals("TMAX"))
            {
                Assert.assertTrue(true);
            }
            else if (id.equals("9AVG"))
            {
                Assert.assertTrue(true);
            }
            else
            {
                Assert.assertTrue(false);
            }
        }
        Assert.assertTrue(list.isValidStat("TAVG"));
        Assert.assertTrue(list.getDataInfo("TAVG")
                .getVariableName().equals("Average Air Temperature"));
    }
}
