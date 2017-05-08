import java.util.ArrayList;
/**
 * This class defines the constraints used when 
 * searching for statistics among the data
 * 
 * @author Logan Spencer
 * @version Project 4
 * 11/14/16
 *
 */
public class KeyConstraints extends ArrayList<Integer>
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private KeyConstraints next;
   
    /**
     * KeyConstraints constructor
     */
    public KeyConstraints()
    {
        // Creates this as an arrayList
        super();
        // Set default next value to nothing
        next = null;
    }
    
    /**
     * Gives the next KeyConstraints object
     * @return KeyConstraints object
     */
    public KeyConstraints getNext()
    {
        // Returns value of next either null or
        // -> KeyConstraints
        return next;
    }
   
    /**
     * Adds a KeyConstraints object to 
     * the list of KeyConstraints objects
     * 
     * @param elem The KeyConstraints element
     */
    public void addEnd(KeyConstraints elem)
    {
        // Holds temporary KeyContraint object
        KeyConstraints current = this;
       
        // Loop through all "set" KeyConstraints until finding
        // an empty child
        while (current.getNext() != null)
        {
            current = current.getNext();
        }
       
        // Assigning empty child the passed parameter
        current.next = elem;
    }
}