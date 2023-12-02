import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class GameList {

  ArrayList<String> gameStrings;

  ArrayList<Game> games;

  ArrayList<Game> maxScores;

  private int validGameCount;
  private int IDsum;

  private int powerSum;

  public GameList (String path) {
    validGameCount = 0;
    IDsum = 0;
    powerSum = 0;
    games = new ArrayList<>();
    gameStrings = parseInputFile(path);
    for (String game : gameStrings) {
      System.out.println(game);
      // Create a new game with the string
      Game newGame = new Game(game);
      games.add(newGame);
      if (newGame.isValidGame()){
        ++validGameCount;
        IDsum = IDsum + newGame.getGameNumber();
      }
      powerSum = powerSum + newGame.getPower();
    }
    System.out.println(IDsum);
    System.out.println(powerSum);
  }

  public static ArrayList<String> parseInputFile(String filePath) {
    ArrayList<String> games = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      StringBuilder currentGame = new StringBuilder();
      String line;

      while ((line = reader.readLine()) != null) {
        line = line.trim();

        if (line.startsWith("Game")) {
          // Start a new game
          if (currentGame.length() > 0) {
            games.add(currentGame.toString());
            currentGame.setLength(0);
          }
          currentGame.append(line).append("");
        } else {
          // Append pulls within the current game
          currentGame.append(line).append("; ");
        }
      }

      // Add the last game (if any)
      if (currentGame.length() > 0) {
        games.add(currentGame.toString());
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    return games;
  }

}
