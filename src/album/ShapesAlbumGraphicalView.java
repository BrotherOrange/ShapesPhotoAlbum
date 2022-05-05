package album;

/**
 * @author Jihao Zhang
 * @version 1.0
 * @date 4/18/22
 * @description album
 */
public class ShapesAlbumGraphicalView implements IShapesAlbumGraphicalView {

  private static final IShapesAlbumGraphicalView INSTANCE = new ShapesAlbumGraphicalView();
  private final IShapesAlbum model = ShapesAlbumModel.getInstance();
  private int xMax;
  private int yMax;

  private ShapesAlbumGraphicalView() {
  }

  public static IShapesAlbumGraphicalView getInstance() {
    return INSTANCE;
  }

  @Override
  public void go(int xMax, int yMax) {
    this.xMax = xMax;
    this.yMax = yMax;
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        drawFrame();
      }
    });
  }

  private void drawFrame() {
    GraphicsFrame album = new GraphicsFrame(xMax, yMax);
    album.setVisible(true);
  }
}
