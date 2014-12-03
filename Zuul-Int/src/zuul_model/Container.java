package zuul_model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sam on 01/12/2014.
 */
public class Container {

    protected Map<String,Item> items; //pro
    protected List<Item> itemsList; //pro
    protected Item[] itemArray;

    public Container()
    {
        items = new HashMap<String, Item>();
        itemsList = new ArrayList<Item>();
    }

    public final boolean giveL(Item item)
    {
        itemsList.add(item);
        items.put(item.getName(), item);
        return true;
    }

    public final boolean take(Item item)
    {
        return itemsList.remove(item);
    }

    public final boolean has(Item item)
    {
        return items.containsValue(item);
    }

    public final Item get(String itemName)
    {
        return items.get(itemName);
    }

    public final boolean give(Item item)
    {
        items.put(item.getName(), item);
        this.giveL(item);
        return true;
    }

    public final boolean take(String itemName)
    {
        Item item = items.get(itemName);
        if(items.containsKey(itemName)) {
            this.take(itemsList.get(itemsList.indexOf(item)));
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
