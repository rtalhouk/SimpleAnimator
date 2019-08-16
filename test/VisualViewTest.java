import org.junit.Before;
import org.junit.Test;
import cs3500.animator.controller.VisualController;
import cs3500.animator.controller.IAnimatorController;
import cs3500.animator.model.IModel;
import cs3500.animator.model.Model;
import cs3500.animator.view.IView;
import cs3500.animator.view.VisualView;

/**
 * tests the visual view of {@code VisualView}.
 */
public class VisualViewTest {

  IModel m1;
  IView v1;
  IAnimatorController c1;

  @Before
  public void setUp() {
    m1 = new Model();
    v1 = new VisualView(0, 0, 0);
    c1 = new VisualController(m1, v1, 30);
  }

  @Test (expected = UnsupportedOperationException.class)
  public void testFail2() {
    v1.printView(m1);
  }
}
