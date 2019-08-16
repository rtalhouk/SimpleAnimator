import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import cs3500.animator.model.Color;

/**
 * Tests the functionality of the {@code Color} class implementation.
 */
public class ColorTest {

  Color color;

  @Before
  public void setup() {
    color = new Color(123, 231, 14);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadConstructor1() {
    Color colorBad = new Color(234, 256, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadConstructor2() {
    Color colorBad = new Color(256, 3, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadConstructor3() {
    Color colorBad = new Color(0, 3, 256);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadConstructor4() {
    Color colorBad = new Color(-1, 3, 256);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadConstructor5() {
    Color colorBad = new Color(23, -3, 256);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadConstructor6() {
    Color colorBad = new Color(23, 3, -256);
  }

  @Test
  public void testGetters() {
    assertEquals(123, color.getRed());
    assertEquals(231, color.getGreen());
    assertEquals(14, color.getBlue());
  }
}
