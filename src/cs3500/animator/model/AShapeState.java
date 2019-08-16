package cs3500.animator.model;

import java.awt.geom.Point2D;


/**
 * Represents the abstracted instantaneous version of any supported shape.
 */

public abstract class AShapeState implements IShapeState {
  // INVARIANT: tick is a positive number > 0
  protected int tick;
  // INVARIANT: width is a positive number > 0
  protected int width;
  // INVARIANT: height is a positive number > 0
  protected int height;
  protected Color color;
  protected Point2D position;

  /**
   * Serves as a common constructor for all subclasses of an abstract shape state.
   *
   * @param tick     the tick at which this state exists
   * @param width    the width of the shape in this state
   * @param height   the the height of the shape in this state
   * @param color    the color of the shape in this state
   * @param position the position of the shape in this state
   */
  public AShapeState(
          int tick, int width, int height, Color color, Point2D position) {
    if (tick < 0 || width < 0 || height < 0 || position == null) {
      throw new IllegalArgumentException("Tick, width, and height must be non-negative");
    }
    this.tick = tick;
    this.width = width;
    this.height = height;
    this.color = color;
    this.position = position;
  }

  /**
   * gets the shape type of this state of the shape.
   *
   * @return the shape's type
   */
  public abstract ShapeType getType();

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public Color getColor() {
    return new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
  }

  @Override
  public Point2D getPosition() {
    return new Point2D.Double(this.position.getX(), this.position.getY());
  }

  @Override
  public int getTick() {
    return this.tick;
  }

  @Override
  public String toString() {
    return "" + this.tick + " "
            + this.position.getX() + " "
            + this.position.getY() + " "
            + this.width + " "
            + this.height + " "
            + this.color.getRed() + " "
            + this.color.getGreen() + " "
            + this.color.getBlue();
  }

  @Override
  public abstract IShapeState deepCopy();

  @Override
  public int compareTo(IShapeState shape) {
    if (this.tick < shape.getTick()) {
      return -1;
    } else if (this.tick > shape.getTick()) {
      return 1;
    } else {
      return 0;
    }
  }

  @Override
  public void setWidth(int value) {
    this.width = value;
  }

  @Override
  public void setHeight(int value) {
    this.height = value;
  }

  @Override
  public void setColor(int r, int g, int b) {
    this.color = new Color(r, g, b);
  }

  @Override
  public void setPosition(double x, double y) {
    this.position = new Point2D.Double(x, y);
  }
  
  @Override
  public void setTick(int value) {
    this.tick = value;
  }

}
