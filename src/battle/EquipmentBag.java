package battle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * A class representing the equipment bag containing a variety 
 * of gears that can be equipped by the players. The bag of equipment 
 * contains a minimum of 5 items of headgear, 5 items of footwear, 
 * 15 belts, and 15 potions.
 * 
 * @author Ashwini Shaktivel Kumar
 *
 */
public class EquipmentBag {
  
  private RandomNumberGenerator random;
  private List<Gears> gearsInEquipmentBag;
  private boolean checkMinimumOfAllGearTypes;
  private List<Gears> headgearsInBag;
  private List<Gears> potionsInBag;
  private List<Gears> beltsInBag;
  private List<Gears> footwearInBag;
  
  /**
   * Construct an equipment bag with a number gears of each type 
   * at least equal to the minimum requirement of each type. 
   * 25% of the items that are in the bag will diminish the player's 
   * ability rather than enhance it.
   * 
   * @param rand Random Numbers Generator
   * @throws IllegalStateException if the number gears generate in each 
   *                               type are not greater than or equal 
   *                               to the minimum requirement or if
   *                               25% of items in the bag do not have 
   *                               a negative effect on the players' 
   *                               abilities.
   */
  public EquipmentBag(RandomNumberGenerator rand) throws IllegalStateException {
    this.checkMinimumOfAllGearTypes = false;
    this.gearsInEquipmentBag = new ArrayList<>();
    this.headgearsInBag = new ArrayList<>();
    this.footwearInBag = new ArrayList<>();
    this.potionsInBag = new ArrayList<>();
    this.beltsInBag = new ArrayList<>();
    
    this.random = rand;
    int i;
    
    int numberOfHeadgears = this.random.getRandomNumber(0, 5) + 5;
    for (i = 0; i < numberOfHeadgears; i++) {
      Gears hg = new Headgear(i + 1);
      this.gearsInEquipmentBag.add(hg);
      this.headgearsInBag.add(hg);
    }
    
    int numberOfFootwears = this.random.getRandomNumber(0, 5) + 5;
    for (i = 0; i < numberOfFootwears; i++) {
      Gears fw = new Footwear(i + 1);
      this.gearsInEquipmentBag.add(fw);
      this.footwearInBag.add(fw);
    }
    
    int numberOfPotions = this.random.getRandomNumber(0, 15) + 15;
    for (i = 0; i < numberOfPotions; i++) {
      Gears pot = new Potions(i + 1);
      this.gearsInEquipmentBag.add(pot);
      this.potionsInBag.add(pot);
    }
    
    int numberOfBelts = this.random.getRandomNumber(0, 15) + 15;
    List<BeltSizes> availableBeltSizes = Arrays.asList(BeltSizes.SMALL,
        BeltSizes.MEDIUM, BeltSizes.LARGE); 
    List<Integer> randBeltSize = this.random.getRepeatedRandomNumbersList(numberOfBelts, 
        0, availableBeltSizes.size());
    for (i = 0; i < numberOfBelts; i++) {
      BeltSizes bs = availableBeltSizes.get(randBeltSize.get(i));
      Gears belt = new Belts(i + 1, bs);
      this.gearsInEquipmentBag.add(belt);
      this.beltsInBag.add(belt);
    }
    
    
    if (!this.checkIfMinimumOfAllGearTypesInBag()) {
      throw new IllegalStateException("The equipment bag does not contain enough of all "
          + "gear types. The bag of equipments must contain "
          + "a minimum of 5 items of headgear, 5 items of footwear, "
          + "15 belts, and 15 potions.");
    }
    if (!this.checkTotalNegativeEffect()) {
      throw new IllegalStateException("The equipment bag does not contain enough gears "
          + "that are diminishing the player abilities.");
    }
  }
 
  /**
   * Get gears assigned to the player. When players equip themselves from the bag, 
   * they are randomly assigned 20 items from the bag.
   * 
   * @return a list of gears
   * @throws IllegalStateException if the number of gears in the bag is less
   *                               than 20
   */
  public List<Gears> getGears() throws IllegalStateException {
    if (this.gearsInEquipmentBag.size() < 20) {
      throw new IllegalStateException("The required number of gears for "
          + "a new player is unavailable in the equipment bag. "
          + "Please refresh arena.");
    }
    
    List<Gears> gearsAssignedToPlayer = new ArrayList<>();
    
    List<Integer> randomIndices = this.random.getUniqueRandomNumbersList(20, 0, 
        this.gearsInEquipmentBag.size());
    
    for (int i = 0; i < 20; i++) {
      int randomIndex = randomIndices.get(i);
      gearsAssignedToPlayer.add(this.gearsInEquipmentBag.get(randomIndex));
    }

    for (Gears g : gearsAssignedToPlayer) {
      this.gearsInEquipmentBag.remove(g);
    }
    
    return gearsAssignedToPlayer;
  }
  
  /**
   * Check if at least 25% of gears have negative effect on the 
   * player abilities.
   * 
   * @return true if 25% of all the gears have negative effect on 
   *         at least one player ability
   */
  private boolean checkTotalNegativeEffect() {
    int count = 0;
    for (Gears g : this.gearsInEquipmentBag) {
      if (this.checkGearNegativeEffect(g)) {
        count++;
      }
    }
    if ((float) count / this.gearsInEquipmentBag.size() >= 0.25) {
      return true;
    }
    return false;
  }
  
  /**
   * Check if the gear has a negative effect on the player 
   * abilities.
   * 
   * @param gear Candidate gear
   * @return true if the gear negatively effects at least one 
   *         ability
   */
  private boolean checkGearNegativeEffect(Gears gear) {
    Map<String, Integer> effect = gear.checkEffectOfGear();
    for (Integer i : effect.values()) {
      if (i < 0) {
        return true;
      }
    }
    return false;
  }
  
  /**
   * Check if minimum number of gears of each type are in the 
   * equipment bag.
   * 
   * @return true if all gear types have minimum required counts
   */
  private boolean checkIfMinimumOfAllGearTypesInBag() {
    if (this.headgearsInBag.size() >= 5 && this.footwearInBag.size() >= 5
        && this.beltsInBag.size() >= 15 && this.potionsInBag.size() >= 15) {
      this.checkMinimumOfAllGearTypes = true;
    }
    return this.checkMinimumOfAllGearTypes;
  }
}
