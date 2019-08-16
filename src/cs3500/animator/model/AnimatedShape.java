package cs3500.animator.model;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents an animated shape over time. It is made up of an initial shape state and a list of
 * subsequent shape states added by the MotionAdder over time
 */
public class AnimatedShape implements IAnimatedShape {
  private final String name;
  // any type of subsequent shapes
  private final ShapeType type;
  // a list of the shapes in its different states
  private ArrayList<IShapeState> states;
  // Represents the order this shape should be layered with other shapes
  private int order;

  private IShapeState curState;


  /**
   * Public constructor that allows for the creation of an {@code AnimatedShape} object while
   * specifying all its fields.
   *
   * @param name   the shape's unique identifier
   * @param type   the type of the shape
   * @param states A list of {@code IShapeState} objects representing end points of motions
   */
  public AnimatedShape(String name, ShapeType type, ArrayList<IShapeState> states, int order) {
    if (type == null || states == null || name == null) {
      throw new IllegalArgumentException("Cannot construct " +
          "animated shape with null type or null states.");
    } else {
      this.name = name;
      this.type = type;
      this.states = states;
      this.order = order;
      switch (type) {
        case RECTANGLE:
          this.curState = new RectangleState(0, 0, 0,new Color(0, 0, 0),
                  new Point2D.Double(0, 0));
          break;
        case ELLIPSE:
          this.curState = new EllipseState(0, 0, 0,new Color(0, 0, 0),
                  new Point2D.Double(0, 0));
          break;
        default:
          throw new IllegalArgumentException(
              "New enum needs support in Animated Shape constructor");
      }
    }
  }

  /**
   * Public constructor that allows for the creation of an {@code AnimatedShape} object at its
   * initial state, with no movements added.
   *
   * @param name its unique identifier
   * @param type the type of shape this is
   */
  public AnimatedShape(String name, ShapeType type, int order) {
    this(name, type, new ArrayList<IShapeState>(), order);
  }


  @Override
  public int compareTo(IAnimatedShape o) {
    if (this.getOrder() < o.getOrder()) {
      return -1;
    }
    else if (this.getOrder() > o.getOrder()) {
      return 1;
    }
    else if (this.getOrder() == o.getOrder()) {
      return 0;
    }
    else {
      return name.compareTo(o.getName());
    }

  }

  /**
   * Serves as something of a builder for motions. This way, anyone using or extending this
   * implementation can very easily personalize the kinds of motions they want to create, while
   * having access to the most recent state of this animated shape.
   */
  private class MotionAdder {
    private IReadOnlyShapeState mostRecentShape;
    private int startWidth;
    private int startHeight;
    private int endWidth;
    private int endHeight;
    private Point2D startPos;
    private Point2D endPos;
    private Color startColor;
    private Color endColor;
    private int startTick;
    private int endTick;

    private MotionAdder() {
      if (AnimatedShape.this.states.isEmpty()) {
        this.mostRecentShape = null;
        this.startWidth = 0;
        this.startHeight = 0;
        this.endWidth = 0;
        this.endHeight = 0;
        this.startPos = new Point2D.Double(0, 0);
        this.endPos = new Point2D.Double(0, 0);
        this.startColor = new Color(0, 0, 0);
        this.endColor = new Color(0, 0, 0);
        this.startTick = 0;
        this.endTick = 0;
      } else {
        this.mostRecentShape = AnimatedShape.this.states.get(AnimatedShape.this.states.size() - 1);
        this.startWidth = this.mostRecentShape.getWidth();
        this.startHeight = this.mostRecentShape.getHeight();
        this.endWidth = this.mostRecentShape.getWidth();
        this.endHeight = this.mostRecentShape.getHeight();
        this.startPos = this.mostRecentShape.getPosition();
        this.endPos = this.mostRecentShape.getPosition();
        this.startColor = this.mostRecentShape.getColor();
        this.endColor = this.mostRecentShape.getColor();
        this.startTick = this.mostRecentShape.getTick();
        this.endTick = this.mostRecentShape.getTick();
      }


    }

    private MotionAdder setStartWidth(int value) {
      if (value <= 0) {
        throw new IllegalArgumentException("Width must be positive");
      }
      this.startWidth = value;
      return this;
    }

    private MotionAdder setStartHeight(int value) {
      if (value <= 0) {
        throw new IllegalArgumentException("Height must be positive");
      }
      this.startHeight = value;
      return this;
    }

    private MotionAdder setEndWidth(int value) {
      if (value <= 0) {
        throw new IllegalArgumentException("Width must be positive");
      }
      this.endWidth = value;
      return this;
    }

