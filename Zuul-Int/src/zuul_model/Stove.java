package zuul_model;

/**
 *
 * @author jdb
 * An extension to item with an additional bit of internal
 * state to record whether it is burning or not.
 */
public class Stove extends Item {
    private boolean burning = false; //pri
    
    public Stove(String name, String description, double weight)
    {
        super(name, description, weight);
    }
    
    /**
     * Light the stove
     */
    public void light()
    {
        burning = true;
    }
    
    /**
     * Test whether stove is lit or not
     * @return true if lit
     */
    public boolean hot()
    {
        return burning;
    }
    
    /**
     * Overridden method that adds info about the stove state
     * @param addName
     * @return descriptive string
     */
    public String longDescription(boolean addName)
    {
        String s;
        if (addName)
            s = getName() + ": " + this.examine();
        else
            s = this.examine();
        if (burning)
            s += " (HOT)";
        else
            s += " (COLD)";
        return s;
                    
    }
}
