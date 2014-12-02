import zuul_text.TextUI;

/**
 * Created by Sam on 02/12/2014.
 */
public class mainFrame {

    public static void main(String[] args) {
        TextUI ui = new TextUI();
        Tester tester = new Tester(ui);
        tester.runTests();
        ui.play();
    }
}
