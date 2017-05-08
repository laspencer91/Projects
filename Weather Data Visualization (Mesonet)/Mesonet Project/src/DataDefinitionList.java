import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Stores all the possible variables that 
 * are stored for a single station
 * 
 * @author Logan Spencer
 * @version Project3
 * 10/25/16
 */
public class DataDefinitionList
{
    /**
     * Used to quickly translate between variableId
     * and the corresponding DataDefintion object
     */
    private HashMap<String, DataDefinition> dataInfoMap;
    
    /**
     * Constructs a list that contains all possible 
     * variables for a single station. dataInfoMap 
     * contains the variableId's and their corresponding
     * DataDefinition objects
     * 
     * @param fileName The file to extract data from
     * @throws IOException In case there are errors reading file
     */
    public DataDefinitionList(String fileName) throws IOException
    {
        dataInfoMap = new HashMap<String, DataDefinition>();
        
        // Create Buffered Reader
        BufferedReader br = new BufferedReader(new FileReader(fileName));
       
        //Read the first line and skip it since it doesn't contain data
        String str = br.readLine();
        str = br.readLine();
        
        // Run through each line as long as it is not empty and perform
        // these functions.
        while (str != null)
        {
            // Create an array out of 1 line with values split by ","
            String[] variableInfo = str.split(",");
            
            // Create DataDefinition object to store into dataInfoMap
            DataDefinition def = new DataDefinition(variableInfo[0], 
                    variableInfo[1], variableInfo[2], 
                    Boolean.getBoolean(variableInfo[3]), variableInfo[4]);
           
            /*
             * Add a new variable entry into dataInfoMap
             * Key: variableId 
             * Value: DataDefinition object
             */
            dataInfoMap.put(variableInfo[1], def);
           
            str = br.readLine();
        }
       
        // Close buffered reader
        br.close();
    }
    
    /**
     * Gives a list of the variable id's in dataInfoMap
     * 
     * @return ArrayList<String> containing the variable id's
     */
    public ArrayList<String> getVariableIds()
    {
        // Create an array list to store the variable id's
        ArrayList<String> keys = new ArrayList<String>();
        
        // Add every key to the keys list
        for (String key: dataInfoMap.keySet())
        {
            keys.add(key);
        }
        return keys;
    }
    
    /**
     * Checks to see if specified key is within dataInfoMap
     * 
     * @param variableId The specified id to be checked
     * @return boolean whether the id specified is a valid stat
     */
    public boolean isValidStat(String variableId)
    {
        return dataInfoMap.containsKey(variableId);
    }
    
    /**
     * Gives the value of the specified variableId
     * 
     * @param variableId The id of the DataDefinition object
     *        to be retrieved
     * @return DataDefinition object 
     */
    public DataDefinition getDataInfo(String variableId)
    {
        return dataInfoMap.get(variableId);
    }
    
    /**
     * Gives a multi-line string containing
     * one DataDefinition String per line.
     * 
     * @return String 
     */
    @Override
    public String toString()
    {
        String str = "";
        
        // Add one DataDefinition object to each line
        for (Map.Entry<String, DataDefinition> entry: dataInfoMap.entrySet())
        {
            str += entry.getValue().toString();
        }
        return str;
    }
}
