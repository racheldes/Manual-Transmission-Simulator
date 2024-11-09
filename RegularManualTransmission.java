package vehicle;


/**
 * vehicle.RegularManualTransmission class represents a type of vehicle.ManualTransmission
 * that has a speed, 5 gears, and a status.
 */
public class RegularManualTransmission implements ManualTransmission {
  private int speed;
  private final Gear gear1;
  private final Gear gear2;
  private final Gear gear3;
  private final Gear gear4;
  private final Gear gear5;
  private String status;
  private Gear currentGear;
  private final int maxSpeed;


  /**
   * Constructs a regular manual transmission if all the inputted values are valid.
   *
   * @param l1 represents the low value of gear 1
   * @param h1 represents the high value of gear 1
   * @param l2 represents the low value of gear 2
   * @param h2 represents the high value of gear 2
   * @param l3 represents the low value of gear 3
   * @param h3 represents the high value of gear 3
   * @param l4 represents the low value of gear 4
   * @param h4 represents the high value of gear 4
   * @param l5 represents the low value of gear 5
   * @param h5 represents the high value of gear 5
   * @throws IllegalArgumentException if the values inputted do not match the
   *                                  conditions required
   */
  public RegularManualTransmission(int l1, int h1, int l2, int h2, int l3, int h3,
                                   int l4, int h4, int l5, int h5) throws IllegalArgumentException {
    if (validNums(l1, h1, l2, h2, l3, h3, l4, h4, l5, h5) && overlapCorrectly(l1,
            l2, l3, l4, l5, h1, h2, h3, h4, h5) && l1 == 0) {
      gear1 = new Gear(l1, h1, 1);
      gear2 = new Gear(l2, h2, 2);
      gear3 = new Gear(l3, h3, 3);
      gear4 = new Gear(l4, h4, 4);
      gear5 = new Gear(l5, h5, 5);
      this.speed = 0;
      this.currentGear = gear1;
      maxSpeed = h5;
      this.status = "OK: everything is OK.";
    } else {
      throw new IllegalArgumentException("Invalid values for gear speeds.");
    }
  }

  /**
   * Returns whether the speeds are high of each gear is greater than or equal
   * to the low of each gear.
   *
   * @param l1 represents the low value of gear 1
   * @param h1 represents the high value of gear 1
   * @param l2 represents the low value of gear 2
   * @param h2 represents the high value of gear 2
   * @param l3 represents the low value of gear 3
   * @param h3 represents the high value of gear 3
   * @param l4 represents the low value of gear 4
   * @param h4 represents the high value of gear 4
   * @param l5 represents the low value of gear 5
   * @param h5 represents the high value of gear 5
   * @return whether the values are valid
   */
  private boolean validNums(int l1, int h1, int l2, int h2, int l3,
                            int h3, int l4, int h4, int l5, int h5) {
    return (l1 <= h1) && (l2 <= h2) && (l3 <= h3) && (l4 <= h4) && (l5 <= h5)
            && (l1 >= 0 && l2 >= 0 && l3 >= 0 && l4 >= 0 && l5 >= 0);
  }

  /**
   * Checks that the lower speeds are strictly lesser for increasing gears
   * and higher speeds are in ascending order.
   *
   * @param h1 represents the high value of gear 1
   * @param l1 represents the low value of gear 1
   * @param l2 represents the low value of gear 2
   * @param h2 represents the high value of gear 2
   * @param l3 represents the low value of gear 3
   * @param h3 represents the high value of gear 3
   * @param l4 represents the low value of gear 4
   * @param h4 represents the high value of gear 4
   * @param l5 represents the low value of gear 5
   * @param h5 represents the high value of gear 5
   * @return whether the values overlap correctly
   */
  private boolean overlapCorrectly(int l1, int l2, int l3, int l4, int l5,
                                   int h1, int h2, int h3, int h4, int h5) {
    // lows in ascending order
    return (l1 < l2 && l2 < l3 && l3 < l4 && l4 < l5)
            // highs in ascending order
            && (h2 > h1 && h3 > h2 && h4 > h3 && h5 > h4)
            // low of next has to be less than high of prev
            && (l2 <= h1 && l3 <= h2 && l4 <= h3 && l5 <= h4)
            // non adjacent gears cannot overlap
            && (l3 > h1 && l4 > h2 && l5 > h3);
  }


  @Override
  public String getStatus() {
    return this.status;
  }

  @Override
  public int getSpeed() {
    return this.speed;
  }


  @Override
  public int getGear() {
    return this.currentGear.getNumber();
  }


  @Override
  public ManualTransmission increaseSpeed() {
    int newSpeed = getSpeed() + 1;
    // withing the speed range and speed is not at maxSpeed
    if (this.currentGear.getNumber() < 5) {
      if (withinSpeedRange(newSpeed) && lessThanMax(newSpeed)
              // within range of next gear
              && newSpeed < nextGear().getLow()) {
        this.speed = newSpeed;
        this.status = "OK: everything is OK.";
      }
      // speed at max
      else if (newSpeed > maxSpeed) {
        this.status = "Cannot increase speed. Reached maximum speed.";
      } else if (withinSpeedRange(newSpeed) && lessThanMax(newSpeed)
              && newSpeed >= nextGear().getLow()) {
        this.speed = newSpeed;
        this.status = "OK: you may increase the gear.";
      } else if (lessThanMax(newSpeed)
              && newSpeed > nextGear().getLow()) {
        this.status = "Cannot increase speed, increase gear first.";
      }
    } else {
      if (withinSpeedRange(newSpeed) && lessThanMax(newSpeed)
              && newSpeed > prevGear().getHigh()) {
        this.speed = newSpeed;
        this.status = "OK: everything is OK.";
      }
      // speed at max
      else {
        this.status = "Cannot increase speed. Reached maximum speed.";
      }
    }
    return this;
  }

