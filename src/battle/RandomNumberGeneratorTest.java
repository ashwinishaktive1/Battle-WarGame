package battle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class represents a random number generator for testing. It 
 * encapsulates the Random class and extends the RandomNumberGenerator 
 * class. 
 * 
 * @author Ashwini Shaktivel Kumar
 *
 */
public class RandomNumberGeneratorTest extends Random 
    implements RandomNumberGenerator {
  
  private int randomNumber;
  private List<Integer> randomNumbersList;
  
  /**
   * Constructor for generating pre-defined random number or a list of 
   * random numbers.
   * 
   * @param numbers A range of numbers than forms the list from where
   *                the random numbers are generated.
   */
  public RandomNumberGeneratorTest(int...numbers) {
    if (numbers.length == 0) {
      this.randomNumber = 0;
    } else if (numbers.length == 1) {
      this.randomNumber = numbers[0];
    } else if (numbers.length > 1) {
      this.randomNumbersList = new ArrayList<>();
      for (int i = 0; i < numbers.length; i++) {
        this.randomNumbersList.add(numbers[i]);
      }
      this.randomNumber = numbers[0];
    }
  }
 
  @Override
  public int getRandomNumber(int lowerBound, int upperBound) {
    return this.randomNumber;
  }

  @Override
  public List<Integer> getUniqueRandomNumbersList(int length, 
      int lowerBound, int upperBound) {
    List<Integer> predefinedNumbers = new ArrayList<>();
    if (this.randomNumbersList == null) {
      predefinedNumbers.add(0);
      predefinedNumbers.add(1);
      predefinedNumbers.add(2);
      predefinedNumbers.add(3);
      predefinedNumbers.add(5);
      predefinedNumbers.add(6);
      predefinedNumbers.add(8);
      predefinedNumbers.add(9);
      predefinedNumbers.add(10);
      predefinedNumbers.add(11);
      predefinedNumbers.add(13);
      predefinedNumbers.add(14);
      predefinedNumbers.add(15);
      predefinedNumbers.add(17);
      predefinedNumbers.add(19);
      predefinedNumbers.add(20);
      predefinedNumbers.add(21);
      predefinedNumbers.add(22);
      predefinedNumbers.add(24);
      predefinedNumbers.add(26);
      return predefinedNumbers;
    }
    return this.randomNumbersList;
  }

  @Override
  public List<Integer> getRepeatedRandomNumbersList(int length, 
      int lowerBound, int upperBound) {
    List<Integer> predefinedNumbers = new ArrayList<>();
    int size = 0;
    for (int i = 0; i < length; i++) {
      if (size == upperBound) {
        size = 0;
      }
      predefinedNumbers.add(size);
      size++;
    }
    return predefinedNumbers;
  }

}
