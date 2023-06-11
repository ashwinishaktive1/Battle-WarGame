<!-- PROJECT LOGO -->
<br />
<h1 align="center"> Project 2 - BATTLE </h1>
</br>
<div align="center">
    <img src="https://www.brawlhalla.com/c/uploads/2022/02/GIJ_Snake1.png" alt="Brawl Halla Logo" width="500" height="250">
    <br />
    <br />
  <p align = "left">
    <h2> Win your luck!</h2>
    An epic <a href = https://en.wikipedia.org/wiki/Role-playing_game>role-playing game</a> were the players battle using a turn-based system. The winners of these battles depend upon the abilities of the players, the gear that they have at their disposal, and, a little bit, on their luck. 
    <br />
    <br />
  </p>
</div>

<!-- Overview -->
## Overview
The Battle starts with two players coming forth on the arena equipped with powerful gears and weapons. The players come in with their basic abilities, and get charged up with the effects of gear. The weapons allow them to make a successful strike or to safely defend themselves from the opponent. The abilities, powes of gears and weapons are all randomly assigned, based on yours truly, luck!
<ol>
<li>
Player 1 attacks using the weapon that they have in-hand by taking a swing at player 2 who tries to avoid the attack. If player 1 hits player 2, then player 2 potentially takes damage.
</li>
<li>
Player 2 attacks using the weapon that they have in-hand by taking a swing at player 1 who tries to avoid the attack. If player 2 hits player 1, then player 1 potentially takes damage.
</li>
<li>
Turns continue back and forth until one of the players has taken a total damage that is greater than or equal to their health.
</li>
<li>
The player who did not take a total damage greater than or equal to their health is declared the victor. It is possible for a battle to end in a draw.
</li>
</ol>

The players are at disposal of the following abilities:
<li> <b>Strength</b> affects how effective the player is at striking their opponent.

<li> <b>Constitution</b> affects how much damage a player can take when they are hit in battle.

<li> <b>Dexterity</b> affects how effective the player is at avoiding a strike from their opponent.

<li> <b>Charisma</b> affects how their opponent views them. This helps them start the game, as the one with greater charisma can greatly affect the opponent's viewpoint.
<br/>
<br/>
These abilities hence form the basis of how a player plays. It either helps them be a great attacker or a defender. The abilities are disposed at random based on a 6-face dice rolled 4 times for each player. Hence, the abilities must range from 8 to 24 (a 1 asks for roll-again).
<br/>
<br/>
Then comes the fun part: <b> Gears and Weapons!!</b>
<ol>
<li>Gears come in a variety of types:
<ul>
<li><b>Headgear</b> is worn on the player's head and increases the player's constitution (protects). But, it also hides the player's face, leading to reduction in charisma. Since a player has one head, they can only wear one piece of headgear.</li>
<li><b>Potions</b> are consumed by the player before entering the arena. They can temporarily increase strength and dexterity (making the players even stronger) and reduce constitution. The potions might have slight side effect on their behaviour, leading to reduced constitution temporarily. There is no limit to the number of these that the player can drink. <i>Note: The temporary effect of potions remain for 2 strikes as an attacker by the player. </i></li>
<li><b>Belts</b> come in three sizes -- small, medium, and large -- and are worn around the player's torso. They protect the player's upper body and hence add up to the constitution, but reduce the movement range and hence decrease dexterity of the player. Players have the ability to wear 10 "units" of belts where small belts count as 1 unit, medium as 2 units, and large as 4 units.</li>
<li><b>Footwear</b> is worn on the player's feet and increase the player's dexterity. However, do not necessarily protect them hence reduce the constitution and present a lower charisma. Footwear always comes in pairs and a player can only wear one piece of footwear at a time.</li>
</ul>
</li>
<li>
Weapons can be three types Swords (Katanas, Broadswords, Two-Handed sword), Axes or Flail.
<ul>
<li><b>Katanas</b> are lightweight curved swords that come in pairs. They can do a base of 4-6 points of damage when they hit. They are so light that a player can carry two of them (which attack separately).
<li><b>Broadswords</b> are a good medium weapon that can do 6-10 points of damage when they hit.
<li><b>Two-handed swords</b> are a heavy sword that can only be effectively wielded by players with <i>strength greater than 14</i>, but they can do 8-12 points of damage when they hit. If the player does not have the strength to wield a two-handed sword, the sword only does half damage.
<li><b>Axes</b> are great general weapons doing 6-10 points of damage when they hit.
<li><b>Flails</b> are also great general weapons but they can only be effectively wielded by players with a <i>dexterity greater than 14</i>. They do 8-12 points of damage when they hit. If the player does not have the dexterity to wield a flail, the flail only does half damage.
</ul>
</li>
</ol>
Now that we know, how a player gets ready for the battle. Let's discuss further about how the battle runs. The play continues in a turn-based system, wherein the players keep switching between attacked and defender roles. 

