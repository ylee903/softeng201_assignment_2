package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.Ai;
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
  public int getAiFingers(int currentGameRound, ArrayList<Choice> playerChoices) {

    // check if the current game round is 3 or less and return 0, elsererurn 1
    if (currentGameRound <= 3) {
      // implement the AI, picks random number between 0 and 5 using utils.random
      int aiFingers = Utils.getRandomNumberRange(0, 5);
      return aiFingers;
    } else {
      return 1;
    }
  }
}
