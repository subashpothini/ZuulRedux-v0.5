package zuul_gui;

import zuul_model.Actionable;
import zuul_model.Game;
import zuul_model.Player;
import zuul_model.tasks.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Sam on 02/12/2014.
 */
public class UserView extends JPanel {

    private static final String PATH = "./data/roomImages/larder.jpg";
    private Game game;
    private Player player;
    private JPanel contentPane;
    private JScrollPane scrollPane = new JScrollPane();
    private JLabel image = new JLabel(new ImageIcon(PATH));
    private JViewport viewPort;
    private JPopupMenu popupMenu;
    private JFrame frame = null;


    public UserView()
    {
        super();
        this.setLayout(new BorderLayout(5,5));
        game = new Game();
        player = game.getPlayer();
        this.buildInterface();
        this.setPreferredSize(new Dimension(500, 300));
        this.setSize(new Dimension(500, 300));
    }

    private void buildInterface()
    {
        scrollPane.setViewportView(image);
        viewPort = scrollPane.getViewport();
        scrollPane.setSize(new Dimension(200, 200));
        scrollPane.setMaximumSize(new Dimension(200, 200));

        this.add(scrollPane, BorderLayout.CENTER);
        this.add(new ScrollButton("^", new Point(0, -100), viewPort), BorderLayout.NORTH);
        this.add(new ScrollButton(">", new Point(200, 0), viewPort), BorderLayout.EAST);
        this.add(new ScrollButton("v", new Point(0, 100), viewPort), BorderLayout.SOUTH);
        this.add(new ScrollButton("<", new Point(-200, 0), viewPort), BorderLayout.WEST);

        viewPort.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point point = e.getPoint();
                //convert to image global coordinate system
                point = SwingUtilities.convertPoint(viewPort, point, image);
                Actionable actionable = player.getRoom().getActionable(point);
                if (actionable == null) {
                    System.out.println("MouseClickEvent - x = " + point.x + ", y = " + point.y);
                } else {
                    buildPopup(actionable.getTasks(player));
                    frame = new JFrame();
                    frame.setUndecorated(true);
                    frame.setLocation(MouseInfo.getPointerInfo().getLocation());
                    frame.add(popupMenu);
                    frame.pack();
                    frame.repaint();
                    frame.setVisible(true);
                    viewPort.repaint();
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    frame = null;
                }
            }
        });

        this.setVisible(true);
    }

    private void buildPopup(Task[] tasks) {
        popupMenu = new JPopupMenu();
        JMenuItem menuItem;
        for(Task task : tasks) {
            menuItem = new JMenuItem(task.getName());
            menuItem.addActionListener(e ->
                    task.performAction());
            popupMenu.add(menuItem);
        }
        popupMenu.setMaximumSize(new Dimension(40, tasks.length * 20));
    }

    private class ScrollButton extends JButton {

        private Point motion;
        private JViewport port;

        public ScrollButton(String label, Point motion, JViewport port) {
            super(label);
            this.motion = motion;
            this.port = port;
            this.addActionListener( ActionListener -> {
                Point position = port.getViewPosition();
                Point newPosition = new Point(position.x + motion.x, position.y + motion.y);
                port.setViewPosition(newPosition);
            });
        }
    }

}

