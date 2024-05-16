package nz.ac.auckland.se281;

public class AIFactory {
  public static Ai createAI(String difficulty) {
    switch (difficulty.toUpperCase()) {
      case "EASY":
        return new Easy();
      case "MEDIUM":
        return new Medium();
        // case "HARD":
        //  return new Hard();
      default:
        throw new IllegalArgumentException("Unknown difficulty level: " + difficulty);
    }
  }
}
