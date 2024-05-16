package nz.ac.auckland;

import java.util.ArrayList;
import nz.ac.auckland.se281.Main.Choice;

public interface Ai {
  // return aiFingerz
  int getAiFingers(int currentGameRound, ArrayList<Choice> playerChoices);
}
