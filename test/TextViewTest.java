import static cs3500.animator.model.ShapeType.ELLIPSE;
import static cs3500.animator.model.ShapeType.RECTANGLE;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import cs3500.animator.model.IModel;
import cs3500.animator.model.Model;
import cs3500.animator.view.IView;
import cs3500.animator.view.TextView;

/**
 * tests the textual view of {@code TextView}.
 */
public class TextViewTest {

  IModel m1;
  IView v1;

  @Before
  public void setUp() {
    m1 = new Model();
    v1 = new TextView();
  }


  @Test
  public void testPrintView3() {
    assertEquals("", v1.printView(m1));
  }

  @Test
  public void testPrintView2() {
    m1.addShape("Dave", RECTANGLE);
    m1.addShape("Rich II", ELLIPSE);
    assertEquals("Rectangle Dave:\n" +
            "Ellipse Rich II:\n", v1.printView(m1));
  }

  @Test
  public void testPrintView() {
    m1.addShape("Dave", RECTANGLE);
    m1.addShape("Rich II", ELLIPSE);
    m1.fullMotion("Dave", 1, 10, 10, 10, 10, 255, 255,
            255, 5, 4, 4, 4, 4, 4, 4, 4);
    m1.fullMotion("Rich II", 4, 20, 30, 20, 2, 3, 30, 57,
            20, 300, 300, 12, 72, 200, 200, 200);
    m1.fullMotion("Dave", 5, 4, 4, 4, 4, 4, 4, 4, 7,
            76, 30, 30, 23, 199, 67, 255);
    assertEquals("Rectangle Dave:\n" +
            "motion Dave 1 10.0 10.0 10 10 255 255 255    5 4.0 4.0 4 4 4 4 4\n" +
            "motion Dave 5 4.0 4.0 4 4 4 4 4    7 76.0 30.0 30 23 199 67 255\n" +
            "Ellipse Rich II:\n" +
            "motion Rich II 4 20.0 30.0 20 2 3 30 57    20 300.0 300.0 12 72 200 200 200\n",
            v1.printView(m1));
  }

  @Test
  public void testPrintViewBasedOnWhenInstantiated() {
    m1.addShape("Dave", RECTANGLE);
    m1.addShape("Adam", ELLIPSE);
    m1.addShape("Daniel", RECTANGLE);
    m1.addShape("Vido", ELLIPSE);
    m1.fullMotion("Dave", 1, 10, 10, 10, 10, 255, 255, 255,
            5, 4, 4, 4, 4, 4, 4, 4);
    m1.fullMotion("Vido", 3, 200, 200, 1, 1, 0, 0, 0, 23,
            250, 250, 23, 34, 90, 36, 78);
    m1.fullMotion("Adam", 10, 20, 30, 20, 2, 3, 30, 57,
            30, 300, 300, 12, 72, 200, 200, 200);
    m1.fullMotion("Dave", 5, 4, 4, 4, 4, 4, 4, 4, 7,
            76, 30, 30, 23, 199, 67, 255);
    m1.fullMotion("Daniel", 5, 30, 30, 10, 10, 30, 78, 180,
            22, 100, 100, 100, 100, 0, 13, 255);
    assertEquals("Rectangle Dave:\n" +
            "motion Dave 1 10.0 10.0 10 10 255 255 255    5 4.0 4.0 4 4 4 4 4\n" +
            "motion Dave 5 4.0 4.0 4 4 4 4 4    7 76.0 30.0 30 23 199 67 255\n" +
            "Ellipse Adam:\n" +
            "motion Adam 10 20.0 30.0 20 2 3 30 57    30 300.0 300.0 12 72 200 200 200\n" +
            "Rectangle Daniel:\n" +
            "motion Daniel 5 30.0 30.0 10 10 30 78 180    22 100.0 100.0 100 100 0 13 255\n" +
            "Ellipse Vido:\n" +
            "motion Vido 3 200.0 200.0 1 1 0 0 0    23 250.0 250.0 23 34 90 36 78\n",
            v1.printView(m1));
  }

  @Test
  public void testPrintViewAlphabetically() {
    m1.addShape("Dave", RECTANGLE);
    m1.addShape("Adam", ELLIPSE);
    m1.addShape("Daniel", RECTANGLE);
    m1.addShape("Vido", ELLIPSE);
    m1.fullMotion("Dave", 3, 10, 10, 10, 10, 255, 255, 255,
            5, 4, 4, 4, 4, 4, 4, 4);
    m1.fullMotion("Vido", 3, 200, 200, 1, 1, 0, 0, 0, 23,
            250, 250, 23, 34, 90, 36, 78);
    m1.fullMotion("Adam", 3, 20, 30, 20, 2, 3, 30, 57,
            30, 300, 300, 12, 72, 200, 200, 200);
    m1.fullMotion("Daniel", 3, 30, 30, 10, 10, 30, 78, 180,
            22, 100, 100, 100, 100, 0, 13, 255);
    assertEquals("Rectangle Dave:\n" +
            "motion Dave 3 10.0 10.0 10 10 255 255 255    5 4.0 4.0 4 4 4 4 4\n" +
            "Ellipse Adam:\n" +
            "motion Adam 3 20.0 30.0 20 2 3 30 57    30 300.0 300.0 12 72 200 200 200\n" +
            "Rectangle Daniel:\n" +
            "motion Daniel 3 30.0 30.0 10 10 30 78 180    22 100.0 100.0 100 100 0 13 255\n" +
            "Ellipse Vido:\n" +
            "motion Vido 3 200.0 200.0 1 1 0 0 0    23 250.0 250.0 23 34 90 36 78\n",
            v1.printView(m1));
  }
}