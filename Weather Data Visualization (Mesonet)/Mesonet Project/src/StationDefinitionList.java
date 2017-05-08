import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Defines a list to store all station definitions 
 * for the Mesonet project
 * 
 * @author Logan Spencer
 * @version Project 4
 * 11/15/16
 */
public class StationDefinitionList
{
    private HashMap<String, StationDefinition> stationMap;
    
    /**
     * StationDefinitionList constructor 
     * 
     * @param fileName The file to construct the definitions from
     * @throws IOException In case of error loading file
     */
    public StationDefinitionList(String fileName) throws IOException {
        stationMap = new HashMap<String, StationDefinition>();
        
        // Create Buffered Reader
        BufferedReader br = new BufferedReader(new FileReader(fileName));
      
        //Read the first line and skip it since it doesn't contain data
        String str = br.readLine();
        str = br.readLine();
       
        while (str != null)
        {
            // Create an array out of 1 line with values split by ","
            String[] dataInfo = str.split(",");
          
            // Add a new day object to this Hashmap
            // Key = stationID or dataInfo[0]
            // Value = StationDefinition object
            stationMap.put(dataInfo[0], new StationDefinition(dataInfo[0],
                    dataInfo[1], dataInfo[2],
                    Double.parseDouble(dataInfo[3]),
                    Double.parseDouble(dataInfo[4])));
          
            str = br.readLine();
        }
      
        // Close buffered reader
        br.close();
    }
      
    /**
     * Returns stationId object
     * 
     * @param stationId The id of the station
     * @return stationDefinition object as the description
     * of a station that is defined by its station ID.
     */
    public StationDefinition getStationInfo(String stationId)
    {
        return stationMap.get(stationId);
    }
   
    /**
     * Helper for StationDefinitionList
     * 
     * @param fileName The file to load data from
     * @throws IOException In case of error loading file
     */
    public void loadData(String fileName) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        String[] field = br.readLine().split(",");
        DataDay.setDataFields(field);
        
        String str = br.readLine();
       
        while (str != null)
        {
            // Split string into array, add day 
            String[] info = str.split(",");            
            addDay(new DataDay(info));
            // Read next line
            str = br.readLine();
        }
        br.close();
    }
   
    /**
     * Add a day by first finding the correct StationDefinition
     * and then calling its add day, which trickles down.
     * 
     * @param day The day to be added
     */
    // and then calling its add day, which trickles down.
    private void addDay(DataDay day) 
    {        
        stationMap.get(day.getStationID()).addDay(day);
    }
       
    
    
    /**
     *  Get ids of Stations.
     *  KeySet returns the names of all the different keys
     * @return return arrayList of keys
     */
    public ArrayList<String> getStationIds()
    {
        // Temporary arrayList
        ArrayList<String> list = new ArrayList<String>();
       
        for (String inFo : stationMap.keySet())
        {
            // Add inFo to list
            list.add(inFo);
        }
        // Return completed list
        return list;
    }
    
    /**
     * Gets the average of a specified statistic
     * 
     * @param stationId The id of the station 
     * @param variableId The id of the variable measured
     * @param constraints The constraints of the statistic observed
     * @return Sample with the average of the specified statistic
     */
    public Sample getStatisticAverage(String stationId, String variableId,
            KeyConstraints constraints) 
    {
        return stationMap.get(stationId).getStatisticAverage(
                variableId, constraints);
    }
   
    /**
     * Gets the day with the maximum of whatever stat is specified
     * 
     * @param stationId The id of the station
     * @param variableId The id of the variable measured
     * @param constraints The constraints of the statistic observed
     * @return DataDay with the max stat
     */
    public DataDay getStatisticMaxDay(String stationId, String variableId,
            KeyConstraints constraints) 
    {
        return stationMap.get(stationId).getStatisticMaxDay(
                variableId, constraints);
    }
   
    /**
     * Gets the day with the minimum of whatever stat is specified
     * 
     * @param stationId The id of the station
     * @param variableId The id of the variable measured
     * @param constraints The constraints of the statistic observed
     * @return DataDay with the min stat
     */
    public DataDay getStatisticMinDay(String stationId, String variableId, 
            KeyConstraints constraints) 
    {
        return stationMap.get(stationId).getStatisticMinDay(
                variableId, constraints);
    }
}