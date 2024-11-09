package vehicle;

/**
 * Class represents a type of gear that has a low speed,
 * high speed, and number.
 */
public class Gear {
  private final int low;
  private final int high;
  private final int number;


  /**
   * Constructs a Gear object.
   *
   * @param low    represents the low speed of a gear
   * @param high   represents the high speed of a gear
   * @param number represents the number that corresponds to the gear
   */
  public Gear(int low, int high, int number) {
    this.low = low;
    this.high = high;
    this.number = number;
  }

  /**
   * Helper method to return the number of the gear.
   *
   * @return the number of a gear
   */
  public int getNumber() {
    return this.number;
  }

  /**
   * Helper method to return the low speed of the gear.
   *
   * @return the low speed of a gear
   */
  public int getLow() {
    return this.low;
  }

  /**
   * Helper method to return the high speed of the gear.
   *
   * @return the high speed of a gear
   */
  public int getHigh() {
    return this.high;
  }
}
