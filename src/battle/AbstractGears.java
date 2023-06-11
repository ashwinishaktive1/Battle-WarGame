package battle;

import java.util.Map;

/**
 * This is an abstract class representing the common features 
 * amongst the various types of gears. It contains the common 
 * methods between the extending child gear classes.
 * 
 * @author Ashwini Shaktivel Kumar
 *
 */
public abstract class AbstractGears implements Gears {
  
  private boolean isUsedByPlayer;
  private boolean isAddedToEquipmentBag;
  private boolean isPlayerAbilitiesUpdated;
  
  /**
   * Construct an abstract gear.
   */
  public AbstractGears() {
    this.isUsedByPlayer = false;
    this.isAddedToEquipmentBag = false;
    this.isPlayerAbilitiesUpdated = false;
  }

  @Override
  public abstract String getGearId();

  @Override
  public void setIsPlayerAbilitiesUpdated(Boolean update) 
      throws IllegalArgumentException {
    if (update == false) {
      throw new IllegalArgumentException("The player abilities cannot "
          + "be recalled to default.");
    }
    this.isPlayerAbilitiesUpdated = update;    
  }

  @Override
  public boolean getIfGearUsedByPlayer() {
    return this.isUsedByPlayer;
  }

  @Override
  public void setIfGearUsedByPlayer(Boolean update) 
      throws IllegalArgumentException {
    if (update == false) {
      throw new IllegalArgumentException("The gear cannot be removed from "
          + "player assignment.");
    }
    this.isUsedByPlayer = update;
  }

  @Override
  public boolean getIfGearAddedtoEquipmentBag() {
    return this.isAddedToEquipmentBag;
  }

  @Override
  public void setIfGearAddedtoEquipmentBag(Boolean update) 
      throws IllegalArgumentException {
    if (update == false) {
      throw new IllegalArgumentException("The gear cannot be removed "
          + "from the equipment bag.");
    }
    this.isAddedToEquipmentBag = update;
  }

  @Override
  public abstract Map<String, Integer> checkEffectOfGear();
}
