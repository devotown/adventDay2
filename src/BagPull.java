public class BagPull {
  private int red;
  private int blue;
  private int green;

  public BagPull(String pulls) {
    String[] cubeAmounts = pulls.split(", ");
    for (String pull : cubeAmounts) {
      if (pull.contains("red")) {
        this.red = splitPullReturnNum(pull);
      } else if (pull.contains("blue")) {
        this.blue = splitPullReturnNum(pull);
      } else if (pull.contains("green")) {
        this.green = splitPullReturnNum(pull);
      }
    }
  }

  public void printCounts() {
    System.out.println("Red: " + red);
    System.out.println("Blue: " + blue);
    System.out.println("Green: " + green);
  }

  private int splitPullReturnNum(String pull) {
    String[] cubeAmounts = pull.split(" ");
    return Integer.parseInt(cubeAmounts[0]);
  }

  public static void main(String[] args) {
    String pullsString = "1 red";
    BagPull bagPull = new BagPull(pullsString);
    bagPull.printCounts();
  }

  public int getRed(){return red;}

  public int getBlue(){return blue;}

  public int getGreen(){return green;}

}
