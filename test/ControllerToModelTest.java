import org.junit.Before;
import org.junit.Test;
import cs3500.animator.controller.IAnimatorController;
import cs3500.animator.controller.VisualController;
import cs3500.animator.model.IModel;
import cs3500.animator.model.TestModel;
import cs3500.animator.view.IView;
import cs3500.animator.view.TestView;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;

/**
 * tests the functionality and ability for communication between the controller
 * and model of this animation program.
 */
public class ControllerToModelTest {
  Appendable appendable;
  IView testView;
  IModel testModel;
  IAnimatorController controller;

  @Before
  public void setUp() {
    appendable = new StringBuilder();
    testModel = new TestModel(appendable);
  }
  
  @Test
  public void testAddShape() {
    this.testView = new TestView(new StringReader("AddShape"));
    controller = new VisualController(testModel, testView, 50);
    testView.drawShapes(null);
    assertEquals("Getting shapesShape Test Success added " +
                    "into modelGetting shapes",
        appendable.toString());
  }
  
  @Test
  public void testRemoveShape() {
    this.testView = new TestView(new StringReader("RemoveShape"));
    controller = new VisualController(testModel, testView, 50);
    testView.drawShapes(null);
    assertEquals("Getting shapesShape Test Success removed " +
                    "from modelGetting shapes",
        appendable.toString());
  }
  
  @Test
  public void testAddKeyFrame() {
    this.testView = new TestView(new StringReader("AddKeyframe"));
    controller = new VisualController(testModel, testView, 50);
    testView.drawShapes(null);
    assertEquals("Getting shapesKey frame added to Test Success" +
                    " in modelGetting shapes",
        appendable.toString());
  }
  
  @Test
  public void testEditKeyFrame() {
    this.testView = new TestView(new StringReader("EditKeyframe"));
    controller = new VisualController(testModel, testView, 50);
    testView.drawShapes(null);
    assertEquals("Getting shapesKey frame edited in Test Success " +
                    "in modelGetting shapes",
        appendable.toString());
  }
  
  @Test
  public void testRemoveKeyFrame() {
    this.testView = new TestView(new StringReader("RemoveKeyframe"));
    controller = new VisualController(testModel, testView, 50);
    testView.drawShapes(null);
    assertEquals("Getting shapesKey frame removed from Test " +
                    "Success in modelGetting shapes",
        appendable.toString());
  }
}