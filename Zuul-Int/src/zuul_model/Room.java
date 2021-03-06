package zuul_model;

import zuul_model.tasks.Examine;
import zuul_model.tasks.Task;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kolling and David J. Barnes
 * Modified by John Bovey
 */

public class Room extends Container implements Actionable
{
    private final String description; //pri
    private Map<String, Room> exits = new HashMap<String, Room>();        // stores exits of this room. //pri //pri
    private List<Door> doors = new ArrayList<Door>();
    private ClickableList  clickable = new ClickableList();

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description)
    {
        this.description = description;
    }

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

    public void addDoor(Door door)
    {
        doors.add(door);
    }

    public Door getDoor(int index) {
        return doors.get(index);
    }

    public Actionable getActionable(Point point) {
        return clickable.checkPoint(point);
    }

    public void addClickable(Actionable actionable, Point pointLoc, Point pointSize) {
        clickable.addObject(actionable, pointLoc, pointSize);
    }

    /**
     * Destroy the item if it exists, otherwise quietly do nothing.
     * @param name of item
     */
    public void useItem(String name)
    {
        if (items.containsKey(name))
            items.remove(name);
    }
    
    /**
     * Return a reference to the item if it is in the room but
     * do not remove it.
     * @param name of item
     * @return reference to item
     * @throws zuul_model.ZuulException if no such item is in the room
     */
    public Item getItem(String name) throws ZuulException {
        if (items.containsKey(name))
            return items.get(name);
        throw new ZuulException("There is no " + name + " in the room!");
    }



    /**
    public List<Item> ()
    {
        return ItemList // need to implemet this
    }
    */

    /**
     * Get all the items in the room
     * @return the hashmap of items
     */
    public Map<String, Item> getItems()
    {
        return items;
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }
    
    /**
     * Test whether the room has this exit
     * @param name The name of the exit
     * @return true if room has exit
     */
    public boolean hasExit(String name)
    {
        return exits.containsKey(name);
    }
    
    /**
     * Return a set of all the exits this room has
     */
    public Set<String> getExits() {
        return exits.keySet();
    }
    
    /**
    Return a collection of all the rooms adjoining this one
     */
    Collection<Room> adjoiningRooms()
    {
        return exits.values();
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     *     A list of items in the room
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are in " + description + ".\n" + getExitString() + "\n" + getItemString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString() //pri
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }
    
    /**
     * Create a list of all the items in the room
     * @return Descriptive string
     */
    private String getItemString() //pri
    {
        Set<String> keys = items.keySet();
        if (keys.isEmpty())
            return "There are no items in the room.";
        String returnString = "The room contains:";
        for (String s: keys)
            returnString += "\n\t" + items.get(s).longDescription();
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
}

