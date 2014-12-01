package zuul_model;

import zuul_model.Item;

/**
 * Created by Sam on 01/12/2014.
 */
public interface Container {

    public boolean has(Item item);
    public boolean give(Item item);
    public boolean take(Item item);
}
