package Battleship.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class EmptyQuote {
  private static int hc = 0;
  private static EmptyQuote instance = null;

  public EmptyQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static EmptyQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new EmptyQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof EmptyQuote;
  }

  public String toString() {

    return "<Empty>";
  }
}
