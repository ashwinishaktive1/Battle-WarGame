package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import battle.BeltSizes;
import battle.Belts;
import battle.Footwear;
import battle.Gears;
import battle.Headgear;
import battle.Potions;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit test case for the Gears interface and component 
 * implementing sub-classes.
 * 
 * @author Ashwini Shaktivel Kumar
 *
 */
public class GearsTest {
  
  private static final BeltSizes MEDIUM = null;
  private Gears hg;
  private Gears pot;
  private Gears fw;
  private Gears belt;
  
  /**
   * Setting up gears.
   */
  @Before
  public void setup() {
    hg = new Headgear(2);
    pot = new Potions(2);
    fw = new Footwear(2);
    belt = new Belts(2, BeltSizes.SMALL);
  }
  
  /**
   * Test if the headgear is created correctly with assigned id.
   */
  @Test
  public void testHeadgearCreation() {
    assertEquals("Headgear 2", hg.getGearId());
  }
  
  /**
   * Test if a newly created headgear is not yet added to equipment 
   * bag and is assigned to a player.
   */
  @Test
  public void testHeadGearStatus() {
    assertFalse(hg.getIfGearAddedtoEquipmentBag());
    assertFalse(hg.getIfGearUsedByPlayer());
  }
  
  /**
   * Test if the headgear status can be updated once the gear is added to 
   * the equipment bag or assigned to a player.
   */
  @Test
  public void testHeadGearUpdateStatus() {
    hg.setIfGearAddedtoEquipmentBag(true);
    assertTrue(hg.getIfGearAddedtoEquipmentBag());
    hg.setIfGearUsedByPlayer(true);
    assertTrue(hg.getIfGearUsedByPlayer());
  }
  
  /**
   * Test if the headgear can be removed from equipment bag.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testHeadGearRemoveFromEquipmentBag() {
    hg.setIfGearAddedtoEquipmentBag(false);
  }
  
  /**
   * Test if the headgear can be removed from a player assignment.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testHeadGearRemoveFromPlayerAssignment() {
    hg.setIfGearUsedByPlayer(false);
  }
  
  /**
   * Test if once the headgear is used to update player abilities, can it be 
   * changed back.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testHeadGearPlayerUpdate() {
    hg.setIsPlayerAbilitiesUpdated(true);
    hg.setIsPlayerAbilitiesUpdated(false);
  }
  
  /**
   * Test the effect headgear has on a player.
   */
  @Test
  public void testHeadGearEffect() {
    Map<String, Integer> map = new HashMap();
    map.put("Strength", 0);
    map.put("Constitution",  2);
    map.put("Dexterity", 0);
    map.put("Charisma", -2);
    
    assertEquals(map, hg.checkEffectOfGear());
  }
  
  /**
   * Test if the potion is created correctly with assigned id.
   */
  @Test
  public void testPotionsCreation() {
    assertEquals("Potion 2", pot.getGearId());
  }
  
  /**
   * Test if a newly created potion is not yet added to equipment 
   * bag and is assigned to a player.
   */
  @Test
  public void testPotionStatus() {
    assertFalse(pot.getIfGearAddedtoEquipmentBag());
    assertFalse(pot.getIfGearUsedByPlayer());
  }
  
  /**
   * Test if the potion status can be updated once the gear is added to 
   * the equipment bag or assigned to a player.
   */
  @Test
  public void testPotionUpdateStatus() {
    pot.setIfGearAddedtoEquipmentBag(true);
    assertTrue(pot.getIfGearAddedtoEquipmentBag());
    pot.setIfGearUsedByPlayer(true);
    assertTrue(pot.getIfGearUsedByPlayer());
  }
  
  /**
   * Test if the potion can be removed from equipment bag.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testPotionRemoveFromEquipmentBag() {
    pot.setIfGearAddedtoEquipmentBag(false);
  }
  
  /**
   * Test if the potion can be removed from a player assignment.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testPotionRemoveFromPlayerAssignment() {
    pot.setIfGearUsedByPlayer(false);
  }
  
  /**
   * Test if once the potion is used to update player abilities, can it be 
   * changed back.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testPotionPlayerUpdate() {
    pot.setIsPlayerAbilitiesUpdated(true);
    pot.setIsPlayerAbilitiesUpdated(false);
  }
  
  /**
   * Test the effect potion has on a player.
   */
  @Test
  public void testPotionEffect() {
    Map<String, Integer> map = new HashMap();
    map.put("Strength", 1);
    map.put("Constitution",  -1);
    map.put("Dexterity", 1);
    map.put("Charisma", 0);
    
    assertEquals(map, pot.checkEffectOfGear());
  }
  
  /**
   * Test the number of strikes the potion has an effect on 
   * player abilities.
   */
  @Test
  public void testPotionTemporaryEffect() {
    assertEquals(2, ((Potions) pot).getTemporaryEffectUsage());
  }
  
  /**
   * Test if the footwear is created correctly with assigned id.
   */
  @Test
  public void testFootwearCreation() {
    assertEquals("Footwear 2", fw.getGearId());
  }
  
