package edu.grinnell.csc207.util;

import static java.lang.reflect.Array.newInstance;
import java.lang.StringBuilder;
import java.util.Arrays;

/**
 * A basic implementation of Associative Arrays with keys of type K
 * and values of type V. Associative Arrays store key/value pairs
 * and permit you to look up values by key.
 *
 * @param <K> the key type
 * @param <V> the value type
 *
 * @author Your Name Here
 * @author Samuel A. Rebelsky
 */
public class AssociativeArray<K, V> {
  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * The default capacity of the initial array.
   */
  static final int DEFAULT_CAPACITY = 16;

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The size of the associative array (the number of key/value pairs).
   */
  int size;

  /**
   * The array of key/value pairs.
   */
  KVPair<K, V> pairs[];

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new, empty associative array.
   */
  @SuppressWarnings({ "unchecked" })
  public AssociativeArray() {
    // Creating new arrays is sometimes a PITN.
    this.pairs = (KVPair<K, V>[]) newInstance((new KVPair<K, V>()).getClass(),
        DEFAULT_CAPACITY);
    this.size = 0;
  } // AssociativeArray()

  private AssociativeArray(KVPair<K, V>[] initKvPair, int initSize) { 
    this.pairs = initKvPair;
    this.size = initSize;
  }

  // +------------------+--------------------------------------------
  // | Standard Methods |
  // +------------------+

  /**
   * Create a copy of this AssociativeArray.
   *
   * @return a new copy of the array
   */
  public AssociativeArray<K, V> clone() {
    // need to copy the KV pairs too
    KVPair<K, V>[] copy = Arrays.copyOf(this.pairs, this.pairs.length);
    for(int i = 0; i < copy.length; ++i) { 
      if(copy[i] != null) { 
        copy[i] = copy[i].clone();
      }
    }

    return new AssociativeArray<K, V>(copy, this.size); 
  } // clone()

  /**
   * Convert the array to a string.
   *
   * @return a string of the form "{Key0:Value0, Key1:Value1, ... KeyN:ValueN}"
   */
  public String toString() {
    StringBuilder str = new StringBuilder("{");
    for(int i = 0; i < size - 1; ++i) { 
      str.append(pairs[i].toString());
      str.append(", ");
    } // for 
    return str.append(pairs[size - 1].toString()).append("}").toString();
  } // toString()

  // +----------------+----------------------------------------------
  // | Public Methods |
  // +----------------+

  /**
   * Set the value associated with key to value. Future calls to
   * get(key) will return value.
   *
   * @param K
   *   The key whose value we are seeting.
   * @param V
   *   The value of that key.
   *
   * @throws NullKeyException
   *   If the client provides a null key.
   */
  public void set(K key, V value) throws NullKeyException {
    if (key == null) { 
      throw new NullKeyException();
    } // if null
    try { 
      this.pairs[this.find(key)].val = value;
    } catch (KeyNotFoundException e) { 
      if(this.pairs.length <= size) { 
        this.expand();
      } // expand
      this.pairs[size++] = new KVPair<K, V>(key, value);
    } // try catch
  } // set(K,V)

  /**
   * Get the value associated with key.
   *
   * @param key
   *   A key
   *
   * @throws KeyNotFoundException
   *   when the key is null or does not appear in the associative array.
   */
  public V get(K key) throws KeyNotFoundException {
    return this.pairs[this.find(key)].val;
  } // get(K)

  /**
   * Determine if key appears in the associative array. Should
   * return false for the null key.
   */
  public boolean hasKey(K key) {
    try{ 
      this.find(key);
      return true;
    } catch (KeyNotFoundException e) { 
      return false; 
    }
  } // hasKey(K)

  /**
   * Remove the key/value pair associated with a key. Future calls
   * to get(key) will throw an exception. If the key does not appear
   * in the associative array, does nothing.
   */
  public void remove(K key) {
    try { 
      int index = this.find(key);
      this.pairs[index] = this.pairs[(size--) - 1];
    } catch (KeyNotFoundException e) { 
      return;
    }
  } // remove(K)

  /**
   * Determine how many key/value pairs are in the associative array.
   */
  public int size() {
    return this.size;
  } // size()

  // +-----------------+---------------------------------------------
  // | Private Methods |
  // +-----------------+

  /**
   * Expand the underlying array.
   */
  void expand() {
    this.pairs = Arrays.copyOf(this.pairs, this.pairs.length * 2);
  } // expand()

  /**
   * Find the index of the first entry in `pairs` that contains key.
   * If no such entry is found, throws an exception.
   *
   * @param key
   *   The key of the entry.
   *
   * @throws KeyNotFoundException
   *   If the key does not appear in the associative array.
   */
  int find(K key) throws KeyNotFoundException {
    for(int i = 0; i < size; ++i) { 
      if(this.pairs[i].key.equals(key)) {
        return i;
      } // if 
    } // for 
    throw new KeyNotFoundException(); 
  } // find(K)

} // class AssociativeArray
