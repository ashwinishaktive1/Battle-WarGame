package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import battle.Axes;
import battle.BattlePlayer;
import battle.BroadSwords;
import battle.EquipmentBag;
import battle.Flails;
import battle.Katanas;
import battle.Players;
import battle.RandomNumberGenerator;
import battle.RandomNumberGeneratorTest;
import battle.TwoHandedSwords;
import battle.Weapons;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit test case for the Weapons interface and component 
 * implementing sub-classes.
 * 
 * @author Ashwini Shaktivel Kumar
 *
 */
public class WeaponsTest {
  RandomNumberGenerator rand;
  Weapons axe;
  Weapons bs;
  Weapons katana;
  Weapons flail;
  Weapons ths;
  Players gamer2;
  
  /**
   * Setting up weapons.
   */
  @Before
  public void setup() {
    rand = new RandomNumberGeneratorTest(1);
    axe = new Axes(2, rand);
    bs = new BroadSwords(2, rand);
    katana = new Katanas(2, rand);
    flail = new Flails(2, rand);
    ths = new TwoHandedSwords(2, rand);
  }
  
  /**
   * Test if the axe is created correctly with assigned id.
   */
  @Test
  public void testAxeCreation() {
    Weapons test = new Axes(2, rand);
    assertEquals("Axe 2", test.getWeaponId());
  }
  
  /**
   * Test if a newly created axe is not yet added to armory 
   * and is not assigned to a .
   */
  @Test
  public void testAxeStatus() {
    assertFalse(axe.getIfWeaponAddedtoArmory());
    assertFalse(axe.getIfWeaponUsedByPlayer());
  }
  
  /**
   * Test if the axe status can be updated once the weapon is added to 
   * the armory or assigned to a .
   */
  @Test
  public void testAxeUpdateStatus() {
    axe.setIfWeaponAddedtoArmory(true);
    assertTrue(axe.getIfWeaponAddedtoArmory());
    axe.setIfWeaponUsedByPlayer(true);
    assertTrue(axe.getIfWeaponUsedByPlayer());
  }
  
  /**
   * Test if the axe can be removed from armory.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testAxeRemoveFromArmory() {
    axe.setIfWeaponAddedtoArmory(false);
  }
  
  /**
   * Test if the axe can be removed from a  assignment.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testAxeRemoveFromPlayerAssignment() {
    axe.setIfWeaponUsedByPlayer(false);
  }
  
  /**
   * Check if the randomly generated points are one amongst the 
   * possible values for a medium sized weapon.
   */
  @Test
  public void testAxeGetStrikePower() {
    rand = new RandomNumberGeneratorTest(2);
    Integer[] possiblePoints = {6, 7, 8, 9, 10};
    
    Players p = new BattlePlayer(1, rand);
    assertTrue(Arrays.asList(possiblePoints).contains(axe.getStrikePower(p)));
  }
  
  /**
   * Test if the broad sword is created correctly with assigned id.
   */
  @Test
  public void testBroadSwordsCreation() {
    Weapons test = new BroadSwords(2, rand);
    assertEquals("Broad Sword 2", test.getWeaponId());
  }
  
  /**
   * Test if a newly created broad sword is not yet added to armory 
   * and is not assigned to a .
   */
  @Test
  public void testBroadSwordStatus() {
    assertFalse(bs.getIfWeaponAddedtoArmory());
    assertFalse(bs.getIfWeaponUsedByPlayer());
  }
  
  /**
   * Test if the broad sword status can be updated once the weapon is added to 
   * the armory or assigned to a .
   */
  @Test
  public void testBroadSwordsUpdateStatus() {
    bs.setIfWeaponAddedtoArmory(true);
    assertTrue(bs.getIfWeaponAddedtoArmory());
    bs.setIfWeaponUsedByPlayer(true);
    assertTrue(bs.getIfWeaponUsedByPlayer());
  }
  
