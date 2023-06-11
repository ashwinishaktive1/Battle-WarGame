package battle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * A BattlePlayer takes part and competes with other players on the 
 * arena to win the game of "Battle". The player is assigned with 
 * abilities, gears and weapons that they can use to strike and defend 
 * from other players. 
 * 
 * @author Ashwini Shaktivel Kumar
 *
 */
public class BattlePlayer implements Players {
  private String playerId;
  private final int initialPlayerHealth;
  private final int initialStrength;
  private final int initialConstitution;
  private final int initialDexterity;
  private final int initialCharisma;
  private int currentPlayerHealth;
  private int strength;
  private int temporaryStrength;
  private int constitution;
  private int temporaryConstitution;
  private int dexterity;
  private int temporaryDexterity;
  private int charisma;
  private int temporaryCharisma;
  private int headgearUnits;
  private int footwearUnits;
  private int beltUnits;
  private int temporaryEffectStrikes;
  private List<Gears> equipments;
  private List<Gears> headGearUsed;
  private List<Gears> footWearUsed;
  private List<Gears> beltsUsed;
  private List<Gears> potionsUsed;
  private List<Weapons> weapon;
  private RandomNumberGenerator random;
  
  /**
   * Construct a battle player with an identifier. The battle player starts 
   * with randomly assigned abilities and health. They're automatically 
   * equipped with the gears and weapon from the equipment bag and armory set 
   * up in on the battle arena. The gears are used if the player's have the 
   * capacity. Based on the abilities the players show striking power while 
   * attacking and avoidance ability while defending. 
   * 
   * @param id Player identifier
   * @param rand RandomNumberGenerator
   * @throws IllegalArgumentException if the equipment bag or armory is null
   */
  public BattlePlayer(int id, RandomNumberGenerator rand) throws 
      IllegalArgumentException {
    if (rand == null) {
      throw new IllegalArgumentException("The random number generator cannot "
          + "be null.");
    }
    this.random = rand;
    this.playerId = "Player " + id;
    this.strength = getAbility();
    this.constitution = getAbility();
    this.dexterity = getAbility();
    this.charisma = getAbility();
    this.initialStrength = this.strength;
    this.initialConstitution = this.constitution;
    this.initialDexterity = this.dexterity;
    this.initialCharisma = this.charisma;
    this.initialPlayerHealth = this.strength + this.constitution 
                               + this.dexterity + this.charisma;
    this.currentPlayerHealth = this.initialPlayerHealth;
    
    this.refreshPlayer();
  }
  
  @Override
  public String getPlayerBasicInfo() {
    return this.playerId + "\n" + "Player basic "
      + "abilities for the entire game: \n" 
      + "Strength - " + this.initialStrength + "\n"
      + "Constitution - " + this.initialConstitution + "\n"
      + "Dexterity - " + this.initialDexterity + "\n"
      + "Charisma - " + this.initialCharisma;
  }

  @Override
  public String getPlayerDescription() {
    String gears = "";
    String weap = "";
    String gearsAndWeapon = "";
    int effect = 0;
    
    // Temporary effect for description
    if (this.temporaryEffectStrikes != 0) {
      effect = this.temporaryEffectStrikes - 1;
    }
    
    // Adding comments depending on whether the player is equipped with 
    // gears and/or weapon.
    if (this.getSortedGears().size() == 0 && this.getWeaponIdUsed().size() > 0) {
      gears = "Player needs to equip gears! \n";
    }
    if (this.getWeaponIdUsed().size() == 0 && this.getSortedGears().size() > 0) {
      weap = "Player needs to request a weapon!";
    }
    if (this.getSortedGears().size() == 0 && this.getWeaponIdUsed().size() == 0) {
      gearsAndWeapon = "Player needs to equip gears and weapons to start the match!";
    }
    if (this.getWeaponIdUsed().size() > 0 && this.getSortedGears().size() > 0) {
      gearsAndWeapon = "Wohoo!! You look ready, let's start the game!";
    }
    
    return this.playerId + "\n" + "Player abilities for the entire game: \n" 
           + "Strength - " + this.strength + "\n"
           + "Constitution - " + this.constitution + "\n"
           + "Dexterity - " + this.dexterity + "\n"
           + "Charisma - " + this.charisma + "\n"
           + "\n" 
           + "The player may temporarily take advangtage of the following "
           + "abilities gained through the effect of potion! \n"
           + "Strength - " + this.temporaryStrength + "\n"
           + "Constitution - " + this.temporaryConstitution + "\n"
           + "Dexterity - " + this.temporaryDexterity + "\n"
           + "Charisma - " + this.temporaryCharisma + "\n"
           + "The temporary effect remains for " + effect + " strikes. \n"
           + "Player is equipped with following gears: \n" + this.getSortedGears()
           + "\n" 
           + gears
           + "Player is using the following weapon: \n" + this.getWeaponIdUsed()
           + "\n" 
           + weap + gearsAndWeapon;
  }
  
