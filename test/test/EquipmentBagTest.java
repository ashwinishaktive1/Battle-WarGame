package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import battle.EquipmentBag;
import battle.Gears;
import battle.RandomNumberGenerator;
import battle.RandomNumberGeneratorTest;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit test case of the EquipmentBag class.
 * 
 * @author Ashwini Shaktivel Kumar
 *
 */
public class EquipmentBagTest {
  RandomNumberGenerator rand;
  EquipmentBag eqBag;
  
  /**
   * Setting up a global equipment bag object.
   */
  @Before
  public void setup() {
    rand = new RandomNumberGeneratorTest(2);
    eqBag = new EquipmentBag(rand);
  }
  
  /**
   * Testing the Equipment Bag constructor.
   */
  @Test
  public void testEquipmentBagConstructor() {
    EquipmentBag eq = new EquipmentBag(rand);
  }
  
  /**
   * Testing if 20 gears are returned to be used by the player.
   */
  @Test
  public void testGetGears() {
    assertEquals(20, eqBag.getGears().size());
  }
  
  /**
   * Testing if more gears can be retrieved from the equipment bag 
   * when the remaining number of gears in the bag is less than 20. 
   * As the bag can have maximum of 80 elements, in the edge case 
   * scenario the bag must throw an IllegalStateException when the 
   * gears are requested for the 5th time.
   */
  @Test (expected = IllegalStateException.class)
  public void testGetGearsEquipmentBagEmpty() {
    List<Gears> g1 = eqBag.getGears();
    List<Gears> g2 = eqBag.getGears();
    List<Gears> g3 = eqBag.getGears();
    List<Gears> g4 = eqBag.getGears();
    List<Gears> g5 = eqBag.getGears();
  }
  
  /**
   * Testing if the gears received from the equipment bag are unique.
   */
  @Test
  public void testGetGearsInBag() {
    List<Gears> g1 = eqBag.getGears();
    List<Gears> g2 = eqBag.getGears();
    
    assertTrue(!g1.containsAll(g2));
  }
}
