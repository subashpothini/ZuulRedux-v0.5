package zuul_model;
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
        rooms.put("kitchen", new Room("an old-fashioned kitchen"));
        rooms.put("passage", new Room("a passage"));
        rooms.put("larder", new Room("cool larder"));
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
        connect("passage", "west", "larder","east");
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
        addItem("larder", new Item("butter", "a pack of fresh butter", 0.5));
        addItem("larder", new Item("flour", "a large sack of flour", 100.0));
        addItem("larder", new Item("sugar", "a bag of sugar", 1.0));
        addItem("cellar", new Item("cake-tin", "a clean cake tin", 0.3));
        addItem("library", new Item("recipe", "a cake recipe using eggs, flour, sugar, butter and milk", 0.0));
        addItem("dairy", new Item("milk", "a jug of fresh milk", 1.0));
        addItem("courtyard", new Item("fire-wood", "a stack of dry fire wood", 15.0));
        addItem("attic", new Item("matches", "a box of matches", 0.0));
        addItem("shed", new Item("trolley", "a sack trolley", -100.0));
        addItem("kitchen", new Stove("stove", "an old but working wood-burning stove", 1000.0));
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
     * Attempt to make the move described by the command.
     * @param command
     * @return either SUCCESS (game won), QUIT or CONTINUE
     * @throws zuul_model.ZuulException if the command can not me obeyed
     */
    public GameResult move(Command__ command) throws ZuulException
    {
        Stove stove;
        Room room = player.getRoom();

        hen.wander();
        String commandWord = command.getCommandWord();
        if (commandWord.equals("go")) {
            player.changeRoom(command);
            return GameResult.CONTINUE;
        }
        if (commandWord.equals("take")) {
            player.takeItem(command.getSecondWord());
            return GameResult.CONTINUE;
        }
        if (commandWord.equals("drop")) {
            player.dropItem(command.getSecondWord());
            return GameResult.CONTINUE;
        }
        
        if (commandWord.equals("light")) {
            if (!room.has(command.getSecondWord())) {
                throw new ZuulException("There is no " + command.getSecondWord()
                        + " in this room!");
            }
            try {
                stove = (Stove)room.getItem(command.getSecondWord());
            }
            catch (ClassCastException e) {
                throw new ZuulException(command.getSecondWord() + " is not a stove!");
            }
            if (!hasItem("matches")) {
                throw new ZuulException("You have nothing to light it with!");
            }
            if (!room.has("fire-wood")) {
                throw new ZuulException("A stove needs fuel!");
            }
            stove.light();
            useItem("fire-wood");
            return GameResult.CONTINUE;
        }
        if (commandWord.equals("bake")) {
            if (!command.getSecondWord().equals("cake"))
                throw new ZuulException("You cannot bake one of those!");
            if (!hasItem("stove"))
                throw new ZuulException("There is no stove!");
            stove = (Stove)room.getItem("stove");
            if (!stove.hot())
                throw new ZuulException("You cannot make a cake in a cold stove.");
            if (!hasItem("recipe"))
                throw new ZuulException("You will need a cake recipe.");
            if (!hasItem("cake-tin"))
                throw new ZuulException("There is nothing to make it in.");
            String[] ingredients = {"butter", "sugar", "flour", "milk"};
            for (String ingredient: ingredients) {
                if (!hasItem(ingredient))
                    throw new ZuulException("You need "+ingredient+" to make a cake.");
            }
            String[] eggs = {"egg1", "egg2", "egg3"};
            for (String egg: eggs) {
                if (!hasItem(egg))
                    throw new ZuulException("You need 3 eggs to make a cake.");
            }
            for (String ingredient: ingredients) {
                useItem(ingredient);
            }
            room.give(new Item("cake", "A delicious freshly baked cake", 0.0));
            return GameResult.CONTINUE;
        }
        if (commandWord.equals("eat")) {
            String food = command.getSecondWord();
            if (food.equals("cake")) {
                if (hasItem("cake")) {
                    useItem("cake");
                    return GameResult.SUCCESS;
                }
                throw new ZuulException("There is no cake!");
            }
            throw new ZuulException("Yuk!!!");
        }
        if (commandWord.equals("quit"))
            return GameResult.QUIT;
        throw new ZuulException("Command not recognised");
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
