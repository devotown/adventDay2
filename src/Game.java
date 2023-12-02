import java.util.ArrayList;

public class Game {

  private int gameNumber;
  private int redCubeMax;
  private int greenCubeMax;
  private int blueCubeMax;

  private int power;

  private boolean isValidGame;

  private ArrayList<Integer> greens;
  private ArrayList<Integer> blues;
  private ArrayList<Integer> reds;

  private String pulls;

  private ArrayList<BagPull> listOfPulls;

  public Game(String game) {
    redCubeMax = 0;
    blueCubeMax = 0;
    greenCubeMax = 0;

    listOfPulls = new ArrayList<>();
    String[] parts = game.split(": ");

    gameNumber = parseGameNum(parts[0]);
    pulls = parts[1];

    String[] onePullArray = pulls.split("; ");

    for (String pull : onePullArray) {
      listOfPulls.add(new BagPull(pull));
    }

    findMaxPull();

    System.out.println(gameNumber);
    System.out.println(redCubeMax);
    System.out.println(blueCubeMax);
    System.out.println(greenCubeMax);

    this.power = powerEquation();
    System.out.println(power);

    isValidGame = checkIfValid();
  }

  public void printMaxCounts() {
    System.out.println("Game " + gameNumber + ":");

  }

  public boolean isValidGame(){
    return isValidGame;
  }
  public static void main(String[] args) {
    String gameString = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green";
    Game game = new Game(gameString);
    game.printMaxCounts();
  }

  public int getPower(){return power;}

  private ArrayList<BagPull> createGamePulls(){
    ArrayList<BagPull> pulls = new ArrayList<>();

    String[] stringPulls = this.pulls.split(";");

    for (String pull : stringPulls){
      BagPull newPull = new BagPull(pull);
      pulls.add(newPull);
    }

    return pulls;
  }

  private int parseGameNum(String game){
    String[] cubeAmounts = game.split(" ");
    return Integer.parseInt(cubeAmounts[1]);
  }

  public int getGameNumber (){
    return gameNumber;
  }

  private void findMaxPull(){
    for (BagPull pull : listOfPulls) {
      if (pull.getRed() > this.redCubeMax){
        this.redCubeMax = pull.getRed();
      }
      if (pull.getGreen() > this.greenCubeMax){
        this.greenCubeMax = pull.getGreen();
      }
      if (pull.getBlue() > this.blueCubeMax){
        this.blueCubeMax = pull.getBlue();
      }
    }
  }

  private boolean checkIfValid(){
    return (redCubeMax <= 12 && blueCubeMax <= 14 && greenCubeMax <= 13);
  }

  private void setMaxPulls() {
    // create an array of int sof every color
    ArrayList<Integer> green = new ArrayList<>();
    ArrayList<Integer> red = new ArrayList<>();
    ArrayList<Integer> blue = new ArrayList<>() ;

    for (BagPull pull :listOfPulls) {
      green.add(pull.getGreen());
      blue.add(pull.getBlue());
      red.add(pull.getRed());
    }

    blueCubeMax = findMax(blue);
    greenCubeMax = findMax(green);
    redCubeMax = findMax(red);
    // Find the max in an Array of ints
  }

  private int findMax(ArrayList<Integer> list) {
    if (list == null || list.isEmpty()) {
      return 0;
    }

    int max = list.get(0);  // Assume the first element is the maximum

    for (int i = 1; i < list.size(); i++) {
      if (list.get(i) > max) {
        max = list.get(i);  // Update the maximum if a larger element is found
      }
    }

    return max;
  }

  private int powerEquation(){
    int power;

    power = redCubeMax * blueCubeMax * greenCubeMax;

    return power;
  }
}
