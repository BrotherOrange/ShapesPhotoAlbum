package album;

/**
 * The interface Shape.
 *
 * @author Jihao Zhang
 * @version 1.0
 * @date 4 /5/22
 * @description album
 */
public interface IShape {
  /**
   * Move, which is change the position of its position point.
   *
   * @param x the x
   * @param y the y
   */
  void move(double x, double y);

  /**
   * Change color RGB values directlu.
   *
   * @param r the r
   * @param g the g
   * @param b the b
   * @return the boolean
   */
  boolean changeColor(double r, double g, double b);

  /**
   * Change positions, which is same as move function.
   *
   * @param x the x
   * @param y the y
   */
  void changePos(double x, double y);

  /**
   * Change first attribute boolean.
   *
   * @param newVal the new val
   * @return the boolean
   */
  boolean changeFirstAttribute(double newVal);

  /**
   * Change second attribute boolean.
   *
   * @param newVal the new val
   * @return the boolean
   */
  boolean changeSecondAttribute(double newVal);

  /**
   * Gets name.
   *
   * @return the name
   */
  String getName();

  /**
   * Gets first attribute.
   *
   * @return the first attribute
   */
  double getFirstAttribute();

  /**
   * Gets second attribute.
   *
   * @return the second attribute
   */
  double getSecondAttribute();

  /**
   * Gets position x.
   *
   * @return the position x
   */
  double getPositionX();

  /**
   * Gets position y.
   *
   * @return the position y
   */
  double getPositionY();

  /**
   * Gets R value of the color.
   *
   * @return the color r
   */
  double getColorR();

  /**
   * Gets G value of the color.
   *
   * @return the color g
   */
  double getColorG();

  /**
   * Gets B value of the color.
   *
   * @return the color b
   */
  double getColorB();

  /**
   * Gets the type name of the shape.
   *
   * @return the type
   */
  String getType();

  /**
   * Copy the shape into a new one.
   *
   * @return the new copied shape
   */
  IShape copy();
}
