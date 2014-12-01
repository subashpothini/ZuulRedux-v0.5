package zuul_text;

import zuul_model.*;

/**
 *
 * @author jdb
 */
public class TextUI {
    private Parser parser;
    private Game game;
    private Player player;
    
    public TextUI() {
        parser = new Parser();
        game = new Game();
        player = game.getPlayer();
    }
    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("You are in a large country house and have a craving for cake.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(player.getRoom().getLongDescription());
    }
    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play()
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        GameResult result = GameResult.CONTINUE;
        while (result == GameResult.CONTINUE) {
            Command__ command = parser.getCommand();
            
            System.out.println(command);

            if(command.isUnknown()) {
                System.out.println("I don't know what you mean...");
                continue;
            }

            String commandWord = command.getCommandWord();
            if (commandWord.equals("help")) {
                printHelp();
                continue;
            }
            
            try {
                result = game.move(command);
            }
            catch (ZuulException e) {
                System.out.println(e.getMessage());
            }
            if (result == GameResult.SUCCESS) {
                System.out.println("Congratulations");
                break;
            } else if (result == GameResult.QUIT) {
                System.out.println("Goodbye");
                break;
            } else
                game.show();
        }
    }

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander around");
        System.out.println("the house getting hungrier and hungrier.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }
}
