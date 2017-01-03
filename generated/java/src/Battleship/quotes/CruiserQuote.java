package Battleship.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class CruiserQuote {
  private static int hc = 0;
  private static CruiserQuote instance = null;

  public CruiserQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static CruiserQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new CruiserQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof CruiserQuote;
  }

  public String toString() {

    return "<Cruiser>";
  }
}
