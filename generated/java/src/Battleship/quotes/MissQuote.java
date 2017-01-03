package Battleship.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class MissQuote {
  private static int hc = 0;
  private static MissQuote instance = null;

  public MissQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static MissQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new MissQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof MissQuote;
  }

  public String toString() {

    return "<Miss>";
  }
}
