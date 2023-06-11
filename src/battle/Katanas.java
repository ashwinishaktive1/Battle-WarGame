package battle;

/**
 * Class represents a katana sword weapon, used by a player during 
 * the battle. Katanas are lightweight curved swords that come in pairs. 
 * They can do a base of 4-6 points of damage when they hit. They are so 
 * light that a player can carry two of them (which attack separately).
 * 
 * @author Ashwini Shaktivel Kumar
 *
 */
public class Katanas extends AbstractWeapons {
  
  private String katanaId;
  
  /**
   * Constructs a katana initialized with an unique id. The 
   * flags indicating current status of the katana is by default set to 
   * false upon initialization.
   * 
   * @param id unique weapon id
   * @param r Random Number Generator
   */
  public Katanas(int id, RandomNumberGenerator r) {
    super(r, WeaponWeightCategory.LIGHT);
    this.katanaId = "Katana " + id;
  }

  @Override
  public String getWeaponId() {
    return this.katanaId;
  }
}
