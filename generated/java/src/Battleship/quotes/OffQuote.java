package Battleship.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class OffQuote {
  private static int hc = 0;
  private static OffQuote instance = null;

  public OffQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static OffQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new OffQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof OffQuote;
  }

  public String toString() {

    return "<Off>";
  }
}
