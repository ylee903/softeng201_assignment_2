package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Main.Choice;

public class Medium implements Ai {
  // scan through playerChoices and figure out if "ODD" or "EVEN" is more common, return the
  // most common one, if they are same frequency, return "SAME", buefore returning, convert to
  // string
  // create skeleton template for function
  public String playersFavourite(int currentGameRound, ArrayList<Choice> playerChoices) {
    int odd = 0;
    int even = 0;
    for (Choice choice : playerChoices) {
      if (choice == Choice.ODD) {
        odd++;
      } else {
        even++;
      }
    }
    if (odd > even) {
      return "ODD";
    } else if (even > odd) {
      return "EVEN";
    } else {
      return "RANDOM";
    }
  }

  @Override
  public int getAiFingers(
      int currentGameRound,
      ArrayList<Choice> playerChoices,
      Choice playerChoice,
      boolean aiWonLastRound) {
    // If the current game round is 3 or less, the AI picks a random number between 0 and 5
    if (currentGameRound <= 3) {
      return Utils.getRandomNumberRange(0, 5);
    } else {
      // Count the occurrences of ODD and EVEN numbers chosen by the player
      int oddCount = 0;
      int evenCount = 0;

      for (Choice choice : playerChoices) {
        if (choice == Choice.EVEN) {
          evenCount++;
        } else if (choice == Choice.ODD) {
          oddCount++;
        }
      }

      // Determine the AI's strategy based on the player's historical choices
      if (oddCount > evenCount) {
        // Player has predominantly chosen ODD numbers
        if (playerChoice == Choice.ODD) {

          // AI needs to win with EVEN, so it shows ODD
          return Utils.getRandomOddNumber(); // Returns 1, 3, or 5

        } else {

          // AI needs to win with ODD, use Utils to return one of 0, 2, or 4
          return Utils.getRandomEvenNumber(); // Returns 0, 2, or 4
        }
      } else if (evenCount > oddCount) {
        // Player has predominantly chosen EVEN numbers
        if (playerChoice == Choice.ODD) {
          // AI needs to win with EVEN, so it shows EVEN
          return Utils.getRandomEvenNumber(); // Returns 0, 2, or 4

        } else {
          // AI needs to win with ODD, so it shows ODD
          return Utils.getRandomOddNumber(); // Returns 1, 3, or 5
        }
      } else {
        // Equal number of ODD and EVEN choices, AI selects randomly between 0 and 5
        return Utils.getRandomNumberRange(0, 5);
      }
    }
  }
}
