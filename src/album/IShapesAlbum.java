package album;

import java.util.List;

/**
 * The interface Shapes Album Model.
 *
 * @author Jihao Zhang
 * @version 1.0
 * @date 4 /5/22
 * @description album
 */
public interface IShapesAlbum {
  /**
   * Gets a shape by its name.
   *
   * @param name the name
   * @return the shape
   */
  IShape getShape(String name);

  /**
   * Gets a snapshot by its ID.
   *
   * @param ID the id
   * @return the snapshot
   */
  ISnapshot getSnapshot(String ID);

  /**
   * Gets the list of all IShapes on the canvas currently.
   *
   * @return the shape list
   */
  List<IShape> getShapeList();

  /**
   * Gets the list of all ISnapshots saved in the model currently.
   *
   * @return the snapshot list
   */
  List<ISnapshot> getSnapshotList();

  /**
   * Gets snapshot id list.
   *
   * @return the snapshot id list
   */
  List<String> getSnapshotIDList();

  /**
   * Add a created shape into the canvas.
   *
   * @param newShape the new shape
   */
  void addShape(IShape newShape);

  /**
   * Clear all shapes on canvas.
   */
  void clearShapes();

  /**
   * Clear all snapshots in the model.
   */
  void clearSnapshots();

  /**
   * Reboot the Model of Shapes Album.
   */
  void reboot();

  /**
   * Create a shape of a defined color with RGB value.
   *
   * @param type            the type of the shape: oval / rectangle
   * @param name            the name of the shape
   * @param x               the x position of the shapes position point
   * @param y               the y position of the shapes position point
   * @param colorR          the R value of the color
   * @param colorG          the G value of the color
   * @param colorB          the B value of the color
   * @param firstAttribute  the first attribute
   * @param secondAttribute the second attribute
   * @return the boolean
   */
  boolean createShape(String type, String name, double x, double y, double colorR, double colorG,
                   double colorB, double firstAttribute, double secondAttribute);

  /**
   * Create a snapshot with a description.
   *
   * @param description the description
   */
  void createSnapshot(String description);

  /**
   * Move shape boolean.
   *
   * @param name the name
   * @param posX the pos x
   * @param posY the pos y
   * @return the boolean
   */
  boolean moveShape(String name, double posX, double posY);

  /**
   * Resize shape boolean.
   *
   * @param name   the name
   * @param first  the first
   * @param second the second
   * @return the boolean
   */
  boolean resizeShape(String name, double first, double second);

  /**
   * Color shape boolean.
   *
   * @param name   the name
   * @param colorR the color r
   * @param colorG the color g
   * @param colorB the color b
   * @return the boolean
   */
  boolean colorShape(String name, double colorR, double colorG, double colorB);

  /**
   * Judge if the Model contains a shape's name or not.
   *
   * @param name the name
   * @return the boolean
   */
  boolean containsName(String name);

  /**
   * Remove boolean.
   *
   * @param name the name
   * @return the boolean
   */
  boolean remove(String name);
}
