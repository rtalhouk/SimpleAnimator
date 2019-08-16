package cs3500.animator.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a read only version of the IShapeState interface.
 */
public interface IReadOnlyAnimatedShape {

  /**
   * Getter for the list of states of this Animated Shape. Used primarily for testing purposes.
   *
   * @return List of shape states.
   */
  List<IReadOnlyShapeState> getStates();

  /**
   * Retrieves all the states associated with this shape.
   *
   * @return the motions associated with this animated shape
   */
  String getMotions();

  /**
   * Based on the added motions of this animated shape, this method will return the read only
   * version of the shape state that represents the animated shape at the given tick.
   *
   * @param tick the tick at which the Animated shape should be represented
   * @return the shape at the given tick
   * @throws IllegalArgumentException when given a tick that does not exist for this shape
   */
  IReadOnlyShapeState getShapeAt(int tick) throws IllegalArgumentException;

  /**
   * Returns the enum representing the type of this Animated Shape.
   * @return correct ShapeType enum
   */
  ShapeType getType();

  /**
   * Retrieves the String name of this shape, which is determined by the user.
   * @return the unique identifier of this shape.
   */
  String getName();

  /**
   * retrieves the states of this shape as an array of strings.
   * @return the states in string format
   */
  ArrayList<String> getStatesStringArray();
  
  /**
   * Retrieves the layer of this read-only shape
   * 
   * @return an integer layer
   */
  int getLayer();
}
