package battle;

import java.util.HashMap;
import java.util.Map;

/**
 * Class representing foot-wears. Footwear is worn on the player's feet and 
 * positively affects the player's dexterity. Since the footwear is equipped 
 * for a battle it reduced the charisma of the player. Footwear always comes in 
 * pairs and a player can only wear one piece of footwear at a time.
 * 
 * @author Ashwini Shaktivel Kumar
 *
 */
public class Footwear extends AbstractGears {
  
  private String footwearId;
  
  /**
   * Constructs a footwear initialized with an unique id. The 
   * flags indicating current status of the footwear is by default set to 
   * false upon initialization.
   * 
   * @param id gear identifier
   */
  public Footwear(int id) {
    super();
    this.footwearId = "Footwear " + id;
  }

  @Override
  public String getGearId() {
    return this.footwearId;
  }

  @Override
  public Map<String, Integer> checkEffectOfGear() {
    Map<String, Integer> map = new HashMap();
    map.put("Strength", 0);
    map.put("Constitution", -3);
    map.put("Dexterity", 5);
    map.put("Charisma", -2);
    
    return map;
  }
}
