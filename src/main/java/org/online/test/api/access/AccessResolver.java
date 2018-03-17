package org.online.test.api.access;

import org.online.test.api.key.ResourceKey;

/**
 * @author Abhishek
 */
public interface AccessResolver<T extends ResourceKey> {

  boolean check(T authKey);
}
