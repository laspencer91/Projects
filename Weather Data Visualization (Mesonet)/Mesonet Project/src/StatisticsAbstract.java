/**
 * 
 * @author skylarsmith
 * @version 10/5/16
 * Project2
 * 
 * Represents any object that statistics can
 * be computed over, such as DataDay 
 *
 */
public abstract class StatisticsAbstract
{
    /** 
     * Gets the day with the minimum of whatever
     * statistic is being measured 
     * 
     * @param statisticId The id of the stat being measured
     * @param constraints The year constraints
     * @return DataDay object with min stat
     */
    public abstract DataDay getStatisticMinDay(String statisticId,
            KeyConstraints constraints);
    /** 
     * Gets the day with the maximum of whatever
     * statistic is being measured 
     * 
     * @param statisticId The id of the stat being measured
     * @param constraints The year constraints
     * @return DataDay object with max stat
     */
    public abstract DataDay getStatisticMaxDay(String statisticId, 
            KeyConstraints constraints);
    /** 
     * Gets the statistical average of the stat as a Sample
     * 
     * @param statisticId The id of the stat being measured
     * @param constraints The year constraints
     * @return Sample object with average stat
     */
    public abstract Sample getStatisticAverage(String statisticId,
            KeyConstraints constraints);
}
