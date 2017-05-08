import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Represents data for multiple stations over multiple years
 * 
 * @author skylarsmith Modified by: Logan Spencer
 * @version Project2
 * 10/11/16
 */
public class DataSet extends MultiStatisticsWithDaysAbstract
{
    /** List of years stored in DataYear objects */
    private TreeMap<Integer, DataYear> years;
    
    /** The id of the station */
    private String stationId;
    
    /**
     * Constructor for a DataSet object
     */
    public DataSet() 
    {
        years = new TreeMap<Integer, DataYear>();
    }
    
    /**
     * Returns the DataYear object that correlates to 
     * the index given.
     * 
     * @param year The integer associated with the specified year
     * @return DataYear object
     */
    @Override
    protected DataYear getItem(Integer year)
    {
        return years.get(year);
    }
    
    /**
     * Adds a day to the data set
     * 
     * @param c The day being added
     */
    @Override
    protected void addDay(DataDay c)
    {
        // Check if the year already exists 
        if (!years.containsKey(c.getYear()))
        {
            years.put(c.getYear(), new DataYear());
        }

        // Add day to year
        years.get(c.getYear()).addDay(c);
        stationId = c.getStationID();
    }
    
    /**
     * Describe the data set
     * @return String representation of the data 
     *         for the years in the data set
     */
    @Override
    public String toString()
    {
        return "Data Set: " + stationId;
    }
    
    @Override
    public Iterator<Integer> iterator()
    {
        return years.navigableKeySet().iterator();
    }
    
    /**
     * Gives the structure of the dataSet for testing
     * @return String representing structure
     */
    public String getStructure()
    {
        String str = toString() + "\n";
        for (Map.Entry<Integer, DataYear> entry: years.entrySet())
        {
            str += entry.getValue().toString();
        }
        return str;
    }
}
