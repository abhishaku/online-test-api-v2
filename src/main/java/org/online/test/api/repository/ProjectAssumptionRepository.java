package org.online.test.api.repository;

import java.util.List;

import org.online.test.api.domain.ProjectAssumption;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface ProjectAssumptionRepository extends MongoRepository<ProjectAssumption, String>,
    QueryDslPredicateExecutor<ProjectAssumption> {

  /**
   * Find project assumptions.
   *
   * @param projectId Project ID.
   * @return {@code List<ProjectAssumption>} List of project assumptions.
   */
  List<ProjectAssumption> findByProjectId(String projectId);
}
