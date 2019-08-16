import static cs3500.animator.model.ShapeType.ELLIPSE;
import static cs3500.animator.model.ShapeType.RECTANGLE;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import cs3500.animator.model.IModel;
import cs3500.animator.model.Model;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;

/**
 * tests the svg formatted view of {@code SVGView}.
 */
public class SVGViewTest {

  IView v1;
  IModel m1;

  @Before
  public void setUp() {
    v1 = new SVGView(30);
    m1 = new Model();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testFail() {
    v1.drawShapes(null);
  }

  @Test
  public void testPrintView3() {
    assertEquals("<svg width=\"500\" height=\"500\" version=\"1.1\" " +
        "xmlns=\"http://www.w3.org/2000/svg\">\n" +
        "\n</svg>\n", v1.printView(m1));
  }

  @Test
  public void testPrintView2() {
    m1.addShape("Dave", RECTANGLE);
    m1.addShape("Rich II", ELLIPSE);
    assertEquals("<svg width=\"500\" height=\"500\" version=\"1.1\" xmlns=\"http://" +
        "www.w3.org/2000/svg\">\n" +
        "<rect id=\"Dave\">\n" +
        "</rect>\n" +
        "<ellipse id=\"Rich II\">\n" +
        "</ellipse>\n" +
        "\n" +
        "</svg>\n", v1.printView(m1));
  }

  @Test
  public void testPrintView() {
    m1.addShape("Dave", RECTANGLE);
    m1.addShape("Rich II", ELLIPSE);
    m1.fullMotion("Dave", 1, 10, 10, 10, 10, 255, 255, 255,
        4, 4, 4, 4, 4, 4, 4, 4);
    m1.fullMotion("Rich II", 1, 20, 30, 20, 2, 3, 30, 57,
        21, 300, 300, 12, 72, 200, 200, 200);
    m1.fullMotion("Dave", 4, 4, 4, 4, 4, 4, 4, 4, 17,
        76, 30, 30, 23, 199, 67, 255);
    assertEquals("<svg width=\"500\" height=\"500\" version=\"1.1\" xmlns=\"http://www.w3.org/200"
        + "0/svg\">\n" + 
        "<rect id=\"Dave\" x=\"10.0\"  y=\"10.0\" width=\"10\" height=\"10\" fill=\"rgb(255,255,"
        + "255)\" visibility=\"visible\" >\n" + 
        "<animate attributesType=\"xml\" begin=\"33.333333333333336ms\" dur=\"100.0ms\" attribut"
        + "eName=\"fill\" from=\"rgb(255,255,255)\" to=\"rgb(4,4,4)\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"33.333333333333336ms\" dur=\"100.0ms\" attribut"
        + "eName=\"x\" from=\"10.0\" to=\"4.0\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"33.333333333333336ms\" dur=\"100.0ms\" attribute"
        + "Name=\"y\" from=\"10.0\" to=\"4.0\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"33.333333333333336ms\" dur=\"100.0ms\" attribute"
        + "Name=\"width\" from=\"10\" to=\"4\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"33.333333333333336ms\" dur=\"100.0ms\" attribute"
        + "Name=\"height\" from=\"10\" to=\"4\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"133.33333333333334ms\" dur=\"433.33333333333337m"
        + "s\" attributeName=\"fill\" from=\"rgb(4,4,4)\" to=\"rgb(199,67,255)\" fill=\"fr"
        + "eeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"133.33333333333334ms\" dur=\"433.33333333333337m"
        + "s\" attributeName=\"x\" from=\"4.0\" to=\"76.0\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"133.33333333333334ms\" dur=\"433.33333333333337m"
        + "s\" attributeName=\"y\" from=\"4.0\" to=\"30.0\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"133.33333333333334ms\" dur=\"433.33333333333337ms"
        + "\" attributeName=\"width\" from=\"4\" to=\"30\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"133.33333333333334ms\" dur=\"433.33333333333337ms"
        + "\" attributeName=\"height\" from=\"4\" to=\"23\" fill=\"freeze\" />\n" + 
        "</rect>\n" + 
        "<ellipse id=\"Rich II\" cx=\"20.0\"  cy=\"30.0\" rx=\"10\" ry=\"1\" fill=\"rgb(3,30,57)\""
        + " visibility=\"visible\" >\n" + 
        "<animate attributesType=\"xml\" begin=\"33.333333333333336ms\" dur=\"666.6666666666666ms\""
        + " attributeName=\"fill\" from=\"rgb(3,30,57)\" to=\"rgb(200,200,200)\" "
        + "fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"33.333333333333336ms\" dur=\"666.6666666666666ms\""
        + " attributeName=\"cx\" from=\"20.0\" to=\"300.0\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"33.333333333333336ms\" dur=\"666.6666666666666ms\""
        + " attributeName=\"cy\" from=\"30.0\" to=\"300.0\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"33.333333333333336ms\" dur=\"666.6666666666666m"
        + "s\" attributeName=\"rx\" from=\"10\" to=\"6\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"33.333333333333336ms\" dur=\"666.6666666666666m"
        + "s\" attributeName=\"ry\" from=\"1\" to=\"36\" fill=\"freeze\" />\n" + 
        "</ellipse>\n" + 
        "\n" + 
        "</svg>\n", v1.printView(m1));
  }

  @Test
  public void testPrintViewBasedOnWhenInstantiated() {
    m1.addShape("Dave", RECTANGLE);
    m1.addShape("Adam", ELLIPSE);
    m1.addShape("Daniel", RECTANGLE);
    m1.addShape("Vido", ELLIPSE);
    m1.fullMotion("Dave", 1, 10, 10, 10, 10, 255, 255, 255,
        5, 4, 4, 4, 4, 4, 4, 4);
    m1.fullMotion("Vido", 3, 200, 200, 1, 1, 0, 0, 0, 23,
        250, 250, 23, 34, 90, 36, 78);
    m1.fullMotion("Adam", 10, 20, 30, 20, 2, 3, 30, 57, 30,
        300, 300, 12, 72, 200, 200, 200);
    m1.fullMotion("Dave", 5, 4, 4, 4, 4, 4, 4, 4, 7,
        76, 30, 30, 23, 199, 67, 255);
    m1.fullMotion("Daniel", 5, 30, 30, 10, 10, 30, 78, 180,
        22, 100, 100, 100, 100, 0, 13, 255);
    assertEquals("<svg width=\"500\" height=\"500\" version=\"1.1\" xmlns=\"http://www.w3.org/20"
        + "00/svg\">\n" + 
        "<rect id=\"Dave\" x=\"10.0\"  y=\"10.0\" width=\"10\" height=\"10\" fill=\"rgb(255,255,"
        + "255)\" visibility=\"visible\" >\n" + 
        "<animate attributesType=\"xml\" begin=\"33.333333333333336ms\" dur=\"133.33333333333334"
        + "ms\" attributeName=\"fill\" from=\"rgb(255,255,255)\" to=\"rgb(4,4,4)\" "
        + "fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"33.333333333333336ms\" dur=\"133.33333333333334"
        + "ms\" attributeName=\"x\" from=\"10.0\" to=\"4.0\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"33.333333333333336ms\" dur=\"133.33333333333334"
        + "ms\" attributeName=\"y\" from=\"10.0\" to=\"4.0\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"33.333333333333336ms\" dur=\"133.33333333333334"
        + "ms\" attributeName=\"width\" from=\"10\" to=\"4\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"33.333333333333336ms\" dur=\"133.33333333333334m"
        + "s\" attributeName=\"height\" from=\"10\" to=\"4\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"166.66666666666666ms\" dur=\"66.66666666666667ms"
        + "\" attributeName=\"fill\" from=\"rgb(4,4,4)\" to=\"rgb(199"
        + ",67,255)\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"166.66666666666666ms\" dur=\"66.66666666666667m"
        + "s\" attributeName=\"x\" from=\"4.0\" to=\"76.0\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"166.66666666666666ms\" dur=\"66.66666666666667m"
        + "s\" attributeName=\"y\" from=\"4.0\" to=\"30.0\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"166.66666666666666ms\" dur=\"66.66666666666667m"
        + "s\" attributeName=\"width\" from=\"4\" to=\"30\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"166.66666666666666ms\" dur=\"66.66666666666667m"
        + "s\" attributeName=\"height\" from=\"4\" to=\"23\" fill=\"freeze\" />\n" + 
        "</rect>\n" + 
        "<ellipse id=\"Adam\" cx=\"20.0\"  cy=\"30.0\" rx=\"10\" ry=\"1\" fill=\"rgb(3,30,57)\" "
        + "visibility=\"visible\" >\n" + 
        "<animate attributesType=\"xml\" begin=\"333.3333333333333ms\" dur=\"666.6666666666666ms"
        + "\" attributeName=\"fill\" from=\"rgb(3,30,57)\" to=\"rgb(200,200,200)\""
        + " fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"333.3333333333333ms\" dur=\"666.666666666666"
        + "6ms\" attributeName=\"cx\" from=\"20.0\" to=\"300.0\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"333.3333333333333ms\" dur=\"666.6666666666666"
        + "ms\" attributeName=\"cy\" from=\"30.0\" to=\"300.0\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"333.3333333333333ms\" dur=\"666.6666666666666"
        + "ms\" attributeName=\"rx\" from=\"10\" to=\"6\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"333.3333333333333ms\" dur=\"666.6666666666666ms"
        + "\" attributeName=\"ry\" from=\"1\" to=\"36\" fill=\"freeze\" />\n" + 
        "</ellipse>\n" + 
        "<rect id=\"Daniel\" x=\"30.0\"  y=\"30.0\" width=\"10\" height=\"10\" fill=\"rgb(30,78,1"
        + "80)\" visibility=\"visible\" >\n" + 
        "<animate attributesType=\"xml\" begin=\"166.66666666666666ms\" dur=\"566.6666666666666m"
        + "s\" attributeName=\"fill\" from=\"rgb(30,78,180)\" to=\"rgb(0,13,255)\" "
        + "fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"166.66666666666666ms\" dur=\"566.6666666666666m"
        + "s\" attributeName=\"x\" from=\"30.0\" to=\"100.0\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"166.66666666666666ms\" dur=\"566.6666666666666ms"
        + "\" attributeName=\"y\" from=\"30.0\" to=\"100.0\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"166.66666666666666ms\" dur=\"566.6666666666666ms"
        + "\" attributeName=\"width\" from=\"10\" to=\"100\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"166.66666666666666ms\" dur=\"566.6666666666666ms\""
        + " attributeName=\"height\" from=\"10\" to=\"100\" fill=\"freeze\" />\n" + 
        "</rect>\n" + 
        "<ellipse id=\"Vido\" cx=\"200.0\"  cy=\"200.0\" rx=\"0\" ry=\"0\" fill=\"rgb(0,0,0)\" vi"
        + "sibility=\"visible\" >\n" + 
        "<animate attributesType=\"xml\" begin=\"100.0ms\" dur=\"666.6666666666666ms\" attributeN"
        + "ame=\"fill\" from=\"rgb(0,0,0)\" to=\"rgb(90,36,78)\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"100.0ms\" dur=\"666.6666666666666ms\" attributeN"
        + "ame=\"cx\" from=\"200.0\" to=\"250.0\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"100.0ms\" dur=\"666.6666666666666ms\" attribute"
        + "Name=\"cy\" from=\"200.0\" to=\"250.0\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"100.0ms\" dur=\"666.6666666666666ms\" attributeN"
        + "ame=\"rx\" from=\"0\" to=\"11\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"100.0ms\" dur=\"666.6666666666666ms\" attribute"
        + "Name=\"ry\" from=\"0\" to=\"17\" fill=\"freeze\" />\n" + 
        "</ellipse>\n" + 
        "\n" + 
        "</svg>\n", v1.printView(m1));
  }

  @Test
  public void testPrintViewAlphabetically() {
    m1.addShape("Dave", RECTANGLE);
    m1.addShape("Adam", ELLIPSE);
    m1.addShape("Daniel", RECTANGLE);
    m1.addShape("Vido", ELLIPSE);
    m1.fullMotion("Dave", 3, 10, 10, 10, 10, 255, 255, 255,
        5, 4, 4, 4, 4, 4, 4, 4);
    m1.fullMotion("Vido", 3, 200, 200, 1, 1, 0, 0, 0, 23,
        250, 250, 23, 34, 90, 36, 78);
    m1.fullMotion("Adam", 3, 20, 30, 20, 2, 3, 30, 57, 30,
        300, 300, 12, 72, 200, 200, 200);
    m1.fullMotion("Dave", 5, 4, 4, 4, 4, 4, 4, 4, 7,
        76, 30, 30, 23, 199, 67, 255);
    m1.fullMotion("Daniel", 3, 30, 30, 10, 10, 30, 78, 180,
        22, 100, 100, 100, 100, 0, 13, 255);
    assertEquals("<svg width=\"500\" height=\"500\" version=\"1.1\" xmlns=\"http://www.w3.org/2000"
        + "/svg\">\n" + 
        "<rect id=\"Dave\" x=\"10.0\"  y=\"10.0\" width=\"10\" height=\"10\" fill=\"rgb(255,255,25"
        + "5)\" visibility=\"visible\" >\n" + 
        "<animate attributesType=\"xml\" begin=\"100.0ms\" dur=\"66.66666666666667ms\" attributeNam"
        + "e=\"fill\" from=\"rgb(255,255,255)\" to=\"rgb(4,4,4)\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"100.0ms\" dur=\"66.66666666666667ms\" attributeNam"
        + "e=\"x\" from=\"10.0\" to=\"4.0\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"100.0ms\" dur=\"66.66666666666667ms\" attributeNam"
        + "e=\"y\" from=\"10.0\" to=\"4.0\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"100.0ms\" dur=\"66.66666666666667ms\" attributeNam"
        + "e=\"width\" from=\"10\" to=\"4\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"100.0ms\" dur=\"66.66666666666667ms\" attributeNam"
        + "e=\"height\" from=\"10\" to=\"4\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"166.66666666666666ms\" dur=\"66.66666666"
        + "666667ms\" attributeName=\"fill\" from=\"rgb(4,4,4)\" to=\"rgb(199,67,2"
        + "55)\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"166.66666666666666ms\" dur=\"66.6666666666"
        + "6667ms\" attributeName=\"x\" from=\"4.0\" to=\"76.0\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"166.66666666666666ms\" dur=\"66.666666666666"
        + "67ms\" attributeName=\"y\" from=\"4.0\" to=\"30.0\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"166.66666666666666ms\" dur=\"66.666666666666"
        + "67ms\" attributeName=\"width\" from=\"4\" to=\"30\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"166.66666666666666ms\" dur=\"66.666666666666"
        + "67ms\" attributeName=\"height\" from=\"4\" to=\"23\" fill=\"freeze\" />\n" + 
        "</rect>\n" + 
        "<ellipse id=\"Adam\" cx=\"20.0\"  cy=\"30.0\" rx=\"10\" ry=\"1\" fill=\"rgb(3,30,57)"
        + "\" visibility=\"visible\" >\n" + 
        "<animate attributesType=\"xml\" begin=\"100.0ms\" dur=\"900.0ms\" attributeName=\"fi"
        + "ll\" from=\"rgb(3,30,57)\" to=\"rgb(200,200,200)\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"100.0ms\" dur=\"900.0ms\" attributeName=\"cx"
        + "\" from=\"20.0\" to=\"300.0\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"100.0ms\" dur=\"900.0ms\" attributeName=\"cy"
        + "\" from=\"30.0\" to=\"300.0\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"100.0ms\" dur=\"900.0ms\" attributeName=\"rx\" "
        + "from=\"10\" to=\"6\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"100.0ms\" dur=\"900.0ms\" attributeName=\"ry\" "
        + "from=\"1\" to=\"36\" fill=\"freeze\" />\n" + 
        "</ellipse>\n" + 
        "<rect id=\"Daniel\" x=\"30.0\"  y=\"30.0\" width=\"10\" height=\"10\" fill=\"rgb(30,78,1"
        + "80)\" visibility=\"visible\" >\n" + 
        "<animate attributesType=\"xml\" begin=\"100.0ms\" dur=\"633.3333333333333ms\" attributeN"
        + "ame=\"fill\" from=\"rgb(30,78,180)\" to=\"rgb(0,13,255)\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"100.0ms\" dur=\"633.3333333333333ms\" attributeNa"
        + "me=\"x\" from=\"30.0\" to=\"100.0\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"100.0ms\" dur=\"633.3333333333333ms\" attributeNa"
        + "me=\"y\" from=\"30.0\" to=\"100.0\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"100.0ms\" dur=\"633.3333333333333ms\" attributeN"
        + "ame=\"width\" from=\"10\" to=\"100\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"100.0ms\" dur=\"633.3333333333333ms\" attributeNa"
        + "me=\"height\" from=\"10\" to=\"100\" fill=\"freeze\" />\n" + 
        "</rect>\n" + 
        "<ellipse id=\"Vido\" cx=\"200.0\"  cy=\"200.0\" rx=\"0\" ry=\"0\" fill=\"rgb(0,0,0)\" vis"
        + "ibility=\"visible\" >\n" + 
        "<animate attributesType=\"xml\" begin=\"100.0ms\" dur=\"666.6666666666666ms\" attributeNa"
        + "me=\"fill\" from=\"rgb(0,0,0)\" to=\"rgb(90,36,78)\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"100.0ms\" dur=\"666.6666666666666ms\" attributeNam"
        + "e=\"cx\" from=\"200.0\" to=\"250.0\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"100.0ms\" dur=\"666.6666666666666ms\" attributeN"
        + "ame=\"cy\" from=\"200.0\" to=\"250.0\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"100.0ms\" dur=\"666.6666666666666ms\" attributeN"
        + "ame=\"rx\" from=\"0\" to=\"11\" fill=\"freeze\" />\n" + 
        "<animate attributesType=\"xml\" begin=\"100.0ms\" dur=\"666.6666666666666ms\" attributeNa"
        + "me=\"ry\" from=\"0\" to=\"17\" fill=\"freeze\" />\n" + 
        "</ellipse>\n" + 
        "\n" + 
        "</svg>\n", v1.printView(m1));
  }
}
