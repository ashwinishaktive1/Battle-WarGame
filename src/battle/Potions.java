package battle;

import java.util.HashMap;
import java.util.Map;

/**
 * Class representing potion gear. Potions are consumed by the player 
 * before entering the arena. They can temporarily increase the strength 
 * ability. The temporary effect remains for 2 strikes by the player as 
 * an attacker. There is no limit to the number of these that the player 
 * can drink.
 * 
 * @author Ashwini Shaktivel Kumar
 *
 */
public class Potions extends AbstractGears {
  
  private String potionId;
  private int temporaryEffect;
  
  /**
   * Constructs a potion initialized with an unique id. The 
   * flags indicating current status of the potion is by default set to 
   * false upon initialization.
   * 
   * @param id gear identifier
   */
  public Potions(int id) {
    super();
    this.potionId = "Potion " + id;
    this.temporaryEffect = 2;
  }

  @Override
  public String getGearId() {
    return this.potionId;
  }

  @Override
  public Map<String, Integer> checkEffectOfGear() {
    Map<String, Integer> map = new HashMap();
    map.put("Strength", 1);
    map.put("Constitution",  -1);
    map.put("Dexterity", 1);
    map.put("Charisma", 0);
    
    return map;
  }
  
  /**
   * Get the number of strikes the potion can have an effect 
   * on the players' abilities.
   * 
   * @return number of strikes for temporary effect
   */
  public int getTemporaryEffectUsage() {
    return this.temporaryEffect;
  }
}
