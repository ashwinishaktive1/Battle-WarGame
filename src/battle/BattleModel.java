package battle;

import java.util.ArrayList;
import java.util.List;

/**
 * The Battle Model is specialized player vs. player fight game 
 * setup on the Arena. The players with the assigned abilities get 
 * prepared for a battle, using the gears and weapons from the 
 * equipment bag and armory in the arena. Player who's health drops 
 * below 0 is considered defeated. In case of both players being 
 * equally competitive, the match is drawn. 
 * 
 * @author Ashwini Shaktivel Kumar
 *
 */
public class BattleModel implements Arena {
  
  private String battleName;
  private List<Players> currentPlayers;
  private EquipmentBag eqBag;
  private Armory armory;
  private Players attacker;
  private Players defender;
  private List<Players> winner;
  private boolean arePlayersHere;
  private boolean arePlayersPrepared;
  private boolean hasGameStarted;
  private boolean isGameOver;
  private int turnCount;
  private boolean strikeSuccess;
  private List<Boolean> strikeRate;
  private int strikeDamage;
  private RandomNumberGenerator random;
  
  /**
   * Construct a battle model. You may call players and let them 
   * battle through this setup.
   * 
   * @param rand Random Number Generator
   * @throws IllegalArgumentException if the random number generator 
   *                                  is null
   */
  public BattleModel(RandomNumberGenerator rand) throws 
      IllegalArgumentException {
    if (rand == null) {
      throw new IllegalArgumentException("Random Number Generator cannot be null.");
    }
    
    this.random = rand;
    this.battleName = "Win Over Luck!";
    this.currentPlayers = new ArrayList<>();
    this.arePlayersHere = false;
    this.prepareArena();
  }
  
  /**
   * Prepare arena by setting up new equipment bag and new armory, 
   * and refreshing the players.
   */
  private void prepareArena() {
    this.eqBag = null;
    this.armory = null;
    EquipmentBag eq = new EquipmentBag(this.random);
    this.eqBag = eq;
    Armory arms = new Armory(this.random);
    this.armory = arms;
    this.attacker = null;
    this.defender = null;
    this.winner = new ArrayList<>();
    this.arePlayersPrepared = false;
    this.hasGameStarted = false;
    this.strikeSuccess = false;
    this.strikeDamage = 0;
    this.strikeRate = new ArrayList<>();
    this.isGameOver = false;
    this.turnCount = 0;
  }

  @Override
  public String introduceBattle() {
    return "Welcome to...." + "\n" + this.battleName + "\n\n" 
        + "A turn-based battle game setup on the grand Arena! \n"
        + "Turn-based battles generally start by pitting two players against one another."
        + "\nThe battle begins by determining which player will go first and "
        + "then proceeds as follows:\n"
        + "\n"
        + "Player 1 attacks using the weapon that they have in-hand by "
        + "taking a swing at player 2 who tries to avoid the attack.\nIf player "
        + "1 hits player 2, then player 2 potentially takes damage.\n"
        + "\n"
        + "Player 2 attacks using the weapon that they have in-hand by taking a "
        + "swing at player 1 who tries to avoid the attack.\nIf player 2 hits player "
        + "1, then player 1 potentially takes damage.\n"
        + "\n"
        + "Turns continue back and forth until one of the players has taken a "
        + "total damage that is greater than or \nequal to their health.\n"
        + "\n"
        + "The player who did not take a total damage greater than or equal "
        + "to their health is declared the victor.\nIt is possible for a battle "
        + "to end in a draw.\n"
        + "\nLet's try your luck! Good luck, haha! :D";
  }
  
  @Override
  public void callPlayers(int playerOneId, int playerTwoId) {
    this.currentPlayers.add(new BattlePlayer(playerOneId, this.random));
    this.currentPlayers.add(new BattlePlayer(playerTwoId, this.random));
    this.arePlayersHere = true;
  }

  @Override
  public void preparePlayers() throws IllegalStateException {
    if (this.arePlayersHere == false) {
      throw new IllegalStateException("Please call the players to prepare "
          + "them.");
    }
    
    for (Players p : this.currentPlayers) {
      p.equipGears(this.eqBag);
      p.requestWeapon(this.armory);
    }
    this.arePlayersPrepared = true;
  }

