package zuul_model;
import java.util.*;

/**
 *
 * @author jdb
 */
public class Mobile implements Actionable, Container{

    private final String description;
    private Room currentRoom;
    private Map<String, Item> items = new HashMap<String, Item>();
    
    public Mobile(String description, Room room)
    {
        currentRoom = room;
        this.description = description;
    }

    public String examine()
    {
        return description;
    }



    @Override
    public boolean has(Item item) {
        if(items.containsValue(item)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean give(Item item) {
        items.put(item.getName(), item);
        return true;
    }

    @Override
    public boolean take(Item item) {
        if(items.remove(item.getName()) != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * mutator for room
     * @param room
     */
    public void changeRoom(Room room) {
        currentRoom = room;
    }
    
    /**
     * accessor for room
     * @return room where olayer is
     */
    public Room getRoom() {
        return currentRoom;
    }
        
    /**
     * Test if mobile is carrying the item
     * @param name of item
     * @return true or false
     */
    public boolean hasItem(String name)
    {
        return items.containsKey(name);
    }
    
    /**
     * Get the total weight of items currently carried
     * @return total weight
     */
    public double itemsWeight()
    {
        double weight = 0.0;
        for (Item i: items.values())
            weight += i.getWeight();
        return weight;
    }
    
    public int itemsCount()
    {
        return items.size();
    }
    
    public void addItem(Item item)
    {
        items.put(item.getName(), item);
    }
    
    /**
     * Remove the named item and return it
     */
    public Item getItem(String name) {
        return items.remove(name);
    }
    
    public boolean haveItem(String name)
    {
        return items.containsKey(name);
    }
    
        
    /**
     * Get all the items being carried
     * @return hashmap of items
     */
    public Collection<Item> getItems()
    {
        return items.values();
    }
    
    public Set<String> itemNames()
    {
        return items.keySet();
    }


}
