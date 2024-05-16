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
    MessageCli.START_ROUND.printMessage(String.valueOf(currentGameRound));
    // print current round
    MessageCli.PRINT_INFO_HAND.printMessage(playerName, "0");

    int fingers;
    while (true) {
      // print messagecli for givive fingers
      MessageCli.ASK_INPUT.printMessage();
      String playerInput = Utils.scanner.nextLine();
      // convert playerInput to integer and make sure it is between 0 and 5, if not print invalid
      // input cli
      try {
        fingers = Integer.parseInt(playerInput);
        if (fingers >= 0 && fingers <= 5) {
          break;
        } else {
          MessageCli.INVALID_INPUT.printMessage();
        }
      } catch (NumberFormatException e) {
        MessageCli.INVALID_INPUT.printMessage();
      }
    }
  }

  public void endGame() {}

  public void showStats() {}
}
