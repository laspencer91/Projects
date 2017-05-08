/**
*
 * @author CS2334.  Modified by: Logan Spencer
* @version Project1
*
 * Date: 2016-09-10
* Project 1
*
* This class represents individual, real-valued samples.  This class
* explicitly addresses the fact that some samples are invalid.
*
*/
 
public class Sample
{
    /** The observed value */
    private double value;
   
    /** Indicates whether the observation is a valid one */
    private boolean valid;
   
    /** Default Constructor sets valid to false */
    public Sample()
    {
        valid = false;
    }
 
    /**
     * Constructor sets valid to true if input is greater than -900
     * @param value is a double to be tested in the constructor.
     */
    public Sample(double value)
    {
        this.value = value;
        
        if (value > -900.00)
        {
            valid = true;
        }
        else
        {
            valid = false;
        }
    }
 
    /**
     * Returns valid variable
     * @return true or false
     */
    public Boolean isValid()
    {
        return valid;
    }
 
    /**
     * Checks if inputed sample is of less value than compared sample.
     * @param s is the inputed sample object to which to compare to
     * @return true or false
     */
    public boolean isLessThan(Sample s)
    {
        if (!this.valid && s.isValid()) {
            return false;
        }
        else if (!this.valid && !s.isValid()) {
            return true;
        }
        else if (this.valid && !s.isValid()) {
            return true;
        }
        else
        {
            return this.getValue() < s.getValue();
        }
    }
   
    /**
     * Checks if inputed sample is of greater value than compared sample.
     * @param s is the inputed sample object to which to compare to
     * @return true or false
     */
    public boolean isGreaterThan(Sample s)
    {
        if (!this.valid && s.isValid()) {
            return false;
        }
        else if (!this.valid && !s.isValid()) {
            return true;
        }
        else if (this.valid && !s.isValid()) {
            return true;
        }
        else
        {
            return this.getValue() > s.getValue(); 
        }
    }
   
    /**
     * Returns double value variable
     * @return value set from constructor
     */
    public double getValue()
    {
        return value;
    }
 
    /**
     * Format String to 4 decimal places
     * or if invalid return "invalid"
     * @return String representation of the Sample
     */
    public String toString()
    {
        if (valid)
        {
            return String.format("%6.4f", value);
        }
        else
        {
            return "invalid";
        }
    }
}