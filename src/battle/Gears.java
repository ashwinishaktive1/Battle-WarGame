package battle;

import java.util.Map;

/**
 * Interface that represents the gears/equipments used by the 
 * players during a battle. The types of gears include: Headgear, 
 * Potions, Belts, and Footwear. The gears may have a positive or 
 * negative effect on the player abilities.
 * 
 * @author Ashwini Shaktivel Kumar
 *
 */
public interface Gears {
  
  /**
   * Return the id of the gear. Every gear must have an unique 
   * identifier.
   * 
   * @return the gear identifier
   */
  public String getGearId();
  
  /**
   * Check if the gear is assigned to a player. The player must use the 
   * gear before starting the match. Returns true if the gear is assigned 
   * to a player.
   * 
   * @return true if gear is used by a player and false if not used by 
   *         any player
   */
  public boolean getIfGearUsedByPlayer();
  
  /**
   * Set true if the gear is assigned to a player.
   * 
   * @param update true if the gear is assigned to a player
   *               and false if removed
   */
  public void setIfGearUsedByPlayer(Boolean update) throws IllegalArgumentException;
  
  /**
   * Set true if the player's abilities are updated upon usage.
   * 
   * @param update true if the player's abilities are updated according 
   *        to the gear effects
   */
  public void setIsPlayerAbilitiesUpdated(Boolean update) throws IllegalArgumentException;
  
  /**
   * Check if the gear is added to the equipment bag.
   * 
   * @return true if the gear is in the equipment bag and false if still 
   *         not added to the bag
   */
  public boolean getIfGearAddedtoEquipmentBag();
  
  /**
   * Set true if the gear is added to an equipment bag.
   * 
   * @param update true if the gear is added to the bag
   *               and false if removed
   */
  public void setIfGearAddedtoEquipmentBag(Boolean update) throws IllegalArgumentException;
  
  /**
   * Return a map of every ability and corresponding integer effect of 
   * the gear on the current abilities of the player.
   * 
   * @return map of abilities and corresponding positive/negative
   *         effects
   */
  public Map<String, Integer> checkEffectOfGear();
}
