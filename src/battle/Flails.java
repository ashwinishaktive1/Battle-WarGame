package battle;

/**
 * Class represents a flail weapon, used by a player during 
 * the battle. Flails are also great general weapons but they can 
 * only be effectively wielded by players with a dexterity greater than 14. 
 * They do 8-12 points of damage when they hit. If the player does not have 
 * the dexterity to wield a flail, the flail only does half damage.
 * 
 * @author Ashwini Shaktivel Kumar
 *
 */
public class Flails extends AbstractWeapons {
  
  private RandomNumberGenerator rand;
  private String flailId;
  
  /**
   * Constructs a flail initialized with an unique id. The 
   * flags indicating current status of the flail is by default set to 
   * false upon initialization.
   * 
   * @param id unique weapon id
   * @param r Random Number Generator
   */
  public Flails(int id, RandomNumberGenerator r) {
    super(r, WeaponWeightCategory.HEAVY);
    this.rand = r;
    this.flailId = "Flail " + id;
  }

  @Override
  public String getWeaponId() {
    return this.flailId;
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
    if (player.getDexterity() > 14) {
      return true;
    }
    return false;
  }
}
