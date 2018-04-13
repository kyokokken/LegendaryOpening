package com.kookoon.legendaryopening.gui;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.event.*;
import java.math.BigDecimal;

public class DialogCreateNewPacksTracker extends JDialog {

  private JPanel contentPane;
  private JButton btnOK;
  private JButton btnCancel;
  private JRadioButton cbxYes;
  private JRadioButton cbxNo;
  private JTextField txtOpenedPacks;
  private JLabel lblFirst10Packs;
  private JLabel lblOpenedPacks;
  private JPanel pnlContent;
  private JPanel pnlButtons;
  private JTextField txtOpenedLegendaries;
  private JLabel lblOpenedLegendaries;
  private JLabel lblName;
  private JTextField txtName;

  private boolean hasOpenedFirstTenPacks;
  private int openedPacks;
  private boolean userPressedOK;
  private int openedLegendaries;

  public DialogCreateNewPacksTracker() {
    userPressedOK = false;
    setContentPane(contentPane);
    setModal(true);
    setResizable(false);
    setTitle("New Packs Tracker");
    txtOpenedPacks.setDocument(new OpenedPacksDocumentFilter(4));
    txtOpenedLegendaries.setDocument(new OpenedPacksDocumentFilter(2));
    txtOpenedPacks.setText("0");
    txtOpenedLegendaries.setText("0");
//    txtOpenedPacks.setInputVerifier(new MyInputVerifier());
    getRootPane().setDefaultButton(btnOK);

    btnOK.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        onOK();
      }
    });

    btnCancel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        onCancel();
      }
    });

    // call onCancel() when cross is clicked
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        onCancel();
      }
    });

    // call onCancel() on ESCAPE
    contentPane.registerKeyboardAction(new ActionListener() {
                                         public void actionPerformed(ActionEvent e) {
                                           onCancel();
                                         }
                                       }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
        JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
  }

  private void onOK() {
    // add your code here
    userPressedOK = true;
    setVariables();
    dispose();
  }

  private void setVariables() {
    if (cbxNo.isSelected()) {
      hasOpenedFirstTenPacks = false;
    } else if (cbxYes.isSelected()) {
      hasOpenedFirstTenPacks = true;
    }
    String strOpenedPacks = txtOpenedPacks.getText();
    if (strOpenedPacks.length() == 0) {
      strOpenedPacks = "0";
    }
    openedPacks = Integer.parseInt(strOpenedPacks);

    String strOpenedLegendaries = txtOpenedLegendaries.getText();
    if (strOpenedLegendaries.length() == 0) {
      strOpenedLegendaries = "0";
    }
    openedLegendaries = Integer.parseInt(strOpenedLegendaries);
  }

  private void onCancel() {
    userPressedOK = false;
    // add your code here if necessary
    setVariables();
    dispose();
  }

  public boolean hasUserPressedOK() {
    return userPressedOK;
  }

  public boolean hasOpenedFirstTenPacks() {
    return hasOpenedFirstTenPacks;
  }

  public int getOpenedPacks() {
    return openedPacks;
  }

  public static void main(String[] args) {
    DialogCreateNewPacksTracker dialog = new DialogCreateNewPacksTracker();
    dialog.pack();
    dialog.setVisible(true);
    System.exit(0);
  }

  public int getOpenedLegendaries() {
    return openedLegendaries;
  }

  public String getTrackerName() {
    return txtName.getText();
  }

  public class MyInputVerifier extends InputVerifier {
    @Override
    public boolean verify(JComponent input) {
      String text = ((JTextField) input).getText();
      try {
        BigDecimal value = new BigDecimal(text);
        return (value.scale() <= Math.abs(4));
      } catch (NumberFormatException e) {
        return false;
      }
    }
  }
}
