package edu.grinnell.csc207;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.grinnell.csc207.util.AssociativeArray;
import org.junit.jupiter.api.Test;

/**
 * Some additional tests from SamR.
 *
 * @author Samuel A. Rebelsky
 */
public class TestsFromSam {
  /** A simple test. */
  @Test
  public void testSetAndGet() throws Exception {
    AssociativeArray<String, String> aa = new AssociativeArray<String, String>();
    aa.set("a", "aardvark");
    assertEquals("aardvark", aa.get("a"), "M: We can get what we just set");
  } // testSetAndGet()
} // class TestsFromSam
