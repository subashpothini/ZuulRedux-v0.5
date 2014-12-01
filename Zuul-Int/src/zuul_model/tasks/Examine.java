package zuul_model.tasks;

import zuul_model.tasks.Task;

/**
 * Created by Sam on 01/12/2014.
 */
public class Examine implements Task{

    private zuulredux.zuul_model.Actionable subject;
    private zuulredux.zuul_model.Actionable dObject;
    private zuulredux.zuul_model.Actionable iObject;

    /*
    examine task: 'subject' examines 'direct object'
    @param subject (Player) the player examining the object
    @param dObject (Actionable) the player/item/room being examined (all Auctionable objects have examine() method)
     */
    public Examine (zuulredux.zuul_model.Actionable subject,
                    zuulredux.zuul_model.Actionable dObject,
                    zuulredux.zuul_model.Actionable iObject) {
        this.subject = subject;
        this.dObject = dObject;
        this.iObject = iObject;
    }

    @Override
    public boolean performAction() {
        //Test that the input values are appropriate.
        if (subject.className() != Player.className()) {
            return false;
        }
        subject.inform(dObject.examine());
    }


}
