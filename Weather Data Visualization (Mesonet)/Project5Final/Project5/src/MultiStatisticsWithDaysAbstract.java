/**
 * Abstract class: 
 * - contains sub-objects over which statistics can be 
 * computed (MultiStatisticsAbstract)
 * - Requires a method for adding a day.
 * 
 * @author CS2334, given
 * @version 2016-11-10
 *
 */
public abstract class MultiStatisticsWithDaysAbstract extends 
    MultiStatisticsAbstract
{
    /** Adds a day to the data structure
     * 
     * @param day The day to be added
     */
    protected abstract void addDay(DataDay day);
}