  @Override
  public String getPlayerId() {
    return this.playerId;
  }
  
  @Override
  public void requestWeapon(Armory arm) throws IllegalStateException {
    if (arm == null) {
      throw new IllegalArgumentException("The armory cannot be null.");
    }
    if (this.weapon.size() > 0) {
      throw new IllegalStateException("Player has already received weapon. No "
          + "more weapons are allowed/necessary.");
    }
    this.weapon = arm.getWeapon();
  }
  
  @Override
  public void equipGears(EquipmentBag eq) throws IllegalStateException {
    if (eq == null) {
      throw new IllegalArgumentException("The equipment bag cannot be null.");
    }
    if (this.equipments.size() > 0) {
      throw new IllegalStateException("Player has already equipped gears. No "
          + "more gears are allowed/necessary.");
    }
    
    this.equipments = eq.getGears();
    Map<String, Integer> effect = new HashMap<>();
    for (Gears gear : equipments) {
      
      effect = gear.checkEffectOfGear();
      Iterator<Map.Entry<String, Integer>> itr = effect.entrySet().iterator();
      
      // Check if gear is a potion, get it's temporary effect
      if (gear.getGearId().charAt(0) == 'P') {
        this.potionsUsed.add(gear);
        gear.setIfGearUsedByPlayer(true);
        while (itr.hasNext()) {
          Map.Entry<String, Integer> entry = itr.next();
          String ability = entry.getKey();
          Integer points = entry.getValue();
          this.updateTemporaryAbilityPoints(ability, points);
        }
      } else { 
        if (this.checkIfGearUseful(gear)) {
          this.useGear(gear);
          while (itr.hasNext()) {
            Map.Entry<String, Integer> entry = itr.next();
            String ability = entry.getKey();
            Integer points = entry.getValue();
            this.updateMatchAbilityPoints(ability, points);
          }
        }
      } 
    }
    this.temporaryEffectStrikes = getInitialTemporaryEffectStrikes();
  }
    
  /**
   * Update the abilities staying for the entire battle according to the 
   * effect of the gear.
   * 
   * @param ability ability name
   * @param points the positive/negative effect on the ability
   */
  private void updateMatchAbilityPoints(String ability, Integer points) {
    if ("Strength".equals(ability)) {
      this.strength += points;
    } else if ("Constitution".equals(ability)) {
      this.constitution += points;
    } else if ("Dexterity".equals(ability)) {
      this.dexterity += points;
    } else if ("Charisma".equals(ability)) {
      this.charisma += points;
    }
  }
  
  /**
   * Update the temporary abilities to the temporary 
   * effect of the gear.
   * 
   * @param ability ability name
   * @param points the positive/negative effect on the ability
   */
  private void updateTemporaryAbilityPoints(String ability, Integer points) {
    if ("Strength".equals(ability)) {
      this.temporaryStrength += points;
    } else if ("Constitution".equals(ability)) {
      this.temporaryConstitution += points;
    } else if ("Dexterity".equals(ability)) {
      this.temporaryDexterity += points;
    } else if ("Charisma".equals(ability)) {
      this.temporaryCharisma += points;
    }
  }
  
  /**
   * Check if the gear can be used according to the current gears used 
   * by the player.
   * 
   * @param gear Gear to be checked
   * @return true if the player can use the gear
   */
  private boolean checkIfGearUseful(Gears gear) {
    char gearType = gear.getGearId().charAt(0);
    if (gearType == 'H') {
      if (this.checkHeadGearAllowance()) {
        return true;
      }
    } else if (gearType == 'F') {
      if (this.checkFootwearAllowance()) {
        return true;
      } 
    } else if (gearType == 'B') {
      BeltSizes size = ((Belts) gear).getBeltSize();
      if (this.checkBeltAllowance(size)) {
        return true;
      }
    }
    return false;
  }
  
