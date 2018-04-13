package com.kookoon.legendaryopening;

import java.io.Serializable;

public class PacksTracker implements Serializable {

  private int openedPacks;
  private int openedLegendaries;
  private boolean openedFirstTenPacks;
  private String trackerName;

  public void setOpenedPacks(int packsOpened) {
    this.openedPacks = packsOpened;
  }

  public void setOpenedLegendaries(int openedLegendaries) {
    this.openedLegendaries = openedLegendaries;
  }

  public float getNextPackLegendaryChance() {
    return  1.0f / (nextLegendaryExpectation() - openedPacks);
  }

  public int getOpenedLegendaries() {
    return openedLegendaries;
  }

  public float nextLegendaryExpectation() {
    if (openedFirstTenPacks) {
      return 20 * (openedLegendaries + 1);
    } else if (openedLegendaries > 0) {
      return (openedLegendaries * 20) + 10;
    } else {
      return 10;
    }
  }

  public void setOpenedFirstTenPacks(boolean value) {
    this.openedFirstTenPacks = value;
  }

  public int getOpenedPacks() {
    return openedPacks;
  }

  public void addOneOpenedPack() {
    openedPacks++;
  }

  public void addOneOpenedLegendary() {
    openedLegendaries++;
  }

  public void setTrackerName(String trackerName) {
    this.trackerName = trackerName;
  }

  public String getName() {
    return trackerName;
  }
}
