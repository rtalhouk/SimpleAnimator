package cs3500.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import cs3500.animator.model.IModel;
import cs3500.animator.model.IReadOnlyAnimatedShape;
import cs3500.animator.model.IReadOnlyShapeState;
import cs3500.animator.model.ShapeType;
import cs3500.animator.view.IView;

/**
 * Represents a controller for the visual-style views of the animator. It handles events fired by
 * the model and then edits the model as desired.
 */
public class VisualController implements IAnimatorController {
  private IModel model;
  private IView view;
  private Timer timer;
  private boolean isLooped;
  private int tick = 0;

  /**
   * The main constructor of this controller. It contains a timer definition that draws the shapes
   * of the animation at the given tick, which is stored in the controller.
   * 
   * @param model the model to be edited by this controller 
   * @param view the view to display the results from the model
   * @param speed the speed at which the controller should display the animation from the model
   */
  public VisualController(IModel model, IView view, int speed) {
    this.model = model;
    this.view = view;
    this.timer = new Timer(1000 / speed, new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        List<IReadOnlyShapeState> shapesToRender = model.getShapesAtTick(tick);
        if (isLooped) {
          tick = (tick + 1) % model.getMaxTick();
        }
        else {
          tick++;
        }
        view.drawShapes(shapesToRender);
        view.setKnob(tick);
      }
    });

    view.addListener(this);
    try {
      this.setShapesArray();
    } catch (UnsupportedOperationException e) {
      //Continue as normal
    }
  }

  @Override
  public void pause() {
    this.timer.stop();
  }

  @Override
  public void play() {
    this.timer.start();
  }

  @Override
  public void restart() {
    this.tick = 0;
    view.drawShapes(model.getShapesAtTick(tick));
  }

  @Override
  public void updateSpeed(int newTPS) {
    this.timer.setDelay(1000 / newTPS);
  }

  @Override
  public void addShape(String id, ShapeType shapeType) {
    model.addShape(id, shapeType);
    setShapesArray();
  }

  @Override
  public void removeShape(String id) {
    model.removeShape(id);
    setShapesArray();
  }

  @Override
  public void loop(boolean value) {
    this.isLooped = value;
  }

  @Override
  public void addKeyFrame(
      String id, int tick, double x, double y, int width, int height, int r, int g, int b) {
    model.addKeyFrame(id, tick, x, y, width, height, r, g, b);
    this.setShapesArray();
  }

  @Override
  public void removeKeyFrame(String id, int index) {
    model.removeKeyFrame(id, index);
    this.setShapesArray();
  }

  @Override
  public void editKeyFrame(String id, int index, int tick, double x, double y,
      int width, int height, int r, int g, int b) {
    model.editKeyFrame(id, index, tick, x, y, width, height, r, g, b);
    this.setShapesArray();
  }

  /**
   * sets the shapes in the animation in the view.
   */
  private void setShapesArray() {
    view.setShapesArray(model.getShapes());
  }

  @Override
  public IReadOnlyAnimatedShape getShape(String id) {
    return model.getShapeObject(id);
  }

  @Override
  public int getMaxTick() {
    return model.getMaxTick();
  }

  @Override
  public void changeTickTo(int tick) {
    this.tick = tick;
  }

  @Override
  public int getCurrentTick() {
    return this.tick;
  }

  @Override
  public List<IReadOnlyShapeState> getShapesAtTick(int tick) {
    return model.getShapesAtTick(tick);
  }

  @Override
  public void changeLayer(String id, int newLayer) {
    model.changeLayer(id, newLayer);
    this.setShapesArray();
  }

}
