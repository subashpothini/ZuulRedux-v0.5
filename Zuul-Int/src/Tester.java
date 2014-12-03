import zuul_model.Game;
import zuul_model.Player;
import zuul_model.ZuulException;
import zuul_model.tasks.Task;

/**
 * Created by Sam on 02/12/2014.
 */
public class Tester {

    private Game game;
    private Player player;



    public void runTests() {
        try {
            test1(); // examine room
            test2(); // examine and use door
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void test1() throws InterruptedException {
        System.out.println("TEST 1 START:");

        // examine room;
        System.out.println("TEST 1.1 START :" + player.getRoom().examine() + ": TEST 1.1 END");

        // examine sugar;
        try {
            System.out.println("TEST 1.2 START :" + player.getRoom().getItem("sugar").examine() + ": <- SUGAR DESCRIPTION? TEST 1.2 END");
        } catch (ZuulException e) {
            e.printStackTrace();
        }
        System.out.println("TEST 1 END.");
        Thread.sleep(2000);
    }

    public void test2() throws InterruptedException {
        System.out.println("TEST 2 START:");

        // EXAMINE DOOR TEXT
        System.out.println("TEST 2.1 START :" + player.getRoom().getDoor(0).examine()+ ": EXAMINED DOOR? TEST 2.1 END");

        // acquire teh two tasks to be tested in 2.2 & 2.3
        Task[] taskList = player.getRoom().getDoor(0).getTasks(player);

        // EXAMINE DOOR FOR PLAYER (SHOULD BE IDENTICAL TO PREVIOUS
        System.out.println("TEST 2.2 START:");
        System.out.println("TASK NAME:" + taskList[0].getName());
        taskList[0].performAction();
        System.out.println("<- HAS ROOM CHANGED? (PASSAGE) TEST 2.2 END");

        // GO TO NEXT ROOM
        System.out.println("TEST 2.3 START:");
        System.out.println("TASK NAME:" + taskList[1].getName());
        taskList[1].performAction();
        System.out.println(player.getRoom().examine() + "NEW ROOM?");
        System.out.println("<- HAS ROOM CHANGED? (PASSAGE) TEST 2.3 END");

        System.out.println("TEST 2 END.");
        Thread.sleep(2000);
    }
}
