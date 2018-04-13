package com.kookoon.legendaryopening.gui;

import com.kookoon.legendaryopening.App;
import com.kookoon.legendaryopening.PacksTracker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUI {

  private final App app;
  private JButton createNewPacksTrackerButton;
  private JPanel pnlContent;
  private JButton btnIncreaseOpenedPacks;
  private JButton btnIncreaseOpenedLegendaries;
  private JLabel lblOpenedPacksValue;
  private JLabel lblOpenedLegendariesValue;
  private JLabel lblLegendaryChanceValue;
  private JPanel pnlTracker;
  private JLabel lblOpenedPacks;
  private JLabel lblOpenedLegendaries;
  private JLabel lblLegendaryChance;
  private JComboBox cmbTrackers;
  private JButton btnDeleteCurrentTracker;
  private int currentSelectedTrackerIndex;


  public GUI(App app) {
    this.app = app;
    cmbTrackers.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        currentSelectedTrackerIndex = cmbTrackers.getSelectedIndex();
        update();
      }
    });
  }

  public void start() {
    init();
    app.loadTrackers();
    createFrame();
  }



  private void init() {
    // Create new tracker
    createNewPacksTrackerButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        DialogCreateNewPacksTracker dialog = new DialogCreateNewPacksTracker();
        dialog.pack();
        dialog.setVisible(true);
        if (dialog.hasUserPressedOK()) {
          PacksTracker tracker = new PacksTracker();
          tracker.setTrackerName(dialog.getTrackerName());
          tracker.setOpenedPacks(dialog.getOpenedPacks());
          tracker.setOpenedFirstTenPacks(dialog.hasOpenedFirstTenPacks());
          tracker.setOpenedLegendaries(dialog.getOpenedLegendaries());
          app.addTracker(tracker);
          populateComboBox();
          update();
        }
      }
    });

    // Delete tracker
    btnDeleteCurrentTracker.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int selectedIndex = cmbTrackers.getSelectedIndex();
        app.removeTracker(selectedIndex);
        populateComboBox();
        update();
      }
    });
  }

  public void update() {
    if (currentSelectedTrackerIndex >= 0) {
      enableTrackerUI(true);
      lblOpenedLegendariesValue.setText(
          String.valueOf(app.getTracker(currentSelectedTrackerIndex).getOpenedLegendaries()));
      lblOpenedPacksValue
          .setText(String.valueOf(app.getTracker(currentSelectedTrackerIndex).getOpenedPacks()));
      lblLegendaryChanceValue.setText(
          String.valueOf(app.getTracker(currentSelectedTrackerIndex).getNextPackLegendaryChance()));
    } else {
      enableTrackerUI(false);
    }
    pnlContent.revalidate();

    // update delete key
    if (cmbTrackers.getItemCount() == 0) {
      btnDeleteCurrentTracker.setEnabled(false);
    } else {
      btnDeleteCurrentTracker.setEnabled(true);
    }
  }

  private void enableTrackerUI(boolean b) {
    btnIncreaseOpenedLegendaries.setEnabled(b);
    btnIncreaseOpenedPacks.setEnabled(b);
    if (!b) {
      lblOpenedPacksValue.setText("");
      lblLegendaryChanceValue.setText("");
      lblOpenedLegendariesValue.setText("");
    }
  }

  private void createFrame() {
    JFrame frame = new JFrame();
    frame.setContentPane(pnlContent);
    frame.pack();
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setVisible(true);
    frame.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        app.saveTrackers();
      }
    });
  }

  public void populateComboBox() {
    DefaultComboBoxModel trackers = new DefaultComboBoxModel();
    for (PacksTracker t : app.getTrackers()) {
      trackers.addElement(t.getName());
    }
    cmbTrackers.setModel(trackers);
    currentSelectedTrackerIndex = app.getTrackers().size()-1;
    cmbTrackers.setSelectedIndex(currentSelectedTrackerIndex);
  }
}
