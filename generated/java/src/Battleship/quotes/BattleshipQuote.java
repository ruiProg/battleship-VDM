package Battleship.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class BattleshipQuote {
  private static int hc = 0;
  private static BattleshipQuote instance = null;

  public BattleshipQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static BattleshipQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new BattleshipQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof BattleshipQuote;
  }

  public String toString() {

    return "<Battleship>";
  }
}
