package zuul_model.tasks;

import zuul_model.Player;
import zuul_model.Room;

/**
 * Created by Sam on 01/12/2014.
 */
public class Go implements Task{

    private zuul_model.Actionable subject;
    private zuul_model.Actionable dObject;
    private zuul_model.Actionable iObject;

    /*
    go task: 'subject' goes to 'direct object'
    @param subject (Player) the player that is traveling (change currentRoom)
    @param dObject (Room) the Room the player is going to
    @param iObject (Actionable) -not used-
     */
    public Go (zuul_model.Actionable subject,
                    zuul_model.Actionable dObject,
                    zuul_model.Actionable iObject) {
        this.subject = (Player) subject;
        this.dObject = dObject;
        this.iObject = iObject;
    }

    @Override
    /*
    @return true if all attributes are appropriate && action is performed successfully
    @return false otherwise
     */
    public boolean performAction() {
        //Test that the input values are appropriate.
        if ((subject.getClass().className() != Player.className()) || (dObject.getName() != Room.getName())) {
            return false;
        }

        //perform action
        subject.changeRoom(dObject);
        return true;
    }
}
