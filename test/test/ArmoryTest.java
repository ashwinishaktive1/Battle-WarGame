package test;

import static org.junit.Assert.assertTrue;

import battle.Armory;
import battle.RandomNumberGenerator;
import battle.RandomNumberGeneratorTest;
import battle.Weapons;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit test case of the Armory class.
 * 
 * @author Ashwini Shaktivel Kumar
 *
 */
public class ArmoryTest {
  
  RandomNumberGenerator rand;
  Armory arm;
  
  /**
   * Setting up armory and Random Number Generator.
   */
  @Before
  public void setup() {
    rand = new RandomNumberGeneratorTest(0);
    arm = new Armory(rand);
  }
  
  /**
   * Testing if the armory object is created successfully.
   */
  @Test
  public void testArmoryConstructor() {
    Armory armTest = new Armory(rand);
  }
  
  /**
   * Testing get weapon and checking length of the list of weapons 
   * returned.
   */
  @Test
  public void testGetWeapon() {
    List<Weapons> test = arm.getWeapon();
    assertTrue(test.size() <= 2);
  }
  
  /**
   * Testing if after removal of all 20 weapons, does the armory 
   * throw an exception.
   */
  @Test (expected = IllegalStateException.class)
  public void testGetAllWeapons() {
    // 5 weapons in the armory
    for (int i = 0; i < 4; i++) {
      arm.getWeapon();
    } 
    arm.getWeapon();
  }
}
