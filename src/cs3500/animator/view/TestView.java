package cs3500.animator.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import cs3500.animator.model.IReadOnlyAnimatedShape;
import cs3500.animator.model.IReadOnlyModel;
import cs3500.animator.model.IReadOnlyShapeState;
import cs3500.animator.model.ShapeType;

/**
 * Represents a dummy view that tests whether the controller and model are synced up correctly with
 * the view. It takes in a readable that fires dummy events to be handled by the controller.
 */
public class TestView implements IView {
  
  List<IViewListener> listeners;
  Readable readable;
  
  /**
   * Constructs a dummy view.
   * 
   * @param readable The readable that is composed of the names of the dummy events to be fired.
   */
  public TestView(Readable readable) {
    listeners = new ArrayList<IViewListener>();
    this.readable = readable;
  }

  @Override
  public void addListener(IViewListener listener) {
    listeners.add(listener);
  }

  @Override
  public String printView(IReadOnlyModel model) {
    throw new UnsupportedOperationException("Test view tests visuals only");
  }

  @Override
  public void drawShapes(List<IReadOnlyShapeState> shapes) {
    Scanner scanner = new Scanner(readable);
    try {
      String command = scanner.next();

      for (IViewListener listener : listeners) {
        switch (command) {
          case "Play":
            listener.play();
            break;
          case "Pause":
            listener.pause();
            break;
          case "Restart":
            listener.restart();
            break;
          case "Speed":
            listener.updateSpeed(50);
            break;
          case "AddShape":
            listener.addShape("Test Success", ShapeType.RECTANGLE);
            break;
          case "RemoveShape":
            listener.removeShape("Test Success");
            break;
          case "AddKeyframe":
            listener.addKeyFrame("Test Success", 25, 0, 0, 0, 0, 0, 0, 0);
            break;
          case "EditKeyframe":
            listener.editKeyFrame("Test Success", 0, 0, 0, 0, 0, 0, 0, 0, 0);
            break;
          case "RemoveKeyframe":
            listener.removeKeyFrame("Test Success", 0);
            break;
          case "Loop":
            listener.loop(true);
            break;
          default:
            throw new IllegalArgumentException(command + " not a supported test command");
        }
      }
    } catch (Exception e) {
      // do nothing
    } 
  }

  @Override
  public void setShapesArray(ArrayList<IReadOnlyAnimatedShape> shapes) {
    throw new UnsupportedOperationException("Setting shapes not supported by this view");
  }

  @Override
  public void setKnob(int placement) {

  }
}
