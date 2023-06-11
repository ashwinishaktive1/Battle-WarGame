package battle;

/**
 * Class represents broad sword weapon, used by a player during 
 * the battle. Broadswords are a good medium weapon that can do 
 * 6-10 points of damage when they hit.
 * 
 * @author Ashwini Shaktivel Kumar
 *
 */
public class BroadSwords extends AbstractWeapons {
  
  private String broadSwordId;
  
  /**
   * Constructs a broad sword initialized with an unique id. The 
   * flags indicating current status of the sword is by default set to 
   * false upon initialization.
   * 
   * @param id unique weapon id
   * @param r Random Number Generator
   */
  public BroadSwords(int id, RandomNumberGenerator r) {
    super(r, WeaponWeightCategory.MEDIUM);
    this.broadSwordId = "Broad Sword " + id;
  }

  @Override
  public String getWeaponId() {
    return this.broadSwordId;
  }
}
