package album;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;


/**
 * @author Jihao Zhang
 * @version 1.0
 * @date 4/23/22
 * @description album
 */
public class IShapesAlbumWebViewTest {
  private final IShapesAlbum model = ShapesAlbumModel.getInstance();
  private final IShapesAlbumWebView view = ShapesAlbumWebView.getInstance();
  private Appendable out;

  @Before
  public void setUp() {
    model.reboot();
  }

  @Test
  public void testNoSnapshot() {
    out = new StringBuilder();
    view.go(out, 500, 500);
    assertEquals("<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "    <title>Shapes Photo Album</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "<h1>Shapes Photo Album</h1>\n" +
            "</body>\n" +
            "</html>\n", out.toString());
  }

  @Test
  public void testEmptySnapshotWithNoDescription() {
    model.createSnapshot("");
    out = new StringBuilder();
    view.go(out, 1000, 1000);
    assertEquals(String.format("<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "    <title>Shapes Photo Album</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "<h1>Shapes Photo Album</h1>\n" +
            "<div style=\"background-color: cyan;\">\n" +
            "        <h2>%s</h2>\n" +
            "        <svg width=\"1000\" height=\"1000\">\n" +
            "        </svg>\n" +
            "    </div>\n" +
            "</body>\n" +
            "</html>\n", model.getSnapshotList().get(0).getID()), out.toString());
  }

  @Test
  public void testEmptySnapshotWithDescription() {
    model.createSnapshot("Empty Snapshot");
    out = new StringBuilder();
    view.go(out, 1000, 1000);
    assertEquals(String.format("<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "    <title>Shapes Photo Album</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "<h1>Shapes Photo Album</h1>\n" +
            "<div style=\"background-color: cyan;\">\n" +
            "        <h2>%s</h2>\n" +
            "        <h3>Empty Snapshot</h3>\n" +
            "        <svg width=\"1000\" height=\"1000\">\n" +
            "        </svg>\n" +
            "    </div>\n" +
            "</body>\n" +
            "</html>\n", model.getSnapshotList().get(0).getID()), out.toString());
  }

