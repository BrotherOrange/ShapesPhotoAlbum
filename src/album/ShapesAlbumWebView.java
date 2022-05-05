package album;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Jihao Zhang
 * @version 1.0
 * @date 4/18/22
 * @description album
 */
public class ShapesAlbumWebView implements IShapesAlbumWebView {
  private final static IShapesAlbumWebView INSTANCE = new ShapesAlbumWebView();
  private final IShapesAlbum model = ShapesAlbumModel.getInstance();
  private Appendable out;

  private int xMax;
  private int yMax;

  public static IShapesAlbumWebView getInstance() {
    return INSTANCE;
  }

  @Override
  public void go(Appendable out, int xMax, int yMax) {
    this.out = out;
    this.xMax = xMax;
    this.yMax = yMax;
    createHead();
    createBody();
    try {
      ((FileWriter) out).flush();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassCastException ignore) {
    }
  }

  private void createHead() {
    try {
      out.append("<!DOCTYPE html>\n" +
              "<html lang=\"en\">\n" +
              "<head>\n" +
              "    <meta charset=\"UTF-8\">\n" +
              "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
              "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
              "    <title>Shapes Photo Album</title>\n" +
              "</head>\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void createBody() {
    try {
      out.append("<body>\n");
      out.append("<h1>Shapes Photo Album</h1>\n");
      for (ISnapshot snapshot : model.getSnapshotList()) {
        createSVG(snapshot);
      }
      out.append("</body>\n" +
              "</html>\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void createSVG(ISnapshot snapshot) {
    try {
      out.append(String.format("<div style=\"background-color: cyan;\">\n" +
              "        <h2>%s</h2>\n", snapshot.getID()));
      if (!snapshot.getDescription().equals("")) {
        out.append(String.format("        <h3>%s</h3>\n", snapshot.getDescription()));
      }
      out.append(String.format("        <svg width=\"%d\" height=\"%d\">\n", xMax, yMax));
      for (IShape shape : snapshot.getShapes()) {
        if (shape.getType().equals("rectangle")) {
          out.append(String.format("            <rect x=\"%d\" y=\"%d\" width=\"%d\" height=\"%d\" " +
                          "fill=\"rgb(%d, %d, %d)\" />\n",
                  (int) shape.getPositionX(),
                  (int) shape.getPositionY(),
                  (int) shape.getFirstAttribute(),
                  (int) shape.getSecondAttribute(),
                  (int) shape.getColorR(),
                  (int) shape.getColorG(),
                  (int) shape.getColorB()));
        } else {
          out.append(String.format("            <ellipse cx=\"%d\" cy=\"%d\" rx=\"%d\" ry=\"%d\" " +
                          "fill=\"rgb(%d, %d, %d)\" />\n",
                  (int) shape.getPositionX(),
                  (int) shape.getPositionY(),
                  (int) shape.getFirstAttribute(),
                  (int) shape.getSecondAttribute(),
                  (int) shape.getColorR(),
                  (int) shape.getColorG(),
                  (int) shape.getColorB()));
        }
      }
      out.append("        </svg>\n" +
              "    </div>\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
