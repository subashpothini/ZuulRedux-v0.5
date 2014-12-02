package zuul_model;

import zuul_model.tasks.Examine;
import zuul_model.tasks.Task;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jdb
 */
public class Item implements Actionable {
    private final String name; //pri
    private final String description; //pri
    private double weight; //pri
    private List<Method> methodList;
    
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
    public String examine()
    {
        return description;
    }

    @Override
    public Task[] getTasks(Player player) {
        Task[] tasks = new Task[1];
        int index = 0;
        tasks[index] = new Examine(player, this, null); index++;

        return tasks;
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
