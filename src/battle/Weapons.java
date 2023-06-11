package battle;

/**
 * Interface that represents the weapon used by the 
 * players during a battle. The player can wield a sword, an axe, 
 * or a flail as their weapon of choice. The player may use only 
 * one type of weapon for the battle.
 * 
 * @author Ashwini Shaktivel Kumar
 */
public interface Weapons {
  
  /**
   * Return the id of the weapon. Every weapon must have an unique 
   * identifier.
   * 
   * @return the weapon identifier
   */
  public String getWeaponId();
  
  /**
   * Get the amount of damage that can be done by the weapon. For 
   * certain weapons the damage (full/half) depends on the player 
   * abilities.
   * 
   * @param player striking power for player
   * @return damaging points
   */
  public int getStrikePower(Players player);
  
  /**
   * Check if the weapon is assigned to a player. Returns true if 
   * the weapon is assigned to a player.
   * 
   * @return true if weapon is used by a player and false if not used by 
   *         any player
   */
  public Boolean getIfWeaponUsedByPlayer();
  
  /**
   * Set true if the weapon is assigned to a player.
   * 
   * @param update true if the weapon is assigned to a player
   *               and false if removed
   */
  public void setIfWeaponUsedByPlayer(Boolean update);
  
  /**
   * Check if the weapon is added to the armory.
   * 
   * @return true if the weapon is in the armory and false if still 
   *         not added to the armory
   */
  public Boolean getIfWeaponAddedtoArmory();
  
  /**
   * Set true if the weapon is added to an armory.
   * 
   * @param update true if the weapon is added to the bag
   *               and false if removed
   */
  public void setIfWeaponAddedtoArmory(Boolean update);
}
