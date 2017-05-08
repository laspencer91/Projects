import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
 
/**
* @author Logan Spencer, Skylar
* @version 10-10-2016
*
* Project 2
* This class holds all of the days in a specific month
*
*/
       
public class DataYear extends MultiStatisticsWithDaysAbstract
{
    /** The set of days. */
    private TreeMap<Integer, DataMonth> months;
    /** The list of years to be accumulated as each day is added */
    private static TreeSet<Integer> yearList = new TreeSet<Integer>();
    /** The number of the year. */
    private int year;
    /** The station's ID. */
    private String stationID;
    /** Wind speed minimum. */
 
    /**
     * Default DataYear constructor
     */
    public DataYear()
    {
        months = new TreeMap<Integer, DataMonth>();
    }
   
    /**
     * Add a new day to the list
     * @param day the day of the year
     */
    protected void addDay(DataDay day)
    {
        // Create new month if it doesn't exist
        if (!months.containsKey(day.getMonth()))
        {
            months.put(day.getMonth(), new DataMonth());
        }

        months.get(day.getMonth()).addDay(day);
        year = day.getYear();
        stationID = day.getStationID();
        yearList.add(year);
    }
   
    /**
     * Get the DataMonth object
     * @param month The number corresponding to the specified month
     * @return DataMonth a specific month
     */
    protected DataMonth getItem(Integer month)
    {
        return months.get(month);
    }
 
    /**
     * Describe the year
     * @return a string describing all of the month and
     * the statistics for the year
     */
    public String toString() 
    {
        return String.format("%d, %s", year, stationID);
    }

    @Override
    public Iterator<Integer> iterator()
    {
        return months.navigableKeySet().iterator();
    }
      
    /**
     * Gives the list of years gathered as each day is added 
     * @return ArrayList<Integer> List of years
     */
    public static ArrayList<Integer> getYearList()
    {
        return new ArrayList<Integer>(yearList);
    }
    
    /**
     * Gives the structure of the dataYear for testing
     * @return String
     */
    public String getStructure() 
    {
        String str = "\tYear: " + toString() + "\n";
        for (Map.Entry<Integer, DataMonth> entry: months.entrySet())
        {
            str += entry.getValue().toString();
        }
        return str;
    }
}