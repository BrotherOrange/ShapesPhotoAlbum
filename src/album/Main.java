package album;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Jihao Zhang
 * @version 1.0
 * @date 4/18/22
 * @description album
 */
public class Main {
  public static void main(String[] args) throws IllegalArgumentException {
    String view = "";
    String in = "";
    String out = "";
    int xMax = 1000;
    int yMax = 1000;
    for (int i = 0; i < args.length; i++) {
      if (args[i].equalsIgnoreCase("-v") || args[i].equals("-view")) {
        view = args[++i];
      }
      if (args[i].equalsIgnoreCase("-in")) {
        in = args[++i];
      }
      if (args[i].equalsIgnoreCase("-out")) {
        out = args[++i];
      }
      try {
        if (Integer.parseInt(args[i]) > 0) {
          xMax = Integer.parseInt(args[i]);
          yMax = Integer.parseInt(args[++i]);
          if (xMax <= 0 || yMax <= 0) {
            throw new IllegalArgumentException("Illegal size arguments!");
          }
        }
      } catch (NumberFormatException ignored) {
      }
    }
    play(view, in, out, xMax, yMax);
  }

  private static void play(String view, String in, String out, int xMax, int yMax) {
    IShapesAlbumController controller = ShapesAlbumController.getInstance();
    try {
      Readable input = new FileReader(in);
      Appendable output = null;
      if (view.equals("web") && !out.equals("")) {
        output = new FileWriter(out);
      }
      controller.run(input, output, xMax, yMax);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
