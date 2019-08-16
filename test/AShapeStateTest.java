import org.junit.Before;
import org.junit.Test;

import cs3500.animator.model.AnimatedShape;
import cs3500.animator.model.Color;
import cs3500.animator.model.EllipseState;
import cs3500.animator.model.IReadOnlyShapeState;
import cs3500.animator.model.IShapeState;
import cs3500.animator.model.RectangleState;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import static cs3500.animator.model.ShapeType.ELLIPSE;
import static cs3500.animator.model.ShapeType.RECTANGLE;
import static org.junit.Assert.assertEquals;

/**
 * tests the abstracted methods of {@code AShapeState} for getters.
 */
public class AShapeStateTest {

  IShapeState shapeState;
  IShapeState shapeState2;
  IReadOnlyShapeState readOnly;
  IReadOnlyShapeState readOnly2;

  @Before
  public void setUp() {
    shapeState = new RectangleState(4, 4, 4, new Color(255, 255, 255),
            new Point2D.Double(100, 100));
    shapeState2 = new EllipseState(4, 4, 4, new Color(1, 10, 100),
            new Point2D.Double(100, 100));
    readOnly = new RectangleState(5, 5, 5, new Color(55, 155, 255),
            new Point2D.Double(100, 100));
    readOnly2 = new EllipseState(10, 10, 10, new Color(10, 10, 10),
            new Point2D.Double(10, 10));
  }

  @Test
  public void testDefaultAnimatedShape() {
    AnimatedShape animation1 = new AnimatedShape("Dave", RECTANGLE, new ArrayList<>(), 0);
    AnimatedShape animation2 = new AnimatedShape("Sandy", ELLIPSE, new ArrayList<>(), 1);
    assertEquals(true, animation1.getStates().isEmpty());
    assertEquals(true, animation2.getStates().isEmpty());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor1() {
    new RectangleState(-1, 2, 2, new Color(0, 0, 0), new Point2D.Double(0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2() {
    new RectangleState(1, -7, 2, new Color(0, 0, 0), new Point2D.Double(0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor3() {
    new RectangleState(0, 2, -4, new Color(0, 0, 0), new Point2D.Double(0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor7() {
    new RectangleState(0, 2, 2, new Color(0, 0, 0), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor4() {
    new EllipseState(-1, 2, 2, new Color(0, 0, 0), new Point2D.Double(0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor5() {
    new EllipseState(1, -7, 2, new Color(0, 0, 0), new Point2D.Double(0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor6() {
    new EllipseState(0, 2, -4, new Color(0, 0, 0), new Point2D.Double(0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor8() {
    new EllipseState(0, 2, 2, new Color(0, 0, 0), null);
  }

  @Test
  public void testGetWidth() {
    assertEquals(readOnly.getWidth(), 5);
  }

  @Test
  public void testGetHeight() {
    assertEquals(readOnly.getHeight(), 5);
  }

  @Test
  public void testGetColor() {
    assertEquals(readOnly.getColor().getRed(), 55);
    assertEquals(readOnly.getColor().getGreen(), 155);
    assertEquals(readOnly.getColor().getBlue(), 255);
  }

  @Test
  public void testGetPosition() {
    assertEquals(readOnly.getPosition(), new Point2D.Double(100, 100));
  }

  @Test
  public void testGetWidth2() {
    assertEquals(readOnly2.getWidth(), 10);
  }

  @Test
  public void testGetHeight2() {
    assertEquals(readOnly2.getHeight(), 10);
  }

  @Test
  public void testGetColor2() {
    assertEquals(readOnly2.getColor().getRed(), 10);
    assertEquals(readOnly2.getColor().getGreen(), 10);
    assertEquals(readOnly2.getColor().getBlue(), 10);
  }

  @Test
  public void testGetPosition2() {
    assertEquals(readOnly2.getPosition(), new Point2D.Double(10, 10));
  }

}