  @Override
  public String getPlayersBasicInfo() throws IllegalStateException {
    if (this.arePlayersHere == false) {
      throw new IllegalStateException("Please call the players to get their "
          + "details.");
    }
    String basicInfo = "The players are in the arena, they look fine "
        + "without the gears! \n";
    
    for (Players p : this.currentPlayers) {
      basicInfo += p.getPlayerBasicInfo() + "\n\n";
    }
    basicInfo = basicInfo.trim();
    
    return basicInfo;
  }
  
  @Override
  public String getPlayersDescription() throws IllegalStateException {
    if (this.arePlayersHere == false) {
      throw new IllegalStateException("Please call the players to get their "
          + "complete description.");
    }
    
    String description = "The complete description of players on "
        + "the arena: \n";
    
    for (Players p : this.currentPlayers) {
      description += p.getPlayerDescription() + "\n\n";
    }
    description = description.trim();
    
    return description;
  }

  @Override
  public void startGame() throws IllegalStateException {
    if (this.arePlayersPrepared == false) {
      throw new IllegalStateException("Please prepare the players to start "
          + "the battle.");
    }
    
    this.defender = this.whoStarts();
    
    for (Players p : this.currentPlayers) {
      if (!this.defender.equals(p)) {
        this.attacker = p;
        break;
      }
    }
    this.hasGameStarted = true;
  }
  
  /**
   * Switch the attacker and defender. 
   */
  private void switchTurn() {
    Players temp = this.attacker;
    this.attacker = this.defender;
    this.defender = temp;
  }
  
  /**
   * Check for turn redundancy. If both players are either defending 
   * or are taking 0 damage, the strike is considered unsuccessful. 
   * This method checks if the last 20 strikes were unsuccessful, and 
   * hence helps end the game.
   * 
   * @return true if last 20 strikes were unsuccessful
   */
  private boolean testTurnRedundancy() {
    boolean flag = false;
    if (turnCount > 20) {
      for (int i = turnCount - 1; i >= turnCount - 21; i--) {
        if (!this.strikeRate.get(i)) {
          flag = true;
        } else {
          flag = false;
          break;
        }
      }
    }
    return flag;
  }
  
  @Override
  public void playTurn() throws IllegalStateException {
    if (!this.hasGameStarted) {
      throw new IllegalStateException("Please start the battle.");
    }
    if (this.isGameOver) {
      throw new IllegalStateException("The battle has ended. Please view "
          + "results.");
    }
    if (this.testTurnRedundancy()) {
      throw new IllegalStateException("The turns are redundant. "
          + "Try for a rematch.");
    }
    
    this.switchTurn();
    
    for (Players p : this.currentPlayers) {
      if (this.attacker.equals(p)) {
        p.getRemainingTemporaryEffectStrikes();
      }
    }
    
    this.move();
    this.turnCount++;
    this.isGameOver();
  }

  @Override
  public String getTurnDetails() {
    String strikingPower = "Striking power: " 
        + this.attacker.getStrikingPower() + "\n";
    String avoidanceAb = "Avoidance ability: "
        + this.defender.getAvoidanceAbility() + "\n";
    String success;
    if (this.turnCount == 0) {
      success = "";
      strikingPower = "";
      avoidanceAb = "";
    } else if (this.strikeSuccess) {
      success = "Whoa, that was a great move. " 
                + this.getAttacker() + " strikes!\n"
                + "Damage caused to " + this.getDefender() 
                + "'s health is " + this.strikeDamage;
    } else {
      success = "That was amazingly defended. " 
                + this.getDefender() + " is safe.";
    }
    String turnDetails = "Round " + this.turnCount + "\n"
        + "Turn details:\n"
        + "Attacker: " + this.getAttacker() + "; Health: " 
        + this.attacker.getCurrentHealth() + "\n"
        + strikingPower
        + "Defender: " + this.getDefender() + "; Health: " 
        + this.defender.getCurrentHealth() + "\n"
        + avoidanceAb
        + success;
    
    turnDetails = turnDetails.trim();
    
    return turnDetails;
  }

  @Override
  public List<Players> getWinner() {
    return this.winner;
  }

  @Override
  public String getFinalResult() {
    if (this.isGameOver()) {
      if (this.winner.size() == 1) {
        return this.getWinner().get(0).getPlayerId() + " wins!!!!!!";
      } else {
        return "It's a draw. Both players are extremely competitive!";
      }
    }
    return "Game is ongoing, please continue playing turns!";
  }

