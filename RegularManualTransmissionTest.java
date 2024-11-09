import org.junit.Test;
import org.junit.Before;

import vehicle.RegularManualTransmission;

import static org.junit.Assert.assertEquals;

/**
 * Tests for Regular Manual Transmission.
 */
public class RegularManualTransmissionTest {
  private RegularManualTransmission mt1;
  private RegularManualTransmission mt2;
  private RegularManualTransmission mt3;
  private RegularManualTransmission mt4;
  private RegularManualTransmission mt5;


  @Before
  public void setUp() {
    mt2 = new RegularManualTransmission(0, 4, 2, 7, 6, 9,
            8, 13, 11, 18);
    mt3 = new RegularManualTransmission(0, 4, 2, 7, 6, 9,
            8, 13, 11, 18);
    mt4 = new RegularManualTransmission(0, 5, 3, 8, 6, 12,
            9, 19, 15, 21);
    mt5 = new RegularManualTransmission(0, 2, 1, 4, 3, 6,
            5, 8, 7, 9);
  }

  // testing Constructor overlapping in the middle
  @Test
  public void testValidConstructing() {
    try {
      mt2 = new RegularManualTransmission(0, 4, 2, 7, 6,
              9, 7, 9, 8, 12);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid values for gear speeds.", e.getMessage());
    }
  }


  // testing constructor throws an exception with a low that's not 0
  @Test
  public void testInvalid() {
    try {
      mt1 = new RegularManualTransmission(0, 10, 8, 12, 11, 16,
              15, 19, 18, 25);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid values for gear speeds.", e.getMessage());
    }
  }

  // testing constructor throws an exception with a low that's not 0
  @Test
  public void testInvalidLowSpeed() {
    try {
      mt1 = new RegularManualTransmission(5, 10, 8, 12, 11, 16,
              15, 19, 18, 25);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid values for gear speeds.", e.getMessage());
    }
  }

  // testing constructor throws an exception with negative numbers
  @Test
  public void testInvalidSpeed() {
    try {
      mt1 = new RegularManualTransmission(0, 10, -8, 12, 11, 16,
              15, -19, 18, 25);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid values for gear speeds.", e.getMessage());
    }
  }

  // testing constructor throws an exception with non overlapping speeds
  @Test
  public void testNonOverlapping() {
    try {
      mt1 = new RegularManualTransmission(0, 10, 11, 15, 16, 20,
              22, 28, 30, 22);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid values for gear speeds.", e.getMessage());
    }
  }

  // testing constructor throws an exception with a non-adjacent gears overlapping
  @Test
  public void testInvalidOverlapping() {
    try {
      mt1 = new RegularManualTransmission(0, 10, 5, 15, 10, 20,
              15, 25, 20, 30);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid values for gear speeds.", e.getMessage());
    }
  }

  // testing constructor throws an exception with values not in ascending order
  @Test
  public void testInvalidRange() {
    try {
      mt1 = new RegularManualTransmission(0, 5, 3, 8, 11, 16,
              7, 19, 15, 25);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid values for gear speeds.", e.getMessage());
    }
  }

  // test that the right value is being printed out
  @Test
  public void testInValidMessage() {
    try {
      mt1 = new RegularManualTransmission(0, 5, -3, 8, 11, 16,
              7, 19, 15, 25);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid values for gear speeds.", e.getMessage());
    }
  }


  // testing that everything is constructed as expected
  @Test
  public void testValidRange() {
    mt1 = new RegularManualTransmission(0, 10, 8, 12, 11, 16,
            15, 19, 18, 25);
    assertEquals("OK: everything is OK.", mt1.getStatus());
    assertEquals(0, mt1.getSpeed());
    assertEquals(1, mt1.getGear());
  }


  // testing get Status on a newly constructed transmission
  @Test
  public void testStatus1() {
    assertEquals("OK: everything is OK.", mt2.getStatus());
  }

  // testing get Status on a valid increase of speed
  // but can increase the gear
  @Test
  public void testStatus2() {
    mt4.increaseSpeed();
    mt4.increaseSpeed();
    mt4.increaseSpeed();
    assertEquals("OK: you may increase the gear.", mt4.getStatus());
  }

  // testing get Status on a valid increase of speed
  // and gears but can decrease the gear
  @Test
  public void testStatus3() {
    mt5.increaseSpeed();
    mt5.increaseSpeed();

    mt5.increaseGear();
    mt5.increaseSpeed();
    mt5.increaseSpeed();
    mt5.increaseGear();

    mt5.increaseSpeed();
    mt5.increaseSpeed();
    mt5.increaseGear();

    mt5.increaseSpeed();
    mt5.increaseSpeed();
    mt5.increaseGear();
    mt5.increaseSpeed();

    mt5.decreaseSpeed();
    assertEquals("OK: you may decrease the gear.", mt5.getStatus());
  }

