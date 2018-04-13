package com.kookoon.legendaryopening.gui;

import com.kookoon.legendaryopening.PacksTracker;

import java.io.*;
import java.util.ArrayList;

public class FileHelper {
  public void saveTrackers(ArrayList<PacksTracker> tracker) {
    try {
      FileOutputStream fileOut =
          new FileOutputStream("tracker.ser");
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject(tracker);
      out.close();
      fileOut.close();
      System.out.printf("Serialized data is saved in tracker.ser");
    } catch (FileNotFoundException e)  {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public ArrayList<PacksTracker> loadTrackers() {
    File f = new File("tracker.ser");
    if (f.exists() && !f.isDirectory()) {
      ArrayList<PacksTracker> trackers = null;
      try {
        FileInputStream fileIn = new FileInputStream("tracker.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        trackers = (ArrayList<PacksTracker>) in.readObject();
        in.close();
        fileIn.close();
      } catch (IOException e) {
        e.printStackTrace();
      } catch (ClassNotFoundException e) {
        System.out.println("PacksTracker class not found");
        e.printStackTrace();
      }
      return trackers;
    }
    return null;
  }

}
