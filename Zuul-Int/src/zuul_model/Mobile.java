package zuul_model;
import java.util.*;

/**
 *
 * @author jdb
 */
public class Mobile {
    private Room currentRoom;
    private Map<String, Item> items = new HashMap<String, Item>();
    
    public Mobile(Room room)
    {
        currentRoom = room;
    }
       
    /**
     * mutator for room
     * @param room
     */
    public void setRoom(Room room) {
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
