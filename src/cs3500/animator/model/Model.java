package cs3500.animator.model;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import cs3500.animator.util.AnimationBuilder;

/**
 * Represents the model of this EasyAnimator. The model stores {@code AnimatedShape} objects mapped
 * to a unique string name.
 */
public final class Model implements IModel {
  private HashMap<String, IAnimatedShape> shapes;
  private int height;
  private int width;
  private int shapeCount;

  /**
   * Builds a version of this model so it can be used by the provided Animation reader and builder.
   */
  public static final class Builder implements AnimationBuilder<IModel> {
    private HashMap<String, IAnimatedShape> shapes = new HashMap<>();
    private int x = 0;
    private int y = 0;
    private int height = 0;
    private int width = 0;
    private int shapeCount = 0;

    @Override
    public IModel build() {
      return new Model(shapes, width, height);
    }

    @Override
    public AnimationBuilder<IModel> setBounds(int x, int y, int width, int height) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      return this;
    }

    @Override
    public AnimationBuilder<IModel> declareShape(String name, String type) {
      switch (type) {
        case "rectangle":
          shapes.put(name, new AnimatedShape(name, ShapeType.RECTANGLE, this.shapeCount));
          break;
        case "ellipse":
          shapes.put(name, new AnimatedShape(name, ShapeType.ELLIPSE, this.shapeCount));
          break;
        default:
          throw new IllegalArgumentException("Shape type not supported");
      }
      this.shapeCount++;
      return this;
    }

    @Override
    public AnimationBuilder<IModel> addMotion(String name, int t1, int x1, int y1, int w1, int h1,
        int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
      if (!shapes.containsKey(name)) {
        throw new IllegalArgumentException("Invalid name");
      }
      shapes.get(name).fullMotion(t1, x1 - this.x, y1 - this.y, w1, h1, r1, g1, b1,
          t2, x2 - this.x, y2 - this.y, w2, h2, r2, g2, b2);
      return this;
    }

