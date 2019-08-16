package cs3500.animator.view;

import java.util.ArrayList;
import java.util.List;
import cs3500.animator.model.IReadOnlyAnimatedShape;
import cs3500.animator.model.IReadOnlyModel;
import cs3500.animator.model.IReadOnlyShapeState;

/**
 * Represents the view for an animator program.
 */
public interface IView {

  /**
   * prints the motions of each shape in the animation in either TextView or SVGView.
   *
   * @param model a Read Only version of the model
   * @return a String description of the shapes and their motions
   */
  String printView(IReadOnlyModel model);


  /**
   * Renders all the shapes in the given list.
   * 
   * @param shapes the list of shapes to be drawn in the view
   */
  void drawShapes(List<IReadOnlyShapeState> shapes);

  /**
   * Adds a listener to this view that handles when events are fired by the view.
   * 
   * @param listener the listener to be added
   */
  void addListener(IViewListener listener);

  /**
   * sets the list of read only shapes in the view.
   * @param shapes the shapes in the animation
   */
  void setShapesArray(ArrayList<IReadOnlyAnimatedShape> shapes);

  /**
   * instructs knob of position along the slider.
   * @param placement the integer value along the slider
   */
  void setKnob(int placement);
}
