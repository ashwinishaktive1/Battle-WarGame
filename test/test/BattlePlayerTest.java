package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import battle.Armory;
import battle.BattlePlayer;
import battle.Belts;
import battle.EquipmentBag;
import battle.Gears;
import battle.Players;
import battle.RandomNumberGenerator;
import battle.RandomNumberGeneratorDev;
import battle.RandomNumberGeneratorTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit test case for the BattlePlayer class. 
 * 
 * @author Ashwini Shaktivel Kumar
 *
 */
public class BattlePlayerTest {
  
  Players gamer1;
  Players gamer2;
  EquipmentBag eqBag;
  Armory arms;
  RandomNumberGenerator rand;
  
  /**
   * Setting up players, equipment bag and armory for the battle.
   */
  @Before
  public void setup() {
    rand = new RandomNumberGeneratorTest(2);
    eqBag = new EquipmentBag(rand);
    arms = new Armory(rand);
    gamer1 = new BattlePlayer(1, rand);
    gamer2 = new BattlePlayer(2, rand);
  }
  
  /**
   * Testing if the BattlePlayer is constructed properly. A 0 index 
   * should not throw an error.
   */
  @Test
  public void testBattlePlayerConstructor() {
    Players test = new BattlePlayer(0, new RandomNumberGeneratorTest(2));
  }
  
  /**
   * Test if a null random number generator is assigned to the player.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testWithNullRandomNumberGenerator() {
    Players test = new BattlePlayer(0, null);
  }
  
  /**
   * Testing get player identifier.
   */
  @Test
  public void testGetPlayerId() {
    assertEquals("Player 1", gamer1.getPlayerId());
    assertEquals("Player 2", gamer2.getPlayerId());
  }
  
  /**
   * Test if the basic abilities are always assigned within the 
   * range of 8 and 24.
   */
  @Test
  public void testPlayerAbilities() {
    rand = new RandomNumberGeneratorDev();
    Players test = new BattlePlayer(1, rand);
    
    for (int i = 0; i < 5000; i++) {
      assertTrue(test.getStrength() >= 8 && test.getStrength() <= 24);
      assertTrue(test.getConstitution() >= 8 && test.getConstitution() <= 24);
      assertTrue(test.getDexterity() >= 8 && test.getDexterity() <= 24);
      assertTrue(test.getCharisma() >= 8 && test.getCharisma() <= 24);
    }
  }
  
  /**
   * Testing if the basic info is correct and remains unchanged 
   * after equipping gears and/or weapon.
   */
  @Test
  public void testPlayerBasicInfo() {
    String expected = "Player 1" + "\n" + "Player basic abilities for the entire game: \n" 
        + "Strength - " + 8 + "\n"
        + "Constitution - " + 8 + "\n"
        + "Dexterity - " + 8 + "\n"
        + "Charisma - " + 8;
    assertEquals(expected, gamer1.getPlayerBasicInfo());
    
    gamer1.equipGears(eqBag);
    gamer1.requestWeapon(arms);
    
    expected = "Player 1" + "\n" + "Player basic abilities for the entire game: \n" 
        + "Strength - " + 8 + "\n"
        + "Constitution - " + 8 + "\n"
        + "Dexterity - " + 8 + "\n"
        + "Charisma - " + 8;
    assertEquals(expected, gamer1.getPlayerBasicInfo());
  }
  
  /**
   * Testing player description for a newly created player.
   */
  @Test
  public void testNewGetPlayerDescription() {
    String expected = "Player 1" + "\n" + "Player abilities for the entire game: \n" 
        + "Strength - " + 8 + "\n"
        + "Constitution - " + 8 + "\n"
        + "Dexterity - " + 8 + "\n"
        + "Charisma - " + 8 + "\n"
        + "\n" 
        + "The player may temporarily take advangtage of the following "
        + "abilities gained through the effect of potion! \n"
        + "Strength - " + 0 + "\n"
        + "Constitution - " + 0 + "\n"
        + "Dexterity - " + 0 + "\n"
        + "Charisma - " + 0 + "\n"
        + "The temporary effect remains for " + 0 + " strikes. \n"
        + "Player is equipped with following gears: \n" + "[]"
        + "\n" 
        + "Player is using the following weapon: \n" + "[]"
        + "\n" 
        + "Player needs to equip gears and weapons to start the match!";
    assertEquals(expected, gamer1.getPlayerDescription());
  }
  
