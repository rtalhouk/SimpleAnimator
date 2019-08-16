package cs3500.animator.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Represents a dummy model to ensure that the controller is calling methods in the model correctly.
 * Instead of mutating, the dummy model appends to an appendable to which expected values will be
 * compared.
 */
public class TestModel implements IModel {
  private final Appendable appendable;

  /**
   * Constructs a dummy model.
   * 
   * @param appendable the appendable to which the tests will be compared.
   */
  public TestModel(Appendable appendable) {
    this.appendable = appendable;
  }

  @Override
  public void addShape(String id, ShapeType type) throws IllegalArgumentException {
    try {
      appendable.append("Shape " + id + " added into model");
    } catch (IOException e) {
      System.err.println("Model appendable couldn't output addShape");
    }
  }

  @Override
  public List<IReadOnlyShapeState> getShapesAtTick(int tick) {
    try {
      appendable.append("Getting shapes at " + tick);
    } catch (IOException e) {
      System.err.println("Model appendable couldn't output getShapesAtTick");
    }
    return null;
  }

  @Override
  public String getShape(String name) throws IllegalArgumentException {
    try {
      appendable.append("Getting shape named " + name);
    } catch (IOException e) {
      System.err.println("Model appendable couldn't output getShape");
    }
    return null;
  }

  @Override
  public IReadOnlyAnimatedShape getShapeObject(String name) throws IllegalArgumentException {
    try {
      appendable.append("Getting shape named " + name);
    } catch (IOException e) {
      System.err.println("Model appendable couldn't output getShapeObject");
    }
    return null;
  }

  @Override
  public ArrayList<IReadOnlyAnimatedShape> getShapes() {
    try {
      appendable.append("Getting shapes");
    } catch (IOException e) {
      System.err.println("Model appendable couldn't output getShapes");
    }
    return null;
  }

  @Override
  public int getWidth() {
    try {
      appendable.append("Getting width");
    } catch (IOException e) {
      System.err.println("Model appendable couldn't output getWidth");
    }
    return 0;
  }

  @Override
  public int getHeight() {
    try {
      appendable.append("Getting model height");
    } catch (IOException e) {
      System.err.println("Model appendable couldn't output getHeight");
    }
    return 0;
  }

  @Override
  public void removeShape(String name) throws IllegalArgumentException {
    try {
      appendable.append("Shape " + name + " removed from model");
    } catch (IOException e) {
      System.err.println("Model appendable couldn't output removeShape");
    }
  }

  @Override
  public void changeColorTo(String name, int red, int green, int blue, int duration)
      throws IllegalArgumentException {
    try {
      appendable.append("Change color of " + name + " in model");
    } catch (IOException e) {
      System.err.println("Model appendable couldn't output changeColorTo");
    }

  }

  @Override
  public void moveTo(String name, double x, double y, int duration) {
    try {
      appendable.append("Change color of " + name + " in model");
    } catch (IOException e) {
      System.err.println("Model appendable couldn't output changeColorTo");
    }
  }

  @Override
  public void changeSizeTo(String name, int newHeight, int newWidth, int duration)
      throws IllegalArgumentException {
    try {
      appendable.append("Change size of " + name + " in model");
    } catch (IOException e) {
      System.err.println("Model appendable couldn't output changeSizeTo");
    }
  }

  @Override
  public void fullMotionTo(String name, int duration, double endX, double endY, int endHeight,
      int endWidth, int endRed, int endGreen, int endBlue) {
    try {
      appendable.append("Full motion to of " + name + " in model");
    } catch (IOException e) {
      System.err.println("Model appendable couldn't output fullMotionTo");
    }
  }

  @Override
  public void doNothing(String name, int duration) throws IllegalArgumentException {
    try {
      appendable.append("Do nothing added to " + name + " in model");
    } catch (IOException e) {
      System.err.println("Model appendable couldn't output doNothing");
    }
  }

  @Override
  public void fullMotion(String name, int t1, int x1, int y1, int w1, int h1, int r1, int g1,
      int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
    try {
      appendable.append("Full motion added to " + name + " in model");
    } catch (IOException e) {
      System.err.println("Model appendable couldn't output fullMotion");
    }
  }

  @Override
  public void removeKeyFrame(String id, int index) {
    try {
      appendable.append("Key frame removed from " + id + " in model");
    } catch (IOException e) {
      System.err.println("Model appendable couldn't output removeKeyFrame");
    }
  }

  @Override
  public void editKeyFrame(String id, int index, int tick, double x, double y, int width,
      int height, int r, int g, int b) {
    try {
      appendable.append("Key frame edited in " + id + " in model");
    } catch (IOException e) {
      System.err.println("Model appendable couldn't output editKeyFrame");
    }
  }

  @Override
  public void addKeyFrame(
      String id, int tick, double x, double y, int width, int height, int r, int g, int b) {
    try {
      appendable.append("Key frame added to " + id + " in model");
    } catch (IOException e) {
      System.err.println("Model appendable couldn't output addKeyFrame");
    }
  }

  @Override
  public int getMaxTick() {
    return 0;
  }

  @Override
  public void changeLayer(String id, int newLayer) {
    try {
      appendable.append("Shape " + id + " layer changed to " + newLayer);
    } catch (IOException e) {
      System.err.println("Model appendable couldn't output changeLayer");
    }
    
  }
}