  /**
   * Test if the broad sword can be removed from armory.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testBroadSwordsRemoveFromArmory() {
    bs.setIfWeaponAddedtoArmory(false);
  }
  
  /**
   * Test if the broad sword can be removed from a  assignment.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testBroadSwordsRemoveFromPlayerAssignment() {
    bs.setIfWeaponUsedByPlayer(false);
  }
  
  /**
   * Check if the randomly generated points are one amongst the 
   * possible values for a medium sized weapon.
   */
  @Test
  public void testBroadSwordGetStrikePower() {
    rand = new RandomNumberGeneratorTest(2);
    Integer[] possiblePoints = {6, 7, 8, 9, 10};
    
    Players p = new BattlePlayer(2, rand);
    assertTrue(Arrays.asList(possiblePoints).contains(bs.getStrikePower(p)));
  }
  
  /**
   * Test if the katana is created correctly with assigned id.
   */
  @Test
  public void testKatanaCreation() {
    Weapons test = new Katanas(2, rand);
    assertEquals("Katana 2", test.getWeaponId());
  }
  
  /**
   * Test if a newly created katana is not yet added to armory 
   * and is not assigned to a .
   */
  @Test
  public void testKatanaStatus() {
    assertFalse(katana.getIfWeaponAddedtoArmory());
    assertFalse(katana.getIfWeaponUsedByPlayer());
  }
  
  /**
   * Test if the katana status can be updated once the weapon is added to 
   * the armory or assigned to a .
   */
  @Test
  public void testKatanaUpdateStatus() {
    katana.setIfWeaponAddedtoArmory(true);
    assertTrue(katana.getIfWeaponAddedtoArmory());
    katana.setIfWeaponUsedByPlayer(true);
    assertTrue(katana.getIfWeaponUsedByPlayer());
  }
  
  /**
   * Test if the katana can be removed from armory.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testKatanaRemoveFromArmory() {
    katana.setIfWeaponAddedtoArmory(false);
  }
  
  /**
   * Test if the katana can be removed from a  assignment.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testKatanaRemoveFromPlayerAssignment() {
    katana.setIfWeaponUsedByPlayer(false);
  }
  
  /**
   * Check if the randomly generated points are one amongst the 
   * possible values for a light weight weapon.
   */
  @Test
  public void testKatanaGetStrikePower() {
    rand = new RandomNumberGeneratorTest(2);
    Integer[] possiblePoints = {4, 5, 6};
    
    Players p = new BattlePlayer(3, rand);
    assertTrue(Arrays.asList(possiblePoints).contains(katana.getStrikePower(p)));
  }
  
  /**
   * Test if the flail is created correctly with assigned id.
   */
  @Test
  public void testFlailCreation() {
    Weapons test = new Flails(2, rand);
    assertEquals("Flail 2", test.getWeaponId());
  }
  
  /**
   * Test if a newly created flail is not yet added to armory 
   * and is not assigned to a .
   */
  @Test
  public void testFlailStatus() {
    assertFalse(flail.getIfWeaponAddedtoArmory());
    assertFalse(flail.getIfWeaponUsedByPlayer());
  }
  
  /**
   * Test if the flail status can be updated once the weapon is added to 
   * the armory or assigned to a .
   */
  @Test
  public void testFlailUpdateStatus() {
    flail.setIfWeaponAddedtoArmory(true);
    assertTrue(flail.getIfWeaponAddedtoArmory());
    flail.setIfWeaponUsedByPlayer(true);
    assertTrue(flail.getIfWeaponUsedByPlayer());
  }
  
