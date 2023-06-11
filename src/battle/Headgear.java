package battle;

import java.util.HashMap;
import java.util.Map;

/**
 * Class representing the headgears. Headgear is worn on the player's head and 
 * affects the player's constitution by increasing it and consequently 
 * decreases the charisma, as now, their face is not visible. Since a player 
 * has one head, they can only wear one piece of headgear.
 * 
 * @author Ashwini Shaktivel Kumar
 *
 */
public class Headgear extends AbstractGears {
  
  private String headgearId;
  
  /**
   * Constructs a headgear initialized with an unique id. The 
   * flags indicating current status of the headgear is by default set to 
   * false upon initialization.
   * 
   * @param id gear identifier
   */
  public Headgear(int id) {
    super();
    this.headgearId = "Headgear " + id;
  }

  @Override
  public String getGearId() {
    return this.headgearId;
  }

  @Override
  public Map<String, Integer> checkEffectOfGear() {
    Map<String, Integer> map = new HashMap();
    map.put("Strength", 0);
    map.put("Constitution",  2);
    map.put("Dexterity", 0);
    map.put("Charisma", -2);
    
    return map;
  }
}