  /**
   * Check if headgear can be used. 
   * 
   * @return true if the player has not used any headgear yet
   */
  private boolean checkHeadGearAllowance() {
    if (this.headgearUnits == 1) {
      return true;
    }
    return false;
  }
  
  /**
   * Check if footwear can be used. 
   * 
   * @return true if the player has not used any footwear yet
   */
  private boolean checkFootwearAllowance() {
    if (this.footwearUnits == 1) {
      return true;
    }
    return false;
  }
  
  /**
   * Check if belt can be used. 
   * 
   * @param size size of the belt
   * @return true if the player has not used enough belts yet
   */
  private boolean checkBeltAllowance(BeltSizes size) {
    if (this.getBeltUnitsRequirement(size) <= this.beltUnits) {
      return true;
    }
    return false;
  }
  
  /**
   * Get the space/unit requirement for the belt size.
   * 
   * @param size size of the belt
   * @return 1 if the belt is small, 2 if the bell is medium sized 
   *         and 4 if the belt is large
   */
  public int getBeltUnitsRequirement(BeltSizes size) {
    if (size == BeltSizes.SMALL) {
      return 1;
    } else if (size == BeltSizes.MEDIUM) {
      return 2;
    } else {
      return 4;
    }
  }
  
  /**
   * Use the headgear.
   * 
   * @param gear headgear
   */
  private void useHeadGear(Gears gear) {
    this.headGearUsed.add(gear);
    gear.setIfGearUsedByPlayer(true);
    this.headgearUnits = 0;
  }
  
  /**
   * Use the footwear.
   * 
   * @param gear footwear
   */
  private void useFootwear(Gears gear) {
    this.footWearUsed.add(gear);
    gear.setIfGearUsedByPlayer(true);
    this.footwearUnits = 0;;
  }
  
  /**
   * Use the belt.
   * 
   * @param gear belt
   * @param size belt size
   */
  private void useBelt(Gears gear, BeltSizes size) {
    this.beltsUsed.add(gear);
    gear.setIfGearUsedByPlayer(true);
    this.beltUnits -= this.getBeltUnitsRequirement(size);
  }
  
  /**
   * Use the gear depending on it's type. 
   * 
   * @param gear received gear from armory
   */
  private void useGear(Gears gear) {
    char gearType = gear.getGearId().charAt(0);
    if (gearType == 'H') {
      this.useHeadGear(gear);
    } else if (gearType == 'F') {
      this.useFootwear(gear);
    } else if (gearType == 'B') {
      BeltSizes size = ((Belts) gear).getBeltSize();
      this.useBelt(gear, size);
    }
  }
  
  /**
   * Assign abilities to the player using random number 
   * generator. Player abilities are determined randomly by rolling 
   * four 6-sided dice, re-rolling any 1s, and then adding the 
   * resulting four values together resulting in a number between 8 
   * and 24, and then assigned to 4 categories
   * 
   * @return randomly assigned ability
   */
  private int getAbility() {
    int totalAbility = 0;
    for (int i = 0; i < 4; i++) {
      int face = this.random.getRandomNumber(1, 6);
      while (face == 1) {
        face = this.random.getRandomNumber(1, 6);
      }
      totalAbility += face;
    }
    return totalAbility;
  }
  
  /**
   * Get the initial number of strikes that the temporary effect can 
   * sustain. 
   * 
   * @return number of strikes with temporary effect
   */
  private int getInitialTemporaryEffectStrikes() {
    if (this.potionsUsed.size() == 0) {
      return 0;
    }
    Gears potion = this.potionsUsed.get(0);
    return ((Potions) potion).getTemporaryEffectUsage() + 1;
  }
  
  @Override
  public int getRemainingTemporaryEffectStrikes() {
    if (this.temporaryEffectStrikes <= 1) {
      this.temporaryStrength = 0;
      this.temporaryConstitution = 0;
      this.temporaryDexterity = 0;
      this.temporaryCharisma = 0;
      return 0;
    }
    this.temporaryEffectStrikes -= 1;
    return this.temporaryEffectStrikes;
  }