  /**
   * Testing if the player description validates the updated abilities 
   * after equipping gears and weapons.
   */
  @Test
  public void testEquippedGetPlayerDescription() {
    gamer1.equipGears(eqBag);
    gamer1.requestWeapon(arms);
    String expected = "Player 1" + "\n" + "Player abilities for the entire game: \n" 
        + "Strength - " + 8 + "\n"
        + "Constitution - " + 7 + "\n"
        + "Dexterity - " + 13 + "\n"
        + "Charisma - " + 4 + "\n"
        + "\n" 
        + "The player may temporarily take advangtage of the following "
        + "abilities gained through the effect of potion! \n"
        + "Strength - " + 9 + "\n"
        + "Constitution - " + -9 + "\n"
        + "Dexterity - " + 9 + "\n"
        + "Charisma - " + 0 + "\n"
        + "The temporary effect remains for " + 2 + " strikes. \n"
        + "Player is equipped with following gears: \n" 
        + "[Headgear 1, Potion 1, Potion 11, Potion 13, Potion 2, Potion 4, "
        + "Potion 6, Potion 7, Potion 8, Potion 9, Footwear 2]"
        + "\n" 
        + "Player is using the following weapon: \n" + "[Axe 3]"
        + "\n" 
        + "Wohoo!! You look ready, let's start the game!";
    assertEquals(expected, gamer1.getPlayerDescription());
    
    gamer2.equipGears(eqBag);
    gamer2.requestWeapon(arms);
    expected = "Player 2" + "\n" + "Player abilities for the entire game: \n" 
        + "Strength - " + 8 + "\n"
        + "Constitution - " + 16 + "\n"
        + "Dexterity - " + 5 + "\n"
        + "Charisma - " + 4 + "\n"
        + "\n" 
        + "The player may temporarily take advangtage of the following "
        + "abilities gained through the effect of potion! \n"
        + "Strength - " + 6 + "\n"
        + "Constitution - " + -6 + "\n"
        + "Dexterity - " + 6 + "\n"
        + "Charisma - " + 0 + "\n"
        + "The temporary effect remains for " + 2 + " strikes. \n"
        + "Player is equipped with following gears: \n" 
        + "[Headgear 5, Potion 10, Potion 12, Potion 15, Potion 16, Potion 17, "
        + "Potion 3, Footwear 1, Belt 1, Belt 10, Belt 3, Belt 4, Belt 5, Belt 7]"
        + "\n" 
        + "Player is using the following weapon: \n" + "[Flail 1]"
        + "\n" 
        + "Wohoo!! You look ready, let's start the game!";
    assertEquals(expected, gamer2.getPlayerDescription());
  }
  
  /**
   * Testing if the player description validates the updated abilities 
   * after equipping gears.
   */
  @Test
  public void testGetPlayerDescriptionWithGears() {
    gamer1.equipGears(eqBag);
    String expected = "Player 1" + "\n" + "Player abilities for the entire game: \n" 
        + "Strength - " + 8 + "\n"
        + "Constitution - " + 7 + "\n"
        + "Dexterity - " + 13 + "\n"
        + "Charisma - " + 4 + "\n"
        + "\n" 
        + "The player may temporarily take advangtage of the following "
        + "abilities gained through the effect of potion! \n"
        + "Strength - " + 9 + "\n"
        + "Constitution - " + -9 + "\n"
        + "Dexterity - " + 9 + "\n"
        + "Charisma - " + 0 + "\n"
        + "The temporary effect remains for " + 2 + " strikes. \n"
        + "Player is equipped with following gears: \n" 
        + "[Headgear 1, Potion 1, Potion 11, Potion 13, Potion 2, Potion 4, "
        + "Potion 6, Potion 7, Potion 8, Potion 9, Footwear 2]"
        + "\n" 
        + "Player is using the following weapon: \n" + "[]"
        + "\n" 
        + "Player needs to request a weapon!";
    assertEquals(expected, gamer1.getPlayerDescription());
  }
  