  /**
   * Test if the flail can be removed from armory.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testFlailRemoveFromArmory() {
    flail.setIfWeaponAddedtoArmory(false);
  }
  
  /**
   * Test if the flail can be removed from a  assignment.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testFlailRemoveFromPlayerAssignment() {
    flail.setIfWeaponUsedByPlayer(false);
  }
  
  /**
   * Testing if the strike power of a flail is adjusted 
   * according to the dexterity of the player.
   */
  @Test
  public void testFlailStrikePower() {
    rand = new RandomNumberGeneratorTest(2);
    Players p1 = new BattlePlayer(1, rand);
    
    // without gears
    Integer[] pointsBeforeGear = {4, 5, 6};
    
    assertTrue(Arrays.asList(pointsBeforeGear)
                     .contains(flail.getStrikePower(p1)));
    assertEquals(8, p1.getDexterity());
    
    // with gears and temporary effect of potions
    p1.equipGears(new EquipmentBag(rand));
    Integer[] pointsAfterGear = {8, 9, 10, 11, 12};
    
    assertTrue(Arrays.asList(pointsAfterGear)
                     .contains(flail.getStrikePower(p1)));
    assertEquals(22, p1.getDexterity());
    
    // without temporary effect of potions
    p1.getRemainingTemporaryEffectStrikes();
    p1.getRemainingTemporaryEffectStrikes();
    p1.getRemainingTemporaryEffectStrikes();
    Integer[] pointsAfterTemporaryEffect = {4, 5, 6};
    
    assertTrue(Arrays.asList(pointsAfterTemporaryEffect)
                     .contains(flail.getStrikePower(p1)));
    assertEquals(13, p1.getDexterity());
  }
  
  /**
   * Test if the two-handed sword is created correctly with assigned id.
   */
  @Test
  public void testTwoHandedSwordCreation() {
    Weapons test = new TwoHandedSwords(2, rand);
    assertEquals("Two-handed Sword 2", test.getWeaponId());
  }
  
  /**
   * Test if a newly created two-handed sword is not yet added to armory 
   * and is not assigned to a .
   */
  @Test
  public void testTwoHandedSwordStatus() {
    assertFalse(ths.getIfWeaponAddedtoArmory());
    assertFalse(ths.getIfWeaponUsedByPlayer());
  }
  
  /**
   * Test if the two-handed sword status can be updated once the weapon is added to 
   * the armory or assigned to a .
   */
  @Test
  public void testTwoHandedSwordUpdateStatus() {
    ths.setIfWeaponAddedtoArmory(true);
    assertTrue(ths.getIfWeaponAddedtoArmory());
    ths.setIfWeaponUsedByPlayer(true);
    assertTrue(ths.getIfWeaponUsedByPlayer());
  }
  
  /**
   * Test if the two-handed sword can be removed from armory.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testTwoHandedSwordRemoveFromArmory() {
    ths.setIfWeaponAddedtoArmory(false);
  }
  
  /**
   * Test if the two-handed sword can be removed from a  assignment.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testTwoHandedSwordRemoveFromPlayerAssignment() {
    ths.setIfWeaponUsedByPlayer(false);
  }
  
  /**
   * Testing if the strike power of a two-handed sword is adjusted 
   * according to the strength of the player.
   */
  @Test
  public void testTwoHandedSwordStrikePower() {
    rand = new RandomNumberGeneratorTest(2);
    Players p1 = new BattlePlayer(1, rand);
    
    // without gears
    Integer[] pointsBeforeGear = {4, 5, 6};
    
    assertTrue(Arrays.asList(pointsBeforeGear)
                     .contains(ths.getStrikePower(p1)));
    assertEquals(8, p1.getStrength());
    
    // with gears and temporary effect
    p1.equipGears(new EquipmentBag(rand));
    Integer[] pointsAfterGear = {8, 9, 10, 11, 12};
    
    assertTrue(Arrays.asList(pointsAfterGear)
                     .contains(ths.getStrikePower(p1)));
    assertEquals(17, p1.getStrength());
    
    // without temporary effect
    p1.getRemainingTemporaryEffectStrikes();
    p1.getRemainingTemporaryEffectStrikes();
    p1.getRemainingTemporaryEffectStrikes();
    Integer[] pointsAfterTemporaryEffect = {4, 5, 6};
    
    assertTrue(Arrays.asList(pointsAfterTemporaryEffect)
                     .contains(ths.getStrikePower(p1)));
    assertEquals(8, p1.getStrength());
  }
}
