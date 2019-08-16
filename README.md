# Easy Animator
## Model
### Overview
Most original text from this README descibes our model from Assignment 5. All additions documenting changes from Assignment 5 to 6 will be added below the original text and will be clearly indicated with "CHANGES." All additions documenting changes from Assignment 6 to 7 will be added below to the text and will be indicated with "FINAL CHANGES."

### IModel/Model
The model interface is the user-facing interface and will be the one called by the controller or view when they are implemented. Our implementation has only one field: a hashmap that maps unique string names to specific `IAnimatedShape` objects. These shapes are added using the `addShapeAt(...)` method, which takes in a name and the attributes required to initalize the an animated shape. The other methods primarily perform actions on specified animated shapes. The model methods simply finds the correct animated shape based on the name and delegates the action to that object's methods.
CHANGES:
- Removal of  `addShapeAt(..)` function, as it did not align with the way shapes were declared by the given text files. Instead, the the method was changed to `addShape(...)` whose states were then set to null until a full motion was added.
- Added a `shapeCount` field that allowed shapes to be layered in the order they were declared in the input text file. This is especially important in the buildings animation as the buildings and windows must be layered over the background correctly.
- Added `width` and `height` fields to dictate the size of the window. We originally thought that could be handled by the view but with the way the given `AnimationBuilder` was implemented, it seemed better to include that in the model.
- Added addtional getters for better data customization. Also we implemented `deepCopy()` methods for all necessary datatypes to aviod bugs.
- Added `fullMotion(...)` method to accomodate how the inputs were passed from the given `AnimationBuilder` and `AnimationReader` interfaces. Previously, all our motions assumed that the starting state for the next motion was the ending state for the most recent one so no gaps or overlap were even possible.
- Added a `Builder` class that implemented the given `AnimationBuilder` interface.
FINAL CHANGES:
-Added `removeKeyFrame(...)` method which removes a certain keyFrame from a specified shape in the animation. The fields for this method are the shape's name and the index of the states of that shape which are both passed from the  `IView` interface to the  `VisualController` class to this interface. 
-Added `editKeyFrame(...)` method which is given a shape's name and all fields to fully change the shape's movement/size/color by a specified tick. This replaces the keyFrame selected by the user. The information in this method also originates form the `IView` interface and passes to the  `VisualController` class, finally calling this method in this interface.
-Added `addKeyFrame(...)` method which adds a keyFrame to the shape specified by the user. Its arguments consist of the shape's name and all of the fields to specify its size, location, and color.
-Added `getMaxTick()` method which retrieves the largest/latest tick out of every state of every shape of the whole animation. The purpose of this function is for the controller to knowthe final tick in order to loop if specified by the user.


