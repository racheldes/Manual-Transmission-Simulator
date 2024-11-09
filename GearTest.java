import org.junit.Before;
import org.junit.Test;

import vehicle.Gear;

import static org.junit.Assert.assertEquals;

/**
 * Tester class for Gear Class.
 */
public class GearTest {
  private Gear gear1;
  private Gear gear2;
  private Gear gear3;
  private Gear gear4;
  private Gear gear5;

  @Before
  public void setUp() {
    gear1 = new Gear(0, 5, 1);
    gear2 = new Gear(3, 8, 2);
    gear3 = new Gear(6, 10, 3);
    gear4 = new Gear(9, 15, 4);
    gear5 = new Gear(13, 20, 5);
  }

  @Test
  // get number on gear 1
  public void testGetNumber1() {
    assertEquals(1, gear1.getNumber());
  }

  @Test
  // get number on gear 2
  public void testGetNumber2() {
    assertEquals(2, gear2.getNumber());
  }

  @Test
  // get number on gear 3
  public void testGetNumber3() {
    assertEquals(3, gear3.getNumber());
  }

  @Test
  // get number on gear 4
  public void testGetNumber4() {
    assertEquals(4, gear4.getNumber());
  }

  @Test
  // get number on gear 5
  public void testGetNumber5() {
    assertEquals(5, gear5.getNumber());
  }

  @Test
  // get low on gear 1
  public void testGetLow1() {
    assertEquals(0, gear1.getLow());
  }

  @Test
  // get low on gear 2
  public void testGetLow2() {
    assertEquals(3, gear2.getLow());
  }

  @Test
  // get low on gear 3
  public void testGetLow3() {
    assertEquals(6, gear3.getLow());
  }

  @Test
  // get low on gear 4
  public void testGetLow4() {
    assertEquals(9, gear4.getLow());
  }

  @Test
  // get low on gear 5
  public void testGetLow5() {
    assertEquals(13, gear5.getLow());
  }


  @Test
  // get high on gear 1
  public void testGetHigh1() {
    assertEquals(5, gear1.getHigh());
  }

  @Test
  // get high on gear 2
  public void testGetHigh2() {
    assertEquals(8, gear2.getHigh());
  }

  @Test
  // get high on gear 3
  public void testGetHigh3() {
    assertEquals(10, gear3.getHigh());
  }

  @Test
  // get high on gear 4
  public void testGetHigh4() {
    assertEquals(15, gear4.getHigh());
  }

  @Test
  // get high on gear 5
  public void testGetHigh5() {
    assertEquals(20, gear5.getHigh());
  }
}