  @Test
  public void testNormal() {
    model.createShape("rectangle", "R1", 2, 3, 128, 0, 255, 10, 10);
    model.createSnapshot("first");
    model.createShape("oval", "O1", 4, 5, 24, 128, 0, 20, 20);
    model.createSnapshot("second");
    model.createShape("rectangle", "R2", 2, 3, 255, 0, 255, 30, 30);
    model.createSnapshot("third");
    model.moveShape("R1", 12, 34);
    model.createSnapshot("fourth");
    model.colorShape("O1", 255, 255, 255);
    model.createSnapshot("fifth");
    model.resizeShape("R2", 100, 100);
    model.createSnapshot("sixth");
    model.remove("R1");
    model.createSnapshot("seventh");

    out = new StringBuilder();
    view.go(out, 1000, 1000);
    assertEquals(String.format("<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "    <title>Shapes Photo Album</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "<h1>Shapes Photo Album</h1>\n" +
            "<div style=\"background-color: cyan;\">\n" +
            "        <h2>%s</h2>\n" +
            "        <h3>first</h3>\n" +
            "        <svg width=\"1000\" height=\"1000\">\n" +
            "            <rect x=\"2\" y=\"3\" width=\"10\" height=\"10\" fill=\"rgb(128, 0, 255)\" />\n" +
            "        </svg>\n" +
            "    </div>\n" +
            "<div style=\"background-color: cyan;\">\n" +
            "        <h2>%s</h2>\n" +
            "        <h3>second</h3>\n" +
            "        <svg width=\"1000\" height=\"1000\">\n" +
            "            <rect x=\"2\" y=\"3\" width=\"10\" height=\"10\" fill=\"rgb(128, 0, 255)\" />\n" +
            "            <ellipse cx=\"4\" cy=\"5\" rx=\"20\" ry=\"20\" fill=\"rgb(24, 128, 0)\" />\n" +
            "        </svg>\n" +
            "    </div>\n" +
            "<div style=\"background-color: cyan;\">\n" +
            "        <h2>%s</h2>\n" +
            "        <h3>third</h3>\n" +
            "        <svg width=\"1000\" height=\"1000\">\n" +
            "            <rect x=\"2\" y=\"3\" width=\"10\" height=\"10\" fill=\"rgb(128, 0, 255)\" />\n" +
            "            <ellipse cx=\"4\" cy=\"5\" rx=\"20\" ry=\"20\" fill=\"rgb(24, 128, 0)\" />\n" +
            "            <rect x=\"2\" y=\"3\" width=\"30\" height=\"30\" fill=\"rgb(255, 0, 255)\" />\n" +
            "        </svg>\n" +
            "    </div>\n" +
            "<div style=\"background-color: cyan;\">\n" +
            "        <h2>%s</h2>\n" +
            "        <h3>fourth</h3>\n" +
            "        <svg width=\"1000\" height=\"1000\">\n" +
            "            <rect x=\"12\" y=\"34\" width=\"10\" height=\"10\" fill=\"rgb(128, 0, 255)\" />\n" +
            "            <ellipse cx=\"4\" cy=\"5\" rx=\"20\" ry=\"20\" fill=\"rgb(24, 128, 0)\" />\n" +
            "            <rect x=\"2\" y=\"3\" width=\"30\" height=\"30\" fill=\"rgb(255, 0, 255)\" />\n" +
            "        </svg>\n" +
            "    </div>\n" +
            "<div style=\"background-color: cyan;\">\n" +
            "        <h2>%s</h2>\n" +
            "        <h3>fifth</h3>\n" +
            "        <svg width=\"1000\" height=\"1000\">\n" +
            "            <rect x=\"12\" y=\"34\" width=\"10\" height=\"10\" fill=\"rgb(128, 0, 255)\" />\n" +
            "            <ellipse cx=\"4\" cy=\"5\" rx=\"20\" ry=\"20\" fill=\"rgb(255, 255, 255)\" />\n" +
            "            <rect x=\"2\" y=\"3\" width=\"30\" height=\"30\" fill=\"rgb(255, 0, 255)\" />\n" +
            "        </svg>\n" +
            "    </div>\n" +
            "<div style=\"background-color: cyan;\">\n" +
            "        <h2>%s</h2>\n" +
            "        <h3>sixth</h3>\n" +
            "        <svg width=\"1000\" height=\"1000\">\n" +
            "            <rect x=\"12\" y=\"34\" width=\"10\" height=\"10\" fill=\"rgb(128, 0, 255)\" />\n" +
            "            <ellipse cx=\"4\" cy=\"5\" rx=\"20\" ry=\"20\" fill=\"rgb(255, 255, 255)\" />\n" +
            "            <rect x=\"2\" y=\"3\" width=\"100\" height=\"100\" fill=\"rgb(255, 0, 255)\" />\n" +
            "        </svg>\n" +
            "    </div>\n" +
            "<div style=\"background-color: cyan;\">\n" +
            "        <h2>%s</h2>\n" +
            "        <h3>seventh</h3>\n" +
            "        <svg width=\"1000\" height=\"1000\">\n" +
            "            <ellipse cx=\"4\" cy=\"5\" rx=\"20\" ry=\"20\" fill=\"rgb(255, 255, 255)\" />\n" +
            "            <rect x=\"2\" y=\"3\" width=\"100\" height=\"100\" fill=\"rgb(255, 0, 255)\" />\n" +
            "        </svg>\n" +
            "    </div>\n" +
            "</body>\n" +
            "</html>\n",
            model.getSnapshotList().get(0).getID(),
            model.getSnapshotList().get(1).getID(),
            model.getSnapshotList().get(2).getID(),
            model.getSnapshotList().get(3).getID(),
            model.getSnapshotList().get(4).getID(),
            model.getSnapshotList().get(5).getID(),
            model.getSnapshotList().get(6).getID()),
            out.toString());
  }

