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
public class Rules {
    
    private static Rules instance = null;
    private Map<String, Task> ruleList;
    
    protected Rules() {
        
    }
    
    public static Rules getRules()
    {
        if(instance == null) {
            instance = new Rules();
        }
    return instance;
    }
}
