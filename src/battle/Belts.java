package battle;

import java.util.HashMap;
import java.util.Map;

/**
 * Class representing belt gears. Belts come in three sizes ({@link BeltSizes}) 
 * and are worn around the player's torso affecting the constitution 
 * and dexterity player abilities.
 *  
 * Players have the ability to wear 10 "units" of belts where 
 * small belts count as 1 unit, medium as 2 units, and large as 4 units. 
 * 
 * @author Ashwini Shaktivel Kumar
 *
 */
public class Belts extends AbstractGears {
  
  private String beltId;
  private BeltSizes beltSize;
  
  /**
   * Constructs a belt initialized with an unique id and given size. The 
   * flags indicating current status of the belt is by default set to 
   * false upon initialization.
   * 
   * @param id gear identifier
   * @param size belt size
   * @throws IllegalArgumentException if the belt size not one amongst 
   *                                  the required size categories
   */
  public Belts(int id, BeltSizes size) throws IllegalArgumentException {
    super();
    if (!(size instanceof BeltSizes)) {
      throw new IllegalArgumentException("The belt sizes must be one amongst "
          + "the following: Small, Medium, Large");
    }
    this.beltId = "Belt " + id;
    this.beltSize = size;
  }

  @Override
  public String getGearId() {
    return this.beltId;
  }

  @Override
  public Map<String, Integer> checkEffectOfGear() {
    int effectConstitution = 0;
    int effectDexterity = 0;
    
    if (this.beltSize == BeltSizes.SMALL) {
      effectConstitution = 1;
      effectDexterity = -1;
    } else if (this.beltSize == BeltSizes.MEDIUM) {
      effectConstitution = 2;
      effectDexterity = -2;
    } else {
      effectConstitution = 3;
      effectDexterity = -2;
    }
    
    Map<String, Integer> map = new HashMap();
    map.put("Strength", 0);
    map.put("Constitution",  effectConstitution);
    map.put("Dexterity", effectDexterity);
    map.put("Charisma", 0);
    
    return map;
  }
  
  /**
   * Return the size of the belt.
   * 
   * @return belt size
   */
  public BeltSizes getBeltSize() {
    return this.beltSize;
  }
}
