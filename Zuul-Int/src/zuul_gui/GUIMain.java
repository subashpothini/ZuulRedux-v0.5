package zuul_gui;

import java.io.IOException;

/**
 * Created by Sam on 02/12/2014.
 */
public class GUIMain {

    public static void main(String[] args) {
        ZuulGUI gui = new ZuulGUI();
        String current = null;
        try {
            current = new java.io.File( "." ).getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Current dir:"+current);
    }


}