### IAnimatedShape/AnimatedShape
The animated shape interface is comprised of objects that represent shapes at different states. Our implementation has three different fields: an enum representing the type of shape. A single shape state representing the initial state of the shape when it is intialized from the model, and a list of states that represent endpoints of motions added by the user. The length of the list will always be even as the start and end points of the motion are added at the same time. The motions are added using a pseudo-builder pattern that creates two shape states based on the specifications of the movement. The first shape state is defaulted to be identical to the ending shape state of the previous motion. This ensures that there will be no gaps int the motions of any shape and that the ending state of one motion always matches the starting state of the next one.
This builder pattern also allows for easy extension to create new types of motions as each of the attributes of the shape can be changed independently. The current motions that are supported are a "full motion" (changing all attributes), "move to" (changing just the position), "change color to", "change size to", and "do nothing" (which is required so no gaps appear in the ticks of the motions of the Animated Shape.
This interface also has a `getShapeAtTick` and `getMotions` function. These methods will return an IllegalArgumentException and an empty string repectively if they are called on an Animated shape that has simply been initalized and no motions have been added. This was a design decision we made becuase we feel that an animated shape is a representation of a shape over time, so simply initializing at a single tick does not have any motion to it.
CHANGES:
- Added `order` field so that they can be layered based on their order. We also exteded the `Comparable<T>` inteface for quick sorting based on order.
- Added addtional getters in the `IReadOnlyAnimatedShape` interface to avoid mutability.
- Added `fullMotion(...)` method, see description above.
FINAL CHANGES:
- Added `getOrder()` method gets the order this shape hould be layered with other shapes in the animation.
-Added `addKeyFrame(...)` method which adds a keyFrame to the shape specified by the user. Its arguments consist of the shape's name and all of the fields to specify its size, location, and color.
-Added `editKeyFrame(...)` method which is given a shape's name and all fields to fully change the shape's movement/size/color by a specified tick. This replaces the keyFrame selected by the user. The information in this method originates form the `IView` interface and passes to the  `VisualController` class, then to the model interface, and finally at this interface.
-Added `removeKeyFrame(...)` method which removes a certain keyFrame from a specified shape in the animation. The fields for this method are the shape's name and the index of the states of that shape which are both passed from the  `IView` interface to the  `VisualController` class, to the `IModel` interface, and finally to this interface.
-Added `addFrameMotion(...)` method which is given both the starting position, color, and size, and the ending position, color, and size to add a Keyframe into a list of already existing keyFrames.
-Added additional getters in the `IReadOnlyAnimatedShape` interface to avoid mutability.
-Added `getStatesStringArray()` method which returns the states of this shape as an ArrayList of Strings.


### IReadOnlyShapeState/IShapeState
These interfaces is comprised of objects that represent shapes at a given tick in the model animation. This inteface was created to represent individual state of an animated shapes at given ticks. Therefore we made an abstract class to implement the interface that had tick, width, height, color, and position fields, as any shape state in the animation will need these properities. The only methods this inteface supports are getters (in both intefaces) and a `toString` and `deepCopy` methods that return new representations of the shapes. This is because we believe that these shape states should be immuatable once created.
The aforementioned abstract class is currently only extended by Rectangles and Ellipses, but new shapes can be added easily by adding new classes and adding the new type of shape to the `ShapeType` enum. The classes that extend the abstract shape state class are comprised only of their explicit constructors and versions of the `deepCopy` method.
All getters are kept and serve to ensure no chance of mutation when passed to locations where users may have access.


### ShapeType Enum
Represents a list of supported types of shapes. In order to add a new supported shape. A user need only add to the enum and write a class exending the abstract shape state class. That class need only have explicit constructors and a override of the `deepCopy` method.

### Color
A simple class to represent the RGB values of a color in an organized way. The implementation is only used internally so users need not be familiar with the `Color` constructor. Instead they can simply pass integers into the `changeColor` methods in the model.
CHANGES:
- Added `getHSB()` method so RBG values could be substituted for HSB values if necesssary.

## View
### IView
Supports all methods for all views and throws `UnsupportedOperationException` if it is not applicable for each concrete implementation.

### VisualView
The VisualView class extends the JFrame class and has a `DrawingPanel`,  `Timer`, and integer field to keep track of the tick. In the view's constructor, the timer is defined to draw shapes (included JPanel method) every time increment specified by the user. The shapes to draw are returned from the `getShapesAtTick(int)` method. Then to start the timer, the `render()` method is called.
FINAL CHANGES:
-Changed the name of the method `formatSVG(...)` to `printView(...)` to get rid of the unneccessary extra method.
-Added `addListener(...)` method which adds a listener to this view that handles when events are fired by the view. 
-Added `setShapesArray(...)` method sets the list of read only shapes in the animation in the view.

### SVGView
The `SVGView` class implements a command pattern for the type of SVG tag it must output. We felt this was a good design decision becuase tags vary based on the type of shape so constructing a tag for a certain kind of `ShapeType` enum is alagous to executing different commands on the view. Each different kind of tag is represented by its own class, with all common code going in an abstracted class. The reason for this was so new types of shapes could be easily added just by extending the abstract class.

### TextView
The `TextView` class is quite simplistic, it is essentially the same as the implementation from Assignment 5, but instead housed in its own class.
FINAL CHANGES:
The format for each motion was off because we did not say "motion" before every motion printed.

### DrawingPanel
The `DrawingPanel` class extends JPanel and is where all the graphics are acutally painted in the `VisuaView`. Its concrete implementation contains the `paintComponent()` method, which is given the shapes to draw and the graphics so it can paint.

### IViewListener
The `IViewListener` interface represents an object that listens to an `IView` interface for methods called based on what events are fired, in this case in the `EditView` class.
FINAL CHANGES:
-Added `pause()` method to pause the animation when fired by the user.
-Added `play()` method to resume the animation when fired by the user.
-Added `restart()` method to restart the timer and animation when fired by the user.
-Added `updateSpeed(...)` method adjusts the speed at which this animation plays based on the given tick rate as the parameter in this method.
-Added `addShape(...)` method adds a shape to the animation when directed by the user and given both a unique name and a type of shape supported by this animation.
-Added `removeShape()` method which removes a shape when directed by the user when given an existing shape's name.
-Added `loop(...)`  method which loops the animation if the boolean passed in indicates so. This is controlled by the user.
-Added `removeKeyFrame(...)` method which removes a certain keyFrame from a specified shape in the animation. The fields for this method are the shape's name and the index of the states of that shape.
-Added `editKeyFrame(...)` method which is given a shape's name and all fields to fully change the shape's movement/size/color by a specified tick. This replaces the keyFrame selected by the user.
-Added `addKeyFrame(...)` method which adds a keyFrame to the shape specified by the user. Its arguments consist of the shape's name and all of the fields to specify its size, location, and color.
-Added `getShape(...)` method which retrieves a specific read only shape based on the name passed in this method.

### TestView 
This class implements `IView` and represents a dummy view that tests whether the controller and model are synced up correctly with the view. It takes in a readable that fires dummy events to be handled by the controller. The purpose of this class is to implement the methodology of mock testing. The only methods implemented in this class are from the interface.


## Controller
### IAnimatorController
Represents a controller interface. It has no methods because the inteface extends another that contains all the features that has methods for all the actions that could come from the view. `IAnimatorController` implements `IViewListener`.

### VisualController
Represents a controller for the visual-style views of the animator. It handles events fired by the model and then edits the model as desired. `VisualController` implements `IAnimatorController`.
FINAL CHANGES:
-Added `pause()` method to pause the animation when fired by the user.
-Added `play()` method to resume the animation when fired by the user.
-Added `restart()` method to restart the timer and animation when fired by the user.
-Added `updateSpeed(...)` method adjusts the speed at which this animation plays based on the given tick rate as the parameter in this method.
-Added `addShape(...)` method adds a shape to the animation when directed by the user and given both a unique name and a type of shape supported by this animation.
-Added `removeShape()` method which removes a shape when directed by the user when given an existing shape's name.
-Added `loop(...)`  method which loops the animation if the boolean passed in indicates so. This is controlled by the user.
-Added `removeKeyFrame(...)` method which removes a certain keyFrame from a specified shape in the animation. The fields for this method are the shape's name and the index of the states of that shape.
-Added `editKeyFrame(...)` method which is given a shape's name and all fields to fully change the shape's movement/size/color by a specified tick. This replaces the keyFrame selected by the user.
-Added `addKeyFrame(...)` method which adds a keyFrame to the shape specified by the user. Its arguments consist of the shape's name and all of the fields to specify its size, location, and color.
-Added `getShape(...)` method which retrieves a specific read only shape based on the name passed in this method.

### TestController
Represents a dummy controller used only for testing. It has an Appendable that each of the methods appends to and implements `IAnimatorController` and `IViewListener`.
-Added `pause()` method to pause the animation when fired by the user.
-Added `play()` method to resume the animation when fired by the user.
-Added `restart()` method to restart the timer and animation when fired by the user.
-Added `updateSpeed(...)` method adjusts the speed at which this animation plays based on the given tick rate as the parameter in this method.
-Added `addShape(...)` method adds a shape to the animation when directed by the user and given both a unique name and a type of shape supported by this animation.
-Added `removeShape()` method which removes a shape when directed by the user when given an existing shape's name.
-Added `loop(...)`  method which loops the animation if the boolean passed in indicates so. This is controlled by the user.
-Added `removeKeyFrame(...)` method which removes a certain keyFrame from a specified shape in the animation. The fields for this method are the shape's name and the index of the states of that shape.
-Added `editKeyFrame(...)` method which is given a shape's name and all fields to fully change the shape's movement/size/color by a specified tick. This replaces the keyFrame selected by the user.
-Added `addKeyFrame(...)` method which adds a keyFrame to the shape specified by the user. Its arguments consist of the shape's name and all of the fields to specify its size, location, and color.
-Added `getShape(...)` method which retrieves a specific read only shape based on the name passed in this method.

# Bonus Assignment Changes

### IViewListener
This `interface` has been updated to include getters such as:
- `getMaxTick()` which retrieves the maximum tick in the whole animation in order to make the total size of the scrubber
- `getCurrentTick()` which gets the current tick in the animation in order to allow for the scrubber's knob to follow along with the animation as it moves
- `getShapesAtTick(...)` which retrieves all the shape states at the given tick in order for the scrubber to know what do draw as it moves along if the scrubber is moved by the user. This means the user can see the animation moving at the rate at which he/she moves the scrubber.
- In addition, `changeTickTo(...)` was also added in order for the animation to receive the location of the knob and animate from there accordingly.  
- `changeLayer(int)` was added to allow for the user to change which layer any of the shapes is in. This calls a method in the model which mutates the correct `AnimatedShape`

### IView
This `interface` has been updated due to the addition of `setKnob()` which sets the position of the knob along the slider as the animation plays out. This is so the scrubber is ont jsut a setter for wherever it is placed, but is interacting simultaneously with the animation whether playing or paused.

### EditView
A new panel was added to the view so that for any shape selected, the layer of the shape could be changed with the click of a button and a text field. The `changeLayerAction()` method was added to fire this event to the controller.

### IModel
Added method:
- `changeLayer(String id, int layer)` changes the layer of the `IAnimatedShape` with the given string ID.

### IAnimatedShape
Added method:
- `changeLayer(int)` changes the layer of this `IAnimatedShape` to the given integer value.



