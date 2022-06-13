/*
Main - this is a runner class that runs the Game class

Author - Marc Shepard and Niyati Trivedi
*/

class Main {
  public static void main(String[] args) {
    Game g = new Game();
    System.out.println();
    System.out.println("Welcome to Hangman! This is a multiplayer hangman game, in which you can compete in Hangman against your friends. To start, add all players. Once you've added all of the players, choose a player to start with. Then, begin!");
    System.out.println();

    g.play();
  }

  // Unit tests for Utils.java. Call this from main if you want to
  // see examples of the Utils methods
  public static void testUtils () {
    String s = Utils.inputStr ("What's your name? ");
    System.out.println ("Hi " + s);
    
    int n = Utils.inputNum ("What's your age? ");
    System.out.println ("You typed " + n);

    int r = Utils.randInt (1, 100);
    System.out.println ("A random number between 1-100 is " + r);

    String[] strs = {"chocolate", "vanilla", "strawberry"};
    s = Utils.randChoice(strs);
    System.out.println ("A random flavor is: " + s);
  }
}