/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zuul_model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Sam
 */
public abstract class Actionable {
    
    private final String name, description;
    private Map<String, zuul_model.Command> commandMap;
    
    public Actionable(String name, String description)
    {
        this.name = name;
        this.description = description;
        commandMap = new HashMap<String, zuul_model.Command>();
    }
    
    public String getName()
    {
        return name;
    }

    public String examine()
    {
        return description;
    }
}
