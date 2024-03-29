package battleship;
//
// THIS FILE IS AUTOMATICALLY GENERATED!!
//
// Generated at 2017-01-04 by the VDM++ to JAVA Code Generator
// (v9.0.7 - Thu 29-Sep-2016 22:13:23 +0900)
//
// ***** VDMTOOLS START Name=HeaderComment KEEP=NO
// ***** VDMTOOLS END Name=HeaderComment

// This file was genereted from "D:\\battleship-VDM\\Player.vdmpp".

// ***** VDMTOOLS START Name=package KEEP=NO
// ***** VDMTOOLS END Name=package

// ***** VDMTOOLS START Name=imports KEEP=NO
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import jp.vdmtools.VDM.UTIL;
import jp.vdmtools.VDM.Sentinel;
import jp.vdmtools.VDM.EvaluatePP;
import jp.vdmtools.VDM.CGException;
// ***** VDMTOOLS END Name=imports



public class Player implements EvaluatePP {

// ***** VDMTOOLS START Name=name KEEP=NO
  protected volatile String name = null;
// ***** VDMTOOLS END Name=name

// ***** VDMTOOLS START Name=wins KEEP=NO
  protected volatile Number wins = null;
// ***** VDMTOOLS END Name=wins

// ***** VDMTOOLS START Name=losses KEEP=NO
  protected volatile Number losses = null;
// ***** VDMTOOLS END Name=losses

// ***** VDMTOOLS START Name=ownBoard KEEP=NO
  protected volatile Board ownBoard = null;
// ***** VDMTOOLS END Name=ownBoard

// ***** VDMTOOLS START Name=enemyBoard KEEP=NO
  protected volatile Board enemyBoard = null;
// ***** VDMTOOLS END Name=enemyBoard

// ***** VDMTOOLS START Name=myShips KEEP=NO
  protected volatile Set myShips = new HashSet();
// ***** VDMTOOLS END Name=myShips

// ***** VDMTOOLS START Name=enemiesShips KEEP=NO
  protected volatile Set enemiesShips = new HashSet();
// ***** VDMTOOLS END Name=enemiesShips

// ***** VDMTOOLS START Name=sentinel KEEP=NO
  volatile PlayerSentinel sentinel;
// ***** VDMTOOLS END Name=sentinel


// ***** VDMTOOLS START Name=PlayerSentinel KEEP=NO
  class PlayerSentinel extends Sentinel {

    public final int Player = 0;

    public final int getName = 1;

    public final int addBoards = 2;

    public final int clearData = 3;

    public final int printInfo = 4;

    public final int startRounds = 5;

    public final int printVictory = 6;

    public final int printTakeDown = 7;

    public final int shipPlacement = 8;

    public final int allShipsPlaced = 9;

    public final int registerAttack = 10;

    public final int registerResult = 11;

    public final int printGameStatus = 12;

    public final int printPlacementStatus = 13;

    public final int nr_functions = 14;

    public PlayerSentinel () throws CGException {}

