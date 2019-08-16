package cs3500.animator.view;

import java.util.List;

import javax.swing.JPanel;

import cs3500.animator.model.IReadOnlyShapeState;

/**
 * represents the abstracted version of a shape's drawing panel.
 */
public class ADrawingPanel extends JPanel implements IDrawingPanel {
  List<IReadOnlyShapeState> shapes = null;

  public ADrawingPanel() {
    super();
  }

  @Override
  public void draw(List<IReadOnlyShapeState> shapes) {
    this.shapes = shapes;
    repaint();
  }
}
