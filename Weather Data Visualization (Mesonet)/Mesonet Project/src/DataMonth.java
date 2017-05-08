 import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
 
/**
* @author Logan Spencer, Skylar
* @version 10-10-2016
*
* Project 2
* This class holds all of the days in a specific month
*
*/
 
public class DataMonth extends MultiStatisticsWithDaysAbstract
{
    /** The set of days. */
    private TreeMap<Integer, DataDay> days;
    /** The number of the year. */
    private int year;
    /** The number of the month. */
    private int month;
    /** The station's ID. */
    private String stationID;
    /** Wind speed minimum. */
 
    /**
     * Default DataMonth constructor
     */
    public DataMonth()
    {
        days = new TreeMap<Integer, DataDay>();
    }
   
    /**
     * Add a new day to the list
     * @param day the day
     */
    protected void addDay(DataDay day)
    {
        // Add day into month
        if (!days.containsKey(day.getDay()))
        {
            days.put(day.getDay(), new DataDay());
        }

        days.put(day.getDay(), day);
        
        year = day.getYear();
        month = day.getMonth();
        stationID = day.getStationID();
    }
   
    /**
     * Get the DataDay object
     * @param day The number corresponding to the specific day
     * @return days a specific day
     */
    @Override
    protected DataDay getItem(Integer day)
    {
        return days.get(day);
    }
 
    /**
     * Describe the month
     * @return a string describing all of the days and
     * the statistics for the month
     */
    public String toString() 
    {
        return String.format("%04d-%02d, %s", year, month, stationID);
    }
    
    @Override
    public Iterator<Integer> iterator()
    {
        return days.navigableKeySet().iterator();
    }
    
    /**
     * Returns the structure of the dataMonth for testing
     * @return String representing structure
     */
    public String getStructure()
    {
        String str = "\t\tMonth: " + toString() + "\n";
        for (Map.Entry<Integer, DataDay> entry: days.entrySet())
        {
            str += "\t\t\t" + entry.getValue().toString() + "\n";
        }
        return str;
    }
}