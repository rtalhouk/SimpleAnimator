package cs3500.animator.model;

import java.awt.geom.Point2D;

import static cs3500.animator.model.ShapeType.RECTANGLE;

/**
 * Represents a state of a Rectangle in an animated shape. This class is used to represent the state
 * of a moving shape at a given tick and are used in the animated shape class to serve as end points
 * for the added motions.
 */
public class RectangleState extends AShapeState {

  /**
   * Public constructor for a {@code RectangleState} object. It relies on the constructor for the
   * super abstract shape class.
   *
   * @param tick     the tick at which this state exists
   * @param width    the width of this shape state
   * @param height   the height of this shape state
   * @param color    the color of this shape state
   * @param position the position of this shape state
   */
  public RectangleState(int tick, int width, int height, Color color, Point2D position) {
    super(tick, width, height, color, position);
  }

  @Override
  public IShapeState deepCopy() {
    return new RectangleState(
            this.tick, this.width, this.height,
            new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue()),
            new Point2D.Double(this.position.getX(), this.position.getY()));
  }

  @Override
  public ShapeType getType() {
    return RECTANGLE;
  }


}