  /**
   * Testing if the player description validates the updated abilities 
   * after equipping weapons.
   */
  @Test
  public void testGetPlayerDescriptionWithWeapon() {
    gamer1.requestWeapon(arms);
    String expected = "Player 1" + "\n" + "Player abilities for the entire game: \n" 
        + "Strength - " + 8 + "\n"
        + "Constitution - " + 8 + "\n"
        + "Dexterity - " + 8 + "\n"
        + "Charisma - " + 8 + "\n"
        + "\n" 
        + "The player may temporarily take advangtage of the following "
        + "abilities gained through the effect of potion! \n"
        + "Strength - " + 0 + "\n"
        + "Constitution - " + 0 + "\n"
        + "Dexterity - " + 0 + "\n"
        + "Charisma - " + 0 + "\n"
        + "The temporary effect remains for " + 0 + " strikes. \n"
        + "Player is equipped with following gears: \n" 
        + "[]"
        + "\n" + "Player needs to equip gears! \n"
        + "Player is using the following weapon: \n" + "[Axe 3]" + "\n";
    assertEquals(expected, gamer1.getPlayerDescription());
  }
  
  /**
   * Testing request weapon, and current weapon used by the player.
   */
  @Test
  public void testRequestWeapon() {
    gamer1.requestWeapon(arms);
    List<String> expected = new ArrayList<>();
    expected.add("Axe 3");
    
    assertEquals(expected, gamer1.getWeaponIdUsed());
  }
  
  /**
   * Testing if null armory can be used to let players 
   * get their weapon.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testRequestWeaponNullArmory() {
    gamer1.requestWeapon(null);
  }
  
  /**
   * Testing requesting weapon after already requesting weapon once.
   */
  @Test (expected = IllegalStateException.class)
  public void testRequestWeaponAgain() {
    gamer1.requestWeapon(arms);
    gamer1.requestWeapon(arms);
  }
  
  /**
   * Testing equipping gears, and the current gears used by 
   * the player.
   */
  @Test
  public void testEquipGears() {
    gamer1.equipGears(eqBag);
    List<String> expected = new ArrayList<>();
    expected.add("Headgear 1");
    expected.add("Potion 1");
    expected.add("Potion 11");
    expected.add("Potion 13");
    expected.add("Potion 2");
    expected.add("Potion 4");
    expected.add("Potion 6");
    expected.add("Potion 7");
    expected.add("Potion 8");
    expected.add("Potion 9");
    expected.add("Footwear 2");
    
    assertEquals(expected, gamer1.getSortedGears());
    
    expected.removeAll(expected);
    gamer2.equipGears(eqBag);
    
    expected.add("Headgear 5");
    expected.add("Potion 10");
    expected.add("Potion 12");
    expected.add("Potion 15");
    expected.add("Potion 16");
    expected.add("Potion 17");
    expected.add("Potion 3");
    expected.add("Footwear 1");
    expected.add("Belt 1");
    expected.add("Belt 10");
    expected.add("Belt 3");
    expected.add("Belt 4");
    expected.add("Belt 5");
    expected.add("Belt 7");
    
    assertEquals(expected, gamer2.getSortedGears());
  }
  
  /**
   * Testing if null equipment bag can be used to let players 
   * get their gears.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testEquipGearsNullEquipmentBag() {
    gamer1.equipGears(null);
  }
  
  /**
   * Testing equipping gears after already equipping gears once.
   */
  @Test (expected = IllegalStateException.class)
  public void testEquipGearsAgain() {
    gamer1.equipGears(eqBag);
    gamer1.equipGears(eqBag);
  }
    
