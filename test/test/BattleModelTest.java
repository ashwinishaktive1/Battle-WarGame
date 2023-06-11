package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import battle.Arena;
import battle.Armory;
import battle.BattleModel;
import battle.BattlePlayer;
import battle.EquipmentBag;
import battle.Players;
import battle.RandomNumberGenerator;
import battle.RandomNumberGeneratorDev;
import battle.RandomNumberGeneratorTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit test case for the BattleModel class.
 * 
 * @author Ashwini Shaktivel Kumar
 *
 */
public class BattleModelTest {
  
  Arena battle;
  RandomNumberGenerator random;
  
  @Before
  public void setup() {
    random = new RandomNumberGeneratorTest(2);
    battle = new BattleModel(random);
  }
  
  /**
   * Testing if the battle model object is created successfully 
   * with a random number generated.
   */
  @Test
  public void testBattleModelConstructor() {
    BattleModel battleTest = new BattleModel(random);
  }
  
  /**
   * Testing if a null random number generator is accepted by 
   * the battle.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testBattleModelNullRandomNumberGen() {
    BattleModel battleTest = new BattleModel(null);
  }
  
  /**
   * Testing the battle introduction. The introduction is tested to explain 
   * how the battle runs.
   */
  @Test
  public void testIntroduceBattle() {
    String expected = "Welcome to....\n"
        + "Win Over Luck!\n"
        + "\n"
        + "A turn-based battle game setup on the grand Arena! \n"
        + "Turn-based battles generally start by pitting two players "
        + "against one another.\n"
        + "The battle begins by determining which player will go first "
        + "and then proceeds as follows:\n"
        + "\n"
        + "Player 1 attacks using the weapon that they have in-hand "
        + "by taking a swing at player 2 who tries to avoid the attack.\n"
        + "If player 1 hits player 2, then player 2 potentially takes damage.\n"
        + "\n"
        + "Player 2 attacks using the weapon that they have in-hand "
        + "by taking a swing at player 1 who tries to avoid the attack.\n"
        + "If player 2 hits player 1, then player 1 potentially takes damage.\n"
        + "\n"
        + "Turns continue back and forth until one of the players has "
        + "taken a total damage that is greater than or \n"
        + "equal to their health.\n"
        + "\n"
        + "The player who did not take a total damage greater than or "
        + "equal to their health is declared the victor.\n"
        + "It is possible for a battle to end in a draw.\n"
        + "\n"
        + "Let's try your luck! Good luck, haha! :D";
    assertEquals(expected, battle.introduceBattle());
  }
  
  /**
   * Testing the the players are called correctly, checked by testing 
   * their basic information.
   */
  @Test
  public void testCallPlayersAndGetBasicInfo() {
    battle.callPlayers(1, 2);
    
    String expected = "The players are in the arena, they look fine without the gears! \n"
        + "Player 1\n"
        + "Player basic abilities for the entire game: \n"
        + "Strength - 8\n"
        + "Constitution - 8\n"
        + "Dexterity - 8\n"
        + "Charisma - 8\n"
        + "\n"
        + "Player 2\n"
        + "Player basic abilities for the entire game: \n"
        + "Strength - 8\n"
        + "Constitution - 8\n"
        + "Dexterity - 8\n"
        + "Charisma - 8";
    
    assertEquals(expected, battle.getPlayersBasicInfo());
  }
  
  /**
   * Testing if the battle throws an exception if player basic information 
   * is requested before calling the players.
   */
  @Test (expected = IllegalStateException.class)
  public void testBasicInfoWithoutCallingPlayers() {
    battle.getPlayersBasicInfo();
  }
  
  /**
   * Testing if the battle throws an exception if players are requested  
   * to prepare themselves before calling the players.
   */
  @Test (expected = IllegalStateException.class)
  public void testPreparePlayersWithoutCallingPlayers() {
    battle.preparePlayers();
  }
  
  /**
   * Testing that the players have updated abilities after preparing 
   * them i.e. equipping gears and receiving weapons.
   */
  @Test
  public void testPreparePlayers() {
    battle.callPlayers(1, 2);
    battle.preparePlayers();
    
    Players p = battle.getPlayers().get(0);
    assertEquals(17, p.getStrength());
    assertEquals(-2, p.getConstitution());
    assertEquals(22, p.getDexterity());
    assertEquals(4, p.getCharisma());
    
    p = battle.getPlayers().get(1);
    assertEquals(14, p.getStrength());
    assertEquals(10, p.getConstitution());
    assertEquals(11, p.getDexterity());
    assertEquals(4, p.getCharisma());
  }
  