    private MotionAdder setEndHeight(int value) {
      if (value <= 0) {
        throw new IllegalArgumentException("Height must be positive");
      }
      this.endHeight = value;
      return this;
    }

    private MotionAdder setStartPos(Point2D pos) {
      this.startPos = pos;
      return this;
    }

    private MotionAdder setEndPos(Point2D pos) {
      this.endPos = pos;
      return this;
    }

    private MotionAdder setStartColor(Color color) {
      this.startColor = color;
      return this;
    }

    private MotionAdder setEndColor(Color color) {
      this.endColor = color;
      return this;
    }

    private MotionAdder setStartTick(int value) {
      if (value < 0) {
        throw new IllegalArgumentException("Tick must be positive");
      }
      this.startTick = value;
      return this;
    }

    private MotionAdder setEndTick(int value) {
      if (value < 0) {
        throw new IllegalArgumentException("Tick must be positive");
      }
      this.endTick = value;
      return this;
    }

    private MotionAdder setDuration(int duration) {
      if (duration < 1) {
        throw new IllegalArgumentException("Durations must be positive.");
      } else {
        this.endTick = this.startTick + duration;
        return this;
      }
    }
    
    /**
     * Adds a new shape state to this animated shape. It does not check if ticks are overlapping
     * because key frame ticks can overlap.
     *
     * @return returns this object after mutating it
     */
    private AnimatedShape addFrame() {

      List<IShapeState> states = AnimatedShape.this.states;

      switch (AnimatedShape.this.type) {
        case RECTANGLE:
          states.add(
              new RectangleState(
                  this.startTick, this.startWidth,
                  this.startHeight, this.startColor, this.startPos));
          states.add(
              new RectangleState(
                  this.endTick, this.endWidth, this.endHeight, this.endColor, this.endPos));
          break;
        case ELLIPSE:
          states.add(
              new EllipseState(
                  this.startTick, this.startWidth,
                  this.startHeight, this.startColor, this.startPos));
          states.add(
              new EllipseState(
                  this.endTick, this.endWidth, this.endHeight, this.endColor, this.endPos));
          break;
        default:
          break;
      }
      Collections.sort(states);
      return AnimatedShape.this;
    }

    /**
     * Adds a new shape state to this animated shape. First checks if the ticks specified create any
     * overlapping motions, then adds the correct {@code IShapeState} to this AnimatedShape.
     *
     * @return returns this object after mutating it
     */
    private AnimatedShape add() {

      List<IShapeState> states = AnimatedShape.this.states;

      for (int i = 0; i < states.size(); i += 2) {
        if (!((this.startTick <= states.get(i).getTick()
            && this.endTick <= states.get(i).getTick())
            || (this.startTick >= states.get(i + 1).getTick()
            && this.endTick >= states.get(i + 1).getTick()))) {
          throw new IllegalArgumentException(
              "Shape is already performing an operation "
                  + "during at least one of the ticks specified.");
        }
      }

      switch (AnimatedShape.this.type) {
        case RECTANGLE:
          states.add(
              new RectangleState(
                  this.startTick, this.startWidth,
                  this.startHeight, this.startColor, this.startPos));
          states.add(
              new RectangleState(
                  this.endTick, this.endWidth, this.endHeight, this.endColor, this.endPos));
          break;
        case ELLIPSE:
          states.add(
              new EllipseState(
                  this.startTick, this.startWidth,
                  this.startHeight, this.startColor, this.startPos));
          states.add(
              new EllipseState(
                  this.endTick, this.endWidth, this.endHeight, this.endColor, this.endPos));
          break;
        default:
          break;
      }
      Collections.sort(states);
      return AnimatedShape.this;
    }
  }

  @Override
  public String getMotions() {
    String result = "";
    int i = 0;
    while (i < this.states.size() - 1) {
      result += "motion " + this.name + " " + this.states.get(i).toString() + "    "
          + this.states.get(i + 1).toString() + "\n";
      i += 2;
    }
    return result;
  }

  @Override
  public IReadOnlyShapeState getShapeAt(int tick) {
    for (int i = 0; i < this.states.size() - 1; i += 2) {
      IShapeState a = this.states.get(i);
      IShapeState b = this.states.get(i + 1);
      if (a.getTick() <= tick && b.getTick() >= tick && a.getTick() != b.getTick()) {
        curState.setColor(
            linearInterpolate(
                a.getColor().getRed(), b.getColor().getRed(), a.getTick(), b.getTick(), tick),
            linearInterpolate(
                a.getColor().getGreen(), b.getColor().getGreen(), a.getTick(), b.getTick(), tick),
            linearInterpolate(
                a.getColor().getBlue(), b.getColor().getBlue(), a.getTick(), b.getTick(), tick));

        curState.setHeight(
            linearInterpolate(a.getHeight(), b.getHeight(), a.getTick(), b.getTick(), tick));

        curState.setWidth(linearInterpolate(
            a.getWidth(), b.getWidth(), a.getTick(), b.getTick(), tick));

        curState.setPosition(
            linearInterpolate(
                a.getPosition().getX(), b.getPosition().getX(), a.getTick(), b.getTick(), tick),
            linearInterpolate(
                a.getPosition().getY(), b.getPosition().getY(), a.getTick(), b.getTick(), tick));
        return curState;
      }
    }
    throw new IllegalArgumentException("No state of this shape at the given tick.");
  }