  @Test
  public void testDemoInput() {
    Readable in = new StringReader("# Make a rectangle and oval. Color red and green\n" +
            "    shape   myrect   rectangle  200  200 50  100  255  0  0\n" +
            "    shape   myoval   oval       500  100 60  30   0 255 1\n" +
            "\n" +
            "# Take a snapshot. Optional description text follows snapshot command\n" +
            "    snapShot After first selfie\n" +
            "\n" +
            "    move myrect     300     200\n" +
            "    resize myrect   25      100\n" +
            "    move myrect     100     300\n" +
            "\n" +
            "# Take another snapshot. Again, optional description included\n" +
            "# Snapshot descriptions go up to the end of the line (no multi-line descriptions to worry about)\n" +
            "    snapShot 2nd selfie\n" +
            "\n" +
            "# Change rectangle color to blue. Move the oval\n" +
            "    color myrect    0  0  255\n" +
            "    move myoval     500   400\n" +
            "\n" +
            "# Another snapshot. This time, descriptive text is omitted.\n" +
            "    snapShot\n" +
            "\n" +
            "# Remove the rectangle, then take one more snapshot\n" +
            "    remove myrect\n" +
            "    snapshot Selfie after removing the rectangle from the picture\n");
    out = new StringBuilder();
    ShapesAlbumController.getInstance().run(in, out, 1000, 1000);
    assertEquals(String.format("<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "    <title>Shapes Photo Album</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "<h1>Shapes Photo Album</h1>\n" +
            "<div style=\"background-color: cyan;\">\n" +
            "        <h2>%s</h2>\n" +
            "        <h3>After first selfie</h3>\n" +
            "        <svg width=\"1000\" height=\"1000\">\n" +
            "            <rect x=\"200\" y=\"200\" width=\"50\" height=\"100\" fill=\"rgb(255, 0, 0)\" />\n" +
            "            <ellipse cx=\"500\" cy=\"100\" rx=\"60\" ry=\"30\" fill=\"rgb(0, 255, 1)\" />\n" +
            "        </svg>\n" +
            "    </div>\n" +
            "<div style=\"background-color: cyan;\">\n" +
            "        <h2>%s</h2>\n" +
            "        <h3>2nd selfie</h3>\n" +
            "        <svg width=\"1000\" height=\"1000\">\n" +
            "            <rect x=\"100\" y=\"300\" width=\"25\" height=\"100\" fill=\"rgb(255, 0, 0)\" />\n" +
            "            <ellipse cx=\"500\" cy=\"100\" rx=\"60\" ry=\"30\" fill=\"rgb(0, 255, 1)\" />\n" +
            "        </svg>\n" +
            "    </div>\n" +
            "<div style=\"background-color: cyan;\">\n" +
            "        <h2>%s</h2>\n" +
            "        <svg width=\"1000\" height=\"1000\">\n" +
            "            <rect x=\"100\" y=\"300\" width=\"25\" height=\"100\" fill=\"rgb(0, 0, 255)\" />\n" +
            "            <ellipse cx=\"500\" cy=\"400\" rx=\"60\" ry=\"30\" fill=\"rgb(0, 255, 1)\" />\n" +
            "        </svg>\n" +
            "    </div>\n" +
            "<div style=\"background-color: cyan;\">\n" +
            "        <h2>%s</h2>\n" +
            "        <h3>Selfie after removing the rectangle from the picture</h3>\n" +
            "        <svg width=\"1000\" height=\"1000\">\n" +
            "            <ellipse cx=\"500\" cy=\"400\" rx=\"60\" ry=\"30\" fill=\"rgb(0, 255, 1)\" />\n" +
            "        </svg>\n" +
            "    </div>\n" +
            "</body>\n" +
            "</html>\n",
            model.getSnapshotList().get(0).getID(),
            model.getSnapshotList().get(1).getID(),
            model.getSnapshotList().get(2).getID(),
            model.getSnapshotList().get(3).getID()),
            out.toString());
  }