  /**
   * Testing if the battle throws an exception if player description 
   * is requested before calling the players.
   */
  @Test (expected = IllegalStateException.class)
  public void testDescriptionWithoutCallingPlayers() {
    battle.getPlayersDescription();
  }
  
  /**
   * Testing a set of players created for the test with same 
   * random number generator has the same description as that of the 
   * model after equipping gears and requesting weapon.
   */
  @Test
  public void testGetPlayersDescription() {
    List<Players> testPlayers = new ArrayList<>();
    testPlayers.add(new BattlePlayer(1, random));
    testPlayers.add(new BattlePlayer(2, random));
    
    EquipmentBag eqTest = new EquipmentBag(random);
    Armory armsTest = new Armory(random);
    
    String expected = "The complete description of players on "
        + "the arena: \n";
    for (Players p : testPlayers) {
      p.equipGears(eqTest);
      p.requestWeapon(armsTest);
      expected += p.getPlayerDescription() + "\n\n";
    }
    expected = expected.trim();
    
    battle.callPlayers(1, 2);
    battle.preparePlayers();
    assertEquals(expected, battle.getPlayersDescription());
  }
  
  /**
   * Testing if the battle throws an exception to start the game 
   * before preparing the players.
   */
  @Test (expected = IllegalStateException.class)
  public void testStartGameWithoutPreparingPlayers() {
    battle.callPlayers(1, 2);
    battle.startGame();
  }
  
  /**
   * Test starting game and checking if the attacker and defenders 
   * are correctly assigned.
   */
  @Test
  public void testStartGame() {
    battle.callPlayers(1, 2);
    battle.preparePlayers();
    battle.startGame();
    
    String expected = "Round 0\n"
        + "Turn details:\n"
        + "Attacker: Player 1; Health: 32\n"
        + "Defender: Player 2; Health: 32";
    assertEquals(expected, battle.getTurnDetails());
  }
  
  /**
   * Test playing turn before starting game.
   */
  @Test (expected = IllegalStateException.class)
  public void testPlayTurnGameNotStarted() {
    battle.callPlayers(1, 2);
    battle.preparePlayers();
    battle.playTurn();
  }
  
  /**
   * Test playing alternative turns between players, to check if 
   * the players are switched as attackers and defenders.
   */
  @Test
  public void testPlayTurn() {
    battle.callPlayers(1, 2);
    battle.preparePlayers();
    battle.startGame();
    battle.playTurn();
    
    String expected = "Round 1\n"
        + "Turn details:\n"
        + "Attacker: Player 2; Health: 32\n"
        + "Striking power: 16\n"
        + "Defender: Player 1; Health: 32\n"
        + "Avoidance ability: 24\n"
        + "That was amazingly defended. Player 1 is safe.";
    assertEquals(expected, battle.getTurnDetails());
    
    battle.playTurn();
    expected = "Round 2\n"
        + "Turn details:\n"
        + "Attacker: Player 1; Health: 32\n"
        + "Striking power: 19\n"
        + "Defender: Player 2; Health: 17\n"
        + "Avoidance ability: 13\n"
        + "Whoa, that was a great move. Player 1 strikes!\n"
        + "Damage caused to Player 2's health is 15";
    assertEquals(expected, battle.getTurnDetails());
  }
  
  /**
   * Test for dropping of temporary abilities after the temporary 
   * effect ends on the attacker.
   */
  @Test
  public void testTemporaryAbilitiesBetweenTurns() {
    Arena test = new BattleModel(new RandomNumberGeneratorTest(2));
    test.callPlayers(1, 2);
    test.preparePlayers();
    test.startGame();
    
    // Initial
    Players p1 = test.getPlayers().get(0);
    assertEquals(17, p1.getStrength());
    assertEquals(-2, p1.getConstitution());
    assertEquals(22, p1.getDexterity());
    assertEquals(4, p1.getCharisma());
    
    // Attacker: Player 2
    test.playTurn();
    Players p2 = test.getPlayers().get(1);
    assertEquals(14, p2.getStrength());
    assertEquals(10, p2.getConstitution());
    assertEquals(11, p2.getDexterity());
    assertEquals(4, p2.getCharisma());
    
    // Attacker: Player 1
    test.playTurn();
    p1 = test.getPlayers().get(0);
    assertEquals(17, p1.getStrength());
    assertEquals(-2, p1.getConstitution());
    assertEquals(22, p1.getDexterity());
    assertEquals(4, p1.getCharisma());
    
    // Attacker: Player 2
    test.playTurn();
    p2 = test.getPlayers().get(1);
    assertEquals(14, p2.getStrength());
    assertEquals(10, p2.getConstitution());
    assertEquals(11, p2.getDexterity());
    assertEquals(4, p2.getCharisma());
    
    // Attacker: Player 1
    test.playTurn();
    p1 = test.getPlayers().get(0);
    assertEquals(17, p1.getStrength());
    assertEquals(-2, p1.getConstitution());
    assertEquals(22, p1.getDexterity());
    assertEquals(4, p1.getCharisma());
    
    // Attacker: Player 2
    // Temporary abilities dropped
    test.playTurn();
    p2 = test.getPlayers().get(1);
    assertEquals(8, p2.getStrength());
    assertEquals(16, p2.getConstitution());
    assertEquals(5, p2.getDexterity());
    assertEquals(4, p2.getCharisma());
    
    // Attacker: Player 1
    // Temporary abilities dropped
    test.playTurn();
    p1 = test.getPlayers().get(0);
    assertEquals(8, p1.getStrength());
    assertEquals(7, p1.getConstitution());
    assertEquals(13, p1.getDexterity());
    assertEquals(4, p1.getCharisma());
  }
  
