package zuul_model.tasks;

import zuul_model.Player;
import zuul_model.Room;

/**
 * Created by Sam on 01/12/2014.
 */
public class Examine implements Task{

    private static String NAME = "Examine";
    private zuul_model.Actionable subject; //pri
    private zuul_model.Actionable dObject; //pri
    private zuul_model.Actionable iObject; //pri
    private Player testPlayer; //pri

    /*
    examine task: 'subject' examines 'direct object'
    @param subject (Player) the player examining the object
    @param dObject (Actionable) the player/item/room being examined (all Actionable objects have examine() method)
    @param iObject (Actionable) -not used-
     */
    public Examine (zuul_model.Actionable subject,
                    zuul_model.Actionable dObject,
                    zuul_model.Actionable iObject) {
        this.subject = subject;
        this.dObject = dObject;
        this.iObject = iObject;
        testPlayer = new Player("", new Room(""));
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

        //Test that the subject is a Player
        test = subject.getClass().getName() != testPlayer.getClass().getName();
        assert test == true : "(Task) Examine.subject is not a Player";

        //perform action
        ((Player) subject).inform(dObject.examine());
        return true;
    }
}
