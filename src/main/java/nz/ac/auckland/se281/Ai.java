package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Main.Choice;

public interface Ai {
  // return aiFingerz
  int getAiFingers(int currentGameRound, ArrayList<Choice> playerChoices, Choice playerChoice);
}
