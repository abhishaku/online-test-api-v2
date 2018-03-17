package org.online.test.api.utils;

import java.util.UUID;

/**
 * Created by Abhishek on 10/6/17.
 */
public final class UniqueIdGenerator {

  private UniqueIdGenerator() {
  }

  public static String generate() {
    return UUID.randomUUID().toString();
  }
}
