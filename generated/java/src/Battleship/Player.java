package Battleship;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Player {
  protected String name = SeqUtil.toStr(SeqUtil.seq());
  protected Number wins;
  protected Number losses;
  protected Board ownBoard = null;
  protected Board enemyBoard = null;
  protected VDMSet myShips = SetUtil.set();
  protected VDMSet enemiesShips = SetUtil.set();

  public void cg_init_Player_1(final String nameArg) {

    name = nameArg;
    wins = 0L;
    losses = 0L;
    return;
  }

  public Player(final String nameArg) {

    cg_init_Player_1(nameArg);
  }

  public String getName() {

    return name;
  }

  public void addBoards() {

    ownBoard = new Board();
    enemyBoard = new Board();
    myShips = ownBoard.getShips();
  }

  public void shipPlacement(
      final Object ship, final Character colCh, final Number line, final Object dir) {

    ownBoard.placeShip(ship, colCh, line, dir);
    myShips = SetUtil.diff(Utils.copy(myShips), SetUtil.set(ship));
  }

  public Boolean allShipsPlaced() {

    return Utils.equals(myShips.size(), 0L);
  }

  public void startRounds() {

    myShips = ownBoard.getShips();
    enemiesShips = SetUtil.set();
  }

  public void clearData() {

    ownBoard = null;
    enemyBoard = null;
    myShips = SetUtil.set();
    enemiesShips = SetUtil.set();
  }

  public Object registerAttack(final Character colCh, final Number line) {

    Object shipHit = ownBoard.registerMove(colCh, line);
    if (Utils.equals(ownBoard.countCellType(((Object) shipHit)), 0L)) {
      myShips = SetUtil.diff(Utils.copy(myShips), SetUtil.set(shipHit));
      if (Utils.equals(myShips.size(), 0L)) {
        losses = losses.longValue() + 1L;
      }

      return shipHit;

    } else {
      if (!(Utils.equals(shipHit, Battleship.quotes.EmptyQuote.getInstance()))) {
        return Battleship.quotes.HitQuote.getInstance();

      } else {
        return Battleship.quotes.MissQuote.getInstance();
      }
    }
  }

  public Boolean registerResult(final Object code, final Character colCh, final Number line) {

    if (Utils.equals(code, Battleship.quotes.MissQuote.getInstance())) {
      enemyBoard.setComponentCol(Battleship.quotes.MissQuote.getInstance(), line, colCh);
    } else {
      enemyBoard.setComponentCol(Battleship.quotes.HitQuote.getInstance(), line, colCh);
      if (!(Utils.equals(code, Battleship.quotes.HitQuote.getInstance()))) {
        enemiesShips = SetUtil.union(Utils.copy(enemiesShips), SetUtil.set(code));
      }

      if (Utils.equals(enemiesShips.size(), enemyBoard.getShipsCount())) {
        wins = wins.longValue() + 1L;
        return true;
      }
    }

    return false;
  }

  public String printInfo() {

    return name
        + " ("
        + VDMUtil.val2seq_of_char(wins)
        + "-"
        + VDMUtil.val2seq_of_char(losses)
        + ")\n";
  }

  public String printPlacementStatus() {

    return "Fleet placement\nPlayer turn: "
        + name
        + "\n"
        + "Ships to be placed: "
        + ownBoard.printRemainShips(Utils.copy(myShips))
        + "\n\n"
        + ownBoard.printBoard();
  }

  public String printGameStatus() {

    String ret = "Player turn: " + name + "\nMy active ships: ";
    for (Iterator iterator_10 = myShips.iterator(); iterator_10.hasNext(); ) {
      Object ship = (Object) iterator_10.next();
      ret = ret + ownBoard.shipToString(((Object) ship)) + "   ";
    }
    ret = ret + "\nDestroyed enemies ships: ";
    for (Iterator iterator_11 = enemiesShips.iterator(); iterator_11.hasNext(); ) {
      Object ship = (Object) iterator_11.next();
      ret = ret + enemyBoard.shipToString(((Object) ship)) + "   ";
    }
    ret =
        ret
            + "\n\n                                 My ships \t\t\t\t\t\t\t\t\t                Enemy ships\n\n\n";
    ret = ret + ownBoard.printParallelBoards(enemyBoard);
    return ret;
  }

  public String printTakeDown(final Object shipDown) {

    return "\n\n" + enemyBoard.shipToString(((Object) shipDown)) + " is sinking\n";
  }

  public String printVictory() {

    return "\nEnemy fleet destroyed. Victory!\n";
  }

  public Player() {}

  public String toString() {

    return "Player{"
        + "name := "
        + Utils.toString(name)
        + ", wins := "
        + Utils.toString(wins)
        + ", losses := "
        + Utils.toString(losses)
        + ", ownBoard := "
        + Utils.toString(ownBoard)
        + ", enemyBoard := "
        + Utils.toString(enemyBoard)
        + ", myShips := "
        + Utils.toString(myShips)
        + ", enemiesShips := "
        + Utils.toString(enemiesShips)
        + "}";
  }
}
