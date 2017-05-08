import java.awt.Graphics2D;

/**
*
 * @author Logan Spencer    Modified by: Skylar Smith
*  @version 10-25-2016
*/
public class StationDefinition extends StatisticsAbstract
{
    // Declare private variables
    private String stationId;
    private String name;
    private String city;
    private double nlat;
    private double elon;
    private DataSet dataSet;
    
    /**
     * Default constructor for Station Definition
     * @param stationId The id of the station being created
     * @param name The name of the station
     * @param city The city where the station is located
     * @param nlat The north latitude of the station's location
     * @param elon The east longitude of the station's location
     */
    public StationDefinition(String stationId, String name,
            String city, double nlat, double elon)
    {
        // Initialize variables with parameter variables
        dataSet = new DataSet();
        this.stationId = stationId;
        this.name = name;
        this.city = city;
        this.nlat = nlat;
        this.elon = elon;   
        
    }
    
    /**
     * Adds a day into the station definition
     * 
     * @param day to be added 
     */
    protected void addDay(DataDay day)
    {
        dataSet.addDay(day);
    }
   
    /**
     * Gives the station id
     * 
     * @return String representation of station id
     */
    public String getStationId() {
        return stationId;
    }
   
    /**
     * Gives the name of the station
     * 
     * @return String with the name of the station
     */
    public String getName() {
        return name;
    }
   
    /**
     * Gives the city the station is located
     * 
     * @return String representing the stationId
     */
    public String getCity() {
        return city;
    }
   
    /**
     * Gives the north latitude of the station
     * 
     * @return String representation of the north latitude
     */
    public double getNlat() {
        return nlat;
    }
   
    /**
     * Gives the east longitude of the station
     * 
     * @return String representation of the east longitude
     */
    public double getElon() {
        return elon;
    }
   
    /**
     * Gets the average of a specified statistic
     * 
     * @param statisticId The id of the statistic measured
     * @param constraints The constraints of the statistic observed
     * @return Sample with the average of the specified statistic
     */
    public Sample getStatisticAverage(String statisticId, 
            KeyConstraints constraints) 
    {
        return dataSet.getStatisticAverage(statisticId, constraints);
    }
   
    /**
     * Gets the day with the maximum of whatever stat is specified
     * 
     * @param statisticId The id of the stat specified
     * @param constraints The constraints of the statistic observed
     * @return DataDay with the max stat
     */
    public DataDay getStatisticMaxDay(String statisticId, 
            KeyConstraints constraints) 
    {
        return dataSet.getStatisticMaxDay(statisticId, constraints);
    }
   
    /**
     * Gets the day with the minimum of whatever stat is specified
     * 
     * @param statisticId The id of the stat specified
     * @param constraints The constraints of the statistic observed
     * @return DataDay with the min stat
     */
    public DataDay getStatisticMinDay(String statisticId, 
            KeyConstraints constraints) 
    {
        return dataSet.getStatisticMinDay(statisticId, constraints);
    }
    
    /**
     * Draws a circle at the station's longitude/latitude.
     * 
     * @param g2 The graphics object used to paint the circle
     */
    public void draw(Graphics2D g2)
    {
        g2.fillOval((int)((elon * StateFrame.LONGITUDE_SCALE) + 
                StateFrame.getOffsetX()), 
                (int)((nlat * StateFrame.LATITUDE_SCALE) + 
                        StateFrame.getOffsetY()), 
                StateFrame.STATION_RADIUS, StateFrame.STATION_RADIUS);
    }
   
    /**
     * Gives a string representation of the station
     * 
     * @return String 
     */
    @Override
    public String toString() {
        return "Station: " + name + city + nlat + elon;
    }
   
    /**
     * Gets the structure of the station for 
     * testing purposes
     * 
     * @return String 
     */
    public String getStructure() {
        return "";
    }
}
