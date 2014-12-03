package zuul_model.tasks;

import zuul_model.Container;
import zuul_model.Item;

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
    }

    @Override
    /*
    @return name of Task
    Note: need to fix this to respond dynamically to context
     */
    public String getName()
    {
        return TAKE;
    }

    @Override
    /*
    @return true if all attributes are appropriate && action is performed successfully
    @return false otherwise
     */
    public boolean performAction() {
        //Test that the subject is an Item
        if (! subject.getClass().getName().equals(testItem.getClass().getName())) {
            return false;
        }

        //Test that the direct and indirect objects are Containers
        String containerID = testContainer.getClass().getName();
        if (! dObject.getClass().getName().equals(containerID)) {
            return false;
        }
        if (! iObject.getClass().getName().equals(containerID)) {
            return false;
        }

        // test that the iObject contains the Item
        if(!((Container) iObject).has((Item) subject)) {
            return false;
        }

        // that that the dObject does not contain the Item
        if(((Container) dObject).has((Item) subject)) {
            return false;
        }


        // transfer item to dObject from iObject
        ((Container) dObject).give((Item) subject);
        ((Container) iObject).take((Item) subject);
        return true;
    }

}
