import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test for the Set, Year, and Month 
 * classes
 * Project 5
 * @author Logan Spencer
 * @version 12-02-2016
 */
public class DataSetYearMonthTest 
{
    /**
     * test that loads the variable list, station list 
     * and data. Test statistics calcs
     * @throws IOException the exception
     */
    @Test
    public void test() throws IOException
    {
        // Create the data definitions list 
        // and the station definition list
        DataDefinitionList listD = 
                new DataDefinitionList("data/DataTranslation.csv");
        StationDefinitionList listS = 
                new StationDefinitionList("data/geoinfo.csv");
        
        // Set the definition list for the data day class
        DataDay.setDataDefinitionList(listD);
        // Load data 
        listS.loadData("data/testData.csv");
        
        // Report for the station
        String string = "";
        String string2 = "";
        
        string = String.format("Station: %s, %s, %s \n", "ACME", 
                listS.getStationInfo("ACME").getName(),
                listS.getStationInfo("ACME").getCity());
        string2 = String.format("Station: %s, %s, %s \n", "ADAX", 
                listS.getStationInfo("ADAX").getName(),
                listS.getStationInfo("ADAX").getCity());
        
        Assert.assertEquals("Station: ACME, Acme, Rush "
                + "Springs \n", string);
        Assert.assertEquals("Station: ADAX, Ada, Ada "
                + "\n", string2);
    }
}
