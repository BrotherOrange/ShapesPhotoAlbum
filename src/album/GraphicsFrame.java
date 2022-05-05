package album;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;

/**
 * @author Jihao Zhang
 * @version 1.0
 * @date 4/21/22
 * @description album
 */
public class GraphicsFrame extends JFrame {
  private final IShapesAlbum model = ShapesAlbumModel.getInstance();
  private final List<ISnapshot> snapshotList = model.getSnapshotList();
  private final List<String> idList = new ArrayList<>();
  private final Map<String, ISnapshot> snapshotMap = new HashMap<>();

  private SnapshotPanel currentPanel;
  private int currentIdIndex;

  private final int xMax;
  private final int yMax;

  private final GridBagLayout layout = new GridBagLayout();
  private final GridBagConstraints constraints = new GridBagConstraints();

  private final JPanel snapshotPanel = new JPanel();
  private final JPanel buttonsPanel = new JPanel();

  private final JButton prev = new JButton("<< Prev <<");
  private final JButton select = new JButton("^^ Select ^^");
  private final JButton next = new JButton(">> Next >>");
  private final JButton quit = new JButton("xx Quit xx");

  public GraphicsFrame(int xMax, int yMax) throws HeadlessException {
    super();
    this.xMax = xMax;
    this.yMax = yMax;
    setSize(xMax + 100, yMax + 100);
    setTitle("Shapes Photo Album");
    setLayout(layout);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    initialize();

    loadSnapshots();

  }

  private void initialize() {
    constraints.fill = GridBagConstraints.BOTH;
    constraints.weightx = 1;
    initSnapshotPanel();
    initButtonPanel();
  }

  private void initSnapshotPanel() {
    constraints.weighty = 0.99;
    constraints.gridwidth = GridBagConstraints.REMAINDER;
    snapshotPanel.setBackground(Color.cyan);
    layout.setConstraints(snapshotPanel, constraints);
    add(snapshotPanel);
  }

  private void initButtonPanel() {
    constraints.weighty = 0.01;
    constraints.gridwidth = GridBagConstraints.REMAINDER;
    buttonsPanel.setBackground(Color.yellow);
    layout.setConstraints(buttonsPanel, constraints);
    add(buttonsPanel);
    loadButtons();
  }

  private void loadButtons() {
    buttonsPanel.setLayout(new GridLayout(1, 4, 20, 10));
    buttonsPanel.add(prev);
    buttonsPanel.add(select);
    buttonsPanel.add(next);
    buttonsPanel.add(quit);
    setPrevAction();
    setSelectAction();
    setNextAction();
    setQuitAction();
  }

  private void loadSnapshots() {
    if (snapshotList.size() == 0) {
      JOptionPane.showMessageDialog(null,
              "There is No Snapshot!",
              "Warning",
              2);
    }
    snapshotPanel.setLayout(new BorderLayout());
    for (ISnapshot snapshot : snapshotList) {
      idList.add(snapshot.getID());
      snapshotMap.put(snapshot.getID(), snapshot);
    }
    currentIdIndex = 0;
    setFrontPanel(currentIdIndex);
  }

  private void setFrontPanel(String id) {
    if (idList.size() == 0) {
      return;
    }
    if (currentPanel != null) {
      currentPanel.setVisible(false);
    }
    currentPanel = new SnapshotPanel(snapshotMap.get(id), xMax, yMax);
    snapshotPanel.add(currentPanel, BorderLayout.CENTER);
    currentPanel.setVisible(true);
  }

  private void setFrontPanel(int idIndex) {
    if (idList.size() == 0) {
      return;
    }
    if (currentPanel != null) {
      currentPanel.setVisible(false);
    }
    currentPanel = new SnapshotPanel(snapshotMap.get(idList.get(idIndex)), xMax, yMax);
    snapshotPanel.add(currentPanel, BorderLayout.CENTER);
    currentPanel.setVisible(true);
  }

  private void setPrevAction() {
    prev.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (currentIdIndex > 0) {
          setFrontPanel(--currentIdIndex);
        } else {
          JOptionPane.showMessageDialog(null,
                  "This is first Snapshot!",
                  "Warning",
                  2);
        }
      }
    });
  }

  private void setNextAction() {
    next.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (currentIdIndex < idList.size() - 1) {
          setFrontPanel(++currentIdIndex);
        } else {
          JOptionPane.showMessageDialog(null,
                  "This is last Snapshot!",
                  "Warning",
                  2);
        }
      }
    });
  }

  private void setQuitAction() {
    quit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        setVisible(false);
        dispose();
      }
    });
  }

  private void setSelectAction() {
    select.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (snapshotList.size() != 0) {
          String[] ids = new String[idList.size()];
          ids = idList.toArray(ids);
          String id = (String) JOptionPane.showInputDialog(null, "Get Snapshot",
                  "Please choose an ID:",
                  JOptionPane.INFORMATION_MESSAGE, null, ids, ids[0]);
          currentIdIndex = idList.indexOf(id);
          setFrontPanel(id);
        }
      }
    });
  }
}