  /**
   * Testing damage done through successful strike and update of 
   * the defender's health.
   */
  @Test
  public void testStrikingDamageAndUpdatingHealth() {
    battle.callPlayers(1, 2);
    battle.preparePlayers();
    battle.startGame();
    battle.playTurn();
    
    String expected = "Round 1\n"
        + "Turn details:\n"
        + "Attacker: Player 2; Health: 32\n"
        + "Striking power: 16\n"
        + "Defender: Player 1; Health: 32\n"
        + "Avoidance ability: 24\n"
        + "That was amazingly defended. Player 1 is safe.";
    assertEquals(expected, battle.getTurnDetails());
    
    battle.playTurn();
    expected = "Round 2\n"
        + "Turn details:\n"
        + "Attacker: Player 1; Health: 32\n"
        + "Striking power: 19\n"
        + "Defender: Player 2; Health: 17\n"
        + "Avoidance ability: 13\n"
        + "Whoa, that was a great move. Player 1 strikes!\n"
        + "Damage caused to Player 2's health is 15";
    assertEquals(expected, battle.getTurnDetails());
  }
  
  /**
   * Testing whether for a negative damage, the final damage is 
   * set to zero and the defender remains unaffected.
   */
  @Test
  public void testNegativeActualDamage() {
    battle.callPlayers(1, 2);
    battle.preparePlayers();
    battle.startGame();
    battle.playTurn();
    String expected = "Round 1\n"
        + "Turn details:\n"
        + "Attacker: Player 2; Health: 32\n"
        + "Striking power: 16\n"
        + "Defender: Player 1; Health: 32\n"
        + "Avoidance ability: 24\n"
        + "That was amazingly defended. Player 1 is safe.";
    assertEquals(expected, battle.getTurnDetails());
    battle.playTurn();
    expected = "Round 2\n"
        + "Turn details:\n"
        + "Attacker: Player 1; Health: 32\n"
        + "Striking power: 19\n"
        + "Defender: Player 2; Health: 17\n"
        + "Avoidance ability: 13\n"
        + "Whoa, that was a great move. Player 1 strikes!\n"
        + "Damage caused to Player 2's health is 15";
    assertEquals(expected, battle.getTurnDetails());
    battle.playTurn();
    expected = "Round 3\n"
        + "Turn details:\n"
        + "Attacker: Player 2; Health: 17\n"
        + "Striking power: 16\n"
        + "Defender: Player 1; Health: 32\n"
        + "Avoidance ability: 24\n"
        + "That was amazingly defended. Player 1 is safe.";
    assertEquals(expected, battle.getTurnDetails());
    battle.playTurn();
    expected = "Round 4\n"
        + "Turn details:\n"
        + "Attacker: Player 1; Health: 32\n"
        + "Striking power: 19\n"
        + "Defender: Player 2; Health: 2\n"
        + "Avoidance ability: 13\n"
        + "Whoa, that was a great move. Player 1 strikes!\n"
        + "Damage caused to Player 2's health is 15";
    assertEquals(expected, battle.getTurnDetails());
    battle.playTurn();
    expected = "Round 5\n"
        + "Turn details:\n"
        + "Attacker: Player 2; Health: 2\n"
        + "Striking power: 10\n"
        + "Defender: Player 1; Health: 32\n"
        + "Avoidance ability: 24\n"
        + "That was amazingly defended. Player 1 is safe.";;
    assertEquals(expected, battle.getTurnDetails());
    battle.playTurn();
    expected = "Round 6\n"
        + "Turn details:\n"
        + "Attacker: Player 1; Health: 32\n"
        + "Striking power: 10\n"
        + "Defender: Player 2; Health: 2\n"
        + "Avoidance ability: 7\n"
        + "Whoa, that was a great move. Player 1 strikes!\n"
        + "Damage caused to Player 2's health is 0";
    assertEquals(expected, battle.getTurnDetails());
  }
  
