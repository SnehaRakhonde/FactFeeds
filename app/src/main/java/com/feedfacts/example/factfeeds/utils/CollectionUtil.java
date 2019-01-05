package com.feedfacts.example.factfeeds.utils;

import java.util.Collection;

/**
 * Collection Utility.
 *
 * @author Sneha Rakhonde
 */

public class CollectionUtil {
  private CollectionUtil() {
  }

  /**
   * Checks if collection is empty.
   *
   * @param collection
   * @return
   */
  public static boolean isEmpty(Collection<?> collection) {
    return collection == null || collection.isEmpty();
  }

  /**
   * Checks if collection is not empty.
   *
   * @param collection
   * @return
   */
  public static boolean isNotEmpty(Collection<?> collection) {
    return !isEmpty(collection);
  }
}