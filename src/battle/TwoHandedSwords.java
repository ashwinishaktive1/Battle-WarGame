package battle;

/**
 * Class represents two-handed sword weapon, used by a player during 
 * the battle. Two-handed swords are a heavy sword that can only be effectively 
 * wielded by players with strength greater than 14, but they can do 8-12 
 * points of damage when they hit. If the player does not have 
 * the strength to wield a two-handed sword, the sword only does half damage.
 * 
 * @author Ashwini Shaktivel Kumar
 *
 */
public class TwoHandedSwords extends AbstractWeapons {
  
  private RandomNumberGenerator rand;
  private String twoHandedSwordId;
  
  /**
   * Constructs a two handed sword initialized with an unique id. The 
   * flags indicating current status of the sword is by default set to 
   * false upon initialization.
   * 
   * @param id unique weapon id
   * @param r Random Number Generator
   */
  public TwoHandedSwords(int id, RandomNumberGenerator r) {
    super(r, WeaponWeightCategory.HEAVY);
    this.rand = r;
    this.twoHandedSwordId = "Two-handed Sword " + id;
  }

  @Override
  public String getWeaponId() {
    return this.twoHandedSwordId;
  }
  
  @Override
  public int getStrikePower(Players player) {
    if (this.checkIfPlayerCanUseWeapon(player)) {
      return rand.getRandomNumber(0, 4) + 8;
    } else {
      return rand.getRandomNumber(0, 2) + 4;
    }
  }
  
  /**
   * Check if the player can use the weapon to full extent.
   * 
   * @param player Player using the weapon
   * @return true if the 
   */
  private Boolean checkIfPlayerCanUseWeapon(Players player) {
    if (player.getStrength() > 14) {
      return true;
    }
    return false;
  }
}
