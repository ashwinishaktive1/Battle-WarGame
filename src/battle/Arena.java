package battle;

import java.util.List;

/**
 * The Arena forms a playground for the players, and finally the 
 * game setting.
 * Lots of games pit one player against another in the Arena.
 * The winners of these games tend to depend upon the abilities 
 * of the players, the gear that they have at their disposal, and, 
 * a little bit, on their luck.
 * One type of game played on the Arena is the "Battle".
 * 
 * @author Ashwini Shaktivel Kumar
 *
 */
public interface Arena {
  
  /**
   * Get an introduction of the battle game.
   * 
   * @return introductory string
   */
  public String introduceBattle();
  
  /**
   * Call the players with basic abilities onto the arena.
   * 
   * @param playerOneId Player 1 identifier
   * @param playerTwoId Player 2 identifier
   */
  public void callPlayers(int playerOneId, int playerTwoId);
  
  /**
   * Prepare the players by equipping them with gears and 
   * weapons for the battle.
   * 
   * @throws IllegalStateException if the players are not called
   */
  public void preparePlayers() throws IllegalStateException;
  
  /**
   * Get the basic information of players including their basic 
   * ability assets.
   * 
   * @return all player basic information
   * @throws IllegalStateException if the players are not called
   */
  public String getPlayersBasicInfo() throws IllegalStateException;
  
  /**
   * Get the complete description of players including their temporary 
   * powers, gears and weapons used.
   * 
   * @return all player description
   * @throws IllegalStateException if the players are not called
   */
  public String getPlayersDescription() throws IllegalStateException;
    
  /**
   * Start the game by assigning the first move to player with greater 
   * charisma.
   *  
   * @throws IllegalStateException if the players are not prepared
   */
  public void startGame() throws IllegalStateException;
  
  /**
   * Play turn. The attacker attacks the defender.
   * 
   * @throws IllegalStateException if the game has not started
   */
  public void playTurn() throws IllegalStateException;
  
  /**
   * Print out information for each round of the battle. This should include 
   * who attacks, whether they successfully hit their opponent, and how much 
   * damage their opponent took.
   * 
   * @return turn details string
   */
  public String getTurnDetails();
  
  /**
   * Get the winner of the game. In case of a drawn match, both 
   * players are returned.
   * 
   * @return winning player(s)
   */
  public List<Players> getWinner();
  
  /**
   * Get the final result of the battle, along with the details of the players 
   * winning and loosing, and their health. 
   * 
   * @return battle result string
   */
  public String getFinalResult();
  
  /**
   * Ask user for a re-match. In which case the players would be allowed to rest to 
   * regain their full health and they would enter the arena for a fresh battle. 
   * This should be the only keyboard input required to use your driver program.
   * 
   * @throws IllegalStateException is user asks to rematch before starting 
   *                               the current game
   */
  public void rematch() throws IllegalStateException;
  
  /**
   * Get the player objects generated during the battle.
   * 
   * @return list of players
   */
  public List<Players> getPlayers();
}
