import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author Logan Spencer, Skylar Smith
 * @version Project1
 * Date: 2015-09-10
 * Project 1
 * 
 * This class represents a summary of one day's data from a single 
 * Mesonet station.
 *
 */

public class DataDay extends StatisticsAbstract 
{
    /** Year in which the data were sampled */
    private int year;
    /** Month in which the data were sampled */
    private int month;
    /** The day on which the data were sampled (1=January, 2=February, etc */
    private int day;
    /** The stationID number of the sample */
    private String stationID;
    /** Used to store all the samples of the day */
    private HashMap<String, Sample> samples;
    /** The list of data definitions */
    private static DataDefinitionList dataDefinitionList;
    /** Contains all the data fields of the day */
    private static ArrayList<String> dataFields;
    /** The index of the year of this day */
    private static int yearIndex;
    /** The index of the month of this day */
    private static int monthIndex;
    /** The index of this day */
    private static int dayIndex;
    /** The index of the station ID of this day */
    private static int stationIDIndex;

    /**
     * Default DataDay constructor
     * 
     * @param args The arguments passed to create a DataDay
     */
    public DataDay(String[] args)
    {
        samples = new HashMap<String, Sample>();
        
        year = Integer.parseInt(args[yearIndex]);
        month = Integer.parseInt(args[monthIndex]);
        day = Integer.parseInt(args[dayIndex]);
        stationID = args[stationIDIndex];
        
        for (int i = 0; i < args.length; ++i)
        {   
            if (dataDefinitionList.isValidStat(dataFields.get(i)))
            {                
                samples.put(dataFields.get(i), 
                        new Sample(Double.parseDouble(args[i])));
            }
        }
    }
    
    /**
     * Constructor for DataDay
     * 
     * Uninitialized data and invalid samples
     */
    public DataDay() 
    {
        samples = new HashMap<String, Sample>();
        
        for (int i = 0; i < dataFields.size(); ++i)
        {
            if (dataDefinitionList.isValidStat(dataFields.get(i)))
            {
                samples.put(dataFields.get(i), new Sample());
            }
        }
    }
    
    /**
     * Gives the day with the minimum of whatever statistic is measured
     * 
     * @param statisticId The id of the statistic
     * @param constraints The constraints of the statistic observed
     * @return DataDay day with minimum statistic
     */
    public DataDay getStatisticMinDay(String statisticId, 
            KeyConstraints constraints)
    {
        return this;
    }
    
    /**
     * Gives the day with maximum of whatever statistic is measured
     * 
     * @param statisticId The id of the statistic
     * @param constraints The constraints of the statistic observed
     * @return DataDay day with maximum statistic
     */
    public DataDay getStatisticMaxDay(String statisticId, 
            KeyConstraints constraints)
    {
        return this;
    }
    
    /**
     * Gives a sample with the average of whatever statistic is measured
     * 
     * @param statisticId The id of the statistic
     * @param constraints The constraints of the statistic observed
     * @return Sample with average statistic
     */
    public Sample getStatisticAverage(String statisticId, 
            KeyConstraints constraints)
    {
        Sample s = samples.get(statisticId);
        if (s == null) 
        {
            return new Sample();
        }
        return s;
    }
    
    /**
     * Describe the data for the day
     * 
     * @return String describing the day
     */
    @Override
    public String toString()
    {
        return String.format("%d-%02d-%02d, %s", 
                year, month, day, stationID);
    }
    
    /**
     * Gets the year
     * 
     * @return the year of the data
     */
    public int getYear()
    {
        return year;
    }

    /**
     * Gets the month
     * 
     * @return the month of the data
     */
    public int getMonth()
    {
        return month;
    }

    /**
     * Gets the day
     * 
     * @return the day of the data
     */
    public int getDay()
    {
        return day;
    }

    /**
     * Gets the station id
     * 
     * @return the stationID the data was collected from
     */
    public String getStationID()
    {
        return stationID;
    }
    
    /**
     * Sets the data definitions for the day
     * 
     * @param dataDefinitions The list of definitions for data variables
     */
    public static void setDataDefinitionList(DataDefinitionList 
            dataDefinitions)
    {
        dataDefinitionList = dataDefinitions;
    }
    
    /**
     * Sets the data fields for the day
     * @param dataFieldList The list of data fields to be set as
     *                      data fields, if valid variables
     */
    public static void setDataFields(String[] dataFieldList)
    {
        dataFields = new ArrayList<String>();
        
        for (int i = 0; i < dataFieldList.length; ++i)
        {
            dataFields.add(dataFieldList[i]);
        }
        yearIndex = dataFields.indexOf("YEAR");
        monthIndex = dataFields.indexOf("MONTH");
        dayIndex = dataFields.indexOf("DAY");
        stationIDIndex = dataFields.indexOf("STID");
    }
}
