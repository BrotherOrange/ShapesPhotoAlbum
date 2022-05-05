package album;

/**
 * The type Oval.
 *
 * @author Jihao Zhang
 * @version 1.0
 * @date 4 /5/22
 * @description album
 */
public class Oval extends AbstractShape {
  private double xRadius;
  private double yRadius;

  /**
   * Instantiates a new Oval.
   *
   * @param name    the name
   * @param x       the x
   * @param y       the y
   * @param colorR  the color r
   * @param colorG  the color g
   * @param colorB  the color b
   * @param xRadius the x radius
   * @param yRadius the y radius
   * @throws IllegalArgumentException the illegal argument exception
   */
  public Oval(String name, double x, double y, double colorR,
              double colorG, double colorB, double xRadius, double yRadius)
          throws IllegalArgumentException {
    super(name, x, y, colorR, colorG, colorB);
    if (!setXRadius(xRadius) || !setYRadius(yRadius)) {
      throw new IllegalArgumentException("Illegal Radius!");
    }
    type = "oval";
  }

  @Override
  public boolean changeFirstAttribute(double newVal) {
    return setXRadius(newVal);
  }

  @Override
  public boolean changeSecondAttribute(double newVal) {
    return setYRadius(newVal);
  }

  @Override
  public double getFirstAttribute() {
    return getXRadius();
  }

  @Override
  public double getSecondAttribute() {
    return getYRadius();
  }

  private boolean setXRadius(double xRadius) {
    if (xRadius <= 0) {
      return false;
    }
    this.xRadius = xRadius;
    return true;
  }

  private boolean setYRadius(double yRadius) {
    if (yRadius <= 0) {
      return false;
    }
    this.yRadius = yRadius;
    return true;
  }

  private double getXRadius() {
    return xRadius;
  }

  private double getYRadius() {
    return yRadius;
  }

  @Override
  public IShape copy() {
    return new Oval(name, positionX, positionY, colorR, colorG, colorB, xRadius, yRadius);
  }

  @Override
  public String toString() {
    return String.format("Name: %s\n"
            + "Type: oval\n"
            + "Center: (%.1f, %.1f), X radius: %.1f, Y radius: %.1f, Color: (%.1f, %.1f, %.1f)\n",
            name, positionX, positionY, xRadius, yRadius, colorR, colorG, colorB);
  }
}