  private static int linearInterpolate(double a, double b, double ta, double tb, double t) {
    return (int) ((a * ((tb - t) / (tb - ta))) + (b * ((t - ta) / (tb - ta))));
  }

  @Override
  public void fullMotionTo(
      Point2D endPos, int endHeight, int endWidth, Color endColor, int duration) {
    if (endPos == null || endColor == null) {
      throw new IllegalArgumentException("Position and color must not be null."
          + "And initState or states must have values.");
    }
    new MotionAdder()
        .setEndColor(endColor)
        .setEndHeight(endHeight)
        .setEndWidth(endWidth)
        .setEndPos(endPos)
        .setDuration(duration)
        .add();
  }

  @Override
  public void changeColor(Color color, int duration) {
    if (color == null) {
      throw new IllegalArgumentException("Position and color must not be null."
          + "And initState or states must have values.");
    }
    new MotionAdder().setEndColor(color).setDuration(duration).add();
  }

  @Override
  public void moveTo(Point2D endPos, int duration) {
    if (endPos == null) {
      throw new IllegalArgumentException("Position must not be null."
          + "And states must have values.");
    }
    new MotionAdder().setEndPos(endPos).setDuration(duration).add();
  }

  @Override
  public void changeSizeTo(int newHeight, int newWidth, int duration) {
    new MotionAdder().setEndWidth(newWidth)
      .setEndHeight(newHeight).setDuration(duration)
      .add();
  }

  @Override
  public void addDoNothing(int duration) {
    new MotionAdder().setDuration(duration).add();
  }

  @Override
  public List<IReadOnlyShapeState> getStates() {
    ArrayList<IReadOnlyShapeState> result = new ArrayList<IReadOnlyShapeState>();

    for (IShapeState state : this.states) {
      result.add(state.deepCopy());
    }

    return result;
  }

  @Override
  public void addFrameMotion(int t1, int x1, int y1, int w1, int h1, int r1, int g1, int b1, int t2,
      int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
    MotionAdder adder = new MotionAdder();

    adder
      .setStartColor(new Color(r1, g1, b1))
      .setEndColor(new Color(r2, g2, b2))
      .setStartTick(t1)
      .setEndTick(t2)
      .setStartHeight(h1)
      .setEndHeight(h2)
      .setStartWidth(w1)
      .setEndWidth(w2)
      .setStartPos(new Point2D.Double(x1, y1))
      .setEndPos(new Point2D.Double(x2, y2))
      .addFrame();
  }

  @Override
  public void fullMotion(int t1, int x1, int y1, int w1, int h1, int r1, int g1, int b1, int t2,
      int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
    MotionAdder adder = new MotionAdder();

    if (adder.mostRecentShape != null && (t1 != adder.endTick || x1 != adder.endPos.getX()
        || y1 != adder.endPos.getY() || w1 != adder.endWidth || h1 != adder.endHeight
        || r1 != adder.endColor.getRed() || g1 != adder.endColor.getGreen()
        || b1 != adder.endColor.getBlue())) {
      throw new IllegalArgumentException("Motion must begin where the last one left off");
    } else {
      adder
        .setStartColor(new Color(r1, g1, b1))
        .setEndColor(new Color(r2, g2, b2))
        .setStartTick(t1)
        .setEndTick(t2)
        .setStartHeight(h1)
        .setEndHeight(h2)
        .setStartWidth(w1)
        .setEndWidth(w2)
        .setStartPos(new Point2D.Double(x1, y1))
        .setEndPos(new Point2D.Double(x2, y2))
        .add();
    }
  }

  public ShapeType getType() {
    return this.type;
  }

  @Override
  public IAnimatedShape deepCopy() {
    ArrayList<IShapeState> statesCopy = new ArrayList<IShapeState>();
    for (IShapeState state : this.states) {
      statesCopy.add(state.deepCopy());
    }
    return new AnimatedShape(this.name, this.type, statesCopy, this.order);
  }


  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public int getOrder() {
    return this.order;
  }

