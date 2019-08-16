package cs3500.animator.controller;

import java.io.IOException;
import java.util.List;

import cs3500.animator.model.IReadOnlyAnimatedShape;
import cs3500.animator.model.IReadOnlyShapeState;
import cs3500.animator.model.ShapeType;
import cs3500.animator.view.IView;
import cs3500.animator.view.IViewListener;

/**
 * Represents a dummy controller used only for testing. It has an Appendable that each of the
 * methods appends to.
 */
public class TestController implements IAnimatorController, IViewListener {
  private final Appendable appendable;

  /**
   * Constructs a dummy controller with the given view and Appendable.
   * 
   * @param view the view from which events will be fired
   * @param appendable the appendable on which the tests will be compared.
   */
  public TestController(IView view, Appendable appendable) {
    this.appendable = appendable;
  }

  @Override
  public void play() {
    try {
      appendable.append("play");
    } catch (IOException e) {
      System.err.println("Appendable could not output play");
    }
  }

  @Override
  public void removeShape(String id) {
    try {
      appendable.append(id + " shape removed");
    } catch (IOException e) {
      System.err.println("Appendable could not output pause");
    }
  }

  @Override
  public void loop(boolean value) {
    try {
      appendable.append("loop");
    } catch (IOException e) {
      System.err.println("Appendable could not output loop");
    }
  }

  @Override
  public void pause() {
    try {
      appendable.append("pause");
    } catch (IOException e) {
      System.err.println("Appendable could not output pause");
    }
  }

  @Override
  public void restart() {
    try {
      appendable.append("restart");
    } catch (IOException e) {
      System.err.println("Appendable could not output restart");
    }

  }

  @Override
  public void updateSpeed(int newTPS) {
    try {
      appendable.append("new speed: " + newTPS);
    } catch (IOException e) {
      System.err.println("Appendable could not output updateSpeed");
    }
  }

  @Override
  public void addShape(String id, ShapeType shapeType) {
    try {
      appendable.append("new shape: " + id);
    } catch (IOException e) {
      System.err.println("Appendable could not output addShape");
    }
  }

  @Override
  public void addKeyFrame(
      String id, int tick, double x, double y, int width, int height, int r, int g, int b) {
    try {
      appendable.append("new key frame to shape " + id + " at tick " + tick);
    } catch (IOException e) {
      System.err.println("Appendable could not output addKeyFrame");
    }
  }

  @Override
  public void removeKeyFrame(String id, int index) {
    try {
      appendable.append("key frame removed from shape " + id);
    } catch (IOException e) {
      System.err.println("Appendable could not output removeKeyFrame");
    }
  }

  @Override
  public void editKeyFrame(String id, int index, int tick, double x, double y, int width,
      int height, int r, int g, int b) {
    try {
      appendable.append("key frame edited for shape " + id);
    } catch (IOException e) {
      System.err.println("Appendable could not output editKeyFrame");
    }
  }

  @Override
  public IReadOnlyAnimatedShape getShape(String id) {
    try {
      appendable.append("Gotten shape " + id);
    } catch (IOException e) {
      System.err.println("Appendable could not output getShape");
    }
    return null;
  }

  @Override
  public int getMaxTick() {
    try {
      appendable.append("Gotten maximum tick " + 0);
    } catch (IOException e) {
      System.err.println("Appendable could not output getMaxTick");
    }
    return 0;
  }

  @Override
  public void changeTickTo(int tick) {
    try {
      appendable.append("The tick has been changed to " + tick);
    } catch (IOException e) {
      System.err.println("Appendable could not output changeTickTo");
    }
  }

  @Override
  public int getCurrentTick() {
    try {
      appendable.append("GCurrent tick is " + 0);
    } catch (IOException e) {
      System.err.println("Appendable could not output getCurrentTick");
    }
    return 0;
  }

  @Override
  public List<IReadOnlyShapeState> getShapesAtTick(int tick) {
    try {
      appendable.append("Shapes have been retrieved at tick: " + tick);
    } catch (IOException e) {
      System.err.println("Appendable could not output getShapesAtTick");
    }
    return null;
  }

  @Override
  public void changeLayer(String id, int newLayer) {
    try {
      appendable.append("Layer changed for shape " + id + " to " + newLayer);
    } catch (IOException e) {
      System.err.println("Appendable could not output changeLayer");
    }
    
  }
}
