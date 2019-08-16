package cs3500.animator.view;

import cs3500.animator.model.IReadOnlyAnimatedShape;
import cs3500.animator.model.IReadOnlyShapeState;
import cs3500.animator.model.ShapeType;

import java.util.List;

/**
 * Represents an object that listens to an {@code IView} and calls methods based on
 * what event was fired.
 */
public interface IViewListener {
  /**
   * This method pauses the animation at its current tick.
   */
  void pause();

  /**
   * This method plays the animation from its current tick.
   */
  void play();

  /**
   * This method restarts the animation from tick 0.
   */
  void restart();

  /**
   * This method updates the speed of the animation to the given ticks per second.
   * @param newTPS the speed to which the animation will be updated.
   */
  void updateSpeed(int newTPS);

  /**
   * Adds a shape to the animation with this given ID and ShapeType.
   * 
   * @param id the unique ID this shape will be given
   * @param shapeType the type of shape the new one will be
   */
  void addShape(String id, ShapeType shapeType);

  /**
   * Removes the shape with the given ID from the animation.
   * 
   * @param id the ID of the shape to be removed
   */
  void removeShape(String id);

  /**
   * Loops or de-loops the animation based on the given boolean value.
   * 
   * @param value true will loop, false will de-loop
   */
  void loop(boolean value);

  /**
   * Adds a key frame to the shape with the given ID at the given tick.
   * 
   * @param id the ID of the shape to which the key frame will be added
   * @param tick the tick of the key frame
   * @param x the x position of the shape at the given tick
   * @param y the y position of the shape at the given tick
   * @param width the width of the shape at the given tick
   * @param height the height of the shape at the given tick
   * @param r the red RBG value of the shape at the given tick
   * @param g the green RBG value of the shape at the given tick
   * @param b the blue RBG value of the shape at the given tick
   */
  void addKeyFrame(
      String id, int tick, double x, double y, int width, int height, int r, int g, int b);

  /**
   * Removes the index-th key frame from the shape with the given ID. For example, if the index was
   * 0, the first key frame of the shape would be removed.
   * 
   * @param id the ID of the shape from which the key frame is removed
   * @param index indicates which key frame of the shape will be removed
   */
  void removeKeyFrame(String id, int index);

  /**
   * Edits the index-th key frame in the shape with the given id. It sets that object's fields to
   * the given parameters.
   * 
   * @param id the ID of the shape whose key frame will be edited
   * @param index indicate which key frame to edit
   * @param tick the new tick that the key frame occurs
   * @param x the new x position of the shape
   * @param y the new y position of the shape
   * @param width the new width of the shape
   * @param height the new height of the shape
   * @param r the new red RGB value of the shape
   * @param g the new green RGB value of the shape
   * @param b the new blue RBG value of the shape
   */
  void editKeyFrame(String id, int index, int tick, double x, double y,
      int width, int height, int r, int g, int b);

  /**
   * Gets shape in the model with the given ID.
   * @param id the id of the shape to be return 
   * @return read only animated shape object with the given id
   */
  IReadOnlyAnimatedShape getShape(String id);

  /**
   * gets the maximum tick in the whole animation.
   * @return the maximum tick
   */
  int getMaxTick();

  /**
   * changes the tick in the animation to the given one.
   * @param tick the tick specified by the user
   */
  void changeTickTo(int tick);

  /**
   * retrieves the current tick of the animation.
   * @return the value of the tick
   */
  int getCurrentTick();
  /**
   * retrieves the state of each shape in the animation at the given tick.
   * @param tick the tick of which is called
   * @return the states of shapes at the given tick
   */
  List<IReadOnlyShapeState> getShapesAtTick(int tick);

  /** 
   * changes the layer of the given shape to the given layer value.
   * @param id the unique name of the shape
   * @param newLayer the new layer value of the shape in the animation
   */
  void changeLayer(String id, int newLayer);
}
