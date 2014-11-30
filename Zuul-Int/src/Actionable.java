/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zuulredux.zuul_model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Sam
 */
public abstract class Actionable {
    
    private final String name;
    private Map<String, zuul_model.Command> commandMap;
    
    public Actionable(String name)
    {
        this.name = name;
        commandMap = new HashMap<String, zuul_model.Command>();
    }
    
    public String getName()
    {
        return name;
    }
    
    public int validCommand(zuul_model.Command testCommand)
    {
        
    }
}
