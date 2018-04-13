package com.kookoon.legendaryopening.gui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;

public class OpenedPacksDocumentFilter extends PlainDocument {

  private int maxCharacters;

  OpenedPacksDocumentFilter(int maxCharacters) {
    this.maxCharacters = maxCharacters;
  }

  @Override
  public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
    if (getLength() + str.length() <= maxCharacters && str.matches("[0-9]+")) {
      super.insertString(offs, str, a);
    } else {
      Toolkit.getDefaultToolkit().beep();
    }
  }

  @Override
  protected void insertUpdate(DefaultDocumentEvent chng, AttributeSet attr) {
    super.insertUpdate(chng, attr);
  }
}