  // test get status on an increasing speed to
  // the point of needing to increase a gear
  @Test
  public void testStatus4() {
    mt4.increaseSpeed();
    mt4.increaseSpeed();
    mt4.increaseSpeed();
    mt4.increaseSpeed();
    mt4.increaseSpeed();
    mt4.increaseSpeed();
    assertEquals("Cannot increase speed, increase gear first.", mt4.getStatus());
  }

  //test get status on a gear that you cannot decrease it's speed
  @Test
  public void testStatus5() {
    mt5.increaseSpeed();
    mt5.increaseSpeed();

    mt5.increaseGear();
    mt5.increaseSpeed();
    mt5.increaseSpeed();
    mt5.increaseGear();

    mt5.increaseSpeed();
    mt5.increaseSpeed();
    mt5.increaseGear();

    mt5.increaseSpeed();
    mt5.increaseSpeed();
    mt5.increaseGear();
    mt5.increaseSpeed();

    mt5.decreaseSpeed();
    mt5.decreaseSpeed();
    mt5.decreaseSpeed();
    assertEquals("Cannot decrease speed, decrease gear first.", mt5.getStatus());
  }

  // get status on a case you can't increase the gear until you increase the speed
  @Test
  public void testStatus6() {
    mt3.increaseSpeed();
    mt3.increaseGear();
    assertEquals("Cannot increase gear, increase speed first.", mt3.getStatus());
  }

  // get status on a case you can't decrease the gear until you decrease the speed
  @Test
  public void testStatus7() {
    mt3.increaseSpeed();
    mt3.increaseSpeed();
    mt3.increaseGear();
    // gear 2 speed 2
    mt3.increaseSpeed();
    mt3.increaseSpeed();
    mt3.increaseSpeed();
    mt3.increaseSpeed();
    mt3.increaseGear();
    // gear 3 speed 6
    mt3.increaseSpeed();
    mt3.increaseSpeed();
    // gear 3 speed 8

    mt3.decreaseGear();

    assertEquals("Cannot decrease gear, decrease speed first.", mt3.getStatus());
  }


  // getSpeed on a newly constructed manual transmission
  @Test
  public void testGetSpeed1() {
    assertEquals(0, mt2.getSpeed());
  }

  // getSpeed on the max speed
  @Test
  public void testGetSpeed2() {
    mt5.increaseSpeed();
    mt5.increaseSpeed();

    mt5.increaseGear();
    mt5.increaseSpeed();
    mt5.increaseSpeed();
    mt5.increaseGear();

    mt5.increaseSpeed();
    mt5.increaseSpeed();
    mt5.increaseGear();

    mt5.increaseSpeed();
    mt5.increaseSpeed();
    mt5.increaseGear();
    mt5.increaseSpeed();
    assertEquals(9, mt5.getSpeed());
  }

  // get speed on a speed in the middle of a transmission
  @Test
  public void testGetSpeed3() {
    mt4.increaseSpeed();
    mt4.increaseSpeed();
    mt4.increaseSpeed();
    mt4.increaseSpeed();
    mt4.increaseSpeed();
    mt4.increaseSpeed();
    assertEquals(5, mt4.getSpeed());
  }


  //  test get Gear on gear 1
  @Test
  public void testGetGear1() {
    mt5.increaseSpeed();
    mt5.increaseSpeed();
    assertEquals(1, mt5.getGear());
  }

  //  test get Gear on gear 2
  @Test
  public void testGetGear2() {
    mt5.increaseSpeed();
    mt5.increaseSpeed();

    mt5.increaseGear();
    mt5.increaseSpeed();
    mt5.increaseSpeed();
    assertEquals(2, mt5.getGear());
  }

  //  test get Gear on gear 3
  @Test
  public void testGetGear3() {
    mt5.increaseSpeed();
    mt5.increaseSpeed();

    mt5.increaseGear();
    mt5.increaseSpeed();
    mt5.increaseSpeed();
    mt5.increaseGear();

    mt5.increaseSpeed();
    assertEquals(3, mt5.getGear());
  }

  //  test get Gear on gear 4
  @Test
  public void testGetGear4() {
    mt5.increaseSpeed();
    mt5.increaseSpeed();

    mt5.increaseGear();
    mt5.increaseSpeed();
    mt5.increaseSpeed();
    mt5.increaseGear();

    mt5.increaseSpeed();
    mt5.increaseSpeed();
    mt5.increaseGear();

    mt5.increaseSpeed();
    mt5.increaseSpeed();
    assertEquals(4, mt5.getGear());
  }

