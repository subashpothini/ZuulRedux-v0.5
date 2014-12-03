package zuul_model;

import com.sun.org.apache.xpath.internal.SourceTree;

/**
 *
 * @author jdb
 */
public class Player extends Mobile
{

    private static final double maxWeight = 15.0; // Max kilos that can be carried //pri
    private static final int maxItems = 2; // Maximum number of items that can be carried //pri
        
    public Player(String description, Room room) {
        super(description, room);
    }


    /**
     * Player attempts to take the item from the room. If successful the item is
     * transferred from the room to the player.
     * @param name of item
     * @throws zuul_model.ZuulException if item cannot be taken
     */
    public void takeItem(String name) throws ZuulException
    {
        if (itemsCount() == maxItems) {
            throw (new ZuulException("You only have two hands."));
        }
        Item item = getRoom().getItem(name);
        double weight = item.getWeight() + itemsWeight();
        if (weight > maxWeight) {
            throw (new ZuulException("You can only carry " + maxWeight + " kilos."));
        }
        this.give(getRoom().get(name));
        this.getRoom().take(name);
    }

    /*
    !!!!implement
    delivers message to the player
    @param message the message top give to the player
     */
    public void inform(String message)
    {
        System.out.println(message);
    }
    
    /**
     * Player attempts to leave the item in the room
     * @param name
     * @throws zuul_model.ZuulException if item is not being carried or if
     *       the remaining items would be too heavy.
     */
    public void dropItem(String name) throws ZuulException
    {
        if (!haveItem(name)) {
            throw new ZuulException("I don't have a " + name);
        }
        
        // We need to check the weight since the dropped item may have
        // negative weight.
        Item item = getItem(name);
        if (itemsWeight() > maxWeight) {
            addItem(item);
            throw (new ZuulException("You can only carry " + maxWeight + " kilos."));
        }
        getRoom().give(item);
    }
    
    /**
     * Destroy the item if it exists but quietly do nothing
     * if it does not.
     * @param name of item
     */
    public void useItem(String name)
    {
        if (haveItem(name))
            getItem(name);        
    }
  
    /**
     * Return a text description of all the items being carried
     * @return the description string
     */
    public String getItemsDescription()
    {
        if (itemsCount() == 0) {
            return "You are not carrying anything";
        }
        String returnString = "You are carrying:";
        for (Item item: getItems()) {
            returnString += "\n\t" + item.longDescription();
        }
        return returnString;
    }
    


}
