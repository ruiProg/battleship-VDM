package Battleship;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class TestGame extends Game {
  private void testCreateGame() {

    Game g = new Game("John", "Diana");
    Player p = g.playerA;
    TestBase.assertEqual(g.players.size(), 2L);
    p = g.createPlayer("Paul");
    TestBase.assertEqual(g.players.size(), 3L);
    TestBase.assertEqual(g.playerA.getName(), "John");
    TestBase.assertEqual(g.playerB.getName(), "Diana");
    g.changePlayers("Paul", "John");
    TestBase.assertEqual(g.playerA.getName(), "Paul");
    TestBase.assertEqual(g.playerB.getName(), "John");
  }

  public void testEmplacement() {

    Game g = new Game("Ana", "Paula");
    String str = SeqUtil.toStr(SeqUtil.seq());
    str = g.startGame();
    str =
        g.shipPlacement(
            Battleship.quotes.SubmarineQuote.getInstance(),
            'C',
            9L,
            Battleship.quotes.RightQuote.getInstance());
    TestBase.assertEqual(((Object) g.currState), Battleship.quotes.StartQuote.getInstance());
    TestBase.assertEqual(g.currPlayer, g.playerA);
    str =
        g.shipPlacement(
            Battleship.quotes.CarrierQuote.getInstance(),
            'H',
            9L,
            Battleship.quotes.UpQuote.getInstance());
    str =
        g.shipPlacement(
            Battleship.quotes.CruiserQuote.getInstance(),
            'G',
            2L,
            Battleship.quotes.RightQuote.getInstance());
    str =
        g.shipPlacement(
            Battleship.quotes.BattleshipQuote.getInstance(),
            'A',
            2L,
            Battleship.quotes.DownQuote.getInstance());
    str =
        g.shipPlacement(
            Battleship.quotes.DestroyerQuote.getInstance(),
            'C',
            4L,
            Battleship.quotes.DownQuote.getInstance());
    TestBase.assertEqual(((Object) g.currState), Battleship.quotes.StartQuote.getInstance());
    TestBase.assertEqual(g.currPlayer, g.playerB);
    str =
        g.shipPlacement(
            Battleship.quotes.SubmarineQuote.getInstance(),
            'C',
            9L,
            Battleship.quotes.RightQuote.getInstance());
    str =
        g.shipPlacement(
            Battleship.quotes.CarrierQuote.getInstance(),
            'H',
            9L,
            Battleship.quotes.UpQuote.getInstance());
    str =
        g.shipPlacement(
            Battleship.quotes.CruiserQuote.getInstance(),
            'G',
            2L,
            Battleship.quotes.RightQuote.getInstance());
    str =
        g.shipPlacement(
            Battleship.quotes.BattleshipQuote.getInstance(),
            'A',
            2L,
            Battleship.quotes.DownQuote.getInstance());
    str =
        g.shipPlacement(
            Battleship.quotes.DestroyerQuote.getInstance(),
            'C',
            4L,
            Battleship.quotes.DownQuote.getInstance());
    TestBase.assertEqual(((Object) g.currState), Battleship.quotes.PlacedQuote.getInstance());
  }

  private void testShipGuess() {

    Game g = new Game("Ana", "Paula");
    String str = SeqUtil.toStr(SeqUtil.seq());
    str = g.startGame();
    str =
        g.shipPlacement(
            Battleship.quotes.SubmarineQuote.getInstance(),
            'C',
            9L,
            Battleship.quotes.RightQuote.getInstance());
    str =
        g.shipPlacement(
            Battleship.quotes.CarrierQuote.getInstance(),
            'H',
            9L,
            Battleship.quotes.UpQuote.getInstance());
    str =
        g.shipPlacement(
            Battleship.quotes.CruiserQuote.getInstance(),
            'G',
            2L,
            Battleship.quotes.RightQuote.getInstance());
    str =
        g.shipPlacement(
            Battleship.quotes.BattleshipQuote.getInstance(),
            'A',
            2L,
            Battleship.quotes.DownQuote.getInstance());
    str =
        g.shipPlacement(
            Battleship.quotes.DestroyerQuote.getInstance(),
            'C',
            4L,
            Battleship.quotes.DownQuote.getInstance());
    str =
        g.shipPlacement(
            Battleship.quotes.SubmarineQuote.getInstance(),
            'C',
            9L,
            Battleship.quotes.RightQuote.getInstance());
    str =
        g.shipPlacement(
            Battleship.quotes.CarrierQuote.getInstance(),
            'H',
            9L,
            Battleship.quotes.UpQuote.getInstance());
    str =
        g.shipPlacement(
            Battleship.quotes.CruiserQuote.getInstance(),
            'G',
            2L,
            Battleship.quotes.RightQuote.getInstance());
    str =
        g.shipPlacement(
            Battleship.quotes.BattleshipQuote.getInstance(),
            'A',
            2L,
            Battleship.quotes.DownQuote.getInstance());
    str =
        g.shipPlacement(
            Battleship.quotes.DestroyerQuote.getInstance(),
            'C',
            4L,
            Battleship.quotes.DownQuote.getInstance());
    str = g.startRounds();
    TestBase.assertEqual(g.currPlayer, g.playerA);
    str = g.guessShipPosition('H', 1L);
    TestBase.assertEqual(g.currPlayer, g.playerB);
    str = g.guessShipPosition('C', 4L);
    TestBase.assertEqual(g.currPlayer, g.playerB);
    str = g.guessShipPosition('C', 5L);
    str = g.guessShipPosition('C', 6L);
    str = g.guessShipPosition('F', 9L);
    str = g.guessShipPosition('C', 9L);
    str = g.guessShipPosition('D', 9L);
    str = g.guessShipPosition('E', 9L);
    str = g.guessShipPosition('A', 2L);
    str = g.guessShipPosition('A', 3L);
    str = g.guessShipPosition('A', 4L);
    str = g.guessShipPosition('A', 5L);
    str = g.guessShipPosition('G', 2L);
    str = g.guessShipPosition('H', 2L);
    str = g.guessShipPosition('I', 2L);
    str = g.guessShipPosition('H', 9L);
    str = g.guessShipPosition('H', 8L);
    str = g.guessShipPosition('H', 7L);
    str = g.guessShipPosition('H', 6L);
    str = g.guessShipPosition('H', 5L);
    TestBase.assertEqual(((Object) g.currState), Battleship.quotes.OffQuote.getInstance());
  }

  public void run() {

    testCreateGame();
    testEmplacement();
    testShipGuess();
  }

  public TestGame() {}

  public String toString() {

    return "TestGame{}";
  }
}
