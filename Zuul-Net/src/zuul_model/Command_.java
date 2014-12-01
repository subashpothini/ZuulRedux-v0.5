/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zuulredux.zuul_model;


/**
 *
 * @author Sam
 */
public class Command_ {

    protected final Actionable subject, iObject, dObject;
    protected final zuul_model.Task verb;
    protected final Object[] list;

    public Command_(Task verb, Actionable subject, Actionable dObject, Actionable iObject) {
        list = new Object[4];
        list[0] = this.verb = verb;
        list[1] = this.subject = subject;
        list[2] = this.dObject = dObject;
        list[3] = this.iObject = iObject;
    }

    public Task getVerb() {
        return verb;
    }

    public Actionable getSub() {
        return subject;
    }

    public Actionable getDObject() {
        return dObject;
    }

    public Actionable getIObject() {
        return iObject;
    }

    public Object[] getList() {
        return list;
    }
}
