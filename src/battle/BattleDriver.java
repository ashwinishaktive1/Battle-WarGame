package battle;

import java.util.Scanner;

/**
 * Driver Class to run the program.
 */
public class BattleDriver {

  public BattleDriver() {
  }
  
  /**
   * Main function where pre-set inputs are given and outputs are printed 
   * to have a full view of the battle creation.
   *
   * @param args Argument
   */
  public static void main(String[] args) {
    // Setting scanner for rematch input:
    Scanner sc = new Scanner(System.in);
    
    // Initializing a Random Number Generator object common for all objects
    RandomNumberGenerator random = new RandomNumberGeneratorDev();
    
    // Initializing the Battle Game:
    Arena battle = new BattleModel(random);
    
    System.out.println("\n!!!!!!!!-----------------------------"
        + "------------------------------------!!!!!!!!\n");
    
    String introduction = battle.introduceBattle();
    
    System.out.print(introduction);
    
    System.out.println("\n-------------------------------------"
        + "------------------------------------\n");
    
    // Calling the players:
    battle.callPlayers(101, 777);
        
    String basicInfo = battle.getPlayersBasicInfo();
    
    System.out.print(basicInfo);
        
    System.out.print("\n\nThe look strong... Now let's get them "
        + "prepared for the ruthless Battle!\n");
    
    System.out.println("\n-------------------------------------"
        + "------------------------------------\n");
    
    // Prepare players:
    battle.preparePlayers();

    String playerInput;
    
    do {
      System.out.print("The players stronger than ever! We're ready!!\n\n");
      
      String description = battle.getPlayersDescription();
      
      System.out.print(description);
      
      System.out.println("\n-------------------------------------"
          + "------------------------------------\n");
      
      // Start game:
      battle.startGame();
      
      System.out.print("Let's see who attacks first?\n\n");
      
      // Battle start details:
      System.out.print(battle.getTurnDetails() + "\n");
      
      System.out.println("\n!!!!!!!!-------------------------------"
          + "------------------------------------!!!!!!!!\n");
      
      System.out.println("The game BEGINS!\n");
      
      // Play turns:
      boolean round = true;
      
      while (round) {
        try {
          battle.playTurn();
          String turnDetails = battle.getTurnDetails();
          System.out.println(turnDetails + "\n");
        } catch (IllegalStateException s) {
          round = false;
        }
      }
      
      System.out.println("\n!!!!!!!!-------------------------------"
          + "------------------------------------!!!!!!!!\n");
      
      System.out.println("WOW! That was a wonderful game. And the much awaited "
          + "results are here...!\n");
      
      String result = battle.getFinalResult();
      
      System.out.println(result);
      
      System.out.println("\n-------------------------------------"
          + "------------------------------------\n");
      
      System.out.println("Are we ready for another game? (Y/N)\n");
      
      playerInput = sc.next();
      playerInput = playerInput.trim().toLowerCase();
      
      System.out.println("\n!!!!!!!!-------------------------------"
          + "------------------------------------!!!!!!!!\n");
      
      if ("y".equals(playerInput)) {
        battle.rematch();
      }
    } while ("y".equals(playerInput));
      
    System.out.println("It was great having you here. Have a great day!");
  }

}
