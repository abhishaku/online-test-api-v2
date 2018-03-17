package org.online.test.api.repository;

import org.online.test.api.domain.Organization;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Abhishek on 10/6/17.
 */

@Repository
public interface OrganizationRepository extends MongoRepository<Organization, String> {

  /**
   * Find organization by name.
   *
   * @param name Organization name.
   * @return {@link Organization} Organization Object.
   */
  Organization findByName(String name);
}