  @Override
  public void addKeyFrame(
      int tick, double x, double y, int width, int height, int r, int g, int b) {
    
    if (this.states.size() == 0) {
      this.addFrameMotion(tick, (int)x, (int)y, width, height, r, g, b,
          tick, (int)x, (int)y, width, height, r, g, b);
    }
    int curIndex = 0;
    if (this.states.size() == 0) {
      this.addFrameMotion(tick,(int)x,(int)y,width,height,r,g,b,tick,(int)x,(int)y,
              width,height,r,g,b);
    }
    try {
      while (tick >= this.states.get(curIndex).getTick()) {
        curIndex++;
      }
    }
    catch (IndexOutOfBoundsException e) {
      IShapeState sMax = this.states.get(this.states.size() - 1);
      this.addFrameMotion(
          sMax.getTick(), (int)sMax.getPosition().getX(), (int)sMax.getPosition().getY(), 
          sMax.getWidth(), sMax.getHeight(), sMax.getColor().getRed(),
          sMax.getColor().getGreen(), sMax.getColor().getBlue(),
          tick, (int)x, (int)y, width, height, r, g, b);
      return;
    }

    if (tick == this.states.get(curIndex).getTick()) {
      throw new IllegalArgumentException(
          "Keyframe exists at this tick. Edit the existing one instead");
    }
    else if (curIndex == 0) {
      IShapeState s0 = this.states.get(0);
      this.addFrameMotion(tick, (int)x, (int)y, width, height, r, g, b,
          s0.getTick(), (int)s0.getPosition().getX(), (int)s0.getPosition().getY(),s0.getWidth(),
          s0.getHeight(), s0.getColor().getRed(),
          s0.getColor().getGreen(), s0.getColor().getBlue());
    }
    else {
      this.addFrameMotion(tick, (int)x, (int)y, width, height, r, g, b,
          tick, (int)x, (int)y, width, height, r, g, b);
    }

  }

  @Override
  public void editKeyFrame(int index, int tick, double x, double y,
      int width, int height, int r, int g, int b) {
    if (index == 0) {
      IShapeState s0 = this.states.get(index);
      s0.setColor(r, g, b);
      s0.setPosition(x, y);
      s0.setHeight(height);
      s0.setWidth(width);
      if (s0.getTick() < tick) {
        IShapeState s1 = this.states.get(index + 1);
        s1.setColor(r, g, b);
        s1.setPosition(x, y);
        s1.setHeight(height);
        s1.setWidth(width);
        s1.setTick(tick);
      }
      s0.setTick(tick);
      Collections.sort(this.states);
    }
    else if ((index * 2) - 2 == this.states.size() - 1) {
      IShapeState s0 = this.states.get((index * 2) - 2);
      s0.setColor(r, g, b);
      s0.setPosition(x, y);
      s0.setHeight(height);
      s0.setWidth(width);
      if (s0.getTick() > tick) {
        IShapeState s1 = this.states.get((index * 2) - 3);
        s1.setColor(r, g, b);
        s1.setPosition(x, y);
        s1.setHeight(height);
        s1.setWidth(width);
        s1.setTick(tick);
      }
      s0.setTick(tick);
      Collections.sort(this.states);
    }
    else {
      IShapeState s0 = this.states.get((index * 2) - 2);
      IShapeState s1 = this.states.get((index * 2) - 1);

      s0.setColor(r, g, b);
      s0.setPosition(x, y);
      s0.setHeight(height);
      s0.setWidth(width);
      s1.setColor(r, g, b);
      s1.setPosition(x, y);
      s1.setHeight(height);
      s1.setWidth(width);
      s1.setTick(tick);
      s0.setTick(tick);
      Collections.sort(this.states);
    }
  }

  @Override
  public void removeKeyFrame(int index) {
    if (index == 0) {
      this.states.remove(index);
      this.states.remove(index);
    }
    else if ((index * 2) - 2 == this.states.size() - 1) {
      this.states.remove((index * 2) - 2);
      this.states.remove((index * 2) - 3);
    }
    else {
      this.states.remove((index * 2) - 1);
      this.states.remove((index * 2) - 2);
    } 
  }

  @Override
  public ArrayList<String> getStatesStringArray() {
    ArrayList<String> result = new ArrayList<String>();
    if (this.states.size() > 0) {
      int i = 1;
      while (i < this.states.size() - 2) {
        if (i % 2 == 1) {
          result.add(this.states.get(i).toString());
        }
        i++;
      }
    }
    else if (this.states.size() > 3) {
      result.add(this.states.get(this.states.size() - 1).toString());
    }
    else {
      result = new ArrayList<>();
    }
    return result;
  }

  @Override
  public int getLayer() {
    return this.order;
  }

  @Override
  public void setLayer(int newLayer) {
    this.order = newLayer;
  }

}