  //  test get Gear on gear 5
  @Test
  public void testGetGear5() {
    mt5.increaseSpeed();
    mt5.increaseSpeed();

    mt5.increaseGear();
    mt5.increaseSpeed();
    mt5.increaseSpeed();
    mt5.increaseGear();

    mt5.increaseSpeed();
    mt5.increaseSpeed();
    mt5.increaseGear();

    mt5.increaseSpeed();
    mt5.increaseSpeed();
    mt5.increaseGear();
    mt5.increaseSpeed();
    mt5.increaseSpeed();
    assertEquals(5, mt5.getGear());
  }


  // increase Speed

  // testing increase Speed starting at gear 1 and increase successfully
  @Test
  public void testIncreaseS1() {
    int temp1 = mt3.getSpeed();
    mt3.increaseSpeed();
    int temp2 = mt3.getSpeed();
    assertEquals(temp1, temp2 - 1);
  }

  // increase but now in range of next
  @Test
  public void testIncreaseS2() {
    //initial g1 s 0
    // g1 s1
    mt4.increaseSpeed();
    // g1 s2
    mt4.increaseSpeed();
    // g1 s3  (low of next gear)
    mt4.increaseSpeed();
    assertEquals("OK: you may increase the gear.", mt4.getStatus());
  }


  // test to increase the speed and decrease the speed on the same gear
  @Test
  public void testIncreaseS5() {
    //initial g1 s 0
    // g1 s1
    mt4.increaseSpeed();
    // g1 s2
    mt4.increaseSpeed();
    assertEquals("OK: everything is OK.", mt4.getStatus());
    // g1 s3  (low of next gear)
    mt4.increaseSpeed();
    assertEquals("OK: you may increase the gear.", mt4.getStatus());
    // g1 s2
    mt4.decreaseSpeed();
    assertEquals("OK: everything is OK.", mt4.getStatus());
  }

  // increase but can't because gear needs to be updated
  @Test
  public void testIncreaseS3() {
    // called increase speed 6 times
    mt4.increaseSpeed();
    mt4.increaseSpeed();
    mt4.increaseSpeed();
    mt4.increaseSpeed();
    mt4.increaseSpeed();
    mt4.increaseSpeed();
    assertEquals("Cannot increase speed, increase gear first.", mt4.getStatus());
  }

  // can't increase speed because at max
  @Test
  public void testIncreaseS4() {
    // called increase speed 10 times but the max is 9
    mt5.increaseSpeed();
    mt5.increaseSpeed();

    mt5.increaseGear();
    mt5.increaseSpeed();
    mt5.increaseSpeed();
    mt5.increaseGear();

    mt5.increaseSpeed();
    mt5.increaseSpeed();
    mt5.increaseGear();

    mt5.increaseSpeed();
    mt5.increaseSpeed();
    mt5.increaseGear();
    mt5.increaseSpeed();
    mt5.increaseSpeed();

    assertEquals("Cannot increase speed. Reached maximum speed.", mt5.getStatus());
  }

  // decrease Speed


  // test to increase the speed and decrease the speed on the same gear
  @Test
  // decrease then increase speed
  public void testDecreaseS5() {
    //initial g1 s 0
    // g1 s1
    mt4.increaseSpeed();
    // g1 s2
    mt4.increaseSpeed();
    assertEquals("OK: everything is OK.", mt4.getStatus());
    // g1 s3  (low of next gear)
    mt4.increaseSpeed();
    assertEquals("OK: you may increase the gear.", mt4.getStatus());
    // g1 s2
    mt4.decreaseSpeed();
    mt4.increaseSpeed();
    mt4.increaseSpeed();
    assertEquals("OK: everything is OK.", mt5.getStatus());
  }

  // decrease but now in range of next
  @Test
  public void testDecreaseS1() {
    mt5.increaseSpeed();
    mt5.increaseSpeed();

    mt5.increaseGear();
    mt5.increaseSpeed();
    mt5.increaseSpeed();
    mt5.increaseGear();

    mt5.increaseSpeed();
    mt5.increaseSpeed();
    mt5.increaseGear();

    mt5.increaseSpeed();
    mt5.increaseSpeed();
    mt5.increaseGear();
    mt5.increaseSpeed();

    mt5.decreaseSpeed();
    assertEquals("OK: you may decrease the gear.", mt5.getStatus());
  }

