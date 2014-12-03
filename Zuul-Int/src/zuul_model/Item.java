package zuul_model;

import zuul_model.tasks.Examine;
import zuul_model.tasks.Give;
import zuul_model.tasks.Task;

import java.awt.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jdb
 */
public class Item implements Actionable {

    private static final Point DEFAULT_SIZE = new Point(200,200);
    private final String name; //pri
    private final String description; //pri
    private final Point size;
    private double weight; //pri
    private Container owner;

    public Item(String name, String description, double weight, Container owner, Point size)
    {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.owner = owner;
        this.size = size;
    }

    public Item(String name, String description, double weight, Container owner)
    {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.owner = owner;
        size = DEFAULT_SIZE;
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
        ArrayList<Task> tempTask = new ArrayList<Task>();

        // create Examine Task
        tempTask.add(new Examine(player, this, null));

        // create Give task (if applicable)
        if(player == owner) {
            tempTask.add(new Give(this, player.getRoom(), player));
            taskNumber++;
        } else if (player.getRoom() == owner) {
            tempTask.add(new Give(this, player, player.getRoom()));
            taskNumber++;
        }



        Task[] tasks = tempTask.toArray(new Task[]);



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

    public Container getOwner() {
        return owner;
    }

    public void setOwner(Container owner) {
        this.owner = owner;
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
