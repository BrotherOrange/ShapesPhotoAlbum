package album;

import java.awt.*;

import javax.swing.*;

/**
 * @author Jihao Zhang
 * @version 1.0
 * @date 4/21/22
 * @description album
 */
public class SnapshotPanel extends JPanel {
  private final ISnapshot mySnapshot;

  private final int xMax;
  private final int yMax;

  private final GridBagLayout layout = new GridBagLayout();
  private final GridBagConstraints constraints = new GridBagConstraints();

  public SnapshotPanel(ISnapshot snapshot, int xMax, int yMax) {
    super();
    this.xMax = xMax;
    this.yMax = yMax;
    mySnapshot = snapshot;
    setLayout(layout);
    drawPanel();
  }

  public void drawPanel() {
    constraints.fill = GridBagConstraints.BOTH;
    constraints.weightx = 1;
    drawLabel();
    drawShapes();
  }

  public void drawLabel() {
    constraints.weighty = 0.01;
    constraints.gridwidth = GridBagConstraints.REMAINDER;
    JPanel labelPanel = new JPanel();
    labelPanel.setBackground(Color.pink);
    GridBagLayout labelLayout = new GridBagLayout();
    GridBagConstraints labelConstraints = new GridBagConstraints();
    labelPanel.setLayout(labelLayout);
    labelConstraints.fill = GridBagConstraints.BOTH;
    labelConstraints.weightx = 1;
    labelConstraints.weighty = 0.5;
    labelConstraints.gridwidth = GridBagConstraints.REMAINDER;
    JLabel idLabel = new JLabel(mySnapshot.getID());
    labelLayout.setConstraints(idLabel, labelConstraints);
    labelPanel.add(idLabel);
    if (!mySnapshot.getDescription().equals("")) {
      labelConstraints.gridwidth = GridBagConstraints.REMAINDER;
      JLabel desLabel = new JLabel(mySnapshot.getDescription());
      labelLayout.setConstraints(desLabel, labelConstraints);
      labelPanel.add(desLabel);
    }
    layout.setConstraints(labelPanel, constraints);
    add(labelPanel);
  }

  private void drawShapes() {
    constraints.weighty = 0.99;
    constraints.gridwidth = GridBagConstraints.REMAINDER;
    JPanel drawPanel = new JPanel() {
      @Override
      public void paint(Graphics g) {
        for (IShape shape : mySnapshot.getShapes()) {
          g.setColor(new Color(
                  (int) shape.getColorR(),
                  (int) shape.getColorG(),
                  (int) shape.getColorB()));
          int posX = (int) shape.getPositionX();
          int posY = (int) shape.getPositionY();
          int first = (int) shape.getFirstAttribute();
          int second = (int) shape.getSecondAttribute();
          if (shape.getType().equals("rectangle")) {
            g.fillRect(posX, posY, first, second);
          } else {
            g.fillOval(posX, posY, first, second);
          }
        }
      }
    };
    drawPanel.setBackground(Color.blue);
    drawPanel.setSize(xMax, yMax);
    drawPanel.setVisible(true);
    layout.setConstraints(drawPanel, constraints);
    add(drawPanel);
  }

  public String getID() {
    return mySnapshot.getID();
  }
}
