/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zuulredux.zuul_model;


import zuul_model.tasks.Task;

/**
 *
 * @author Sam
 */
public class Command_ {

    protected final Actionable subject, iObject, dObject;
    protected final Task verb;
    protected final Object[] list;

    public Command_(Task verb, zuulredux.zuul_model.Actionable subject, zuulredux.zuul_model.Actionable dObject, zuulredux.zuul_model.Actionable iObject) {
        list = new Actionable[3];
        list[0] = this.subject = subject;
        list[1] = this.dObject = dObject;
        list[2] = this.iObject = iObject;
    }

    public Task getVerb() {
        return verb;
    }

    public Actionable getSubject() {
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
