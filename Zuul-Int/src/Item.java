package zuul_model;

/**
 *
 * @author jdb
 */
public class Item {
    private String name;
    private String description;
    private double weight;
    
    public Item(String name, String description, double weight)
    {
        this.name = name;
        this.description = description;
        this.weight = weight;
    }
    
    /**
     * Accessor for the item's name
     * @return name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Accessor for item's description
     * @return description
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Accessor for item's weight in killograms
     * Negative weights are carrying capacities.
     * @return weight
     */
    public double getWeight()
    {
        return weight;
    }
    
    /**
     * More detailed description
     * @param addName include the item's name
     * @return description with weight or carrying capacity
     */
    public String longDescription(boolean addName)
    {
        String s = description;
        if (addName)
            s = name + ": " + s;
        if (weight > 0)
            return s + " weighing " + weight + " kilos";
        else if (weight < 0)
            return s + " to carry " + -weight + " kilos";
        else
            return s;          
    }
    
    /**
     * Return long description with name
     * @return
     */
    public String longDescription()
    {
        return longDescription(true);
    }

}