    public PlayerSentinel (EvaluatePP instance) throws CGException {
      init(nr_functions, instance);
    }

  }
// ***** VDMTOOLS END Name=PlayerSentinel

// ***** VDMTOOLS START Name=evaluatePP#1|int KEEP=NO
  public Boolean evaluatePP (int fnr) throws CGException {
    return Boolean.TRUE;
  }
// ***** VDMTOOLS END Name=evaluatePP#1|int

// ***** VDMTOOLS START Name=setSentinel KEEP=NO
  public void setSentinel () {
    try {
      sentinel = new PlayerSentinel(this);
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
// ***** VDMTOOLS END Name=setSentinel

// ***** VDMTOOLS START Name=vdm_init_Player KEEP=NO
  private void vdm_init_Player () {
    try {
      setSentinel();
      name = "";
      ownBoard = null;
      enemyBoard = null;
      myShips = new HashSet();
      enemiesShips = new HashSet();
    }
    catch (Exception e) {
      e.printStackTrace(System.out);
      System.out.println(e.getMessage());
    }
  }
// ***** VDMTOOLS END Name=vdm_init_Player

// ***** VDMTOOLS START Name=inv_Player KEEP=NO
  public Boolean inv_Player () {
    return Boolean.valueOf(name.length() < 256);
  }
// ***** VDMTOOLS END Name=inv_Player

// ***** VDMTOOLS START Name=Player KEEP=NO
  public Player () throws CGException {
    vdm_init_Player();
  }
// ***** VDMTOOLS END Name=Player

// ***** VDMTOOLS START Name=Player#1|String KEEP=NO
  public Player (final String nameArg) throws CGException {
    vdm_init_Player();
    name = UTIL.ConvertToString(UTIL.clone(nameArg));
    wins = UTIL.NumberToInt(UTIL.clone(Integer.valueOf(0)));
    losses = UTIL.NumberToInt(UTIL.clone(Integer.valueOf(0)));
  }
// ***** VDMTOOLS END Name=Player#1|String

// ***** VDMTOOLS START Name=getName KEEP=NO
  public String getName () throws CGException {
    sentinel.entering(sentinel.getName);
    try {
      return name;
    }
    finally {
      sentinel.leaving(sentinel.getName);
    }
  }
// ***** VDMTOOLS END Name=getName

// ***** VDMTOOLS START Name=addBoards KEEP=NO
  public void addBoards () throws CGException {
    sentinel.entering(sentinel.addBoards);
    try {
      if (!this.pre_addBoards().booleanValue()) 
        UTIL.RunTime("Precondition failure in addBoards");
      ownBoard = new Board();
      if (!this.inv_Player().booleanValue()) 
        UTIL.RunTime("Instance invariant failure in Player");
      enemyBoard = new Board();
      if (!this.inv_Player().booleanValue()) 
        UTIL.RunTime("Instance invariant failure in Player");
      myShips = (Set)UTIL.clone(ownBoard.getShips());
      if (!this.inv_Player().booleanValue()) 
        UTIL.RunTime("Instance invariant failure in Player");
    }
    finally {
      sentinel.leaving(sentinel.addBoards);
    }
  }
// ***** VDMTOOLS END Name=addBoards

// ***** VDMTOOLS START Name=pre_addBoards KEEP=NO
  public Boolean pre_addBoards () throws CGException {
    return Boolean.valueOf(UTIL.equals(ownBoard, null) ? UTIL.equals(enemyBoard, null) : false);
  }
// ***** VDMTOOLS END Name=pre_addBoards

// ***** VDMTOOLS START Name=shipPlacement#4|Object|Character|Number|Object KEEP=NO
  public void shipPlacement (final Object ship, final Character colCh, final Number line, final Object dir) throws CGException {
    sentinel.entering(sentinel.shipPlacement);
    try {
      if (!this.pre_shipPlacement(ship, colCh, line, dir).booleanValue()) 
        UTIL.RunTime("Precondition failure in shipPlacement");
      ownBoard.placeShip(ship, colCh, line, dir);
      Set rhs_10 = new HashSet();
      Set var2_12 = new HashSet();
      var2_12.add(ship);
      rhs_10 = new HashSet(myShips);
      rhs_10.removeAll(var2_12);
      myShips = (Set)UTIL.clone(rhs_10);
      if (!this.inv_Player().booleanValue()) 
        UTIL.RunTime("Instance invariant failure in Player");
    }
    finally {
      sentinel.leaving(sentinel.shipPlacement);
    }
  }
// ***** VDMTOOLS END Name=shipPlacement#4|Object|Character|Number|Object

// ***** VDMTOOLS START Name=pre_shipPlacement#4|Object|Character|Number|Object KEEP=NO
  public Boolean pre_shipPlacement (final Object ship, final Character colCh, final Number line, final Object dir) throws CGException {
    return Boolean.valueOf(UTIL.Contains(myShips, ship));
  }
// ***** VDMTOOLS END Name=pre_shipPlacement#4|Object|Character|Number|Object

// ***** VDMTOOLS START Name=allShipsPlaced KEEP=NO
  public Boolean allShipsPlaced () throws CGException {
    sentinel.entering(sentinel.allShipsPlaced);
    try {
      return Boolean.valueOf(myShips.size() == 0);
    }
    finally {
      sentinel.leaving(sentinel.allShipsPlaced);
    }
  }
// ***** VDMTOOLS END Name=allShipsPlaced

// ***** VDMTOOLS START Name=startRounds KEEP=NO
  public void startRounds () throws CGException {
    sentinel.entering(sentinel.startRounds);
    try {
      myShips = (Set)UTIL.clone(ownBoard.getShips());
      if (!this.inv_Player().booleanValue()) 
        UTIL.RunTime("Instance invariant failure in Player");
      enemiesShips = new HashSet();
      if (!this.inv_Player().booleanValue()) 
        UTIL.RunTime("Instance invariant failure in Player");
    }
    finally {
      sentinel.leaving(sentinel.startRounds);
    }
  }
// ***** VDMTOOLS END Name=startRounds

// ***** VDMTOOLS START Name=clearData KEEP=NO
  public void clearData () throws CGException {
    sentinel.entering(sentinel.clearData);
    try {
      ownBoard = null;
      if (!this.inv_Player().booleanValue()) 
        UTIL.RunTime("Instance invariant failure in Player");
      enemyBoard = null;
      if (!this.inv_Player().booleanValue()) 
        UTIL.RunTime("Instance invariant failure in Player");
      myShips = new HashSet();
      if (!this.inv_Player().booleanValue()) 
        UTIL.RunTime("Instance invariant failure in Player");
      enemiesShips = new HashSet();
      if (!this.inv_Player().booleanValue()) 
        UTIL.RunTime("Instance invariant failure in Player");
    }
    finally {
      sentinel.leaving(sentinel.clearData);
    }
  }
// ***** VDMTOOLS END Name=clearData

// ***** VDMTOOLS START Name=registerAttack#2|Character|Number KEEP=NO
  public Object registerAttack (final Character colCh, final Number line) throws CGException {
    sentinel.entering(sentinel.registerAttack);
    try {
      Object shipHit = ownBoard.registerMove(colCh, line);
      if (ownBoard.countCellType(shipHit).intValue() == 0) {
        Set rhs_14 = new HashSet();
        Set var2_16 = new HashSet();
        var2_16.add(shipHit);
        rhs_14 = new HashSet(myShips);
        rhs_14.removeAll(var2_16);
        myShips = (Set)UTIL.clone(rhs_14);
        if (!this.inv_Player().booleanValue()) 
          UTIL.RunTime("Instance invariant failure in Player");
        if (myShips.size() == 0) {
          losses = UTIL.NumberToInt(UTIL.clone(Integer.valueOf(losses.intValue() + 1)));
          if (!this.inv_Player().booleanValue()) 
            UTIL.RunTime("Instance invariant failure in Player");
        }
        return shipHit;
      }
      else if (!UTIL.equals(shipHit, new quotes.Empty())) 
        return new quotes.Hit();
      else 
        return new quotes.Miss();
    }
    finally {
      sentinel.leaving(sentinel.registerAttack);
    }
  }
// ***** VDMTOOLS END Name=registerAttack#2|Character|Number

// ***** VDMTOOLS START Name=registerResult#3|Object|Character|Number KEEP=NO
  public Boolean registerResult (final Object code, final Character colCh, final Number line) throws CGException {
    sentinel.entering(sentinel.registerResult);
    try {
      if (UTIL.equals(code, new quotes.Miss())) 
        enemyBoard.setComponentCol(new quotes.Miss(), line, colCh);
      else {
        enemyBoard.setComponentCol(new quotes.Hit(), line, colCh);
        if (!UTIL.equals(code, new quotes.Hit())) {
          enemiesShips.add(code);
          if (!this.inv_Player().booleanValue()) 
            UTIL.RunTime("Instance invariant failure in Player");
        }
        if (enemiesShips.size() == enemyBoard.getShipsCount().intValue()) {
          wins = UTIL.NumberToInt(UTIL.clone(Integer.valueOf(wins.intValue() + 1)));
          if (!this.inv_Player().booleanValue()) 
            UTIL.RunTime("Instance invariant failure in Player");
          return Boolean.TRUE;
        }
      }
      return Boolean.FALSE;
    }
    finally {
      sentinel.leaving(sentinel.registerResult);
    }
  }
// ***** VDMTOOLS END Name=registerResult#3|Object|Character|Number

// ***** VDMTOOLS START Name=printInfo KEEP=NO
  public String printInfo () throws CGException {
    sentinel.entering(sentinel.printInfo);
    try {
      return name.concat(" (").concat(VDMUtil.val2seq_of_char(wins)).concat("-").concat(VDMUtil.val2seq_of_char(losses)).concat(")\n");
    }
    finally {
      sentinel.leaving(sentinel.printInfo);
    }
  }
// ***** VDMTOOLS END Name=printInfo

// ***** VDMTOOLS START Name=printPlacementStatus KEEP=NO
  public String printPlacementStatus () throws CGException {
    sentinel.entering(sentinel.printPlacementStatus);
    try {
      return new String("Fleet placement\nPlayer turn: ").concat(name).concat("\n").concat("Ships to be placed: ").concat(ownBoard.printRemainShips(myShips)).concat("\n\n").concat(ownBoard.printBoard());
    }
    finally {
      sentinel.leaving(sentinel.printPlacementStatus);
    }
  }
// ***** VDMTOOLS END Name=printPlacementStatus

// ***** VDMTOOLS START Name=printGameStatus KEEP=NO
  public String printGameStatus () throws CGException {
    sentinel.entering(sentinel.printGameStatus);
    try {
      String ret = new String("Player turn: ").concat(name).concat("\nMy active ships: ");
      {
        Set tmpSet_16 = new HashSet(myShips);
        for (Iterator enm_15 = tmpSet_16.iterator(); enm_15.hasNext(); ) {
          final Object ship = enm_15.next();
          ret = UTIL.ConvertToString(UTIL.clone(ret.concat(ownBoard.shipToString(ship)).concat("   ")));
          if (!this.inv_Player().booleanValue()) 
            UTIL.RunTime("Instance invariant failure in Player");
        }
      }
      ret = UTIL.ConvertToString(UTIL.clone(ret.concat("\nDestroyed enemies ships: ")));
      if (!this.inv_Player().booleanValue()) 
        UTIL.RunTime("Instance invariant failure in Player");
      {
        Set tmpSet_31 = new HashSet(enemiesShips);
        for (Iterator enm_30 = tmpSet_31.iterator(); enm_30.hasNext(); ) {
          final Object ship = enm_30.next();
          ret = UTIL.ConvertToString(UTIL.clone(ret.concat(enemyBoard.shipToString(ship)).concat("   ")));
          if (!this.inv_Player().booleanValue()) 
            UTIL.RunTime("Instance invariant failure in Player");
        }
      }
      ret = UTIL.ConvertToString(UTIL.clone(ret.concat("\n\n                                 My ships \t\t\t\t\t\t\t\t\t                Enemy ships\n\n\n")));
      if (!this.inv_Player().booleanValue()) 
        UTIL.RunTime("Instance invariant failure in Player");
      ret = UTIL.ConvertToString(UTIL.clone(ret.concat(ownBoard.printParallelBoards(enemyBoard))));
      if (!this.inv_Player().booleanValue()) 
        UTIL.RunTime("Instance invariant failure in Player");
      return ret;
    }
    finally {
      sentinel.leaving(sentinel.printGameStatus);
    }
  }
// ***** VDMTOOLS END Name=printGameStatus

// ***** VDMTOOLS START Name=printTakeDown#1|Object KEEP=NO
  public String printTakeDown (final Object shipDown) throws CGException {
    sentinel.entering(sentinel.printTakeDown);
    try {
      return new String("\n\n").concat(enemyBoard.shipToString(shipDown)).concat(" is sinking\n");
    }
    finally {
      sentinel.leaving(sentinel.printTakeDown);
    }
  }
// ***** VDMTOOLS END Name=printTakeDown#1|Object

// ***** VDMTOOLS START Name=printVictory KEEP=NO
  public String printVictory () throws CGException {
    sentinel.entering(sentinel.printVictory);
    try {
      return "\nEnemy fleet destroyed. Victory!\n";
    }
    finally {
      sentinel.leaving(sentinel.printVictory);
    }
  }
// ***** VDMTOOLS END Name=printVictory

}

