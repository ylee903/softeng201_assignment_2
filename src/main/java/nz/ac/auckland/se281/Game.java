package nz.ac.auckland.se281;

import java.util.ArrayList;
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
  private Ai ai = null;
  // create a arraylist of players choices
  private ArrayList<Choice> playerChoices = new ArrayList<>();
  private boolean aiWonLastRound = false; // Track if AI won the last round
  // fields to track the player's and AI's wins and looses and if game started
  private boolean gameStarted = false;
  private int playerWins = 0;
  private int playerLosses = 0;
  private int aiWins = 0;
  private int aiLosses = 0;

  public void newGame(Difficulty difficulty, Choice choice, String[] options) {
    if (gameStarted) {
      gameStarted = false;
    }

    // record the players name
    playerName = options[0];
    // print wellcome message using messagecli class
    MessageCli.WELCOME_PLAYER.printMessage(playerName);
    // store the player's choice
    playerChoice = choice;
    // Set the AI difficulty using the factory method
    setDifficulty(difficulty.name());
    aiWonLastRound = false; // Reset last outcome for new game

    // Reset game statistics
    gameStarted = true;
    playerWins = 0;
    playerLosses = 0;
    aiWins = 0;
    aiLosses = 0;
    currentGameRound = 0;
    playerChoices.clear();
  }

  public void setDifficulty(String difficulty) {
    this.ai = AIFactory.createAI(difficulty);
  }

  public void play() {
    // if game has not started, print error message and return
    if (!gameStarted) {
      MessageCli.GAME_NOT_STARTED.printMessage();
      return;
    }

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
    // if the player/s input is even, store EVEN into the playerChoices arraylist, else store ODD
    playerChoices.add(fingers % 2 == 0 ? Choice.EVEN : Choice.ODD);
    // print stuff
    MessageCli.PRINT_INFO_HAND.printMessage(playerName, String.valueOf(fingers));

    // get ai fingers using ai interface
    int aiFingers = ai.getAiFingers(currentGameRound, playerChoices, playerChoice, aiWonLastRound);

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

    // Determine if AI won the round
    aiWonLastRound = (playerChoice != currentRoundOutcome);

    // Determine if AI won the round
    aiWonLastRound = (playerChoice != currentRoundOutcome);

    if (aiWonLastRound) {
      aiWins++;
      playerLosses++;
    } else {
      playerWins++;
      aiLosses++;
    }
  }

  public void endGame() {
    if (!gameStarted) {
      MessageCli.GAME_NOT_STARTED.printMessage();
      return;
    }

    showStats();
    if (playerWins > aiWins) {
      MessageCli.PRINT_END_GAME.printMessage(playerName);
    } else if (aiWins > playerWins) {
      MessageCli.PRINT_END_GAME.printMessage(aiName);
    } else {
      MessageCli.PRINT_END_GAME_TIE.printMessage();
    }
    gameStarted = false; // End the game
  }

  public void showStats() {
    if (!gameStarted) {
      MessageCli.GAME_NOT_STARTED.printMessage();
      return;
    }

    MessageCli.PRINT_PLAYER_WINS.printMessage(
        playerName, String.valueOf(playerWins), String.valueOf(playerLosses));
    MessageCli.PRINT_PLAYER_WINS.printMessage(
        aiName, String.valueOf(aiWins), String.valueOf(aiLosses));
  }
}