  /**
   * Test re-match before attempting the current game setup. 
   */
  @Test (expected = IllegalStateException.class)
  public void testRematchBeforeStartingCurrentGame() {
    battle.callPlayers(1, 2);
    battle.preparePlayers();
    battle.rematch();
  }
  
  /**
   * Testing rematch after attempting current game.
   */
  @Test
  public void testRematch() {
    battle.callPlayers(1, 2);
    battle.preparePlayers();
    battle.startGame();
    battle.rematch();
  }
  
  /**
   * Testing the players and their abilities after refreshing from the 
   * previous game.
   */
  @Test
  public void testRematchPlayers() {
    battle.callPlayers(1, 2);
    battle.preparePlayers();
    battle.startGame();
    battle.playTurn();
    battle.playTurn();
    
    // Initial
    Players p1 = battle.getPlayers().get(0);
    assertEquals(17, p1.getStrength());
    assertEquals(-2, p1.getConstitution());
    assertEquals(22, p1.getDexterity());
    assertEquals(4, p1.getCharisma());
    
    Players p2 = battle.getPlayers().get(1);
    assertEquals(14, p2.getStrength());
    assertEquals(10, p2.getConstitution());
    assertEquals(11, p2.getDexterity());
    assertEquals(4, p2.getCharisma());
    
    battle.playTurn();
    battle.playTurn();
    battle.playTurn();
    battle.playTurn();
    
    // After playing few turns from the current game.
    p1 = battle.getPlayers().get(0);
    assertEquals(8, p1.getStrength());
    assertEquals(7, p1.getConstitution());
    assertEquals(13, p1.getDexterity());
    assertEquals(4, p1.getCharisma());
    
    p2 = battle.getPlayers().get(1);
    assertEquals(8, p2.getStrength());
    assertEquals(16, p2.getConstitution());
    assertEquals(5, p2.getDexterity());
    assertEquals(4, p2.getCharisma());
        
    battle.rematch();
    
    // Player is again assigned the same full-match and temporary 
    // abilities.
    p1 = battle.getPlayers().get(0);
    assertEquals(17, p1.getStrength());
    assertEquals(-2, p1.getConstitution());
    assertEquals(22, p1.getDexterity());
    assertEquals(4, p1.getCharisma());
    
    p2 = battle.getPlayers().get(1);
    assertEquals(14, p2.getStrength());
    assertEquals(10, p2.getConstitution());
    assertEquals(11, p2.getDexterity());
    assertEquals(4, p2.getCharisma());
  }
  
  /**
   * Testing match with redundant strikes. Both players continue 
   * to defend. The game draws.
   */
  @Test (expected = IllegalStateException.class)
  public void testRedundantMatch() {
    battle.callPlayers(1, 2);
    battle.preparePlayers();
    battle.startGame();
    for (int i = 0; i < 100; i++) {
      battle.playTurn();
    }
  }
  
  /**
   * Testing result after the game ends (winning or drawing).
   */
  @Test
  public void testGameWin() {
    battle = new BattleModel(new RandomNumberGeneratorDev());
    battle.callPlayers(1, 2);
    battle.preparePlayers();
    battle.startGame();
    while (true) {
      try {
        battle.playTurn();
      } catch (IllegalStateException s) {
        // do nothing
        break;
      }
    }
    
    assertTrue("It's a draw. Both players are extremely competitive!"
        .equals(battle.getFinalResult())
        || "Player 1 wins!!!!!!".equals(battle.getFinalResult())
        || "Player 2 wins!!!!!!".equals(battle.getFinalResult()));
  }
  
  /**
   * Testing if a match with redundant strikes is identified as draw.
   */
  @Test
  public void testGameDraw() {
    battle.callPlayers(1, 2);
    battle.preparePlayers();
    battle.startGame();
    for (int i = 0; i < 100; i++) {
      try {
        battle.playTurn();
      } catch (IllegalStateException s) {
        // do nothing
      }
    }
    
    assertTrue("It's a draw. Both players are extremely competitive!"
        .equals(battle.getFinalResult()));
  }
}
