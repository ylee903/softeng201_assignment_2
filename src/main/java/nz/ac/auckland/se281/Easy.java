package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Main.Choice;

public class Easy implements Ai {

  @Override
  public int getAiFingers(
      int currentGameRound,
      ArrayList<Choice> playerChoices,
      Choice playerChoice,
      boolean aiWonLastRound) {

    // implement the AI, picks random number between 0 and 5 using utils.random
    int aiFingers = Utils.getRandomNumberRange(0, 5);
    return aiFingers;
  }
}
