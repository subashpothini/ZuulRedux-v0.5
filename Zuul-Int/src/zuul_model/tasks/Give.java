package zuul_model.tasks;

import zuul_model.Actionable;
import zuul_model.Container;
import zuul_model.Item;

/**
 * Created by Sam on 01/12/2014.
 */
public class Give implements Task{

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
    @return true if all attributes are appropriate && action is performed successfully
    @return false otherwise
     */
    public boolean performAction() {
        //Test that the input values are appropriate.
        if (! subject.getClass().getName().equals(testItem.getClass().getName())) {
            return false;
        }
        Item subjectItem = (Item) subject;
        String containerID = testContainer.getClass().getName();
        if (! dObject.getClass().getName().equals(containerID)) {
            return false;
        }
        Container dObjectContainer = (Container) dObject;
        if (! iObject.getClass().getName().equals(containerID)) {
            return false;
        }
        Container iObjectContainer = (Container) iObject;

        //perform action
        if(iObjectContainer.has(subjectItem)) {
            dObjectContainer.give(subjectItem);
            iObjectContainer.take(subjectItem);
            return true;
        }
        return false;
    }
}
