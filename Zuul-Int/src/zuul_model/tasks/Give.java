package zuul_model.tasks;

import zuul_model.Actionable;
import zuul_model.Item;

/**
 * Created by Sam on 01/12/2014.
 */
public class Give {

    private zuul_model.Actionable subject;
    private zuul_model.Actionable dObject;
    private zuul_model.Actionable iObject;
    private zuul_model.Item item;

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
        item = new Item("", "", 0);
    }

    @Override
    /*
    @return true if all attributes are appropriate && action is performed successfully
    @return false otherwise
     */
    public boolean performAction() {
        //Test that the input values are appropriate.
        if (subject.className() != Item.getClass().getName()) {
            return false;
        }
        if (! this.implementsContainer(dObject)) {
            return false;
        }
        if (! this.implementsContainer(iObject)) {
            return false;
        }

        //perform action
        if(iObject.has(subject)) {
            dObject.give(subject);
            iObject.take(subject);
            return true
        }
        return false;
    }

    private boolean implementsContainer(Actionable test) {
        Class[] interface_list = test.class().getInterfaces();
        String container_name = Container.getName();
        for(Class interface_item : interface_list) {
            if (interface_item.getName() == container_name) {
                return true;
            }
        }
        return false;
    }
}
