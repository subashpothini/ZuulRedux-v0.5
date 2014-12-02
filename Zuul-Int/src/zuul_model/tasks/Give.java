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
        testItem = new Item("", "", 0);
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

        //Populate the array for testing and Item transfer
        Container[] array = new Container[2];
        this.populateArray(array, (Container) dObject, (Item) subject);
        this.populateArray(array, (Container) iObject, (Item) subject);

        //Test that there is a value in each array slot, and hence that only one of them contains the object
        if(array[0] == null || array[1] == null) {
            return false;
        }

        //perform swap and return true afterwards
        array[1].give((Item) subject);
        array[0].take((Item) subject);
        return true;
    }

    /*
    assigns teh container to the first slot of teh array if it has a copy og teh subject
    and teh second slot if not.
     */
    private void populateArray(Container[] array, Container container, Item item)
    {
        if(container.has(item)) {
            array[0] = container;
        } else {
            array[1] = container;
        }
    }
}
