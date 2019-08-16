package cs3500.animator.view;

import java.util.List;

import cs3500.animator.model.IReadOnlyAnimatedShape;
import cs3500.animator.model.IReadOnlyShapeState;

/**
 * represents an abstracted version of a vsg tag depending on the type of shape.
 */
public abstract class ASVGTag implements ISVGTag {

  protected final IReadOnlyAnimatedShape shape;
  protected int rate;

  /**
   * the constructor of this abstracted class.
   * @param shape the shape
   * @param rate the rate at which the animation moves
   */
  protected ASVGTag(IReadOnlyAnimatedShape shape, int rate) {
    this.shape = shape;
    this.rate = rate;
  }

  @Override
  public abstract String format();

  /**
   * converts the information of the states of a shape into SVG format.
   * @param states the read only states of the shape
   * @return the string svg formatted version
   */
  protected String stateConverter(List<IReadOnlyShapeState> states) {
    String result = "";
    for (int i = 0; i < states.size() - 1; i += 2) {
      if (states.get(i).getTick() != states.get(i + 1).getTick()) {
        if (states.get(i).getColor() != states.get(i + 1).getColor()) {
          result += "<animate attributesType=\"xml\" begin=\"" +
              ((double)states.get(i).getTick() / (double)this.rate * 1000) + "ms\" dur=\""
              + ((double)(states.get(i + 1).getTick() - states.get(i).getTick())
                  / (double)this.rate * 1000)
              + "ms\" attributeName=\"fill\" from=\"rgb(" +
              shape.getStates().get(i).getColor().getRed() + ","
              + shape.getStates().get(i).getColor().getGreen() + ","
              + shape.getStates().get(i).getColor().getBlue() +
              ")\" to=\"rgb("
              + shape.getStates().get(i + 1).getColor().getRed() + ","
              + shape.getStates().get(i + 1).getColor().getGreen() + ","
              + shape.getStates().get(i + 1).getColor().getBlue() + ")\" fill=\"freeze\" />\n";
        }
        result += stateConverterHelper(states, i);
      }
    }
    return result;
  }

  /**
   * accounts for all other attributes than color.
   * @param states the states of this shape
   * @param i the index in the list of states in this shape
   * @return the string format of these attributes
   */
  protected String stateConverterHelper(List<IReadOnlyShapeState> states, int i) {
    return "";
  }
}
