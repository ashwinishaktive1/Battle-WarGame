package battle;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing the armory containing a variety 
 * of weapons that can be handled by the players. players to request a 
 * weapon from the armory. Requests for a weapon are satisfied by 
 * randomly selecting one of the many weapons that are available 
 * (at least 1 of each type of weapon).
 * 
 * @author Ashwini Shaktivel Kumar
 *
 */
public class Armory {
  
  private RandomNumberGenerator random;
  private List<Weapons> weaponsInArmory;
  private List<Weapons> katanasInArmory;
  
  /**
   * Construct an Armory with atleast 1 unit of each type of weapon. 
   * 
   * @param rand Random Numbers Generator
   */
  public Armory(RandomNumberGenerator rand) {
    if (rand == null) {
      throw new IllegalArgumentException("The random number generator cannot "
          + "be null.");
    }
    this.weaponsInArmory = new ArrayList<>();
    this.katanasInArmory = new ArrayList<>();
    
    this.random = rand;
    int i;
    
    int numberOfAxes = this.random.getRandomNumber(0, 3) + 1;
    for (i = 0; i < numberOfAxes; i++) {
      Weapons axe = new Axes(i + 1, this.random);
      this.weaponsInArmory.add(axe);
    }
    
    int numberOfFlails = this.random.getRandomNumber(0, 3) + 1;
    for (i = 0; i < numberOfFlails; i++) {
      Weapons flail = new Flails(i + 1, this.random);
      this.weaponsInArmory.add(flail);
    }
    
    int numberOfBroadSwords = this.random.getRandomNumber(0, 3) + 1;
    for (i = 0; i < numberOfBroadSwords; i++) {
      Weapons bs = new BroadSwords(i + 1, this.random);
      this.weaponsInArmory.add(bs);
    }
    
    int numberOfKatanas = this.random.getRandomNumber(0, 3) + 1;
    for (i = 0; i < numberOfKatanas; i++) {
      Weapons katana = new Katanas(i + 1, this.random);
      this.weaponsInArmory.add(katana);
      this.katanasInArmory.add(katana);
    }
    
    int numberOfTwoHandedSwords = this.random.getRandomNumber(0, 3) + 1;
    for (i = 0; i < numberOfTwoHandedSwords; i++) {
      Weapons ths = new TwoHandedSwords(i + 1, this.random);
      this.weaponsInArmory.add(ths);
    }
  }
  
  /**
   * Get a weapon for the player. If the weapon is a Katana, a pair of 
   * the light-weight katana swords are returned.
   * 
   * @return list of assigned weapon(s)
   */
  public List<Weapons> getWeapon() throws IllegalStateException {
    if (this.weaponsInArmory.size() < 2) {
      throw new IllegalStateException("Weapons unavailable in armory. "
          + "Please refresh arena.");
    }
    
    List<Weapons> weaponAssignedToPlayer = new ArrayList<>();
    
    Weapons playerWeaponOne = this.weaponsInArmory.get(this.random
                                                  .getRandomNumber(0, this.weaponsInArmory.size()));
    
    // Check if weapon is a Katana
    if (this.checkIfWeaponIsKatana(playerWeaponOne)) {
      // If number of Katanas in the armory is just 1, then choose 
      // another weapon or choose another Katana
      if (this.katanasInArmory.size() < 2) {
        weaponAssignedToPlayer = this.removeKatanaAddOther(playerWeaponOne);
      } else {
        weaponAssignedToPlayer = this.getMoreKatanas(playerWeaponOne);
      }
    } else {
      weaponAssignedToPlayer.add(playerWeaponOne);
      this.weaponsInArmory.remove(playerWeaponOne);
    }
    
    return weaponAssignedToPlayer;
  }
  
  /**
   * Check if the weapon is a Katana.
   * 
   * @param w weapon for test
   * @return true if the weapon is a katana sword
   */
  private boolean checkIfWeaponIsKatana(Weapons w) {
    if ('K' == w.getWeaponId().charAt(0)) {
      return true;
    }
    return false;
  }
  
  /**
   * Get another Katana for the player.
   * 
   * @param w1 first assigned katana sword
   * @param r random number generator
   * @return a list of katanas
   */
  private List<Weapons> getMoreKatanas(Weapons w1) {
    List<Weapons> katanaList = new ArrayList<>();
    
    katanaList.add(w1);
    this.weaponsInArmory.remove(w1);
    this.katanasInArmory.remove(w1);
    
    Weapons w2 = this.katanasInArmory.get(this.random
                                     .getRandomNumber(0, this.katanasInArmory.size()));
    katanaList.add(w2);
    this.weaponsInArmory.remove(w2);
    this.katanasInArmory.remove(w2);

    return katanaList;
  }
  
  private List<Weapons> removeKatanaAddOther(Weapons w1) {
    List<Weapons> weaponList = new ArrayList<>();
    
    this.weaponsInArmory.remove(w1);
    w1 = this.weaponsInArmory.get(this.random
                             .getRandomNumber(0, this.weaponsInArmory.size()));
    weaponList.add(w1);
    this.weaponsInArmory.remove(w1);
    return weaponList;
  }
}
