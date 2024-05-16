package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Main.Choice;
import nz.ac.auckland.se281.Main.Difficulty;

/** This class represents the Game is the main entry point. */
public class Game {

  // store current game round
  private int currentGameRound = 0;
  private String playerName;
  // name ai player "HAL-9000"
  private final String aiName = "HAL-9000";
  private Choice playerChoice = null;

  public void newGame(Difficulty difficulty, Choice choice, String[] options) {

    // record the players name
    playerName = options[0];
    // print wellcome message using messagecli class
    MessageCli.WELCOME_PLAYER.printMessage(playerName);
    // store the player's choice
    playerChoice = choice;
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

    // implement the AI, picks random number between 0 and 5 using utils.random
    int aiFingers = Utils.getRandomNumberRange(0, 5);
    // print ai fingers
    MessageCli.PRINT_INFO_HAND.printMessage(aiName, String.valueOf(aiFingers));

    // create string called current round, it is even is sum of fingers is even, and odd if sum of
    // current finghrs is odd
    Choice currentRoundOutcome = (fingers + aiFingers) % 2 == 0 ? Choice.EVEN : Choice.ODD;

    // if player chose odd, ai chooses even, and vice versa, if sum is even, whoever chose even
    // wins, using PRINT_OUTCOME_ROUND.
    MessageCli.PRINT_OUTCOME_ROUND.printMessage(
        String.valueOf(fingers + aiFingers),
        (fingers + aiFingers) % 2 == 0 ? "EVEN" : "ODD",
        playerChoice == currentRoundOutcome ? playerName : aiName);
  }

  public void endGame() {}

  public void showStats() {}
}
