package cs3500.animator.model;

/**
 * Represents an instantaneous state of a shape.
 */
public interface IShapeState extends IReadOnlyShapeState, Comparable<IShapeState> {

  /**
   * Overrides the java-provided toString method. This method returns all the methods fields/
   * attributes as strings.
   *
   * @return tick, x position, y position, width, height, red, green, blue as string values.
   */
  String toString();

  /**
   * returns a new copy of the object instead of a pointer to its memory location in order to avoid
   * mid-animation outside changes by other users.
   *
   * @return a complete copy of a shape's state
   */
  IShapeState deepCopy();

  /**
   * sets the width to the given value.
   * @param value the new width of the shape
   */
  void setWidth(int value);

  /**
   * sets the height to the given value.
   * @param value the new height of the shape
   */
  void setHeight(int value);

  /**
   * sets the color to the given red, green, and blue values.
   * @param r the red color value
   * @param g the green color value
   * @param b the blue color value
   */
  void setColor(int r, int g, int b);

  /**
   * sets the position of the shape to the x and y coordinates.
   * @param x the new x coordinate
   * @param y the new y coordinate
   */
  void setPosition(double x, double y);

  /**
   * Sets the tick of this shape state to the given value.
   * @param tick the new tick of this Shape State
   */
  void setTick(int tick);
}
