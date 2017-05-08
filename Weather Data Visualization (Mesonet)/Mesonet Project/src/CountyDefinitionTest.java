import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
/**
 * Tests all components of the County Definition class
 * 
 * @author Logan Spencer
 * @version Project 5
 * 12/5/16
 */
public class CountyDefinitionTest
{
    /**
     * Tests the CountyDefinition class
     * @throws IOException
     */
    @Test
    public void test() throws IOException
    {
        // Read in the file, create the county
        BufferedReader br = new BufferedReader(
                new FileReader("data/CountyDefinitionTest"));
        String elements = br.readLine(); 
        String[] elem = elements.split(",");
        
        // Create county and sample for testing
        CountyDefinition county = new CountyDefinition(elem);
        Sample s = new Sample();
        county.setSample(s);
        
        // Confirm data was inputed correctly
        Assert.assertEquals(CountyDefinitionList.BAD_DATA_COLOR,
                county.getColor());
        Assert.assertEquals("BEAVER", county.getName().trim());
        Assert.assertEquals(s, county.getSample());
        br.close();
    }
}