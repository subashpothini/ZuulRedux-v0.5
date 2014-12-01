package zuul_model.tasks;

import zuul_model.Player;
import zuul_model.tasks.Task;

/**
 * Created by Sam on 01/12/2014.
 */
public class Examine implements Task{

    private zuul_model.Actionable subject;
    private zuul_model.Actionable dObject;
    private zuul_model.Actionable iObject;

    /*
    examine task: 'subject' examines 'direct object'
    @param subject (Player) the player examining the object
    @param dObject (Actionable) the player/item/room being examined (all Auctionable objects have examine() method)
    @param iObject (Actionable) -not used-
     */
    public Examine (zuul_model.Actionable subject,
                    zuul_model.Actionable dObject,
                    zuul_model.Actionable iObject) {
        this.subject = subject;
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
        if (subject.className() != Player.className()) {
            return false;
        }

        //perform action
        subject.inform(dObject.examine());
        return true;
    }
}
