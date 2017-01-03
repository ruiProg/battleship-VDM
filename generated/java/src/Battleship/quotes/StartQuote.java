package Battleship.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class StartQuote {
  private static int hc = 0;
  private static StartQuote instance = null;

  public StartQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static StartQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new StartQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof StartQuote;
  }

  public String toString() {

    return "<Start>";
  }
}
