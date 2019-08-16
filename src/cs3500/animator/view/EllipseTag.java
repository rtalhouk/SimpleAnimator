package cs3500.animator.view;

import java.util.List;

import cs3500.animator.model.IReadOnlyAnimatedShape;
import cs3500.animator.model.IReadOnlyShapeState;

/**
 * represents the svg format specifically for shapes of the type ellipse.
 */
public class EllipseTag extends ASVGTag {

  public EllipseTag(IReadOnlyAnimatedShape shape, int rate) {
    super(shape, rate);
  }

  @Override
  public String format() {
    String result = "";
    if (!shape.getStates().isEmpty()) {
      result += "<ellipse id=\""
              + shape.getName() + "\" cx=\"" + shape.getStates().get(0).getPosition().getX()
              + "\"  cy=\"" + shape.getStates().get(0).getPosition().getY() + "\" rx=\""
              + shape.getStates().get(0).getWidth() / 2 + "\" ry=\""
              + shape.getStates().get(0).getHeight() / 2 + "\" fill=\"rgb("
              + shape.getStates().get(0).getColor().getRed() + ","
              + shape.getStates().get(0).getColor().getGreen() + ","
              + shape.getStates().get(0).getColor().getBlue() + ")\" visibility=\"visible\" >\n";
      result += stateConverter(shape.getStates());
    } else {
      result += "<ellipse id=\"" + shape.getName() + "\">\n";
    }
    result += "</ellipse>\n";
    return result;
  }

  @Override
  protected String stateConverterHelper(List<IReadOnlyShapeState> states, int i) {
    String result = "";
    if (states.get(i).getPosition().getX() != states.get(i + 1).getPosition().getX()) {
      result += "<animate attributesType=\"xml\" begin=\"" +
              ((double)states.get(i).getTick() / (double)this.rate * 1000) + "ms\" dur=\""
              + (((double)states.get(i + 1).getTick() - (double)states.get(i).getTick())
                  / (double)this.rate * 1000)
              + "ms\" attributeName=\"cx\" from=\"" + states.get(i).getPosition().getX()
              + "\" to=\"" + states.get(i + 1).getPosition().getX() + "\" fill=\"freeze\" />\n";
    }
    if (states.get(i).getPosition().getY() != states.get(i + 1).getPosition().getY()) {
      result += "<animate attributesType=\"xml\" begin=\"" +
              ((double)states.get(i).getTick() / (double)this.rate * 1000) + "ms\" dur=\""
              + (((double)states.get(i + 1).getTick() - states.get(i).getTick())
                  / (double)this.rate * 1000)
              + "ms\" attributeName=\"cy\" from=\"" + states.get(i).getPosition().getY()
              + "\" to=\"" + states.get(i + 1).getPosition().getY() + "\" fill=\"freeze\" />\n";
    }
    if (states.get(i).getWidth() != states.get(i + 1).getWidth()) {
      result += "<animate attributesType=\"xml\" begin=\"" +
              ((double)states.get(i).getTick() / (double)this.rate * 1000) + "ms\" dur=\""
              + (((double)states.get(i + 1).getTick() - (double)states.get(i).getTick())
                  / (double)this.rate * 1000)
              + "ms\" attributeName=\"rx\" from=\"" + states.get(i).getWidth() / 2
              + "\" to=\"" + states.get(i + 1).getWidth() / 2 + "\" fill=\"freeze\" />\n";
    }
    if (states.get(i).getHeight() != states.get(i + 1).getHeight()) {
      result += "<animate attributesType=\"xml\" begin=\"" +
              ((double)states.get(i).getTick() / (double)this.rate * 1000) + "ms\" dur=\""
              + (((double)states.get(i + 1).getTick() - states.get(i).getTick())
                  / (double)this.rate * 1000)
              + "ms\" attributeName=\"ry\" from=\"" + states.get(i).getHeight() / 2
              + "\" to=\"" + states.get(i + 1).getHeight() / 2 + "\" fill=\"freeze\" />\n";
    }
    return result;
  }

}
