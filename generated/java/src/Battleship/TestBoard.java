package Battleship;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class TestBoard extends Board {
  private void testCreateBoard() {

    Board b = new Board();
    TestBase.assertEqual(b.countCellType(Battleship.quotes.EmptyQuote.getInstance()), 100L);
  }

  private void testColMap() {

    Board b = new Board();
    Board c = new Board();
    TestBase.assertEqual(2L, ((Number) Utils.get(colMap, 'B')));
    TestBase.assertEqual(2L - 1L, ((Number) Utils.get(colMap, 'A')));
    TestBase.assertEqual(2L + 1L, ((Number) Utils.get(colMap, 'C')));
    b.setComponent(Battleship.quotes.CruiserQuote.getInstance(), 1L, 2L);
    c.setComponentCol(Battleship.quotes.CruiserQuote.getInstance(), 1L, 'B');
    TestBase.assertEqual(
        ((Object) Utils.get(((VDMSeq) Utils.get(b.cells, 1L)), 2L)),
        ((Object) Utils.get(((VDMSeq) Utils.get(c.cells, 1L)), ((Number) Utils.get(colMap, 'B')))));
  }

  private void testEmptyBeforePlacement() {

    Board b = new Board();
    b.setComponentCol(Battleship.quotes.CruiserQuote.getInstance(), 6L, 'F');
    TestBase.assertTrue(
        b.emptyValidCells(
            2L, ((Number) Utils.get(colMap, 'C')), Battleship.quotes.RightQuote.getInstance(), 3L));
    TestBase.assertTrue(
        !(b.emptyValidCells(
            7L, ((Number) Utils.get(colMap, 'F')), Battleship.quotes.UpQuote.getInstance(), 3L)));
    TestBase.assertTrue(
        !(b.emptyValidCells(
            1L, ((Number) Utils.get(colMap, 'F')), Battleship.quotes.UpQuote.getInstance(), 3L)));
    TestBase.assertTrue(
        !(b.emptyValidCells(
            1L, ((Number) Utils.get(colMap, 'A')), Battleship.quotes.LeftQuote.getInstance(), 2L)));
    TestBase.assertTrue(
        !(b.emptyValidCells(
            9L, ((Number) Utils.get(colMap, 'H')), Battleship.quotes.DownQuote.getInstance(), 3L)));
    TestBase.assertTrue(
        !(b.emptyValidCells(
            5L, ((Number) Utils.get(colMap, 'F')), Battleship.quotes.DownQuote.getInstance(), 3L)));
    TestBase.assertTrue(
        !(b.emptyValidCells(
            6L,
            ((Number) Utils.get(colMap, 'C')),
            Battleship.quotes.RightQuote.getInstance(),
            5L)));
    TestBase.assertTrue(
        !(b.emptyValidCells(
            6L, ((Number) Utils.get(colMap, 'H')), Battleship.quotes.LeftQuote.getInstance(), 4L)));
    TestBase.assertTrue(
        !(b.emptyValidCells(
            2L,
            ((Number) Utils.get(colMap, 'H')),
            Battleship.quotes.RightQuote.getInstance(),
            5L)));
    TestBase.assertEqual(b.countCellType(Battleship.quotes.SubmarineQuote.getInstance()), 0L);
    TestBase.assertEqual(
        ((Number) Utils.get(shipSize, Battleship.quotes.SubmarineQuote.getInstance())), 3L);
    b.placeShip(
        Battleship.quotes.SubmarineQuote.getInstance(),
        'C',
        2L,
        Battleship.quotes.RightQuote.getInstance());
    TestBase.assertEqual(b.countCellType(Battleship.quotes.SubmarineQuote.getInstance()), 3L);
    b.placeShip(
        Battleship.quotes.CarrierQuote.getInstance(),
        'J',
        1L,
        Battleship.quotes.DownQuote.getInstance());
    TestBase.assertEqual(b.countCellType(Battleship.quotes.CarrierQuote.getInstance()), 5L);
    b.placeShip(
        Battleship.quotes.DestroyerQuote.getInstance(),
        'J',
        9L,
        Battleship.quotes.LeftQuote.getInstance());
    TestBase.assertEqual(b.countCellType(Battleship.quotes.DestroyerQuote.getInstance()), 2L);
    b.placeShip(
        Battleship.quotes.BattleshipQuote.getInstance(),
        'A',
        7L,
        Battleship.quotes.UpQuote.getInstance());
    TestBase.assertEqual(b.countCellType(Battleship.quotes.BattleshipQuote.getInstance()), 4L);
  }

  private void testGetShips() {

    Board b = new Board();
    TestBase.assertEqual(b.getShipsCount(), 5L);
    TestBase.assertEqual(
        b.getShips(),
        SetUtil.set(
            Battleship.quotes.CarrierQuote.getInstance(),
            Battleship.quotes.BattleshipQuote.getInstance(),
            Battleship.quotes.CruiserQuote.getInstance(),
            Battleship.quotes.SubmarineQuote.getInstance(),
            Battleship.quotes.DestroyerQuote.getInstance()));
  }

  private void testRegisterMove() {

    Board b = new Board();
    Object ret = Battleship.quotes.EmptyQuote.getInstance();
    b.placeShip(
        Battleship.quotes.SubmarineQuote.getInstance(),
        'C',
        2L,
        Battleship.quotes.RightQuote.getInstance());
    ret = b.registerMove('A', 3L);
    TestBase.assertEqual(((Object) ret), Battleship.quotes.EmptyQuote.getInstance());
    TestBase.assertEqual(
        ((Object) Utils.get(((VDMSeq) Utils.get(b.cells, 3L)), ((Number) Utils.get(colMap, 'A')))),
        Battleship.quotes.MissQuote.getInstance());
    ret = b.registerMove('D', 2L);
    TestBase.assertEqual(((Object) ret), Battleship.quotes.SubmarineQuote.getInstance());
    TestBase.assertEqual(
        ((Object) Utils.get(((VDMSeq) Utils.get(b.cells, 2L)), ((Number) Utils.get(colMap, 'D')))),
        Battleship.quotes.HitQuote.getInstance());
    ret = b.registerMove('C', 2L);
    TestBase.assertEqual(((Object) ret), Battleship.quotes.SubmarineQuote.getInstance());
    TestBase.assertEqual(
        ((Object) Utils.get(((VDMSeq) Utils.get(b.cells, 2L)), ((Number) Utils.get(colMap, 'C')))),
        Battleship.quotes.HitQuote.getInstance());
  }

  private void testPrints() {

    TestBase.assertEqual(shipToString(Battleship.quotes.CarrierQuote.getInstance()), "Carrier");
    TestBase.assertEqual(cellToString(Battleship.quotes.CarrierQuote.getInstance()), "Car");
  }

  public void run() {

    testCreateBoard();
    testColMap();
    testEmptyBeforePlacement();
    testGetShips();
    testRegisterMove();
    testPrints();
  }

  public TestBoard() {}

  public String toString() {

    return "TestBoard{}";
  }
}
