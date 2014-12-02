package zuul_gui;


import javax.swing.*;
import java.awt.*;

/**
 * Created by Sam on 02/12/2014.
 */
public class ZuulGUI extends JFrame {

    private UserView view = new UserView();
    private JPanel left, right;

    public ZuulGUI() {
        super();
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.buildInterface();
        this.setPreferredSize(new Dimension(500, 300));
        this.setSize(new Dimension(600, 400));
    }



    private void buildInterface()
    {
        this.setMaximumSize(new Dimension(500, 300));

        left = new JPanel(new BorderLayout(5,5));
        this.add(view, BorderLayout.CENTER);



        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void windowToCenter() {

    }
}
