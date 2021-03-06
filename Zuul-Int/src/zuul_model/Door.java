package zuul_model;

import zuul_model.tasks.Examine;
import zuul_model.tasks.Go;
import zuul_model.tasks.Task;

import java.awt.*;
import java.util.Random;

/**
 * Created by Sam on 02/12/2014.
 */

public class Door implements Actionable {

    private static final Point DEFAULT_SIZE = new Point(200,200);
    private final String description;
    private final Point size;
    private Room[] exits;


    public Door(String description, Room[] exits, Point size)
    {
        this.description = description;
        this.exits = exits;
        this.size = size;
        for(Room room : exits) {
            room.addDoor(this);
        }
    }

    public Door(String description, Room[] exits)
    {
        this.description = description;
        this.exits = exits;
        this.size = DEFAULT_SIZE;
        for(Room room : exits) {
            room.addDoor(this);
        }
    }

    @Override
     public Task[] getTasks(Player player) {
        Task[] tasks = new Task[2];
        int index = 0;
        tasks[index] = new Examine(player, this, null); index++;
        tasks[index] = new Go(player, this.getExit(player), null);

        return tasks;
    }

    @Override
    public String examine() {
        return description;
    }

    private Room getExit(Player player)
    {
        Room currentRoom = player.getRoom();
        Random rand = new Random();

        for(int i = 10; i > 0; i--) {
            int index = rand.nextInt(exits.length);
            if (currentRoom != exits[index]) {
                return exits[index];
            }
        }
        return currentRoom;
    }
}
