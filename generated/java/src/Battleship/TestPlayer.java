package Battleship;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class TestPlayer extends Player {
  private void testCreatePlayer() {

    Player pl = new Player("John");
    TestBase.assertEqual(pl.losses.longValue() + pl.wins.longValue(), 0L);
    TestBase.assertTrue(Utils.equals(pl.ownBoard, null));
    pl.addBoards();
    TestBase.assertTrue(!(Utils.equals(pl.ownBoard, null)));
    TestBase.assertEqual(pl.getName(), "John");
  }

  private void testShipPlacement() {

    Player pl = new Player("John");
    pl.addBoards();
    pl.shipPlacement(
        Battleship.quotes.CruiserQuote.getInstance(),
        'B',
        2L,
        Battleship.quotes.RightQuote.getInstance());
    TestBase.assertTrue(
        !(Utils.equals(
            pl.ownBoard.countCellType(Battleship.quotes.CruiserQuote.getInstance()), 0L)));
    pl.shipPlacement(
        Battleship.quotes.BattleshipQuote.getInstance(),
        'A',
        1L,
        Battleship.quotes.DownQuote.getInstance());
    TestBase.assertTrue(!(pl.allShipsPlaced()));
    pl.shipPlacement(
        Battleship.quotes.CarrierQuote.getInstance(),
        'J',
        4L,
        Battleship.quotes.LeftQuote.getInstance());
    pl.shipPlacement(
        Battleship.quotes.SubmarineQuote.getInstance(),
        'J',
        10L,
        Battleship.quotes.UpQuote.getInstance());
    pl.shipPlacement(
        Battleship.quotes.DestroyerQuote.getInstance(),
        'G',
        8L,
        Battleship.quotes.LeftQuote.getInstance());
    TestBase.assertTrue(pl.allShipsPlaced());
  }

  private void testAttackResponse() {

    Player pl = new Player("John");
    Object ret = Battleship.quotes.EmptyQuote.getInstance();
    Number nbShips = 1L;
    Number nbLosses = pl.losses;
    pl.addBoards();
    pl.shipPlacement(
        Battleship.quotes.CruiserQuote.getInstance(),
        'B',
        2L,
        Battleship.quotes.RightQuote.getInstance());
    pl.shipPlacement(
        Battleship.quotes.BattleshipQuote.getInstance(),
        'A',
        1L,
        Battleship.quotes.DownQuote.getInstance());
    pl.shipPlacement(
        Battleship.quotes.CarrierQuote.getInstance(),
        'J',
        4L,
        Battleship.quotes.LeftQuote.getInstance());
    pl.shipPlacement(
        Battleship.quotes.SubmarineQuote.getInstance(),
        'J',
        10L,
        Battleship.quotes.UpQuote.getInstance());
    pl.shipPlacement(
        Battleship.quotes.DestroyerQuote.getInstance(),
        'G',
        8L,
        Battleship.quotes.LeftQuote.getInstance());
    pl.startRounds();
    nbShips = pl.myShips.size();
    TestBase.assertTrue(nbShips.longValue() > 0L);
    ret = pl.registerAttack('A', 10L);
    TestBase.assertEqual(((Object) ret), Battleship.quotes.MissQuote.getInstance());
    ret = pl.registerAttack('G', 8L);
    TestBase.assertEqual(((Object) ret), Battleship.quotes.HitQuote.getInstance());
    ret = pl.registerAttack('F', 8L);
    TestBase.assertEqual(((Object) ret), Battleship.quotes.DestroyerQuote.getInstance());
    TestBase.assertEqual(pl.myShips.size(), nbShips.longValue() - 1L);
    ret = pl.registerAttack('J', 8L);
    ret = pl.registerAttack('J', 9L);
    ret = pl.registerAttack('J', 10L);
    ret = pl.registerAttack('J', 4L);
    ret = pl.registerAttack('I', 4L);
    ret = pl.registerAttack('H', 4L);
    ret = pl.registerAttack('G', 4L);
    ret = pl.registerAttack('F', 4L);
    ret = pl.registerAttack('E', 4L);
    ret = pl.registerAttack('A', 1L);
    ret = pl.registerAttack('A', 2L);
    ret = pl.registerAttack('A', 3L);
    ret = pl.registerAttack('A', 4L);
    ret = pl.registerAttack('B', 2L);
    ret = pl.registerAttack('C', 2L);
    ret = pl.registerAttack('D', 2L);
    TestBase.assertEqual(pl.losses, nbLosses.longValue() + 1L);
    pl.clearData();
    TestBase.assertTrue(Utils.equals(pl.ownBoard, null));
  }

  private void testAttackResult() {

    Player pl = new Player("John");
    Boolean ret = false;
    Number nbShips = 0L;
    Number nbWins = pl.wins;
    pl.addBoards();
    pl.startRounds();
    nbWins = pl.wins;
    nbShips = pl.enemiesShips.size();
    ret = pl.registerResult(Battleship.quotes.MissQuote.getInstance(), 'A', 5L);
    TestBase.assertTrue(!(ret));
    ret = pl.registerResult(Battleship.quotes.HitQuote.getInstance(), 'D', 10L);
    TestBase.assertEqual(pl.enemiesShips.size(), nbShips);
    ret = pl.registerResult(Battleship.quotes.SubmarineQuote.getInstance(), 'A', 6L);
    TestBase.assertEqual(pl.enemiesShips.size(), nbShips.longValue() + 1L);
    ret = pl.registerResult(Battleship.quotes.CruiserQuote.getInstance(), 'J', 2L);
    ret = pl.registerResult(Battleship.quotes.CarrierQuote.getInstance(), 'D', 10L);
    ret = pl.registerResult(Battleship.quotes.DestroyerQuote.getInstance(), 'I', 6L);
    ret = pl.registerResult(Battleship.quotes.BattleshipQuote.getInstance(), 'C', 4L);
    TestBase.assertTrue(ret);
    TestBase.assertEqual(pl.wins, nbWins.longValue() + 1L);
  }

  public void run() {

    testCreatePlayer();
    testShipPlacement();
    testAttackResponse();
    testAttackResult();
  }

  public TestPlayer() {}

  public String toString() {

    return "TestPlayer{}";
  }
}