  /**
   * Testing if player equips only one headgear from the received 
   * headgears from the equipment bag. 
   */
  @Test
  public void testEquipMoreThanOneHeadGear() {
    RandomNumberGenerator r = new RandomNumberGeneratorTest(2, 1, 0, 3, 5, 6, 
        8, 9, 10, 11, 13, 14, 15, 17, 19, 20, 21, 22, 24, 26); 
    EquipmentBag test = new EquipmentBag(r);
    List<Gears> expected = test.getGears();
    
    // number of headgears
    int numberOfHeadgears = 0;
    for (Gears g : expected) {
      if (g.getGearId().charAt(0) == 'H') {
        numberOfHeadgears++;
      }
    }
    gamer1.equipGears(test);
    
    // used headgears
    int usedHeadgear = 0;
    for (String g : gamer1.getSortedGears()) {
      if (g.charAt(0) == 'H') {
        usedHeadgear++;
      }
    }
    assertTrue(numberOfHeadgears >= usedHeadgear);
    assertEquals(1, usedHeadgear);
  }
  
  /**
   * Testing if player equips only one footwear from the received 
   * footwears from the equipment bag. 
   */
  @Test
  public void testEquipMoreThanOneFootWear() {
    RandomNumberGenerator r = new RandomNumberGeneratorTest(2, 1, 0, 3, 5, 6, 
        8, 9, 10, 11, 13, 14, 15, 17, 19, 20, 21, 22, 24, 26); 
    EquipmentBag test = new EquipmentBag(r);
    List<Gears> expected = test.getGears();
    
    // number of footwears
    int numberOfFootwears = 0;
    for (Gears g : expected) {
      if (g.getGearId().charAt(0) == 'F') {
        numberOfFootwears++;
      }
    }
    gamer1.equipGears(test);
    
    // used footwears
    int usedFootwear = 0;
    for (String g : gamer1.getSortedGears()) {
      if (g.charAt(0) == 'F') {
        usedFootwear++;
      }
    }
    assertTrue(numberOfFootwears >= usedFootwear);
    assertEquals(1, usedFootwear);
  }
  
  /**
   * Testing if player equips only allowed belts from the received 
   * belts from the equipment bag. 
   */
  @Test
  public void testEquipMoreThanAllowedBelts() {
    RandomNumberGenerator r = new RandomNumberGeneratorTest(2, 1, 0, 3, 5, 6, 
        8, 9, 10, 11, 13, 14, 15, 17, 19, 20, 21, 22, 24, 26);
    EquipmentBag test = new EquipmentBag(r);
    // With this set of random numbers, the first player 
    // does not receive any belt. So I am skipping that set
    // and instead checking the second set of gears received.
    test.getGears();
    List<Gears> expected = test.getGears();
    
    // number of belts
    int numberOfBelts = 0;
    for (Gears g : expected) {
      if (g.getGearId().charAt(0) == 'B') {
        numberOfBelts++;
      }
    }
    
    EquipmentBag eq = new EquipmentBag(r);
    gamer2.equipGears(eq);
    // second set (same as above)
    gamer1.equipGears(eq);
    // used footwears
    int usedBelts = gamer1.getUsedBelts().size();
    int units = 0;
    for (Gears g : gamer1.getUsedBelts()) {
      units += ((BattlePlayer) gamer1).getBeltUnitsRequirement(((Belts) g).getBeltSize());
    }
    assertTrue(numberOfBelts >= usedBelts);
    assertTrue(units <= 10);
    assertEquals(10, units);
  }
  
  /**
   * Testing if correct strength is returned before equipping 
   * gears.
   */
  @Test
  public void testGetStrengthInitial() {
    assertEquals(8, gamer1.getStrength());
    Players gamer = new BattlePlayer(1, new RandomNumberGeneratorTest(3));
    assertEquals(12, gamer.getStrength());
  }
  
  /**
   * Testing if correct constitution is returned before equipping 
   * gears.
   */
  @Test
  public void testGetConstitutionInitial() {
    assertEquals(8, gamer1.getConstitution());
    Players gamer = new BattlePlayer(1, new RandomNumberGeneratorTest(3));
    assertEquals(12, gamer.getConstitution());
  }
  
