/**
 * This represents information about a single variable including:
 *      1. Name
 *      2. ID
 *      3. Text description 
 *      4. Whether it encodes info positively or negatively
 * @author Logan Spencer
 * @version Project3
 * 10/25/16
 */
public class DataDefinition
{
    /**
     * Name of the variable being defined
     */
    private String variableName;
    
    /**
     * The ID associated with variable
     */
    private String variableId;
    
    /**
     * The units the variable is measured in
     */
    private String unit;
    
    /**
     * Indicates whether data is encoded 
     * positively or negatively
     */
    private boolean positive;
    
    /**
     * A description of the variable specified
     */
    private String description;
    
    /**
     * Default constructor for DataDefinition object
     * 
     * @param variableName The name of the variable 
     * @param id The ID associated with the variable
     * @param unit The unit the variable is measured in
     * @param positive Determines if info is encoded 
     *        positively or negatively
     * @param description Description of the variable
     */
    public DataDefinition(String variableName, String id, 
            String unit, boolean positive, String description)
    {
        this.variableName = variableName;
        this.variableId = id;
        this.unit = unit;
        this.positive = positive;
        this.description = description;
    }
    
    /**
     * Gives the name of the variable
     * @return String with variable name
     */
    public String getVariableName()
    {
        return variableName;
    }
    
    /**
     * Gives the variable's id
     * @return String representing variable's id
     */
    public String getVariableId()
    {
        return variableId;
    }
    
    /**
     * Gives the units of the variable
     * @return String representing units of the variable
     */
    public String getUnit()
    {
        return unit;
    }
    
    /**
     * Tells if variable info coded 
     * positively or negatively
     * @return boolean indicating info encoding method
     */
    public boolean isPositive()
    {
        return positive;
    }
    
    /**
     * Gives description of the variable
     * @return String description of the variable
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Gives a string representation of variable components
     * @return String representation of variable
     */
    @Override
    public String toString()
    {
        return String.format("%s, %s (%s)", 
                variableId, variableName, unit);
    }
}
