package cs3500.animator.view;

/**
 * represents a factory to make the specific View type given.
 */
public class ViewFactory {

  /**
   * creates an instance of the requested view based on the given type.
   *
   * @param type           a String version of the type of view being requested
   * @param ticksPerSecond the speed at which this view should run.
   * @param width          the width of the animation
   * @param height         the height of the animation
   * @return an instance of the requested view
   * @throws IllegalArgumentException when given an invalid string type of a view
   */
  public static IView makeView(String type, int ticksPerSecond, int width, int height)
          throws IllegalArgumentException {
    switch (type) {
      case "text":
        return new TextView();
      case "svg":
        return new SVGView(ticksPerSecond);
      case "visual":
        return new VisualView(ticksPerSecond, width, height);
      case "edit":
        return new EditView(ticksPerSecond, width, height);
      default:
        throw new IllegalArgumentException("Must be a valid type of view.");
    }
  }
}
