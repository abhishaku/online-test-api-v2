package org.online.test.api.access.expression;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

/**
 * @author Abhishek
 */
public class ResourceAccessExpression extends SecurityExpressionRoot implements
    MethodSecurityExpressionOperations {

  public ResourceAccessExpression(Authentication authentication) {
    super(authentication);
  }

  @Override
  public void setFilterObject(Object filterObject) {

  }

  @Override
  public Object getFilterObject() {
    return null;
  }

  @Override
  public void setReturnObject(Object returnObject) {

  }

  @Override
  public Object getReturnObject() {
    return null;
  }

  @Override
  public Object getThis() {
    return null;
  }
}