    @Override
    public AnimationBuilder<IModel> addKeyframe(String name, int t, int x, int y, int w, int h,
        int r, int g, int b) {
      return this;
    }

  }

  /**
   * Constructs a default model.
   */
  public Model() {
    this(null, 500, 500);
  }

  /**
   * Constructs a model, but a list of Shapes can be given when initialized.
   *
   * @param shapes list of shapes added when instantiating this model
   */
  public Model(HashMap<String, IAnimatedShape> shapes, int width, int height) {
    if (shapes == null) {
      this.shapes = new HashMap<String, IAnimatedShape>();
    } else {
      this.shapes = shapes;
    }

    this.width = width;
    this.height = height;
  }

  @Override
  public void removeShape(String name) throws IllegalArgumentException {
    if (shapes.containsKey(name)) {
      shapes.remove(name);
      shapeCount--;
    }
    else {
      throw new IllegalArgumentException("This is not a valid name or " +
          "is not a current shape's name.");
    }
  }

  @Override
  public void fullMotionTo(String name, int duration, double endX,
      double endY, int endHeight, int endWidth,
      int endRed, int endGreen, int endBlue) {
    IAnimatedShape shape = this.shapes.getOrDefault(name, null);
    if (shape == null) {
      throw new IllegalArgumentException("Shape with name " + name + " does not exist");
    } else {
      shape.fullMotionTo(
          new Point2D.Double(endX, endY), endHeight, endWidth,
          new Color(endRed, endGreen, endBlue), duration);
    }
    this.height = (int) Math.max(endY, this.height);
    this.width = (int) Math.max(endX, this.width);
  }


  @Override
  public void addShape(String name, ShapeType type) throws IllegalArgumentException {
    if (this.shapes.containsKey(name) || type == null || name == null || name.equals("")) {
      throw new IllegalArgumentException("Shape names must be unique and non-null.");
    }
    switch (type) {
      case RECTANGLE:
        shapes.put(name, new AnimatedShape(name, type, this.shapeCount));
        break;
      case ELLIPSE:
        shapes.put(name, new AnimatedShape(name, type, this.shapeCount));
        break;
      default:
        shapes.put(name, null);
    }
    this.shapeCount++;
  }


  @Override
  public void changeColorTo(String name, int red, int green, int blue, int duration)
      throws IllegalArgumentException {
    IAnimatedShape shape = this.shapes.getOrDefault(name, null);
    if (shape == null) {
      throw new IllegalArgumentException("Shape with name " + name + " does not exist");
    } else {
      shape.changeColor(new Color(red, green, blue), duration);
    }

  }


  @Override
  public void moveTo(String name, double x, double y, int duration) {
    IAnimatedShape shape = this.shapes.getOrDefault(name, null);
    if (shape == null) {
      throw new IllegalArgumentException("Shape with name " + name + " does not exist");
    } else {
      shape.moveTo(new Point2D.Double(x, y), duration);
    }

    this.height = (int) Math.max(x, this.height);
    this.width = (int) Math.max(y, this.width);
  }


  @Override
  public void changeSizeTo(String name, int newHeight, int newWidth, int duration)
      throws IllegalArgumentException {
    IAnimatedShape shape = this.shapes.getOrDefault(name, null);
    if (shape == null) {
      throw new IllegalArgumentException("Shape with name " + name + " does not exist");
    } else {
      shape.changeSizeTo(newHeight, newWidth, duration);
    }
  }


  @Override
  public List<IReadOnlyShapeState> getShapesAtTick(int tick) {
    if (tick < 0) {
      throw new IllegalArgumentException("The tick must be a positive number.");
    } else {
      List<IReadOnlyShapeState> shapesAtTick = new ArrayList<IReadOnlyShapeState>();
      List<IAnimatedShape> sortedShapes = new ArrayList<IAnimatedShape>(this.shapes.values());
      Collections.sort(sortedShapes);
      
      for (IAnimatedShape shape : sortedShapes) {
        try {
          IReadOnlyShapeState tickShape = shape.getShapeAt(tick);
          shapesAtTick.add(tickShape);
        } catch (IllegalArgumentException e) {
          // ignore if a certain shape doesnt have this tick
        }
      }
      return shapesAtTick;
    }
  }

  @Override
  public int getMaxTick() {
    int curMax = 0;
    for (IAnimatedShape shape : this.shapes.values()) {
      for (IReadOnlyShapeState state : shape.getStates()) {
        curMax = Math.max(curMax, state.getTick());
      }
    }
    return curMax;
  }

  @Override
  public String getShape(String name) throws IllegalArgumentException {
    if (shapes.containsKey(name)) {
      return shapes.get(name).getMotions();
    } else {
      throw new IllegalArgumentException("This name does not exist in the current shapes");
    }
  }

  @Override
  public void doNothing(String name, int duration) throws IllegalArgumentException {
    if (!shapes.containsKey(name)) {
      throw new IllegalArgumentException("Invalid name");
    }
    shapes.get(name).addDoNothing(duration);
  }

  @Override
  public IReadOnlyAnimatedShape getShapeObject(String name) {
    if (shapes.containsKey(name)) {
      return shapes.get(name).deepCopy();
    } else {
      throw new IllegalArgumentException("This name does not exist in the current shapes "
              + name + shapes.size());
    }
  }

  @Override
  public void fullMotion(String name, int t1, int x1, int y1, int w1, int h1, int r1, int g1,
      int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
    if (!shapes.containsKey(name)) {
      throw new IllegalArgumentException("Invalid name");
    }
    shapes.get(name).fullMotion(t1, x1, y1, w1, h1, r1, g1, b1, t2, x2, y2, w2, h2, r2, g2, b2);

    this.height = (int) Math.max(x2, this.height);
    this.width = (int) Math.max(y2, this.width);
  }

  @Override
  public ArrayList<IReadOnlyAnimatedShape> getShapes() {
    ArrayList<IReadOnlyAnimatedShape> result = new ArrayList<IReadOnlyAnimatedShape>();
    for (IAnimatedShape shape : this.shapes.values()) {
      result.add(shape.deepCopy());
    }
    Collections.sort((List) result);
    return result;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public void removeKeyFrame(String id, int index) {
    if (!shapes.containsKey(id)) {
      throw new IllegalArgumentException("Invalid name" + id);
    }
    else {
      shapes.get(id).removeKeyFrame(index);
    }
  }

  @Override
  public void editKeyFrame(String id, int index, int tick, double x, double y,
      int width, int height, int r, int g, int b) {
    if (!shapes.containsKey(id)) {
      throw new IllegalArgumentException("Invalid name");
    }
    else {
      shapes.get(id).editKeyFrame(index, tick, x, y, width, height, r, g, b);
    } 
  }

  @Override
  public void addKeyFrame(
      String id, int tick, double x, double y, int width, int height, int r, int g, int b) {
    if (!shapes.containsKey(id)) {
      throw new IllegalArgumentException("Invalid name");
    }
    else {
      shapes.get(id).addKeyFrame(tick, x, y, width, height, r, g, b);
    }  
  }

  @Override
  public void changeLayer(String id, int newLayer) {
    if (shapes.containsKey(id)) {
      shapes.get(id).setLayer(newLayer);
    }
    else {
      throw new IllegalArgumentException("This is not a valid name or " +
          "is not a current shape's name.");
    }
    
  }

}

