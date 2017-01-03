package Battleship.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class LeftQuote {
  private static int hc = 0;
  private static LeftQuote instance = null;

  public LeftQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static LeftQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new LeftQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof LeftQuote;
  }

  public String toString() {

    return "<Left>";
  }
}
