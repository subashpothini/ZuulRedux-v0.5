/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zuul_model;



/**
 *
 * @author Sam
 */
public class Command {

    protected final Actionable subject, iObject, dObject; //pro
    protected final Object[] list; //pro

    public Command(zuul_model.Actionable subject, zuul_model.Actionable dObject, zuul_model.Actionable iObject) {
        list = new Actionable[3];
        list[0] = this.subject = subject;
        list[1] = this.dObject = dObject;
        list[2] = this.iObject = iObject;
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
