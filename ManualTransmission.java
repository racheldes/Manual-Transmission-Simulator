package vehicle;

/**
 * Manual Transmission Interface to represent the
 * different types of Manual Transmissions.
 */
public interface ManualTransmission {
  /**
   * Returns the current status of a gear.
   *
   * @return the status of a gear as a String
   */
  public String getStatus();

  /**
   * Based on the current speed of the transmission returns it.
   *
   * @return the current speed of a Manual Transmission as an integer
   */
  public int getSpeed();

  /**
   * Based on the current speed, returns the current gear.
   *
   * @return the current gear the Manual Transmission is at
   */
  public int getGear();

  /**
   * Increases the speed if possible and updates the status.
   *
   * @return the manual transmission with the correct fields
   */
  public ManualTransmission increaseSpeed();

  /**
   * Decreases the speed if possible and updates the status.
   *
   * @return the manual transmission with the correct fields
   */
  public ManualTransmission decreaseSpeed();

  /**
   * Increases the gear if possible and updates the status.
   *
   * @return the manual transmission with the correct fields
   */
  public ManualTransmission increaseGear();

  /**
   * Decreases the gear if possible and updates the status.
   *
   * @return the manual transmission with the correct fields
   */
  public ManualTransmission decreaseGear();
}
