package cs3500.animator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import cs3500.animator.controller.IAnimatorController;
import cs3500.animator.controller.VisualController;
import cs3500.animator.model.IModel;
import cs3500.animator.model.Model;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.IView;
import cs3500.animator.view.ViewFactory;

/**
 * Runs the main function of the program.
 *
 */
public class Excellence {
  /**
   * Parses command arguments and sets up the program correctly.
   * @param args supplied command line arguments
   */
  public static void main(String[] args) {
    String argString = "";
    for (String s : args) {
      argString += s + " ";
    }

    Scanner argParser = new Scanner(new StringReader(argString));
    Readable in = new InputStreamReader(System.in);
    Appendable out = System.out;
    int tps = 1;
    String type = "";
    JFrame frame = new JFrame();
    frame.setBounds(0, 0, 200, 100);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    try {
      while (argParser.hasNext()) {
        String next = argParser.next();
        switch (next) {
          case "-view":
            type = argParser.next();
            break;
          case "-in":
            in = new FileReader(argParser.next());
            break;
          case "-out":
            out = new FileWriter(argParser.next());
            break;
          case "-speed":
            tps = Integer.valueOf(argParser.next());
            break;
          default:
            JOptionPane.showMessageDialog(frame, "Valid arguments consist of the following"
                            + "-in \"name-of-animation-file\" -view \"type-of-view\" -out "
                            + "\"where-output-show-go\" -speed \"integer-ticks-per-second\"\n",
                    "Invalid command line arguments", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
            return;
        }
      }
    } catch (NoSuchElementException e) {
      JOptionPane.showMessageDialog(frame, "Insufficient arguments to run program."
                      + "Each argument must be followed by a value",
              "Invalid command line arguments", JOptionPane.ERROR_MESSAGE);
      System.exit(1);
      return;
    } catch (FileNotFoundException e) {
      JOptionPane.showMessageDialog(frame, e.getMessage(),
              "Invalid command line arguments", JOptionPane.ERROR_MESSAGE);
      System.exit(1);
      return;
    } catch (IOException e) {
      JOptionPane.showMessageDialog(frame, e.getMessage(),
              "FileWriter failed", JOptionPane.ERROR_MESSAGE);
      System.exit(1);
      return;
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(frame, "Speed must be an integer.",
              "Invalid command line arguments", JOptionPane.ERROR_MESSAGE);
      System.exit(1);
      return;
    }

    IModel model = AnimationReader.parseFile(in, new Model.Builder());
    IView view = ViewFactory.makeView(type, tps, model.getWidth(), model.getHeight());
    IAnimatorController controller = new VisualController(model, view, tps);

    try {
      switch (type) {
        case "svg":
        case "SVG":
        case "text":
          out.append(view.printView(model));
          break;
        case "visual":
        case "edit":
          controller.play();
          break;
        default:
          JOptionPane.showMessageDialog(
                  frame, "Supported views are \"text\", \"svg\", and \"visual\".",
                  "Invalid command line arguments", JOptionPane.ERROR_MESSAGE);
          System.exit(1);
          return;
      }
    } catch (IOException e) {
      JOptionPane.showMessageDialog(
              frame, "Could not output.",
              "Invalid command line arguments", JOptionPane.ERROR_MESSAGE);
      System.exit(1);
      return;
    }
    
    if (out instanceof FileWriter) {
      try {
        ((FileWriter) out).close();
      } catch (IOException e) {
        JOptionPane.showMessageDialog(
            frame, "Could not output.",
            "Invalid command line arguments", JOptionPane.ERROR_MESSAGE);
        argParser.close();
        System.exit(1);
        return;
      }
    }
  }
}
