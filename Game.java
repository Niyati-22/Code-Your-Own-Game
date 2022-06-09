/*
Game class - all of the code for the hangman game!

Author - Niyati Trivedi

Change history:
5/12: Design Plan Created
I can't remember the rest but I coded it from 5/12-6/3.


Design Plan
I want to create a multiplayer hangman game.
State:
-list of words (arrayList)
-hangman states (string, different strings for each hangman image)
-word (string)
-guesses (int)
-incorrect letters (array of chars)
-player + points (hashmap; string: int)

Methods:
-add player (add a player to the hashmap and set their points to 0)
-choose player (choose what player's points you wanna change)
-guess (takes a string and returns what was correct)
-return points (returns the players points)


*/

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
  
  private ArrayList<String> words;
  private ArrayList<String> states;
  private String answer;
  private int guesses;
  private ArrayList<String> wrong_letters;
  HashMap<String, Integer> players;
  private String currentPlayer = "";
  Scanner scan = new Scanner(System.in);
  private String[] guessString;
  private int wrongGuesses;
  private boolean foundWord;

  public Game () {
    states = new ArrayList<String>();
    states.add("no wrong answers given yet");
    states.add(" o");
    states.add(" o\n |"); 
    states.add(" o\n |\\");
    states.add(" o\n/|\\\n |");
    states.add(" o\n/|\\\n |\n/ \\");
    words = new ArrayList<String>();
    words.add("dream");
    words.add("blink");
    words.add("sting");
    words.add("great");
    words.add("plate");
    words.add("mince");
    words.add("crate");
    words.add("drape");
    words.add("drone");
    words.add("exist");
    words.add("hours");
    words.add("clout");
    words.add("mount");
    words.add("store");
    words.add("prose");
    words.add("crisp");
    words.add("drift");
    
    players = new HashMap<String, Integer>();
    wrong_letters = new ArrayList<String>();
  }
  public void addPlayer(){
    //add player name on hashmap, and set points to 0
    System.out.println("What is the player's name?");
    String n = scan.nextLine();
    players.put(n, 0);
  }
  public void choosePlayer(){
    if(players.size() == 0){
      System.out.println("Please add players before continuing.");
    }
    else{
      System.out.println("Here are all current players: ");
      System.out.println("");
      for (String thing : players.keySet()){
        System.out.println(thing);
      }
      System.out.println("What player do you want to play?");
      String cp = scan.nextLine();
      boolean exists = false;
      for (String thing : players.keySet()){
        if (cp.equals(thing)){
          currentPlayer = cp;
          exists = true;
          }
        }
      if (exists == false){
        System.out.println("This player does not exist. Please select a new player or add this player.");
      }
      }
      System.out.println();
      }

  public void guessWord(String guess){
    if (answer.indexOf(guess) == -1){
      wrong_letters.add(guess);
      wrongGuesses += 1;
    }
    int x = answer.indexOf(guess);
    int start = 0;
    while (x != -1){
      String temp = answer.substring(start, x);
      guessString[x] = guess;
      start = x;
      x = temp.indexOf(guess);
      }
      }
  
  public void returnPoints(String playerName){
    if (players.get(playerName) == null){
      System.out.println("This player doesn't exist. Please add a player and then return.");
    }
    else{
      System.out.println(playerName + " has " + players.get(playerName) + " points.");
      System.out.println();
      }
  }
  public void addWord(String word){
    words.add(word);
  }
  
  public void play(){
    int choice = 0;
    while (choice <= 0|| choice >= 6){
      System.out.println("Choose an option from the menu: \n\t1-add a player\n\t2-choose a player to play\n\t3-view player points\n\t4-add a word\n\t5-play a game");
      System.out.println("Please type a number between 1-5.");
      try{
        String choiceString = scan.nextLine();
        choice = Integer.parseInt(choiceString);
      } catch (Exception e){
        System.out.println("Your answer was incorrect. Please type a number between 1-5.");
        System.out.println();
      }
    }
    switch (choice){
      case 1:
        addPlayer();
        break;
      case 2:
        choosePlayer();
        break;
      case 3:
        System.out.println("Here are all current players: ");
      for (String thing : players.keySet()){
        System.out.println(thing);
      }
        System.out.println("Which player's points do you want to see?");
        String s = scan.nextLine();
        returnPoints(s);
        break;
      case 4:
        System.out.println("Please make sure your word has no repeating letters.");
        System.out.println("What word do you want to add?");
        String w = scan.nextLine();
        addWord(w);
        break;
      case 5:
        String y;
        guesses = 0;
        wrongGuesses = 0;
        foundWord = false;
        answer = words.get(Utils.randInt(0,words.size()-1));
        guessString = new String[answer.length()];
        for (int i = 0; i < answer.length(); i++){
          System.out.print("_");
          System.out.print(" ");
        }
        for (int i = 0; i < answer.length(); i++){
       guessString[i] = "_";
      }
        
        System.out.println();
        while (!(foundWord) || wrongGuesses < 6){
          if (currentPlayer.equals("")){
            System.out.println("Please select a player before continuing.");
            break;
          }
          System.out.println("Current number of wrong guesses: " + wrongGuesses);
          System.out.println("You have " + (6-wrongGuesses) + " wrong guesses remaining.");
          System.out.println(states.get(wrongGuesses));
          System.out.println("Current wrong letters: " + wrong_letters);
          String b = "";
          while (b.length() != 1){
            System.out.println("Please only type one chracter.");
            System.out.println("What is your guess?");
            try{
              b = scan.nextLine();
              } catch (Exception e){
              System.out.println("Your guess is not the right length. It can only be one character. Try again.");
          System.out.println();}
            }
          guesses += 1;
          System.out.println();
          guessWord(b);
          if (wrongGuesses == 5){
            foundWord = true;
          }
          y = "";
          for (int i = 0; i < guessString.length; i++){
            y += guessString[i];
          }
          if (y.equals(answer)){
            System.out.println("Great Job!!");
            int x = players.get(currentPlayer);
            players.remove(currentPlayer);
            players.put(currentPlayer, x + 1);
            foundWord = true;
            wrongGuesses = 6;
          }
          for (int i = 0; i < guessString.length; i++){
            System.out.print(guessString[i]);
            System.out.print(" ");
          }
          System.out.println();
          }
        System.out.println("The correct answer was: " + answer);
        System.out.println("You took "+ guesses + " guesses");
        int c = wrong_letters.size() - 1;
        while (c > -1){
          wrong_letters.remove(c);
          c--;
        }
        break;
    }
    play();
  }
}