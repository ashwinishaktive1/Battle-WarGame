package battle;

/**
 * Class represents an axe weapon, used by a player during 
 * the battle. Axes are great general weapons doing 6-10 points of 
 * damage when they hit.
 * 
 * @author Ashwini Shaktivel Kumar
 *
 */
public class Axes extends AbstractWeapons {
  
  private String axeId;
  
  /**
   * Constructs an axe initialized with an unique id. The 
   * flags indicating current status of the axe is by default set to 
   * false upon initialization.
   * 
   * @param id unique weapon id
   * @param r Random Number Generator
   */
  public Axes(int id, RandomNumberGenerator r) {
    super(r, WeaponWeightCategory.MEDIUM);
    this.axeId = "Axe " + id;
  }

  @Override
  public String getWeaponId() {
    return this.axeId;
  }
}
