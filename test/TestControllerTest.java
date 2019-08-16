import org.junit.Before;
import org.junit.Test;
import cs3500.animator.controller.IAnimatorController;
import cs3500.animator.view.IView;
import cs3500.animator.view.TestView;
import java.io.StringReader;
import cs3500.animator.controller.TestController;

import static org.junit.Assert.assertEquals;

/**
 * uses the methodology of mock testing to test the {@code VisualController} and
 * the {@code EditView}.
 */
public class TestControllerTest {
  Appendable appendable;
  IView testView;
  IAnimatorController testController;
  
  @Before
  public void setup() {
    appendable = new StringBuilder();
    testController = new TestController(testView, appendable);
  }
  
  
  @Test
  public void testPlay() {
    testView = new TestView(new StringReader("Play"));
    testView.addListener(testController);
    testView.drawShapes(null);
    assertEquals("play", appendable.toString());
  }
  
  @Test
  public void testPause() {
    testView = new TestView(new StringReader("Pause"));
    testView.addListener(testController);
    testView.drawShapes(null);
    assertEquals("pause", appendable.toString());
  }
  
  @Test
  public void testRestart() {
    testView = new TestView(new StringReader("Restart"));
    testView.addListener(testController);
    testView.drawShapes(null);
    assertEquals("restart", appendable.toString());
  }
  
  @Test
  public void testLoop() {
    testView = new TestView(new StringReader("Loop"));
    testView.addListener(testController);
    testView.drawShapes(null);
    assertEquals("loop", appendable.toString());
  }
  
  @Test
  public void testUpdateSpeed() {
    testView = new TestView(new StringReader("Speed"));
    testView.addListener(testController);
    testView.drawShapes(null);
    assertEquals("new speed: 50", appendable.toString());
  }
  
  @Test
  public void testAddShape() {
    testView = new TestView(new StringReader("AddShape"));
    testView.addListener(testController);
    testView.drawShapes(null);
    assertEquals("new shape: Test Success", appendable.toString());
  }
  
  @Test
  public void testRemoveShape() {
    testView = new TestView(new StringReader("RemoveShape"));
    testView.addListener(testController);
    testView.drawShapes(null);
    assertEquals("Test Success shape removed", appendable.toString());
  }
  
  @Test
  public void testAddKeyFrame() {
    testView = new TestView(new StringReader("AddKeyframe"));
    testView.addListener(testController);
    testView.drawShapes(null);
    assertEquals("new key frame to shape Test Success at tick 25",
            appendable.toString());
  }
  
  @Test
  public void testEditKeyFrame() {
    testView = new TestView(new StringReader("EditKeyframe"));
    testView.addListener(testController);
    testView.drawShapes(null);
    assertEquals("key frame edited for shape Test Success",
            appendable.toString());
  }
  
  @Test
  public void testRemoveKeyFrame() {
    testView = new TestView(new StringReader("RemoveKeyframe"));
    testView.addListener(testController);
    testView.drawShapes(null);
    assertEquals("key frame removed from shape Test Success",
            appendable.toString());
  }
}