</br>
The damage for each strike is calculated based on the attacker's strength, the weapon used and the defender's constitution (plus a pinch of luck).
The game ends when the defender of any turn reaches a health of 0 or less, and the attacker wins the game. 
</br>
<i> The game may also end if the last 20 rounds of the battle has been unsuccessful. The round is considered unsuccessful if the attacker does not successfully strike the defender or if upon successful strike the damage is nil. Such a game is declared as drawn.</i>

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- List of features -->
## List of features

<ol>
    <li>Random Number Generator: Generate a random number or a list of random numbers (unique and with repetitions) within a given range for adding randomness to the game elements.</li>
    <li>Initializing the Battle Game: Automatically create a battle setup, with inbuilt equipment bag for gears and armory for weapons.
    <li>Introduce the battle: Describe the turn-based battle game. Includes brief overview of the game's name, it's proceduce and how the game ends.
    <li>Calling the players: Players are created using integer numbers acting as identifiers. The players are initialized with basic abilities and are unequipped.
    <li>Displaying player's basic information: All current players identifier, abilities, and their total health are displayed.
    <li>Prepare players: Players now equip gears from the equipment bag and weapon from the armory set up in the battle arena. The players have their abilities updated according to the effects of the gears.</li>
    <li>Displaying player description: All current players identifier, abilities, their total health, temporary effects, gears equipped and weapons to be used are displayed.</li>
    <li>Start game: The attacker and defender are decided based on the charisma of the player.</li>
    <li>Battle start details: Details of the attacker defender and healths is displayed before the first strike happens.</li>
    <li>Play turns: The turns are played and swapped until the game ends.</li>
    <li>A strike is considered successful if the attacker's striking power is greater than defender's avoidance ability. The damage is equal to the difference between potential striking damage by the attacker and the defender's constitution.</li>
    <li>Is game over?: The game is over when either player's health drops below or equal to 0. The game may also end if there are more than 20 redundant turns (no successful strikes).</li>
    <li>Display final result: Get the winner for the game, or a notification about the game being drawn.</li>
    <li>Rematch: The players are reset to original abilities. They are dropped off the gears and weapons. The equipment bag and armory are reset in the battle arena. All the game aspects collected in the previous game are nullified.</li>
    <li>Exit.</li>
</ol>

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- How To Run? -->
## How To Run?

To run the JAR file packed, execute the follow command:
   ```sh
   java -jar project02_Battle.jar
   ```

<!-- How to Use the Program? -->
## How to Use the Program?

<ul>
    <li>Source files (src folder) - Run the BattleDriver file. Use Main method to run a static listed run of the battle. Other java files include:
    <ol>
    <li> Arena Interface
    <li> BattleModel Class
    <li> Players Interface
    <li> BattlePlayer Class
    <li> Equipment Bag Class
    <li> Gears Interface
    <ul>
    <li> AbstractGears Abstract Class
    <ul>
    <li> Headgear Class
    <li> Potions Class
    <li> Footwear Class
    <li> Belts Class
    </ul>
    <li> BeltSizes ENUM
    </ul>
    <li> Armory Class
    <li> Weapons Interface
    <ul>
    <li> AbstractWeapons Abstract Class
    <ul>
    <li> Axes Class
    <li> BroadSwords Class
    <li> Flails Class
    <li> Katanas Class
    <li> TwoHandedSwords Class
    </ul>
    <li> WeaponWeightCategories ENUM
    </ul>
    <li> RandomNumberGenerator Interface
    <ul>
    <li>RandomNumberGeneratorDev Class
    <li>RandomNumberGeneratorTest Class
    </ul>
    </ol>
    </li>
    <li>Test Files - Run test file including BattleModelTest, BattlePlayerTest, EquipmentBagTest, ArmoryTest, GearsTest and WeaponsTest for JUnit tests of each component class. The test files are executed from outside the battle package (in the test package).</li>
    <li>The preliminary and updated design documents are available in the res directory for reference. 
    <li>JAR file (res directory) - To run the java file, use the following command on command line (no arguments needed): 
    <br/> 
    <b> java -jar project02_Battle.jar </b>
    </li>
</ul>


<p align="right">(<a href="#top">back to top</a>)</p>

<!-- Description of Examples -->
## Description of Examples

