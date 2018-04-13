package com.kookoon.legendaryopening;

import com.kookoon.legendaryopening.gui.FileHelper;
import com.kookoon.legendaryopening.gui.GUI;

import java.util.ArrayList;

public class App {

  private ArrayList<PacksTracker> trackers = new ArrayList<PacksTracker>();
  private FileHelper fileHelper;
  private GUI gui;

  public static void main(String args[]) {
    new App().startGUI();
  }

  public void startGUI() {
    fileHelper = new FileHelper();
    gui = new GUI(this);
    gui.start();
  }

  public void loadTrackers() {
    ArrayList<PacksTracker> trackers = fileHelper.loadTrackers();
    if (trackers != null) {
      this.trackers = trackers;
      gui.populateComboBox();
      gui.update();
    }
  }

  public void addTracker(PacksTracker tracker) {
    trackers.add(tracker);
  }

  public void saveTrackers() {
    fileHelper.saveTrackers(trackers);
  }

  public ArrayList<PacksTracker> getTrackers() {
    return trackers;
  }

  public void removeTracker(int selectedIndex) {
    trackers.remove(selectedIndex);
  }

  public PacksTracker getTracker(int currentSelectedTrackerIndex) {
    return trackers.get(currentSelectedTrackerIndex);
  }
}
