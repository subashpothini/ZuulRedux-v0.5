package zuul_model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sam on 01/12/2014.
 */
public abstract class Container_ {

    protected Map<String,Item> items;
    protected List<Item> itemsList;

    public Container_()
    {
        items = new HashMap<String, Item>();
        itemsList = new ArrayList<Item>();
    }

    public final boolean giveL(Item item)
    {
        itemsList.add(item);
        return true;
    }

    public final boolean take(Item item)
    {
        return itemsList.remove(item);
    }

    public final boolean has(Item item)
    {
        return has(item);
    }

    public final boolean give(Item item)
    {
        items.put(item.getName(), item);
        this.giveL(item);
        return true;
    }

    public final boolean take(String itemName)
    {
        if(items.containsKey(itemName) == true) {
            this.take(itemsList.get(itemsList.indexOf(itemName)));
            items.remove(itemName);
            return true;
        } else {
            return false;
        }
    }

    public final boolean has(String itemName)
    {
        return items.containsKey(itemName);
    }
}
