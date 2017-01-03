package Battleship;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class TestBase {
  public static void assertTrue(final Boolean cond) {

    return;
  }

  public static void assertEqual(final Object result, final Object expected) {

    return;
  }

  public static void runAllTests() {

    TestBoard tb = new TestBoard();
    TestPlayer tp = new TestPlayer();
    TestGame tg = new TestGame();
    tb.run();
    tp.run();
    tg.run();
  }

  public TestBase() {}

  public String toString() {

    return "TestBase{}";
  }
}
