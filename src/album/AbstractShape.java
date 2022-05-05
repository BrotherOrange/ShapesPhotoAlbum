package album;

/**
 * The type Abstract shape.
 *
 * @author Jihao Zhang
 * @version 1.0
 * @date 4 /5/22
 * @description album
 */
public abstract class AbstractShape implements IShape {
  /**
   * The Name.
   */
  protected String name;
  /**
   * The Position x.
   */
  protected double positionX;
  /**
   * The Position y.
   */
  protected double positionY;
  /**
   * The Color r.
   */
  protected double colorR;
  /**
   * The Color g.
   */
  protected double colorG;
  /**
   * The Color b.
   */
  protected double colorB;
  /**
   * The Type.
   */
  protected String type;

  /**
   * Instantiates a new Abstract shape.
   *
   * @param name      the name
   * @param positionX the position x
   * @param positionY the position y
   * @param colorR    the color r
   * @param colorG    the color g
   * @param colorB    the color b
   * @throws IllegalArgumentException the illegal argument exception
   */
  public AbstractShape(String name, double positionX, double positionY,
                       double colorR, double colorG, double colorB)
          throws IllegalArgumentException {
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Empty name!");
    }
    this.name = name;
    this.positionX = positionX;
    this.positionY = positionY;
    if (!changeColor(colorR, colorG, colorB)) {
      throw new IllegalArgumentException("Illegal color!");
    }
  }

  @Override
  public void move(double x, double y) {
    positionX = x;
    positionY = y;
  }

  @Override
  public boolean changeColor(double r, double g, double b) {
    if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
      return false;
    }
    colorR = r;
    colorG = g;
    colorB = b;
    return true;
  }

  @Override
  public void changePos(double x, double y) {
    move(x, y);
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public double getPositionX() {
    return positionX;
  }

  @Override
  public double getPositionY() {
    return positionY;
  }

  @Override
  public double getColorR() {
    return colorR;
  }

  @Override
  public double getColorG() {
    return colorG;
  }

  @Override
  public double getColorB() {
    return colorB;
  }

  @Override
  public String getType() {
    return type;
  }
}
