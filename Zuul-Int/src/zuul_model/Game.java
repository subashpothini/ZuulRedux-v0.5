package zuul_model;
import java.awt.*;
import java.util.*;


/**
 */
public class Game 
{
    protected Player player; //pro
    private Hen hen; //pri
    HashMap<String, Room> rooms = new HashMap<String, Room>();
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        player = new Player("this is a player of the game", rooms.get("larder"));
        hen = new Hen(rooms.get("henhouse"), 3);
    }

    public Player getPlayer() //pri
    {
        return player;
    }

    /**
     * Connect room 1 and room 2
     * @param room1
     * @param exit1 of room1 leads to room2
     * @param room2
     * @param exit2 of room2 leads to room1
     */
    private void connect(String room1, String exit1, String room2, String exit2) //pri
    {
        rooms.get(room1).setExit(exit1, rooms.get(room2));
        rooms.get(room2).setExit(exit2, rooms.get(room1));
    }
    
    /**
     * Add the item to the room
     * @param room
     * @param item
     */
    private void addItem(String room, Item item) //pri
    {
        rooms.get(room).give(item);
    }
    
    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() //pri
    {
      
        // create the rooms
        Room larder = new Room("cool larder");
        rooms.put("kitchen", new Room("an old-fashioned kitchen"));
        rooms.put("passage", new Room("a passage"));
        rooms.put("larder", larder);
        rooms.put("courtyard", new Room("a cobbled courtyard"));
        rooms.put("shed", new Room("a large storage shed"));
        rooms.put("henhouse", new Room("a hen house"));
        rooms.put("dairy", new Room("a dairy"));
        rooms.put("attic", new Room("a gloomy attic"));
        rooms.put("hallway", new Room("a hallway"));
        rooms.put("library", new Room("a library with bookshelves"));
        rooms.put("cellar", new Room("a cellar"));

        // create doors
        int i = 0;
        Door larderDoor = new Door("door " + 1, new Room[] {rooms.get("larder"), rooms.get("passage")});
        rooms.get("larder").addClickable(larderDoor, new Point(440, 250), new Point(120,170));
        this.makeDoor("passage","larder", i); i++;
        this.makeDoor("passage","larder", i); i++;
        this.makeDoor("passage","kitchen", i); i++;
        this.makeDoor("passage","cellar", i); i++;
        this.makeDoor("kitchen","courtyard", i); i++;
        this.makeDoor("kitchen","hallway", i); i++;
        this.makeDoor("hallway", "attic", i); i++;
        this.makeDoor("hallway","library", i); i++;
        this.makeDoor("courtyard","dairy", i); i++;
        this.makeDoor("courtyard","henhouse", i); i++;
        this.makeDoor("courtyard", "shed", i);
        i++;

        // initialise room exits
        connect("passage", "west", "larder", "east");
        connect("passage", "east", "kitchen", "west");
        connect("passage", "down", "cellar", "up");
        connect("kitchen", "east", "courtyard", "west");
        connect("kitchen", "south", "hallway", "north");
        connect("hallway", "up", "attic", "down");
        connect("hallway", "south", "library", "north");
        connect("courtyard", "south", "dairy", "north");
        connect("courtyard", "east", "henhouse", "west");
        connect("courtyard", "north", "shed", "south");
        
        // add some items
        Item item;
        item = new Item("butter", "a pack of fresh butter", 0.5, rooms.get("larder"));
        addItem("larder", item);

        item = new Item("flour", "a large sack of flour", 100.0, rooms.get("larder"));
        addItem("larder", item);

        item = new Item("sugar", "a bag of sugar", 1.0, rooms.get("larder"));
        addItem("larder", item);
        larder.addClickable(item, new Point(1400,120), new Point(150,130));

        addItem("cellar", new Item("cake-tin", "a clean cake tin", 0.3, rooms.get("cellar")));
        addItem("library", new Item("recipe", "a cake recipe using eggs, flour, sugar, butter and milk", 0.0, rooms.get("library")));
        addItem("dairy", new Item("milk", "a jug of fresh milk", 1.0, rooms.get("dairy")));
        addItem("courtyard", new Item("fire-wood", "a stack of dry fire wood", 15.0, rooms.get("courtyard")));
        addItem("attic", new Item("matches", "a box of matches", 0.0, rooms.get("attic")));
        addItem("shed", new Item("trolley", "a sack trolley", -100.0, rooms.get("shed")));
        addItem("kitchen", new Stove("stove", "an old but working wood-burning stove", 1000.0, rooms.get("kitchen")));

    }

    private void makeDoor(String start, String end, int index)
    {
        Room[] links = {rooms.get(start), rooms.get(end)};
        new Door("door " + index, links);
    }

    /**
     * Determine whether an item is available
     * @param name the item
     * @return true if the item is held by the player or is in the room
     */
    private boolean hasItem(String name) //pri
    {
        return player.hasItem(name) || player.getRoom().has(name);
    }
    
    /**
     * The item is used (i.e. destroyed) if it is available
     * @param name item name
     */
    private void useItem(String name) //pri
    {
        player.useItem(name);
        player.getRoom().useItem(name);
    }
    

    
    /**
     * Print out a description of the current state of the game.
     */
    public void show()
    {
        System.out.println(player.getRoom().getLongDescription());
        System.out.println(player.getItemsDescription());
    }

}
