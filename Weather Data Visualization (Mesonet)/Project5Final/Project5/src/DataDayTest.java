import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test for the data day class
 * Project 5
 * @author Logan Spencer
 * @version 12-02-2016
 */
public class DataDayTest 
{
    /**
     * Test for the empty constructor
     * @throws IOException Error reading file
     */
    @Test
    public void emptyConstructorTest() throws IOException
    {
        // Empty constructor
        DataDay.setDataFields( new String[]{"YEAR", "MONTH", 
            "DAY", "STID", "TMAX", "TMIN", "TAVG"});
        DataDay.setDataDefinitionList( 
                new DataDefinitionList("data/DataTranslation.csv"));
        DataDay day = new DataDay();
        Assert.assertNotNull(day);
    }

    /**
     * Test for the other constructor
     * Tests for the getter methods: 
     * getDay, getMonth, getYear, getStationId
     * Test for the toString method
     * @throws IOException Error reading file
     */
    @Test
    public void constructorTest() throws IOException
    {
        // Create the data definition list, set the data fields
        // and create a new data day object
        DataDay.setDataFields( new String[]{"YEAR", "MONTH", 
            "DAY", "STID", "TMAX", "TMIN", "TAVG"});
        DataDay.setDataDefinitionList( 
                new DataDefinitionList("data/DataTranslation.csv"));
        String[] args = {"2016", "1", "1", "ACME", "44.1",
            "23.65", "32.69"};
        DataDay day2 = new DataDay(args);

        Assert.assertEquals(2016, day2.getYear());
        Assert.assertEquals(1, day2.getDay());
        Assert.assertEquals(1, day2.getMonth());
        Assert.assertEquals("ACME", day2.getStationID());
        Assert.assertEquals("2016-01-01, ACME", day2.toString());
    }

    /**
     * Test for the get statistics method
     * @throws IOException Error reading file
     */
    @Test
    public void getStatisticsTest() throws IOException
    {
        DataDay.setDataFields( new String[]{"YEAR", "MONTH", 
            "DAY", "STID", "TMAX", "TMIN", "TAVG"});
        DataDay.setDataDefinitionList( 
                new DataDefinitionList("data/DataTranslation.csv"));
        String[] args = {"2016", "1", "1", "ACME", "44.1",
            "23.65", "32.69"};
        DataDay day2 = new DataDay(args);

        Assert.assertEquals("44.1000", day2.getStatisticMinDay("TMAX",
                new KeyConstraints())
                .getStatisticAverage("TMAX", new KeyConstraints()).toString());
        Assert.assertEquals("23.6500", day2.getStatisticMaxDay("TMIN",
                new KeyConstraints())
                .getStatisticAverage("TMIN", new KeyConstraints()).toString());
        Assert.assertEquals("32.6900", day2.getStatisticAverage("TAVG",
                new KeyConstraints()).toString());
    }
}
