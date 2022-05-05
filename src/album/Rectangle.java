package album;

/**
 * The type Rectangle.
 *
 * @author Jihao Zhang
 * @version 1.0
 * @date 4 /5/22
 * @description album
 */
public class Rectangle extends AbstractShape {
  private double width;
  private double height;

  /**
   * Instantiates a new Rectangle.
   *
   * @param name   the name
   * @param x      the x
   * @param y      the y
   * @param colorR the color r
   * @param colorG the color g
   * @param colorB the color b
   * @param width  the width
   * @param height the height
   * @throws IllegalArgumentException the illegal argument exception
   */
  public Rectangle(String name, double x, double y, double colorR,
              double colorG, double colorB, double width, double height)
          throws IllegalArgumentException {
    super(name, x, y, colorR, colorG, colorB);
    if (!setWidth(width) || !setHeight(height)) {
      throw new IllegalArgumentException("Illegal width or height!");
    }
    type = "rectangle";
  }

  @Override
  public boolean changeFirstAttribute(double newVal) {
    return setWidth(newVal);
  }

  @Override
  public boolean changeSecondAttribute(double newVal) {
    return setHeight(newVal);
  }

  @Override
  public double getFirstAttribute() {
    return getWidth();
  }

  @Override
  public double getSecondAttribute() {
    return getHeight();
  }

  private boolean setWidth(double width) {
    if (width <= 0) {
      return false;
    }
    this.width = width;
    return true;
  }

  private boolean setHeight(double height) {
    if (height <= 0) {
      return false;
    }
    this.height = height;
    return true;
  }

  private double getWidth() {
    return width;
  }

  private double getHeight() {
    return height;
  }

  @Override
  public IShape copy() {
    return new Rectangle(name, positionX, positionY, colorR, colorG, colorB, width, height);
  }

  @Override
  public String toString() {
    return String.format("Name: %s\n"
            + "Type: rectangle\n"
            + "Center: (%.1f, %.1f), Width: %.1f, Height: %.1f, Color: (%.1f, %.1f, %.1f)\n",
            name, positionX, positionY, width, height, colorR, colorG, colorB);
  }
}
