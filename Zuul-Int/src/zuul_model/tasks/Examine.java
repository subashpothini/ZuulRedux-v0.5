package zuul_model.tasks;

import zuul_model.Player;
import zuul_model.Room;

/**
 * Created by Sam on 01/12/2014.
 */
public class Examine implements Task{

    private zuul_model.Actionable subject;
    private zuul_model.Actionable dObject;
    private zuul_model.Actionable iObject;
    private Player testPlayer;

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
    @return true if all attributes are appropriate && action is performed successfully
    @return false otherwise
     */
    public boolean performAction() {
        //Test that the input values are appropriate & replace test value with subject
        if (subject.getClass().getName() != testPlayer.getClass().getName()) {
            return false;
        } else {
            testPlayer = (Player) subject;
        }

        //perform action
        testPlayer.inform(dObject.examine());
        return true;
    }
}
