package zuul_model;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Sam on 02/12/2014.
 */

public class ClickableList {

    private final static int OBJECT = 0, LOCATION = 1, SIZE = 2;


    private ArrayList[] array = new ArrayList[3];
    private ArrayList<Actionable> itemsArray = new ArrayList<Actionable>();
    private ArrayList<Point> locationArray = new ArrayList<Point>();
    private ArrayList<Point> sizeArray = new ArrayList<Point>();

    public ClickableList() {
        array[OBJECT] = itemsArray;
        array[LOCATION] = locationArray;
        array[SIZE] = sizeArray;
    }

    public void addObject(Actionable actionable, Point pointLoc, Point pointSize) {
        array[OBJECT].add(actionable);
        array[LOCATION].add(pointLoc);
        array[SIZE].add(pointSize);
        if(!this.testArrays()) {
            System.out.println("arrays are not synced");
        }
    }

    public Actionable checkPoint(Point point) {
        for(int i = 0; i < array[OBJECT].size(); i++) {
            Point loc = (Point) array[LOCATION].get(i);
            Point size = (Point) array[SIZE].get(i);
            if(point.x >= loc.x && point.x <= loc.x + size.x) {
                if(point.y >= loc.y && point.y <= loc.y + size.y) {
                    return (Actionable) array[OBJECT].get(i);
                }
            }
        }
        return null;
    }

    private boolean testArrays() {
        this.trimArrays();
        int check = array[OBJECT].size();
        if(check == array[LOCATION].size() && array[SIZE].size() == check) {
            return true;
        }
        return false;
    }

    private void trimArrays() {
        for(ArrayList list : array) {
            list.trimToSize();
        }
    }
}
