package cs3500.animator.view;

import java.util.List;

import cs3500.animator.model.IReadOnlyShapeState;

/**
 * represents the panel drawing of this program's animation.
 */
public interface IDrawingPanel {
  /**
   * draws the shapes given in this animation.
   *
   * @param shapes the states of shapes in this animation
   */
  void draw(List<IReadOnlyShapeState> shapes);
}
