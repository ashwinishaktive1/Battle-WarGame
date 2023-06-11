package battle;

import java.util.List;

/**
 * The Players interface is used to define players with the Strength, 
 * Constitution, Dexterity, and Charisma abilities. The Players have 
 * a pre-set health based on these abilities. They can play a variety of 
 * games including the "Battle".
 * 
 * @author Ashwini Shaktivel Kumar
 *
 */
public interface Players {
  
  /**
   * Get new player information with basic abilities.
   * 
   * @return player basic information
   */
  public String getPlayerBasicInfo();
  
  /**
   * Get the player description in following format:
   * a complete description of players that will enter the arena including 
   * the player's temporary ability values (based on the affects of the 
   * potions that they may have consumed) along with any and all the gear 
   * they are wearing, and what weapon they are using.
   * 
   * @return description
   */
  public String getPlayerDescription();
  
  /**
   * Get the player identifier/name.
   * @return player id
   */
  public String getPlayerId();
  
  /**
   * Player can request for a weapon used during the game. 
   * 
   * @param ar Armory from the battle
   * @throws IllegalStateException if the player has already received 
   *                               a weapon.
   */
  public void requestWeapon(Armory ar) throws IllegalStateException;
  
  /**
   * Get the identifier of the weapon used by the player. 
   * 
   * @return list of weapon id
   */
  public List<String> getWeaponIdUsed();
  
  /**
   * Get the weapon objects used by the player. 
   * 
   * @return list of weapon used by the player
   */
  public List<Weapons> getWeaponsUsed();
  
  /**
   * Get a sorted list of gears. Gear should be returned in order of top to bottom, 
   * then alphabetically: thus any headgear should come before 
   * potions which come before any belts which should come before any footwear.
   * 
   * @return sorted list of gear id
   */
  public List<String> getSortedGears();
  
  /**
   * Player can request for gears before the game and can 
   * equip the same for battle. The abilities are affected positively/
   * negatively by the gears.
   * 
   * @param eq Equipment Bag from the battle
   * @throws IllegalStateException if the player has already equipped 
   *                               the gears.
   */
  public void equipGears(EquipmentBag eq) throws IllegalStateException;
      
  /**
   * Get the remaining number of strikes for which certain gears can have a 
   * temporary effect.
   * 
   * @return number of strikes remaining where temporary abilities can be 
   *         used
   */
  public int getRemainingTemporaryEffectStrikes();
  
  /**
   * Get the initial health of the player before starting the game.
   * 
   * @return initial health of the player
   */
  public int getInitialHealth();
  
  /**
   * Get the current health of the player.
   * 
   * @return current health of the player
   */
  public int getCurrentHealth();
  
  /**
   * Apply damage to the player based on the match strike.
   * 
   * @param damage damage caused by the opponent
   */
  public void applyDamage(int damage);
  
  /**
   * Get the striking power of the player. Striking power is the 
   * sum of the strength of the player, any of the gear that adds 
   * (or subtracts) from strength, and a random number between 1 
   * and 10 (inclusive).
   * 
   * @return striking power of the player
   * @throws IllegalStateException if the player is not equipped with 
   *                               gears and weapons
   */
  public int getStrikingPower() throws IllegalStateException;
  
  /**
   * Get the avoidance ability of the player. Avoidance ability is 
   * the sum of the dexterity of the player, any of the gear that adds 
   * (or subtracts) from dexterity, and a random number between 1 
   * and 6 (inclusive).
   * 
   * @return avoidance ability of the player
   * @throws IllegalStateException if the player is not equipped with 
   *                               gears and weapons
   */
  public int getAvoidanceAbility() throws IllegalStateException;
  
  /**
   * Get the strength of the player after equipping the gears. Strength affects 
   * how effective the player is at striking their opponent. 
   * 
   * @return player strength
   */
  public int getStrength();
  
  /**
   * Get the constitution of the player after equipping the gears. Constitution 
   * affects how much damage a player can take when they are hit in battle.
   * 
   * @return player constitution
   */
  public int getConstitution();
  
  /**
   * Get the dexterity of the player after equipping the gears. Dexterity affects 
   * how effective the player is at avoiding a strike from their opponent.
   * 
   * @return player dexterity
   */
  public int getDexterity();
  
  /**
   * Get the charisma of the player after equipping the gears. Charisma affects 
   * how their opponent views them.
   * 
   * @return player charisma
   */
  public int getCharisma();
  
  /**
   * A string representing all fields of the player.
   * 
   * @return descriptive string
   */
  public String toString();
  
  /**
   * Set the players back to initial state with random ability and 
   * health values for a rematch. 
   */
  public void rematchSetup();
  
  /**
   * Get a list of used belts by the player.
   * 
   * @return list of used belts
   */
  public List<Gears> getUsedBelts();
}
