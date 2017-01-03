package Battleship.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class DestroyerQuote {
  private static int hc = 0;
  private static DestroyerQuote instance = null;

  public DestroyerQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static DestroyerQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new DestroyerQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof DestroyerQuote;
  }

  public String toString() {

    return "<Destroyer>";
  }
}
