import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import cs3500.animator.model.AnimatedShape;
import cs3500.animator.model.IAnimatedShape;
import cs3500.animator.model.IShapeState;
import cs3500.animator.model.ShapeType;
import cs3500.animator.view.EllipseTag;
import cs3500.animator.view.ISVGTag;
import cs3500.animator.view.RectangleTag;

import static org.junit.Assert.assertEquals;

/**
 * Tests the functionality of the {@code ISVGTag} inteface and class implementation.
 */
public class ISVGTagTest {

  ISVGTag tagRect;
  ISVGTag tagEll;
  IAnimatedShape animatedRectangle;
  IAnimatedShape animatedEllipse;

  @Before
  public void setUp() {
    ArrayList stateRect = new ArrayList<IShapeState>();
    animatedRectangle = new AnimatedShape("Dave",
            ShapeType.RECTANGLE, stateRect,0);
    ArrayList stateEll = new ArrayList<IShapeState>();
    animatedEllipse = new AnimatedShape("Vido",
            ShapeType.ELLIPSE, stateEll,1);
    tagRect = new RectangleTag(animatedRectangle,20);
    tagEll = new EllipseTag(animatedEllipse,20);
  }

  @Test
  public void testFormat1() {
    assertEquals(tagRect.format(),"<rect id=\"Dave\">\n" +
            "</rect>\n");
  }

  @Test
  public void testFormat2() {
    assertEquals(tagEll.format(),"<ellipse id=\"Vido\">\n" +
            "</ellipse>\n");
  }

  @Test
  public void testFormat3() {
    animatedRectangle.fullMotion(1,23,4,5,6,7,8,9,5,2,3,4,5,6,7,8);
    assertEquals(tagRect.format(),"<rect id=\"Dave\" x=\"23.0\"  y=\"4.0\" width=\"5\" " +
            "height=\"6\" fill=\"rgb(7,8,9)\" visibility=\"visible\" >\n" +
            "<animate attributesType=\"xml\" begin=\"50.0ms\" dur=\"200.0ms\" attributeName=\"" +
            "fill\" from=\"rgb(7,8,9)\" to=\"rgb(6,7,8)\" fill=\"freeze\" />\n" +
            "<animate attributesType=\"xml\" begin=\"50.0ms\" dur=\"200.0ms\" attributeName=\"" +
            "x\" from=\"23.0\" to=\"2.0\" fill=\"freeze\" />\n" +
            "<animate attributesType=\"xml\" begin=\"50.0ms\" dur=\"200.0ms\" attributeName=\"" +
            "y\" from=\"4.0\" to=\"3.0\" fill=\"freeze\" />\n" +
            "<animate attributesType=\"xml\" begin=\"50.0ms\" dur=\"200.0ms\" attributeName=\"" +
            "width\" from=\"5\" to=\"4\" fill=\"freeze\" />\n" +
            "<animate attributesType=\"xml\" begin=\"50.0ms\" dur=\"200.0ms\" attributeName=\"" +
            "height\" from=\"6\" to=\"5\" fill=\"freeze\" />\n" +
            "</rect>\n");
  }

  @Test
  public void testFormat4() {
    animatedEllipse.fullMotion(1,23,4,5,6,7,8,9,5,2,3,4,5,6,7,8);
    assertEquals(tagEll.format(),"<ellipse id=\"Vido\" cx=\"23.0\"  cy=\"4.0\" rx=\"2\"" +
            " ry=\"3\" fill=\"rgb(7,8,9)\" visibility=\"visible\" >\n" +
            "<animate attributesType=\"xml\" begin=\"50.0ms\" dur=\"200.0ms\" attributeName=\"" +
            "fill\" from=\"rgb(7,8,9)\" to=\"rgb(6,7,8)\" fill=\"freeze\" />\n" +
            "<animate attributesType=\"xml\" begin=\"50.0ms\" dur=\"200.0ms\" attributeName=" +
            "\"cx\" from=\"23.0\" to=\"2.0\" fill=\"freeze\" />\n" +
            "<animate attributesType=\"xml\" begin=\"50.0ms\" dur=\"200.0ms\" attributeName=" +
            "\"cy\" from=\"4.0\" to=\"3.0\" fill=\"freeze\" />\n" +
            "<animate attributesType=\"xml\" begin=\"50.0ms\" dur=\"200.0ms\" attributeName=" +
            "\"rx\" from=\"2\" to=\"2\" fill=\"freeze\" />\n" +
            "<animate attributesType=\"xml\" begin=\"50.0ms\" dur=\"200.0ms\" attributeName=\"" +
            "ry\" from=\"3\" to=\"2\" fill=\"freeze\" />\n" +
            "</ellipse>\n");
  }



}