  /**
   * Test if a newly created footwear is not yet added to equipment 
   * bag and is assigned to a player.
   */
  @Test
  public void testFootwearStatus() {
    assertFalse(fw.getIfGearAddedtoEquipmentBag());
    assertFalse(fw.getIfGearUsedByPlayer());
  }
  
  /**
   * Test if the footwear status can be updated once the gear is added to 
   * the equipment bag or assigned to a player.
   */
  @Test
  public void testFootwearUpdateStatus() {
    fw.setIfGearAddedtoEquipmentBag(true);
    assertTrue(fw.getIfGearAddedtoEquipmentBag());
    fw.setIfGearUsedByPlayer(true);
    assertTrue(fw.getIfGearUsedByPlayer());
  }
  
  /**
   * Test if the footwear can be removed from equipment bag.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testFootwearRemoveFromEquipmentBag() {
    fw.setIfGearAddedtoEquipmentBag(false);
  }
  
  /**
   * Test if the footwear can be removed from a player assignment.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testFootwearRemoveFromPlayerAssignment() {
    fw.setIfGearUsedByPlayer(false);
  }
  
  /**
   * Test if once the footwear is used to update player abilities, can it be 
   * changed back.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testFootwearPlayerUpdate() {
    fw.setIsPlayerAbilitiesUpdated(true);
    fw.setIsPlayerAbilitiesUpdated(false);
  }
  
  /**
   * Test the effect footwear has on a player.
   */
  @Test
  public void testFootwearEffect() {    
    Map<String, Integer> map = new HashMap();
    map.put("Strength", 0);
    map.put("Constitution",  -3);
    map.put("Dexterity", 5);
    map.put("Charisma", -2);
    
    assertEquals(map, fw.checkEffectOfGear());
  }
  
  /**
   * Test if the belt is created correctly with assigned id.
   */
  @Test
  public void testBeltCreation() {
    assertEquals("Belt 2", belt.getGearId());
    assertEquals(BeltSizes.SMALL, ((Belts) belt).getBeltSize());
  }
  
  /**
   * Test if a newly created belt is not yet added to equipment 
   * bag and is assigned to a player.
   */
  @Test
  public void testBeltStatus() {
    belt = new Belts(2, BeltSizes.MEDIUM);
    assertFalse(belt.getIfGearAddedtoEquipmentBag());
    assertFalse(belt.getIfGearUsedByPlayer());
  }
  
  /**
   * Test if the belt status can be updated once the gear is added to 
   * the equipment bag or assigned to a player.
   */
  @Test
  public void testBeltUpdateStatus() {
    belt = new Belts(2, BeltSizes.LARGE);
    belt.setIfGearAddedtoEquipmentBag(true);
    assertTrue(belt.getIfGearAddedtoEquipmentBag());
    belt.setIfGearUsedByPlayer(true);
    assertTrue(belt.getIfGearUsedByPlayer());
  }
  
  /**
   * Test if the belt can be removed from equipment bag.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testBeltRemoveFromEquipmentBag() {
    belt = new Belts(2, BeltSizes.MEDIUM);
    belt.setIfGearAddedtoEquipmentBag(false);
  }
  
  /**
   * Test if the belt can be removed from a player assignment.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testBeltRemoveFromPlayerAssignment() {
    belt = new Belts(2, BeltSizes.MEDIUM);
    belt.setIfGearUsedByPlayer(false);
  }
  
  /**
   * Test if once the belt is used to update player abilities, can it be 
   * changed back.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testBeltPlayerUpdate() {
    belt = new Belts(2, BeltSizes.MEDIUM);
    belt.setIsPlayerAbilitiesUpdated(true);
    belt.setIsPlayerAbilitiesUpdated(false);
  }
  
  /**
   * Test the effect small sized belt has on a player.
   */
  @Test
  public void testSmallBeltEffect() {
    Map<String, Integer> map = new HashMap();
    map.put("Strength", 0);
    map.put("Constitution", 1);
    map.put("Dexterity", -1);
    map.put("Charisma", 0);
    
    assertEquals(map, belt.checkEffectOfGear());
  }
  
  /**
   * Test the effect medium sized belt has on a player.
   */
  @Test
  public void testMediumBeltEffect() {
    belt = new Belts(1, BeltSizes.MEDIUM);
    
    Map<String, Integer> map = new HashMap();
    map.put("Strength", 0);
    map.put("Constitution", 2);
    map.put("Dexterity", -2);
    map.put("Charisma", 0);
    
    assertEquals(map, belt.checkEffectOfGear());
  }
  
  /**
   * Test the effect large sized belt has on a player.
   */
  @Test
  public void testLargeBeltEffect() {
    belt = new Belts(1, BeltSizes.LARGE);
    
    Map<String, Integer> map = new HashMap();
    map.put("Strength", 0);
    map.put("Constitution", 3);
    map.put("Dexterity", -2);
    map.put("Charisma", 0);
    
    assertEquals(map, belt.checkEffectOfGear());
  }
  
  /**
   * Test if an illegal keyword, not from the BeltSizes enum 
   * is considered valid.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testBeltIllegalSize() {
    belt = new Belts(2, MEDIUM);
  }
}
