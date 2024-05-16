package nz.ac.auckland.se281;

import nz.ac.auckland.Ai;

public class Easy implements Ai {

  public int getAiFingers() {

    // implement the AI, picks random number between 0 and 5 using utils.random
    int aiFingers = Utils.getRandomNumberRange(0, 5);
    return aiFingers;
  }
}
