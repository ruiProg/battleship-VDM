package Battleship;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Game {
  protected Player playerA = new Player("Default_1");
  protected Player playerB = new Player("Default_2");
  protected Player currPlayer = playerA;
  protected VDMSet players = SetUtil.set(playerA, playerB);
  protected Object currState = Battleship.quotes.OffQuote.getInstance();

  public void cg_init_Game_1(final String name1, final String name2) {

    playerA = new Player(name1);
    playerB = new Player(name2);
    currPlayer = playerA;
    players = SetUtil.set(playerA, playerB);
    return;
  }

  public Game(final String name1, final String name2) {

    cg_init_Game_1(name1, name2);
  }

  public Player createPlayer(final String name) {

    Player player = new Player(name);
    players = SetUtil.union(Utils.copy(players), SetUtil.set(player));
    return player;
  }

  public Player getPlayer(final String name) {

    Player tmpPlayer = playerA;
    for (Iterator iterator_9 = players.iterator(); iterator_9.hasNext(); ) {
      Player x = (Player) iterator_9.next();
      if (Utils.equals(x.getName(), name)) {
        tmpPlayer = x;
        return x;
      }
    }
    return tmpPlayer;
  }

  public void changePlayers(final String name1, final String name2) {

    Player tmpPlayer = getPlayer(name1);
    Player atomicTmp_1 = tmpPlayer;
    Player atomicTmp_2 = getPlayer(name2);
    Player atomicTmp_3 = tmpPlayer;
    {
        /* Start of atomic statement */
      playerA = atomicTmp_1;
      playerB = atomicTmp_2;
      currPlayer = atomicTmp_3;
    } /* End of atomic statement */
  }

  public void switchTurns() {

    if (Utils.equals(currPlayer, playerA)) {
      currPlayer = playerB;
    } else {
      currPlayer = playerA;
    }
  }

  public Player getOtherPlayer() {

    if (Utils.equals(currPlayer, playerA)) {
      return playerB;

    } else {
      return playerA;
    }
  }

  public String startGame() {

    playerA.addBoards();
    playerB.addBoards();
    currState = Battleship.quotes.StartQuote.getInstance();
    return "Game started with following players:\n"
        + playerA.printInfo()
        + playerB.printInfo()
        + "\n\n\n\n"
        + currPlayer.printPlacementStatus();
  }

  public String shipPlacement(
      final Object ship, final Character colCh, final Number line, final Object dir) {

    String ret = null;
    currPlayer.shipPlacement(ship, colCh, line, dir);
    ret = currPlayer.printPlacementStatus();
    if (currPlayer.allShipsPlaced()) {
      switchTurns();
      ret = ret + "\n\n\n\n\n";
      if (currPlayer.allShipsPlaced()) {
        currState = Battleship.quotes.PlacedQuote.getInstance();
        ret = ret + "All ships placed\n";

      } else {
        ret = ret + currPlayer.printPlacementStatus();
      }
    }

    return ret;
  }

  public String startRounds() {

    playerA.startRounds();
    playerB.startRounds();
    currState = Battleship.quotes.RoundQuote.getInstance();
    return currPlayer.printGameStatus();
  }

  public String guessShipPosition(final Character colCh, final Number line) {

    Player othPlayer = getOtherPlayer();
    Object code = othPlayer.registerAttack(colCh, line);
    String ret = SeqUtil.toStr(SeqUtil.seq());
    Boolean final_ = currPlayer.registerResult(((Object) code), colCh, line);
    if (Utils.equals(code, Battleship.quotes.MissQuote.getInstance())) {
      ret = ret + "\n\nSplash!! You missed!\n";
      switchTurns();

    } else {
      if (Utils.equals(code, Battleship.quotes.HitQuote.getInstance())) {
        ret = ret + "\n\nGreat strike\n";
      } else {
        ret = ret + currPlayer.printTakeDown(((Object) code));
      }
    }

    if (final_) {
      ret = ret + currPlayer.printVictory();
      currState = Battleship.quotes.OffQuote.getInstance();
      playerA.clearData();
      playerB.clearData();
      return ret;
    }

    ret = ret + "\n\n\n\n\n" + currPlayer.printGameStatus();
    return ret;
  }

  public Game() {}

  public String toString() {

    return "Game{"
        + "playerA := "
        + Utils.toString(playerA)
        + ", playerB := "
        + Utils.toString(playerB)
        + ", currPlayer := "
        + Utils.toString(currPlayer)
        + ", players := "
        + Utils.toString(players)
        + ", currState := "
        + Utils.toString(currState)
        + "}";
  }
}
