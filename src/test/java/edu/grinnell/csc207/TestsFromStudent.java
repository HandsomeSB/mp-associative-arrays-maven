package edu.grinnell.csc207;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import edu.grinnell.csc207.util.AssociativeArray;
import edu.grinnell.csc207.util.KeyNotFoundException;
import edu.grinnell.csc207.util.NullKeyException;
import org.junit.jupiter.api.Test;

/**
 * A place for you to put your own tests (beyond the shared repo).
 *
 * @author Your Name Here
 */
public class TestsFromStudent {
  /** A simple test. */
  @Test
  public void alwaysPass() throws Exception {} // alwaysPass()

  @Test
  public void testNullKey() throws Exception {
    AssociativeArray<Integer, Integer> arr = new AssociativeArray<Integer, Integer>();
    try {
      arr.set(null, 1);
      fail("should throw error");
    } catch (NullKeyException e) {

    } catch (Exception e) {
      fail("Should throw NullKeyException");
    }

    try {
      arr.get(null);
    } catch (KeyNotFoundException e) {

    } catch (Exception e) {
      fail("Should throw KeyNotFoundException");
    }

    try {
      assertFalse(arr.hasKey(null));
    } catch (Exception e) {
      fail("should not throw exception");
    }
  }
} // class TestsFromSam