  /**
   * Helper to determine whether the inputted speed is less than the max speed.
   *
   * @param nS represents the new speed
   * @return whether the new speed is less than the max speed
   */
  private boolean lessThanMax(int nS) {
    return nS <= maxSpeed;
  }

  /**
   * Helper that determines whether the inputted speed is more than the
   * minimum speed.
   *
   * @param nS represents the new speed
   * @return whether the new speed is more than the max speed
   */
  private boolean moreThanMin(int nS) {
    return nS >= 0;
  }

  @Override
  public ManualTransmission decreaseSpeed() {
    int newSpeed = getSpeed() - 1;
    // make sure i'm not on gear 1
    if (this.currentGear.getNumber() > 1) {
      if (withinSpeedRange(newSpeed) && moreThanMin(newSpeed)
              // within range of next gear
              && newSpeed > prevGear().getHigh()) {
        this.speed = newSpeed;
        this.status = "OK: everything is OK.";
      }
      // speed at min
      else if (newSpeed < 0) {
        this.status = "Cannot decrease speed. Reached minimum speed.";
      } else if (withinSpeedRange(newSpeed) && moreThanMin(newSpeed)
              && newSpeed <= prevGear().getHigh()) {
        this.speed = newSpeed;
        this.status = "OK: you may decrease the gear.";
      } else if (moreThanMin(newSpeed)
              && newSpeed <= prevGear().getHigh()) {
        this.status = "Cannot decrease speed, decrease gear first.";
      }
    } else {
      if (withinSpeedRange(newSpeed) && moreThanMin(newSpeed)
              && newSpeed <= nextGear().getLow()) {
        // within range of next gear
        this.speed = newSpeed;
        this.status = "OK: everything is OK.";
      }
      // speed at min
      else {
        this.status = "Cannot decrease speed. Reached minimum speed.";
      }
    }
    return this;
  }

  /**
   * Helper to get the next gear.
   *
   * @return the next gear based on the current gear's number
   */
  private Gear nextGear() {
    if (this.currentGear.getNumber() == 1) {
      return gear2;
    } else if (this.currentGear.getNumber() == 2) {
      return gear3;
    } else if (this.currentGear.getNumber() == 3) {
      return gear4;
    } else {
      return gear5;
    }
  }

  /**
   * Helper to get the previous gear.
   *
   * @return the previous gear based on the current gear's number
   */
  private Gear prevGear() {
    if (this.currentGear.getNumber() == 5) {
      return gear4;
    } else if (this.currentGear.getNumber() == 4) {
      return gear3;
    } else if (this.currentGear.getNumber() == 3) {
      return gear2;
    } else {
      return gear1;
    }
  }

  /**
   * Helper to determine whether the new speed is within range of the current gear.
   *
   * @param newS represents the new speed
   * @return whether the value is within the boundaries of the current gear
   */
  private boolean withinSpeedRange(int newS) {
    return newS >= this.currentGear.getLow() && newS <= this.currentGear.getHigh();
  }


  @Override
  public ManualTransmission increaseGear() {
    if (this.currentGear.getNumber() < 5) {
      if (this.currentGear.getNumber() == 1 && this.speed >= gear2.getLow()) {
        this.currentGear = gear2;
        this.status = "OK: everything is OK.";
      } else if (this.currentGear.getNumber() == 2 && this.speed >= gear3.getLow()) {
        this.currentGear = gear3;
        this.status = "OK: everything is OK.";
      } else if (this.currentGear.getNumber() == 3 && this.speed >= gear4.getLow()) {
        this.currentGear = gear4;
        this.status = "OK: everything is OK.";
      } else if (this.currentGear.getNumber() == 4 && this.speed >= gear5.getLow()) {
        this.currentGear = gear5;
        this.status = "OK: everything is OK.";
      } else {
        this.status = "Cannot increase gear, increase speed first.";
      }
    } else {
      this.status = "Cannot increase gear. Reached maximum gear.";
    }
    return this;
  }


  @Override
  public ManualTransmission decreaseGear() {
    if (this.currentGear.getNumber() > 1) {
      if (this.currentGear.getNumber() == 5 && this.speed <= gear4.getHigh()) {
        this.currentGear = gear4;
        this.status = "OK: everything is OK.";
      } else if (this.currentGear.getNumber() == 4 && this.speed <= gear3.getHigh()) {
        this.currentGear = gear3;
        this.status = "OK: everything is OK.";
      } else if (this.currentGear.getNumber() == 3 && this.speed <= gear2.getHigh()) {
        this.currentGear = gear2;
        this.status = "OK: everything is OK.";
      } else if (this.currentGear.getNumber() == 2 && this.speed <= gear1.getHigh()) {
        this.currentGear = gear1;
        this.status = "OK: everything is OK.";
      } else {
        this.status = "Cannot decrease gear, decrease speed first.";
      }
    } else {
      this.status = "Cannot decrease gear. Reached minimum gear.";
    }
    return this;
  }
}
