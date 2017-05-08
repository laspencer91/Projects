import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

/** 
 * Test for the DataDefinition and DataDefinitionList
 * Project 5
 * @author Logan Spencer and Skylar
 * @version 12-02-2016
 */
public class StationDefinitionListTest 
{
    /**
     * Test for the constructor, getStationID,
     * getName, getCity, getNlat, and getElon methods.
     */
    @Test
    public void dataDefinitionest() 
    {
        StationDefinition sdn = new StationDefinition("ACME", "Acme", 
                "Rush Springs", 34.80833, -98.02325);
        
        Assert.assertEquals("ACME", sdn.getStationId());
        Assert.assertEquals("Acme", sdn.getName());
        Assert.assertEquals("Rush Springs", sdn.getCity());
        Assert.assertEquals(34.80833, sdn.getNlat(), 0.001);
        Assert.assertEquals(-98.02325, sdn.getElon(), 0.001);
    }
    
    /**
     * Test for the toString method
     */
    @Test
    public void toStringTest() 
    {
        StationDefinition sdn = new StationDefinition("ACME", "Acme", 
                "Rush Springs", 34.80833, -98.02325);

        Assert.assertEquals("Station: AcmeRush Springs34.80833-98.02325",
                sdn.toString());
    }
    
    /**
     * Test for the getStructure method from the 
     * StationDefinition class and the toString for the
     * StationDefinitionList class
     * @throws IOException the exception
     */
    @Test
    public void getStructureTest() throws IOException 
    {
        // Create an initialize all the necessary data
        DataDefinitionList dataDef = 
                new DataDefinitionList("data/DataTranslation.csv");
        DataDay.setDataDefinitionList(dataDef);
        StationDefinitionList sdn = new StationDefinitionList("data/test2.csv");
        sdn.loadData("data/test3.csv");
        
        // getStructure for the StationDefintion class
        Assert.assertEquals("", 
                sdn.getStationInfo("ACME").getStructure());
        
        // toString for the StationDefinitionList class
        Assert.assertEquals("StationDefinitionList@531d72ca",
                sdn.toString() );
    }

    /**
     * Tests the getStationIds method from the 
     * StationDefinitionList class
     * @throws IOException the exception
     */
    @Test
    public void getStationIdsTest() throws IOException
    {
        DataDefinitionList dataDef = 
                new DataDefinitionList("data/DataTranslation.csv");
        DataDay.setDataDefinitionList(dataDef);
        StationDefinitionList sdn = new
                StationDefinitionList("data/test2.csv");
        sdn.loadData("data/test3.csv");
        
        Assert.assertEquals("[ACME]", sdn.getStationIds().toString());
    }
    
    /**
     * Test for the get statistic methods in 
     * station definition and station definition classes
     * @throws IOException the exception
     */
    @Test
    public void getStatisticsTest() throws IOException
    {
        DataDefinitionList dataDef = 
                new DataDefinitionList("data/DataTranslation.csv");
        DataDay.setDataDefinitionList(dataDef);
        StationDefinitionList sdl = 
                new StationDefinitionList("data/geoinfo.csv");
        sdl.loadData("data/alldata_2015.csv");
        
        // Statistics
        Assert.assertEquals("invalid", sdl.getStatisticAverage("ACME", "TMAX",
                new KeyConstraints()).toString());
        Assert.assertEquals("invalid", sdl.getStatisticMinDay("TISH", "ATOT",
                new KeyConstraints())
                .getStatisticAverage("ATOT", new KeyConstraints()).toString());
        Assert.assertEquals("invalid", sdl.getStatisticMaxDay("TISH", "ATOT",
                new KeyConstraints())
                .getStatisticAverage("ATOT", new KeyConstraints()).toString());
    }
}
