package cs3500.animator.view;

import java.awt.Graphics;
import java.awt.Color;
import cs3500.animator.model.IReadOnlyShapeState;

/**
 * represents a panel in the animation of this program.
 */
public class DrawingPanel extends ADrawingPanel {

  public DrawingPanel() {
    super();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (shapes != null) {
      for (IReadOnlyShapeState shape : shapes) {
        float[] hsbColor = shape.getColor().getHSB();
        g.setColor(Color.getHSBColor(hsbColor[0], hsbColor[1], hsbColor[2]));
        switch (shape.getType()) {
          case RECTANGLE:
            g.fillRect(
                    (int) shape.getPosition().getX(), (int) shape.getPosition().getY(),
                    shape.getWidth(), shape.getHeight());
            break;
          case ELLIPSE:
            g.fillOval((int) shape.getPosition().getX(), (int) shape.getPosition().getY(),
                    shape.getWidth(), shape.getHeight());
            break;
          default:
            throw new IllegalArgumentException(
                    "Enum has been updated without support in DrawingPanel");
        }
      }
    }
  }
}
