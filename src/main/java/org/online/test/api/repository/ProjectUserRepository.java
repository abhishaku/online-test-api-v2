package org.online.test.api.repository;

import org.online.test.api.domain.ProjectUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectUserRepository extends MongoRepository<ProjectUser, String> {

  /**
   * Fetch project user.
   *
   * @param projectId Project ID.
   * @param userId User ID.
   * @param active Project status indicator.
   * @return {@code ProjectUser} Project user object.
   */
  ProjectUser findByProjectIdAndUserIdAndActive(String projectId, String userId, boolean active);
}
