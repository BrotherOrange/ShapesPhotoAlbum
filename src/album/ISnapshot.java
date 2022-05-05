package album;

import java.util.List;

/**
 * The interface Snapshot.
 *
 * @author Jihao Zhang
 * @version 1.0
 * @date 4 /5/22
 * @description album
 */
public interface ISnapshot {
  /**
   * Gets all shapes included in the snapshot.
   *
   * @return the shapes
   */
  List<IShape> getShapes();

  /**
   * Gets the snapshot's id.
   *
   * @return the id
   */
  String getID();

  /**
   * Gets the time stamp of the snapshot.
   *
   * @return the time stamp
   */
  String getTimeStamp();

  /**
   * Gets the snapshot's description.
   *
   * @return the description
   */
  String getDescription();
}