  // decrease but can't because gear needs to be updated
  @Test
  public void testDecreaseS2() {
    mt5.increaseSpeed();
    mt5.increaseSpeed();

    mt5.increaseGear();
    mt5.increaseSpeed();
    mt5.increaseSpeed();
    mt5.increaseGear();

    mt5.increaseSpeed();
    mt5.increaseSpeed();
    mt5.increaseGear();

    mt5.increaseSpeed();
    mt5.increaseSpeed();
    mt5.increaseGear();
    mt5.increaseSpeed();

    mt5.decreaseSpeed();
    mt5.decreaseSpeed();
    mt5.decreaseSpeed();
    assertEquals("Cannot decrease speed, decrease gear first.", mt5.getStatus());
  }

  // can't decrease speed because at min
  @Test
  public void testDecreaseS3() {
    mt4.decreaseSpeed();
    assertEquals("Cannot decrease speed. Reached minimum speed.", mt4.getStatus());
  }

  // testing decrease successfully
  @Test
  public void testDecreaseS4() {
    mt5.increaseSpeed();
    mt5.increaseSpeed();

    mt5.increaseGear();
    mt5.increaseSpeed();
    mt5.increaseSpeed();
    mt5.increaseGear();

    mt5.increaseSpeed();
    mt5.increaseSpeed();
    mt5.increaseGear();

    mt5.increaseSpeed();
    mt5.increaseSpeed();
    mt5.increaseGear();
    mt5.increaseSpeed();

    mt5.decreaseSpeed();
    mt5.decreaseGear();
    mt5.decreaseSpeed();
    assertEquals("OK: everything is OK.", mt5.getStatus());
  }

  // starting at gear 1 increase Gear successfully
  @Test
  public void testIncreaseG1() {
    mt3.increaseSpeed();
    mt3.increaseSpeed();
    mt3.increaseGear();
    assertEquals("OK: everything is OK.", mt3.getStatus());

  }

  // increase but can't because speed needs to be updated
  @Test
  public void testIncreaseG2() {
    mt3.increaseSpeed();
    mt3.increaseGear();
    assertEquals("Cannot increase gear, increase speed first.", mt3.getStatus());
  }

  // can't increase gear because at max
  @Test
  public void testIncreaseG3() {
    mt5.increaseSpeed();
    mt5.increaseSpeed();

    mt5.increaseGear();
    mt5.increaseSpeed();
    mt5.increaseSpeed();
    mt5.increaseGear();

    mt5.increaseSpeed();
    mt5.increaseSpeed();
    mt5.increaseGear();

    mt5.increaseSpeed();
    mt5.increaseSpeed();
    mt5.increaseGear();
    mt5.increaseSpeed();
    mt5.increaseGear();
    assertEquals("Cannot increase gear. Reached maximum gear.", mt5.getStatus());
  }


  // increase G then decrease G
  @Test
  public void testIncreaseG4() {
    mt3.increaseSpeed();
    mt3.increaseSpeed();
    mt3.increaseGear();
    mt3.decreaseGear();
    assertEquals("OK: everything is OK.", mt3.getStatus());
  }


  // starting at gear 1 can't decrease gear because at min
  @Test
  public void testDecreaseG1() {
    mt4.decreaseGear();
    assertEquals("Cannot decrease gear. Reached minimum gear.", mt4.getStatus());
  }

  // decrease successfully
  @Test
  public void testDecreaseG2() {
    mt5.increaseSpeed();
    mt5.increaseGear();
    // gear 2 speed 1

    mt5.decreaseGear();
    // gear 1 speed 1
    assertEquals("OK: everything is OK.", mt5.getStatus());
  }

  // decrease but can't because speed needs to be updated
  @Test
  public void testDecreaseG3() {
    mt3.increaseSpeed();
    mt3.increaseSpeed();
    mt3.increaseGear();
    // gear 2 speed 2
    mt3.increaseSpeed();
    mt3.increaseSpeed();
    mt3.increaseSpeed();
    mt3.increaseSpeed();
    mt3.increaseGear();
    // gear 3 speed 6
    mt3.increaseSpeed();
    mt3.increaseSpeed();
    // gear 3 speed 8

    mt3.decreaseGear();

    assertEquals("Cannot decrease gear, decrease speed first.", mt3.getStatus());
  }

  // decrease G then increase G
  @Test
  public void testDecreaseG4() {
    mt4.increaseSpeed();
    mt4.increaseSpeed();
    mt4.increaseGear();
    mt4.increaseSpeed();
    mt4.decreaseGear();
    mt4.increaseGear();
    assertEquals("OK: everything is OK.", mt3.getStatus());
  }
}
