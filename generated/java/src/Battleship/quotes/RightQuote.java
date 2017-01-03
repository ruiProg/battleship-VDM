package Battleship.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class RightQuote {
  private static int hc = 0;
  private static RightQuote instance = null;

  public RightQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static RightQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new RightQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof RightQuote;
  }

  public String toString() {

    return "<Right>";
  }
}