  @Override
  public void rematch() throws IllegalStateException {
    if (!this.hasGameStarted) {
      throw new IllegalStateException("Please start the current game. "
          + "You may try for a re-match after this attempt.");
    }
    this.refreshPlayers();
    this.prepareArena();
    this.preparePlayers();
  }

  @Override
  public List<Players> getPlayers() {
    return this.currentPlayers;
  }
  
  /**
   * Get the current attacker in the battle.
   * 
   * @return attacker id
   */
  public String getAttacker() {
    return this.attacker.getPlayerId();
  }
  
  /**
   * Get the current defender in the battle.
   * 
   * @return defender id
   */
  public String getDefender() {
    return this.defender.getPlayerId();
  }
    
  /**
   * Refresh the players, by letting them take off the gears/weapon 
   * and rest. Player is back to the initial health and abilities.
   */
  private void refreshPlayers() {
    this.arePlayersPrepared = false;
    for (Players p : this.currentPlayers) {
      p.rematchSetup();
    }
  }
  
  /**
   * Check who starts the game. Player with greater charisma gets to 
   * attack first. In case of equal charisma, one of the two players 
   * are selected at random.
   * 
   * @return attacking player
   */
  private Players whoStarts() {
    Players player1 = this.currentPlayers.get(0);
    Players player2 = this.currentPlayers.get(1);
    
    if (player1.getCharisma() > player2.getCharisma()) {
      return player2;
    } else if (player2.getCharisma() > player1.getCharisma()) {
      return player1;
    } else {
      int randomChoice = random.getRandomNumber(1, 2) - 1;
      return this.currentPlayers.get(randomChoice);
    }
  }
  
  /**
   * Attacker strikes the defender. 
   *
   * If the striking power of the attacking player 
   * is greater than the avoidance ability of their opponent, the attacking 
   * player successfully strikes their opponent and the damage must be calculated.
   * 
   * If the actual damage is greater than 0, it is subtracted from 
   * the player's health.
   */
  private void move() {
    int attackerStrikingPower = this.attacker.getStrikingPower();
    int defenderAvoidanceAbility = this.defender.getAvoidanceAbility();
    
    if (attackerStrikingPower > defenderAvoidanceAbility) {
      this.strikeSuccess = true;
      int damage = this.calculateActualDamage();
      if (damage > 0) {
        this.defender.applyDamage(damage);
        this.strikeDamage = damage;
        this.strikeRate.add(this.strikeSuccess);
      } else {
        this.strikeDamage = 0;
        this.strikeRate.add(!this.strikeSuccess);
      }
    } else {
      this.strikeSuccess = false;
      this.strikeRate.add(this.strikeSuccess);
    }
  }
  
  /**
   * The actual damage is the potential striking damage minus the constitution 
   * of their opponent.
   * 
   * @return actual damage
   */
  private int calculateActualDamage() {
    return this.calculatePotentialStrikingDamage() - this.defender.getConstitution();
  }
  
  /**
   * The potential striking damage is calculated by adding the strength of the 
   * attacking player to a random value in the range of the damage that their 
   * weapon can inflict.
   * 
   * @return potential striking damage
   */
  private int calculatePotentialStrikingDamage() {
    int weaponDamage = 0;
    for (Weapons w : this.attacker.getWeaponsUsed()) {
      weaponDamage += w.getStrikePower(this.attacker);
    }
    return this.attacker.getStrength() + weaponDamage;
  }
  
  /**
   * Check if the game is over. The game ends if the defender health 
   * drops below or equal to 0. In case of redundant moves, with 
   * players either defending or taking 0 damage for more than 20 
   * turns, the match is considered drawn and the game is over.
   * 
   * @return true if game is over
   */
  private boolean isGameOver() {
    if (this.defender.getCurrentHealth() <= 0 
        && this.winner.size() == 0) {
      this.isGameOver = true;
      this.winner.add(this.attacker);
    } else if (this.testTurnRedundancy()
        && this.winner.size() == 0) {
      this.isGameOver = true;
      this.winner.add(this.attacker);
      this.winner.add(this.defender);
    }
    return this.isGameOver;
  }

}
