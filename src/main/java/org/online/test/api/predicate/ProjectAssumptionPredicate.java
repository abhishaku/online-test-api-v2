package org.online.test.api.predicate;

import org.online.test.api.domain.ProjectAssumption;
import org.online.test.api.key.SearchCriteria;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;

public final class ProjectAssumptionPredicate {

  public static BooleanExpression getPredicate(SearchCriteria searchCriteria) {
    PathBuilder<ProjectAssumption> entityPath = new PathBuilder<>(ProjectAssumption.class,
        "projectassumption");

    String value = String.valueOf(searchCriteria.getValue1());
    StringPath path = entityPath.getString(searchCriteria.getKey());
    switch (searchCriteria.getOperation()) {
      case "<=":
        return path.loe(value);
      case ">":
        return path.gt(value);
      case "<":
        return path.lt(value);
      case ">=":
        return path.goe(value);
      case ":":
        return path.containsIgnoreCase(value);
      case "=":
        return path.equalsIgnoreCase(value);
      case "!=":
        return path.notEqualsIgnoreCase(value);
    }
    return null;
  }
}
