/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zuul_model;


import zuul_model.tasks.Task;

/**
 *
 * @author Sam
 */
public interface Actionable {

    public String examine();

    public Task[] getTasks(Player player);
}