  /**
   * Testing if correct dexterity is returned before equipping 
   * gears.
   */
  @Test
  public void testGetDexterityInitial() {
    assertEquals(8, gamer1.getDexterity());
    Players gamer = new BattlePlayer(1, new RandomNumberGeneratorTest(3));
    assertEquals(12, gamer.getDexterity());
  }
  
  /**
   * Testing if correct charisma is returned before equipping 
   * gears.
   */
  @Test
  public void testGetCharismaInitial() {
    assertEquals(8, gamer1.getCharisma());
    Players gamer = new BattlePlayer(1, new RandomNumberGeneratorTest(3));
    assertEquals(12, gamer.getCharisma());
  }
  
  /**
   * Testing the initial health of the player.
   */
  @Test
  public void testGetInitialHealth() {
    assertEquals(32, gamer1.getInitialHealth());
    Players gamer = new BattlePlayer(1, new RandomNumberGeneratorTest(3));
    assertEquals(48, gamer.getInitialHealth());
  }
  
  /**
   * Test strength after temporary effect.
   */
  @Test
  public void testGetTemporaryStrength() {
    gamer1.equipGears(eqBag);
    assertEquals(8 + 9, gamer1.getStrength());
  }
  
  /**
   * Test constitution after temporary effect.
   */
  @Test
  public void testGetTemporaryConstitution() {
    gamer1.equipGears(eqBag);
    assertEquals(7 - 9, gamer1.getConstitution());
  }
  
  /**
   * Test dexterity after temporary effect.
   */
  @Test
  public void testGetTemporaryDexterity() {
    gamer1.equipGears(eqBag);
    assertEquals(13 + 9, gamer1.getDexterity());
  }
  
  /**
   * Test charisma after temporary effect.
   */
  @Test
  public void testGetTemporaryCharisma() {
    gamer1.equipGears(eqBag);
    assertEquals(4 + 0, gamer1.getCharisma());
  }
  
  /**
   * Test getting current health after a damage is applied.
   */
  @Test
  public void testGetCurrentHealth() {
    assertEquals(32, gamer1.getCurrentHealth());
    gamer1.applyDamage(2);
    assertEquals(30, gamer1.getCurrentHealth());
  }
  
  /**
   * Testing the number of strikes the temporary gears have additional 
   * effect.
   */
  @Test
  public void testGetRemainingTemporaryEffectStrikes() {
    gamer1.equipGears(eqBag);
    // when game starts with potions used
    assertEquals(2, gamer1.getRemainingTemporaryEffectStrikes());
    
    // after one strike done
    assertEquals(1, gamer1.getRemainingTemporaryEffectStrikes());
    
    // after another strike done
    assertEquals(0, gamer1.getRemainingTemporaryEffectStrikes());
    
    // after no more strikes remaining
    assertEquals(0, gamer1.getRemainingTemporaryEffectStrikes());
  }
  
  /**
   * Testing the temporary effect and corresponding change in strength.
   */
  @Test
  public void testTemporaryEffectAndFinalAbilities() {
    gamer1.equipGears(eqBag);
    assertEquals(8 + 9, gamer1.getStrength());
    assertEquals(7 - 9, gamer1.getConstitution());
    assertEquals(13 + 9, gamer1.getDexterity());
    assertEquals(4 + 0, gamer1.getCharisma());
    gamer1.getRemainingTemporaryEffectStrikes();
    assertEquals(8 + 9, gamer1.getStrength());
    assertEquals(7 - 9, gamer1.getConstitution());
    assertEquals(13 + 9, gamer1.getDexterity());
    assertEquals(4 + 0, gamer1.getCharisma());
    gamer1.getRemainingTemporaryEffectStrikes();
    assertEquals(8 + 9, gamer1.getStrength());
    assertEquals(7 - 9, gamer1.getConstitution());
    assertEquals(13 + 9, gamer1.getDexterity());
    assertEquals(4 + 0, gamer1.getCharisma());
    gamer1.getRemainingTemporaryEffectStrikes();
    assertEquals(8 + 0, gamer1.getStrength());
    assertEquals(7 - 0, gamer1.getConstitution());
    assertEquals(13 + 0, gamer1.getDexterity());
    assertEquals(4 + 0, gamer1.getCharisma());
  }
  
