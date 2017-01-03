package Battleship;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Board {
  protected static final VDMMap shipSize =
      MapUtil.map(
          new Maplet(Battleship.quotes.CarrierQuote.getInstance(), 5L),
          new Maplet(Battleship.quotes.BattleshipQuote.getInstance(), 4L),
          new Maplet(Battleship.quotes.CruiserQuote.getInstance(), 3L),
          new Maplet(Battleship.quotes.SubmarineQuote.getInstance(), 3L),
          new Maplet(Battleship.quotes.DestroyerQuote.getInstance(), 2L));
  protected static final VDMMap colMap =
      MapUtil.map(
          new Maplet('A', 1L),
          new Maplet('B', 2L),
          new Maplet('C', 3L),
          new Maplet('D', 4L),
          new Maplet('E', 5L),
          new Maplet('F', 6L),
          new Maplet('G', 7L),
          new Maplet('H', 8L),
          new Maplet('I', 9L),
          new Maplet('J', 10L));
  protected VDMSeq cells = SeqUtil.seq();

  public void cg_init_Board_1() {

    VDMSeq seqCompResult_1 = SeqUtil.seq();
    VDMSet set_1 = SetUtil.range(1L, 10L);
    for (Iterator iterator_1 = set_1.iterator(); iterator_1.hasNext(); ) {
      Number x = ((Number) iterator_1.next());
      VDMSeq seqCompResult_2 = SeqUtil.seq();
      VDMSet set_2 = SetUtil.range(1L, 10L);
      for (Iterator iterator_2 = set_2.iterator(); iterator_2.hasNext(); ) {
        Number y = ((Number) iterator_2.next());
        seqCompResult_2.add(Battleship.quotes.EmptyQuote.getInstance());
      }
      seqCompResult_1.add(Utils.copy(seqCompResult_2));
    }
    cells = Utils.copy(seqCompResult_1);

    return;
  }

  public Board() {

    cg_init_Board_1();
  }

  public void setComponent(final Object content, final Number line, final Number col) {

    Utils.mapSeqUpdate(
        cells,
        line,
        SeqUtil.mod(
            Utils.copy(((VDMSeq) Utils.get(cells, line))), MapUtil.map(new Maplet(col, content))));
  }

  public void setComponentCol(final Object content, final Number line, final Character colCh) {

    setComponent(((Object) content), line, ((Number) Utils.get(colMap, colCh)));
  }

  public Boolean emptyValidCells(
      final Number line, final Number col, final Object dir, final Number size) {

    if (Utils.equals(dir, Battleship.quotes.UpQuote.getInstance())) {
      long toVar_1 = size.longValue() - 1L;

      for (Long i = 0L; i <= toVar_1; i++) {
        if (line.longValue() - i.longValue() <= 0L) {
          return false;
        }

        if (!(Utils.equals(
            Utils.get(((VDMSeq) Utils.get(cells, line.longValue() - i.longValue())), col),
            Battleship.quotes.EmptyQuote.getInstance()))) {
          return false;
        }
      }
    } else {
      if (Utils.equals(dir, Battleship.quotes.DownQuote.getInstance())) {
        long toVar_2 = size.longValue() - 1L;

        for (Long i = 0L; i <= toVar_2; i++) {
          if (line.longValue() + i.longValue() > 10L) {
            return false;
          }

          if (!(Utils.equals(
              Utils.get(((VDMSeq) Utils.get(cells, line.longValue() + 1L)), col),
              Battleship.quotes.EmptyQuote.getInstance()))) {
            return false;
          }
        }
      } else {
        if (Utils.equals(dir, Battleship.quotes.LeftQuote.getInstance())) {
          long toVar_3 = size.longValue() - 1L;

          for (Long i = 0L; i <= toVar_3; i++) {
            if (col.longValue() - i.longValue() <= 0L) {
              return false;
            }

            if (!(Utils.equals(
                Utils.get(((VDMSeq) Utils.get(cells, line)), col.longValue() - i.longValue()),
                Battleship.quotes.EmptyQuote.getInstance()))) {
              return false;
            }
          }
        } else {
          long toVar_4 = size.longValue() - 1L;

          for (Long i = 0L; i <= toVar_4; i++) {
            if (col.longValue() + i.longValue() > 10L) {
              return false;
            }

            if (!(Utils.equals(
                Utils.get(((VDMSeq) Utils.get(cells, line)), col.longValue() + i.longValue()),
                Battleship.quotes.EmptyQuote.getInstance()))) {
              return false;
            }
          }
        }
      }
    }

    return true;
  }

  public Number countCellType(final Object type) {

    Number sum = 0L;
    long toVar_6 = cells.size();

    for (Long i = 1L; i <= toVar_6; i++) {
      long toVar_5 = ((VDMSeq) Utils.get(cells, i)).size();

      for (Long j = 1L; j <= toVar_5; j++) {
        if (Utils.equals(Utils.get(((VDMSeq) Utils.get(cells, i)), j), type)) {
          sum = sum.longValue() + 1L;
        }
      }
    }
    return sum;
  }

  public void placeShip(
      final Object ship, final Character colCh, final Number line, final Object dir) {

    Number col = ((Number) Utils.get(colMap, colCh));
    if (Utils.equals(dir, Battleship.quotes.UpQuote.getInstance())) {
      long toVar_7 = ((Number) Utils.get(shipSize, ship)).longValue() - 1L;

      for (Long i = 0L; i <= toVar_7; i++) {
        setComponent(((Object) ship), line.longValue() - i.longValue(), col);
      }
    } else {
      if (Utils.equals(dir, Battleship.quotes.DownQuote.getInstance())) {
        long toVar_8 = ((Number) Utils.get(shipSize, ship)).longValue() - 1L;

        for (Long i = 0L; i <= toVar_8; i++) {
          setComponent(((Object) ship), line.longValue() + i.longValue(), col);
        }
      } else {
        if (Utils.equals(dir, Battleship.quotes.LeftQuote.getInstance())) {
          long toVar_9 = ((Number) Utils.get(shipSize, ship)).longValue() - 1L;

          for (Long i = 0L; i <= toVar_9; i++) {
            setComponent(((Object) ship), line, col.longValue() - i.longValue());
          }
        } else {
          long toVar_10 = ((Number) Utils.get(shipSize, ship)).longValue() - 1L;

          for (Long i = 0L; i <= toVar_10; i++) {
            setComponent(((Object) ship), line, col.longValue() + i.longValue());
          }
        }
      }
    }
  }

  public VDMSet getShips() {

    return MapUtil.dom(Utils.copy(Board.shipSize));
  }

  public Number getShipsCount() {

    return MapUtil.dom(Utils.copy(Board.shipSize)).size();
  }

  public Object registerMove(final Character colCh, final Number line) {

    Number col = ((Number) Utils.get(colMap, colCh));
    Object oldValue = Utils.get(((VDMSeq) Utils.get(cells, line)), col);
    if (Utils.equals(oldValue, Battleship.quotes.EmptyQuote.getInstance())) {
      Utils.mapSeqUpdate(
          ((VDMSeq) Utils.get(cells, line)), col, Battleship.quotes.MissQuote.getInstance());
    } else {
      Utils.mapSeqUpdate(
          ((VDMSeq) Utils.get(cells, line)), col, Battleship.quotes.HitQuote.getInstance());
    }

    return oldValue;
  }

  public String shipToString(final Object type) {

    Object quotePattern_1 = type;
    Boolean success_1 = Utils.equals(quotePattern_1, Battleship.quotes.EmptyQuote.getInstance());

    if (!(success_1)) {
      Object quotePattern_2 = type;
      success_1 = Utils.equals(quotePattern_2, Battleship.quotes.CarrierQuote.getInstance());

      if (!(success_1)) {
        Object quotePattern_3 = type;
        success_1 = Utils.equals(quotePattern_3, Battleship.quotes.BattleshipQuote.getInstance());

        if (!(success_1)) {
          Object quotePattern_4 = type;
          success_1 = Utils.equals(quotePattern_4, Battleship.quotes.CruiserQuote.getInstance());

          if (!(success_1)) {
            Object quotePattern_5 = type;
            success_1 =
                Utils.equals(quotePattern_5, Battleship.quotes.SubmarineQuote.getInstance());

            if (!(success_1)) {
              Object quotePattern_6 = type;
              success_1 =
                  Utils.equals(quotePattern_6, Battleship.quotes.DestroyerQuote.getInstance());

              if (!(success_1)) {
                Object quotePattern_7 = type;
                success_1 = Utils.equals(quotePattern_7, Battleship.quotes.HitQuote.getInstance());

                if (!(success_1)) {
                  Object quotePattern_8 = type;
                  success_1 =
                      Utils.equals(quotePattern_8, Battleship.quotes.MissQuote.getInstance());

                  if (success_1) {
                    return "Miss";
                  }

                } else {
                  return "Hit";
                }

              } else {
                return "Destroyer";
              }

            } else {
              return "Submarine";
            }

          } else {
            return "Cruiser";
          }

        } else {
          return "Battleship";
        }

      } else {
        return "Carrier";
      }

    } else {
      return "Empty";
    }

    return "Unknow";
  }

  public String cellToString(final Object type) {

    String fullStr = shipToString(((Object) type));
    String seqCompResult_3 = new String(new char[] {});
    VDMSet set_3 = SeqUtil.inds(fullStr);
    for (Iterator iterator_3 = set_3.iterator(); iterator_3.hasNext(); ) {
      Number i = ((Number) iterator_3.next());
      if (i.longValue() < 4L) {
        seqCompResult_3 += fullStr.charAt(Utils.index(i));
      }
    }
    return seqCompResult_3;
  }

  public String printBoard() {

    String board = SeqUtil.toStr(SeqUtil.seq());
    long toVar_12 = cells.size();

    for (Long i = 1L; i <= toVar_12; i++) {
      if (i.longValue() < 10L) {
        board = board + "0";
      }

      board = board + VDMUtil.val2seq_of_char(i) + "    ";
      long toVar_11 = ((VDMSeq) Utils.get(cells, i)).size();

      for (Long j = 1L; j <= toVar_11; j++) {
        board =
            board + cellToString(((Object) Utils.get(((VDMSeq) Utils.get(cells, i)), j))) + "    ";
      }
      board = board + "\n";
    }
    return "      A      B      C      D      E      F      G      H      I      J    \n\n" + board;
  }

  public String printParallelBoards(final Board enemyBoard) {

    String pBoard = SeqUtil.toStr(SeqUtil.seq());
    long toVar_15 = cells.size();

    for (Long i = 1L; i <= toVar_15; i++) {
      if (i.longValue() < 10L) {
        pBoard = pBoard + "0";
      }

      pBoard = pBoard + VDMUtil.val2seq_of_char(i) + "    ";
      long toVar_13 = ((VDMSeq) Utils.get(cells, i)).size();

      for (Long j = 1L; j <= toVar_13; j++) {
        pBoard =
            pBoard + cellToString(((Object) Utils.get(((VDMSeq) Utils.get(cells, i)), j))) + "    ";
      }
      pBoard = pBoard + "\t\t\t";
      if (i.longValue() < 10L) {
        pBoard = pBoard + "0";
      }

      pBoard = pBoard + VDMUtil.val2seq_of_char(i) + "    ";
      long toVar_14 = ((VDMSeq) Utils.get(enemyBoard.cells, i)).size();

      for (Long j = 1L; j <= toVar_14; j++) {
        pBoard =
            pBoard
                + cellToString(((Object) Utils.get(((VDMSeq) Utils.get(enemyBoard.cells, i)), j)))
                + "    ";
      }
      pBoard = pBoard + "\n";
    }
    return "      A      B      C      D      E      F      G      H      I      J    "
        + "\t\t\t"
        + "      A      B      C      D      E      F      G      H      I      J    \n\n"
        + pBoard;
  }

  public String printRemainShips(final VDMSet ships) {

    String res = SeqUtil.toStr(SeqUtil.seq());
    for (Iterator iterator_8 = ships.iterator(); iterator_8.hasNext(); ) {
      Object ship = (Object) iterator_8.next();
      res =
          res
              + shipToString(((Object) ship))
              + ": "
              + VDMUtil.val2seq_of_char(((Number) Utils.get(shipSize, ship)))
              + "    ";
    }
    return res;
  }

  public String toString() {

    return "Board{"
        + "shipSize = "
        + Utils.toString(shipSize)
        + ", colMap = "
        + Utils.toString(colMap)
        + ", cells := "
        + Utils.toString(cells)
        + "}";
  }
}
