package album;

import java.util.Scanner;

/**
 * @author Jihao Zhang
 * @version 1.0
 * @date 4/18/22
 * @description album
 */
public class ShapesAlbumController implements IShapesAlbumController {
  private static final IShapesAlbumController INSTANCE = new ShapesAlbumController();
  private final IShapesAlbum model;
  private Readable in;
  private Appendable out;
  private Scanner scan;

  private ShapesAlbumController() {
    model = ShapesAlbumModel.getInstance();
  }

  @Override
  public void run(Readable in, Appendable out, int xMax, int yMax) {
    this.in = in;
    this.out = out;
    scan = new Scanner(in);
    readAll();
    if (out == null) {
      runGraphical(xMax, yMax);
    }
    else {
      runWeb(xMax, yMax);
    }
  }

  public static IShapesAlbumController getInstance() {
    return INSTANCE;
  }

  private void runGraphical(int xMax, int yMax) {
    IShapesAlbumGraphicalView album = ShapesAlbumGraphicalView.getInstance();
    album.go(xMax, yMax);
  }

  private void runWeb(int xMax, int yMax) {
    IShapesAlbumWebView album = ShapesAlbumWebView.getInstance();
    album.go(out, xMax, yMax);
  }

  private void readAll() {
    while(scan.hasNextLine()) {
      String line = scan.nextLine().trim();
      if (line.length() == 0 || line.charAt(0) == '#') {
        continue;
      }
      String[] commands = line.split("\\s+");
      switch (commands[0].toLowerCase()) {
        case "shape" -> {
          addShape(commands);
        }
        case "move" -> {
          moveShape(commands);
        }
        case "resize" -> {
          resizeShape(commands);
        }
        case "color" -> {
          colorShape(commands);
        }
        case "remove" -> {
          removeShape(commands);
        }
        case "snapshot" -> {
          takeSnapshot(commands);
        }
        default -> {
          System.out.println("Bad command!");
        }
      }
    }
  }

  private void addShape(String[] commands) {
    try {
      String name = commands[1];
      String type = commands[2].toLowerCase();
      double positionX = Double.parseDouble(commands[3]);
      double positionY = Double.parseDouble(commands[4]);
      double firstAttribute = Double.parseDouble(commands[5]);
      double secondAttribute = Double.parseDouble(commands[6]);
      double colorR = Double.parseDouble(commands[7]);
      double colorG = Double.parseDouble(commands[8]);
      double colorB = Double.parseDouble(commands[9]);
      model.createShape(type, name, positionX, positionY,
              colorR, colorG, colorB, firstAttribute, secondAttribute);
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }
  }

  private void moveShape(String[] commands) throws IllegalArgumentException {
    try {
      String name = commands[1];
      double positionX = Double.parseDouble(commands[2]);
      double positionY = Double.parseDouble(commands[3]);
      boolean isSuccessful = model.moveShape(name, positionX, positionY);
      if (!isSuccessful) {
        throw new IllegalArgumentException("Moved shape not found!");
      }
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }
  }

  private void resizeShape(String[] commands) throws IllegalArgumentException {
    try {
      String name = commands[1];
      double firstAttribute = Double.parseDouble(commands[2]);
      double secondAttribute = Double.parseDouble(commands[3]);
      boolean isSuccessful = model.resizeShape(name, firstAttribute, secondAttribute);
      if (!isSuccessful) {
        throw new IllegalArgumentException("Resized shape not found!");
      }
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }
  }

  private void colorShape(String[] commands) throws IllegalArgumentException {
    try {
      String name = commands[1];
      double colorR = Double.parseDouble(commands[2]);
      double colorG = Double.parseDouble(commands[3]);
      double colorB = Double.parseDouble(commands[4]);
      boolean isSuccessful = model.colorShape(name, colorR, colorG, colorB);
      if (!isSuccessful) {
        throw new IllegalArgumentException("Colored shape not found!");
      }
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }
  }

  private void takeSnapshot(String[] commands) {
    String description = "";
    for (int i = 1; i < commands.length; i++) {
      if (i != 1) {
        description += " ";
      }
      description += commands[i];
    }
    model.createSnapshot(description);
  }

  private void removeShape(String[] commands) throws IllegalArgumentException {
    String name = commands[1];
    boolean isSuccessful = model.remove(name);
    if (!isSuccessful) {
      throw new IllegalArgumentException("Removed shape not found!");
    }
  }
}
