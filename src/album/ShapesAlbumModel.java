package album;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * The type Shapes album model.
 *
 * @author Jihao Zhang
 * @version 1.0
 * @date 4 /5/22
 * @description album
 */
public class ShapesAlbumModel implements IShapesAlbum {
  private final static IShapesAlbum INSTANCE = new ShapesAlbumModel();
  private final List<IShape> shapeList = new ArrayList<>();
  private final Set<String> nameSet = new HashSet<>();
  private final List<ISnapshot> snapshotList = new ArrayList<>();
  private final List<String> snapshotIDList = new ArrayList<>();
  private final Random idPool = new Random();

  private ShapesAlbumModel() {}

  /**
   * Gets the only instance of the canvas Model of the Shapes Album.
   *
   * @return the instance
   */
  public static IShapesAlbum getInstance() {
    return INSTANCE;
  }

  @Override
  public IShape getShape(String name) {
    if (nameSet.contains(name)) {
      for (IShape shape : shapeList) {
        if (shape.getName().equals(name)) {
          return shape;
        }
      }
    }
    return null;
  }

  @Override
  public ISnapshot getSnapshot(String ID) {
    for (ISnapshot snapshot : snapshotList) {
      if (snapshot.getID().equals(ID)) {
        return snapshot;
      }
    }
    return null;
  }

  @Override
  public List<IShape> getShapeList() {
    return shapeList;
  }

  @Override
  public List<ISnapshot> getSnapshotList() {
    return snapshotList;
  }

  @Override
  public List<String> getSnapshotIDList() {
    return snapshotIDList;
  }

  @Override
  public void addShape(IShape newShape) {
    nameSet.add(newShape.getName());
    shapeList.add(newShape);
  }

  @Override
  public void clearShapes() {
    shapeList.clear();
  }

  @Override
  public void clearSnapshots() {
    snapshotList.clear();
  }

  @Override
  public void reboot() {
    clearShapes();
    clearSnapshots();
  }

  @Override
  public boolean createShape(String type, String name, double x, double y, double colorR, double colorG,
                          double colorB, double firstAttribute, double secondAttribute) {
    if (!nameSet.contains(name)){
      IShape shape;
      if (type.equalsIgnoreCase("oval")) {
        shape = new Oval(name, x, y, colorR, colorG, colorB, firstAttribute, secondAttribute);
        addShape(shape);
        return true;
      } else if (type.equalsIgnoreCase("rectangle")) {
        shape = new Rectangle(name, x, y, colorR, colorG, colorB, firstAttribute, secondAttribute);
        addShape(shape);
        return true;
      }
    }
    return false;
  }

  @Override
  public void createSnapshot(String description) {
    LocalDateTime myDateObj = LocalDateTime.now();
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    String formattedDate = myDateObj.format(myFormatObj);
    long id = idPool.nextLong(1000000, 9999999);
    ISnapshot newSnapeshot = new Snapshot(id, formattedDate, description, shapeList);
    snapshotList.add(newSnapeshot);
    snapshotIDList.add(newSnapeshot.getID());
  }

  @Override
  public boolean moveShape(String name, double posX, double posY) {
    if (containsName(name)) {
      IShape result = getShape(name);
      result.move(posX, posY);
      return true;
    }
    return false;
  }

  @Override
  public boolean resizeShape(String name, double first, double second) {
    if (containsName(name)) {
      IShape result = getShape(name);
      result.changeFirstAttribute(first);
      result.changeSecondAttribute(second);
      return true;
    }
    return false;
  }

  @Override
  public boolean colorShape(String name, double colorR, double colorG, double colorB) {
    if (containsName(name)) {
      IShape result = getShape(name);
      result.changeColor(colorR, colorG, colorB);
      return true;
    }
    return false;
  }

  @Override
  public boolean containsName(String name) {
    return nameSet.contains(name);
  }

  @Override
  public boolean remove(String name) {
    if (containsName(name)) {
      for (int i = 0; i < shapeList.size(); i++) {
        if (shapeList.get(i).getName().equals(name)) {
          shapeList.remove(i);
          nameSet.remove(name);
          return true;
        }
      }
    }
    return false;
  }
}
