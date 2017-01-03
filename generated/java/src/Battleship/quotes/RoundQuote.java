package Battleship.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class RoundQuote {
  private static int hc = 0;
  private static RoundQuote instance = null;

  public RoundQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static RoundQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new RoundQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof RoundQuote;
  }

  public String toString() {

    return "<Round>";
  }
}
