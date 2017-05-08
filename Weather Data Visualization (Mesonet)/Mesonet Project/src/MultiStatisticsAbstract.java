import java.util.Iterator;
 
/**
*
 * @author skylarsmith
* @version 10/5/16
* Project2
*
 * Represents any object that computes data over
 * sub-objects, such as DataMonth, DataYear, and DataSet
*
*/
public abstract class MultiStatisticsAbstract extends StatisticsAbstract
    implements Iterable<Integer>
{
    /**
     * Get a specified item in a set of data
     * @param key The key of the item to be retrieved.
     * @return The item specified by the key containing data
     */
    protected abstract StatisticsAbstract getItem(Integer key);
   
    /**
     * Gets the day with the minimum given statistic
     *
     * @param statisticId The id of the statistic measured
     * @param constraints The constraints of the search
     * @return DataDay
     */
    public DataDay getStatisticMinDay(String statisticId, 
            KeyConstraints constraints)
    {
        Iterator<Integer> iterator = this.iterator();
        DataDay min = new DataDay();
       
        // Run through iterations
        while (iterator.hasNext())
        {
            int nextVal = iterator.next();
            DataDay tempDay = new DataDay();
            
            // If there is no keyConstraint 
            if (constraints == null)
            {
                // Collect data
                tempDay = getItem(nextVal)
                        .getStatisticMinDay(statisticId, 
                                constraints);
            }
            // If there is a constraint on the search
            else if (constraints.contains(nextVal))
            {
                // Collect data
                tempDay = getItem(nextVal)
                        .getStatisticMinDay(statisticId, 
                                constraints.getNext());
            }   
            
            // If the current day being checked has a greater average then
            // -> the previously saved day, Change the max to new day
            if (tempDay.getStatisticAverage(statisticId, constraints)
                    .isLessThan(min.getStatisticAverage(
                            statisticId, constraints)))
            {
                // Change max to the new day.
                min = tempDay;
            }
        }
        return min;   
    }
   
    /**
     * Gets the day with the maximum given statistic
     * 
     * @param statisticId The id of the statistic measured
     * @param constraints The constraints of the search
     * @return DataDay
     */
    public DataDay getStatisticMaxDay(String statisticId, 
            KeyConstraints constraints)
    {
        Iterator<Integer> iterator = this.iterator();
        DataDay max = new DataDay();
       
        // Run through iterations
        while (iterator.hasNext())
        {
            int nextVal = iterator.next();
            DataDay tempDay = new DataDay();
            
            // If there is no keyConstraint 
            if (constraints == null)
            {
                // Collect data
                tempDay = getItem(nextVal)
                        .getStatisticMaxDay(statisticId, 
                                constraints);
            }
            // If there is a constraint on the search
            else if (constraints.contains(nextVal))
            {
                // Collect data
                tempDay = getItem(nextVal)
                        .getStatisticMaxDay(statisticId, 
                                constraints.getNext());
            }   
            
            // If the current day being checked has a greater average then
            // -> the previously saved day, Change the max to new day
            if (tempDay.getStatisticAverage(statisticId, constraints)
                    .isGreaterThan(max.getStatisticAverage(
                            statisticId, constraints)))
            {
                // Change max to the new day.
                max = tempDay;
            }
        }
        return max;   
    }
    
    /**
     * Gets the average of the given statistic
     * 
     * @param statisticId The id of the statistic measured
     * @param constraints The constraints of the search
     * @return Sample
     */
    public Sample getStatisticAverage(String statisticId, 
            KeyConstraints constraints)
    {
        Iterator<Integer> iterator = this.iterator();
        double total = 0.0;
        int countNum = 0;
       
        // Run through iterations
        while (iterator.hasNext())
        {
            // Check itemValue of index "iterator.next" to be a valid sample
            Integer nextVal = iterator.next();
            Sample sample = new Sample();
            
            // If there is no keyConstraint 
            if (constraints == null)
            {
                // Collect data
                sample = getItem(nextVal)
                        .getStatisticAverage(statisticId, constraints);
            }
            // If constraints holds the key
            else if (constraints.contains(nextVal))
            {
                // Collect data
                sample = getItem(nextVal).getStatisticAverage(
                        statisticId, constraints.getNext());
            }
            
            if (sample.isValid())
            {
                // Add value of this specific day to the total
                total += sample.getValue();
                countNum ++;
            }
        }
        // Return average as a Sample Object
        if (countNum == 0)
        {
            // If all samples are invalid
            return new Sample();
        }
        return new Sample(total / countNum);
    }
}
