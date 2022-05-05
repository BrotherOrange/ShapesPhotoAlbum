package album;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Snapshot.
 *
 * @author Jihao Zhang
 * @version 1.0
 * @date 4 /5/22
 * @description album
 */
public class Snapshot implements ISnapshot {
  private final long ID;
  private final String timeStamp;
  private final String description;
  private final List<IShape> shapes;

  /**
   * Instantiates a new Snapshot.
   *
   * @param ID          the id
   * @param timeStamp   the time stamp
   * @param description the description
   * @param shapes      the shapes
   */
  public Snapshot(long ID, String timeStamp, String description, List<IShape> shapes) {
    this.ID = ID;
    this.timeStamp = timeStamp;
    this.description = description;
    this.shapes = new ArrayList<>();
    copyShapes(shapes, this.shapes);
  }

  /**
   * Copy shapes.
   *
   * @param src the src
   * @param dst the dst
   */
  public static void copyShapes(List<IShape> src, List<IShape> dst) {
    dst.clear();
    for (IShape shape : src) {
      dst.add(shape.copy());
    }
  }

  @Override
  public List<IShape> getShapes() {
    return this.shapes;
  }

  @Override
  public String getID() {
    String[] dateAndTIme = timeStamp.split(" ");
    return dateAndTIme[0] + "T" + dateAndTIme[1] + "." + ID;
  }

  @Override
  public String getTimeStamp() {
    return timeStamp;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public String toString() {
    String shapesDescription = "";
    for (IShape shape : shapes) {
      shapesDescription += shape.toString() + "\n";
    }
    return String.format("Snapshot ID: %s\n"
            + "Timestamp: %s\n"
            + "Description: %s\n"
            + "Shape Information:\n"
            + "%s",
            getID(), timeStamp, description, shapesDescription);
  }
}
