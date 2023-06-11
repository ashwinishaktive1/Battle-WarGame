package battle;

/**
 * This is an abstract class representing the common features 
 * amongst the various types of weapons. It contains the common 
 * methods between the extending child weapon classes.
 * 
 * @author Ashwini Shaktivel Kumar
 *
 */
public abstract class AbstractWeapons implements Weapons {
  
  protected WeaponWeightCategory weaponSize;
  private boolean isUsedByPlayer;
  private boolean isAddedToArmory;
  private RandomNumberGenerator rand;
  
  /**
   * Construct an abstract weapon.
   * 
   * @param r Random Number Generator
   * @param size weapon size
   * @throws IllegalArgumentException if size is not of enum type or null or 
   *                                  if random number generator is null
   */
  public AbstractWeapons(RandomNumberGenerator r, WeaponWeightCategory size) 
      throws IllegalArgumentException {
    if (!(size instanceof WeaponWeightCategory) || size == null) {
      throw new IllegalArgumentException("The weapon must be of one size amongst "
          + "the following: Light, Medium, Heavy");
    }
    if (r == null) {
      throw new IllegalArgumentException("Random Number Generator cannot be null.");
    }
    this.rand = r;
    this.isUsedByPlayer = false;
    this.isAddedToArmory = false;
    this.weaponSize = size;
  }

  @Override
  public abstract String getWeaponId();

  @Override
  public int getStrikePower(Players player) {
    if (this.weaponSize == WeaponWeightCategory.LIGHT) {
      return rand.getRandomNumber(0, 2) + 4;
    } else if (this.weaponSize == WeaponWeightCategory.MEDIUM) {
      return rand.getRandomNumber(0, 4) + 6;
    }
    return 0;
  }

  @Override
  public Boolean getIfWeaponUsedByPlayer() {
    return this.isUsedByPlayer;
  }

  @Override
  public void setIfWeaponUsedByPlayer(Boolean update) 
      throws IllegalArgumentException {
    if (update == false) {
      throw new IllegalArgumentException("The weapon cannot be removed "
          + "from player assignment.");
    }
    this.isUsedByPlayer = update;
  }

  @Override
  public Boolean getIfWeaponAddedtoArmory() {
    return this.isAddedToArmory;
  }

  @Override
  public void setIfWeaponAddedtoArmory(Boolean update) 
      throws IllegalArgumentException {
    if (update == false) {
      throw new IllegalArgumentException("The weapon cannot be removed "
          + "from armory, for reasons other than assigning it "
          + "to a player.");
    }
    this.isAddedToArmory = update;
  }
}