  /**
   * Test getting striking power without equipping weapon.
   */
  @Test (expected = IllegalStateException.class)
  public void testGetStrikingPowerWithoutGearsOrWeapon() {
    gamer1.equipGears(eqBag);
    assertEquals(8 + 6 + 2, gamer1.getStrikingPower());
  }
  
  /**
   * Test getting striking power with and without temporary effect.
   */
  @Test
  public void testGetStrikingPower() {
    gamer1.equipGears(eqBag);
    gamer1.requestWeapon(arms);
    assertEquals(8 + 9 + 2, gamer1.getStrikingPower());
    gamer1.getRemainingTemporaryEffectStrikes();
    gamer1.getRemainingTemporaryEffectStrikes();
    gamer1.getRemainingTemporaryEffectStrikes();
    assertEquals(8 + 0 + 2, gamer1.getStrikingPower());
  }
  
  /**
   * Test getting avoidance ability without equipping gears.
   */
  @Test (expected = IllegalStateException.class)
  public void testGetAvoidanceAbilityWithoutGearsOrWeapon() {
    gamer1.requestWeapon(arms);
    assertEquals(4 + 6 + 2, gamer1.getAvoidanceAbility());
  }
  
  /**
   * Test getting avoidance ability with and without temporary effect.
   */
  @Test
  public void testGetAvoidanceAbility() {
    gamer1.equipGears(eqBag);
    gamer1.requestWeapon(arms);
    assertEquals(13 + 9 + 2, gamer1.getAvoidanceAbility());
    gamer1.getRemainingTemporaryEffectStrikes();
    gamer1.getRemainingTemporaryEffectStrikes();
    gamer1.getRemainingTemporaryEffectStrikes();
    assertEquals(13 + 0 + 2, gamer1.getAvoidanceAbility());
  }
  
  /**
   * Testing when damage is more than the player current health.
   */
  @Test
  public void testApplyDamageNearZero() {
    assertEquals(32, gamer1.getCurrentHealth());
    gamer1.applyDamage(34);
    assertEquals(-2, gamer1.getCurrentHealth());
  }
  
  /**
   * Testing if the abilities are reset to original abilities 
   * equal to the randomly assigned abilities.
   */
  @Test
  public void testRematchAbilities() {
    gamer1.equipGears(eqBag);
    gamer1.requestWeapon(arms);
    gamer1.rematchSetup();
    String expected = "Player 1" + "\n" + "Player abilities for the entire game: \n" 
        + "Strength - " + 8 + "\n"
        + "Constitution - " + 8 + "\n"
        + "Dexterity - " + 8 + "\n"
        + "Charisma - " + 8 + "\n"
        + "\n" 
        + "The player may temporarily take advangtage of the following "
        + "abilities gained through the effect of potion! \n"
        + "Strength - " + 0 + "\n"
        + "Constitution - " + 0 + "\n"
        + "Dexterity - " + 0 + "\n"
        + "Charisma - " + 0 + "\n"
        + "The temporary effect remains for " + 0 + " strikes. \n"
        + "Player is equipped with following gears: \n" + "[]"
        + "\n" 
        + "Player is using the following weapon: \n" + "[]"
        + "\n" 
        + "Player needs to equip gears and weapons to start the match!";
    assertEquals(expected, gamer1.getPlayerDescription());

  }
  
  /**
   * Testing if after asking for a rematch, the player is allowed to 
   * equip new gears.
   */
  @Test
  public void testRematchGetNewEquipments() {
    gamer1.equipGears(eqBag);
    gamer1.rematchSetup();
    gamer1.equipGears(eqBag);
  }
  
  /**
   * Testing if after asking for a rematch, the player is allowed to 
   * request new weapon.
   */
  @Test
  public void testRematchGetNewWeapon() {
    gamer1.requestWeapon(arms);
    gamer1.rematchSetup();
    gamer1.requestWeapon(arms);
  }
}
