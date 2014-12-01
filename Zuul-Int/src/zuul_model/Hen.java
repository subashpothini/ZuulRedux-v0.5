package zuul_model;
import java.util.*;

/**
 *
 * @author jdb
 */
public class Hen extends Mobile {
    final int eggs;
    final int layFrequency = 8;
    Random rand;
    
    /**
     * Create an egg-laying hen
     * @param room it starts in
     * @param eggs number of eggs it can lay
     */
    public Hen(Room room, int eggs)
    {
        super("a small brown Hen", room);
        rand = new Random();
        this.eggs = eggs;
        for (int i = 1; i <= eggs; i++)
            addItem(new Item("egg" + i, "a freshly laid egg.", 0.1));
    }
    
    /**
     * Lay an egg if we still have one
     */
    public void lay()
    {
        for (int i = 1; i <= eggs; i++)
            if (hasItem("egg" + i)) {
                getRoom().give(getItem("egg" + i));
                return;
            }
    }
    
    /**
     * Go to another room and possibly lay an egg
     */
    public void wander()
    {
        Room rooms[] = getRoom().adjoiningRooms().toArray(new Room[0]);
        if (rooms.length > 0) {
            changeRoom(rooms[rand.nextInt(rooms.length)]);
            if (rand.nextInt(layFrequency) == 0)
                lay();
        }
    }
}
