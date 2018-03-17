package org.online.test.api.utils;

import org.online.test.api.domain.User;
import org.online.test.api.exception.ApplicationException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Abhishek
 */
public class SecurityUtil {

  public static User loggedInUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication instanceof AnonymousAuthenticationToken) {
      throw new ApplicationException("No user session available.");
    }

    return (User) authentication.getPrincipal();
  }

  public static String loggedInUserEmail() {
    return loggedInUser().getEmail();
  }
}