  @Test
  public void testBuildings() {
    Readable in = new StringReader("# canvas 0 0 800 800\n" +
            "shape background rectangle 0 0 800 800 33 94 248\n" +
            "shape B0 rectangle 80 424 100 326 0 0 0\n" +
            "shape B1 rectangle 260 365 100 385 0 0 0\n" +
            "shape B2 rectangle 440 375 100 375 0 0 0\n" +
            "shape B3 rectangle 620 445 100 305 0 0 0\n" +
            "shape window000 rectangle 100 500 20 20 255 255 255\n" +
            "shape window001 rectangle 140 500 20 20 255 255 255\n" +
            "shape window010 rectangle 100 600 20 20 255 255 255\n" +
            "shape window011 rectangle 140 600 20 20 255 255 255\n" +
            "snapshot\n" +
            "shape window002 rectangle 280 500 20 20 255 255 255\n" +
            "shape window021 rectangle 320 500 20 20 255 255 255\n" +
            "shape window022 rectangle 280 600 20 20 255 255 255\n" +
            "shape window200 rectangle 320 600 20 20 255 255 255\n" +
            "snapshot\n" +
            "# Lights On!\n" +
            "shape window003 rectangle 460 500 20 20 255 255 255\n" +
            "shape window033 rectangle 500 500 20 20 255 255 255\n" +
            "shape window333 rectangle 460 600 20 20 255 255 255\n" +
            "shape window313 rectangle 500 600 20 20 255 255 255\n" +
            "\n" +
            "shape window004 rectangle 640 500 20 20 255 255 255\n" +
            "shape window044 rectangle 680 500 20 20 255 255 255\n" +
            "shape window444 rectangle 640 600 20 20 255 255 255\n" +
            "shape window414 rectangle 680 600 20 20 255 255 255\n" +
            "\n" +
            "shape moon oval 200 200 100 100 229 229 255\n" +
            "snapshot Turn on the Lights!");
    out = new StringBuilder();
    ShapesAlbumController.getInstance().run(in, out, 1000, 1000);
    assertEquals(String.format("<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "    <title>Shapes Photo Album</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "<h1>Shapes Photo Album</h1>\n" +
            "<div style=\"background-color: cyan;\">\n" +
            "        <h2>%s</h2>\n" +
            "        <svg width=\"1000\" height=\"1000\">\n" +
            "            <rect x=\"0\" y=\"0\" width=\"800\" height=\"800\" fill=\"rgb(33, 94, 248)\" />\n" +
            "            <rect x=\"80\" y=\"424\" width=\"100\" height=\"326\" fill=\"rgb(0, 0, 0)\" />\n" +
            "            <rect x=\"260\" y=\"365\" width=\"100\" height=\"385\" fill=\"rgb(0, 0, 0)\" />\n" +
            "            <rect x=\"440\" y=\"375\" width=\"100\" height=\"375\" fill=\"rgb(0, 0, 0)\" />\n" +
            "            <rect x=\"620\" y=\"445\" width=\"100\" height=\"305\" fill=\"rgb(0, 0, 0)\" />\n" +
            "            <rect x=\"100\" y=\"500\" width=\"20\" height=\"20\" fill=\"rgb(255, 255, 255)\" />\n" +
            "            <rect x=\"140\" y=\"500\" width=\"20\" height=\"20\" fill=\"rgb(255, 255, 255)\" />\n" +
            "            <rect x=\"100\" y=\"600\" width=\"20\" height=\"20\" fill=\"rgb(255, 255, 255)\" />\n" +
            "            <rect x=\"140\" y=\"600\" width=\"20\" height=\"20\" fill=\"rgb(255, 255, 255)\" />\n" +
            "        </svg>\n" +
            "    </div>\n" +
            "<div style=\"background-color: cyan;\">\n" +
            "        <h2>%s</h2>\n" +
            "        <svg width=\"1000\" height=\"1000\">\n" +
            "            <rect x=\"0\" y=\"0\" width=\"800\" height=\"800\" fill=\"rgb(33, 94, 248)\" />\n" +
            "            <rect x=\"80\" y=\"424\" width=\"100\" height=\"326\" fill=\"rgb(0, 0, 0)\" />\n" +
            "            <rect x=\"260\" y=\"365\" width=\"100\" height=\"385\" fill=\"rgb(0, 0, 0)\" />\n" +
            "            <rect x=\"440\" y=\"375\" width=\"100\" height=\"375\" fill=\"rgb(0, 0, 0)\" />\n" +
            "            <rect x=\"620\" y=\"445\" width=\"100\" height=\"305\" fill=\"rgb(0, 0, 0)\" />\n" +
            "            <rect x=\"100\" y=\"500\" width=\"20\" height=\"20\" fill=\"rgb(255, 255, 255)\" />\n" +
            "            <rect x=\"140\" y=\"500\" width=\"20\" height=\"20\" fill=\"rgb(255, 255, 255)\" />\n" +
            "            <rect x=\"100\" y=\"600\" width=\"20\" height=\"20\" fill=\"rgb(255, 255, 255)\" />\n" +
            "            <rect x=\"140\" y=\"600\" width=\"20\" height=\"20\" fill=\"rgb(255, 255, 255)\" />\n" +
            "            <rect x=\"280\" y=\"500\" width=\"20\" height=\"20\" fill=\"rgb(255, 255, 255)\" />\n" +
            "            <rect x=\"320\" y=\"500\" width=\"20\" height=\"20\" fill=\"rgb(255, 255, 255)\" />\n" +
            "            <rect x=\"280\" y=\"600\" width=\"20\" height=\"20\" fill=\"rgb(255, 255, 255)\" />\n" +
            "            <rect x=\"320\" y=\"600\" width=\"20\" height=\"20\" fill=\"rgb(255, 255, 255)\" />\n" +
            "        </svg>\n" +
            "    </div>\n" +
            "<div style=\"background-color: cyan;\">\n" +
            "        <h2>%s</h2>\n" +
            "        <h3>Turn on the Lights!</h3>\n" +
            "        <svg width=\"1000\" height=\"1000\">\n" +
            "            <rect x=\"0\" y=\"0\" width=\"800\" height=\"800\" fill=\"rgb(33, 94, 248)\" />\n" +
            "            <rect x=\"80\" y=\"424\" width=\"100\" height=\"326\" fill=\"rgb(0, 0, 0)\" />\n" +
            "            <rect x=\"260\" y=\"365\" width=\"100\" height=\"385\" fill=\"rgb(0, 0, 0)\" />\n" +
            "            <rect x=\"440\" y=\"375\" width=\"100\" height=\"375\" fill=\"rgb(0, 0, 0)\" />\n" +
            "            <rect x=\"620\" y=\"445\" width=\"100\" height=\"305\" fill=\"rgb(0, 0, 0)\" />\n" +
            "            <rect x=\"100\" y=\"500\" width=\"20\" height=\"20\" fill=\"rgb(255, 255, 255)\" />\n" +
            "            <rect x=\"140\" y=\"500\" width=\"20\" height=\"20\" fill=\"rgb(255, 255, 255)\" />\n" +
            "            <rect x=\"100\" y=\"600\" width=\"20\" height=\"20\" fill=\"rgb(255, 255, 255)\" />\n" +
            "            <rect x=\"140\" y=\"600\" width=\"20\" height=\"20\" fill=\"rgb(255, 255, 255)\" />\n" +
            "            <rect x=\"280\" y=\"500\" width=\"20\" height=\"20\" fill=\"rgb(255, 255, 255)\" />\n" +
            "            <rect x=\"320\" y=\"500\" width=\"20\" height=\"20\" fill=\"rgb(255, 255, 255)\" />\n" +
            "            <rect x=\"280\" y=\"600\" width=\"20\" height=\"20\" fill=\"rgb(255, 255, 255)\" />\n" +
            "            <rect x=\"320\" y=\"600\" width=\"20\" height=\"20\" fill=\"rgb(255, 255, 255)\" />\n" +
            "            <rect x=\"460\" y=\"500\" width=\"20\" height=\"20\" fill=\"rgb(255, 255, 255)\" />\n" +
            "            <rect x=\"500\" y=\"500\" width=\"20\" height=\"20\" fill=\"rgb(255, 255, 255)\" />\n" +
            "            <rect x=\"460\" y=\"600\" width=\"20\" height=\"20\" fill=\"rgb(255, 255, 255)\" />\n" +
            "            <rect x=\"500\" y=\"600\" width=\"20\" height=\"20\" fill=\"rgb(255, 255, 255)\" />\n" +
            "            <rect x=\"640\" y=\"500\" width=\"20\" height=\"20\" fill=\"rgb(255, 255, 255)\" />\n" +
            "            <rect x=\"680\" y=\"500\" width=\"20\" height=\"20\" fill=\"rgb(255, 255, 255)\" />\n" +
            "            <rect x=\"640\" y=\"600\" width=\"20\" height=\"20\" fill=\"rgb(255, 255, 255)\" />\n" +
            "            <rect x=\"680\" y=\"600\" width=\"20\" height=\"20\" fill=\"rgb(255, 255, 255)\" />\n" +
            "            <ellipse cx=\"200\" cy=\"200\" rx=\"100\" ry=\"100\" fill=\"rgb(229, 229, 255)\" />\n" +
            "        </svg>\n" +
            "    </div>\n" +
            "</body>\n" +
            "</html>\n",
            model.getSnapshotList().get(0).getID(),
            model.getSnapshotList().get(1).getID(),
            model.getSnapshotList().get(2).getID()),
            out.toString());
  }
}