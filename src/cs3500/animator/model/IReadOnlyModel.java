package cs3500.animator.model;

import java.util.ArrayList;
import java.util.List;

/**
 * represents an immutable model to intentionally ensure users cannot mutate anything.
 */
public interface IReadOnlyModel {

  /**
   * retrieves all shapes at a certain tick.
   *
   * @param tick the tick at which shapes are retrieves
   * @return the list of readable shapes at the given tick
   */
  List<IReadOnlyShapeState> getShapesAtTick(int tick);

  /**
   * helps for testing by allowing shapes to be retrieved.
   *
   * @param name the unique identifier of a shape
   * @return the shape
   * @throws IllegalArgumentException when the name is not a current shape
   */
  String getShape(String name) throws IllegalArgumentException;

  /**
   * returns the object version of an snimsted shape.
   *
   * @param name the unique identifier of a shape
   * @return the animated shape with the given name
   * @throws IllegalArgumentException when the name is invalid
   */
  IReadOnlyAnimatedShape getShapeObject(String name) throws IllegalArgumentException;

  /**
   * creates a list of all the animated shapes in the animation.
   *
   * @return a list of Read Only animated shapes
   */
  ArrayList<IReadOnlyAnimatedShape> getShapes();

  /**
   * Gets the width of the animation as an integer.
   * @return the width of the animation
   */
  int getWidth();

  /**
   * Gets the height of the animation as an integer.
   * @return the height of the animation
   */
  int getHeight();
}
