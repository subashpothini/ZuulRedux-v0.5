package zuul_model.tasks;

import zuul_model.Container;
import zuul_model.Item;
import zuul_model.Player;
import zuul_model.Room;

/**
 * Created by Sam on 01/12/2014.
 */
public class Go implements Task{

    private static String NAME = "Go";
    private zuul_model.Actionable subject;
    private zuul_model.Actionable dObject;
    private zuul_model.Actionable iObject;
    private Player testPlayer;
    private Room testRoom;

    /*
    go task: 'subject' goes to 'direct object'
    @param subject (Player) the player that is traveling (change currentRoom)
    @param dObject (Room) the Room the player is going to
    @param iObject (Actionable) -not used-
     */
    public Go (zuul_model.Actionable subject,
                    zuul_model.Actionable dObject,
                    zuul_model.Actionable iObject) {
        this.subject = subject;
        this.dObject = dObject;
        this.iObject = iObject;
        testRoom = new Room("");
        testPlayer = new Player("", testRoom);
    }

    @Override
    /*
    @return name of Task
     */
    public String getName()
    {
        return NAME;
    }

    @Override
    /*
    @return true if all attributes are appropriate && action is performed successfully
    @return false otherwise
     */
    public boolean performAction() {

        // create test boolean
        boolean test;

        //Test that the subject is a Player object
        test = ( ! subject.getClass().getName().equals(testPlayer.getClass().getName()));
        assert test == true : "(Task) Go.subject is not a Player";

        //test that the dObject is a Room object
        test = ( ! dObject.getClass().getName().equals(testRoom.getClass().getName()));
        assert test == true : "(Task) Go.dObject is not a Room";

        //perform action
        ((Player) subject).changeRoom((Room) dObject);
        ((Player) subject).inform("you have entered " + dObject.examine());

        return true;
    }
}