  @Override
  public int getInitialHealth() {
    return this.initialPlayerHealth;
  }

  @Override
  public int getCurrentHealth() {
    return this.currentPlayerHealth;
  }

  @Override
  public void applyDamage(int damage) {
    this.currentPlayerHealth -= damage;    
  }

  @Override
  public int getStrikingPower() throws IllegalStateException {
    if (this.equipments.size() == 0 || this.weapon.size() == 0) {
      throw new IllegalStateException("Player must be equipped with gears "
          + "and weapon before striking!");
    }
    int randomPower = this.random.getRandomNumber(1, 10);
    return this.strength + this.temporaryStrength + randomPower;
  }

  @Override
  public int getAvoidanceAbility() throws IllegalStateException {
    if (this.equipments.size() == 0 || this.weapon.size() == 0) {
      throw new IllegalStateException("Player must be equipped with gears "
          + "and weapon before avoiding!");
    }
    int randomPower = this.random.getRandomNumber(1, 6);
    return this.dexterity + this.temporaryDexterity + randomPower;
  }
    
  @Override
  public int getStrength() {
    return this.strength + this.temporaryStrength;
  }

  @Override
  public int getConstitution() {
    return this.constitution + this.temporaryConstitution;
  }

  @Override
  public int getDexterity() {
    return this.dexterity + this.temporaryDexterity;
  }

  @Override
  public int getCharisma() {
    return this.charisma + this.temporaryCharisma;
  }

  @Override
  public void rematchSetup() {
    this.refreshPlayer();
    this.currentPlayerHealth = this.initialPlayerHealth;
    this.strength = this.initialStrength;
    this.constitution = this.initialConstitution;
    this.dexterity = this.initialDexterity;
    this.charisma = this.initialCharisma;
  }

  @Override
  public List<Weapons> getWeaponsUsed() {
    return this.weapon;
  }

  
  @Override
  public List<String> getWeaponIdUsed() {
    List<String> weapons = new ArrayList<>();
    
    for (Weapons w : this.weapon) {
      weapons.add(w.getWeaponId());
    }
    return weapons;
  }
  
  @Override
  public List<String> getSortedGears() {
    List<String> sortedHeadGears = new ArrayList<>();
    
    for (Gears hg : this.headGearUsed) {
      sortedHeadGears.add(hg.getGearId());
    }
    Collections.sort(sortedHeadGears);
    
    List<String> sortedPotions = new ArrayList<>();
    
    for (Gears pot : this.potionsUsed) {
      sortedPotions.add(pot.getGearId());
    }
    Collections.sort(sortedPotions);
    
    List<String> sortedBelts = new ArrayList<>();
    
    for (Gears belt : this.beltsUsed) {
      sortedBelts.add(belt.getGearId());
    }
    Collections.sort(sortedBelts);
    
    List<String> sortedFootwears = new ArrayList<>();
    
    for (Gears fw : this.footWearUsed) {
      sortedPotions.add(fw.getGearId());
    }
    Collections.sort(sortedFootwears);
    
    List<String> sortedGears = new ArrayList<>();
    sortedGears.addAll(sortedHeadGears);
    sortedGears.addAll(sortedPotions);
    sortedGears.addAll(sortedBelts);
    sortedGears.addAll(sortedFootwears);
    
    return sortedGears;
  }

  @Override
  public List<Gears> getUsedBelts() {
    return this.beltsUsed;
  }
  
  /**
   * Setting up place for a new set of equipments/gears and weapons.
   */
  private void refreshPlayer() {
    this.temporaryStrength = 0;
    this.temporaryConstitution = 0;
    this.temporaryDexterity = 0;
    this.temporaryCharisma = 0;
    this.temporaryEffectStrikes = 0;
    this.headgearUnits = 1;
    this.footwearUnits = 1;
    this.beltUnits = 10;
    this.equipments = new ArrayList<>();
    this.headGearUsed = new ArrayList<>();
    this.footWearUsed = new ArrayList<>();
    this.beltsUsed = new ArrayList<>();
    this.potionsUsed = new ArrayList<>();
    this.weapon = new ArrayList<>();
  }
}
