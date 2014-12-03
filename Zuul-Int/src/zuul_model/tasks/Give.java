package zuul_model.tasks;

import zuul_model.Container;
import zuul_model.Item;
import zuul_model.Player;
import zuul_model.Room;

/**
 * Created by Sam on 01/12/2014.
 */
public class Give implements Task{

    private static String TAKE = "Take", DROP = "Drop";
    private zuul_model.Actionable subject;
    private zuul_model.Actionable dObject;
    private zuul_model.Actionable iObject;
    private zuul_model.Item testItem;
    private zuul_model.Container testContainer;
    private final String testPlayer;

    /*
    go task: 'subject' is taken from 'indirect object' and given to 'direct object'
    @param subject (Item) the Item to be moved (passive)
    @param dObject (Container) the Container that is given the subject
    @param iObject (Container) the Container the subject is taken from
    Note: the two objects can be reversed, so long as one of them contains the subject it will be transferred to the other
    Addendum: place container with Item in iObject (useful for dynamically changing name of Task
     */
    public Give (zuul_model.Actionable subject,
                    zuul_model.Actionable dObject,
                    zuul_model.Actionable iObject) {
        this.subject = subject;
        this.dObject = dObject;
        this.iObject = iObject;
        testItem = new Item("", "", 0, null);
        testContainer = new Container();
        testPlayer = (new Player("", new Room(""))).getClass().getName();
    }

    @Override
    /*
    @return name of Task
    Note: need to fix this to respond dynamically to context
     */
    public String getName()
    {
        boolean test;

        test = subject.getClass().getName().equals(testItem.getClass().getName());
        assert test == true : "(Task) Give.subject is not an Item";
        if(test) {
            if(((Item)subject).getOwner() == iObject) {
                return DROP;
            }
        }
        return TAKE;
    }

    @Override
    /*
    @return true if all attributes are appropriate && action is performed successfully
    @return false otherwise
     */
    public boolean performAction() {

        //create test boolean
        boolean test;

        //Test that the subject is an Item
        test = ! subject.getClass().getName().equals(testItem.getClass().getName());
        assert test == true : "(Task) Give.subject is not an Item";

        //Test that the direct object are Containers
        String containerID = testContainer.getClass().getName();
        test = ! dObject.getClass().getName().equals(containerID);
        assert test == true : "(Task) Give.dObject is not a Container";

        //Test that the indirect object are Containers
        test = ! iObject.getClass().getName().equals(containerID);
        assert test == true : "(Task) Give.iObject is not a Container";

        // test that the iObject contains the Item
        test = !((Container) iObject).has((Item) subject);
        assert test == true : "(Task) Give.iObject does not possess the (Item) subject";

        //test that that the dObject does not contain the Item
        test = ((Container) dObject).has((Item) subject);
        assert test == true : "both (Task) Give.dObject and (Task) Give.iObject possess the (Item) subject";

        // transfer item to dObject from iObject
        ((Container) dObject).give((Item) subject);
        ((Container) iObject).take((Item) subject);

        // inform Player (if dObject or iObject is a Player) that they have picked up/dropped an item
        if(dObject.getClass().getName().equals(testPlayer)) {
            ((Player) dObject).inform("you have picked up " + subject.examine());
        } else if (iObject.getClass().getName().equals(testPlayer)) {
            ((Player) iObject).inform("you have dropped " + subject.examine());
        }

        // return true only if all tasks have been completed
        return true;
    }

}