Run 1 -- DriverRun.txt:
<ol> 
<li> Run the BattleDriver.java class file. </li>
<li> Initialize input scanner. </li>
<li> Initialize random number generator object </li>
<li> Initialize Battle. </li>
<li> Introduce battle game. </li>
<li> Call players on the battle arena. </li>
<li> Get basic information of players with initial abilities. </li>
<li> Prepare players for the battle.</li>
<li> Get complete descriptio of players with temporary abilities, gears equipped, and weapon used. </li>
<li> Start game.
<li> Get the initial player setup and the attacker-defender assignment for the first round.</li>
<li> Play turns until game ends.</li>
<li> Display final result of the game. </li>
<li> Ask user for a  rematch.</li>
<li> If yes, do rematch setup and go to step 8.</li>
<li> If no, quit game.</li>
</ol>

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- Design/Model Changes -->
## Design/Model Changes

<ul>
<p>The original design did not include the AbstractWeapons and AbstractGears class. While creating and implementing the sub-classes (types of weapons and gears), I observed that a set of methods remained common amongst all the sub-classes. Implementing abstract classes seemed the best idea to avoid code redundancy and repetition. Further, I added an enum class for the weapon weight/size categories. The design also reduces directional dependency from the weapons/gears to players. According to the recommendation received on my original plan, I also added a Players interface. This will allow extending the application to different types of games, other than Battle. </p>
</ul>


<p align="right">(<a href="#top">back to top</a>)</p>

<!-- Assumptions -->
## Assumptions

<ul>
<li>Temporary effects of potions remain for 2 strikes amde by the player as an attacker.</li>
<li>Temporary effect on charisma will be ignored while deciding who starts the game.</li>
<li>Health is calculated based on initial abilities.</li>
<li>While initializing the temporary abilities are equated to 0.</li>
<li>Equipments from equipment bag are alloted only until gears greater than 20 are remaining. 
<li>Effect of headgear: Strength: 0 | Constitution: +2 | Dexterity: 0 | Charisma: -2
<li>Effect of footwear: Strength: 0 | Constitution: -3 | Dexterity: +5 | Charisma: -2
<li>Effect of potion: Strength: +1 | Constitution: -1 | Dexterity: +1 | Charisma: 0
<li>Effect of belt: 
<ul>
<li>Small: Strength: 0 | Constitution: +1 | Dexterity: -1 | Charisma: 0
<li>Medium: Strength: 0 | Constitution: +2 | Dexterity: -2 | Charisma: 0
<li>Large: Strength: 0 | Constitution: +3 | Dexterity: -2 | Charisma: 0
</ul>
<li>Weapon from armory are alloted only until weapons greater than 2 are remaining.
<li>The gears used by the player from the set of gears received from equipment bag are chosen in a FCFS basis.</li>
<li>When the striking power > avoidance ability, and the damage is greater than 0, the strike is considered successful.</li>
<li>When the striking power > avoidance ability, and the damage is 0, the strike is considered unsuccessful.</li>
<li>When the striking power < avoidance ability, the strike is considered unsuccessful.</li>
<li>If the actual damage is greater than 0, it is subtracted from the player's health.</li>
<li>The game ends if defender's health drops to 0 or below 0, or if the match continues to have unsuccessful rounds for the last 20 turns.</li>
<li>In case of rematch, the players are switched back to initial abilities and health. The battle is setup with a new equipment bag and armory. Allowing user's to get a better chance at picking gears and weapons.</li>
</ul>

</br>

<!-- LIMITATIONS -->
## Limitations

<ul>
<li>The program is designed for battles, but it can be adapted to work with other games to be setup on arena. The players will still have same abilities, gears and weapons, but could be invoked for a variety of games. </li>
<li>User inputs and direct access/manipulation are not currently being actuated, except for the input request in the driver class.</li>
<li>The data is dynamic. The user can a test out the application with the RandomNumberGeneratorTest object, however it comes with it's own limitations. The user does not have any say on the players will perform.</li>
<li>The player cannot rematch when current game has not started. </li>
</ul>

</br>

<!-- CITATIONS -->
## Citations

<ul>
<li><a href="https://www.markdownguide.org/getting-started/">Markdown guide</a></li>
<li><a href="https://www.baeldung.com/java-random-list-element">Get Random Item From A List</a></li>
<li><a href="https://www.geeksforgeeks.org/iterate-map-java/">How to iterate any Map in Java</a></li>
<li><a href="https://www.geeksforgeeks.org/builder-pattern-in-java/">Builder methods in java</a></li>
</ul>

<p align="right">(<a href="#top">back to top</a>)</p>
