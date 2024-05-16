package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Main.Choice;
import nz.ac.auckland.se281.Main.Difficulty;

/** This class represents the Game is the main entry point. */
public class Game {

  // store current game round
  private int currentGameRound = 0;
  private String playerName;

  public void newGame(Difficulty difficulty, Choice choice, String[] options) {

    // record the players name
    playerName = options[0];
    // print wellcome message using messagecli class
    MessageCli.WELCOME_PLAYER.printMessage(playerName);
  }

  public void play() {
    // display current game round and increment
    currentGameRound++;
    // print current round
    MessageCli.START_ROUND.printMessage(String.valueOf(currentGameRound));

    // Declare an integer variable to store the number of fingers the player shows
    int fingers;
    // Start an infinite loop to keep asking for input until a valid one is provided
    while (true) {
      // Print a message asking for the player's input
      MessageCli.ASK_INPUT.printMessage();
      // Read the player's input from the command line
      String playerInput = Utils.scanner.nextLine();
      // Try to parse the player's input as an integer
      try {
        // Convert the player's input to an integer
        fingers = Integer.parseInt(playerInput);
        // If the number of fingers is between 0 and 5 (inclusive), break the loop
        if (fingers >= 0 && fingers <= 5) {
          break;
        } else {
          // If the number of fingers is not between 0 and 5, print an invalid input message
          MessageCli.INVALID_INPUT.printMessage();
        }
      } catch (NumberFormatException e) {
        // If the player's input cannot be parsed as an integer, print an invalid input message
        MessageCli.INVALID_INPUT.printMessage();
      }
    }
    // print stuff
    MessageCli.PRINT_INFO_HAND.printMessage(playerName, String.valueOf(fingers));
  }

  public void endGame() {}

  public void showStats() {}
}
