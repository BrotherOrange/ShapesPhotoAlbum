package album;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;

import org.junit.rules.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;



/**
 * @author Jihao Zhang
 * @version 1.0
 * @date 4/23/22
 * @description album
 */
public class IShapesAlbumControllerTest {
  private final IShapesAlbum model = ShapesAlbumModel.getInstance();
  private final IShapesAlbumController controller = ShapesAlbumController.getInstance();
  private Readable in;
  private Appendable out;
  private int xMax;
  private int yMax;

  @Rule
  public final ExpectedException exception = ExpectedException.none();

  @Before
  public void setUp() throws Exception {
    model.reboot();
    xMax = 1000;
    yMax = 1000;
  }

  @Test
  public void testReadNormal() {
    in = new StringReader("# Make a rectangle and oval. Color red and green\n" +
            "    shape   myrect0   rectangle  200  200 50  100  255  0  0\n" +
            "    shape   myoval0   oval       500  100 60  30   0 255 1\n" +
            "\n" +
            "# Take a snapshot. Optional description text follows snapshot command\n" +
            "    snapShot After first selfie\n");
    out = new StringBuilder();
    controller.run(in, out, xMax, yMax);
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
            "</body>\n" +
            "</html>\n",
            model.getSnapshotList().get(0).getID()), out.toString());
  }

  @Test
  public void testRunDemoInput() {
    in = new StringReader("# Make a rectangle and oval. Color red and green\n" +
            "    shape   myrect1   rectangle  200  200 50  100  255  0  0\n" +
            "    shape   myoval1   oval       500  100 60  30   0 255 1\n" +
            "\n" +
            "# Take a snapshot. Optional description text follows snapshot command\n" +
            "    snapShot After first selfie\n" +
            "\n" +
            "    move myrect1     300     200\n" +
            "    resize myrect1   25      100\n" +
            "    move myrect1     100     300\n" +
            "\n" +
            "# Take another snapshot. Again, optional description included\n" +
            "# Snapshot descriptions go up to the end of the line (no multi-line descriptions to worry about)\n" +
            "    snapShot 2nd selfie\n" +
            "\n" +
            "# Change rectangle color to blue. Move the oval\n" +
            "    color myrect1    0  0  255\n" +
            "    move myoval1     500   400\n" +
            "\n" +
            "# Another snapshot. This time, descriptive text is omitted.\n" +
            "    snapShot\n" +
            "\n" +
            "# Remove the rectangle, then take one more snapshot\n" +
            "    remove myrect1\n" +
            "    snapshot Selfie after removing the rectangle from the picture\n");
    out = new StringBuilder();
    controller.run(in, out, xMax, yMax);
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
  public void testMoveNonExistentShape() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Moved shape not found!");
    in = new StringReader("# Make a rectangle and oval. Color red and green\n" +
            "    shape   myrect2   rectangle  200  200 50  100  255  0  0\n" +
            "    shape   myoval2   oval       500  100 60  30   0 255 1\n" +
            "\n" +
            "# Take a snapshot. Optional description text follows snapshot command\n" +
            "    snapShot After first selfie\n" +
            "\n" +
            "    move notmyrect     300     200\n");
    out = new StringBuilder();
    controller.run(in, out, xMax, yMax);
  }

  @Test
  public void testResizeShapeNonExistent() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Resized shape not found!");
    in = new StringReader("# Make a rectangle and oval. Color red and green\n" +
            "    shape   myrect3   rectangle  200  200 50  100  255  0  0\n" +
            "    shape   myoval3   oval       500  100 60  30   0 255 1\n" +
            "\n" +
            "# Take a snapshot. Optional description text follows snapshot command\n" +
            "    snapShot After first selfie\n" +
            "\n" +
            "    resize notmyrect     25     50\n");
    out = new StringBuilder();
    controller.run(in, out, xMax, yMax);
  }

  @Test
  public void testColorShapeNonExistent() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Colored shape not found!");
    in = new StringReader("# Make a rectangle and oval. Color red and green\n" +
            "    shape   myrect4   rectangle  200  200 50  100  255  0  0\n" +
            "    shape   myoval4   oval       500  100 60  30   0 255 1\n" +
            "\n" +
            "# Take a snapshot. Optional description text follows snapshot command\n" +
            "    snapShot After first selfie\n" +
            "\n" +
            "    color notmyrect     0     0   255\n");
    out = new StringBuilder();
    controller.run(in, out, xMax, yMax);
  }

  @Test
  public void testRemoveShapeNonExistent() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Removed shape not found!");
    in = new StringReader("# Make a rectangle and oval. Color red and green\n" +
            "    shape   myrect5   rectangle  200  200 50  100  255  0  0\n" +
            "    shape   myoval5   oval       500  100 60  30   0 255 1\n" +
            "\n" +
            "# Take a snapshot. Optional description text follows snapshot command\n" +
            "    snapShot After first selfie\n" +
            "\n" +
            "    remove notmyrect\n");
    out = new StringBuilder();
    controller.run(in, out, xMax, yMax);
  }
}