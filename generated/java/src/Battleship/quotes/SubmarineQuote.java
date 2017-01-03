package Battleship.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class SubmarineQuote {
  private static int hc = 0;
  private static SubmarineQuote instance = null;

  public SubmarineQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static SubmarineQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new SubmarineQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof SubmarineQuote;
  }

  public String toString() {

    return "<Submarine>";
  }
}
